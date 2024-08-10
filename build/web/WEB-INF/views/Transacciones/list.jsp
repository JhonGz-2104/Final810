<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Transacciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Lista de Transacciones</h1>
    <a href="transacciones?action=create" class="btn btn-primary mb-3">Crear Nueva Transacción</a>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Cantidad</th>
                <th>Artículo</th>
                <th>Tipo de Transacción</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<com.jp.entity.Transacciones> transaccionesList = (List<com.jp.entity.Transacciones>) request.getAttribute("transaccionesList");
                if (transaccionesList != null) {
                    for (com.jp.entity.Transacciones transaccion : transaccionesList) {
            %>
                <tr>
                    <td><%= transaccion.getId() %></td>
                    <td><%= transaccion.getFecha() %></td>
                    <td><%= transaccion.getCantidad() %></td>
                    <td><%= transaccion.getArticuloId() != null ? transaccion.getArticuloId().getDescripcion() : "N/A" %></td>
                    <td><%= transaccion.getTipoTransaccionId() != null ? transaccion.getTipoTransaccionId().getDescripcion() : "N/A" %></td>
                    <td>
                        <a href="transacciones?action=view&id=<%= transaccion.getId() %>" class="btn btn-info btn-sm">Ver</a>
                        <a href="transacciones?action=edit&id=<%= transaccion.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <a href="transacciones?action=delete&id=<%= transaccion.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar esta transacción?');">Eliminar</a>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="6" class="text-center">No se encontraron transacciones.</td>
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
