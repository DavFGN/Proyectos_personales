/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.awt.Color;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Galeria extends javax.swing.JFrame {

   ImageIcon imagen1, imagen2, imagen3, imagen4, imagen5, imagen6,     
             imagen7, imagen8, imagen9, imagen10, imagen11, imagen12;
         
    public Galeria() {
        try{ 
             // Icono de la aplicación
            setIconImage(ImageIO.read(new File("src/Recursos/logoVicsio.png")));
          } 
                catch (IOException e){
                  e.printStackTrace();
                }
             BufferedImage img = null;
          try {
              img = ImageIO.read(new File("src/Recursos/piramid.jpg"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          Image dimg = img.getScaledInstance(1012, 478, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          setContentPane(new JLabel(imageIcon));
          this.setUndecorated(true);
         
            initComponents();      
            // Carga las imágenes en los label
            cargar_img();

    }
    
    void cargar_img (){
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

        met_rec(imagen1, viento1);
        met_rec(imagen2, viento2);
        met_rec(imagen3, fuego1);       
        met_rec(imagen4, fuego2);
        met_rec(imagen5, agua1);
        met_rec(imagen6, agua2);
        met_rec(imagen7, tierra1);       
        met_rec(imagen8, tierra2);
        met_rec(imagen9, luz1);
        met_rec(imagen10, luz2);
        met_rec(imagen11, oscuridad1);       
        met_rec(imagen12, oscuridad2);
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
        viento1 = new javax.swing.JLabel();
        viento2 = new javax.swing.JLabel();
        fuego2 = new javax.swing.JLabel();
        fuego1 = new javax.swing.JLabel();
        agua2 = new javax.swing.JLabel();
        tierra2 = new javax.swing.JLabel();
        tierra1 = new javax.swing.JLabel();
        agua1 = new javax.swing.JLabel();
        luz2 = new javax.swing.JLabel();
        oscuridad1 = new javax.swing.JLabel();
        oscuridad2 = new javax.swing.JLabel();
        luz1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ayuda");
        setMaximumSize(new java.awt.Dimension(1012, 478));
        setMinimumSize(new java.awt.Dimension(1012, 478));
        setResizable(false);
        setSize(new java.awt.Dimension(1012, 478));

        cerrar.setText("CERRAR");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        viento1.setToolTipText("");
        viento1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 102, 51)));
        viento1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viento1MousePressed(evt);
            }
        });

        viento2.setToolTipText("");
        viento2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 102, 51)));
        viento2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viento2MousePressed(evt);
            }
        });

        fuego2.setToolTipText("");
        fuego2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        fuego2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fuego2MousePressed(evt);
            }
        });

        fuego1.setToolTipText("");
        fuego1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        fuego1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fuego1MousePressed(evt);
            }
        });

        agua2.setToolTipText("");
        agua2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 204, 204)));
        agua2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                agua2MousePressed(evt);
            }
        });

        tierra2.setToolTipText("");
        tierra2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 102, 0)));
        tierra2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tierra2MousePressed(evt);
            }
        });

        tierra1.setToolTipText("");
        tierra1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 102, 0)));
        tierra1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tierra1MousePressed(evt);
            }
        });

        agua1.setToolTipText("");
        agua1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 204, 204)));
        agua1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                agua1MousePressed(evt);
            }
        });

        luz2.setForeground(new java.awt.Color(255, 255, 51));
        luz2.setToolTipText("");
        luz2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(204, 204, 0)));
        luz2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                luz2MousePressed(evt);
            }
        });

        oscuridad1.setToolTipText("");
        oscuridad1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 0, 102)));
        oscuridad1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                oscuridad1MousePressed(evt);
            }
        });

        oscuridad2.setToolTipText("");
        oscuridad2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 0, 102)));
        oscuridad2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                oscuridad2MousePressed(evt);
            }
        });

        luz1.setBackground(new java.awt.Color(255, 255, 153));
        luz1.setForeground(new java.awt.Color(255, 255, 51));
        luz1.setToolTipText("");
        luz1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(204, 204, 0)));
        luz1.setOpaque(true);
        luz1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                luz1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(viento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viento2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fuego1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fuego2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(agua1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agua2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tierra1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tierra2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(luz1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(luz2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oscuridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oscuridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cerrar))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cerrar)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(agua1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tierra1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(luz1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(oscuridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(viento1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fuego1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(agua2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tierra2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(luz2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(oscuridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(viento2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fuego2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     // botón sencillo para cerrar la ventana sin salir de la aplicación
    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_cerrarActionPerformed
    // Si son pulsadas las imagenes, se expandirán
    private void fuego2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fuego2MousePressed
        mostrarImagen(imagen4);               
    }//GEN-LAST:event_fuego2MousePressed

    private void viento1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viento1MousePressed
        mostrarImagen(imagen1); 
    }//GEN-LAST:event_viento1MousePressed

    private void viento2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viento2MousePressed
        mostrarImagen(imagen2);
    }//GEN-LAST:event_viento2MousePressed

    private void fuego1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fuego1MousePressed
        mostrarImagen(imagen3);
    }//GEN-LAST:event_fuego1MousePressed

    private void agua1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agua1MousePressed
        mostrarImagen(imagen5);
    }//GEN-LAST:event_agua1MousePressed

    private void agua2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agua2MousePressed
        mostrarImagen(imagen6);
    }//GEN-LAST:event_agua2MousePressed

    private void tierra1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tierra1MousePressed
        mostrarImagen(imagen7);
    }//GEN-LAST:event_tierra1MousePressed

    private void tierra2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tierra2MousePressed
        mostrarImagen(imagen8);
    }//GEN-LAST:event_tierra2MousePressed

    private void luz1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luz1MousePressed
        mostrarImagen(imagen9);
    }//GEN-LAST:event_luz1MousePressed

    private void luz2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luz2MousePressed
        mostrarImagen(imagen10);
    }//GEN-LAST:event_luz2MousePressed

    private void oscuridad1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oscuridad1MousePressed
        mostrarImagen(imagen11);
    }//GEN-LAST:event_oscuridad1MousePressed

    private void oscuridad2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oscuridad2MousePressed
        mostrarImagen(imagen12);
    }//GEN-LAST:event_oscuridad2MousePressed

   public void mostrarImagen (ImageIcon img){
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
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Galeria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agua1;
    private javax.swing.JLabel agua2;
    private javax.swing.JButton cerrar;
    private javax.swing.JLabel fuego1;
    private javax.swing.JLabel fuego2;
    private javax.swing.JLabel luz1;
    private javax.swing.JLabel luz2;
    private javax.swing.JLabel oscuridad1;
    private javax.swing.JLabel oscuridad2;
    private javax.swing.JLabel tierra1;
    private javax.swing.JLabel tierra2;
    private javax.swing.JLabel viento1;
    private javax.swing.JLabel viento2;
    // End of variables declaration//GEN-END:variables
}
