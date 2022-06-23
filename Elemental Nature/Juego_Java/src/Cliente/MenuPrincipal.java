package Cliente;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuPrincipal extends javax.swing.JFrame {

    public static String name, pass;
    int codigoU = 0;
    int win = 0;
    int per = 0;
    boolean baneado;
    
    String nombre = getNombre();
    String contra = getPass();
    
    Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";       

    public MenuPrincipal() {       
         try{ 
             // Icono de la aplicación
            setIconImage(ImageIO.read(new File("src/Recursos/logoVicsio.png")));
          } 
                catch (IOException e){
                  e.printStackTrace();
                }
             BufferedImage img = null;
          try {
              img = ImageIO.read(new File("src/Recursos/costan.jpg"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          Image dimg = img.getScaledInstance(671, 498, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          setContentPane(new JLabel(imageIcon));
          
          initComponents();
          
          // Comprueba la última vez que se ingresó
          ultimaVez();
          // Carga el número de partidas ganadas y perdidas
          partidas_ganadas();
          partidas_perdidas();
          // En caso de no haber jugado aún, avisa de esto
          if (win == 0 && per == 0) {
            historial.append("No ha jugado todavía una partida");
        } 
          // En caso de haber jugado una partida, informa en el panel
          informar_panel();
          // Comprueba si el usuario está baneado y aplica las medidas correspondientes
          comprobar_baneo();
          
    }
    
     void ultimaVez(){
        
            Timestamp fecha = null;
            String cod = "";
            String sql = "select fecha_ult as fecha from registro where cod_usuario = ? "
                    + "order by fecha_ult desc limit 1";  
            
           String codigo = "select codigo, ban from Usuario where nombre like ? and pass like ?"; 
           
        try {
            Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
            PreparedStatement sr = conn.prepareStatement(codigo);
            sr.setString(1, nombre);
            sr.setString(2, contra);
            ResultSet re = sr.executeQuery();
            while (re.next()) {           
             cod = re.getString("codigo"); 
             baneado = re.getBoolean("ban");
                System.out.println("Codigo usuario: "+cod);
             }
            codigoU = Integer.parseInt(cod);
            PreparedStatement ult = conn.prepareStatement(sql);          
            ult.setInt(1, codigoU);           
            ResultSet fe = ult.executeQuery();
            while (fe.next()) {
             fecha = fe.getTimestamp("fecha");             
             }
            String tm = (""+fecha);
            regF.setText(tm);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
     void partidas_ganadas (){
       
        String comp = "select count(*) as total from Partida where ganador = ?";            
        try {
            Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
            try (PreparedStatement cm = conn.prepareStatement(comp)) {
                cm.setInt(1, codigoU);
                ResultSet rs = cm.executeQuery();
                while (rs.next()) {
                    win = rs.getInt("total");
                    System.out.println(win);
                }
                String gan = Integer.toString(win);
                winC.setText(gan);
            }
        }
            catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     void partidas_perdidas(){  
         
        String comp = "select count(*) as total from Partida where perdedor = ?";
           
        try {
            Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
             try (PreparedStatement cm = conn.prepareStatement(comp)) {
                 cm.setInt(1, codigoU);
                 ResultSet rs = cm.executeQuery();
                 while (rs.next()) {
                     per = rs.getInt("total");
                     System.out.println(per);
                 }
                 String gan = Integer.toString(per);
                 losC.setText(gan);
             }
        }
            catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     void informar_panel (){
         
          String comp = "select * from Partida where ganador = ? or perdedor = ?";
           
        try {
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection(connURL,username,pwd);
                
             try (PreparedStatement cm = conn.prepareStatement(comp)) {
                 
                 cm.setInt(1, codigoU);
                 cm.setInt(2, codigoU);
                 ResultSet rs = cm.executeQuery();
                 while (rs.next()) {
                     
                     int g = rs.getInt("ganador");
                     int l = rs.getInt("perdedor");
                     String nameR = "";
                     Timestamp t = rs.getTimestamp("fecha"); 
                     
                String codigo = "select nombre from Usuario where codigo = ?";
                
                PreparedStatement sr = conn.prepareStatement(codigo);
                
                     if (g == codigoU) {
                         
                     sr.setInt(1, l);  
                     
                     } 
                     else {
                         
                     sr.setInt(1, g);
                     
                     }
                     
                ResultSet re = sr.executeQuery();
                while (re.next()) {   
                    
                nameR = re.getString("nombre"); 
                    
                 }
                 
                     if (g == codigoU) {
                    
                    historial.append("\n"+nombre+" ganó a "+nameR+" el "
                                + ""+t+"");   
                     }
                     else {
                      
                    historial.append("\n"+nombre+" perdió contra "+nameR+" el "
                                + ""+t+"");
                         
                     }
                     
                 }
                 String gan = Integer.toString(per);
                 losC.setText(gan);
             }
        }
            catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     void comprobar_baneo (){
         
         if (baneado == true) {
             
             historial.append("\n\nESTÁS BANEADO"); 
             
         JOptionPane.showMessageDialog(null, "Estás baneado, entra en la web"
                 + " para comprobar la razón");   
         
         jugar.setEnabled(false);
         }
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lnombre = new javax.swing.JLabel();
        fechR = new javax.swing.JLabel();
        lpartidas = new javax.swing.JLabel();
        PartidaG = new javax.swing.JLabel();
        partidaP1 = new javax.swing.JLabel();
        jugar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        historial = new javax.swing.JTextArea();
        winC = new javax.swing.JTextField();
        losC = new javax.swing.JTextField();
        regF = new javax.swing.JTextField();
        nom = new javax.swing.JTextField();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu ");
        setResizable(false);
        setSize(new java.awt.Dimension(588, 497));

        lnombre.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lnombre.setText("Nombre:");

        fechR.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        fechR.setText("Última vez:");

        lpartidas.setBackground(new java.awt.Color(255, 153, 102));
        lpartidas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lpartidas.setText("Historial de partidas:");
        lpartidas.setOpaque(true);

        PartidaG.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        PartidaG.setText("Partidas ganadas:");

        partidaP1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        partidaP1.setText("Partidas perdidas:");

        jugar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jugar.setText("EMPEZAR");
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });

        historial.setColumns(20);
        historial.setRows(5);
        jScrollPane1.setViewportView(historial);

        winC.setEditable(false);
        winC.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        winC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        winC.setText("0");

        losC.setEditable(false);
        losC.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        losC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        losC.setText("0");

        regF.setEditable(false);
        regF.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        regF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        regF.setText("0");

        nom.setEditable(false);
        nom.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        salir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(partidaP1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(losC))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PartidaG)
                        .addGap(18, 18, 18)
                        .addComponent(winC, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lnombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(regF, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(nom))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lpartidas)
                        .addContainerGap(109, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PartidaG, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(winC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(partidaP1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(losC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lpartidas, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Abre la nueva ventana pasando los datos
    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        String info[]=new String[1]; 
        info[0]= name;     
        Seleccion.main(info); 
        this.setVisible(false);
    }//GEN-LAST:event_jugarActionPerformed

    // Botón sencillo para salir de la aplicación
    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        // Recoge los parámetros
        name=args[0];
        pass=args[1];
        java.awt.EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
            nom.setText(name);
            
        });
    }
   
    // Get para recoger los datos 
        public static String getNombre() {
        return name;
    }

        public static String getPass() {
        return pass;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PartidaG;
    private javax.swing.JLabel fechR;
    private javax.swing.JTextArea historial;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jugar;
    private javax.swing.JLabel lnombre;
    private javax.swing.JTextField losC;
    private javax.swing.JLabel lpartidas;
    private static javax.swing.JTextField nom;
    private javax.swing.JLabel partidaP1;
    private javax.swing.JTextField regF;
    private javax.swing.JButton salir;
    private javax.swing.JTextField winC;
    // End of variables declaration//GEN-END:variables
}
