package com.jp.controller;

import com.jp.entity.TipoTransaccion;
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

@WebServlet("/tipoTransaccion")
public class TipoTransaccionController extends HttpServlet {

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
        List<TipoTransaccion> tipoTransaccionList = em.createNamedQuery("TipoTransaccion.findAll", TipoTransaccion.class).getResultList();
        request.setAttribute("tipoTransaccionList", tipoTransaccionList);
        request.getRequestDispatcher("/WEB-INF/views/TipoTransaccion/list.jsp").forward(request, response);
        em.close();
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setDescripcion(request.getParameter("descripcion"));
        em.getTransaction().begin();
        em.persist(tipoTransaccion);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("tipoTransaccion?action=list");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TipoTransaccion tipoTransaccion = em.find(TipoTransaccion.class, id);
        tipoTransaccion.setDescripcion(request.getParameter("descripcion"));
        em.getTransaction().begin();
        em.merge(tipoTransaccion);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("tipoTransaccion?action=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TipoTransaccion tipoTransaccion = em.find(TipoTransaccion.class, id);
        em.getTransaction().begin();
        em.remove(tipoTransaccion);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("tipoTransaccion?action=list");
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TipoTransaccion tipoTransaccion = em.find(TipoTransaccion.class, id);
        request.setAttribute("tipoTransaccion", tipoTransaccion);
        request.getRequestDispatcher("/WEB-INF/views/TipoTransaccion/view.jsp").forward(request, response);
        em.close();
    }

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String descripcion = request.getParameter("descripcion");
        List<TipoTransaccion> tipoTransaccionList = em.createNamedQuery("TipoTransaccion.findByDescripcion", TipoTransaccion.class)
                                                      .setParameter("descripcion", descripcion)
                                                      .getResultList();
        request.setAttribute("tipoTransaccionList", tipoTransaccionList);
        request.getRequestDispatcher("/WEB-INF/views/TipoTransaccion/list.jsp").forward(request, response);
        em.close();
    }
}
