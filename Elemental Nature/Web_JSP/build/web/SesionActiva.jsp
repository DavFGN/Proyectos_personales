<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fallo sesión</title>
    </head>
    <body>
        <%@include file="ComprobarSesion.jsp"%>
        <%
        Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
        String total = "";
		       
          try {   
              
        Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String comp = "select count(*) as total from Usuario where nombre like ? and pass like ?";
            PreparedStatement cm = conn.prepareStatement(comp);
            cm.setString(1, nom);
            cm.setString(2, cnt);
            ResultSet rs = cm.executeQuery();
            while (rs.next()) {
            total = rs.getString("total"); 
                System.out.println(total);
             }
            cm.close();
            
            if (!total.equals("1")) {
          
            RequestDispatcher rd = request.getRequestDispatcher("AvisoSesion.jsp");
			rd.forward(request, response);
            }
           
      }
       catch (SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }
        %>        
    </body>
</html>
