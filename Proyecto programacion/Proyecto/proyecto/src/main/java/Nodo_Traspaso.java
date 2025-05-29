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

    public Nodo_Traspaso anterior;
    public Nodo_Traspaso siguiente;

    public Nodo_Traspaso(String departamento, String placa, String dpiAnterior, String nombreAnterior, String fecha, String dpiNuevo, String nombreNuevo) {
        this.departamento= departamento; 
        this.placa = placa;
        this.dpiAnterior = dpiAnterior;
        this.nombreAnterior = nombreAnterior;
        this.fecha = fecha;
        this.dpiNuevo = dpiNuevo;
        this.nombreNuevo = nombreNuevo;
        this.anterior = null;
        this.siguiente = null;
    }

    
    
}
