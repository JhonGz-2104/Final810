<%@page import="com.jp.entity.Transacciones"%>
<%@page import="com.jp.entity.Articulos"%>
<%@page import="com.jp.entity.TipoTransaccion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Transacción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center"><%= (request.getAttribute("transaccion") == null ? "Crear" : "Editar") %> Transacción</h1>

    <% 
        String error = (String) request.getAttribute("error");
        if (error != null) { 
    %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <form action="transacciones?action=<%= (request.getAttribute("transaccion") == null ? "create" : "edit") %>" method="post">
        <% 
            Transacciones transaccion = (Transacciones) request.getAttribute("transaccion");
            if (transaccion != null && transaccion.getId() != null) { 
        %>
            <input type="hidden" name="id" value="<%= transaccion.getId() %>">
        <% } %>

        <div class="form-group">
            <label for="cantidad">Cantidad:</label>
            <input type="number" class="form-control" id="cantidad" name="cantidad" value="<%= transaccion != null ? transaccion.getCantidad() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="fecha">Fecha:</label>
            <input type="date" class="form-control" id="fecha" name="fecha" value="<%= transaccion != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(transaccion.getFecha()) : "" %>" required>
        </div>

        <div class="form-group">
            <label for="articuloId">Artículo:</label>
            <select class="form-control" id="articuloId" name="articuloId">
                <% 
                    List<Articulos> articulosList = (List<Articulos>) request.getAttribute("articulosList");
                    if (articulosList != null) {
                        for (Articulos articulo : articulosList) { 
                %>
                    <option value="<%= articulo.getId() %>" <%= (transaccion != null && articulo.getId().equals(transaccion.getArticuloId().getId())) ? "selected" : "" %>>
                        <%= articulo.getDescripcion() %>
                    </option>
                <% 
                        }
                    } 
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="tipoTransaccionId">Tipo de Transacción:</label>
            <select class="form-control" id="tipoTransaccionId" name="tipoTransaccionId">
                <% 
                    List<TipoTransaccion> tipoTransaccionList = (List<TipoTransaccion>) request.getAttribute("tipoTransaccionList");
                    if (tipoTransaccionList != null) {
                        for (TipoTransaccion tipo : tipoTransaccionList) { 
                %>
                    <option value="<%= tipo.getId() %>" <%= (transaccion != null && tipo.getId().equals(transaccion.getTipoTransaccionId().getId())) ? "selected" : "" %>>
                        <%= tipo.getDescripcion() %>
                    </option>
                <% 
                        }
                    } 
                %>
            </select>
        </div>

        <div class="mt-4 text-center">
            <button type="submit" class="btn btn-success"><%= transaccion == null ? "Crear" : "Guardar" %></button>
            <a href="transacciones?action=list" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
