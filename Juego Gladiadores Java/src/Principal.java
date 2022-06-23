
public class Principal {


    public static void main(String[] args) {
            
        System.out.println("Este juego es una lucha de 2 gladiadores, y presenta 2 opciones:");
        System.out.println("-Atacar, que resta vida al rival con los puntos de ataque que se tengan");
        System.out.println("-Potenciar, que multiplica un 50% el ataque propio");
        
         // Se inicia un hilo demonio que servirá para soltar publicidad cada 40 segundos
        Demonio demon = new Demonio();
        demon.start();
        
        // Se declara clase monitor
        Monitor monitor = new Monitor();
        
        // Se declaran los 2 luchadores, pasando el monitor y los datos de los luchadores
        Luchador j1 = new Luchador (monitor, "Hercules", 1000, 20);
        Luchador j2 = new Luchador (monitor, "Kratos", 800, 30);
        
        // Se declara la bendición, se pasa el monitor y los 2 luchadores para usarlos después
        Bendicion bendicion = new Bendicion(monitor);
        bendicion.setLucha1(j1);
        bendicion.setLucha2(j2);
        
        // Se declara la clase Cerbero, pasando el monitor y los datos de los luchadores    
        Cerbero cerbero = new Cerbero (monitor, j1, j2);

        // Arrancan los hilos
        j1.start();
        j2.start();       
        bendicion.start();
        cerbero.start();
        

    }
    
}
