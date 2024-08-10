<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio - Gestión de Inventario</title>
    <!-- Bootstrap CSS desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Gestión de Inventario</h1>
    <p class="text-center">Bienvenido a la aplicación de gestión de inventario. Seleccione una opción para continuar:</p>

    
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    Gestionar
                </div>
                <div class="list-group list-group-flush">
                    <a href="transacciones?action=list" class="list-group-item list-group-item-action">Transacciones</a>
                    <a href="articulos?action=list" class="list-group-item list-group-item-action">Artículos</a>
                    <a href="almacen?action=list" class="list-group-item list-group-item-action">Almacen</a>
               <%--      <a href="estadoAlmacen?action=list" class="list-group-item list-group-item-action">Estado Almacen</a>
                    <a href="estadoArticulo?action=list" class="list-group-item list-group-item-action">Estado Artículo</a>
                    <a href="tipoInventario?action=list" class="list-group-item list-group-item-action">Tipo Inventario</a>
                    <a href="tipoTransaccion?action=list" class="list-group-item list-group-item-action">Tipo Transacción</a>
               --%>
                    
                </div>
            </div>
        </div>

        <div class="col-md-6 d-flex align-items-center justify-content-center">
            <a href="contabilizar.jsp" class="btn btn-primary btn-lg">Contabilizar Asientos</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS y sus dependencias desde CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
