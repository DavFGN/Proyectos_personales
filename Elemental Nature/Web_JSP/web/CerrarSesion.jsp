<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrar sesión</title>
        <link rel="icon" href="logoVicsio.png" type="image/gif" sizes="16x16">
    </head>
    <body>      
        <%
           HttpSession sesion = request.getSession(true);
		if(sesion != null) {	
			sesion.invalidate();	
			}    
            %>
            
            <div class="cerrarS" id="countdown"></div>
                   
 <%@include file="Pie.jsp"%>           
    </body>
</html>
