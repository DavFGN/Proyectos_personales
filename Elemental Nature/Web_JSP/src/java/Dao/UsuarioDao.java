package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Conexiones.Conexion;
import Clases.Usuario;

public class UsuarioDao {

	static Connection conexion;
	static PreparedStatement ps;
	
	public static int UsuarioInsertar(Usuario u){
		
		int status=0;
		
		try {
			
			java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
			
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("insert into Usuario (nombre, pass, fecha, administrador, ban)"
                    + " values (?,?,?,?,?)");
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getPass());
			ps.setTimestamp(3, sqlDate);
			ps.setBoolean(4, u.isAdministrador());
			ps.setBoolean(5, u.isBan());
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}
	
	public static int UsuarioModificar(Usuario u){
		
		int status=0;
		
		try {
						
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("update Usuario set nombre = ?, pass = ?, "
					+ "administrador = ?, ban = ? where codigo = ?");
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getPass());
			ps.setBoolean(3, u.isAdministrador());
			ps.setBoolean(4, u.isBan());
			ps.setInt(5, u.getCodigo());
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}
        
	public static int UsuarioBorrar(Usuario u){
	
	int status=0;
	
	try {
		
		conexion=Conexion.getConexion();
		ps=conexion.prepareStatement("delete from Usuario where codigo = ?");
		ps.setInt(1, u.getCodigo());
		status= ps.executeUpdate();

	conexion.close();
	
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");
	
		System.out.println(ex);
	}
		
	return status;
	
	}
		
}
