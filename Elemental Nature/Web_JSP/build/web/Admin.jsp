<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Zona administrador</title>
    <link rel="icon" href="./Img/logoVicsio.png" type="image/gif" sizes="16x16">
    <script type="text/javascript" src="./JS/JavaScript.js" charset="UTF-8"></script>
    <LINK REL=StyleSheet HREF="./CSS/Hoja.css" TYPE="text/css" MEDIA=screen>
    </head>
    <body>
        <%@include file="ComprobarSesion.jsp"%>       
        <%   
            if (adm == null) {
               adm = false;     
                }
            if (adm == false || adm == null) {
        RequestDispatcher rd = request.getRequestDispatcher("AvisoSesion.jsp");
	rd.forward(request, response);  
        
                }
          %>

          <div class="inicio">
        <h1 class="tituloIndex">ZONA ADMIN</h1>
        <table class="tablaIndex">
        <tbody>
        <tr>
        <td><a href="GestionarUsuario.jsp">CONTROLAR USUARIOS</a></td>
        </tr>
        <tr>
        <td><a href="BandejaAdmin.jsp">CORREOS DIRIGIDOS A ADMINS</a></td>
        </tr>
        <tr>
        <td><a href="Logs.jsp">VER REGISTROS</a></td>
        </tr>            
        <tr>
        <td><a href="Index.jsp">VOLVER A INDEX</a></td>
        </tr>
        </tbody>
        </table>
            </div> 
          
          
          <div class="sesion">
              <p class="parrafoA">ESTÁS AQUÍ PORQUE <br> 
                  ERES ADMINISTRADOR <br> DE LA WEB</p>
          </div>

    </body>
</html>
