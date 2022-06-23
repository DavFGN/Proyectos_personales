<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Controles.*" %>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de usuario</title>
    <link rel="icon" href="logoVicsio.png" type="image/gif" sizes="16x16">
    <script type="text/javascript" src="./JS/JavaScript.js" charset="UTF-8"></script>
    <LINK REL=StyleSheet HREF="./CSS/Hoja.css" TYPE="text/css" MEDIA=screen>
    </head>
    <body>
        <%@include file="ComprobarSesion.jsp"%>  
        <%!int codigo = 0; %>
        <%!String nombre = ""; %>
        <%!String pass = ""; %>
        <%!Timestamp fecha; %>
        <%!Boolean administrador = false; %>
        <%!Boolean ban = false; %>
        <%   
            if (adm == null) {
               adm = false;     
                }
            if (adm == false || adm == null) {
        RequestDispatcher rd = request.getRequestDispatcher("AvisoSesion.jsp");
	rd.forward(request, response);  
        
                }
            
          %>
        
          <%
            String parametro = request.getParameter("param1");
          
        Connection con = null;
        String usern ="postgres";
	String pw ="postgres";
	String conu ="jdbc:postgresql://localhost:5432/ProyectoFinal";
        
        
        try {      
        Class.forName("org.postgresql.Driver");
        con=DriverManager.getConnection(conu,usern,pw);
        
        String cadena = "select * from usuario where nombre like '"+parametro+"'";

        PreparedStatement cm = con.prepareStatement(cadena);
        
        ResultSet rs = cm.executeQuery();
        
        while(rs.next()){
            codigo = rs.getInt("codigo");
            nombre = rs.getString("nombre");
            pass = rs.getString("pass");
            fecha = rs.getTimestamp("fecha");
            administrador = rs.getBoolean("administrador");
            ban = rs.getBoolean("ban");           
        }
           
           cm.close();
        }             
        catch (SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            } 
            %>
          
            <div class="manU">
                <table class="tablaP">
                    <form action="ModificarUsuario" method="post">
             <tr>
                <td class="tdU">
                    Codigo:
                </td>     
                <td class="tdU" colspan=2>
                    <input type="text" name="codMU"  value="<%=codigo%>" 
                     style="width:90%" max="30" readonly>    
                </td>
           </tr>  
            <tr>
                <td class="tdU">
                    Nombre:
                </td>     
                <td class="tdU" colspan=2>
                <input type="text" name="nameMU"  value="<%=nombre%>"
                 style="width:90%" max="30">    
                </td>
           </tr>
           <tr>
                <td class="tdU">
                    Contraseña:
                </td>     
                <td class="tdU" colspan=2>
                <input type="text" name="passMU"  value="<%=pass%>"
                 style="width:90%" max="30">    
                </td>
           </tr> 
            <tr>
                <td class="tdU">
                    Fecha registro:
                </td>     
                <td class="tdU" colspan=2>
                <input type="datetime" name="fechaMU"  value="<%=fecha%>"
                 style="width:90%">   
                </td>
           </tr> 
            <tr>
                <td class="tdU">
                    Administrador:
                </td>     
                <td class="tdU" colspan=2>
            <input type="checkbox" name="adminMU" <%if (administrador==false) {} 
            else{out.print("checked");}%>>                                   
                </td>
           </tr> 
            <tr>
                <td class="tdU">
                    Baneado:
                </td>     
                <td class="tdU" colspan=2>
                    <input type="checkbox" name="banMU" <%if (ban==false) {} 
                    else{out.print("checked");}%>>                      
                </td>
           </tr> 
           <tr>
                <td class="tdU" colspan=2>
                    ¿BORRAR USUARIO?
                </td>     
                <td class="tdU">
                    <input type="checkbox" name="borrarMU">    
                </td>
           </tr> 
           <tr>
               <td class="tdU" colspan=3>
                Debe cumplir los siguientes criterios:
                <br>El nombre será menor a 30 caracteres.
                <br>La fecha debe seguir el patrón establecido.
                <br>Marque y desmarque para indicar si debe ser
                administrador y baneado.
                <br>Si quiere borrarlo, marque la casilla.
                </td>    
           </tr>
           <tr>
                <td class="tdU">
                <div class="padre">
                <div class="hijo">
                <a href="Admin.jsp" class="boton">VOLVER A ZONA ADMIN</a>
                </div>
                </div>
                </td> 
                <td class="tdU">
                <input type="submit" value="ACTUALIZAR USUARIO">     
                </td>                 
                <td class="tdU">
                <div class="padre">
                <div class="hijo">
                <a href="GestionarUsuario.jsp" class="boton">LISTA DE USUARIOS</a>
                </div>
                </div>   
                </td>
           </tr> 
           </form>
             </table>   
            </div>
    </body>
</html>
