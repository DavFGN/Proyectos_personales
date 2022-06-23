package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Conexiones.Conexion;
import Clases.Registro;

public class RegistroDao {

	static Connection conexion;
	static PreparedStatement ps;
	
	public static int RegistroInsertar(Registro r){
		
		int status=0;
		
		try {
			
			java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
			
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("insert into Registro (cod_usuario, fecha_ult, ip)"
                    + " values (?,?,?)");
			
			ps.setInt(1, r.getCod_usuario());
			ps.setTimestamp(2, sqlDate);
			ps.setString(3, r.getIp());
			
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}
	
	public static int RegistroModificar(Registro r){
		
		int status=0;
		
		try {
			
			java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
						
			conexion=Conexion.getConexion();
			
			ps=conexion.prepareStatement("update Registro set cod_usuario = ?, fecha_ult = ?, "
					+ "ip = ? where codigo = ?");
			ps.setInt(1, r.getCod_usuario());
			ps.setTimestamp(2, sqlDate);
			ps.setString(3, r.getIp());
			ps.setInt(4, r.getCodigo());
			
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}

	public static int RegistroBorrar(Registro r){
	
	int status=0;
	
	try {
		
		conexion=Conexion.getConexion();
		
		ps=conexion.prepareStatement("delete from Registro where codigo = ?");
		ps.setInt(1, r.getCodigo());
		status= ps.executeUpdate();

	conexion.close();
	
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");
	
		System.out.println(ex);
	}
		
	return status;
	
	}
			
}
