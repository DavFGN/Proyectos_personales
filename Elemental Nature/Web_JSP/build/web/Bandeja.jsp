<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <%@ page import = "java.io.*,java.util.*,java.sql.*"%>
        <%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bandeja de entrada</title>
    </head>
    <body>
        <%@include file="SesionActiva.jsp"%>
        
        <sql:setDataSource var = "fuente" driver = "org.postgresql.Driver" 
      url = "jdbc:postgresql://localhost:5432/ProyectoFinal" 
      user = "postgres" password = "postgres"/> 
        
        <sql:query dataSource = '${fuente}' var = 'result'>           
    select correo.codigo as cod, asunto from correo, usuario where usuario.nombre =
    '<%= session.getAttribute("nombre")%>' and correo.usu_r = usuario.codigo limit 10
    </sql:query> 
    
        <div class="bandejaD">
            <table class="tablaP">             
                 <tr>
                <td class="tdP">
                    Codigo
                </td>     
                <td class="tdP" colspan=2>
                    Asunto
                </td>
            </tr> 
            <c:forEach var = "row" items = "${result.rows}">
               <tr>
                <td class="tdP">
                    <c:out value = "${row.cod}"/>
                </td>     
                <td class="tdP" colspan=2>
                    <a href='Mensaje.jsp?param1=<c:out value = "${row.cod}"/>'>
                        <c:out value = "${row.asunto}"/></a> 
                </td>
            </tr> 
            </c:forEach>
            
              <tr>
                  <td class="tdP">
                   <div class="padre">
            <div class="hijo">
            <a href="Index.jsp" class="boton">VOLVER A INDEX</a>
            </div>
            </div>
                </td>
            <td class="tdP">
              <div class="padre">
            <div class="hijo">
            <a href="EnviarMensaje.jsp" class="boton">ENVIAR MENSAJE</a>
            </div>
            </div>
                </td>
                <td class="tdP">
             <div class="padre">
            <div class="hijo">
            <a href="BandejaEnvio.jsp" class="boton">MENSAJES ENVIADOS</a>
            </div>
            </div>
                </td>    
            </tr>   
            </table>
        </div>
        <%@include file="Pie.jsp"%>
    </body>
</html>
