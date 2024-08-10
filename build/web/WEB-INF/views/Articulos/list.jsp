<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Artículos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Lista de Artículos</h1>
    <a href="articulos?action=create" class="btn btn-primary mb-3">Crear Nuevo Artículo</a>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Existencias</th>
                <th>Precio Venta</th>
                <th>Precio Compra</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<com.jp.entity.Articulos> articulosList = (List<com.jp.entity.Articulos>) request.getAttribute("articulosList");
                if (articulosList != null) {
                    for (com.jp.entity.Articulos articulo : articulosList) {
            %>
                <tr>
                    <td><%= articulo.getId() %></td>
                    <td><%= articulo.getDescripcion() %></td>
                    <td><%= articulo.getExistencias() %></td>
                    <td><%= articulo.getPrecioVenta() %></td>
                    <td><%= articulo.getPrecioCompra() %></td>
                    <td>
                        <a href="articulos?action=view&id=<%= articulo.getId() %>" class="btn btn-info btn-sm">Ver</a>
                        <a href="articulos?action=edit&id=<%= articulo.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <a href="articulos?action=delete&id=<%= articulo.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar este artículo?');">Eliminar</a>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="6" class="text-center">No se encontraron artículos.</td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
