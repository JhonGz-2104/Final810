<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Transacciones No Contabilizadas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Transacciones No Contabilizadas</h2>

    <!-- Formulario para consultar transacciones -->
    <form action="contabilizar" method="get" class="row g-3">
        <input type="hidden" name="action" value="consultar">
        <div class="col-md-4">
            <label for="startDate" class="form-label">Fecha de Inicio</label>
            <input type="date" class="form-control" id="startDate" name="startDate" required>
        </div>
        <div class="col-md-4">
            <label for="endDate" class="form-label">Fecha de Fin</label>
            <input type="date" class="form-control" id="endDate" name="endDate" required>
        </div>
        <div class="col-md-4 d-flex align-items-end">
            <button type="submit" class="btn btn-primary">Consultar</button>
        </div>
    </form>

    <!-- Tabla para mostrar las transacciones -->
    <table class="table table-bordered mt-4">
        <thead>
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Monto</th>
            <th>Descripción</th>
            <th>Cuenta Débito</th>
            <th>Cuenta Crédito</th>
            <th>ID Asiento</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<com.jp.entity.Transacciones> transaccionesList = 
                (List<com.jp.entity.Transacciones>) request.getAttribute("transaccionesList");

            if (transaccionesList != null && !transaccionesList.isEmpty()) {
                for (com.jp.entity.Transacciones transaccion : transaccionesList) {
                    Integer tipoTransaccionId = (transaccion.getTipoTransaccionId() != null) ? transaccion.getTipoTransaccionId().getId() : null;

                    int cuentaDebito = (tipoTransaccionId != null && tipoTransaccionId == 1) ? 6 : 12;
                    int cuentaCredito = (tipoTransaccionId != null && tipoTransaccionId == 1) ? 82 : 6;
                    String descripcion = (tipoTransaccionId != null && tipoTransaccionId == 1) ? "compra" : "venta";
        %>
        <tr>
            <td><%= transaccion.getId() %></td>
            <td><%= transaccion.getFecha() %></td>
            <td><%= transaccion.getMonto() %></td>
            <td><%= (tipoTransaccionId != null) ? transaccion.getTipoTransaccionId().getDescripcion() : "Tipo no especificado" %></td>
            <td><%= cuentaDebito %></td>
            <td><%= cuentaCredito %></td>
            <td><%= transaccion.getIdAsiento() != null ? transaccion.getIdAsiento() : "No contabilizado" %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7" class="text-center">No se encontraron transacciones.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <!-- Botón para contabilizar transacciones -->
    <div class="mt-3">
        <% if (transaccionesList != null && !transaccionesList.isEmpty()) { %>
        <form action="contabilizar" method="get">
            <input type="hidden" name="action" value="contabilizar">
            <button type="submit" class="btn btn-primary">Contabilizar Asientos</button>
        </form>
        <% } else { %>
        <p>No hay transacciones disponibles para contabilizar.</p>
        <% } %>
        <a href="index.jsp" class="btn btn-secondary">Volver</a>
    </div>
</div>
</body>
</html>
