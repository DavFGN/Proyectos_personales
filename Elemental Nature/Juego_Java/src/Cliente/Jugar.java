package Cliente;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Jugar extends javax.swing.JFrame {
    
    ImageIcon  limpiar, imagen1, imagen2, imagen3, imagen4, imagen5, imagen6,     
                imagen7, imagen8, imagen9, imagen10, imagen11, imagen12;
    
    public static String nombre, p1, p2, p3, p4, p5, p6, p7;
    
    String nom, ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7;
    int tipo = 0, ba = 0, bt = 0, bd = 0, be = 0, bv = 0, bs = 0;
    
    Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
    
    ArrayList <String> posiciones;
    int puerto=46000;
    String servidor="localhost";
    Socket socket;
    Boolean fin = false; 
    OutputStream sal;
    PrintWriter salServidor;
   
    public Jugar() {
        
         try {
             
             // Icono de la aplicación
            setIconImage(ImageIO.read(new File("src/Recursos/logoVicsio.png")));
          } 
                catch (IOException e){
                  e.printStackTrace();
                }
             BufferedImage img = null;
          try {
              img = ImageIO.read(new File("src/Recursos/fondoJ.png"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          Image dimg = img.getScaledInstance(1025, 561, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          setContentPane(new JLabel(imageIcon));
          
        initComponents();
        
        // TIENE LO MISMO QUE SELECCIÓN PERO LIMITADO A LOS 4 LUCHADORES SELECCIONADOS
        // EN CASO DE QUE SE HAYAN MODIFICADO MUCHO, SE EXPLICARÁN DE NUEVO
        // TENDRÁN COMENTARIOS TODO LO QUE NO ESTUVIERA EN SELECCIÓN
        comprobar_bonus();
        pas_static_to_n();
        llenar_cajas();
        luchador_caja();
        
        pos1.setTransferHandler(new TransferHandler("text"));       
        pos2.setTransferHandler(new TransferHandler("text"));
        pos3.setTransferHandler(new TransferHandler("text"));
        pos4.setTransferHandler(new TransferHandler("text"));
        pos5.setTransferHandler(new TransferHandler("text"));
        pos6.setTransferHandler(new TransferHandler("text"));
        bsel.setTransferHandler(new TransferHandler("text")); 
                     
    }
    
    // Hilo de escucha y salida. Se encarga de enviar los datos de los luchadores,
    // de recibir información del servidor, tanto de la salud del jugador como del 
    // chat con el otro jugador.
    // Cierra la comunicación cuando el combate llega a su fin
    
    public class EscuchaC extends Thread {
        BufferedReader entradas;
        
        public EscuchaC (Socket sock){
            socket = sock;      
                          
        try {
            entradas = new BufferedReader(new InputStreamReader(socket.getInputStream()));       
            String empezar = entradas.readLine();
            System.out.println(empezar);
            if (empezar.equals("repetido")) {
                JOptionPane.showMessageDialog(null, "No puedes jugar contra ti mismo");
                conectar.setEnabled(true);
                validar.setEnabled(true);
                enviar.setEnabled(false);
                calcular.setEnabled(false);
                limpiar1.setEnabled(false);
                pos1.setEnabled(false);
                pos2.setEnabled(false);
                pos3.setEnabled(false);
                pos4.setEnabled(false);
                pos5.setEnabled(false);
                pos6.setEnabled(false);
                l1.setEnabled(false);
                l2.setEnabled(false);
                l3.setEnabled(false);
                l4.setEnabled(false);
                repaint();              
                } else {
            salServidor = new PrintWriter(sal, true);  
            String mensaje = ("opcion-"+nom+"-"
                              + ""+posiciones.get(0)+"-"
                              + ""+posiciones.get(1)+"-"
                              + ""+posiciones.get(2)+"-"
                              + ""+posiciones.get(3)+"-"
                              + ""+posiciones.get(4)+"-"
                              + ""+posiciones.get(5)+"-"     
                              + ""+posiciones.get(6));
            salServidor.println(mensaje);
            validar.setEnabled(true);
            enviar.setEnabled(true);
            calcular.setEnabled(true);
            limpiar1.setEnabled(true);
            pos1.setEnabled(true);
            pos2.setEnabled(true);
            pos3.setEnabled(true);
            pos4.setEnabled(true);
            pos5.setEnabled(true);
            pos6.setEnabled(true);
            l1.setEnabled(true);
            l2.setEnabled(true);
            l3.setEnabled(true);
            l4.setEnabled(true);
            // Arrancamos el run
             start();
            }
            repaint();
            } catch (IOException ex) {
            Logger.getLogger(EscuchaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        @Override
        public void run(){
             try {              
            
              String menRec = entradas.readLine();
             
            while(fin==false)
            {
                if (menRec.equals("repetido")) {
                JOptionPane.showMessageDialog(null, "No puedes jugar contra ti mismo");
                conectar.setEnabled(true);
                validar.setEnabled(true);
                enviar.setEnabled(false);
                calcular.setEnabled(false);
                limpiar1.setEnabled(false);
                pos1.setEnabled(false);
                pos2.setEnabled(false);
                pos3.setEnabled(false);
                pos4.setEnabled(false);
                pos5.setEnabled(false);
                pos6.setEnabled(false);
                l1.setEnabled(false);
                l2.setEnabled(false);
                l3.setEnabled(false);
                l4.setEnabled(false);
                repaint();
                break;
                }
                
                 if (menRec.startsWith("chat")) {
                     String[] parts = menRec.split("-");
                     String part2 = parts[1];
                     chat.append("\n"+part2);
                    
                 }
                 
                 if (menRec.startsWith("Salud")) {
                    String[] parts = menRec.split("-");
                     String ntemp = parts[1];
                     if (ntemp.equals(nom)) {                    
                       health.setText(parts[2]);
                       
                       int salt = Integer.parseInt(health.getText());
                       
                         if (salt < 5000) {
                     chat.append("\nSISTEMA: tu salud ha bajado más de la mitad");   
                         }
                       
                     if (health.getText().equals("0")) {
                     chat.append("\nSISTEMA: tu salud ha llegado a 0");
                         }
                     }
                }
                 
                 if (menRec.startsWith("Ganador")) {
                     String[] parts = menRec.split("-"); 
                     System.out.println(Arrays.toString(parts));
                     if (parts[1].equals(nom)) {                        
                      chat.append("\n"+nom+" ha ganado y "+parts[3]+" ha perdido"); 
                      chat.append("\n¡Buena victoria!"); 
                     }    
                     else {
                       chat.append("\n"+parts[1]+" ha ganado y "+nom+" ha perdido"); 
                      chat.append("\n¡Más suerte la próxima vez!");   
                     }
                     
                     break;
                 }
                  
                    if (fin==true) {
                  break;  
                }
                    repaint();
                   menRec = entradas.readLine(); 
            }
                            
             }catch (ConnectException c){
               JOptionPane.showMessageDialog(null, "No hay un servidor escuchando "
                       + "por el puerto "+puerto);   
            }
             catch (SocketException e){
               JOptionPane.showMessageDialog(null, "El servidor ha cerrado "
                       + "la comunicación"); 
             }catch (IOException ex) {
              //  Logger.getLogger(EscuchaC.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        
    }

    public void pas_static_to_n () {
        
        nom = getNombre();
        ps1 = getP1();
        ps2 = getP2();
        ps3 = getP3();
        ps4 = getP4();
        ps5 = getP5();
        ps6 = getP6();
        ps7 = getP7();
        
    }
    
    // Rellena las cajas con los luchadores recibidos de la pantalla anterior y 
    // también los incluye en las cajas inferiores
    public void llenar_cajas () {
        
        nombreT.setText(nom);
        pos1.setText(ps1);
        pos2.setText(ps2);
        pos3.setText(ps3);
        pos4.setText(ps4);
        pos5.setText(ps5);
        pos6.setText(ps6);
        bsel.setText(ps7);
        
        posiciones = new ArrayList();
        
        posiciones.add(ps1);
        posiciones.add(ps2);
        posiciones.add(ps3);
        posiciones.add(ps4);
        posiciones.add(ps5);
        posiciones.add(ps6);
        posiciones.add(ps7);
       
        for (String pos : posiciones) {          
            if (pos.startsWith("L")) {                           
            if (l1.getText().equals("")) {
                l1.setText(pos);
            } else if (l2.getText().equals("")) {
                l2.setText(pos);
            }
            else if (l3.getText().equals("")) {
                l3.setText(pos);
            }
            else if (l4.getText().equals("")) {
                l4.setText(pos);
            }
        }
        }
    }
    
    // Pinta las cajas fijas de los luchadores con sus colores característicos y 
    // los datos del luchador al pasar por encima. 
    // Pasa las variables al método, que se encarga de ello
    public void luchador_caja(){
        
        colorear_luchadores(l1);
        colorear_luchadores(l2);
        colorear_luchadores(l3);
        colorear_luchadores(l4);
        
    }
    
    public void colorear_luchadores (JTextField j){

         switch (j.getText()) {
           case "L1":
               
              j.setBackground(new Color (153 ,255 ,153));
              j.setToolTipText(viento1);
               break;
               
           case "L2":
               
               j.setBackground(new Color (51,102,0));
               j.setToolTipText(viento2);
               break;
               
           case "L3":
               
               j.setBackground(new Color (255,102,102));
               j.setToolTipText(fuego1);
               break;  
               
           case "L4":
               
               j.setBackground(new Color (153,0,0));
               j.setToolTipText(fuego2);
               break;
               
           case "L5":
               
               j.setBackground(new Color (153,255,255));
               j.setToolTipText(agua1);
               break;
               
           case "L6":
               
               j.setBackground(new Color (0,51,204));
               j.setToolTipText(agua2);
               break; 
               
           case "L7":
               
               j.setBackground(new Color (255,153,102));
               j.setToolTipText(tierra1);
               break;
               
           case "L8":
               
               j.setBackground(new Color (153,102,0));
               j.setToolTipText(tierra2);
               break;
               
           case "L9":
               
               j.setBackground(new Color (255,255,204));
               j.setToolTipText(luz1);
               break;
               
           case "L10":
               
               j.setBackground(new Color (255,255,102));
               j.setToolTipText(luz2);
               break;
               
           case "L11":
               
               j.setBackground(new Color (153,153,153));
               j.setToolTipText(oscuridad1);
               break;
               
           case "L12":
               
               j.setBackground(new Color (0,0,0));
               j.setToolTipText(oscuridad2);
               break; 
               
           default:              
       }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreT = new javax.swing.JTextField();
        jugadorN = new javax.swing.JLabel();
        conectar = new javax.swing.JButton();
        tiempo = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        enviar = new javax.swing.JButton();
        chatT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        pos2 = new javax.swing.JTextField();
        pos1 = new javax.swing.JTextField();
        pos3 = new javax.swing.JTextField();
        pos4 = new javax.swing.JTextField();
        pos5 = new javax.swing.JTextField();
        pos6 = new javax.swing.JTextField();
        bonus = new javax.swing.JLabel();
        validar = new javax.swing.JButton();
        limpiar1 = new javax.swing.JButton();
        bsel = new javax.swing.JTextField();
        pl1 = new javax.swing.JTextField();
        pl2 = new javax.swing.JTextField();
        re2 = new javax.swing.JTextField();
        pl3 = new javax.swing.JTextField();
        pl4 = new javax.swing.JTextField();
        re1 = new javax.swing.JTextField();
        pl5 = new javax.swing.JTextField();
        pl6 = new javax.swing.JTextField();
        re3 = new javax.swing.JTextField();
        calcular = new javax.swing.JButton();
        health = new javax.swing.JTextField();
        l4 = new javax.swing.JTextField();
        l1 = new javax.swing.JTextField();
        l2 = new javax.swing.JTextField();
        l3 = new javax.swing.JTextField();
        ayuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jugar");
        setSize(new java.awt.Dimension(1025, 561));

        nombreT.setEditable(false);
        nombreT.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nombreT.setEnabled(false);

        jugadorN.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jugadorN.setText("Jugador: ");

        conectar.setText("CONECTAR");
        conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarActionPerformed(evt);
            }
        });

        tiempo.setMaximum(10);

        chat.setColumns(20);
        chat.setRows(5);
        jScrollPane1.setViewportView(chat);

        enviar.setText("Enviar mensaje");
        enviar.setEnabled(false);
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        chatT.setToolTipText("");

        jPanel2.setOpaque(false);

        pos2.setEditable(false);
        pos2.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos2.setText("2");
        pos2.setEnabled(false);
        pos2.setName("2"); // NOI18N

        pos1.setEditable(false);
        pos1.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos1.setText("1");
        pos1.setEnabled(false);
        pos1.setName("1"); // NOI18N

        pos3.setEditable(false);
        pos3.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos3.setText("3");
        pos3.setEnabled(false);
        pos3.setName("3"); // NOI18N

        pos4.setEditable(false);
        pos4.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos4.setText("4");
        pos4.setEnabled(false);
        pos4.setName("4"); // NOI18N

        pos5.setEditable(false);
        pos5.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos5.setText("5");
        pos5.setEnabled(false);
        pos5.setName("5"); // NOI18N

        pos6.setEditable(false);
        pos6.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos6.setText("6");
        pos6.setEnabled(false);
        pos6.setName("6"); // NOI18N

        bonus.setBackground(new java.awt.Color(255, 255, 255));
        bonus.setFont(new java.awt.Font("Impact", 1, 24)); // NOI18N
        bonus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bonusMousePressed(evt);
            }
        });

        validar.setText("VALIDAR");
        validar.setEnabled(false);
        validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarActionPerformed(evt);
            }
        });

        limpiar1.setText("LIMPIAR");
        limpiar1.setEnabled(false);
        limpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar1ActionPerformed(evt);
            }
        });

        bsel.setEditable(false);
        bsel.setBackground(new java.awt.Color(0, 0, 0));
        bsel.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        bsel.setForeground(new java.awt.Color(255, 255, 255));
        bsel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bsel.setText("Esperando bonus");

        pl1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pl1.setText("Combinación 1");

        pl2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pl2.setText("Combinación 2");

        re2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        re2.setText("Sinergia 2");

        pl3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pl3.setText("Combinación 3");

        pl4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pl4.setText("Combinación 4");

        re1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        re1.setText("Sinergia 1");

        pl5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pl5.setText("Combinación 5");

        pl6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pl6.setText("Combinación 6");

        re3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        re3.setText("Sinergia 3");

        calcular.setText("CALCULAR");
        calcular.setEnabled(false);
        calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularActionPerformed(evt);
            }
        });

        health.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        health.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        health.setText("10000");
        health.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(health, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pos1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(pos2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                            .addComponent(re1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pl2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pos3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(pos4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pl3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pl4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(re2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pl5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pl6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(re3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(validar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(limpiar1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bonus, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(bsel))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bonus, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bsel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(pl3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pl4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(re2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(pl5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(pl6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(re3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pl1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pl2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(re1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(health, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validar)
                    .addComponent(calcular)
                    .addComponent(limpiar1))
                .addGap(18, 18, 18))
        );

        l4.setEditable(false);
        l4.setBackground(new java.awt.Color(255, 255, 255));
        l4.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l4.setForeground(new java.awt.Color(0, 0, 0));
        l4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l4.setDragEnabled(true);
        l4.setEnabled(false);
        l4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l4FocusGained(evt);
            }
        });
        l4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l4MousePressed(evt);
            }
        });

        l1.setEditable(false);
        l1.setBackground(new java.awt.Color(255, 255, 255));
        l1.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l1.setForeground(new java.awt.Color(0, 0, 0));
        l1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l1.setToolTipText("");
        l1.setDragEnabled(true);
        l1.setEnabled(false);
        l1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l1FocusGained(evt);
            }
        });
        l1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l1MousePressed(evt);
            }
        });

        l2.setEditable(false);
        l2.setBackground(new java.awt.Color(255, 255, 255));
        l2.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l2.setForeground(new java.awt.Color(0, 0, 0));
        l2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l2.setDragEnabled(true);
        l2.setEnabled(false);
        l2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l2FocusGained(evt);
            }
        });
        l2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l2MousePressed(evt);
            }
        });

        l3.setEditable(false);
        l3.setBackground(new java.awt.Color(255, 255, 255));
        l3.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l3.setForeground(new java.awt.Color(0, 0, 0));
        l3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l3.setDragEnabled(true);
        l3.setEnabled(false);
        l3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l3FocusGained(evt);
            }
        });
        l3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l3MousePressed(evt);
            }
        });

        ayuda.setText("AYUDA");
        ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jugadorN, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addComponent(conectar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chatT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(tiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(enviar)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jugadorN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(conectar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(enviar)
                                .addComponent(ayuda)))
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chatT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bonusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bonusMousePressed
        bonus_img("L1", imagen1);
        bonus_img("L2", imagen2);
        bonus_img("L3", imagen3);
        bonus_img("L4", imagen4);
        bonus_img("L5", imagen5);
        bonus_img("L6", imagen6);
        bonus_img("L7", imagen7);
        bonus_img("L8", imagen8);
        bonus_img("L9", imagen9);
        bonus_img("L10", imagen10);
        bonus_img("L11", imagen11);
        bonus_img("L12", imagen12);

    }//GEN-LAST:event_bonusMousePressed

    void bonus_img (String b, ImageIcon img) {
        if (bsel.getText().equals(b)) {
            mostrarImagen(img);
        }
    }
    
    void mostrarImagen (ImageIcon img){
       JDialog dialog = new JDialog();     
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setTitle("Detalles de luchador");
                    try {
                        dialog.setIconImage(ImageIO.read(new File("src/Recursos/logoVicsio.png")));
                    } catch (IOException ex) {
                        Logger.getLogger(Galeria.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dialog.setResizable(false);
                    dialog.add(new JLabel(new ImageIcon(img.getImage().getScaledInstance
                    (500, 500, java.awt.Image.SCALE_DEFAULT))));
                    dialog.setBackground(Color.yellow);

                    dialog.pack();
                    dialog.setLocationByPlatform(true);
                    dialog.setVisible(true);
   }
    
    // Método modificado de selección. Explicaciones dentro del método referenciado
    private void validarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarActionPerformed
        comprobar_pos();
    }//GEN-LAST:event_validarActionPerformed
    
    void comprobar_pos () {
         
        
        // Pasa los datos de las cajas a mayúsculas, por si hay una l en minúscula
        pos1.setText(pos1.getText().toUpperCase());
        pos2.setText(pos2.getText().toUpperCase());
        pos3.setText(pos3.getText().toUpperCase());
        pos4.setText(pos4.getText().toUpperCase());
        pos5.setText(pos5.getText().toUpperCase());
        pos6.setText(pos6.getText().toUpperCase());
            
        // Limpia el array en caso de tener datos de un envío anterior
        posiciones.clear();
        posiciones = new ArrayList();
        
        // Carga las posiciones en el array
        posiciones.add(pos1.getText());
        posiciones.add(pos2.getText());
        posiciones.add(pos3.getText());
        posiciones.add(pos4.getText());
        posiciones.add(pos5.getText());
        posiciones.add(pos6.getText());
        posiciones.add(bsel.getText());
        
        String uno = l1.getText(), dos = l2.getText(), tres = l3.getText(), 
        cuatro = l4.getText();
     
        int contador = 0, cnt = 0, sumar = 0, raro = 0;
        
        // For que comprueba si hay luchadores repetidos o uno el cual no fue elegido
        // en selección. Si hay un solo fallo, no dejará enviar
        for (String pos : posiciones) {
            if (pos.startsWith("L")) {
                switch (pos) {
                    case "L1":
                        contador++;
                        break;
                    case "L2":
                        contador++;
                        break;
                    case "L3":
                        contador++;
                        break;  
                    case "L4":
                        contador++;
                        break;
                    case "L5":
                        contador++;
                        break;
                    case "L6":
                        contador++;
                        break; 
                    case "L7":
                        contador++;
                        break;
                    case "L8":
                        contador++;
                        break;
                    case "L9":
                        contador++;
                        break;
                    case "L10":
                        contador++;
                        break;
                    case "L11":
                        contador++;
                        break;
                    case "L12":
                        contador++;
                        break; 
                    default:
                      raro++;  
                }              
            }
            for (String posicion : posiciones) {
                if (pos.equals(posicion)) {
                    cnt++;
                }
            }
            
            if (uno.equals(pos)) {
                sumar++;
            }
            if (dos.equals(pos)) {
                sumar++;
            }
            if (tres.equals(pos)) {
                sumar++;
            }
            if (cuatro.equals(pos)) {
                sumar++;
            }

        }
        if (sumar <= 3 || sumar >= 5) {
            JOptionPane.showMessageDialog(null, "Debe introducir solo los 4 "
                    + "luchadores que eligió");  
        } else if ( raro > 0) {            
         JOptionPane.showMessageDialog(null, "No debe haber una posición con 'L' "
                 + "seguida de letras, un 0 o superior a 12");  
        } else if (contador < 4 || contador > 4) {
         JOptionPane.showMessageDialog(null, "Debe poner 3 luchadores y un bonus");          
        } else if (cnt >= 9) {
          JOptionPane.showMessageDialog(null, "No debe haber luchadores repetidos");  
        } else if (!bsel.getText().startsWith("L")) {
          JOptionPane.showMessageDialog(null, "Debe incluir un bonus");  
        } else {
            
           validar.setEnabled(false); 
           
           // En caso de estar todo bien, envía al servidor las posiciones nuevas
           // y el reloj de arena pasa a activarse, evitando el flood de envíos, limitando
           // a cada 10 segundos la posibilidad de hacerlo
           
              Runnable runn = () -> {
                  try {
                      // Se preparan las salidas
                      sal = socket.getOutputStream();
                      salServidor = new PrintWriter(sal, true);
                      // Se crea una cadena con el nombre y la opcion de ataque
                      // porque este es el botón de atacar
                      String mensaje = ("opcion-"+nom+"-"
                              + ""+posiciones.get(0)+"-"
                              + ""+posiciones.get(1)+"-"
                              + ""+posiciones.get(2)+"-"
                              + ""+posiciones.get(3)+"-"
                              + ""+posiciones.get(4)+"-"
                              + ""+posiciones.get(5)+"-"    
                              + ""+posiciones.get(6));
                      
                      salServidor.println(mensaje);
                      System.out.println(mensaje);
                      
                      // Se hace un for de 10 segundos para ir actualizando cada
                      // segundo la barra de espera
                      for (int i = 0; i < 10; i++) {
                          try {
                              validar.setEnabled(false);                             
                              Thread.sleep(1000);
                              tiempo.setValue(i+1);
                              repaint();
                          } catch (InterruptedException ex) {
                              Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                          }
                      }
                      // Al finalizar la barra se vacía y se activan los botones
                      // de nuevo para realizar la acción
                      tiempo.setValue(0);
                      validar.setEnabled(true);
                      repaint();
                  } catch (IOException ex) {
                      Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                  }
              };
        // Se arranca el hilo
        Thread hilo = new Thread (runn);
      hilo.start();
    
        }
    }
    
    private void limpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar1ActionPerformed
        pos1.setText("1");
        pos2.setText("2");
        pos3.setText("3");
        pos4.setText("4");
        pos5.setText("5");
        pos6.setText("6");
        bsel.setText("");

        limp_comb(pl1, 1);
        limp_comb(pl2, 2);
        limp_comb(pl3, 3);
        limp_comb(pl4, 4);
        limp_comb(pl5, 5);
        limp_comb(pl6, 6);

        re1.setText("Sinergia 1");
        re2.setText("Sinergia 2");
        re3.setText("Sinergia 3");

        Border border = BorderFactory.createLineBorder(Color.yellow, 0);
        bsel.setBorder(border);
        bsel.setBackground(Color.black);
        bsel.setForeground(Color.white);
        bonus.setBorder(border);

        limpiar = new ImageIcon("src/Recursos/bonus_l.jpg");
        met_rec(limpiar, bonus);

        limp_sin(re1);
        limp_sin(re2);
        limp_sin(re3);

    }//GEN-LAST:event_limpiar1ActionPerformed
     
    void limp_sin (JTextField ter){
         Border border = BorderFactory.createLineBorder(Color.gray, 0);
                        ter.setBorder(border);
                        ter.setBackground(Color.white);
                        ter.setForeground(Color.black);
                        ter.setText("Sinergia");
     }
     
    void met_rec (ImageIcon img, JLabel lab ){    
        ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance
        (lab.getWidth(), lab.getHeight(), java.awt.Image.SCALE_DEFAULT));
         lab.setIcon(icono);
    }
    
    void limp_comb (JTextField p, int t){
         p.setText("Combinación "+t);
         p.setBackground(Color.white);
         p.setForeground(Color.black);
     }
    
    private void calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularActionPerformed
        calc_bonus (bsel);
        calc_pos (pos1, pl1);
        calc_pos (pos2, pl2);
        calc_pos (pos3, pl3);
        calc_pos (pos4, pl4);
        calc_pos (pos5, pl5);
        calc_pos (pos6, pl6);
        calc_sin (pos1, pos2, re1);
        calc_sin (pos3, pos4, re2);
        calc_sin (pos5, pos6, re3);
    }//GEN-LAST:event_calcularActionPerformed

    void calc_sin (JTextField pri, JTextField seg, JTextField ter) {
        String primer = pri.getText();
        String segun = seg.getText();
        
        int p = 0, s = 0;
                
        try {
         Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String selb = "select tipo from Luchador where codigo like ? or codigo like ?";
            PreparedStatement preb;       
            preb = conn.prepareStatement(selb);
            preb.setString(1, primer);
            preb.setString(2, segun);
            ResultSet re = preb.executeQuery();
            
            int contador = 0;
            while(re.next()){
                if (contador == 0) {
                    p = re.getInt("tipo"); 
                    contador++;
                } else {
                    s = re.getInt("tipo"); 
                }                             
            }
            
            boolean comp = false;
            
            if ((p >= 1 && p <= 6) && (s >= 1 && s <= 6)) {
                if ((p == 1 && s == 2) || (s == 1 && p == 2)) {
                    ter.setText("Sinergia");
                    comp = true;
                } else if ((p == 3 && s == 4) || (s == 3 && p == 4)) {
                    ter.setText("Sinergia");
                    comp = true;
                } else if (p == 5 || s == 5 || p == 6  || s == 6 ){
                    ter.setText("Sinergia");
                    comp = true;
                }
                Border border = BorderFactory.createLineBorder(Color.yellow, 2);
             ter.setBorder(border);
             ter.setBackground(Color.yellow);
             ter.setForeground(Color.black);   
             
                if (comp == false) {
                    if ((p == 1 && s == 4) || (s == 1 && p == 4)) {
                    ter.setText("Mala sinergia");
                    comp = true;
                } else if ((p == 2 && s == 3) || (s == 2 && p == 3)) {
                    ter.setText("Mala sinergia");
                    comp = true;
                }
                    
             border = BorderFactory.createLineBorder(Color.gray, 2);
             ter.setBorder(border);
             ter.setBackground(Color.gray);
             ter.setForeground(Color.white);      
                    
                    if (comp == false) {
                        border = BorderFactory.createLineBorder(Color.gray, 0);
                        ter.setBorder(border);
                        ter.setBackground(Color.white);
                        ter.setForeground(Color.black);
                        ter.setText("Sin sinergia");
                    }
                }
                    
            } else {
            Border border = BorderFactory.createLineBorder(Color.yellow, 0);
             ter.setBorder(border);
             ter.setBackground(Color.white);
             ter.setForeground(Color.black);
             ter.setText("Sin sinergia");
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void calc_bonus (JTextField bonu) {
        String b = bonu.getText();
    
        try {
         Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String selb = "select * from Luchador where codigo like ?";
            PreparedStatement preb;       
            preb = conn.prepareStatement(selb);
            preb.setString(1, b);
            ResultSet re = preb.executeQuery();
            
            while(re.next()){
             tipo = re.getInt("tipo");
             ba = re.getInt("bonusa"); 
             bt = re.getInt("bonust");
             bd = re.getInt("bonusd"); 
             be = re.getInt("bonuse");
             bv = re.getInt("bonusv"); 
             bs = re.getInt("bonuss");
             
             Border border = BorderFactory.createLineBorder(Color.yellow, 3);
             bsel.setBorder(border);
             bsel.setBackground(Color.white);
             bsel.setForeground(Color.red);
             bonus.setBorder(border);            
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
  
    void calc_pos (JTextField pos, JTextField cal){
        String tmp = pos.getText();
        int tip = 0;
        int ataque = 0, defensa = 0, velocidad = 0, p1 = 0, p2 = 0;
        int sumA = 0, velA = 0, defA = 0; 
        int sumAA = 0, velAA = 0, defAA = 0;
        int sumAAA = 0, velAAA = 0, defAAA = 0;
        
        cal.setBackground(Color.white);
                    cal.setForeground(Color.black);
                    cal.setText("----");
          try {
                Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String comp = "select * from Luchador where codigo like ?";
            PreparedStatement cm = conn.prepareStatement(comp);
            cm.setString(1, tmp);
            ResultSet rs = cm.executeQuery();
            while (rs.next()) {
            tip = rs.getInt("tipo"); 
            ataque = rs.getInt("ataque");
            defensa = rs.getInt("defensa");
            velocidad = rs.getInt("velocidad");
            p1 = rs.getInt("posf1");
            p2 = rs.getInt("posf2");
            
            String nombr =  pos.getName();
               int temporal = Integer.parseInt(nombr);
                if (p1==temporal || p2==temporal) {
                    cal.setBackground(Color.yellow);
                    cal.setText("Bien");
                    sumA = ataque * 25 / 100;
                    defA = 5;
                    velA = velocidad * 25 / 100;
                }
                else {
                    cal.setBackground(Color.gray);
                    cal.setForeground(Color.white);
                    cal.setText("Normal");
                }
            
             }
            cm.close();
            
              sumAA = ataque * ba / 100;
              defAA = bd;
              velAA = velocidad * bv / 100;
              
              if (tip == tipo) {
              sumAAA = ataque * bt / 100;
              defAAA = be;
              velAAA = velocidad * bs / 100; 
              }

            String frase = "Ataque:"+ataque+" + bonus P:"+sumA+" + "
                    + "bonus B:"+sumAA+" + bonus A:"+sumAAA+"\n \n"
                         + "Defensa:"+defensa+"% + bonus P:"+defA+"% "
                    + "bonus B:"+defAA+"% + bonus A:"+defAAA+"%\n \n"
                         + "Velocidad:"+velocidad+" + bonus P:"+velA+" "
                    + "bonus B: "+velAA+" + bonus A:"+velAAA+"";
                      
            for( MouseListener al : cal.getMouseListeners() ) {
                cal.removeMouseListener(al);
            } 
                       
            cal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {           
            JOptionPane.showMessageDialog(null, frase, "INFO", 
            JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Recursos/info-i.png")); 
                }
          });

      }
       catch (ClassNotFoundException | SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            } 
    }

    private void l4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l4FocusGained
        fijar(evt);
    }//GEN-LAST:event_l4FocusGained

    private void l4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l4MousePressed
        arrastrar(evt);
    }//GEN-LAST:event_l4MousePressed

    private void l1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l1FocusGained
        fijar(evt);
    }//GEN-LAST:event_l1FocusGained

    private void l1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MousePressed
        arrastrar(evt);
    }//GEN-LAST:event_l1MousePressed

    private void l2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l2FocusGained
        fijar(evt);
    }//GEN-LAST:event_l2FocusGained

    private void l2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MousePressed
        arrastrar(evt);
    }//GEN-LAST:event_l2MousePressed

    private void l3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l3FocusGained
        fijar(evt);
    }//GEN-LAST:event_l3FocusGained

    private void l3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l3MousePressed
        arrastrar(evt);
    }//GEN-LAST:event_l3MousePressed

    // Botón que intenta conectar con el servidor. Si lo consigue, manda el nombre
    // y se prepara el hilo de escucha
    private void conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarActionPerformed
        conectar.setEnabled(false);
        
        Runnable runn = new Runnable() {
                
            @Override
            public void run() {
               
             try {
                 // Se prepara el socket pasándole la ip y puerto del servidor
            socket = new Socket (servidor, puerto);
            System.out.println("Conexion realizada con el servidor"); 
                // Se manda lo primero el nombre al servidor
                    sal = socket.getOutputStream();            
                    salServidor = new PrintWriter(sal, true);                                      
                    salServidor.println(nom);
            // Se le pasa el socket a la clase de escucha
                new EscuchaC(socket);
                    
                repaint();
        } catch(BindException e)           
        { String msn = ("Lo siento, no hay ningún programa escuchando por el puerto "+puerto+" "
                    + "en el servidor"+servidor);
        JOptionPane.showMessageDialog(null, msn);
           }
        catch(UnknownHostException e)
        {
            System.out.println("Lo siento, no hay ningún ordenador en la red con el nombre: "+servidor);
        }
        catch(ConnectException e)
        {
            String msn = ("Lo siento, en el ordenador "+servidor+" no hay ningún programa escuchando por el puerto "+puerto);
            JOptionPane.showMessageDialog(null, msn);
            conectar.setEnabled(true);            
        }
        catch (IOException ex) {
            System.out.println("IOException");
        }
             }
        };
       
        Thread hilo = new Thread (runn);
      hilo.start();
    }//GEN-LAST:event_conectarActionPerformed

    private void ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayudaActionPerformed
        new Ayuda().setVisible(true);
    }//GEN-LAST:event_ayudaActionPerformed
    
    // Botón que manda, si hay algo escrito, un mensaje por el chat
    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
       
        Runnable runn = () -> {
            try {
                
                sal = socket.getOutputStream();
                salServidor = new PrintWriter(sal, true);
                
                if (chatT.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe introducir algo en la"
                        + " caja de texto");
                }
                else {
                String mensaje = ("chat-"+nom+": "+chatT.getText());
                
                salServidor.println(mensaje);
                System.out.println(mensaje);
                
                chatT.setText("");
                }
            } catch (IOException ex) {
                Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
       
        Thread hilo = new Thread (runn);
      hilo.start();

    }//GEN-LAST:event_enviarActionPerformed
    
    void fijar (java.awt.event.FocusEvent evt){
        if (!(evt.getSource() instanceof JTextField)) return;
    JTextField txt = (JTextField)evt.getSource();
    txt.selectAll();
    }
    
    void arrastrar(java.awt.event.MouseEvent evento){
        JComponent jc = (JComponent)evento.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, evento, TransferHandler.COPY);
    }
    
    void comprobar_bonus(){
        
        bsel.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void changedUpdate(DocumentEvent e) {
            bonus.setText("");           
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            bonus.setText("");
        }
        @Override
        public void insertUpdate(DocumentEvent e) {
            bonus.setText("");
            pintar_bonus();
        }
        
    });
        toolDescripcion(l1);
        toolDescripcion(l2);
        toolDescripcion(l3);
        toolDescripcion(l4);
    }
    
    void pintar_bonus(){
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
         
       
         switch (bsel.getText()) {
           case "L1":
               met_rec(imagen1, bonus);
               bonus.setToolTipText(viento1);
               break;
           case "L2":
               met_rec(imagen2, bonus);
               bonus.setToolTipText(viento2);
               break;
           case "L3":
               met_rec(imagen3, bonus);
               bonus.setToolTipText(fuego1);
               break;  
           case "L4":
               met_rec(imagen4, bonus);
               bonus.setToolTipText(fuego2);
               break;
           case "L5":
               met_rec(imagen5, bonus);
               bonus.setToolTipText(agua1);
               break;
           case "L6":
               met_rec(imagen6, bonus);
               bonus.setToolTipText(agua2);
               break; 
           case "L7":
               met_rec(imagen7, bonus);
               bonus.setToolTipText(tierra1);
               break;
           case "L8":
               met_rec(imagen8, bonus);
               bonus.setToolTipText(tierra2);
               break;
           case "L9":
               met_rec(imagen9, bonus);
               bonus.setToolTipText(luz1);
               break;
           case "L10":
               met_rec(imagen10, bonus);
               bonus.setToolTipText(luz2);
               break;
           case "L11":
               met_rec(imagen11, bonus);
               bonus.setToolTipText(oscuridad1);
               break;
           case "L12":
               met_rec(imagen12, bonus);
               bonus.setToolTipText(oscuridad2);
               break; 
           default:
               bonus.setText("BONUS NO VALIDO");
       }
    }
    
    void toolDescripcion(JTextField eles){
               
        switch (eles.getText()) {
           case "L1":
               eles.setToolTipText(viento1); 
               break;
           case "L2":
               eles.setToolTipText(viento2);
               break;
           case "L3":
               eles.setToolTipText(fuego1);
               break;  
           case "L4":
               eles.setToolTipText(fuego2);
               break;
           case "L5":
               eles.setToolTipText(agua1);
               break;
           case "L6":
               eles.setToolTipText(agua2);
               break; 
           case "L7":
               eles.setToolTipText(tierra1);
               break;
           case "L8":              
               eles.setToolTipText(tierra2);   
               break;
           case "L9":
               eles.setToolTipText(luz1);
               break;
           case "L10":
               eles.setToolTipText(luz2);
               break;
           case "L11":
               eles.setToolTipText(oscuridad1);
               break;
           case "L12":
               eles.setToolTipText(oscuridad2);
               break; 
           default:
               bonus.setText("BONUS NO VALIDO");
       }       
    }
    
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
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        nombre = args[0];
        p1     = args[1];
        p2     = args[2];
        p3     = args[3];
        p4     = args[4];
        p5     = args[5];
        p6     = args[6];
        p7     = args[7];
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jugar().setVisible(true);
            }
        });
    }
    
     String viento1 = ("<html>\n" +
"    <body>\n" +
"        <div style='background:rgb(153,255,153);'>\n" +
"        Espíritu del viento con gran agilidad <br>\n" +
"        Ataque:    200 <br>\n" +
"        Defensa:   20% <br>\n" +
"        Velocidad: 600 <br>\n" +
"        Bonus: Aumenta un 25% la velocidad de los luchadores (si es tipo viento "
            + "añade un 25% también de ataque)<br>\n" +
"        Posiciones preferidas: <br>\n" +
"        O   &ensp;   X     &ensp;   X <br>\n" +
"        X   &ensp;   X     &ensp;   O   \n" +
"        </div>\n" +
"</html>");
    
    String viento2 = ("<html>\n" +
"    <body>\n" +
"        <div style='background:rgb(51,102,0); color:white'>\n" +
"        Reina de las valquirias, gran poder ofensivo y agilidad  <br>\n" +
"        Ataque:    400 <br>\n" +
"        Defensa:   10% <br>\n" +
"        Velocidad: 500 <br>\n" +
"        Bonus: Aumenta un 10% la velocidad y 15% de ataque de los \n" +
"        luchadores (15% y 10% adicional si es tipo viento)  <br>\n" +
"        Posiciones preferidas: <br>\n" +
"        X   &ensp;    O   &ensp;    X <br>\n" +
"        O  &ensp;     X   &ensp;    X   \n" +
"        </div>\n" +
"</html>");
    
    String fuego1 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(255,102,102)'>\n" +
"Considerado el principe demonio, poder equilibrado  <br>\n" +
"Ataque:    350 <br>\n" +
"Defensa:   30% <br>\n" +
"Velocidad: 350 <br>\n" +
"Bonus: Aumenta un 10% la defensa y 15% de ataque de los \n" +
"luchadores (10% de velocidad y 15% de ataque adicional si es tipo fuego)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"O  &ensp;     X   &ensp;    O <br>\n" +
"X  &ensp;     X   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    String fuego2 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(153,0,0); color:white'>\n" +
"Emperador antiguo dragón de fuego, gran poder ofensivo <br>\n" +
"Ataque:    600 <br>\n" +
"Defensa:   25% <br>\n" +
"Velocidad: 150 <br>\n" +
"Bonus: Aumenta un 25% el ataque de los \n" +
"luchadores (25% adicional si es tipo fuego)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"X  &ensp;     X   &ensp;    O <br>\n" +
"X  &ensp;     O   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    String agua1 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(153,255,255)'>\n" +
"Criatura del abismo oceánico, gran poder ofensivo y defensivo <br>\n" +
"Ataque:    500 <br>\n" +
"Defensa:   40% <br>\n" +
"Velocidad: 100 <br>\n" +
"Bonus: Aumenta un 15% la defensa y 10% el ataque de los \n" +
"luchadores (10% y 15% respectivamente adicional si es tipo agua)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"O  &ensp;     X   &ensp;    X <br>\n" +
"X  &ensp;     X   &ensp;    O  \n" +
"</div>\n" +
"</html>");
    
    String agua2 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(0,51,204); color:white'>\n" +
"Deidad del mar al que controla a su antojo, poder equilibrado <br>\n" +
"Ataque:    350 <br>\n" +
"Defensa:   35% <br>\n" +
"Velocidad: 300 <br>\n" +
"Bonus: Aumenta un 8% de ataque, 9% defensa y 8% de velocidad\n" +
"de los luchadores (25% adicional de velocidad si es tipo agua)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"O  &ensp;     X   &ensp;    X <br>\n" +
"O  &ensp;     X   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    String tierra1 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(255,153,102)'>\n" +
"Titán de tierra, poder equilibrado <br>\n" +
"Ataque:    300 <br>\n" +
"Defensa:   40% <br>\n" +
"Velocidad: 300 <br>\n" +
"Bonus: Aumenta un 10% de ataque, 10% defensa y 5% de velocidad\n" +
"de los luchadores (25% adicional de velocidad si es tipo tierra)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"X  &ensp;     X   &ensp;    X <br>\n" +
"X  &ensp;     O   &ensp;    O  \n" +
"</div>\n" +
"</html>");
    
    String tierra2 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(153,102,0); color:white'>\n" +
"Tortuga gigante que carga una montaña en sus hombros, gran poder defensivo <br>\n" +
"Ataque:    300 <br>\n" +
"Defensa:   60% <br>\n" +
"Velocidad: 100 <br>\n" +
"Bonus: Aumenta un 20% de defensa y 5% de ataque\n" +
"de los luchadores (25% adicional de ataque si es tipo tierra)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"X  &ensp;     X   &ensp;    O <br>\n" +
"O  &ensp;     X   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    String luz1 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(255,255,204)'>\n" +
"Caballero protector de una diosa, gran agilidad y ataque <br>\n" +
"Ataque:    400 <br>\n" +
"Defensa:   20% <br>\n" +
"Velocidad: 400 <br>\n" +
"Bonus: Aumenta un 15% de ataque y 10% de velocidad\n" +
"de los luchadores (10% de ataque y 15% de velocidad adicional si es tipo luz)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"X  &ensp;     O   &ensp;    O <br>\n" +
"X  &ensp;     X   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    String luz2 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(255,255,102)'>\n" +
"Dragón que protege con su luz de todo mal, poder equilibrado <br>\n" +
"Ataque:    350 <br>\n" +
"Defensa:   35% <br>\n" +
"Velocidad: 300 <br>\n" +
"Bonus: Aumenta un 15% de defensa y 10% de ataque\n" +
"de los luchadores (25% adicional de defensa si es tipo luz)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"X  &ensp;     O   &ensp;    X <br>\n" +
"X  &ensp;     O   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    String oscuridad1 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(153,153,153)'>\n" +
"Demonio encerrado durante milenios bajo una pirámide, gran poder defensivo <br>\n" +
"Ataque:    300 <br>\n" +
"Defensa:   50% <br>\n" +
"Velocidad: 200 <br>\n" +
"Bonus: Aumenta un 20% de ataque y 5% de velocidad\n" +
"de los luchadores (25% adicional de defensa si es tipo oscuridad)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"X  &ensp;     X   &ensp;    O <br>\n" +
"X  &ensp;     O   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    String oscuridad2 = ("<html>\n" +
"<body>\n" +
"<div style='background:rgb(0,0,0); color:white'>\n" +
"Dios que teme ser olvidado, gran poder ofensivo <br>\n" +
"Ataque:    700 <br>\n" +
"Defensa:   10% <br>\n" +
"Velocidad: 200 <br>\n" +
"Bonus: Aumenta un 20% de ataque y 5% de defensa\n" +
"de los luchadores (25% adicional de ataque si es tipo oscuridad)  <br>\n" +
"Posiciones preferidas: <br>\n" +
"O  &ensp;     X   &ensp;    X <br>\n" +
"X  &ensp;     O   &ensp;    X  \n" +
"</div>\n" +
"</html>");
    
    public static String getNombre() {
        return nombre;
    }

    public static String getP1() {
        return p1;
    }

    public static String getP2() {
        return p2;
    }

    public static String getP3() {
        return p3;
    }

    public static String getP4() {
        return p4;
    }

    public static String getP5() {
        return p5;
    }

    public static String getP6() {
        return p6;
    }

    public static String getP7() {
        return p7;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ayuda;
    private javax.swing.JLabel bonus;
    private javax.swing.JTextField bsel;
    private javax.swing.JButton calcular;
    private javax.swing.JTextArea chat;
    private javax.swing.JTextField chatT;
    private javax.swing.JButton conectar;
    private javax.swing.JButton enviar;
    private javax.swing.JTextField health;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel jugadorN;
    private javax.swing.JTextField l1;
    private javax.swing.JTextField l2;
    private javax.swing.JTextField l3;
    private javax.swing.JTextField l4;
    private javax.swing.JButton limpiar1;
    private javax.swing.JTextField nombreT;
    private javax.swing.JTextField pl1;
    private javax.swing.JTextField pl2;
    private javax.swing.JTextField pl3;
    private javax.swing.JTextField pl4;
    private javax.swing.JTextField pl5;
    private javax.swing.JTextField pl6;
    private javax.swing.JTextField pos1;
    private javax.swing.JTextField pos2;
    javax.swing.JTextField pos3;
    private javax.swing.JTextField pos4;
    private javax.swing.JTextField pos5;
    private javax.swing.JTextField pos6;
    private javax.swing.JTextField re1;
    private javax.swing.JTextField re2;
    private javax.swing.JTextField re3;
    private javax.swing.JProgressBar tiempo;
    private javax.swing.JButton validar;
    // End of variables declaration//GEN-END:variables
}
