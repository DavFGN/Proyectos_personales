
import java.util.logging.Level;
import java.util.logging.Logger;


public class Luchador extends Thread {
    
    // Cada luchador tiene un nombre, ataque y salud 
    private float vida, ataque;
    private String nombre;
    Monitor monitor;

    public Luchador(Monitor monitor, String nombre, float vida, float ataque) {
        this.monitor = monitor;
        this.vida = vida;
        this.ataque = ataque;
        this.nombre = nombre;
        this.setName(nombre);
    }
 
    @Override
    public void run(){
        
        // Si no hay un ganador, se sigue jugando, se duerme para el cambio de hilo
        
         while(!monitor.isAcabado())
        {
        monitor.jugar();   
             try {
                 Thread.sleep(200);
             } catch (InterruptedException ex) {
                 Logger.getLogger(Luchador.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public float getAtaque() {
        return ataque;
    }

    public void setAtaque(float ataque) {
        this.ataque = ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
