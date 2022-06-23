
import java.util.logging.Level;
import java.util.logging.Logger;


public class Demonio extends Thread {
    
public static final String ANSI_PURPLE = "\u001B[35m";

    public Demonio(){
        setDaemon(true);
    }
    
    @Override
     public void run() {
        
         // Un bucle infinito para que no pare de mandar publicidad
         
     while(true)
         
     {
         System.out.println("");
         System.out.println(ANSI_PURPLE+ "SONY PATROCINA ESTE COMBATE");
         System.out.println("");
         
         try {
             
             Thread.sleep(40000);
             
         } catch (InterruptedException ex) {
             Logger.getLogger(Demonio.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    }
}
