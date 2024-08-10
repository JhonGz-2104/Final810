<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${accion == 'edit' ? 'Editar Almacen' : 'Crear Almacen'}</title>
    <!-- Bootstrap CSS desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">${accion == 'edit' ? 'Editar Almacen' : 'Crear Almacen'}</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="almacen?action=${accion}" method="post">
        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <input type="text" class="form-control" id="descripcion" name="descripcion" value="${almacen.descripcion}" required>
        </div>

        <input type="hidden" name="id" value="${almacen.id}">
        <button type="submit" class="btn btn-primary mt-3">${accion == 'edit' ? 'Guardar Cambios' : 'Crear Almacen'}</button>
    </form>

    <a href="almacen?action=list" class="btn btn-secondary mt-3">Volver a la lista</a>
</div>

<!-- Bootstrap JS y sus dependencias desde CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
