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
        <title>Perfil de usuario</title>
        <link rel="icon" href="logoVicsio.png" type="image/gif" sizes="16x16">
    </head>
    <body>
         
        <%@include file="SesionActiva.jsp"%>      
                  
      <sql:setDataSource var = "fuente" driver = "org.postgresql.Driver" 
      url = "jdbc:postgresql://localhost:5432/ProyectoFinal" 
      user = "postgres" password = "postgres"/> 


    <sql:query dataSource = '${fuente}' var = 'result'>           
    select * from Usuario where nombre = '<%= session.getAttribute("nombre")%>'
    </sql:query>        
              
    <div class="divP">
        
        <table class="tablaP">
            
        <tbody>
            <c:forEach var = "row" items = "${result.rows}">
            <tr>
                <td class="tdP">
                    Codigo
                </td>
                <td class="tdP">
                 <c:out value = "${row.codigo}"/>
                </td>
                <td class="tdP">
                    NO MODIFICABLE
                </td>
            </tr>    
            <tr>
                <td class="tdP">
                    Nombre
                </td>
                <td class="tdP">
                    <c:out value = "${row.nombre}"/>
                </td>
                <td class="tdP">               
            <a href="CambiarDatosP.jsp">MODIFICAR</a>           
                </td>
            </tr> 
            <tr>
                <td class="tdP">
                    Fecha registro
                </td>
                <td class="tdP">
                    <c:out value = "${row.fecha}"/>
                </td>
                <td class="tdP">
                    NO MODIFICABLE
                </td>
            </tr> 
           <tr>
                <td class="tdP">
                    Administrador
                </td>
                <td class="tdP">
                    <c:out value = "${row.administrador}"/>
                </td>
                <td class="tdP">
                    NO MODIFICABLE EN ESTA SECCIÓN
                </td>
            </tr> 
            <tr>
                <td class="tdP">
                    Baneado
                </td>
                <td class="tdP">
                    <c:out value = "${row.ban}"/>
                </td>
                <td class="tdP">
                    NO MODIFICABLE EN ESTA SECCIÓN
                </td>
            </tr> 
            <tr>
                <td class="tdP">
                    Contraseña
                </td>
                <td class="tdP">
                    --
                </td>
                <td class="tdP">
                  <a href="CambiarDatosP.jsp">MODIFICAR</a>   
                </td>
            </tr>
            <tr><td class="tdP" colspan=3>
                   <div class="padre">
            <div class="hijo">
            <a href="Index.jsp" class="boton">VOLVER A INDEX</a>
            </div>
            </div>
                </td>
            </tr>
         </c:forEach>
         </tbody>       
        </table>
     </div>          
        <%@include file="Pie.jsp"%>
    </body>
</html>
