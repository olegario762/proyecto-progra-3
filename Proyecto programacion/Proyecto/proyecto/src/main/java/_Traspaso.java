
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
public class _Traspaso extends javax.swing.JFrame {
    
    DefaultTableModel modeloVehiculos = new DefaultTableModel();
    DefaultTableModel modeloTraspasos = new DefaultTableModel();
   

  
    Lista_Traspasos listaTraspasos = new Lista_Traspasos();
    Lista_Multas list_multas = new Lista_Multas();
    

    public _Traspaso() {
        initComponents();
        
      
        
        
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
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
        Traspasos = new java.awt.Label();
        Fecha_tex = new javax.swing.JTextField();
        placa_tex = new javax.swing.JTextField();
        Dpi_tex = new javax.swing.JTextField();
        Nombre_tex = new javax.swing.JTextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        gaurdar_nodo = new javax.swing.JButton();
        Guardar_en_el_txt___ = new javax.swing.JButton();
        label5 = new java.awt.Label();
        Depa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Traspasos.setFont(new java.awt.Font("Perpetua Titling MT", 2, 48)); // NOI18N
        Traspasos.setText("Traspasos");
        jPanel1.add(Traspasos, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 320, 70));
        jPanel1.add(Fecha_tex, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 620, 50));
        jPanel1.add(placa_tex, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 610, 50));
        jPanel1.add(Dpi_tex, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 610, 50));
        jPanel1.add(Nombre_tex, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 610, 50));

        label1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 36)); // NOI18N
        label1.setName(""); // NOI18N
        label1.setText("Fecha");
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 140, -1));
        label1.getAccessibleContext().setAccessibleName("");

        label2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 36)); // NOI18N
        label2.setName(""); // NOI18N
        label2.setText("Placa");
        jPanel1.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 140, -1));

        label3.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 36)); // NOI18N
        label3.setName(""); // NOI18N
        label3.setText("Dpi");
        jPanel1.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 140, -1));

        label4.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 36)); // NOI18N
        label4.setName(""); // NOI18N
        label4.setText("Nombre");
        jPanel1.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 140, -1));

        gaurdar_nodo.setText("Realizar");
        gaurdar_nodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaurdar_nodoActionPerformed(evt);
            }
        });
        jPanel1.add(gaurdar_nodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 560, 170, 50));

        Guardar_en_el_txt___.setText("Guardar");
        Guardar_en_el_txt___.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar_en_el_txt___ActionPerformed(evt);
            }
        });
        jPanel1.add(Guardar_en_el_txt___, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, 160, 50));

        label5.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 36)); // NOI18N
        label5.setName(""); // NOI18N
        label5.setText("Fecha");
        jPanel1.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 140, -1));
        jPanel1.add(Depa, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 620, 50));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1282, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gaurdar_nodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaurdar_nodoActionPerformed
    

 

    }//GEN-LAST:event_gaurdar_nodoActionPerformed

    private void Guardar_en_el_txt___ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_en_el_txt___ActionPerformed
   
       
    }//GEN-LAST:event_Guardar_en_el_txt___ActionPerformed

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
            java.util.logging.Logger.getLogger(_Traspaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(_Traspaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(_Traspaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(_Traspaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new _Traspaso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Depa;
    private javax.swing.JTextField Dpi_tex;
    private javax.swing.JTextField Fecha_tex;
    private javax.swing.JButton Guardar_en_el_txt___;
    private javax.swing.JTextField Nombre_tex;
    private java.awt.Label Traspasos;
    private javax.swing.JButton gaurdar_nodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private javax.swing.JTextField placa_tex;
    // End of variables declaration//GEN-END:variables
}
