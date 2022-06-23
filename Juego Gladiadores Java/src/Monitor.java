
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Monitor {
    
    // Variables estáticas para cambiar de color el texto según el efecto del dios correspondiente
public static final String ANSI_RED = "\u001B[31m";    
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";

   boolean acabado = false, primeraVez = true, prim = true;
   
   String primero = "", op = "", ganador = "";
   float AtqAct = 0;
   int ronda = 1;
   
   synchronized void iniciarBendicion (Luchador lucha1, Luchador lucha2){
       
       // Si el combate termina antes de la bendición, no se enviará ninguna
       
         if (!lucha1.isAlive() && !lucha2.isAlive()) {
             System.out.println("Los dioses han sido complacidos"); 
           
       // En caso de que el combate siga activo, se enviará una bendición aleatoria    
       // elegida mediante un número random 
       } else {
         Random rand = new Random(); 
         int value = rand.nextInt(10); 
       
       if (value<=4) {
           
           // Ares aumentará un 50% el ataque a los 2 luchadores
           
           System.out.println("");
           System.out.println( ANSI_RED + "El dios Ares ha visto la pelea y ha decidido daros su bendición");
          ares(lucha1);
          ares(lucha2);  
           System.out.println("");
       } else if (value>=5 && value <=8){
           
           // Esculapio aumentará un 20% la vida de los 2 luchadores
           
           System.out.println("");
           System.out.println(ANSI_GREEN+ "Esculapio ha sentido vuestro dolor y os cura");
           esculapio(lucha1);
           esculapio(lucha2);
           System.out.println("");
       }
       else {
           
           // Morfeo dormirá a uno de los 2 aleatoriamente durante 5 segundos 
           
           int dormir = rand.nextInt(1); 
           if (dormir==0) {
               morfeo(lucha1);
           } else{
               morfeo(lucha2);
           }
                 
       }}
   }
   
   void morfeo(Luchador luchador){
       System.out.println("");
        System.out.println(ANSI_BLUE+ "Morfeo ha puesto a dormir 5 segundos a "+luchador.getNombre());
               try {
                   luchador.sleep(5000);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
               }            
   }
   
   void ares(Luchador luchador){
       float temp = luchador.getAtaque();
           temp = temp + (temp/2);
                       luchador.setAtaque(temp);                      
 System.out.println(ANSI_RED + "Ataque de "+luchador.getNombre()+" ha aumentado a "+luchador.getAtaque());
   }
   
   void esculapio(Luchador luchador){
       float temp = luchador.getVida();
           temp = temp + (temp/5);
                       luchador.setVida(temp);
 System.out.println(ANSI_GREEN + "Salud de "+luchador.getNombre()+" ha aumentado a "+luchador.getVida());
   }
   
   synchronized void zeus(Luchador luchador){
       
       if (ronda==7 && prim) {
           System.out.println(ANSI_YELLOW + "Zeus ha decidido crear una tormenta de rayos");
           prim = false;
       }
       if (ronda>=7){
       System.out.println("");
       System.out.println(ANSI_YELLOW + "El campo de electricidad estática afecta a "+luchador.getNombre());
       float vidaA = luchador.getVida();
                 vidaA = vidaA - (vidaA/8);             
                 luchador.setVida(vidaA);
                 System.out.println(ANSI_YELLOW + "La salud de "+luchador.getNombre()+" se reduce a "+luchador.getVida());
       System.out.println("");
       }
   }
   
   synchronized void invocarCerbero(){
       
       // Método que sirve para dormir el hilo de Cerbero
    try {
        wait();
    } catch (InterruptedException ex) {
        Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
   
 synchronized void llamarCerbero(){
       
     // Mediante un número random, se decide si el hilo de Cerbero despierta
        Random rand = new Random(); 
         int value = rand.nextInt(18);             
            if (value==6) {
           notifyAll();
       }
   }

    synchronized void jugar (){
        
        // Se llama al luchador actual 
        
           Luchador jugadorActual = (Luchador) Thread.currentThread();
           
            Scanner pedir = new Scanner (System.in);
            
            // Lo primero será recibir el daño en caso de que la opción haya sido
            // elegir ataque, si la salud llega a 0, se da por finalizado el combate
            // mediante un boolean, que servirá tanto para dar el ganador como para
            // romper el bucle en la clase luchador
            // Se llama al notify para asegurar el levantamiento de la clase Cerbero
            // por si acaso no lo ha hecho durante el combate
            
            if (op.equals("A")) {
                 System.out.println(jugadorActual.getNombre()+" recibe el golpe");
                 float vidaA = jugadorActual.getVida();
                 vidaA = vidaA - AtqAct;
                 if (vidaA < 0) {
                    vidaA = 0;
                }
                 jugadorActual.setVida(vidaA);
                 System.out.println(jugadorActual.getNombre()+" tiene de vida "+jugadorActual.getVida());
                 if (jugadorActual.getVida()== 0) {
                     System.out.println(jugadorActual.getNombre()+" está fuera de combate");
                     System.out.println("");
                System.out.println(ganador+" ha ganado el combate");
                     System.out.println("");
                     acabado = true;
                     notifyAll();
                }
                 op = "";
        }
            
            // Se llama a este método para ver si se levanta el hilo de Cerbero o no
            llamarCerbero();
            
            // Si el combate no ha terminado, se continúa
            
            if (!acabado) {
            
                // Se controla el número de rondas
             if (primeraVez) {
            primero = jugadorActual.getNombre();
                System.out.println("Ronda "+ronda);
                ronda++;
            primeraVez = false;
        } else {if (primero.equals(jugadorActual.getNombre())) {
                 System.out.println("");
                System.out.println("Ronda "+ronda);
                ronda++;
        }}
              
             // Se informa de los datos del combatiente para que decida su acción
             
            System.out.println("");
            System.out.println("Nombre de jugador: "+jugadorActual.getNombre());
        System.out.println("Puntos de Salud: "+jugadorActual.getVida());
        System.out.println("Puntos de ataque: "+jugadorActual.getAtaque());
                System.out.println("");
 
                // Zeus a partir de la ronda 7, pondrá un campo de electricidad, 
                // que irá restando 12,5% de salud a los combatientes
                
                zeus(jugadorActual);
                
                // Se elige si atacar o potenciarse, se pedirá hasta que se de una p 
                // o a
                
        boolean repetirE = true;
      while (repetirE){
        
          System.out.println(jugadorActual.getNombre()+", elige atacar ("+jugadorActual.getAtaque()+") con A "
                + "o potenciarte con P: ");
        op = pedir.nextLine();
        op = op.toUpperCase();
        
               switch (op) {
                   case "A":
                       System.out.println(jugadorActual.getNombre()+" ataca a rival");
                           AtqAct = jugadorActual.getAtaque(); 
                           repetirE = false;
                       break;
                   case "P":
                       System.out.println(jugadorActual.getNombre()+" se concentra");
                       float aumentoA = jugadorActual.getAtaque();
                       aumentoA = aumentoA + (aumentoA/2);
                       jugadorActual.setAtaque(aumentoA);
                       System.out.println("Ataque de "+jugadorActual.getNombre()+" "
                               + "ha aumentado a "+jugadorActual.getAtaque());
                                                  repetirE = false;
                       break;
                   default:
                       System.out.println("No se ha elegido ninguna de las opciones, vuelva a elegir"); 
                       break;
               }
      }
       
      // Se guarda en la variable ganador el nombre del luchador por si el combate
      // finaliza, informar de que ha sido el ganador 
      
      ganador = jugadorActual.getNombre();
      
        }
    }

    public boolean isAcabado() {
        return acabado;
    }
  
    
}
