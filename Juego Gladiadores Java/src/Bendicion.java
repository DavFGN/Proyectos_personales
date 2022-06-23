
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bendicion extends Thread {
    
    // Se llaman a 2 luchadores para pasarlos al método en monitor
    
    Luchador lucha1, lucha2;
    
    Monitor monitor;

    public void setLucha1(Luchador lucha1) {
        this.lucha1 = lucha1;
    }

    public void setLucha2(Luchador lucha2) {
        this.lucha2 = lucha2;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Bendicion(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
   public void run(){
       
        try {
            // a los 30 segundos después de empezar el combate, una bendición será dada
            Thread.sleep(30000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Bendicion.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        monitor.iniciarBendicion(lucha1, lucha2);
   }
    
    
}
