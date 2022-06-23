<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="icon" href="./Img/logoVicsio.png" type="image/gif" sizes="16x16">
<script type="text/javascript" src="./JS/JavaScript.js"></script>
</head>
<body>
  
    <%@include file="ComprobarSesion.jsp"%>
    <div class="inicio">
<h1 class="tituloIndex">INDEX</h1>
<table class="tablaIndex">
<tbody>
<tr>
<%

String sin = "<td><a href='Login.jsp'>LOGIN</a></td>";
String con = "<td><a href='CerrarSesion.jsp'>SALIR</a></td>";

// Si nom y cnt son nulos, significa que no se ha iniciado sesión
if( nom == null || cnt == null ){
	out.print(sin);
} else {
    out.print(con);
}

%>
</tr>
<tr>
<td><a href="Bandeja.jsp">BANDEJA DE ENTRADA</a></td>
</tr>
<tr>
<td><a href="Perfil.jsp">PERFIL</a></td>
</tr>
<tr>
<td><a href="Partidas.jsp">PARTIDAS</a></td>
</tr>
<tr>
<td><a href="Creadores.jsp">CREADORES</a></td>
</tr>
<tr>
    <td><a href="Galeria.jsp">GALERIA</a></td>
</tr>
<tr>
<td><a href="Admin.jsp">ZONA ADMIN</a></td>
</tr>
</tbody>
</table>
    </div>  
<div class="sesion">
    
    <%

        // Si está iniciada la sesión, muestra el nombre de usuario y privilegios
if( nom == null || cnt == null ){
	out.print("<h4> Todavía no ha iniciado sesión<br>"
                + "(no podrá acceder a las funciones hasta que no inicie)</h4>");
} else {
    out.print("<h3>Bienvenido "+nom+"</h3>");
    
    if (adm == true) {
    out.print("<br><h3>Privilegios de usuario: ADMIN</h3>"); 
        }
    else {
    out.print("<br><h3>Privilegios de usuario: ESTÁNDAR</h3>");     
    }
}

%> </div>
<%@include file="Pie.jsp"%>
</body>
</html>