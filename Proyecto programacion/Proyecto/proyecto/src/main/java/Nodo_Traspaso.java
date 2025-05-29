/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Nodo_Traspaso {
    public String placa;
    public String dpiAnterior;
    public String nombreAnterior;
    public String fecha;
    public String dpiNuevo;
    public String nombreNuevo;
    String departamento; 
    
    
    Nodo_Vehiculo vehiculo;

    public Nodo_Traspaso anterior;
    public Nodo_Traspaso siguiente;

    public Nodo_Traspaso( String placa, String dpiAnterior, String nombreAnterior, String fecha, String dpiNuevo, String nombreNuevo, String departamento,Nodo_Vehiculo vehiculo) {
        
        this.placa = placa;
        this.dpiAnterior = dpiAnterior;
        this.nombreAnterior = nombreAnterior;
        this.fecha = fecha;
        this.dpiNuevo = dpiNuevo;
        this.nombreNuevo = nombreNuevo;
        this.departamento= departamento; 
        this.vehiculo = vehiculo;
        this.anterior = null;
        this.siguiente = null;
        //String placa, String dpiAnterior, String nombreAnterior, String fecha, String dpiNuevo, String nombreNuevo
    }

    
    
}
