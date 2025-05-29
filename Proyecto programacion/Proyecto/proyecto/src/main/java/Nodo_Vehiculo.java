/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Nodo_Vehiculo {
    public String placa;
    public String dpi;
    public String nombre;
    public String marca;
    public String modelo;
    public int anio;
    String departamento; 
    

    
    
    public int cantidadMultas;
    public int cantidadTraspasos;

    public Nodo_Vehiculo anterior;
    public Nodo_Vehiculo siguiente;

    // Listas internas
   Lista_Doble multas;
   Lista_Traspasos traspaso ;

    public Nodo_Vehiculo( String placa, String dpi, String nombre, String marca, String modelo, int anio, int cantidadMultas, int cantidadTraspasos,String departamento) {
    
        this.placa = placa;
        this.dpi = dpi;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
         this.departamento= departamento; 
        this.cantidadMultas = cantidadMultas;
        this.cantidadTraspasos=cantidadTraspasos;
      
        
      
        
        this.multas = new Lista_Doble();
        this.traspaso = new Lista_Traspasos();
        
        
        
        
        
        
        this.anterior = null;
        this.siguiente =null;
        
    }

    public Lista_Doble getMultas() {
        return multas;
    }

    public Lista_Traspasos getTraspaso() {
        return traspaso;
    }
    
   

    

    
    
}
