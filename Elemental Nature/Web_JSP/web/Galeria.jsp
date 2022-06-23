<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Galería</title>
        <link rel="icon" href="logoVicsio.png" type="image/gif" sizes="16x16">
    </head>
    <body>
            <div class="inicio">
                <table class="tablaG">

        <tbody>
            <tr>
                <td class="tdG">
                    <input type="button" value="L1" id="l1" onclick="op_gal1()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L2" id="l2" onclick="op_gal2()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L3" id="l3" onclick="op_gal3()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L4" id="l4" onclick="op_gal4()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L5" id="l5" onclick="op_gal5()">
                </td>
            </tr>
            <tr>
              <td class="tdG">
                  <input type="button" value="L6" id="l6" onclick="op_gal6()">
              </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L7" id="l7" onclick="op_gal7()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L8" id="l8" onclick="op_gal8()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L9" id="l9" onclick="op_gal9()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L10" id="l10" onclick="op_gal10()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L11" id="l11" onclick="op_gal11()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
                    <input type="button" value="L12" id="l12" onclick="op_gal12()">
                </td>
            </tr>
            <tr>
                <td class="tdG">
            <div class="padre">
            <div class="hijo">
            <a href="Index.jsp" class="boton">VOLVER A INDEX</a>
            </div>
            </div>
                </td>
            </tr>
        </tbody>
    </table>
            </div>
        
        <div class ="imgYexp">
        <div class="imagen">
            <img id="cambiar" src="./Img/logoVicsio.png" >
        </div>       
        <div class="texto_l" id="textoG">
        </div>
        </div>
        <%@include file="Pie.jsp"%>
    </body>
</html>
