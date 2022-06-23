<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Partidas</title>
        <LINK REL=StyleSheet HREF="./CSS/Hoja.css" TYPE="text/css" MEDIA=screen>
<script type="text/javascript" src="./JS/JavaScript.js" charset="UTF-8"></script>
<link rel="icon" href="./Img/logoVicsio.png" type="image/gif" sizes="16x16">
    </head>
    <body>
         <%@include file="SesionActiva.jsp"%> 
         <div class="partidaP">              
              <table class="tablaP">          
           <tr>
                <td class="tdU">
                    Codigo Partida
                </td>     
                <td class="tdU">
                    Ganador
                </td>
                <td class="tdU">
                    Perdedor
                </td>   
                <td class="tdU" colspan=2>
                    Fecha
                </td> 
           </tr>              
         <%
             
         Connection con = null;
        String usern="postgres";
	String pasw="postgres";
	String conURL="jdbc:postgresql://localhost:5432/ProyectoFinal";     
             
        String nam = (String)session.getAttribute("nombre");
        
        String cadena = "";
        String nameR = "";
        String tmp = "";
        int codU = 0;
        int codigo = 0;
        int ganador = 0; 
        int perdedor = 0;
        Timestamp fecha;
      
         try {      
        Class.forName("org.postgresql.Driver");
        con=DriverManager.getConnection(conURL,usern,pasw);       
        String gCod = "select codigo from Usuario where nombre like '"+nam+"'"; 
        PreparedStatement sr = con.prepareStatement(gCod);
            ResultSet re = sr.executeQuery();
            while (re.next()) {           
             codU = re.getInt("codigo"); 
             System.out.println("Codigo de usuario: "+codU+"");
             }

       cadena = "select * from Partida where ganador = "+codU+" "
               + "or perdedor = "+codU+" limit 10";
       PreparedStatement cm = con.prepareStatement(cadena);           
       ResultSet rs = cm.executeQuery();
       
            while (rs.next()) {
            
            codigo = rs.getInt("codigo"); 
            ganador = rs.getInt("ganador"); 
            perdedor = rs.getInt("perdedor"); 
            fecha = rs.getTimestamp("fecha"); 
            
             cadena = "select nombre from Usuario where codigo = ?";
                
             sr = con.prepareStatement(cadena);
                
                     if (ganador == codU) {
                         
                     sr.setInt(1, perdedor);  
                     
                     } 
                     else {
                         
                     sr.setInt(1, ganador);
                     
                     };
                     
                re = sr.executeQuery();
                
                while (re.next()) {   
                    
                nameR = re.getString("nombre"); 
                    
                 };
                
                if (ganador == codU) {
                    
                tmp =    
                "<td class=\"tdU\">"+
                " "+nam+""+
                "</td>"+
                "<td class=\"tdU\">"+
                " "+nameR+""+
                "</td>";
                
                     }
                     else {
                
                tmp =    
                "<td class=\"tdU\">"+
                " "+nameR+""+
                "</td>"+
                "<td class=\"tdU\">"+
                " "+nam+""+
                "</td>";                   
                 
                     };
            
            
            out.print("<tr>"+
                "<td class=\"tdU\">"+
                "    "+codigo+""+
                "</td>"+  
                ""+tmp+""+               
                "<td class=\"tdU\" colspan=2>"+
                "    "+fecha+""+
               " </td>"+
           "</tr>        ");
            
             };          
            
      }
       catch (SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }       
           %>
          <tr>             
                <td class="tdU" >                  
                </td> 
                <td class="tdU" >                   
                </td> 
                <td class="tdU" >                    
                </td> 
                <td class="tdU" >                
                </td> 
                <td class="tdU" colspan=5>
                 <div class="padre">
            <div class="hijo">
            <a href="Index.jsp" class="boton">VOLVER A INDEX</a>
            </div>
            </div>
                </td>  
           </tr>  
            </table>     
          </div>
 
          <div class="topL">             
         <%
             
         String luch = "";
         int cant = 0;
         String imagen = "";
         boolean first = true, second = true, third = true;
         
        try {   
            
        Class.forName("org.postgresql.Driver");
        con=DriverManager.getConnection(conURL,usern,pasw);  
        String gCod = "select luchador, count(luchador) as usado from equipo "  
        +"where jugador = (select codigo from usuario where nombre like '"+nam+"')" 
        +"group by luchador order by usado desc"; 
        
        PreparedStatement pre = con.prepareStatement(gCod);
            ResultSet resu = pre.executeQuery();
            while (resu.next()) { 
             luch = resu.getString("luchador");
             cant = resu.getInt("usado");  
             System.out.println("Cargadas variables");
             if(luch.equals("L1")){
                     imagen = "./Img/L1.jpg";                           
             }            
             if(luch.equals("L2")){
                     imagen = "./Img/L2.png"; 
             }        
             if(luch.equals("L3")){
                     imagen = "./Img/L3.jpg";    
             }
             if(luch.equals("L4")){
                     imagen = "./Img/L4.png";    
             }
             if(luch.equals("L5")){
                     imagen = "./Img/L5.jpg";    
             }
             if(luch.equals("L6")){
                     imagen = "./Img/L6.png";    
             }
             if(luch.equals("L7")){
                     imagen = "./Img/L7.jpg";    
             }
             if(luch.equals("L8")){
                     imagen = "./Img/L8.jpg";    
             }
             if(luch.equals("L9")){
                     imagen = "./Img/L9.png";    
             }
             if(luch.equals("L10")){
                     imagen = "./Img/L10.jpg";    
             }
             if(luch.equals("L11")){
                     imagen = "./Img/L11.jpg";    
             }
             if(luch.equals("L12")){
                     imagen = "./Img/L12.png";    
             } 
             
             System.out.println("Imagen metida");
             
             if (first == true) {
               out.print("<div class=\"luchP\"><img id=\"camP\" src=\""+imagen+"\">"
               +"<div class=\"texto-encima\">1º: "+cant+" veces</div></div> ");
               
               first = false;
                 }
             else  if (second == true) {
               out.print("<div class=\"luchS\"><img id=\"camS\" src=\""+imagen+"\">"
               +"<div class=\"texto-encimaM\">2º: "+cant+" veces</div></div> ");
               
                second = false;
                 } 
             
            else if (third == true) {
               out.print("<div class=\"luchT\"><img id=\"camT\" src=\""+imagen+"\">"
               +"<div class=\"texto-encimaA\">3º: "+cant+" veces</div></div> ");
                
               third = false;
                 }           
            }
      }
       catch (SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }       
                %>         
          </div>  
            <%@include file="Pie.jsp"%>
    </body>
</html>
