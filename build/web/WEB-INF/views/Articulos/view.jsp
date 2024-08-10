<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Artículo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Detalles del Artículo</h1>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">ID: ${articulo.id}</h5>
            <p class="card-text">Descripción: ${articulo.descripcion}</p>
            <p class="card-text">Existencias: ${articulo.existencias}</p>
            <p class="card-text">Precio de Venta: ${articulo.precioVenta}</p>
            <p class="card-text">Precio de Compra: ${articulo.precioCompra}</p>
            <p class="card-text">Almacén: ${articulo.almacenId.descripcion}</p>
            <p class="card-text">Estado del Artículo: ${articulo.estadoArticuloId.descripcion}</p>
            <p class="card-text">Tipo de Inventario: ${articulo.tipoInventarioId.descripcion}</p>
        </div>
    </div>

    <a href="articulos?action=list" class="btn btn-primary mt-3">Volver a la lista</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
