/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Nodo_Doble {
    String Placa, fecha, Descripcion;
    int monto;
    String departamento; 
    
    
    Nodo_Doble anterio;
    Nodo_Doble Siguiente;

    public Nodo_Doble(String departamento, String Placa, String fecha, String descripcion, int monto) {
         this.departamento= departamento;
        this.Placa = Placa;
        this.fecha = fecha;
        this.Descripcion = descripcion;
       
        this.monto = monto;
        this.anterio = null;
        this.Siguiente = null;
    }

    
    
    
    
    
    
    
}
