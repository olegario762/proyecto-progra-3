/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Nodo_Multas {
    String Placa, fecha, Descripcion;
    int monto;
    String departamento; 
    
    
    Nodo_Multas anterio;
    Nodo_Multas Siguiente;

    public Nodo_Multas(String departamento, String Placa, String fecha, String descripcion, int monto) {
         this.departamento= departamento;
        this.Placa = Placa;
        this.fecha = fecha;
        this.Descripcion = descripcion;
       
        this.monto = monto;
        this.anterio = null;
        this.Siguiente = null;
    }

    
    
    
    
    
    
    
}
