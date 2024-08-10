<%@page import="com.jp.entity.Almacen"%>
<%@page import="com.jp.entity.EstadoArticulo"%>
<%@page import="com.jp.entity.TipoInventario"%>
<%@page import="com.jp.entity.Articulos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= (request.getAttribute("articulo") == null ? "Crear" : "Editar") %> Artículo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center"><%= (request.getAttribute("articulo") == null ? "Crear" : "Editar") %> Artículo</h1>
    <form action="articulos?action=<%= (request.getAttribute("articulo") == null ? "create" : "edit") %>" method="post">
        <input type="hidden" name="id" value="<%= (request.getAttribute("articulo") != null) ? ((Articulos) request.getAttribute("articulo")).getId() : "" %>">

        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <input type="text" class="form-control" id="descripcion" name="descripcion" required
                   value="<%= (request.getAttribute("articulo") != null) ? ((Articulos) request.getAttribute("articulo")).getDescripcion() : "" %>">
        </div>

        <div class="mb-3">
            <label for="existencias" class="form-label">Existencias</label>
            <input type="number" class="form-control" id="existencias" name="existencias" required
                   value="<%= (request.getAttribute("articulo") != null) ? ((Articulos) request.getAttribute("articulo")).getExistencias() : "" %>">
        </div>

        <div class="mb-3">
            <label for="precio_venta" class="form-label">Precio de Venta</label>
            <input type="number" step="0.01" class="form-control" id="precio_venta" name="precio_venta" required
                   value="<%= (request.getAttribute("articulo") != null) ? ((Articulos) request.getAttribute("articulo")).getPrecioVenta() : "" %>">
        </div>

        <div class="mb-3">
            <label for="precio_compra" class="form-label">Precio de Compra</label>
            <input type="number" step="0.01" class="form-control" id="precio_compra" name="precio_compra" required
                   value="<%= (request.getAttribute("articulo") != null) ? ((Articulos) request.getAttribute("articulo")).getPrecioCompra() : "" %>">
        </div>

        <div class="mb-3">
            <label for="almacen_id" class="form-label">Almacén</label>
            <select class="form-select" id="almacen_id" name="almacen_id" required>
                <option value="">Seleccione un almacén</option>
                <% 
                List<Almacen> almacenList = (List<Almacen>) request.getAttribute("almacenList");
                for (Almacen almacen : almacenList) { 
                %>
                    <option value="<%= almacen.getId() %>" 
                        <%= (request.getAttribute("articulo") != null && ((Articulos) request.getAttribute("articulo")).getAlmacenId().getId().equals(almacen.getId())) ? "selected" : "" %>>
                        <%= almacen.getDescripcion() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="estado_articulo_id" class="form-label">Estado del Artículo</label>
            <select class="form-select" id="estado_articulo_id" name="estado_articulo_id" required>
                <option value="">Seleccione un estado</option>
                <% 
                List<EstadoArticulo> estadoArticuloList = (List<EstadoArticulo>) request.getAttribute("estadoArticuloList");
                for (EstadoArticulo estado : estadoArticuloList) { 
                %>
                    <option value="<%= estado.getId() %>" 
                        <%= (request.getAttribute("articulo") != null && ((Articulos) request.getAttribute("articulo")).getEstadoArticuloId().getId().equals(estado.getId())) ? "selected" : "" %>>
                        <%= estado.getDescripcion() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="tipo_inventario_id" class="form-label">Tipo de Inventario</label>
            <select class="form-select" id="tipo_inventario_id" name="tipo_inventario_id" required>
                <option value="">Seleccione un tipo de inventario</option>
                <% 
                List<TipoInventario> tipoInventarioList = (List<TipoInventario>) request.getAttribute("tipoInventarioList");
                for (TipoInventario tipo : tipoInventarioList) { 
                %>
                    <option value="<%= tipo.getId() %>" 
                        <%= (request.getAttribute("articulo") != null && ((Articulos) request.getAttribute("articulo")).getTipoInventarioId().getId().equals(tipo.getId())) ? "selected" : "" %>>
                        <%= tipo.getDescripcion() %>
                    </option>
                <% } %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="articulos?action=list" class="btn btn-secondary">Cancelar</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
