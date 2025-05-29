/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Lista_Vehiculos {
    public Nodo_Vehiculo cabeza;
    public Nodo_Vehiculo colita;

    public Lista_Vehiculos() {
        this.cabeza = null;
        this.colita = null;
    }

    public Nodo_Vehiculo getCabeza() {
        return cabeza;
    }
    
    
    
    
    
    

    public void agregarFinal( String placa, String dpi, String nombre, String marca, String modelo, int anio, int cantidadMultas, int cantidadTraspasos,String departamento) {
        Nodo_Vehiculo nuevo = new Nodo_Vehiculo( placa, dpi, nombre, marca, modelo, anio, cantidadMultas, cantidadTraspasos, departamento);
        if (cabeza == null) {
            cabeza = nuevo;
            colita = nuevo;
        } else {
            colita.siguiente = nuevo;
            nuevo.anterior = colita;
            colita = nuevo;
        }
    }
    
    public void vaciar() {
        cabeza = null;
        colita = null;
    }
    
    
   
    
    
    
    
    public void eliminarPorPlaca(String placaBuscada) {
        Nodo_Vehiculo actual = cabeza;

        while (actual != null) {
            if (actual.placa.equalsIgnoreCase(placaBuscada)) {
               
                if (actual == cabeza) {
                    cabeza = actual.siguiente;
                    if (cabeza != null) {
                        cabeza.anterior = null;
                    } else {
                        colita = null; 
                    }

                // Nodo al final
                } else if (actual == colita) {
                    colita = actual.anterior;
                    colita.siguiente = null;

                // Nodo en medio
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }

                System.out.println("Nodo con placa " + placaBuscada + " eliminado.");
                return;
            }

            actual = actual.siguiente;
        }

        System.out.println("Placa no encontrada en la lista.");
    }
    
    
    
    
    
    
    
    

    public Nodo_Vehiculo buscarPorPlaca(String placa) {
        Nodo_Vehiculo actual = cabeza;
        while (actual != null) {
            if (actual.placa.trim().equalsIgnoreCase(placa.trim())) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    /*   */
   

    public void mostrar() {
        Nodo_Vehiculo aux = cabeza;
        while (aux != null) {
            System.out.println(aux.placa + " - " + aux.nombre + " - " + aux.marca + " - " + aux.modelo + " - " + aux.anio);
            aux = aux.siguiente;
        }
    }

 

    
    
}
