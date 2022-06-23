<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Controles.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enviar mensaje</title>
    </head>
    <body>
        <%@include file="SesionActiva.jsp"%>
        <div class="bandejaD">
            <table class="tablaP"> 
                <form method="post" action="EnviarMensajeAdmin">   
              <tr>
                <td class="tdB" colspan=2>
                Asunto: <input type="text" id="envA" name="asunto" style="width:85%" 
                oninput="comprobarCajas()" max="30">  
                </td>    
                <td class="tdB" colspan=2>
                   Para: <input type="text" id="envR" name="nombreE" style="width:85%" 
                                oninput="comprobarCajas()" >
                </td> 
            </tr> 
            <tr>
                <td class="tdB" colspan=4>
                    Introduzca el mensaje: 
                    <br>
                   <textarea name="caja" id="mensaj" class="caja" max="500" 
                             oninput="comprobarCajas()"
                   cols="10" rows="8" placeholder="
                   Ponga en asunto el título del mensaje (máx 30 caracteres).
                   
                   Si quiere mandar un mensaje a un administrador, ponga 0.
                   Si el usuario no existe, el mensaje será enviado a admin.
                   
                   Escriba aquí el contenido del mensaje (máx 500 caracteres)."></textarea>
                </td>                   
            </tr> 
            <tr><td class="tdP">
                   <div class="padre">
            <div class="hijo">
            <a href="Admin.jsp" class="boton">VOLVER A ZONA ADMIN</a>
            </div>
            </div>
                </td>
            <td class="tdP" colspan=1>
                <input type="submit" id="conf" value="ENVIAR" disabled=""> 
                </td>
             <td class="tdP">              
              <div class="padre">
            <div class="hijo">
            <a href="BandejaAdmin.jsp" class="boton">BANDEJA</a>
            </div>
            </div>
                </td>
                <td class="tdP">
              <div class="padre">
            <div class="hijo">
            <a href="BandejaEnvioAdmin.jsp" class="boton">ENVIADOS</a>
            </div>
            </div>
                </td>
                </tr>
              </form>   
            </table>
            </div>
        <%@include file="Pie.jsp"%>
    </body>
</html>
