
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Pantallaagregarvehiculos extends javax.swing.JFrame {

    private VentanaPrincipal ventanaPrincipal;
    
    public Pantallaagregarvehiculos(VentanaPrincipal vp) {
        initComponents();
        
        this.ventanaPrincipal = vp;
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
        depa = new javax.swing.JTextField();
        plca1 = new javax.swing.JTextField();
        dpi1 = new javax.swing.JTextField();
        nomrbe1 = new javax.swing.JTextField();
        marca1 = new javax.swing.JTextField();
        modelo1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        realizar = new javax.swing.JButton();
        anios1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(depa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 420, 30));
        jPanel1.add(plca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 420, -1));
        jPanel1.add(dpi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 420, -1));
        jPanel1.add(nomrbe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 420, -1));
        jPanel1.add(marca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 420, -1));
        jPanel1.add(modelo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 420, -1));

        jLabel1.setText("Placa");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        jLabel2.setText("Dpi");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, -1));

        jLabel3.setText("Nombre");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        jLabel4.setText("Marca");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        jLabel5.setText("Modelo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, -1, -1));

        jLabel6.setText("Departamento");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, -1, -1));

        realizar.setText("realizar");
        realizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarActionPerformed(evt);
            }
        });
        jPanel1.add(realizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 410, -1, -1));
        jPanel1.add(anios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 410, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void realizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarActionPerformed
    
    String placa = plca1.getText().trim();
    String dpi = dpi1.getText().trim();
    String nombre = nomrbe1.getText().trim();
    String marca = marca1.getText().trim();
    String modelo = modelo1.getText().trim();
    String anioStr = anios1.getText().trim();
    String departamento = depa.getText().trim();

    if (placa.isEmpty() || dpi.isEmpty() || nombre.isEmpty() ||
        marca.isEmpty() || modelo.isEmpty() || anioStr.isEmpty() || departamento.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
    }

    int anio;
    try {
        anio = Integer.parseInt(anioStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El año debe ser un número válido.");
        return;
    }

    // Verificar si la placa ya existe
    if (ControladorSistema.arbolVehiculos.buscar(placa) != null ||
        ControladorSistema.arbolesbb.buscar(placa) != null) {
        JOptionPane.showMessageDialog(this, "Ya existe un vehículo con esta placa.");
        return;
    }

    // Aquí se colocan 0 multas y 0 traspasos explícitamente
    Vehiculo nuevo = new Vehiculo(departamento, placa, dpi, nombre, marca, modelo, anio, 0, 0);

    // Insertar en ambos árboles
    ControladorSistema.arbolVehiculos.insertar(nuevo);
    ControladorSistema.arbolesbb.insertar(nuevo);

    // Guardar en archivos por departamento
    ControladorSistema.arbolVehiculos.guardarVehiculosEnArchivos("C:\\Users\\Ixtamer\\Desktop\\archivo proyecto");

    // Actualizar interfaz
    ventanaPrincipal.actualizarTablaVehiculos();
    ventanaPrincipal.actualizarTablaVehiculosbb();

    JOptionPane.showMessageDialog(this, "Vehículo agregado correctamente.");
      ventanaPrincipal.setVisible(true);
    this.dispose();

     
    }//GEN-LAST:event_realizarActionPerformed

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
            java.util.logging.Logger.getLogger(Pantallaagregarvehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantallaagregarvehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantallaagregarvehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantallaagregarvehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal vp = new VentanaPrincipal();
            // Luego crea el formulario de agregar vehículos con esa instancia
            new Pantallaagregarvehiculos(vp).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anios1;
    private javax.swing.JTextField depa;
    private javax.swing.JTextField dpi1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField marca1;
    private javax.swing.JTextField modelo1;
    private javax.swing.JTextField nomrbe1;
    private javax.swing.JTextField plca1;
    private javax.swing.JButton realizar;
    // End of variables declaration//GEN-END:variables
}
