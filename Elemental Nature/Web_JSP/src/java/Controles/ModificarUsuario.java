package Controles;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class ModificarUsuario extends HttpServlet {

  private static final long serialVersionUID = 1L;
    Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
        String total = "";

        public ModificarUsuario() {
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

        String codigo = request.getParameter("codMU");
	String nombre = request.getParameter("nameMU");
        String pass = request.getParameter("passMU");
        String fecha = request.getParameter("fechaMU");
        
        String ad = "", bn = "", dl = "";

         ad =  request.getParameter("adminMU");  
         bn =  request.getParameter("banMU");     
         dl =  request.getParameter("borrarMU"); 


        boolean administrador = false, ban = false, borrar = false;
            
        System.out.println(ad+" y "+bn+" y "+dl);
        if (ad == null) {
           administrador = false;
       } else if (ad.equals("on")) {
            System.out.println("HOL");
           administrador = true;
       };

        if (bn == null) {
           ban = false;
       } else if (bn.equals("on")) {
           ban = true;
       };
        
       if (dl == null) {
           borrar = false;
       } else if (dl.equals("on")) {
           borrar = true;
       };
        
          try {      
        Class.forName("org.postgresql.Driver");
        conn=DriverManager.getConnection(connURL,username,pwd);
        
              if (borrar == true) {
           String sql = "delete from Usuario where codigo = "+codigo+"";
           PreparedStatement cm = conn.prepareStatement(sql);
           cm.executeUpdate(); 
           RequestDispatcher rd = request.getRequestDispatcher("GestionarUsuario.jsp");
           rd.forward(request, response); 
              }
          
        String comp = "update Usuario set nombre = '"+nombre+"', pass = '"+pass+"', "
                + " fecha = '"+fecha+"', administrador = "+administrador+","
                + " ban = "+ban+" where codigo = "+codigo+"";
              System.out.println(comp);
        PreparedStatement cm = conn.prepareStatement(comp);
           cm.executeUpdate();
        
        
            RequestDispatcher rd = request.getRequestDispatcher("GestionarUsuario.jsp");
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
