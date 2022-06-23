<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Controles.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
<LINK REL=StyleSheet HREF="Hoja.css" TYPE="text/css" MEDIA=screen>
<script type="text/javascript" src="JavaScript.js"></script>
</head>
<body>
    
<header>
           <h1 class="te">INICIO DE SESIÓN</h1>
           
        </header>
        
        <section>
            
             <br>
             <br>
            <h1 class="te">BIENVENIDO, INSERTE SU NOMBRE Y CONTRASEÑA</h1> 
            <br>
            
            <form method="post" action="Controlar">   
            <center>    
            <br>        
        <h2 class="te">NOMBRE: </h2> <input type="text" name="nombre">      
           <br>
           <br>
        <h2 class="te"> CONTRASEÑA: </h2><input type="password" name="contra">
           <br>
           <br>  
           <input type="submit" value="ENTRAR">  
           </center>          
            </form> 
            <br> <br> <br>
          <button onclick="goBack()">VOLVER</button>
        </section>
<%@include file="Pie.jsp"%>
</body>
</html>