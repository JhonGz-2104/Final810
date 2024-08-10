<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Almacenes</title>
    <!-- Bootstrap CSS desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Lista de Almacenes</h1>
    <a href="almacen?action=create" class="btn btn-primary mb-3">Crear Nuevo Almacen</a>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<com.jp.entity.Almacen> almacenList = (List<com.jp.entity.Almacen>) request.getAttribute("almacenList");
                if (almacenList != null) {
                    for (com.jp.entity.Almacen almacen : almacenList) {
            %>
                <tr>
                    <td><%= almacen.getId() %></td>
                    <td><%= almacen.getDescripcion() %></td>
                    <td>
                        <a href="almacen?action=view&id=<%= almacen.getId() %>" class="btn btn-info btn-sm">Ver</a>
                        <a href="almacen?action=edit&id=<%= almacen.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <a href="almacen?action=delete&id=<%= almacen.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar este almacén?');">Eliminar</a>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="3" class="text-center">No se encontraron almacenes.</td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
</div>

<!-- Bootstrap JS y sus dependencias desde CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
