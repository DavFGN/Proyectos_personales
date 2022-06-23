<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionar usuario</title>
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

          <div class="usuarioD">              
              <table class="tablaP">
                  <form action="GestionarUsuario.jsp">
                  <tr>
                      <td class="tdU" colspan=2>
                    Buscar por nombre: 
                 <input type="text" id="seaN" name="searchN"  style="width:40%"
                 max="30">
                 <input type="submit" value="BUSCAR">  
                </td>   
                <td class="tdU" colspan="2">
                 Buscar por código: 
                 <input type="text" id="seaC" name="searchC" style="width:30%"
                 max="30">
                <input type="submit" value="BUSCAR">  
                </td>
                <td class="tdU">
                <div class="padre">
                <div class="hijo">
                <a href="Admin.jsp" class="boton">VOLVER A ZONA ADMIN</a>
                </div>
                </div>
                </td>  
                  </tr>  
                  </form>
           <tr>
                <td class="tdU">
                    Codigo
                </td>     
                <td class="tdU">
                    Nombre
                </td>
                <td class="tdU">
                    Fecha alta
                </td>
                <td class="tdU">
                    Administrador
                </td>
                <td class="tdU">
                    Baneado
                </td>
           </tr>           
           <%
               
        Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
              
        String sName = request.getParameter("searchN");
	String Scod = request.getParameter("searchC");
        
        String comp = "";
        
        if (sName == null && Scod == null) {     
           System.out.println("Primera entrada"); 
            } else
        {
                
         try {      
        Class.forName("org.postgresql.Driver");
        conn=DriverManager.getConnection(connURL,username,pwd);
        
        if (sName.equals("") && Scod.equals("")) {     
            comp = "select * from Usuario limit 10";
            }
        
        if (!sName.equals("")) {
        comp = "select * from Usuario where nombre like '%"+sName+"%' limit 10"; 
            }
        else if (!Scod.equals("")) {
        comp = "select * from Usuario where codigo = "+Scod+"";        
            }
        
       PreparedStatement cm = conn.prepareStatement(comp);           
       ResultSet rs = cm.executeQuery();
       
            while (rs.next()) {
            String nombre = rs.getString("nombre"); 
            int codigo = rs.getInt("codigo"); 
            Timestamp fecha = rs.getTimestamp("fecha"); 
            boolean administrador = rs.getBoolean("administrador"); 
            boolean ban = rs.getBoolean("ban"); 
            
            out.print("<tr>"+
                "<td class=\"tdU\">"+
                "    "+codigo+""+
                "</td>   "+  
                "<td class=\"tdU\">"+
                " <a href='ManipularUsuario.jsp?param1="+nombre+"'>"+nombre+"</a>"+
                "</td>"+
                "<td class=\"tdU\">"+
                "    "+fecha+""+
                "</td>"+
                "<td class=\"tdU\">"+
                "    "+administrador+""+
                "</td>"+
                "<td class=\"tdU\">"+
                "    "+ban+""+
               " </td>"+
           "</tr>        ");
            
             }
            cm.close();
            
      }
       catch (SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }
        }
           %>
              </table>     
          </div>             
            
    </body>
</html>
