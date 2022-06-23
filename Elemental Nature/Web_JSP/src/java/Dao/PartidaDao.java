package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Conexiones.Conexion;
import Clases.Partida;

public class PartidaDao {

	static Connection conexion;
	static PreparedStatement ps;
	
	public static int PartidaInsertar(Partida p){
		
		int status=0;
		
		try {
			
			java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
			
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("insert into Partida (ganador, perdedor, fecha)"
                    + " values (?,?,?)");
			ps.setInt(1, p.getGanador());
			ps.setInt(2, p.getPerdedor());
			ps.setTimestamp(3, sqlDate);

			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}
	
	public static int PartidaModificar(Partida p){
		
		int status=0;
		
		try {
						
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("update Partida set ganador = ?, perdedor = ? "
					+ " where codigo = ?");
			
			ps.setInt(1, p.getGanador());
			ps.setInt(2, p.getPerdedor());
			ps.setInt(3, p.getCodigo());
			
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}

	public static int PartidaBorrar(Partida p){
	
	int status=0;
	
	try {
		
		conexion=Conexion.getConexion();
		ps=conexion.prepareStatement("delete from Partida where codigo = ?");
		ps.setInt(1, p.getCodigo());
		status= ps.executeUpdate();

	conexion.close();
	
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");
	
		System.out.println(ex);
	}
		
	return status;
	
	}
			
}
