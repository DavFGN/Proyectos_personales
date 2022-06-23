package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Conexiones.Conexion;
import Clases.Luchador;

public class LuchadorDao {

	static Connection conexion;
	static PreparedStatement ps;
	
	public static int LuchadorInsertar(Luchador l){
		
		int status=0;
		
		try {
					
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("insert into Luchador values (?,?,?,?,?)");
			ps.setString(1, l.getCodigo());
			ps.setInt(2, l.getTipo());
			ps.setInt(3, l.getAtaque());
			ps.setInt(4, l.getDefensa());
			ps.setInt(5, l.getVelocidad());		
			ps.setInt(6, l.getPosf1());
			ps.setInt(7, l.getPosf2());
			ps.setInt(8, l.getBonusA());
			ps.setInt(9, l.getBonusT());
			ps.setInt(10, l.getBonusD());
			ps.setInt(11, l.getBonusE());
			ps.setInt(12, l.getBonusV());
			ps.setInt(13, l.getBonusS());
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}
	
	public static int LuchadorModificar(Luchador l){
		
		int status=0;
		
		try {
						
			conexion=Conexion.getConexion();
			ps=conexion.prepareStatement("update Luchador set tipo = ?, ataque = ?, "
					+ "defensa = ?, velocidad = ?, posf1 = ?, posf2 = ?, "
					+ "bonusA = ?, bonusT = ?, bonusD = ?, bonusE = ?, bonusV = ?,"
					+ "bonusS = ?  where codigo = ?");
			
			ps.setInt(1, l.getTipo());
			ps.setInt(2, l.getAtaque());
			ps.setInt(3, l.getDefensa());
			ps.setInt(4, l.getVelocidad());		
			ps.setInt(5, l.getPosf1());
			ps.setInt(6, l.getPosf2());
			ps.setInt(7, l.getBonusA());
			ps.setInt(8, l.getBonusT());
			ps.setInt(9, l.getBonusD());
			ps.setInt(10, l.getBonusE());
			ps.setInt(11, l.getBonusV());
			ps.setInt(12, l.getBonusS());
			ps.setString(13, l.getCodigo());
			status= ps.executeUpdate();

		conexion.close();
		
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");

		System.out.println(ex);
	}
		
	return status;
}

	public static int LuchadorBorrar(Luchador l){
	
	int status=0;
	
	try {
		
		conexion=Conexion.getConexion();
		ps=conexion.prepareStatement("delete from Luchador where codigo = ?");
		ps.setString(1, l.getCodigo());
		status= ps.executeUpdate();

	conexion.close();
	
	}catch(Exception ex){
		
		System.out.println("Fallo en la introduccion de datos");
	
		System.out.println(ex);
	}
		
	return status;
	
	}
			
}
