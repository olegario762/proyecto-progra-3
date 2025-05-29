/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Lista_Traspasos {
    public Nodo_Traspaso cabeza;
    public Nodo_Traspaso colita;

    public Lista_Traspasos() {
        this.cabeza = null;
        this.colita = null;
    }

    public Nodo_Traspaso getCabeza() {
        return cabeza;
    }
    

    public void agregarFinal(String departamento, String placa, String dpiAnterior, String nombreAnterior, String fecha, String dpiNuevo, String nombreNuevo) {
        Nodo_Traspaso nuevo = new Nodo_Traspaso( departamento, placa, dpiAnterior, nombreAnterior, fecha, dpiNuevo, nombreNuevo);
        if (cabeza == null) {
            cabeza = nuevo;
            colita = nuevo;
        } else {
            colita.siguiente= nuevo;
            nuevo.anterior = colita;
            nuevo.siguiente = null;
            colita = nuevo;
        }
    }
    public void vaciar() {
        cabeza = null;
        colita = null;
    }
    
        
    
    
     public Nodo_Traspaso buscarPorPlaca(String placaBuscada) {
        Nodo_Traspaso actual = cabeza;

        while (actual != null) {
            if (actual.placa.equalsIgnoreCase(placaBuscada)) {
                return actual; 
            }
            actual = actual.siguiente;
        }

        return null; 
    }


    public void mostrar() {
        Nodo_Traspaso aux = cabeza;
        while (aux != null) {
            System.out.println(aux.placa + " " + aux.dpiAnterior + " " + aux.nombreAnterior + " " + aux.fecha + " " + aux.dpiNuevo + " " + aux.nombreNuevo);
            aux = aux.siguiente;
        }
    }

    
    
}
