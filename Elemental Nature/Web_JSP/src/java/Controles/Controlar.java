package Controles;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class Controlar extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
        String total = "";

        public Controlar() {
        super();
        // TODO Auto-generated constructor stub
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
        
        String usuario = request.getParameter("nombre");
	String contra = request.getParameter("contra");
	Boolean admin = false;	
	HttpSession sesion = request.getSession(true);
		       
          try {      
        Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String comp = "select count(*) as total from Usuario where nombre like ? and pass like ?";
            PreparedStatement cm = conn.prepareStatement(comp);
            cm.setString(1, usuario);
            cm.setString(2, contra);
            ResultSet rs = cm.executeQuery();
            while (rs.next()) {
            total = rs.getString("total"); 
                System.out.println(total);
             }
            cm.close();
            
            if (total.equals("1")) {
                
            comp = "select administrador from Usuario where nombre like ? and pass like ?";
            cm = conn.prepareStatement(comp);
            cm.setString(1, usuario);
            cm.setString(2, contra);
            rs = cm.executeQuery();
            while (rs.next()) {
            admin = rs.getBoolean("administrador"); 
                System.out.println(admin);
             }
            cm.close();  
            
		sesion.setAttribute("nombre", usuario);
		sesion.setAttribute("contra", contra);
                sesion.setAttribute("admin", admin);
                
                RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
			rd.forward(request, response);
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
            }
      }
       catch (ClassNotFoundException | SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }
		
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}