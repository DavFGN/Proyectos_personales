<%@page import="java.util.Calendar"%>
<%@page import="oracle.net.aso.l"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar logs</title>
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
                  <form action="Logs.jsp">
                  <tr>
                      <td class="tdU" colspan=2>
                    Buscar por nombre: 
                 <input type="text" name="seaN"  style="width:40%"
                 max="30">
                 <input type="submit" value="BUSCAR">  
                </td>   
                <td class="tdU" colspan="2">
                 Buscar por fecha: 
                 <input type="text" name="seaF" style="width:30%"
                 max="30" placeholder="yyyy-mm-dd">
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
                <td class="tdU" colspan=2>
                    Usuario
                </td>
                <td class="tdU">
                    Fecha 
                </td>
                <td class="tdU">
                    IP
                </td>
           </tr>           
           <%
               
        Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
              
        String sName = request.getParameter("seaN");
	String Sfech = request.getParameter("seaF");

        String comp = "";
        
        Timestamp diamas = null;
        
        if (sName == null && Sfech == null) {     
           System.out.println("Primera entrada"); 
            } else
        {
                
         try {      
        Class.forName("org.postgresql.Driver");
        conn=DriverManager.getConnection(connURL,username,pwd);
        
        if (sName.equals("") && Sfech.equals("")) {     
            comp = "select registro, nombre from registro, usuario order by fecha_ult desc limit 10 ";
            }
        
        if (!sName.equals("")) {
        comp = "select * from Registro, usuario where "
        + "usuario.codigo = cod_usuario and nombre like '%"+sName+"%' order by fecha_ult"
                + " desc limit 10"; 
            }
        else if (!Sfech.equals("")) {
            try {
    
            String fe = ""+Sfech+" 00:00:00";
            diamas = Timestamp.valueOf(fe);
            Calendar cal = Calendar.getInstance();
            cal.setTime(diamas);
            cal.add(Calendar.DAY_OF_WEEK, 1);
            diamas.setTime(cal.getTime().getTime()); // or
            diamas = new Timestamp(cal.getTime().getTime());
            
            } catch (IllegalArgumentException e) {
               out.print("<tr>"+
                "<td class=\"tdU\" colspan=5>"+
                "    "+e+""+
                "</td>   "+               
           "</tr>        "); 
                }
        comp = "select registro.codigo, cod_usuario, fecha_ult, ip, nombre"
                + " from registro, usuario "
                + "where fecha_ult::date = date '"+Sfech+"' and "
                + "cod_usuario = usuario.codigo order by fecha_ult desc limit 10;";        
            }
        
       PreparedStatement cm = conn.prepareStatement(comp);           
       ResultSet rs = cm.executeQuery();
       
            while (rs.next()) {
            String codigo = rs.getString("codigo"); 
            int cod_usuario = rs.getInt("cod_usuario"); 
            Timestamp fecha = rs.getTimestamp("fecha_ult"); 
            String ip = rs.getString("ip"); 
            String codName = rs.getString("nombre"); 
            
            out.print("<tr>"+
                "<td class=\"tdU\">"+
                "    "+codigo+""+
                "</td>   "+  
                "<td class=\"tdU\" colspan=2>"+
                " "+cod_usuario+" ("+codName+")"+
                "</td>"+
                "<td class=\"tdU\">"+
                "    "+fecha+""+
                "</td>"+              
                "<td class=\"tdU\">"+
                "    "+ip+""+
               " </td>"+
           "</tr>        ");
            
             }
            cm.close();
            
      }
       catch (SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
              out.print("<tr>"+
                "<td class=\"tdU\" colspan=5>"+
                "    "+e+""+
                "</td>   "+               
           "</tr>        ");  
            }
        }
           %>
              </table>     
          </div>             
            
    </body>
</html>
