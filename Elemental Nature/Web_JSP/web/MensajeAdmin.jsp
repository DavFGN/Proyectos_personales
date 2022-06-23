<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
    </head>
    <body>
        <%@include file="SesionActiva.jsp"%>   
        <%!String asunto = ""; %>
	 <%!String mensaje = ""; %>
         <%!String nombre = ""; %>
        <%
            String codigo = request.getParameter("param1");
          
        Connection con = null;
        String usern ="postgres";
	String pw ="postgres";
	String conu ="jdbc:postgresql://localhost:5432/ProyectoFinal";
        
        
        try {      
        Class.forName("org.postgresql.Driver");
        con=DriverManager.getConnection(conu,usern,pw);
        
        String cadena = "select contenido, asunto, nombre from correo, usuario "
                + "where correo.codigo = "+codigo+" and usuario.codigo = correo.usu_e;";

        PreparedStatement cm = con.prepareStatement(cadena);
        
        ResultSet rs = cm.executeQuery();
        
        while(rs.next()){
            asunto = rs.getString("asunto");
            mensaje = rs.getString("contenido");
            nombre = rs.getString("nombre");
        }
           
           cm.close();
        }             
        catch (SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            } 
            %>
            
          <div class="bandejaD">
            <table class="tablaP">  
              <tr>
                  <td class="tdB" colspan="2">
                   Asunto: <%=asunto%>
                </td>    
                <td class="tdB">
                   Remitente: <%=nombre%>
                </td> 
            </tr> 
            <tr>
                <td class="tdB" colspan=3>
                    <%=mensaje%>
                </td>                   
            </tr> 
            <tr><td class="tdP">
                   <div class="padre">
            <div class="hijo">
            <a href="Admin.jsp" class="boton">VOLVER A INDEX</a>
            </div>
            </div>
                </td>
                    
             <td class="tdP">
              <div class="padre">
            <div class="hijo">
            <a href="BandejaAdmin.jsp" class="boton">VOLVER A BANDEJA</a>
            </div>
            </div>
                </td>
            <td class="tdP">
             <div class="padre">
            <div class="hijo">
            <a href="BandejaEnvioAdmin.jsp" class="boton">MENSAJES ENVIADOS</a>
            </div>
            </div>
                </td>      
                </tr>
                
            </table>
            </div>   
            
            
             <%@include file="Pie.jsp"%>
    </body>
</html>
