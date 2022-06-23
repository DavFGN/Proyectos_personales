<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Controles.*" %>
<!DOCTYPE html>
<html>
    <%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar datos perfil</title>
    </head>
    <body>        
        <%@include file="SesionActiva.jsp"%>  
           <div class="divP">
         <form method="POST" action="CambiarP">
        <table class="tablaP">  
        <tbody>            
            <tr>
                <td class="tdP">
                    Nombre
                </td>
                <td class="tdP">
                 <%= session.getAttribute("nombre")%>
                </td>
                <td class="tdP">
                    <input type="text" name="nameP">
                </td>
            </tr>    
            <tr>
                <td class="tdP">
                    Contraseña
                </td>
                <td class="tdP">
                 Nueva contraseña <input type="password" name="contraN">
                </td>  
                <td class="tdP">
                 Confirmar contraseña <input type="password" name="contraC">
                </td>
            </tr> 
            <tr>
                <td class="tdCam" colspan=2>
                    Introduzca los datos que quiera cambiar. <br> Si no quiere
                    cambiar un dato, deje la caja <br> vacía correspondiente 
                    (es necesario <br>
                    introducir la contraseña para cambiar datos).
                    <br>Deberá iniciar de nuevo sesión al actualizar.
                </td>
                <td class="tdP">
                    Contraseña <input type="password" name="contraA">
                </td>
            </tr>        
            <tr>
                <td class="tdP" colspan=3>
                    <input type="submit" value="Actualizar datos">
                </td>
            </tr>  
            <tr><td class="tdP" colspan=3>
                   <div class="padre">
            <div class="hijo">
            <a href="Perfil.jsp" class="boton">VOLVER A PERFIL</a>
            </div>
            </div>
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
         </tbody>       
        </table>
         </form> 
     </div>        
         <%@include file="Pie.jsp"%>
    </body>
</html>
