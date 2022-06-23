
package Controles;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class EnviarMensajeAdmin extends HttpServlet {

  private static final long serialVersionUID = 1L;
    Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
        String total = "";

        public EnviarMensajeAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
        
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.getWriter().append("Served at: ").append(request.getContextPath());
    }
   
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

        int codU = 0;
	
        String asunto = request.getParameter("asunto");
	String nombreE = request.getParameter("nombreE");
        int codI = 0;
        String caja = request.getParameter("caja");
        String sql;

          try {      
        Class.forName("org.postgresql.Driver");
        conn=DriverManager.getConnection(connURL,username,pwd);
        
        String comp = "select codigo from Usuario where nombre like ? ";


        PreparedStatement cm = conn.prepareStatement(comp);
        cm.setString(1, nombreE);
        ResultSet rs = cm.executeQuery();
        while (rs.next()) {
         codI = rs.getInt("codigo"); 
            System.out.println("codigo i "+codI);
         }
        
              if (codI == 0) {
               sql = "insert into Correo (usu_e, usu_r, asunto, contenido,"
                       + "fecha, administrador)"
                       + " values (?,?,?,?,?, true)";   
              } else {
               sql = "insert into Correo (usu_e, usu_r, asunto, contenido,"
                       + "fecha, administrador)"
                       + " values (?,?,?,?,?, false)";  
              }
        
        
              System.out.println(sql);
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, codU);
            st.setInt(2, codI);
            st.setString(3, asunto);
            st.setString(4, caja);
            java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
            st.setTimestamp(5, sqlDate);  
            
            st.executeUpdate();
            
            st.close();  
            
            RequestDispatcher rd = request.getRequestDispatcher("BandejaEnvioAdmin.jsp");
            rd.forward(request, response); 
            
      }
       catch (ClassNotFoundException | SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }
       }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

