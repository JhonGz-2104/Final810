package com.jp.controller;

import com.jp.entity.EstadoAlmacen;
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

@WebServlet("/estadoAlmacen")
public class EstadoAlmacenController extends HttpServlet {

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
        EntityManager em = emf.createEntityManager();
        List<EstadoAlmacen> estadoAlmacenList = em.createNamedQuery("EstadoAlmacen.findAll", EstadoAlmacen.class).getResultList();
        request.setAttribute("estadoAlmacenList", estadoAlmacenList);
        request.getRequestDispatcher("/WEB-INF/views/EstadoAlmacen/list.jsp").forward(request, response);
        em.close();
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        EstadoAlmacen estadoAlmacen = new EstadoAlmacen();
        estadoAlmacen.setDescripcion(request.getParameter("descripcion"));
        em.getTransaction().begin();
        em.persist(estadoAlmacen);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("estadoAlmacen?action=list");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        EstadoAlmacen estadoAlmacen = em.find(EstadoAlmacen.class, id);
        estadoAlmacen.setDescripcion(request.getParameter("descripcion"));
        em.getTransaction().begin();
        em.merge(estadoAlmacen);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("estadoAlmacen?action=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        EstadoAlmacen estadoAlmacen = em.find(EstadoAlmacen.class, id);
        em.getTransaction().begin();
        em.remove(estadoAlmacen);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("estadoAlmacen?action=list");
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        EstadoAlmacen estadoAlmacen = em.find(EstadoAlmacen.class, id);
        request.setAttribute("estadoAlmacen", estadoAlmacen);
        request.getRequestDispatcher("/WEB-INF/views/EstadoAlmacen/view.jsp").forward(request, response);
        em.close();
    }

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String descripcion = request.getParameter("descripcion");
        List<EstadoAlmacen> estadoAlmacenList = em.createNamedQuery("EstadoAlmacen.findByDescripcion", EstadoAlmacen.class)
                                                  .setParameter("descripcion", descripcion)
                                                  .getResultList();
        request.setAttribute("estadoAlmacenList", estadoAlmacenList);
        request.getRequestDispatcher("/WEB-INF/views/EstadoAlmacen/list.jsp").forward(request, response);
        em.close();
    }
}
