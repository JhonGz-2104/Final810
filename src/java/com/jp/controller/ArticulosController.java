package com.jp.controller;

import com.jp.entity.Articulos;
import com.jp.entity.Almacen;
import com.jp.entity.EstadoArticulo;
import com.jp.entity.TipoInventario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet("/articulos")
public class ArticulosController extends HttpServlet {

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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            list(request, response);
        } else {
            switch (action) {
                case "create" -> {
                    loadRelatedData(request);
                    create(request, response);
                }
                case "edit" -> {
                    loadRelatedData(request);
                    edit(request, response);
                }
                case "delete" -> delete(request, response);
                case "view" -> view(request, response);
                case "search" -> search(request, response);
                default -> list(request, response);
            }
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Articulos> articulosList = em.createNamedQuery("Articulos.findAll", Articulos.class).getResultList();
        request.setAttribute("articulosList", articulosList);
        request.getRequestDispatcher("/WEB-INF/views/Articulos/list.jsp").forward(request, response);
        em.close();
    }

  private void create(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();
    Articulos articulo = new Articulos();

    // Validación de los parámetros antes de procesarlos
    String descripcion = request.getParameter("descripcion");
    String existenciasStr = request.getParameter("existencias");
    String precioVentaStr = request.getParameter("precio_venta");
    String precioCompraStr = request.getParameter("precio_compra");
    String almacenIdStr = request.getParameter("almacen_id");
    String estadoArticuloIdStr = request.getParameter("estado_articulo_id");
    String tipoInventarioIdStr = request.getParameter("tipo_inventario_id");

    if (descripcion == null || descripcion.isEmpty() ||
        existenciasStr == null || existenciasStr.isEmpty() ||
        precioVentaStr == null || precioVentaStr.isEmpty() ||
        precioCompraStr == null || precioCompraStr.isEmpty() ||
        almacenIdStr == null || almacenIdStr.isEmpty() ||
        estadoArticuloIdStr == null || estadoArticuloIdStr.isEmpty() ||
        tipoInventarioIdStr == null || tipoInventarioIdStr.isEmpty()) {

        request.setAttribute("error", "Todos los campos son obligatorios.");
        loadRelatedData(request); // Recargar las listas desplegables
        request.getRequestDispatcher("/WEB-INF/views/Articulos/form.jsp").forward(request, response);
        return;
    }

    articulo.setDescripcion(descripcion);
    articulo.setExistencias(Integer.parseInt(existenciasStr));
    articulo.setPrecioVenta(Double.parseDouble(precioVentaStr));
    articulo.setPrecioCompra(Double.parseDouble(precioCompraStr));

    articulo.setAlmacenId(em.find(Almacen.class, Integer.parseInt(almacenIdStr)));
    articulo.setEstadoArticuloId(em.find(EstadoArticulo.class, Integer.parseInt(estadoArticuloIdStr)));
    articulo.setTipoInventarioId(em.find(TipoInventario.class, Integer.parseInt(tipoInventarioIdStr)));

    em.getTransaction().begin();
    em.persist(articulo);
    em.getTransaction().commit();
    em.close();
    response.sendRedirect("articulos?action=list");
}


 private void edit(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();
    Integer id = Integer.parseInt(request.getParameter("id"));
    Articulos articulo = em.find(Articulos.class, id);

    // Validación de los parámetros antes de procesarlos
    String descripcion = request.getParameter("descripcion");
    String existenciasStr = request.getParameter("existencias");
    String precioVentaStr = request.getParameter("precio_venta");
    String precioCompraStr = request.getParameter("precio_compra");
    String almacenIdStr = request.getParameter("almacen_id");
    String estadoArticuloIdStr = request.getParameter("estado_articulo_id");
    String tipoInventarioIdStr = request.getParameter("tipo_inventario_id");

    if (descripcion == null || descripcion.isEmpty() ||
        existenciasStr == null || existenciasStr.isEmpty() ||
        precioVentaStr == null || precioVentaStr.isEmpty() ||
        precioCompraStr == null || precioCompraStr.isEmpty() ||
        almacenIdStr == null || almacenIdStr.isEmpty() ||
        estadoArticuloIdStr == null || estadoArticuloIdStr.isEmpty() ||
        tipoInventarioIdStr == null || tipoInventarioIdStr.isEmpty()) {

        request.setAttribute("error", "Todos los campos son obligatorios.");
        request.setAttribute("articulo", articulo);
        loadRelatedData(request); // Recargar las listas desplegables
        request.getRequestDispatcher("/WEB-INF/views/Articulos/form.jsp").forward(request, response);
        return;
    }

    articulo.setDescripcion(descripcion);
    articulo.setExistencias(Integer.parseInt(existenciasStr));
    articulo.setPrecioVenta(Double.parseDouble(precioVentaStr));
    articulo.setPrecioCompra(Double.parseDouble(precioCompraStr));

    articulo.setAlmacenId(em.find(Almacen.class, Integer.parseInt(almacenIdStr)));
    articulo.setEstadoArticuloId(em.find(EstadoArticulo.class, Integer.parseInt(estadoArticuloIdStr)));
    articulo.setTipoInventarioId(em.find(TipoInventario.class, Integer.parseInt(tipoInventarioIdStr)));

    em.getTransaction().begin();
    em.merge(articulo);
    em.getTransaction().commit();
    em.close();
    response.sendRedirect("articulos?action=list");
}

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Articulos articulo = em.find(Articulos.class, id);
        em.getTransaction().begin();
        em.remove(articulo);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("articulos?action=list");
    }

private void view(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();
    Integer id = Integer.parseInt(request.getParameter("id"));
    Articulos articulo = em.find(Articulos.class, id);

    if (articulo != null) {
        request.setAttribute("articulo", articulo);
    } else {
        request.setAttribute("error", "Artículo no encontrado.");
    }

    request.getRequestDispatcher("/WEB-INF/views/Articulos/view.jsp").forward(request, response);
    em.close();
}


    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String descripcion = request.getParameter("descripcion");
        List<Articulos> articulosList = em.createNamedQuery("Articulos.findByDescripcion", Articulos.class)
                                          .setParameter("descripcion", descripcion)
                                          .getResultList();
        request.setAttribute("articulosList", articulosList);
        request.getRequestDispatcher("/WEB-INF/views/Articulos/list.jsp").forward(request, response);
        em.close();
    }

    private void loadRelatedData(HttpServletRequest request) {
        EntityManager em = emf.createEntityManager();
        
        // Cargar lista de almacenes
        List<Almacen> almacenList = em.createNamedQuery("Almacen.findAll", Almacen.class).getResultList();
        request.setAttribute("almacenList", almacenList);
        
        // Cargar lista de tipos de inventario
        List<TipoInventario> tipoInventarioList = em.createNamedQuery("TipoInventario.findAll", TipoInventario.class).getResultList();
        request.setAttribute("tipoInventarioList", tipoInventarioList);
        
        // Cargar lista de estados de artículo
        List<EstadoArticulo> estadoArticuloList = em.createNamedQuery("EstadoArticulo.findAll", EstadoArticulo.class).getResultList();
        request.setAttribute("estadoArticuloList", estadoArticuloList);
        
        em.close();
    }
}
