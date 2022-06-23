package Cliente;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {

        Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";
        String total = "";
        
    public Login() {
        try{ 
             // Icono de la aplicación
            setIconImage(ImageIO.read(new File("src/Recursos/logoVicsio.png")));
          } 
                catch (IOException e){
                  e.printStackTrace();
                }
             BufferedImage img = null;
          try {
              // Carga el fondo
              img = ImageIO.read(new File("src/Recursos/fondoL.jpg"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          Image dimg = img.getScaledInstance(527, 450, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          setContentPane(new JLabel(imageIcon));
              initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passT = new javax.swing.JTextField();
        nombreT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        validar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INICIO DE SESIÓN");
        setResizable(false);

        registrar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        registrar.setText("CREAR CUENTA");
        registrar.setMaximumSize(new java.awt.Dimension(175, 50));
        registrar.setMinimumSize(new java.awt.Dimension(175, 50));
        registrar.setPreferredSize(new java.awt.Dimension(175, 50));
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("USUARIO:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("CONTRASEÑA:");

        passT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        nombreT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 204, 153));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel3.setText("   ¿No tienes cuenta? Rellena los datos y dale al botón de abajo");
        jLabel3.setOpaque(true);

        validar.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        validar.setText("VALIDAR");
        validar.setMaximumSize(new java.awt.Dimension(175, 50));
        validar.setMinimumSize(new java.awt.Dimension(175, 50));
        validar.setPreferredSize(new java.awt.Dimension(175, 50));
        validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passT, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(validar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(validar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Comprueba que existe el usuario, y si existe, comprueba si es buena la contraseña.
    // En caso positivo, pasa a la siguiente ventana pasando variables
    private void validarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarActionPerformed
            String nom = "", con = "", ip = "";
            int cod = 0;
        try {
                Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String comp = "select count(*) as total from Usuario where nombre like ? and pass like ?";
            PreparedStatement cm = conn.prepareStatement(comp);
            cm.setString(1, nombreT.getText());
            cm.setString(2, passT.getText());
            ResultSet rs = cm.executeQuery();
            while (rs.next()) {
            total = rs.getString("total"); 
                System.out.println(total);
             }
            cm.close();
            
            if (total.equals("1")) {
            String sql = "select codigo, nombre, pass from Usuario where nombre like ? and pass like ?"; 
            String insert_reg = "insert into registro (cod_usuario, fecha_ult, ip) values (?, ?, ?)";
            java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
                    try {
                        ip = Inet4Address.getLocalHost().getHostAddress();
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
            PreparedStatement sr = conn.prepareStatement(sql);
            sr.setString(1, nombreT.getText());
            sr.setString(2, passT.getText());
            
            ResultSet re = sr.executeQuery();
            while (re.next()) {
             cod = re.getInt("codigo");
             nom = re.getString("nombre"); 
             con = re.getString("pass");
             }
            PreparedStatement inser = conn.prepareStatement(insert_reg);
            
            inser.setInt(1, cod);
            inser.setTimestamp(2, sqlDate);
            inser.setString(3, ip);
            inser.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Sesión iniciada");
            
            String info[]=new String[2];  
            info[0]= nom;
            info[1]= con;
            MenuPrincipal.main(info); 
            this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error, credenciales no válidas");
            }
      }
       catch (ClassNotFoundException | SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            } 
    }//GEN-LAST:event_validarActionPerformed

    // Si no existe el usuario, se puede registrar metiendo un nombre de usuario que no
    // exista y contraseña
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
            
            try {
                Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(connURL,username,pwd);
                
            String comp = "select count(*) as total from Usuario where nombre like ? ";
            PreparedStatement cm = conn.prepareStatement(comp);
            cm.setString(1, nombreT.getText());
            ResultSet rs = cm.executeQuery();
            while (rs.next()) {
            total = rs.getString("total"); 
                System.out.println(total);
             }
            cm.close();
                if (!total.equals("1")) {  
            String sql = "insert into Usuario (nombre, pass, fecha, administrador, ban)"
                    + " values (?,?,?,FALSE, FALSE)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nombreT.getText());
            st.setString(2, passT.getText());
            java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
            st.setTimestamp(3, sqlDate);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "El usuario "+nombreT.getText()+" "
                    + "ha sido insertado en la base de datos");
            System.out.println("Datos insertados");
            st.close();
                }
                else
                {
            JOptionPane.showMessageDialog(null, "El usuario "+nombreT.getText()+""
                    + " ya está registrado en la base de datos");
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Conexión fallida, error: "+e.getMessage());
            }              
    }//GEN-LAST:event_registrarActionPerformed
  
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nombreT;
    private javax.swing.JTextField passT;
    private javax.swing.JButton registrar;
    private javax.swing.JButton validar;
    // End of variables declaration//GEN-END:variables
}
