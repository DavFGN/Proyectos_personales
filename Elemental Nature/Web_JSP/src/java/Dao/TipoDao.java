package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Conexiones.Conexion;
import Clases.Tipo;

public class TipoDao {

	static Connection conexion;
	static PreparedStatement ps;
	
	public static int TipoInsertar(Tipo t){
		
		int status=0;
		
		try {
			
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("insert into Tipo (nombre)"
                    + " values (?)");
			ps.setString(1, t.getNombre());
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}
	
	public static int TipoModificar(Tipo t){
		
		int status=0;
		
		try {
						
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("update Tipo set nombre = ? where codigo = ?");
			ps.setString(1, t.getNombre());
			ps.setInt(5, t.getCodigo());
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}

	public static int TipoBorrar(Tipo t){
	
	int status=0;
	
	try {
		
		conexion=Conexion.getConexion();
		ps=conexion.prepareStatement("delete from Tipo where codigo = ?");
		ps.setInt(1, t.getCodigo());
		status= ps.executeUpdate();

	conexion.close();
	
	} catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");
	
		System.out.println(ex);
	}
		
	return status;
	
	}
			
}
