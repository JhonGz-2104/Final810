package com.jp.controller;

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

@WebServlet("/tipoInventario")
public class TipoInventarioController extends HttpServlet {

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
        List<TipoInventario> tipoInventarioList = em.createNamedQuery("TipoInventario.findAll", TipoInventario.class).getResultList();
        request.setAttribute("tipoInventarioList", tipoInventarioList);
        request.getRequestDispatcher("/WEB-INF/views/TipoInventario/list.jsp").forward(request, response);
        em.close();
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        TipoInventario tipoInventario = new TipoInventario();
        tipoInventario.setDescripcion(request.getParameter("descripcion"));
        tipoInventario.setCuentaContable(request.getParameter("cuentaContable"));
        em.getTransaction().begin();
        em.persist(tipoInventario);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("tipoInventario?action=list");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TipoInventario tipoInventario = em.find(TipoInventario.class, id);
        tipoInventario.setDescripcion(request.getParameter("descripcion"));
        tipoInventario.setCuentaContable(request.getParameter("cuentaContable"));
        em.getTransaction().begin();
        em.merge(tipoInventario);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("tipoInventario?action=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TipoInventario tipoInventario = em.find(TipoInventario.class, id);
        em.getTransaction().begin();
        em.remove(tipoInventario);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("tipoInventario?action=list");
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TipoInventario tipoInventario = em.find(TipoInventario.class, id);
        request.setAttribute("tipoInventario", tipoInventario);
        request.getRequestDispatcher("/WEB-INF/views/TipoInventario/view.jsp").forward(request, response);
        em.close();
    }

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String descripcion = request.getParameter("descripcion");
        List<TipoInventario> tipoInventarioList = em.createNamedQuery("TipoInventario.findByDescripcion", TipoInventario.class)
                                                    .setParameter("descripcion", descripcion)
                                                    .getResultList();
        request.setAttribute("tipoInventarioList", tipoInventarioList);
        request.getRequestDispatcher("/WEB-INF/views/TipoInventario/list.jsp").forward(request, response);
        em.close();
    }
}
