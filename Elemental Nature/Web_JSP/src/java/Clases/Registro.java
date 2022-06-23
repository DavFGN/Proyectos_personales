package Clases;

//import com.sun.jmx.snmp.Timestamp;
import java.sql.Timestamp;

public class Registro {

	int codigo;
	int cod_usuario;
	Timestamp fecha_ult;
	String ip;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCod_usuario() {
		return cod_usuario;
	}
	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public Timestamp getFecha_ult() {
		return fecha_ult;
	}
	public void setFecha_ult(Timestamp fecha_ult) {
		this.fecha_ult = fecha_ult;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
