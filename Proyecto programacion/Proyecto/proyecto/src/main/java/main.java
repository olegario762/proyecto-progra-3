
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class main {
    public static void main(String[] args) {
        
        DefaultTableModel modelo_ve = new DefaultTableModel();
        
        
        Lista_Vehiculos listaVehiculos = new Lista_Vehiculos();
        Lista_Traspasos listaTraspasos = new Lista_Traspasos();
        Lista_Doble list_multas = new Lista_Doble ();
        
       
        Importar_archivos_multas.cargarTraspasos(modelo_ve, listaTraspasos, listaVehiculos);
        Importar_archivos_multas.cargarTodosLosArchivos_multas(modelo_ve, list_multas, listaVehiculos);
        
        
        Vehiculos vehiculos = new Vehiculos();
        vehiculos.setVisible(true);
        
        
      
        
    }
    
    
}
