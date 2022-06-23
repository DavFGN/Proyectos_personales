package Conexiones;
import java.sql.*;

public class Conexion implements Acceso {
	
static Connection conexion=null;
	
	public static Connection getConexion(){
		try{
			Class.forName("org.postgresql.Driver");
			conexion=DriverManager.getConnection(connURL,username,pwd);
		}
		catch(Exception ex){
			System.out.println(ex);
			
		}
		return conexion;
	}

}
