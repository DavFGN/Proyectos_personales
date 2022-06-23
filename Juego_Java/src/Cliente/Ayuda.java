
package Cliente;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Ayuda extends javax.swing.JFrame {

   ImageIcon imagen1, imagen2, imagen3, imagen4, imagen5, imagen6, lyo, cycle;
         
    public Ayuda() {
        try{ 
             // Icono de la aplicación
            setIconImage(ImageIO.read(new File("src/Recursos/logoVicsio.png")));
          } 
                catch (IOException e){
                  e.printStackTrace();
                }
             BufferedImage img = null;
          try {
              img = ImageIO.read(new File("src/Recursos/fondo_a.jpg"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          Image dimg = img.getScaledInstance(1008, 598, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          setContentPane(new JLabel(imageIcon));
          this.setUndecorated(true);
         
            initComponents();   
            // Carga las imagenes en los label
            cargar_img();
            // Carga las reglas del juego en el panel de información
            info.setText(cad);

    }
    
    
    
    void cargar_img (){
         imagen1 = new ImageIcon("src/Recursos/viento.jpg");    
         imagen2 = new ImageIcon("src/Recursos/fire.jpg");     
         imagen3 = new ImageIcon("src/Recursos/agua.jpg");    
         imagen4 = new ImageIcon("src/Recursos/tierra.jpg");      
         imagen5 = new ImageIcon("src/Recursos/luz.jpg");      
         imagen6 = new ImageIcon("src/Recursos/oscuridad.jpg");     
         lyo = new ImageIcon("src/Recursos/lyo.png"); 
         cycle = new ImageIcon("src/Recursos/ciclo.png"); 
         
        met_rec(imagen1, viento1);
        met_rec(imagen2, fuego1);       
        met_rec(imagen3, agua1);
        met_rec(imagen4, tierra1);       
        met_rec(imagen5, luz1);
        met_rec(imagen6, oscuridad1);  
        met_rec(lyo, lablyo);
        met_rec(cycle, ciclo);
    }
    
   void met_rec (ImageIcon img, JLabel lab ){    
        ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance
        (lab.getWidth(), lab.getHeight(), java.awt.Image.SCALE_DEFAULT));
         lab.setIcon(icono);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        fuego1 = new javax.swing.JLabel();
        tierra1 = new javax.swing.JLabel();
        agua1 = new javax.swing.JLabel();
        oscuridad1 = new javax.swing.JLabel();
        luz1 = new javax.swing.JLabel();
        viento1 = new javax.swing.JLabel();
        lablyo = new javax.swing.JLabel();
        ciclo = new javax.swing.JLabel();
        info = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ayuda");
        setMaximumSize(new java.awt.Dimension(1008, 598));
        setMinimumSize(new java.awt.Dimension(1008, 598));
        setResizable(false);
        setSize(new java.awt.Dimension(1008, 598));

        cerrar.setText("CERRAR");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        fuego1.setToolTipText("");
        fuego1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        tierra1.setToolTipText("");
        tierra1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 102, 0)));

        agua1.setToolTipText("");
        agua1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 204, 204)));

        oscuridad1.setToolTipText("");
        oscuridad1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 0, 102)));

        luz1.setBackground(new java.awt.Color(255, 255, 153));
        luz1.setForeground(new java.awt.Color(255, 255, 51));
        luz1.setToolTipText("");
        luz1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(204, 204, 0)));
        luz1.setOpaque(true);

        viento1.setToolTipText("");
        viento1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 102, 51)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(luz1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tierra1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ciclo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lablyo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oscuridad1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fuego1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viento1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agua1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(viento1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fuego1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tierra1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(agua1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oscuridad1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(luz1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lablyo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        info.setBackground(new java.awt.Color(7, 23, 138));
        info.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        info.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(0, 0, 102)));
        info.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(cerrar)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cerrar)
                .addGap(45, 45, 45)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
            .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_cerrarActionPerformed

        String cad = ("<html> <body>\n" +
"<div style='color:white'> \n" +
"-El juego consiste en una pelea en la que se enfrentan 2 equipos <br><br>\n" +
"-El ataque y velocidad actúan como valores puros, y la defensa reduce el ataque \n" +
"recibido en el porcentaje que se tenga, aunque al recibir el golpe, se reduce un\n" +
"25%. <br><br>\n" +
"-Si no hay un enemigo en frente, el daño se multiplica X2.<br><br>\n" +
"-Deben colocarse 3 luchadores y un bonus en el campo. <br><br>\n" +
"-Los bonus se van sumando, es decir, no se reducen a los valores iniciales después\n" +
"de cada ronda. <br><br>\n" +
"-Cada luchador tiene dos zonas preferidas, si es colocado en una de ellas, recibirá\n" +
"un incremento de 25% en ataque, velocidad, y un 5% sumado a la Defensa. <br><br>\n" +
"-El bonus incrementará las estadísticas de los luchadores, y si el tipo es el mismo,\n" +
"recibirá un bonus adicional.<br><br>\n" +
"-Si 2 luchadores están en escala, puede haber sinergía, según la tabla derecha, por\n" +
"ejemplo, el aire y fuego, se potenciarán entre sí 25% ataque,velocidad y 10% de \n" +
"defensa, aunque si hay fuego y agua, se reduce un 5% las estadísticas de ambos. <br><br>\n" +
"-Los tipo luz y oscuridad son especiales y tienen sinergía especial, \n" +
"otorgando un 15% en ataque, velocidad y 5% en defensa entre ellos y 10% con el \n" +
"resto de tipos.<br><br>\n" +
"-Al finalizar la ronda, la dimensión en la que se lucha cambia, pudiendo potenciar\n" +
"o frenar el poder dependiendo del tipo del luchador.<br><br>\n" +
"-Sigue jugando para descubrir todas las estrategias posibles.\n" +
"</div> </html>");
  
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Ayuda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agua1;
    private javax.swing.JButton cerrar;
    private javax.swing.JLabel ciclo;
    private javax.swing.JLabel fuego1;
    private javax.swing.JLabel info;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lablyo;
    private javax.swing.JLabel luz1;
    private javax.swing.JLabel oscuridad1;
    private javax.swing.JLabel tierra1;
    private javax.swing.JLabel viento1;
    // End of variables declaration//GEN-END:variables
}
