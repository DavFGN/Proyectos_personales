package Controles;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
public class CambiarP extends HttpServlet {

     private static final long serialVersionUID = 1L;
    Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
        String total = "";
        
    public CambiarP() {
        super();
       
    }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        
        HttpSession sesion = request.getSession(true);
        
        String usuario = (String) sesion.getAttribute("nombre");
	String contra = (String)  sesion.getAttribute("contra");
	
        String comp = "";
        
        String nameP = request.getParameter("nameP");
	String contraN = request.getParameter("contraN");
        String contraC = request.getParameter("contraC");
        String contraA = request.getParameter("contraA");

        boolean prim = false, seg = false;
        
          try {      
        Class.forName("org.postgresql.Driver");
        conn=DriverManager.getConnection(connURL,username,pwd);
        
            if (!contra.equals(contraA)) {
                System.out.println("No contraseña igual");
            RequestDispatcher rd = request.getRequestDispatcher("CambiarDatosP.jsp");
            rd.forward(request, response);   
            
            } else {
          
            if ((nameP.equals("")) && 
                (contraN.equals("")) ) {
                System.out.println("Sin datos");
              RequestDispatcher rd = request.getRequestDispatcher("CambiarDatosP.jsp");
              rd.forward(request, response);            
              } else {
            
            if ((!nameP.equals("")) ) 
                
                prim = true;               

                }
            
              if ( !contraN.equals("")){
                  
                seg = true;
                    
              }
              
              if (prim == false && seg == false) {
                  System.out.println("Datos mal insertados");
              RequestDispatcher rd = request.getRequestDispatcher("CambiarDatosP.jsp");
              rd.forward(request, response); 
              } else {
              
               if (prim == true && seg == true) {
               
              RequestDispatcher rd = request.getRequestDispatcher("CambiarDatosP.jsp");
             
                  if (contraC != null && !contraC.equals("")) {
                      
                   
                      if (contraC.equals(contraN)) {
                          
           comp = "update Usuario set pass = '"+contraC+"', nombre = '"+nameP+"' where nombre like '"+usuario+"'";
                          System.out.println("Cambiado nombre y contraseña");
             PreparedStatement cm = conn.prepareStatement(comp);
            cm.executeUpdate();                      
            rd = request.getRequestDispatcher("CerrarSesion.jsp");
              rd.forward(request, response);           
                      } else {
                          rd.forward(request, response);
                      }
                      
                  } else {
                      rd.forward(request, response);
                  }   }
               
               else {
             
              if (prim == true && seg == false) {
                  
            comp = "update Usuario set nombre = '"+nameP+"' where nombre like '"+usuario+"'";
                  System.out.println("Nombre cambiado");
            PreparedStatement cm = conn.prepareStatement(comp);
            cm.executeUpdate();                      
            RequestDispatcher rd = request.getRequestDispatcher("CerrarSesion.jsp");
              rd.forward(request, response); 
            
              }
                          
              else {    
              if (prim == false && seg == true) {
                  
            RequestDispatcher rd = request.getRequestDispatcher("CambiarDatosP.jsp");
             
                  if (contraC != null && !contraC.equals("")) {
                      
                   
                      if (contraC.equals(contraN)) {
                          
           comp = "update Usuario set pass = '"+contraC+"' where nombre like '"+usuario+"'";  
                          System.out.println("Contraseña cambiada");
           PreparedStatement cm = conn.prepareStatement(comp);
            cm.executeUpdate();                      
            rd = request.getRequestDispatcher("CerrarSesion.jsp");
              rd.forward(request, response); 
                       
                      } else {
                          rd.forward(request, response);
                      }
                      
                  } else {
                      rd.forward(request, response);
                  }             
              }
              }
              }
              }
              }               
                       
            
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