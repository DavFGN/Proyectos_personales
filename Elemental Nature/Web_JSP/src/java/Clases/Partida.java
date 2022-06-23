package Clases;


import java.sql.Timestamp;

public class Partida {

	int codigo;
	int ganador;
	int perdedor;
	Timestamp fecha;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getGanador() {
		return ganador;
	}
	public void setGanador(int ganador) {
		this.ganador = ganador;
	}
	public int getPerdedor() {
		return perdedor;
	}
	public void setPerdedor(int perdedor) {
		this.perdedor = perdedor;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	
}
