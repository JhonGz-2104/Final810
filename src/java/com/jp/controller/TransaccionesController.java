package com.jp.controller;

import com.jp.entity.Articulos;
import com.jp.entity.TipoTransaccion;
import com.jp.entity.Transacciones;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet("/transacciones")
public class TransaccionesController extends HttpServlet {

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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TransaccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TransaccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String action = request.getParameter("action");

        if (action == null) {
            list(request, response);
        } else {
            switch (action) {
                case "create" -> create(request, response);
                case "edit" -> edit(request, response);
                case "delete" -> delete(request, response);
                case "view" -> view(request, response);
                default -> list(request, response);
            }
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Transacciones> transaccionesList = em.createNamedQuery("Transacciones.findAll", Transacciones.class).getResultList();
        request.setAttribute("transaccionesList", transaccionesList);
        request.getRequestDispatcher("/WEB-INF/views/Transacciones/list.jsp").forward(request, response);
        em.close();
    }

    private void loadRelatedData(HttpServletRequest request) {
        EntityManager em = emf.createEntityManager();

        // Cargar lista de artículos
        List<Articulos> articulosList = em.createNamedQuery("Articulos.findAll", Articulos.class).getResultList();
        request.setAttribute("articulosList", articulosList);

        // Cargar lista de tipos de transacción
        List<TipoTransaccion> tipoTransaccionList = em.createNamedQuery("TipoTransaccion.findAll", TipoTransaccion.class).getResultList();
        request.setAttribute("tipoTransaccionList", tipoTransaccionList);

        em.close();
    }

private void create(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String cantidadStr = request.getParameter("cantidad");
    String fechaStr = request.getParameter("fecha");
    String articuloIdStr = request.getParameter("articuloId");
    String tipoTransaccionIdStr = request.getParameter("tipoTransaccionId");

    // Verifica que todos los parámetros requeridos no sean nulos ni vacíos
    if (cantidadStr == null || cantidadStr.isEmpty() || fechaStr == null || fechaStr.isEmpty() ||
        articuloIdStr == null || articuloIdStr.isEmpty() || tipoTransaccionIdStr == null || tipoTransaccionIdStr.isEmpty()) {
        loadRelatedData(request);
        request.setAttribute("error", "Parámetros obligatorios no proporcionados.");
        request.getRequestDispatcher("/WEB-INF/views/Transacciones/form.jsp").forward(request, response);
        return;
    }

    EntityManager em = emf.createEntityManager();
    Transacciones transaccion = new Transacciones();
    
    // Intenta convertir y asignar los valores, manejando posibles errores
    try {
        transaccion.setCantidad(Integer.parseInt(cantidadStr));
        transaccion.setFecha(java.sql.Date.valueOf(fechaStr));
        transaccion.setArticuloId(em.find(Articulos.class, Integer.parseInt(articuloIdStr)));
        transaccion.setTipoTransaccionId(em.find(TipoTransaccion.class, Integer.parseInt(tipoTransaccionIdStr)));
    } catch (NumberFormatException e) {
        loadRelatedData(request);
        request.setAttribute("error", "Error al procesar los datos proporcionados.");
        request.getRequestDispatcher("/WEB-INF/views/Transacciones/form.jsp").forward(request, response);
        return;
    }

    em.getTransaction().begin();
    em.persist(transaccion);
    em.getTransaction().commit();
    em.close();
    response.sendRedirect("transacciones?action=list");
}

private void edit(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();
    Integer id = Integer.valueOf(request.getParameter("id"));
    Transacciones transaccion = em.find(Transacciones.class, id);

    // Carga de datos relacionados
    loadRelatedData(request);

    // Establece la transacción en la solicitud para que el formulario lo use
    request.setAttribute("transaccion", transaccion);

    // Despacha al formulario de edición
    request.getRequestDispatcher("/WEB-INF/views/Transacciones/form.jsp").forward(request, response);
    em.close();
}

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.valueOf(request.getParameter("id"));
        Transacciones transaccion = em.find(Transacciones.class, id);
        em.getTransaction().begin();
        em.remove(transaccion);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("transacciones?action=list");
    }

  private void view(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();
    Integer id = Integer.parseInt(request.getParameter("id"));
    Transacciones transaccion = em.find(Transacciones.class, id);

    if (transaccion != null) {
        request.setAttribute("transaccion", transaccion);
    } else {
        request.setAttribute("error", "Transacción no encontrada.");
    }

    request.getRequestDispatcher("/WEB-INF/views/Transacciones/view.jsp").forward(request, response);
    em.close();
}

}
