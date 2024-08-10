package com.jp.controller;

import com.jp.entity.Transacciones;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet("/contabilizar")
public class ContabilizarController extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("WebApplication3PU");
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("consultar".equals(action)) {
            consultarTransacciones(request, response);
        } else if ("contabilizar".equals(action)) {
            contabilizarTransacciones(request, response);
        } else {
            request.setAttribute("error", "Acción desconocida: " + action);
            request.getRequestDispatcher("/contabilizar.jsp").forward(request, response);
        }
    }

    private void consultarTransacciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        if (startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()) {
            try {
                java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);
                java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);

                EntityManager em = emf.createEntityManager();
                List<Transacciones> transaccionesList = em.createQuery("SELECT t FROM Transacciones t WHERE t.fecha BETWEEN :startDate AND :endDate AND t.idAsiento IS NULL", Transacciones.class)
                        .setParameter("startDate", startDate)
                        .setParameter("endDate", endDate)
                        .getResultList();
                em.close();

                request.setAttribute("transaccionesList", transaccionesList);
                request.getRequestDispatcher("/contabilizar.jsp").forward(request, response);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Error en la conversión de fechas: " + e.getMessage());
                request.getRequestDispatcher("/contabilizar.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Las fechas de inicio y fin son obligatorias.");
            request.getRequestDispatcher("/contabilizar.jsp").forward(request, response);
        }
    }

   

   private String getDescripcion(int tipoTransaccionId, int cantidad) {
    String tipo;
    switch (tipoTransaccionId) {
        case 1:
            tipo = "compra";
            break;
        case 2:
            tipo = "venta";
            break;
        default:
            tipo = "transacción desconocida";
            break;
    }
    return tipo + " de " + cantidad + " mercancías";
}

private int getCuentaDebito(int tipoTransaccionId) {
    switch (tipoTransaccionId) {
        case 1:
            return 6;  // Inventario
        case 2:
            return 12; // Ventas
        default:
            // Retornar un valor que indique que no se encontró una cuenta válida
            throw new IllegalArgumentException("Tipo de transacción no válido para cuenta de débito: " + tipoTransaccionId);
    }
}

private int getCuentaCredito(int tipoTransaccionId) {
    switch (tipoTransaccionId) {
        case 1:
            return 82; // Cuentas por pagar proveedores
        case 2:
            return 6;  // Inventario
        default:
            // Retornar un valor que indique que no se encontró una cuenta válida
            throw new IllegalArgumentException("Tipo de transacción no válido para cuenta de crédito: " + tipoTransaccionId);
    }
}

    private int asientoContable(int idAuxiliarOrigen, String descripcion, int cuentaDB, int cuentaCR, double monto) {
        com.jp.ws.SSWS service = new com.jp.ws.SSWS();
        com.jp.ws.SSWSSoap port = service.getSSWSSoap();
        return port.asientoContable(6, descripcion, cuentaDB, cuentaCR, monto);
    }
    
    private void contabilizarTransacciones(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    List<Transacciones> transaccionesList = (List<Transacciones>) session.getAttribute("transaccionesList");

    if (transaccionesList == null || transaccionesList.isEmpty()) {
        request.setAttribute("error", "No hay transacciones para contabilizar.");
        request.getRequestDispatcher("/contabilizar.jsp").forward(request, response);
        return;
    }

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    try {
        for (Transacciones transaccion : transaccionesList) {
            String descripcion = getDescripcion(transaccion.getTipoTransaccionId().getId(), transaccion.getCantidad());
            int cuentaDB = getCuentaDebito(transaccion.getTipoTransaccionId().getId());
            int cuentaCR = getCuentaCredito(transaccion.getTipoTransaccionId().getId());
            double monto = transaccion.getMonto();
            int idAsiento = asientoContable(6, descripcion, cuentaDB, cuentaCR, monto);

            if (idAsiento <= 0) {
                throw new IllegalStateException("No se pudo crear el asiento contable para la transacción ID: " + transaccion.getId());
            }

            // Obtener la transacción desde la base de datos y actualizarla
            Transacciones transaccionActualizada = em.find(Transacciones.class, transaccion.getId());
            if (transaccionActualizada != null) {
                transaccionActualizada.setIdAsiento(idAsiento);
                em.merge(transaccionActualizada); // Aquí se guarda la actualización
            } else {
                throw new IllegalStateException("No se encontró la transacción con ID: " + transaccion.getId());
            }

            System.out.println("ID Asiento " + idAsiento + " asignado y guardado para la transacción ID: " + transaccion.getId());
        }
        em.getTransaction().commit();

        // Limpiar la lista de transacciones en la sesión
        session.removeAttribute("transaccionesList");

    } catch (IllegalStateException e) {
        em.getTransaction().rollback();
        throw new ServletException("Error durante la contabilización de transacciones", e);
    } finally {
        em.close();
    }

    request.getRequestDispatcher("/contabilizar.jsp").forward(request, response);
}
}
