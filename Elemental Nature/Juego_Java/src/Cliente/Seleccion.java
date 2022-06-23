package Cliente;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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


public class Seleccion extends javax.swing.JFrame {
    ImageIcon  limpiar, imagen1, imagen2, imagen3, imagen4, imagen5, imagen6,     
                imagen7, imagen8, imagen9, imagen10, imagen11, imagen12;

    ArrayList <String> posiciones;
    
    public static String name;
    
    int tipo = 0, ba = 0, bt = 0, bd = 0, be = 0, bv = 0, bs = 0;
    
    Connection conn = null;
        String username="postgres";
	String pwd="postgres";
	String connURL="jdbc:postgresql://localhost:5432/ProyectoFinal";

    public Seleccion() {
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
          Image dimg = img.getScaledInstance(795, 701, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          setContentPane(new JLabel(imageIcon));

            initComponents();
            // al ser una ventana complicada, se explicarán los métodos individualmente
            // en vez de una breve explicación aquí como en login o menú principal
            comprobar_bonus();
            toolDescripcion();
            
            // Se activa para poder recibir el texto, sobreescribiéndolo 
            pos1.setTransferHandler(new TransferHandler("text"));       
            pos2.setTransferHandler(new TransferHandler("text"));
            pos3.setTransferHandler(new TransferHandler("text"));
            pos4.setTransferHandler(new TransferHandler("text"));
            pos5.setTransferHandler(new TransferHandler("text"));
            pos6.setTransferHandler(new TransferHandler("text"));
            bsel.setTransferHandler(new TransferHandler("text"));  
            limpiar = new ImageIcon("src/Recursos/bonus_l.jpg");  
            // Se pone por defecto la siguiente imagen en el bonus
            met_rec(limpiar, bonus);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jugadorN = new javax.swing.JLabel();
        disponible = new javax.swing.JLabel();
        l1 = new javax.swing.JTextField();
        l2 = new javax.swing.JTextField();
        l3 = new javax.swing.JTextField();
        l4 = new javax.swing.JTextField();
        l5 = new javax.swing.JTextField();
        l6 = new javax.swing.JTextField();
        l7 = new javax.swing.JTextField();
        l8 = new javax.swing.JTextField();
        l9 = new javax.swing.JTextField();
        l10 = new javax.swing.JTextField();
        l11 = new javax.swing.JTextField();
        l12 = new javax.swing.JTextField();
        lviento = new javax.swing.JLabel();
        lviento1 = new javax.swing.JLabel();
        lviento2 = new javax.swing.JLabel();
        lviento3 = new javax.swing.JLabel();
        lviento4 = new javax.swing.JLabel();
        lviento5 = new javax.swing.JLabel();
        galeria = new javax.swing.JButton();
        nombreT = new javax.swing.JTextField();
        ayuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selección");
        setResizable(false);
        setSize(new java.awt.Dimension(795, 701));

        jPanel2.setOpaque(false);

        pos2.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos2.setText("2");
        pos2.setName("2"); // NOI18N

        pos1.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos1.setText("1");
        pos1.setName("1"); // NOI18N

        pos3.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos3.setText("3");
        pos3.setName("3"); // NOI18N

        pos4.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos4.setText("4");
        pos4.setName("4"); // NOI18N

        pos5.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos5.setText("5");
        pos5.setName("5"); // NOI18N

        pos6.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        pos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos6.setText("6");
        pos6.setName("6"); // NOI18N

        bonus.setBackground(new java.awt.Color(255, 255, 255));
        bonus.setFont(new java.awt.Font("Impact", 1, 24)); // NOI18N
        bonus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bonusMousePressed(evt);
            }
        });

        validar.setText("VALIDAR");
        validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarActionPerformed(evt);
            }
        });

        limpiar1.setText("LIMPIAR");
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
        calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pl3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pl4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pl5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pl6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(re3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(re2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(validar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(limpiar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
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
                        .addComponent(bsel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pl3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pl5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pl1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pl2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pl4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(re1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pl6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(re3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(re2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validar)
                    .addComponent(calcular)
                    .addComponent(limpiar1))
                .addGap(18, 18, 18))
        );

        pos2.getAccessibleContext().setAccessibleName("2");
        pos1.getAccessibleContext().setAccessibleName("1");
        pos1.getAccessibleContext().setAccessibleDescription("");
        pos3.getAccessibleContext().setAccessibleName("3");
        pos4.getAccessibleContext().setAccessibleName("4");
        pos5.getAccessibleContext().setAccessibleName("5");
        pos6.getAccessibleContext().setAccessibleName("6");

        jugadorN.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jugadorN.setText("Jugador: ");

        disponible.setBackground(new java.awt.Color(204, 153, 0));
        disponible.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        disponible.setText("LUCHADORES DISPONIBLES:");
        disponible.setOpaque(true);

        l1.setEditable(false);
        l1.setBackground(new java.awt.Color(153, 255, 153));
        l1.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l1.setText("L1");
        l1.setToolTipText("");
        l1.setDragEnabled(true);
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
        l2.setBackground(new java.awt.Color(51, 102, 0));
        l2.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l2.setText("L2");
        l2.setToolTipText("");
        l2.setDragEnabled(true);
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
        l3.setBackground(new java.awt.Color(255, 102, 102));
        l3.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l3.setText("L3");
        l3.setDragEnabled(true);
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

        l4.setEditable(false);
        l4.setBackground(new java.awt.Color(153, 0, 0));
        l4.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l4.setText("L4");
        l4.setDragEnabled(true);
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

        l5.setEditable(false);
        l5.setBackground(new java.awt.Color(153, 255, 255));
        l5.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l5.setText("L5");
        l5.setDragEnabled(true);
        l5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l5FocusGained(evt);
            }
        });
        l5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l5MousePressed(evt);
            }
        });

        l6.setEditable(false);
        l6.setBackground(new java.awt.Color(0, 51, 204));
        l6.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l6.setForeground(new java.awt.Color(204, 255, 255));
        l6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l6.setText("L6");
        l6.setDragEnabled(true);
        l6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l6FocusGained(evt);
            }
        });
        l6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l6MousePressed(evt);
            }
        });

        l7.setEditable(false);
        l7.setBackground(new java.awt.Color(255, 153, 102));
        l7.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l7.setText("L7");
        l7.setDragEnabled(true);
        l7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l7FocusGained(evt);
            }
        });
        l7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l7MousePressed(evt);
            }
        });

        l8.setEditable(false);
        l8.setBackground(new java.awt.Color(153, 102, 0));
        l8.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l8.setText("L8");
        l8.setDragEnabled(true);
        l8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l8FocusGained(evt);
            }
        });
        l8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l8MousePressed(evt);
            }
        });

        l9.setEditable(false);
        l9.setBackground(new java.awt.Color(255, 255, 204));
        l9.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l9.setText("L9");
        l9.setDragEnabled(true);
        l9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l9FocusGained(evt);
            }
        });
        l9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l9MousePressed(evt);
            }
        });

        l10.setEditable(false);
        l10.setBackground(new java.awt.Color(255, 255, 102));
        l10.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l10.setText("L10");
        l10.setDragEnabled(true);
        l10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l10FocusGained(evt);
            }
        });
        l10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l10MousePressed(evt);
            }
        });

        l11.setEditable(false);
        l11.setBackground(new java.awt.Color(153, 153, 153));
        l11.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l11.setText("L11");
        l11.setDragEnabled(true);
        l11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l11FocusGained(evt);
            }
        });
        l11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l11MousePressed(evt);
            }
        });

        l12.setEditable(false);
        l12.setBackground(new java.awt.Color(0, 0, 0));
        l12.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        l12.setForeground(new java.awt.Color(255, 255, 204));
        l12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l12.setText("L12");
        l12.setDragEnabled(true);
        l12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                l12FocusGained(evt);
            }
        });
        l12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l12MousePressed(evt);
            }
        });

        lviento.setBackground(new java.awt.Color(51, 255, 51));
        lviento.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        lviento.setForeground(new java.awt.Color(51, 51, 51));
        lviento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lviento.setText("VIENTO");
        lviento.setToolTipText("");
        lviento.setOpaque(true);

        lviento1.setBackground(new java.awt.Color(255, 51, 51));
        lviento1.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        lviento1.setForeground(new java.awt.Color(51, 51, 51));
        lviento1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lviento1.setText("FUEGO");
        lviento1.setToolTipText("");
        lviento1.setOpaque(true);

        lviento2.setBackground(new java.awt.Color(0, 255, 255));
        lviento2.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        lviento2.setForeground(new java.awt.Color(51, 51, 51));
        lviento2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lviento2.setText("AGUA");
        lviento2.setToolTipText("");
        lviento2.setOpaque(true);

        lviento3.setBackground(new java.awt.Color(255, 102, 0));
        lviento3.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        lviento3.setForeground(new java.awt.Color(51, 51, 51));
        lviento3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lviento3.setText("TIERRA");
        lviento3.setToolTipText("");
        lviento3.setOpaque(true);

        lviento4.setBackground(new java.awt.Color(255, 255, 0));
        lviento4.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        lviento4.setForeground(new java.awt.Color(51, 51, 51));
        lviento4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lviento4.setText("LUZ");
        lviento4.setToolTipText("");
        lviento4.setOpaque(true);

        lviento5.setBackground(new java.awt.Color(102, 51, 255));
        lviento5.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        lviento5.setForeground(new java.awt.Color(255, 255, 255));
        lviento5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lviento5.setText("OSCURIDAD");
        lviento5.setToolTipText("");
        lviento5.setOpaque(true);

        galeria.setText("GALERIA");
        galeria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                galeriaActionPerformed(evt);
            }
        });

        nombreT.setEditable(false);
        nombreT.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nombreT.setEnabled(false);

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
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(disponible, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lviento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                                .addGap(23, 23, 23)
                                .addComponent(lviento1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lviento2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lviento3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lviento4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lviento5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(galeria, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jugadorN, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jugadorN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(disponible, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ayuda)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lviento)
                            .addComponent(lviento1)
                            .addComponent(lviento2)
                            .addComponent(lviento3)
                            .addComponent(lviento4)
                            .addComponent(lviento5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(galeria)))
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    // Los siguientes métodos indican que es posible arrastrar el texto de sus cajas
    // necesario para colocar luchadores y bonus
    private void l2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MousePressed
    arrastrar(evt);
    }//GEN-LAST:event_l2MousePressed

    private void l1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MousePressed
    arrastrar(evt);
    }//GEN-LAST:event_l1MousePressed

    private void l3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l3MousePressed
    arrastrar(evt);
    }//GEN-LAST:event_l3MousePressed

    private void l4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l4MousePressed
     arrastrar(evt);
    }//GEN-LAST:event_l4MousePressed

    private void l5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l5MousePressed
    arrastrar(evt);
    }//GEN-LAST:event_l5MousePressed

    private void l6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l6MousePressed
    arrastrar(evt);
    }//GEN-LAST:event_l6MousePressed

    private void l7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l7MousePressed
     arrastrar(evt);
    }//GEN-LAST:event_l7MousePressed

    private void l8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l8MousePressed
     arrastrar(evt);
    }//GEN-LAST:event_l8MousePressed

    private void l9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l9MousePressed
     arrastrar(evt);
    }//GEN-LAST:event_l9MousePressed

    private void l10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l10MousePressed
     arrastrar(evt);
    }//GEN-LAST:event_l10MousePressed

    private void l11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l11MousePressed
     arrastrar(evt);
    }//GEN-LAST:event_l11MousePressed

    private void l12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l12MousePressed
    arrastrar(evt);
    }//GEN-LAST:event_l12MousePressed

    // Los siguientes métodos hacen que si es pulsado una vez, automáticamente
    // el texto de los mismos sea seleccionado manteniendo pulsado
    private void l1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l1FocusGained
     fijar(evt);
    }//GEN-LAST:event_l1FocusGained

    private void l2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l2FocusGained
    fijar(evt);
    }//GEN-LAST:event_l2FocusGained

    private void l3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l3FocusGained
       fijar(evt);
    }//GEN-LAST:event_l3FocusGained

    private void l4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l4FocusGained
      fijar(evt);
    }//GEN-LAST:event_l4FocusGained

    private void l5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l5FocusGained
     fijar(evt);
    }//GEN-LAST:event_l5FocusGained

    private void l6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l6FocusGained
     fijar(evt);
    }//GEN-LAST:event_l6FocusGained

    private void l7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l7FocusGained
    fijar(evt);
    }//GEN-LAST:event_l7FocusGained

    private void l8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l8FocusGained
      fijar(evt);
    }//GEN-LAST:event_l8FocusGained

    private void l9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l9FocusGained
     fijar(evt);
    }//GEN-LAST:event_l9FocusGained

    private void l10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l10FocusGained
       fijar(evt);
    }//GEN-LAST:event_l10FocusGained

    private void l11FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l11FocusGained
      fijar(evt);
    }//GEN-LAST:event_l11FocusGained

    private void l12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_l12FocusGained
      fijar(evt);
    }//GEN-LAST:event_l12FocusGained

    // Limpiar todas las cajas para que vuelvan a estar como al principio
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
     
     void limp_comb (JTextField p, int t){
         p.setText("Combinación "+t);
         p.setBackground(Color.white);
         p.setForeground(Color.black);
     }
     
     // Hace visible la ventana galería
    private void galeriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_galeriaActionPerformed
       new Galeria().setVisible(true);
    }//GEN-LAST:event_galeriaActionPerformed

    // Al ser pulsado, llama al método que controla si los datos están bien. Más info
    // en el mismo
    private void validarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarActionPerformed
        comprobar_pos();
    }//GEN-LAST:event_validarActionPerformed

    // Al pulsar el botón calcular, activa los siguientes métodos. Se les pasa las variables
    // que tienen que calcular. Más info en los mismos
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

    // Calcula la sinergía entre 2 luchadores en vertical
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
    
    // Recoge las variables de la base de datos del luchador en el bonus
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
  
    // Calcula si el luchador está en una de sus posiciones favoritas, bonus y el
    // bonus que pudiera tener como sinergía. Hace las cajas de combinación pulsables
    // informando de lo que mejorará el luchador si es lanzado a la batalla
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
    
    // Si la imagen del bonus es presionado, se muestra la imagen del luchador
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

    // Lanza la ventana de ayuda
    private void ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayudaActionPerformed
        new Ayuda().setVisible(true);
    }//GEN-LAST:event_ayudaActionPerformed
   
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
    
    void comprobar_pos () {
        pos1.setText(pos1.getText().toUpperCase());
        pos2.setText(pos2.getText().toUpperCase());
        pos3.setText(pos3.getText().toUpperCase());
        pos4.setText(pos4.getText().toUpperCase());
        pos5.setText(pos5.getText().toUpperCase());
        pos6.setText(pos6.getText().toUpperCase());
        
        posiciones = new ArrayList();
        posiciones.add(pos1.getText());
        posiciones.add(pos2.getText());
        posiciones.add(pos3.getText());
        posiciones.add(pos4.getText());
        posiciones.add(pos5.getText());
        posiciones.add(pos6.getText());
        posiciones.add(bsel.getText());
     
        int contador = 0, cnt = 0, raro = 0;
        
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
        }
        
        if ( raro > 0) {            
         JOptionPane.showMessageDialog(null, "No debe haber una posición con 'L' "
                 + "seguida de letras, un 0 o superior a 12");  
        }  else if (contador < 4 || contador > 4) {
         JOptionPane.showMessageDialog(null, "Debe poner 3 luchadores y un bonus");          
        } else if (cnt >= 8) {
          JOptionPane.showMessageDialog(null, "No debe haber luchadores repetidos");  
        } else if (!bsel.getText().startsWith("L")) {
          JOptionPane.showMessageDialog(null, "Debe incluir un bonus");  
        } else {
           JOptionPane.showMessageDialog(null, "Datos correctos, ya puede jugar"); 
           des_boton();
           String info[]=new String[9];            
           info [0] = getNombre();        
            for (int i = 0; i < posiciones.size(); i++) {
                info[i+1] = posiciones.get(i);
            }
            Jugar.main(info);
            this.setVisible(false);
        }
    }
    
    void des_boton() {
        pos1.setEnabled(false);
        pos2.setEnabled(false);
        pos3.setEnabled(false);
        pos4.setEnabled(false);
        pos5.setEnabled(false);
        pos6.setEnabled(false);
        bsel.setEnabled(false);
        
        pos1.setEditable(false);
        pos2.setEditable(false);
        pos3.setEditable(false);
        pos4.setEditable(false);
        pos5.setEditable(false);
        pos6.setEditable(false);
         bsel.setEditable(false); 
    }
    
    // Se crea con el bonus una escucha personalizada sensible, si cambia el texto de
    // la caja de bonus, se carga con la imagen del luchador en caso de haberla
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
   
    void met_rec (ImageIcon img, JLabel lab ){    
        ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance
        (lab.getWidth(), lab.getHeight(), java.awt.Image.SCALE_DEFAULT));
         lab.setIcon(icono);
    }
    
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
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        name=args[0];
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seleccion().setVisible(true);
                nombreT.setText(name);
            }
        });
    }

    public static String getNombre() {
        return name;
    }
    
    // Carga la descripción de los luchadores en las cajas, visibles si el ratón pasa
    // por encima
    void toolDescripcion (){
        l1.setToolTipText(viento1);
        l2.setToolTipText(viento2);
        l3.setToolTipText(fuego1);
        l4.setToolTipText(fuego2);
        l5.setToolTipText(agua1);
        l6.setToolTipText(agua2);
        l7.setToolTipText(tierra1);
        l8.setToolTipText(tierra2);
        l9.setToolTipText(luz1);
        l10.setToolTipText(luz2);
        l11.setToolTipText(oscuridad1);
        l12.setToolTipText(oscuridad2);
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
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ayuda;
    private javax.swing.JLabel bonus;
    private javax.swing.JTextField bsel;
    private javax.swing.JButton calcular;
    private static javax.swing.JLabel disponible;
    private javax.swing.JButton galeria;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JLabel jugadorN;
    private javax.swing.JTextField l1;
    private javax.swing.JTextField l10;
    private javax.swing.JTextField l11;
    private javax.swing.JTextField l12;
    private javax.swing.JTextField l2;
    private javax.swing.JTextField l3;
    private javax.swing.JTextField l4;
    private javax.swing.JTextField l5;
    private javax.swing.JTextField l6;
    private javax.swing.JTextField l7;
    private javax.swing.JTextField l8;
    private javax.swing.JTextField l9;
    private javax.swing.JButton limpiar1;
    private javax.swing.JLabel lviento;
    private javax.swing.JLabel lviento1;
    private javax.swing.JLabel lviento2;
    private javax.swing.JLabel lviento3;
    private javax.swing.JLabel lviento4;
    private javax.swing.JLabel lviento5;
    private static javax.swing.JTextField nombreT;
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
    private javax.swing.JButton validar;
    // End of variables declaration//GEN-END:variables
}
