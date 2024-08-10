package com.jp.controller;

import com.jp.entity.Almacen;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.registry.infomodel.User;


@WebServlet("/almacen")
public class AlmacenController extends HttpServlet {

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
                case "create" -> create(request, response);
                case "edit" -> edit(request, response);
                case "delete" -> delete(request, response);
                case "view" -> view(request, response);
                case "search" -> search(request, response);
                default -> list(request, response);
            }
        }
    }
 private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            List<Almacen> almacenList = em.createNamedQuery("Almacen.findAll", Almacen.class).getResultList();
            
            // Debug: Verificar tamaño de la lista
            System.out.println("Número de almacenes encontrados: " + almacenList.size());

            request.setAttribute("almacenList", almacenList);
            request.getRequestDispatcher("/WEB-INF/views/Almacen/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar almacenes.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



private void create(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();
    Almacen almacen = new Almacen();
    
    // Obtener y validar el parámetro "descripcion"
    String descripcion = request.getParameter("descripcion");
    if (descripcion == null || descripcion.trim().isEmpty()) {
        // Manejar el error, reenviar a la misma página con un mensaje de error
        request.setAttribute("error", "La descripción no puede estar vacía.");
        request.setAttribute("almacen", almacen);
        request.setAttribute("accion", "create");
        request.getRequestDispatcher("/WEB-INF/views/Almacen/form.jsp").forward(request, response);
        return;
    }
    
    almacen.setDescripcion(descripcion);
    
    em.getTransaction().begin();
    em.persist(almacen);
    em.getTransaction().commit();
    em.close();
    response.sendRedirect("almacen?action=list");
}

private void edit(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();
    Integer id = Integer.parseInt(request.getParameter("id"));
    Almacen almacen = em.find(Almacen.class, id);

    // Obtener y validar el parámetro "descripcion"
    String descripcion = request.getParameter("descripcion");
    if (descripcion == null || descripcion.trim().isEmpty()) {
        // Manejar el error, reenviar a la misma página con un mensaje de error
        request.setAttribute("error", "La descripción no puede estar vacía.");
        request.setAttribute("almacen", almacen);
        request.setAttribute("accion", "edit");
        request.getRequestDispatcher("/WEB-INF/views/Almacen/form.jsp").forward(request, response);
        return;
    }
    
    almacen.setDescripcion(descripcion);
    
    em.getTransaction().begin();
    em.merge(almacen);
    em.getTransaction().commit();
    em.close();
    response.sendRedirect("almacen?action=list");
}
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Almacen almacen = em.find(Almacen.class, id);
        em.getTransaction().begin();
        em.remove(almacen);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("almacen?action=list");
    }

private void view(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String idParam = request.getParameter("id");

    // Validación del parámetro 'id'
    if (idParam == null || idParam.trim().isEmpty()) {
        // Manejar el error: redirigir a la lista o mostrar un mensaje de error
        request.setAttribute("error", "El ID del almacén no es válido.");
        request.getRequestDispatcher("/WEB-INF/views/Almacen/list.jsp").forward(request, response);
        return;
    }

    Integer id = null;
    try {
        id = Integer.parseInt(idParam);
    } catch (NumberFormatException e) {
        // Manejar el error si el ID no es un número válido
        request.setAttribute("error", "El ID del almacén no es un número válido.");
        request.getRequestDispatcher("/WEB-INF/views/Almacen/list.jsp").forward(request, response);
        return;
    }

    EntityManager em = emf.createEntityManager();
    Almacen almacen = em.find(Almacen.class, id);
    if (almacen == null) {
        // Manejar el caso en que no se encuentra el almacén
        request.setAttribute("error", "El almacén no fue encontrado.");
        request.getRequestDispatcher("/WEB-INF/views/Almacen/list.jsp").forward(request, response);
        return;
    }

    request.setAttribute("almacen", almacen);
    request.getRequestDispatcher("/WEB-INF/views/Almacen/view.jsp").forward(request, response);
    em.close();
}

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String descripcion = request.getParameter("descripcion");
        List<Almacen> almacenList = em.createNamedQuery("Almacen.findByDescripcion", Almacen.class)
                                      .setParameter("descripcion", descripcion)
                                      .getResultList();
        request.setAttribute("almacenList", almacenList);
        request.getRequestDispatcher("/WEB-INF/views/Almacen/list.jsp").forward(request, response);
        em.close();
    }
}
