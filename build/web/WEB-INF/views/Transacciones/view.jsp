<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Transacción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Detalles de la Transacción</h1>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">ID: ${transaccion.id}</h5>
            <p class="card-text">Fecha: ${transaccion.fecha}</p>
            <p class="card-text">Cantidad: ${transaccion.cantidad}</p>
            <p class="card-text">Monto: ${transaccion.monto}</p>
            <p class="card-text">ID de Asiento: ${transaccion.idAsiento}</p>
            <p class="card-text">Artículo: ${transaccion.articuloId.descripcion}</p>
            <p class="card-text">Tipo de Transacción: ${transaccion.tipoTransaccionId.descripcion}</p>
        </div>
    </div>

    <a href="transacciones?action=list" class="btn btn-primary mt-3">Volver a la lista</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
