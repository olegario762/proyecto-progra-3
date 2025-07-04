
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Avl_arboles_frame extends javax.swing.JFrame {

  
    ArbolAVL arbol = new ArbolAVL (); 
    
    public Avl_arboles_frame() {
        initComponents();
        
        
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        
        
        String ruta = "C:\\Users\\Ixtamer\\Desktop\\archivo proyecto";  // <-- cambia esto a tu ruta real
       DefaultTableModel modelo = arbol.cargarVehiculosDesdeCarpetas(ruta);
        tabla__avl___.setModel(modelo);
        
    }
    
    
    private void mostrarEnTabla(ArrayList<Vehiculo> lista) {
    String[] columnas = {"Departamento", "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    for (Vehiculo v : lista) {
        Object[] fila = {
            v.getDepartamento(),
            v.getPlaca(),
            v.getDpi(),
            v.getNombre(),
            v.getMarca(),
            v.getModelo(),
            v.getAno(),
            v.getMultas(),
            v.getTraspasos()
        };
        modelo.addRow(fila);
    }

    tabla__avl___.setModel(modelo);
}
    private void seleccionarFilaPorPlaca(String placa) {
    DefaultTableModel modelo = (DefaultTableModel) tabla__avl___.getModel();
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String placaTabla = modelo.getValueAt(i, 1).toString(); // Columna 1 = Placa
        if (placaTabla.equalsIgnoreCase(placa)) {
            tabla__avl___.setRowSelectionInterval(i, i);
            tabla__avl___.scrollRectToVisible(tabla__avl___.getCellRect(i, 0, true));
            return;
        }
    }
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla__avl___ = new javax.swing.JTable();
        pre___ = new javax.swing.JButton();
        post____ = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ver__Arbol___ = new javax.swing.JButton();
        Buscar____ = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        Tiempo___ = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla__avl___.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Departamento", "Placa", "Dpi", "Nombre", "Marca", "Modelo", "Anio", "Multas", "Traspasos"
            }
        ));
        jScrollPane1.setViewportView(tabla__avl___);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 930, -1));

        pre___.setText("Pre");
        pre___.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pre___ActionPerformed(evt);
            }
        });
        jPanel1.add(pre___, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, -1, -1));

        post____.setText("post");
        post____.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                post____ActionPerformed(evt);
            }
        });
        jPanel1.add(post____, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, -1, -1));

        jButton2.setText("in");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 520, -1, -1));

        ver__Arbol___.setText("ver arbol");
        ver__Arbol___.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver__Arbol___ActionPerformed(evt);
            }
        });
        jPanel1.add(ver__Arbol___, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, -1, -1));

        Buscar____.setText("Buscar");
        Buscar____.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar____ActionPerformed(evt);
            }
        });
        jPanel1.add(Buscar____, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 320, -1));

        Tiempo___.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel1.add(Tiempo___, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 170, 330, 140));

        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pre___ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pre___ActionPerformed
         ArrayList<Vehiculo> lista = arbol.recorridoPreorden();
    mostrarEnTabla(lista);
    }//GEN-LAST:event_pre___ActionPerformed

    private void post____ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_post____ActionPerformed
        ArrayList<Vehiculo> lista = arbol.recorridoPostorden();
    mostrarEnTabla(lista);
    }//GEN-LAST:event_post____ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ArrayList<Vehiculo> lista = arbol.recorridoInorden();
    mostrarEnTabla(lista);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ver__Arbol___ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver__Arbol___ActionPerformed
          try {
        // 1. Obtener el código DOT desde tu árbol
        String dot = arbol.generarDOT(); // Asegúrate que "arbolVehiculos" esté accesible

        // 2. Guardar el archivo .dot
        FileWriter writer = new FileWriter("arbol_vehiculos.dot");
        writer.write(dot);
        writer.close();

        // 3. Generar imagen con Graphviz
        ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "arbol_vehiculos.dot", "-o", "arbol_vehiculos.png");
        
        
        //dot", "-Tpng", "arbol_vehiculos.dot", "-o", "arbol_vehiculos.png
        
       
        pb.redirectErrorStream(true);
        Process p = pb.start();
        int exitCode = p.waitFor();

        if (exitCode == 0) {
            JOptionPane.showMessageDialog(this, "Imagen generada exitosamente.");

            Desktop.getDesktop().open(new File("arbol_vehiculos.png"));
        } else {
            JOptionPane.showMessageDialog(this, "Error al generar la imagen con Graphviz.");
        }

    } catch (IOException | InterruptedException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
    }//GEN-LAST:event_ver__Arbol___ActionPerformed

    private void Buscar____ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar____ActionPerformed
         String placaBuscada = jTextField1.getText().trim();

    if (placaBuscada.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor ingresa una placa.");
        return;
    }

    long inicio = System.nanoTime();
    Vehiculo encontrado = arbol.buscarPorPlacaConTiempo(placaBuscada); // <- asegúrate de que este método exista en tu clase ArbolAVL
    long fin = System.nanoTime();

    double tiempoMilisegundos = (fin - inicio) / 1_000_000.0;
    Tiempo___.setText(String.format("Tiempo: %.4f ms", tiempoMilisegundos));

    if (encontrado != null) {
        seleccionarFilaPorPlaca(placaBuscada);
        JOptionPane.showMessageDialog(this, "Vehículo encontrado:\n" );
    } else {
        JOptionPane.showMessageDialog(this, "Vehículo con placa '" + placaBuscada + "' no encontrado.");
    }

    }//GEN-LAST:event_Buscar____ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Vehiculos atras = new Vehiculos();
        atras.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Avl_arboles_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Avl_arboles_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Avl_arboles_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Avl_arboles_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Avl_arboles_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar____;
    private javax.swing.JLabel Tiempo___;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton post____;
    private javax.swing.JButton pre___;
    private javax.swing.JTable tabla__avl___;
    private javax.swing.JButton ver__Arbol___;
    // End of variables declaration//GEN-END:variables
}
