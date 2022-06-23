
public class Cerbero extends Thread {
    
    // Se pide el monitor para poder dormirlo luego y los luchadores para los 
    // posibles mensajes a lanzar
    Monitor monitor;
    Luchador j1, j2;

    public Cerbero(Monitor monitor, Luchador j1, Luchador j2) {
        this.monitor = monitor;
        this.j1 = j1;
        this.j2 = j2;
    }
    
    @Override
    public void run(){
        
        // Mensaje para notificar que el hilo ha iniciado bien
        System.out.println("Se oye un aullido a lo lejos");  
        
        // Se duerme el hilo, para que el azar en la clase monitor lo depierte después
        monitor.invocarCerbero();
        
        // dependiendo de cuando haya despertado, sea en mitad del combate, haya ganado 
        // jugador 1 o 2, lanzará un mensaje u otro
        System.out.println("");
        System.out.println("Cerbero aparece en el campo de batalla");
        System.out.println("");
        if(j1.getVida() > 0 && j2.getVida() > 0){
            System.out.println("Hades ordena a Cerbero que no interrumpa la batalla");
        }
        else if (j1.getVida()<=0){
            System.out.println("Cerbero devora a "+j1.getNombre());
        } else if (j2.getVida()<=0){
            System.out.println("Cerbero devora a "+j2.getNombre());
        }
          
    }
}
