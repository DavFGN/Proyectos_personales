
package Servidor;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;


public class VentanaJuego extends javax.swing.JFrame {

    ImageIcon  limpiar, imagen1, imagen2, imagen3, imagen4, imagen5, imagen6,     
               imagen7, imagen8, imagen9, imagen10, imagen11, imagen12;
    
    int tipo = 0, ataque = 0, defensa = 0, velocidad = 0, 
       posf1 = 0, posf2 = 0, 
       bonusA = 0, bonusT = 0, bonusD = 0, bonusE = 0, bonusV = 0, bonusS = 0;
    
    Luchador j1, j2, j3, j4, j5, j6, j7, j8;
    
    String winner = "";
    String loser = "";
    int puerto = 46000;
    int ronda = 1;
    String jugador1, jugador2;
    ServerSocket servidor;
    Socket socket;
    ArrayList <Socket> jugadores;
    BufferedReader entradas;
    OutputStream sal;
    PrintWriter salServidor;
    boolean ganador = false, inicio = true;
       
     Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
        
         public VentanaJuego() {
    try{ 
             // Icono de la aplicación
            setIconImage(ImageIO.read(new File("src/Recursos/logoVicsio.png")));
          } 
          catch (IOException e){
            e.printStackTrace();
          }
       BufferedImage img = null;
    try {
        img = ImageIO.read(new File("src/Recursos/fondo.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    Image dimg = img.getScaledInstance(1165, 743, Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(dimg);
    setContentPane(new JLabel(imageIcon));
        initComponents();
    DefaultCaret caret = (DefaultCaret)chat.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 
    
    DefaultCaret care = (DefaultCaret)informacion.getCaret();
    care.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    
    // Prepara los 2 bonus para que estén a la escucha de modificaciones y pueda
    // cambiarse la imagen de los mismos dependiendo del texto recibido en bsel
        comprobar_bonus(bsel1, bonus1);
        comprobar_bonus(bsel2, bonus2);
           
    } 
    
         // Hilo de escucha del servidor, explicaciones dentro.
         public class EscuchaS extends Thread {
        BufferedReader entradas;
        
          public EscuchaS(Socket sock){
           socket = sock; 
                     
        try {
            
            // Recibe el primer nombre, y lo referencia en el jugador 1
            entradas = new BufferedReader(new InputStreamReader(socket.getInputStream()));                 
            if (jugador1 == null) {
                jugador1 = entradas.readLine();
                System.out.println(jugador1);
                nombre1.setText(jugador1);
                repaint();
            }
            else {
                
                // Si el jugador 1 ya está dentro, lo referencia al jugador 2
                jugador2 = entradas.readLine();
                nombre2.setText(jugador2);
                repaint();
            }
            
            // En caso de estar repetidos los nombres, significa que es el mismo
            // jugador, y por lo tanto, se les manda a los 2 clientes este hecho
            // y dado que no se puede luchar contra si mismo, se cerrará la comunicación
            
            if (nombre1.getText().equals(nombre2.getText())) {
                for (Socket jugador : jugadores) {
            sal = jugador.getOutputStream(); 
            salServidor = new PrintWriter(sal, true);  
            salServidor.println("repetido");  
            }
                JOptionPane.showMessageDialog(null, "El jugador ha intentado jugar"
                        + " contra si mismo, este servidor se cerrará"); 
                System.exit(0);
            }
            else {
                // Si está todo bien, se procede a iniciar el hilo de escucha
                System.out.println(jugador1+" y "+jugador2);                
                
                // Por si acaso, se controla el tamaño de la lista, se informa en el
             // panel, boton de empezar y se activa este último
             if (jugadores.size()>1) {
                 informacion.append("\nPULSE EL BOTÓN 'TODO LISTO' PARA EMPEZAR EL JUEGO");
                empezar.setText("TODO LISTO");
                empezar.setEnabled(true);
                
            }
                repaint();
               start(); 
            }                           
            
            } catch (IOException ex) {
            Logger.getLogger(EscuchaS.class.getName()).log(Level.SEVERE, null, ex);
        }       
        }
          
          @Override
        public void run(){

             try {              
            repaint();
                // Prepara el string necesario, esperando recibir información de uno
                // de los clientes
                String menRec = entradas.readLine();
                // Mientras no haya ganador, el juego sigue
             while (ganador == false){
                 // En caso de haber ganador y por motivos de evitar quedarse en bucle,
                // se comprueba si hay ganador para salir
                 if (ganador == true) {
                    break; 
                 }
                 
                 System.out.println(menRec);
                 // Si recibe un mensaje que empieza por chat, significa que es un mensaje
                 // dirigido a comunicarse, por lo que lo coge, lo procesa, mostrandolo 
                 // en el panel mismo del servidor, y lo envía a los 2 clientes
                 if (menRec.startsWith("chat")) {
                     String[] parts = menRec.split("-");
                     String part2 = parts[1];
                     chat.append("\n"+part2);
                     
                     for (Socket jugador : jugadores) {
                    sal = jugador.getOutputStream(); 
                    salServidor = new PrintWriter(sal, true);  
                    salServidor.println(menRec);
                     }
                 }
                    // Si recibe un mensaje con opción al principio, es que un jugador ha decidido
                    // cambiar de posición a sus luchadores y pintan de nuevo las cajas
                 if (menRec.startsWith("opcion")) {
                     pintar_n_pos(menRec);
                 }
                 repaint();
                 
                 // Esperando mensaje a recibir
                 menRec = entradas.readLine();
             }
             
             // Si ha salido del bucle, es que el juego ha finalizado, e informa a los luchadores
             for (Socket jugador : jugadores) {
                    sal = jugador.getOutputStream(); 
                    salServidor = new PrintWriter(sal, true);  
                    salServidor.println("Juego finalizado");
                     }
             
             } 
             catch (ConnectException c){
                 System.out.println("No se ha podido conectar");
            }
             catch (SocketException e){
                 System.out.println("Ha caído un jugador");
             }
              catch (IOException ex) {
                Logger.getLogger(EscuchaS.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } 
    }
     
         // Se pasan las cajas del campo de batalla al método
     public void pasar_cajas_datos (){
         
         cargar_datos(pos1);
         cargar_datos(pos2);
         cargar_datos(pos3);
         cargar_datos(pos4);
         cargar_datos(pos5);
         cargar_datos(pos6);
         cargar_datos(bsel1);
         
         cargar_datos(pos11);
         cargar_datos(pos12);
         cargar_datos(pos13);
         cargar_datos(pos14);
         cargar_datos(pos15);
         cargar_datos(pos16);
         cargar_datos(bsel2);
         
     }
     
     // Comprueba el contenido de la caja y si coincide con uno de los códigos de
     // los luchadores, se pasa el luchador y la caja en la que está.
     // Se va pasando del jugador 1 al 8, dando a entender que los 4 primeros deben 
     // ser del jugador 1 y los 4 restantes del 2. No puede haber posibilidad de fallo
     // porque se controla en el cliente
     public void cargar_datos (JTextField j) {
         
         String t = j.getText();
         
         if (t.equals("L1") || t.equals("L1") || t.equals("L2") ||
             t.equals("L3") || t.equals("L4") || t.equals("L5") ||
             t.equals("L6") || t.equals("L7") || t.equals("L8") ||
             t.equals("L9") || t.equals("L10")|| t.equals("L11")|| 
             t.equals("L12") ) 
         {
             if (j1 == null) {
                 
                load_data(j1, t);
                
             } 
             else if (j2 == null) {
                 
                load_data(j2, t);
                
             }
             else if (j3 == null) {
                 
                load_data(j3, t);
                
             }
             else if (j4 == null) {
                 
                load_data(j4, t);
                
             }
             else if (j5 == null) {
                 
                load_data(j5, t);
                
             }
             else if (j6 == null) {
                 
                load_data(j6, t);
                
             }
             else if (j7 == null) {
                 
                load_data(j7, t);
                
             }
             else if (j8 == null) {
                 
                load_data(j8, t);
                
             }
 
         }
     }  
     
     // Recoge los datos del luchador escogido de la base de datos, se declara una instancia
     // y se iguala el luchador con la misma. 
     // No se hace directamente, es decir, hacer j1 = new luchador... porque da un error
     // que no sé por qué ocurre
     public void load_data (Luchador l, String c) {
       
         try {
         Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String s = "select * from Luchador where codigo like ?";
            PreparedStatement pr;       
            pr = conn.prepareStatement(s);
            pr.setString(1, c);
            ResultSet re = pr.executeQuery();
            
            while(re.next()){
                
             tipo = re.getInt("tipo");
             ataque = re.getInt("ataque"); 
             defensa = re.getInt("defensa");
             velocidad = re.getInt("velocidad"); 
             posf1 = re.getInt("posf1");
             posf2 = re.getInt("posf2"); 
             bonusA = re.getInt("bonusa"); 
             bonusT = re.getInt("bonust");
             bonusD = re.getInt("bonusd"); 
             bonusE = re.getInt("bonuse");
             bonusV = re.getInt("bonusv"); 
             bonusS = re.getInt("bonuss");   
           
            }
           
            l = new Luchador 
                (c, tipo, ataque, defensa, velocidad, posf1, posf2, 
                    bonusA, bonusT, bonusD, bonusE, bonusV, bonusS);
            
           if (j1 == null) {
                j1 = l;
                
             } 
             else if (j2 == null) {
                j2 = l;
                
             }
             else if (j3 == null) {
                j3 = l;
                
             }
             else if (j4 == null) {
                j4 = l;
                
             }
             else if (j5 == null) {
                j5 = l;
                
             }
             else if (j6 == null) {
                j6 = l;
                
             }
             else if (j7 == null) {              
                j7 = l;
                
             }
             else if (j8 == null) {                
                j8 = l;
                
             }
            
            System.out.println("Luchador "+l.getCodigo()+" cargado");
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     // Una vez se tiene la caja y luchador, se comprueba si una de las posiciones favoritas
     // encaja con la caja del mismo. Si es así, se aumentan sus estadísticas
     public void pos_luchador (JTextField j, Luchador l){     
         String i = j.getName();
         int p = Integer.parseInt(i);
         String u = j.getText().toUpperCase();

         if (u.equals(l.getCodigo())) {
         if (l.getPosf1() == p || l.getPosf2() == p) {    
             System.out.println(""+l.getCodigo()+" está en su posición favorita");
            l.setAtaque(l.getAtaque() + (l.getAtaque() * 25 / 100));
            l.setVelocidad(l.getVelocidad() + (l.getVelocidad() * 25 / 100));
            l.setDefensa(l.getDefensa() + 5);
         }
         }
     }  
     
     // Se pasan la caja correspondiente y se pasa al método la misma y los 4 luchadores
     // del jugador 1, forma que se tiene de ver si la caja tiene uno de los 4 luchadores
     public void pos_luchador_a (JTextField j){
         
         pos_luchador(j, j1);
         pos_luchador(j, j2);
         pos_luchador(j, j3);
         pos_luchador(j, j4);
         
     }
     
     // Se pasan las cajas del campo de batalla del jugador 1
     public void pos_luchador_1 (){
         
         pos_luchador_a(pos1);
         pos_luchador_a(pos2);
         pos_luchador_a(pos3);
         pos_luchador_a(pos4);
         pos_luchador_a(pos5);
         pos_luchador_a(pos6);
         
     }
     
     // Se pasan la caja correspondiente y se pasa al método la misma y los 4 luchadores
     // del jugador 1, forma que se tiene de ver si la caja tiene uno de los 4 luchadores
     public void pos_luchador_b (JTextField j){
         
         pos_luchador(j, j5);
         pos_luchador(j, j6);
         pos_luchador(j, j7);
         pos_luchador(j, j8);
         
     }
     
     // Se pasan las cajas del campo de batalla del jugador 2
     public void pos_luchador_2 (){
         
         pos_luchador_b(pos11);
         pos_luchador_b(pos12);
         pos_luchador_b(pos13);
         pos_luchador_b(pos14);
         pos_luchador_b(pos15);
         pos_luchador_b(pos16);
         
     }
     
     // Método que reune los 2 usados para luchadores del jugador 1 y 2
     public void pos_luchadorT (){
         
         pos_luchador_1 ();
         pos_luchador_2 ();
                 
     }
     
     // Se comprueba cual de los 4 luchadores es el que está en la posición de bonus
     // y se pasa al método los datos del mismo y luchadores
     public void luchador_bonus (JTextField j, Luchador a, Luchador b, Luchador c, 
             Luchador d) {
        
         int t, aa, at, ad, ae, av, as;
         
         if (j.getText().equals(a.getCodigo())) { 
             
          t   = a.getTipo();
          aa  = a.getBonusA();
          at  = a.getBonusT();
          ad  = a.getBonusD();
          ae  = a.getBonusE();
          av  = a.getBonusV();
          as  = a.getBonusS();
          
          luchador_bonus_a(b, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(c, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(d, t, aa, at, ad, ae, av, as);
         }
         
         if (j.getText().equals(b.getCodigo())) { 
             
          t   = b.getTipo();
          aa  = b.getBonusA();
          at  = b.getBonusT();
          ad  = b.getBonusD();
          ae  = b.getBonusE();
          av  = b.getBonusV();
          as  = b.getBonusS();
          
          luchador_bonus_a(a, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(c, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(d, t, aa, at, ad, ae, av, as);
         }
         
         if (j.getText().equals(c.getCodigo())) { 
             
          t   = c.getTipo();
          aa  = c.getBonusA();
          at  = c.getBonusT();
          ad  = c.getBonusD();
          ae  = c.getBonusE();
          av  = c.getBonusV();
          as  = c.getBonusS();
          
          luchador_bonus_a(a, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(b, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(d, t, aa, at, ad, ae, av, as);
         }
         
         if (j.getText().equals(d.getCodigo())) { 
             
          t   = d.getTipo();
          aa  = d.getBonusA();
          at  = d.getBonusT();
          ad  = d.getBonusD();
          ae  = d.getBonusE();
          av  = d.getBonusV();
          as  = d.getBonusS();
          
          luchador_bonus_a(a, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(b, t, aa, at, ad, ae, av, as);
          luchador_bonus_a(c, t, aa, at, ad, ae, av, as);
         }
         
     }
     
     // Aumenta las estadísticas del luchador dependiendo de los bonus que ofrece
     // y si son del mismo tipo, un incremento especial
     public void luchador_bonus_a (Luchador b, int t, int aa, int at, int ad, 
             int ae, int av, int as){
         
          b.setAtaque(b.getAtaque() + (b.getAtaque() * aa / 100)); 
          b.setDefensa(b.getDefensa() + ad);
          b.setVelocidad(b.getVelocidad() + (b.getVelocidad() * av / 100)); 
          
          if (b.getTipo() == t) {
              
          b.setAtaque(b.getAtaque() + (b.getAtaque() * at / 100)); 
          b.setDefensa(b.getDefensa() + ae);
          b.setVelocidad(b.getVelocidad() + (b.getVelocidad() * as / 100)); 
          
          } 
     }
     
     // Se pasa el bonus y los 4 luchadores de cada jugador al método
     public void luchador_bonus_p (){
         
         luchador_bonus(bsel1, j1, j2, j3, j4);
         luchador_bonus(bsel2, j5, j6, j7, j8);
         
     }
     
     // Se pasan las filas verticales de los campos y los luchadores
     public void luchador_sinergiaT (){
         
         luchador_sinergia (pos1, pos2, j1, j2, j3, j4);
         luchador_sinergia (pos3, pos4, j1, j2, j3, j4);
         luchador_sinergia (pos5, pos6, j1, j2, j3, j4);
         
         luchador_sinergia (pos11, pos12, j5, j6, j7, j8);
         luchador_sinergia (pos13, pos14, j5, j6, j7, j8);
         luchador_sinergia (pos15, pos16, j5, j6, j7, j8);
         
     }
     
     // en el caso de que haya 2 jugadores en fila, se procederá a aplicar la sinergía 
     public void luchador_sinergia (JTextField t1, JTextField t2, Luchador a, 
             Luchador b, Luchador c, Luchador d){
         
         if (t1.getText().equals(a.getCodigo()) || 
             t1.getText().equals(b.getCodigo()) ||
             t1.getText().equals(c.getCodigo()) ||
             t1.getText().equals(d.getCodigo()) ) {
            
         if (t2.getText().equals(a.getCodigo()) || 
             t2.getText().equals(b.getCodigo()) ||
             t2.getText().equals(c.getCodigo()) ||
             t2.getText().equals(d.getCodigo()) ) {
             
           
             if (a.getCodigo().equals(t1.getText())) {
                 if (b.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(a, b);                   
                 } else if (c.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(a, c); 
                 } else if (d.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(a, d); 
                 }   
             }
             
             if (b.getCodigo().equals(t1.getText())) {
                 if (a.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(b, a);                   
                 } else if (c.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(b, c); 
                 } else if (d.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(b, d); 
                 }   
             }
             
             if (c.getCodigo().equals(t1.getText())) {
                 if (a.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(c, a);                   
                 } else if (b.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(c, b); 
                 } else if (d.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(c, d); 
                 }   
             }
             
             if (d.getCodigo().equals(t1.getText())) {
                 if (a.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(d, a);                   
                 } else if (b.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(d, b); 
                 } else if (c.getCodigo().equals(t2.getText())) {
                     calcular_sinergia(d, c); 
                 }   
             }
           
         }    
             
         }
     }
     
     // Dependiendo de los tipos de los 2 luchadores, se enviarán sus datos al método
     // correspondiente
     
     public void calcular_sinergia (Luchador a, Luchador b){
        
         if ((j1.getTipo() == 1 && j2.getTipo() == 2) ||
             (j1.getTipo() == 2 && j2.getTipo() == 1) ||
             (j1.getTipo() == 3 && j2.getTipo() == 4) ||
             (j1.getTipo() == 4 && j2.getTipo() == 3) ) {
             
                calcular_sinergia_a (a, b);        
        }
         
         if ((j1.getTipo() == 2 && j2.getTipo() == 3) ||
             (j1.getTipo() == 3 && j2.getTipo() == 2) ||
             (j1.getTipo() == 1 && j2.getTipo() == 4) ||
             (j1.getTipo() == 4 && j2.getTipo() == 1) ) {
             
                calcular_sinergia_d (a, b);        
        }
         
         if ((j1.getTipo() == 5 && j2.getTipo() == 6) ||
             (j1.getTipo() == 6 && j2.getTipo() == 5) ||
             (j1.getTipo() == 5 && j2.getTipo() == 5) ||
             (j1.getTipo() == 6 && j2.getTipo() == 6) ) {
             
             calcular_sinergia_b (a, b);        
        }
         
         if ((j1.getTipo() == 5 && j2.getTipo() == 1) ||
             (j1.getTipo() == 5 && j2.getTipo() == 2) ||
             (j1.getTipo() == 5 && j2.getTipo() == 3) ||
             (j1.getTipo() == 5 && j2.getTipo() == 4) ||
             (j1.getTipo() == 1 && j2.getTipo() == 5) ||
             (j1.getTipo() == 2 && j2.getTipo() == 5) ||
             (j1.getTipo() == 3 && j2.getTipo() == 5) ||
             (j1.getTipo() == 4 && j2.getTipo() == 5) ||
             (j1.getTipo() == 6 && j2.getTipo() == 1) ||
             (j1.getTipo() == 6 && j2.getTipo() == 2) ||
             (j1.getTipo() == 6 && j2.getTipo() == 3) ||
             (j1.getTipo() == 6 && j2.getTipo() == 4) ||
             (j1.getTipo() == 1 && j2.getTipo() == 6) ||
             (j1.getTipo() == 2 && j2.getTipo() == 6) ||
             (j1.getTipo() == 3 && j2.getTipo() == 6) ||
             (j1.getTipo() == 4 && j2.getTipo() == 6) ) {
             
                calcular_sinergia_c (a, b); 
                
        }           
     }
    
     // En caso de ser una sinergia potenciadora, se aumentarán las estadísticas
     public void calcular_sinergia_a (Luchador a, Luchador b){
          a.setAtaque(a.getAtaque() + (a.getAtaque() * 25 / 100)); 
          a.setDefensa(a.getDefensa() + 10);
          a.setVelocidad(a.getVelocidad() + (a.getVelocidad() * 25 / 100));   
             
          b.setAtaque(b.getAtaque() + (b.getAtaque() * 25 / 100)); 
          b.setDefensa(b.getDefensa() + 10);
          b.setVelocidad(b.getVelocidad() + (b.getVelocidad() * 25 / 100));
    }
    
     // Si son los luchadores del tipo oscuridad o luz, se aplicará este incremento
     // en vez del normal
    public void calcular_sinergia_b (Luchador a, Luchador b){
        a.setAtaque(a.getAtaque() + (a.getAtaque() * 15 / 100)); 
          a.setDefensa(a.getDefensa() + 5);
          a.setVelocidad(a.getVelocidad() + (a.getVelocidad() * 15 / 100));   
             
          b.setAtaque(b.getAtaque() + (b.getAtaque() * 15 / 100)); 
          b.setDefensa(b.getDefensa() + 5);
          b.setVelocidad(b.getVelocidad() + (b.getVelocidad() * 15 / 100));
    }
    
    // Si uno de los luchadores es tipo luz u oscuridad y el otro de cualquier otro
    // tipo, se aplica este bonus
    public void calcular_sinergia_c (Luchador a, Luchador b){
        a.setAtaque(a.getAtaque() + (a.getAtaque() * 10 / 100)); 
          a.setDefensa(a.getDefensa() + 10);
          a.setVelocidad(a.getVelocidad() + (a.getVelocidad() * 10 / 100));   
             
          b.setAtaque(b.getAtaque() + (b.getAtaque() * 10 / 100)); 
          b.setDefensa(b.getDefensa() + 10);
          b.setVelocidad(b.getVelocidad() + (b.getVelocidad() * 10 / 100));
    }
    
    // Si la sinergía es desfavoralbe, se reducirán las estadísticas
    public void calcular_sinergia_d (Luchador a, Luchador b){
          a.setAtaque(a.getAtaque() - (a.getAtaque() * 5 / 100)); 
          a.setDefensa(a.getDefensa() - 5);
          if (a.getDefensa() < 0) {
            a.setDefensa(0);
          }
          a.setVelocidad(a.getVelocidad() - (a.getVelocidad() * 5 / 100));   
             
          b.setAtaque(b.getAtaque() -+ (b.getAtaque() * 5 / 100)); 
          b.setDefensa(b.getDefensa() - 5);
          if (b.getDefensa() < 0) {
            b.setDefensa(0);
          }
          b.setVelocidad(b.getVelocidad() - (b.getVelocidad() * 5 / 100));
    }  
    
    // Se suma aleatoriamente una pequeña cantidad en las estadísticas del luchador
    public void subida_random(Luchador l){
        
         Random rand = new Random(); 
         int v = rand.nextInt(20); 
         
        l.setAtaque(l.getAtaque()+ v);
        
        v = rand.nextInt(20); 
        
        l.setVelocidad(l.getVelocidad()+ v);
        
        v = rand.nextInt(5); 
        
        l.setDefensa(l.getDefensa() + v);
        
    }
    
    // Se pasa al método de subida random los luchadores
    public void subida_randomT(){
        
        subida_random(j1);
        subida_random(j2);
        subida_random(j3);
        subida_random(j4);
        subida_random(j5);
        subida_random(j6);
        subida_random(j7);
        subida_random(j8);
        
    }
    
    // Si el luchador que se pasa por parámetro (calculado por velocidad en el hilo 
    // de juego) tiene el mismo identificador que uno de los 8, significa que es el 
    // mismo y se procede a pasarlo al método de ataque correspondiente
    public void atacar(Luchador l){
        
        if (j1.hashCode() == l.hashCode()) {
            
        atacar_p(l);
        
        }
        
        if (j2.hashCode() == l.hashCode()) {
         
        atacar_p(l);
          
        }
        
        if (j3.hashCode() == l.hashCode()) {
         
        atacar_p(l);
             
        }
        
        if (j4.hashCode() == l.hashCode()) {
         
        atacar_p(l);
             
        }
        
        if (j5.hashCode() == l.hashCode()) {
         
        atacar_w(l);
              
        }
        
        if (j6.hashCode() == l.hashCode()) {
         
        atacar_w(l);
              
        }
        
        if (j7.hashCode() == l.hashCode()) {
         
        atacar_w(l);
              
        }
        
        if (j8.hashCode() == l.hashCode()) {
         
        atacar_w(l);
              
        }
        
    } 
    
    // Se pasa a los métodos el luchador referenciado, y se le pasa todas las opciones
    // posibles de ataque y a donde deben dirigirse sus ataques
    public void atacar_p (Luchador l){
       
        ataque_x(l, pos1, pos2, pos15, pos16);
        ataque_x(l, pos3, pos4, pos13, pos14);
        ataque_x(l, pos5, pos6, pos11, pos12);
        
    }

    // Se comprueba si el luchador está en una de las posiciones recibidas por
    // parámetro. Si es así, se comprueba si tiene delante a algún luchador,
    // si no tiene a nadie, ataca a la salud del jugador rival directamente
    public void ataque_x (Luchador l, JTextField p1, JTextField p2,
                          JTextField p3, JTextField p4){
        
         if (l.getCodigo().equals(p1.getText()) ||
            l.getCodigo().equals(p2.getText())  ) {
            
            if (p3.getText().startsWith("L")) {
                
               ataque_y(p3, l);
                
            }
            else if (p4.getText().startsWith("L")) {
              
               ataque_y(p4, l); 
               
            } else {
              
        String s = salud2.getText();
        int ps = Integer.parseInt(s);
        
        informacion.append("\n Luchador "+l.getCodigo()+", con "+l.getAtaque()+" de ataque, "
                + "ataca directamente al jugador rival");
        int a = l.getAtaque() * 2;
        
        ps = ps - a;
        
        informacion.append("\nHa sido un golpe crítico");
        
         if (ps <= 0) {
            ps = 0;
            ganador = true;
            winner = jugador1;
            salud2.setBackground(Color.red);
            salud2.setForeground(Color.white);
            informacion.append("\n"+winner+" ha ganado");
        }
        
        s = String.valueOf(ps);
        
        salud2.setText(s);
                
            }
             
        }
        
    }
    
    // Se comprueba cual es el luchador delante del que va a atacar comprobando
    // su posición para pasárselo al método 
    public void ataque_y (JTextField x, Luchador l){
        
    
                if (x.getText().equals(j5.getCodigo())) {
                    
                   ataque_aa(l, j5);
                    
                }
                
                if (x.getText().equals(j6.getCodigo())) {
                   
                    ataque_aa(l, j6);
                    
                }
                
                if (x.getText().equals(j7.getCodigo())) {
                    
                    ataque_aa(l, j7);
                    
                }
                
                if (x.getText().equals(j8.getCodigo())) {
                    
                    ataque_aa(l, j8);
                    
                }
                
            
        
    }
    
    // El luchador ataca y el luchador rival sirve de escudo, dependiendo de su defensa
    // el jugador rival recibirá más o menos daño. En caso de bajar la salud del rival a 0,
    // se informa de ello, se cambia el boolean del ganador y se pasan los nombres de los
    // jugadores al método encargado de guardar los resultados en la base de datos
    public void ataque_aa(Luchador l1, Luchador l2){
        
        String s = salud2.getText();
        int ps = Integer.parseInt(s);
        
        informacion.append("\n Luchador "+l1.getCodigo()+", con "+l1.getAtaque()+" de ataque, "
                + "ataca a "+l2.getCodigo());
        int a = l1.getAtaque() - (l1.getAtaque() * l2.getDefensa() / 100);
        informacion.append("\nLuchador "+l2.getCodigo()+" con "+l2.getDefensa()+" de defensa, "
                + "recibe el golpe y reduce su defensa");
        l2.setDefensa(l2.getDefensa() - 25);
        if (l2.getDefensa() <= 0) {
            
          l2.setDefensa(0);  
          
        }
        if (l2.getDefensa() == 0) {
           informacion.append("\n"+l2.getCodigo()+" está mermado"); 
        }
        
        ps = ps - a;
        
        if (ps <= 0) {
            
            ps = 0;
            ganador = true;
            winner = jugador1;
            loser = jugador2;
            salud2.setBackground(Color.red);
            salud2.setForeground(Color.white);
            informacion.append("\n"+winner+" ha ganado, "+loser+" ha perdido");
            repaint();
            enviar_datos (jugador1, jugador2);
            
        }
        
        s = String.valueOf(ps);
        
        salud2.setText(s);
        
    }
    
    // Se pasa a los métodos el luchador referenciado, y se le pasa todas las opciones
    // posibles de ataque y a donde deben dirigirse sus ataques
    public void atacar_w (Luchador l){
       
        ataque_q(l, pos11, pos12, pos5, pos6);
        ataque_q(l, pos13, pos14, pos3, pos4);
        ataque_q(l, pos15, pos16, pos1, pos2);
        
    }

    // Se comprueba si el luchador está en una de las posiciones recibidas por
    // parámetro. Si es así, se comprueba si tiene delante a algún luchador,
    // si no tiene a nadie, ataca a la salud del jugador rival directamente
    public void ataque_q (Luchador l, JTextField p1, JTextField p2,
                          JTextField p3, JTextField p4){
        
         if (l.getCodigo().equals(p1.getText()) ||
            l.getCodigo().equals(p2.getText())  ) {
            
            if (p3.getText().startsWith("L")) {
                
               ataque_f(p3, l);
                
            }
            else if (p4.getText().startsWith("L")) {
              
               ataque_f(p4, l); 
               
            } else {
              
        String s = salud1.getText();
        int ps = Integer.parseInt(s);
        
        informacion.append("\n Luchador "+l.getCodigo()+", con "+l.getAtaque()+" de ataque, "
                + "ataca directamente al jugador rival");
        
        int a = l.getAtaque() * 2;
        
        ps = ps - a;
        
        informacion.append("\nHa sido un golpe crítico");
        
         if (ps <= 0) {
            ps = 0;
            ganador = true;
            winner = jugador1;
            salud1.setBackground(Color.red);
            salud1.setForeground(Color.white);
            informacion.append("\n"+winner+" ha ganado");
        }
        
        s = String.valueOf(ps);
        
        salud2.setText(s);
                
            }
        }
        
    }
    
    // Se comprueba cual es el luchador delante del que va a atacar comprobando
    // su posición para pasárselo al método 
    public void ataque_f (JTextField x, Luchador l){
        
    
                if (x.getText().equals(j1.getCodigo())) {
                    
                   ataque_d(l, j1);
                    
                }
                
                if (x.getText().equals(j2.getCodigo())) {
                   
                    ataque_d(l, j2);
                    
                }
                
                if (x.getText().equals(j3.getCodigo())) {
                    
                    ataque_d(l, j3);
                    
                }
                
                if (x.getText().equals(j4.getCodigo())) {
                    
                    ataque_d(l, j4);
                    
                }
                
            
        
    }
    
    // El luchador ataca y el luchador rival sirve de escudo, dependiendo de su defensa
    // el jugador rival recibirá más o menos daño. En caso de bajar la salud del rival a 0,
    // se informa de ello, se cambia el boolean del ganador y se pasan los nombres de los
    // jugadores al método encargado de guardar los resultados en la base de datos
    public void ataque_d(Luchador l1, Luchador l2){
        
        String s = salud1.getText();
        int ps = Integer.parseInt(s);
        
        informacion.append("\n Luchador "+l1.getCodigo()+" con "+l1.getAtaque()+" de ataque, "
                + "ataca a "+l2.getCodigo());
        
        int a = l1.getAtaque() - (l1.getAtaque() * l2.getDefensa() / 100);
        informacion.append("\nLuchador "+l2.getCodigo()+" con "+l2.getDefensa()+" de defensa, "
                + "recibe el golpe y reduce su defensa");
        l2.setDefensa(l2.getDefensa() - 25);
        
        if (l2.getDefensa() <= 0) {
            
          l2.setDefensa(0);  
          
        }
        if (l2.getDefensa() == 0) {
           informacion.append("\n"+l2.getCodigo()+" está mermado"); 
        }
              
        ps = ps - a;
        
        if (ps <= 0) {
            
            ps = 0;
            ganador = true;
            winner = jugador2;
            loser = jugador1;
            salud1.setBackground(Color.red);
            salud1.setForeground(Color.white);
            informacion.append("\n"+winner+" ha ganado, "+loser+" ha perdido");
            repaint();
            enviar_datos (jugador2, jugador1);
            
        }
        
        s = String.valueOf(ps);
        
        salud1.setText(s);
    }
    
    // Si la defensa de algún jugador es superior a 100, la convierte el 100,
    // límite establecido
    public void controlar_defensa(){
        
        if (j1.getDefensa() > 100) {
            j1.setDefensa(100);
        }
        
        if (j2.getDefensa() > 100) {
            j2.setDefensa(100);
        }
        
        if (j3.getDefensa() > 100) {
            j3.setDefensa(100);
        }
        
        if (j4.getDefensa() > 100) {
            j4.setDefensa(100);
        }
        
        if (j5.getDefensa() > 100) {
            j5.setDefensa(100);
        }
        
        if (j6.getDefensa() > 100) {
            j6.setDefensa(100);
        }
        
        if (j7.getDefensa() > 100) {
            j7.setDefensa(100);
        }
        
        if (j8.getDefensa() > 100) {
            j8.setDefensa(100);
        }
        
    }
    
    // Se pasan las variables de los luchadores y el panel que será afectado
    public void campo_activar(){
        
       elegir_efecto(j1, j2, j3, j4, pan1);
       elegir_efecto(j5, j6, j7, j8, pan2);
       
    }
    
    // Dependiendo del azar, el campo se activará tomando en cuenta el tipo del
    // luchador escogido
    public void elegir_efecto(Luchador l1, Luchador l2, Luchador l3, Luchador l4,
    JPanel p){
        
         Random rand = new Random(); 
        int value = rand.nextInt(4); 
        
        switch (value) {
            case 0:
                comprobar_tipo(l1.getTipo(), p, l1, l2, l3, l4);
                break;
            case 1:
                comprobar_tipo(l2.getTipo(), p, l1, l2, l3, l4);
                break;
            case 2:
                comprobar_tipo(l3.getTipo(), p, l1, l2, l3, l4);
                break;
            case 3:
                comprobar_tipo(l4.getTipo(), p, l1, l2, l3, l4);
                break;
            default:
                break;
        }
    }
    
    // Dependiendo del tipo, el equipo recibirá un bonus distinto, pintando el campo
    // con el color del mismo
    public void comprobar_tipo(int t, JPanel p, Luchador l1, Luchador l2, 
            Luchador l3, Luchador l4){
        
        switch (t) {
            case 1:
               p.setBackground(Color.green); 
               agrupar_au_vel(l1, l2, l3, l4);
                break;
            case 2:
               p.setBackground(Color.red); 
               agrupar_au_ata(l1, l2, l3, l4);
                break;
            case 3:
               p.setBackground(Color.blue); 
               agrupar_au_vel(l1, l2, l3, l4); 
                break;
            case 4:
               p.setBackground(Color.orange); 
               agrupar_au_def(l1, l2, l3, l4); 
                break;
            case 5:
               p.setBackground(Color.yellow); 
               agrupar_au_ata(l1, l2, l3, l4); 
                break;
            case 6:
               p.setBackground(Color.black); 
               agrupar_au_def(l1, l2, l3, l4);
                break;    
            default:
                break;
        }
        
    }
    
    // Se pasan los luchadores al método
    public void agrupar_au_def (Luchador l1, Luchador l2, 
            Luchador l3, Luchador l4){
        
        aumentar_defensa(l1);
        aumentar_defensa(l2);
        aumentar_defensa(l3);
        aumentar_defensa(l4);
        
    }
    
    // Se aumenta la estadística seleccionada
    public void aumentar_defensa (Luchador l){
        l.setDefensa(l.getDefensa()+(l.getDefensa() * 20 / 100));
    }
    
    // Se pasan los luchadores al método
    public void agrupar_au_ata (Luchador l1, Luchador l2, 
            Luchador l3, Luchador l4){
        
        aumentar_ataque(l1);
        aumentar_ataque(l2);
        aumentar_ataque(l3);
        aumentar_ataque(l4);
        
    }
    
    // Se aumenta la estadística seleccionada
    public void aumentar_ataque (Luchador l){
        l.setAtaque(l.getAtaque()+(l.getAtaque() * 20 / 100));
    }
    
    // Se pasan los luchadores al método
    public void agrupar_au_vel(Luchador l1, Luchador l2, 
            Luchador l3, Luchador l4){
        aumentar_velocidad(l1);
        aumentar_velocidad(l2);
        aumentar_velocidad(l3);
        aumentar_velocidad(l4);
    }
    
    // Se aumenta la estadística seleccionada
    public void aumentar_velocidad (Luchador l){
        l.setVelocidad(l.getVelocidad()+(l.getVelocidad() * 20 / 100));
    }
    
    // Método que reune el efecto que tendrán los luchadores, tanto en campo propio
    // como contrario, positivos y negativos
    public void campos(){
        
        campo_activar();
        campo_rival();
        
        repaint();
    }
    
    // Se pasan las variables al método, en uno con los luchadores del jugador 1
    // y el otro con los del jugador 2
    public void campo_rival (){
        
       elegir_efectoR(j1, j2, j3, j4, j5, j6, j7, j8, pos11, pos12, pos13, pos14,
               pos15, pos16);
       elegir_efectoR(j5, j6, j7, j8, j1, j2, j3, j4, pos1, pos2, pos3, pos4,
               pos5, pos6);
       
    }
    
    // Dependiendo del azar, el rival sufrirá un determinado efecto a base del tipo
    // del luchador elegido
        public void elegir_efectoR(Luchador l1, Luchador l2, Luchador l3, 
        Luchador l4, Luchador l5, Luchador l6, Luchador l7, Luchador l8, 
        JTextField t1, JTextField t2, JTextField t3, JTextField t4, 
        JTextField t5, JTextField t6){
        
         Random rand = new Random(); 
        int value = rand.nextInt(4); 
        
        switch (value) {
        case 0:
            comprobar_tipoR(l1.getTipo(), t1, t2, t3, t4, t5, t6, l5, l6, l7, l8);
            break;
        case 1:
            comprobar_tipoR(l2.getTipo(), t1, t2, t3, t4, t5, t6, l5, l6, l7, l8);
            break;
        case 2:
            comprobar_tipoR(l3.getTipo(), t1, t2, t3, t4, t5, t6, l5, l6, l7, l8);
            break;
        case 3:
            comprobar_tipoR(l4.getTipo(), t1, t2, t3, t4, t5, t6, l5, l6, l7, l8);
            break;
        default:
            break;
        }
    }
    
        // Dependiendo del tipo, las cajas del rival cambiarán de color (forma de 
        // indicar que ha sufrido x efecto del rival) y sufrirán un descenso de
        // estadísticas 
    public void comprobar_tipoR(int t, JTextField t1, JTextField t2, 
    JTextField t3, JTextField t4, JTextField t5, JTextField t6, 
    Luchador l1, Luchador l2, Luchador l3, Luchador l4){
        
        switch (t) {
            case 1:
               pintar_cajas(Color.lightGray, Color.BLACK, t1, t2,t3, t4, t5, t6);
               agrupar_re_vel(l1, l2, l3, l4);
                break;
            case 2:
               pintar_cajas(Color.darkGray, Color.white, t1, t2,t3, t4, t5, t6);
               agrupar_re_ata(l1, l2, l3, l4);
                break;
            case 3:
               pintar_cajas(Color.lightGray, Color.BLACK, t1, t2,t3, t4, t5, t6);
               agrupar_re_vel(l1, l2, l3, l4); 
                break;
            case 4:
               pintar_cajas(Color.red, Color.white, t1, t2,t3, t4, t5, t6); 
               agrupar_re_def(l1, l2, l3, l4); 
                break;
            case 5:
               pintar_cajas(Color.darkGray, Color.white, t1, t2,t3, t4, t5, t6);
               agrupar_re_ata(l1, l2, l3, l4); 
                break;
            case 6:
               pintar_cajas(Color.red, Color.white, t1, t2,t3, t4, t5, t6);
               agrupar_re_def(l1, l2, l3, l4);
                break;    
            default:
                break;
        }
        
    }
    
    // Pasa las variables del color con el que debe ser pintado la caja
    public void pintar_cajas(Color b, Color l, JTextField t1, JTextField t2, 
    JTextField t3, JTextField t4, JTextField t5, JTextField t6){
        
        pintar_una(b, l, t1);
        pintar_una(b, l, t2);
        pintar_una(b, l, t3);
        pintar_una(b, l, t4);
        pintar_una(b, l, t5);
        pintar_una(b, l, t6);
        
        
    }
    
    // Pinta la caja y el texto 
    public void pintar_una (Color b, Color l, JTextField t1){
        t1.setBackground(b);
        t1.setForeground(l);
    }
    
    // Pasa al método los luchadores que verán reducidas sus estadísticas
    public void agrupar_re_def (Luchador l1, Luchador l2, 
            Luchador l3, Luchador l4){
        
        restar_defensa(l1);
        restar_defensa(l2);
        restar_defensa(l3);
        restar_defensa(l4);
        
    }
    
    // Resta al luchador la estadística correspondiente
    public void restar_defensa (Luchador l){
        l.setDefensa(l.getDefensa()-(l.getDefensa() * 20 / 100));
    }
    
    // Pasa al método los luchadores que verán reducidas sus estadísticas
    public void agrupar_re_ata (Luchador l1, Luchador l2, 
            Luchador l3, Luchador l4){
        
        restar_ataque(l1);
        restar_ataque(l2);
        restar_ataque(l3);
        restar_ataque(l4);
        
    }
    
    // Resta al luchador la estadística correspondiente
    public void restar_ataque (Luchador l){
        l.setAtaque(l.getAtaque()-(l.getAtaque() * 20 / 100));
    }
    
    // Pasa al método los luchadores que verán reducidas sus estadísticas
    public void agrupar_re_vel(Luchador l1, Luchador l2, 
            Luchador l3, Luchador l4){
        restar_velocidad(l1);
        restar_velocidad(l2);
        restar_velocidad(l3);
        restar_velocidad(l4);
    }
    
    // Resta al luchador la estadística correspondiente
    public void restar_velocidad (Luchador l){
        l.setVelocidad(l.getVelocidad()-(l.getVelocidad() * 20 / 100));
    }
    
    // Se envía a la base de datos los datos de la partida y se llama al método 
    // de guardar los equipos en la base de datos
    public void enviar_datos (String g, String p){
        
        int win = 0, los = 0;
         try {
                Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
           
        String sql = "select codigo from usuario where nombre like ?"; 
            PreparedStatement sr = conn.prepareStatement(sql);
            System.out.println(g + " " + p);
            sr.setString(1, g);
            
            ResultSet re = sr.executeQuery();
            
            while (re.next()) {
                win = re.getInt("codigo");
             }    
            
            String sql2 = "select codigo from usuario where nombre like ?"; 
            PreparedStatement sr2 = conn.prepareStatement(sql2);
            sr2.setString(1, p);
            
            ResultSet re2 = sr2.executeQuery();
            
            while (re2.next()) {
                los = re2.getInt("codigo");   
             } 
            

            String insert_reg = "insert into Partida (ganador, perdedor, fecha) "
                    + "values (?, ?, ?)";
            System.out.println("------ eeee " + win + " " + los);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());

            PreparedStatement inser = conn.prepareStatement(insert_reg);
            
            inser.setInt(1, win);
            inser.setInt(2, los);
            inser.setTimestamp(3, sqlDate);
            inser.executeUpdate();
            
            insertar_luchadores();
            
            informacion.append("\nDatos de la batalla guardados en la base de datos");
       
      }
       catch (ClassNotFoundException | SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }
    }
    
    // Se coge el código de la partida generado, el de los luchadores participantes
    // y se pasan al método 
    public void insertar_luchadores(){
        
         int codigoP = 0, cod1 = 0, cod2 = 0;
         
         try {
                Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String comp = "select codigo from Partida where" 
        +"(ganador = (select codigo from usuario where nombre like ?) or" 
        +" perdedor = (select codigo from usuario where nombre like ?))" 
        +"and "
        +"(ganador = (select codigo from usuario where nombre like ?) or" 
        +" perdedor = (select codigo from usuario where nombre like ?))" 
        +"order by fecha desc limit 1";
            
            PreparedStatement cm = conn.prepareStatement(comp);
            cm.setString(1, jugador1);
            cm.setString(2, jugador1);
            cm.setString(3, jugador2);
            cm.setString(4, jugador2);
            ResultSet rs = cm.executeQuery();
            while (rs.next()) {
            codigoP = rs.getInt("codigo"); 
                System.out.println("Codigo de lucha: "+codigoP);
             }            
            
            comp = "select codigo from usuario where nombre like '"+jugador1+"'";
            cm = conn.prepareStatement(comp);
            rs = cm.executeQuery();
            while (rs.next()) {
            cod1 = rs.getInt("codigo"); 
                System.out.println("Codigo de luchador1: "+cod1);
             }

            comp = "select codigo from usuario where nombre like '"+jugador2+"'";
            cm = conn.prepareStatement(comp);          
            rs = cm.executeQuery();
            while (rs.next()) {
            cod2 = rs.getInt("codigo"); 
                System.out.println("Codigo de luchador2: "+cod2);
             }
           
            cm.close();
            
            insertar_luchadoresE(codigoP, cod1, j1.getCodigo());
            insertar_luchadoresE(codigoP, cod1, j2.getCodigo());
            insertar_luchadoresE(codigoP, cod1, j3.getCodigo());
            insertar_luchadoresE(codigoP, cod1, j4.getCodigo());
            
            insertar_luchadoresE(codigoP, cod2, j5.getCodigo());
            insertar_luchadoresE(codigoP, cod2, j6.getCodigo());
            insertar_luchadoresE(codigoP, cod2, j7.getCodigo());
            insertar_luchadoresE(codigoP, cod2, j8.getCodigo());
           
      }
       catch (ClassNotFoundException | SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            } 
    }
    
    
    // Se envía a la base de datos el equipo usado del jugador
    public void insertar_luchadoresE (int p, int j, String l){
        
        try {
            
        String insert_reg = "insert into Equipo (partida, jugador, luchador)"
                    + " values ("+p+", "+j+", '"+l+"')"; 
        
            PreparedStatement inser;    
            inser = conn.prepareStatement(insert_reg);
            
            inser.executeUpdate();
            System.out.println("Insertado "+p+", "+j+", "+l+"");
        } catch (SQLException e) {
            System.out.println("Conexión fallida, error: "+e.getMessage());
        }
            
    }
        
    public void pintar_n_pos (String pin){
        
        String[] parts = pin.split("-");
        
        String n = parts[1];
        String z = parts[2];
        String x = parts[3];
        String c = parts[4];
        String v = parts[5];
        String l = parts[6];
        String m = parts[7];
        String b = parts[8];
        
        if (n.equals(jugador1)) {
            
            pos1.setText(z);
            pos2.setText(x);
            pos3.setText(c);
            pos4.setText(v);
            pos5.setText(l);
            pos6.setText(m);
            bsel1.setText(b);

        }
        
        if (n.equals(jugador2)) {
            
            pos11.setText(z);
            pos12.setText(x);
            pos13.setText(c);
            pos14.setText(v);
            pos15.setText(l);
            pos16.setText(m);
            bsel2.setText(b);

        }
        
    }
    
    public void comprobar_bonus(JTextField t, JLabel j){   
        t.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void changedUpdate(DocumentEvent e) {        
        }
        @Override
        public void removeUpdate(DocumentEvent e) {          
        }
        @Override
        public void insertUpdate(DocumentEvent e) {         
            pintar_bonus(t, j);
        }
        
    });
    }
    
    public void pintar_bonus(JTextField t, JLabel j){
         imagen1 = new ImageIcon("src/Recursos/L1.jpg");    
         imagen2 = new ImageIcon("src/Recursos/L2.png");     
         imagen3 = new ImageIcon("src/Recursos/L3.jpg");    
         imagen4 = new ImageIcon("src/Recursos/L4.png");      
         imagen5 = new ImageIcon("src/Recursos/L5.jpg");      
         imagen6 = new ImageIcon("src/Recursos/L6.png");     
         imagen7 = new ImageIcon("src/Recursos/L7.jpg");  
         imagen8 = new ImageIcon("src/Recursos/L8.jpg");      
         imagen9 = new ImageIcon("src/Recursos/L9.png");      
         imagen10 = new ImageIcon("src/Recursos/L10.jpg");        
         imagen11 = new ImageIcon("src/Recursos/L11.jpg");        
         imagen12 = new ImageIcon("src/Recursos/L12.png");
         
       
         switch (t.getText()) {
           case "L1":
               met_rec(imagen1, j);         
               break;
           case "L2":
               met_rec(imagen2, j);             
               break;
           case "L3":
               met_rec(imagen3, j);
               break;  
           case "L4":
               met_rec(imagen4, j);
               break;
           case "L5":
               met_rec(imagen5, j);
               break;
           case "L6":
               met_rec(imagen6, j);
               break; 
           case "L7":
               met_rec(imagen7, j);
               break;
           case "L8":
               met_rec(imagen8, j);
               break;
           case "L9":
               met_rec(imagen9, j);
               break;
           case "L10":
               met_rec(imagen10, j);
               break;
           case "L11":
               met_rec(imagen11, j);
               break;
           case "L12":
               met_rec(imagen12, j);
               break; 
           default:
               j.setText("");
       }
    }

    public void met_rec (ImageIcon img, JLabel lab ){    
        ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance
        (lab.getWidth(), lab.getHeight(), java.awt.Image.SCALE_DEFAULT));
         lab.setIcon(icono);
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan2 = new javax.swing.JPanel();
        pos15 = new javax.swing.JTextField();
        pos16 = new javax.swing.JTextField();
        pos14 = new javax.swing.JTextField();
        pos13 = new javax.swing.JTextField();
        pos12 = new javax.swing.JTextField();
        pos11 = new javax.swing.JTextField();
        bonus2 = new javax.swing.JLabel();
        salud2 = new javax.swing.JTextField();
        nombre2 = new javax.swing.JTextField();
        bsel2 = new javax.swing.JTextField();
        pan1 = new javax.swing.JPanel();
        pos2 = new javax.swing.JTextField();
        pos1 = new javax.swing.JTextField();
        pos3 = new javax.swing.JTextField();
        pos4 = new javax.swing.JTextField();
        pos5 = new javax.swing.JTextField();
        pos6 = new javax.swing.JTextField();
        bonus1 = new javax.swing.JLabel();
        salud1 = new javax.swing.JTextField();
        nombre1 = new javax.swing.JTextField();
        bsel1 = new javax.swing.JTextField();
        empezar = new javax.swing.JButton();
        activar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        informacion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tiempo = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELEMENTAL NATURE");
        setMinimumSize(new java.awt.Dimension(1165, 743));
        setResizable(false);
        setSize(new java.awt.Dimension(1165, 743));

        pan2.setMaximumSize(new java.awt.Dimension(531, 284));
        pan2.setMinimumSize(new java.awt.Dimension(531, 284));

        pos15.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos15.setText("POSICION");
        pos15.setMinimumSize(new java.awt.Dimension(90, 31));
        pos15.setName("15"); // NOI18N

        pos16.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos16.setText("POSICION");
        pos16.setMinimumSize(new java.awt.Dimension(90, 31));
        pos16.setName("16"); // NOI18N

        pos14.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos14.setText("POSICION");
        pos14.setMinimumSize(new java.awt.Dimension(90, 31));
        pos14.setName("14"); // NOI18N

        pos13.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos13.setText("POSICION");
        pos13.setMinimumSize(new java.awt.Dimension(90, 31));
        pos13.setName("13"); // NOI18N

        pos12.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos12.setText("POSICION");
        pos12.setMinimumSize(new java.awt.Dimension(90, 31));
        pos12.setName("12"); // NOI18N

        pos11.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos11.setText("POSICION");
        pos11.setMinimumSize(new java.awt.Dimension(90, 31));
        pos11.setName("11"); // NOI18N

        bonus2.setBackground(new java.awt.Color(255, 255, 255));
        bonus2.setFont(new java.awt.Font("Impact", 1, 24)); // NOI18N

        salud2.setEditable(false);
        salud2.setBackground(new java.awt.Color(0, 255, 0));
        salud2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        salud2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        salud2.setText("10000");

        nombre2.setEditable(false);
        nombre2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nombre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre2.setText("NOMBRE");

        bsel2.setEditable(false);
        bsel2.setBackground(new java.awt.Color(0, 0, 0));
        bsel2.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        bsel2.setForeground(new java.awt.Color(255, 255, 255));
        bsel2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bsel2.setText("Esperando bonus");
        bsel2.setMinimumSize(new java.awt.Dimension(97, 24));

        javax.swing.GroupLayout pan2Layout = new javax.swing.GroupLayout(pan2);
        pan2.setLayout(pan2Layout);
        pan2Layout.setHorizontalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(salud2))
                .addGap(18, 18, 18)
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombre2)
                    .addComponent(bsel2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(bonus2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salud2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addComponent(bonus2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bsel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addGap(15, 28, Short.MAX_VALUE))
        );

        pos15.getAccessibleContext().setAccessibleName("15");
        pos16.getAccessibleContext().setAccessibleName("6");
        pos14.getAccessibleContext().setAccessibleName("14");
        pos13.getAccessibleContext().setAccessibleName("13");
        pos12.getAccessibleContext().setAccessibleName("12");
        pos11.getAccessibleContext().setAccessibleName("11");

        pan1.setMaximumSize(new java.awt.Dimension(531, 284));
        pan1.setMinimumSize(new java.awt.Dimension(531, 284));

        pos2.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos2.setText("POSICION");
        pos2.setMinimumSize(new java.awt.Dimension(90, 31));
        pos2.setName("2"); // NOI18N

        pos1.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos1.setText("POSICION");
        pos1.setMinimumSize(new java.awt.Dimension(90, 31));
        pos1.setName("1"); // NOI18N

        pos3.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos3.setText("POSICION");
        pos3.setMinimumSize(new java.awt.Dimension(90, 31));
        pos3.setName("3"); // NOI18N

        pos4.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos4.setText("POSICION");
        pos4.setMinimumSize(new java.awt.Dimension(90, 31));
        pos4.setName("4"); // NOI18N

        pos5.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos5.setText("POSICION");
        pos5.setMinimumSize(new java.awt.Dimension(90, 31));
        pos5.setName("5"); // NOI18N

        pos6.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos6.setText("POSICION");
        pos6.setMinimumSize(new java.awt.Dimension(90, 31));
        pos6.setName("6"); // NOI18N

        bonus1.setBackground(new java.awt.Color(255, 255, 255));
        bonus1.setFont(new java.awt.Font("Impact", 1, 24)); // NOI18N

        salud1.setEditable(false);
        salud1.setBackground(new java.awt.Color(0, 255, 0));
        salud1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        salud1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        salud1.setText("10000");

        nombre1.setEditable(false);
        nombre1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nombre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre1.setText("NOMBRE");

        bsel1.setEditable(false);
        bsel1.setBackground(new java.awt.Color(0, 0, 0));
        bsel1.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        bsel1.setForeground(new java.awt.Color(255, 255, 255));
        bsel1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bsel1.setText("Esperando bonus");
        bsel1.setMinimumSize(new java.awt.Dimension(97, 24));

        javax.swing.GroupLayout pan1Layout = new javax.swing.GroupLayout(pan1);
        pan1.setLayout(pan1Layout);
        pan1Layout.setHorizontalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pan1Layout.createSequentialGroup()
                        .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(salud1))
                .addGap(24, 24, 24)
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bsel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(bonus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre1))
                .addGap(19, 19, Short.MAX_VALUE))
        );
        pan1Layout.setVerticalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan1Layout.createSequentialGroup()
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pan1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bsel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(bonus1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salud1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pos2.getAccessibleContext().setAccessibleName("2");
        pos1.getAccessibleContext().setAccessibleName("1");
        pos3.getAccessibleContext().setAccessibleName("3");
        pos4.getAccessibleContext().setAccessibleName("4");
        pos5.getAccessibleContext().setAccessibleName("5");
        pos6.getAccessibleContext().setAccessibleName("6");

        empezar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        empezar.setText("ESPERANDO JUGADORES");
        empezar.setEnabled(false);
        empezar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empezarActionPerformed(evt);
            }
        });

        activar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        activar.setText("ACTIVAR SERVIDOR");
        activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activarActionPerformed(evt);
            }
        });

        chat.setBackground(new java.awt.Color(0, 0, 51));
        chat.setColumns(20);
        chat.setForeground(new java.awt.Color(255, 255, 255));
        chat.setRows(5);
        jScrollPane1.setViewportView(chat);

        informacion.setColumns(20);
        informacion.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        informacion.setRows(5);
        jScrollPane2.setViewportView(informacion);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CHAT");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INFORMACIÓN COMBATE");

        tiempo.setBackground(new java.awt.Color(204, 204, 204));
        tiempo.setForeground(new java.awt.Color(255, 0, 0));
        tiempo.setMaximum(24);
        tiempo.setOrientation(1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(activar, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empezar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 161, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addGap(135, 135, 135))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1)
                                            .addComponent(jScrollPane2))
                                        .addContainerGap())))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(211, 211, 211))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activar)
                    .addComponent(empezar))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(69, 69, 69))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Botón para empezar el juego cuando ya están los jugadores dentro
    private void empezarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empezarActionPerformed
         // Se deshabilita botón
        empezar.setEnabled(false);
         Runnable runn = new Runnable(){
            @Override
            public void run() {
        
        try {
            // Se manda un mensaje indicando a los jugadores que ya pueden empezar
            // a luchar y los datos 
            for (Socket jugadore : jugadores) {
            sal = jugadore.getOutputStream(); 
            salServidor = new PrintWriter(sal, true);  
            salServidor.println("empieza");            
              }
   
        } catch (IOException ex) {
            Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
            }};
        Thread hilo = new Thread (runn);
      hilo.start();
      
      Thread juego = new Juego();
      juego.start();
    }//GEN-LAST:event_empezarActionPerformed
   
    // Activa el servidor para poder recibir a los jugadores, limitado a 2
    private void activarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarActionPerformed
     activar.setEnabled(false);
     informacion.append("\nServidor activado");
     Runnable runn = new Runnable(){
            @Override
            public void run() {
        
        try {
           servidor = new ServerSocket(puerto, 2);
           jugadores = new ArrayList();
             for (int i = 0; i < 2; i++) {  
                      if (i==0) {
                    socket = servidor.accept(); 
                    jugadores.add(socket);  
                    new EscuchaS(socket);                   
                    informacion.append("\nConectado "+jugador1);
                    informacion.append("\nEsperando que se conecte otro jugador...");
                     repaint();
                 } else {
                     socket = servidor.accept(); 
                     jugadores.add(socket);                   
                     new EscuchaS(socket);                                    
                     informacion.append("\nConectado "+jugador2);
                     repaint();
                      }
            }
             
             servidor.close();          
             
           
        } catch (IOException ex) {
            Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
            }};
     Thread hilo = new Thread (runn);
      hilo.start();
    }//GEN-LAST:event_activarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaJuego().setVisible(true);               
            }
        });
        
    }
    
    // Hilo que controla el juego, explicaciones dentro
    public class Juego extends Thread {

        @Override
        public void run(){
            
            // Mientras no haya ganador, se continua el juego
            while(ganador==false) 
                
            {
            
                // Reloj de tiempo del juego, cada 24 segundos, la acción se activa
                // La barra se va recargando segundo a segundo hasta los 24
            for (int i = 0; i < 24; i++) {
                
             try {
                 
             Thread.sleep(1000);
             tiempo.setValue(i+1);
             
            repaint();
            
             } catch (InterruptedException ex) {
            Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
            } }     
            
            // Si es la primera ronda, es el principio del juego, por lo tanto, carga 
            // los datos de los luchadores en su instancia correspondiente
                if (inicio == true) {
                   
                   pasar_cajas_datos();
                   inicio = false; 
                   
                }
        
        informacion.append("\nRONDA "+ronda);  
        ronda++;
        System.out.println("//////DATOS LUCHADORES ANTES DE LOS INCREMENTOS");
        System.out.println("Datos luchador "+j1.getCodigo()+" a = "+j1.getAtaque()+" "
                + "d = "+j1.getDefensa()+" v = "+j1.getVelocidad()+"");
        System.out.println("Datos luchador "+j2.getCodigo()+" a = "+j2.getAtaque()+" "
                + "d = "+j2.getDefensa()+" v = "+j2.getVelocidad()+"");
        System.out.println("Datos luchador "+j3.getCodigo()+" a = "+j3.getAtaque()+" "
                + "d = "+j3.getDefensa()+" v = "+j3.getVelocidad()+"");
        System.out.println("Datos luchador "+j4.getCodigo()+" a = "+j4.getAtaque()+" "
                + "d = "+j4.getDefensa()+" v = "+j4.getVelocidad()+"");
        System.out.println("Datos luchador "+j5.getCodigo()+" a = "+j5.getAtaque()+" "
                + "d = "+j5.getDefensa()+" v = "+j5.getVelocidad()+"");
        System.out.println("Datos luchador "+j2.getCodigo()+" a = "+j2.getAtaque()+" "
                + "d = "+j6.getDefensa()+" v = "+j6.getVelocidad()+"");
        System.out.println("Datos luchador "+j7.getCodigo()+" a = "+j7.getAtaque()+" "
                + "d = "+j7.getDefensa()+" v = "+j7.getVelocidad()+"");
        System.out.println("Datos luchador "+j8.getCodigo()+" a = "+j8.getAtaque()+" "
                + "d = "+j8.getDefensa()+" v = "+j8.getVelocidad()+"");
        System.out.println("////////////////////////////////////////////////");   
        
        // AQUÍ SE EXPLICARÁ EL FUNCIONAMIENTO POR ENCIMA DE LOS MISMOS, PARA SABER MÁS, 
        // IR AL MÉTODO CON CTRL + CLICK IZQUIERDO
        
        // Comprueba si los luchadores están en una de sus posiciones favoritas, en 
        //cuyo caso aumentará sus estadísticas
        pos_luchadorT();
        
        // Aumenta las estadísticas de acuerdo al bonus, comprobando si cumplen también
        // los requisitos
        luchador_bonus_p();    
        
        // Comprueba si hay sinergía, en cuyo caso aplicará la lógica de tipos
        luchador_sinergiaT();
        
        // Se sube aleatoriamente las estadísticas de los luchadores en una pequeña cantidad.
        // Hecho básicamente para evitar en lo máximo una posible igualdad sin variaciones
        subida_randomT();

        // Si el luchador después del aumento de estadísticas tiene más de 100 de defensa,
        // Se ajusta para que solo tenga 100
        controlar_defensa();
            
        System.out.println("//////DATOS LUCHADORES DESPUÉS DE LOS INCREMENTOS");
        System.out.println("Datos luchador "+j1.getCodigo()+" a = "+j1.getAtaque()+" "
                + "d = "+j1.getDefensa()+" v = "+j1.getVelocidad()+"");
        System.out.println("Datos luchador "+j2.getCodigo()+" a = "+j2.getAtaque()+" "
                + "d = "+j2.getDefensa()+" v = "+j2.getVelocidad()+"");
        System.out.println("Datos luchador "+j3.getCodigo()+" a = "+j3.getAtaque()+" "
                + "d = "+j3.getDefensa()+" v = "+j3.getVelocidad()+"");
        System.out.println("Datos luchador "+j4.getCodigo()+" a = "+j4.getAtaque()+" "
                + "d = "+j4.getDefensa()+" v = "+j4.getVelocidad()+"");
        System.out.println("Datos luchador "+j5.getCodigo()+" a = "+j5.getAtaque()+" "
                + "d = "+j5.getDefensa()+" v = "+j5.getVelocidad()+"");
        System.out.println("Datos luchador "+j2.getCodigo()+" a = "+j2.getAtaque()+" "
                + "d = "+j6.getDefensa()+" v = "+j6.getVelocidad()+"");
        System.out.println("Datos luchador "+j7.getCodigo()+" a = "+j7.getAtaque()+" "
                + "d = "+j7.getDefensa()+" v = "+j7.getVelocidad()+"");
        System.out.println("Datos luchador "+j8.getCodigo()+" a = "+j8.getAtaque()+" "
                + "d = "+j8.getDefensa()+" v = "+j8.getVelocidad()+"");
        System.out.println("////////////////////////////////////////////////"); 
        
        // Se crea un arraylist para pasarle los luchadores y poder ordenarlos por velocidad
        // También con este método, se fijan los luchadores para que en caso de que 
        // un luchador modifique la posición de sus luchadores cuando se va a acceder 
        // a la acción, no haya bugs 
        ArrayList <Luchador> b;
        b = new ArrayList();
        
        b.add(j1);
        b.add(j2);
        b.add(j3);
        b.add(j4);
        b.add(j5);
        b.add(j6);
        b.add(j7);
        b.add(j8);
        
        b.sort(Comparator.comparing(Luchador::getVelocidad).reversed());
        int contador = 1;
        for (int i = 0; i < b.size(); i++) {
            
        System.out.println("Posición "+contador+": "+b.get(i).getCodigo());
        contador++;
        
        }
        
        // Una vez ordenados, el más rápido atacará primero y así sucesivamente.
        // En caso de que la salud de uno de los jugadores baje a 0, hace un break
        // para salir del bucle y dar paso al fin del juego
        
        atacar(b.get(0));
        if (ganador==true) {break;} repaint();
        atacar(b.get(1));
        if (ganador==true) {break;} repaint();
        atacar(b.get(2));
        if (ganador==true) {break;} repaint();
        atacar(b.get(3));
        if (ganador==true) {break;} repaint();
        atacar(b.get(4));
        if (ganador==true) {break;} repaint();
        atacar(b.get(5));
        if (ganador==true) {break;} repaint();
        atacar(b.get(6));
        if (ganador==true) {break;} repaint();
        atacar(b.get(7));  
        if (ganador==true) {break;} repaint();
        
        // Si la salud de alguno o los 2 luchadores es inferior al 50%, se cambia
        // el color de su caja y se avisa por el panel de información
        if (5000 > Integer.parseInt(salud1.getText())) {
        salud1.setBackground(Color.orange); 
        informacion.append("\n"+jugador1+" tiene menos del 50% de salud");
        }
        if (5000 > Integer.parseInt(salud2.getText())) {
        salud2.setBackground(Color.orange); 
        informacion.append("\n"+jugador2+" tiene menos del 50% de salud");
        }
        
        // Dependiendo de tus luchadores y los del rival, se activará una mejora
        // y una desventaja para cada de los luchadores
        campos();
        
        // Se envía la salud de los luchadores
        for (Socket jugador : jugadores) {
                try { 
                    sal = jugador.getOutputStream();
                    salServidor = new PrintWriter(sal, true);  
                    salServidor.println("Salud-"+jugador1+"-"+salud1.getText()+"");
                    salServidor.println("Salud-"+jugador2+"-"+salud2.getText()+"");
                } catch (IOException ex) {
                    Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                }          }
        
        repaint();
        
        }
            // Si estamos aquí es que hay ganador, se ha salido del bucle y se procede
            // a enviar la salud y nombre del ganador o perdedor a los jugadores, cerrando
            // la comunicación al terminar
            for (Socket jugador : jugadores) {
                try { 
                    sal = jugador.getOutputStream();
                    salServidor = new PrintWriter(sal, true);  
                    salServidor.println("Salud-"+jugador1+"-"+salud1.getText()+"");
                    salServidor.println("Salud-"+jugador2+"-"+salud2.getText()+"");
                } catch (IOException ex) {
                    Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                }          }
            
            for (Socket jugador : jugadores) {
                try { 
                    sal = jugador.getOutputStream();
                    salServidor = new PrintWriter(sal, true);  
                    salServidor.println("Ganador-"+winner+"-Perdedor-"+loser+"");
                } catch (IOException ex) {
                    Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                }          }
            repaint();
        }
    }
    
    // Clase de luchador, necesario para poder cargar los datos de los mismos en los 8
    // necesarios para el combate
    public class Luchador{
    
    private String codigo;
    private int tipo, ataque, defensa, velocidad,
    posf1, posf2, 
    bonusA, bonusT, bonusD, bonusE, bonusV, bonusS;

        public Luchador(String codigo, int tipo, 
        int ataque, int defensa, int velocidad, 
        int posf1, int posf2, 
        int bonusA, int bonusT, int bonusD, int bonusE, int bonusV, int bonusS)
        {
            
            this.codigo = codigo;
            this.tipo = tipo;
            this.ataque = ataque;
            this.defensa = defensa;
            this.velocidad = velocidad;
            this.posf1 = posf1;
            this.posf2 = posf2;
            this.bonusA = bonusA;
            this.bonusT = bonusT;
            this.bonusD = bonusD;
            this.bonusE = bonusE;
            this.bonusV = bonusV;
            this.bonusS = bonusS;
            
        }
    
        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        public int getAtaque() {
            return ataque;
        }

        public void setAtaque(int ataque) {
            this.ataque = ataque;
        }

        public int getDefensa() {
            return defensa;
        }

        public void setDefensa(int defensa) {
            this.defensa = defensa;
        }

        public int getVelocidad() {
            return velocidad;
        }

        public void setVelocidad(int velocidad) {
            this.velocidad = velocidad;
        }

        public int getPosf1() {
            return posf1;
        }

        public void setPosf1(int posf1) {
            this.posf1 = posf1;
        }

        public int getPosf2() {
            return posf2;
        }

        public void setPosf2(int posf2) {
            this.posf2 = posf2;
        }

        public int getBonusA() {
            return bonusA;
        }

        public void setBonusA(int bonusA) {
            this.bonusA = bonusA;
        }

        public int getBonusT() {
            return bonusT;
        }

        public void setBonusT(int bonusT) {
            this.bonusT = bonusT;
        }

        public int getBonusD() {
            return bonusD;
        }

        public void setBonusD(int bonusD) {
            this.bonusD = bonusD;
        }

        public int getBonusE() {
            return bonusE;
        }

        public void setBonusE(int bonusE) {
            this.bonusE = bonusE;
        }

        public int getBonusV() {
            return bonusV;
        }

        public void setBonusV(int bonusV) {
            this.bonusV = bonusV;
        }

        public int getBonusS() {
            return bonusS;
        }

        public void setBonusS(int bonusS) {
            this.bonusS = bonusS;
        }  
       
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activar;
    private javax.swing.JLabel bonus1;
    private javax.swing.JLabel bonus2;
    private javax.swing.JTextField bsel1;
    private javax.swing.JTextField bsel2;
    private javax.swing.JTextArea chat;
    private javax.swing.JButton empezar;
    private javax.swing.JTextArea informacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombre1;
    private javax.swing.JTextField nombre2;
    private javax.swing.JPanel pan1;
    private javax.swing.JPanel pan2;
    private javax.swing.JTextField pos1;
    private javax.swing.JTextField pos11;
    private javax.swing.JTextField pos12;
    private javax.swing.JTextField pos13;
    private javax.swing.JTextField pos14;
    private javax.swing.JTextField pos15;
    private javax.swing.JTextField pos16;
    private javax.swing.JTextField pos2;
    javax.swing.JTextField pos3;
    private javax.swing.JTextField pos4;
    private javax.swing.JTextField pos5;
    private javax.swing.JTextField pos6;
    private javax.swing.JTextField salud1;
    private javax.swing.JTextField salud2;
    private javax.swing.JProgressBar tiempo;
    // End of variables declaration//GEN-END:variables
}
