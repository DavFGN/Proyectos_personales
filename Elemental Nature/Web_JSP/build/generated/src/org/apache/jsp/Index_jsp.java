package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Calendar;

public final class Index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/ComprobarSesion.jsp");
    _jspx_dependants.add("/Pie.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"ISO-8859-1\">\r\n");
      out.write("<title>Index</title>\r\n");
      out.write("<link rel=\"icon\" href=\"./Img/logoVicsio.png\" type=\"image/gif\" sizes=\"16x16\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"./JS/JavaScript.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("  \r\n");
      out.write("    ");

    
String nom = (String)session.getAttribute("nombre");
String cnt = (String)session.getAttribute("contra");
Boolean adm = (Boolean)session.getAttribute("admin");


      out.write('\n');
      out.write('\n');
      out.write("\r\n");
      out.write("    <div class=\"inicio\">\r\n");
      out.write("<h1 class=\"tituloIndex\">INDEX</h1>\r\n");
      out.write("<table class=\"tablaIndex\">\r\n");
      out.write("<tbody>\r\n");
      out.write("<tr>\r\n");


String sin = "<td><a href='Login.jsp'>LOGIN</a></td>";
String con = "<td><a href='CerrarSesion.jsp'>SALIR</a></td>";

// Si nom y cnt son nulos, significa que no se ha iniciado sesión
if( nom == null || cnt == null ){
	out.print(sin);
} else {
    out.print(con);
}


      out.write("\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td><a href=\"Bandeja.jsp\">BANDEJA DE ENTRADA</a></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td><a href=\"Perfil.jsp\">PERFIL</a></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td><a href=\"Partidas.jsp\">PARTIDAS</a></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td><a href=\"Creadores.jsp\">CREADORES</a></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("    <td><a href=\"Galeria.jsp\">GALERIA</a></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td><a href=\"Admin.jsp\">ZONA ADMIN</a></td>\r\n");
      out.write("</tr>\r\n");
      out.write("</tbody>\r\n");
      out.write("</table>\r\n");
      out.write("    </div>  \r\n");
      out.write("<div class=\"sesion\">\r\n");
      out.write("    \r\n");
      out.write("    ");


        // Si está iniciada la sesión, muestra el nombre de usuario y privilegios
if( nom == null || cnt == null ){
	out.print("<h4> Todavía no ha iniciado sesión<br>"
                + "(no podrá acceder a las funciones hasta que no inicie)</h4>");
} else {
    out.print("<h3>Bienvenido "+nom+"</h3>");
    
    if (adm == true) {
    out.print("<br><h3>Privilegios de usuario: ADMIN</h3>"); 
        }
    else {
    out.print("<br><h3>Privilegios de usuario: ESTÁNDAR</h3>");     
    }
}


      out.write(" </div>\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"ISO-8859-1\">\r\n");
      out.write("<title>Pie de página</title>\r\n");
      out.write("<LINK REL=StyleSheet HREF=\"./CSS/Hoja.css\" TYPE=\"text/css\" MEDIA=screen>\r\n");
      out.write("<script type=\"text/javascript\" src=\"./JS/JavaScript.js\" charset=\"UTF-8\"></script>\r\n");
      out.write("<link rel=\"icon\" href=\"./Img/logoVicsio.png\" type=\"image/gif\" sizes=\"16x16\">\r\n");
      out.write("</head>\r\n");
      out.write("<body onload=\"startTime()\">\r\n");
      out.write("<footer>\r\n");
      out.write("<div class=\"pie\" id=\"txt\" style=\"background-color:yellow\">\r\n");
      out.write(" </footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
