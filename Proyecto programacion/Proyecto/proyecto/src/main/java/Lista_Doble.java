/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Lista_Doble {
    public Nodo_Doble cabeza;
    public Nodo_Doble colita;

    public Lista_Doble() {
        this.cabeza = null;
        this.colita = null;
    }

    public Nodo_Doble getCabeza() {
        return cabeza;
    }
    
    
   
    
    public void insetarfinal(String departamento, String placa, String fecha, String descripcion, int monto){
         Nodo_Doble nuevo = new Nodo_Doble(departamento,placa, fecha, descripcion, monto );
        
        if(cabeza == null){
            cabeza = nuevo;
            colita = nuevo;
        } else {
            colita.Siguiente = nuevo;
            nuevo.anterio = colita;
            nuevo.Siguiente = null;
            colita = nuevo;
        }
      
        
    }
    
    
    public int calcularTotalMultas() {
        int total = 0;
        Nodo_Doble actual = cabeza;
        while (actual != null) {
            total += actual.monto;
            actual = actual.Siguiente;
        }
        return total;
    }
        public void vaciar() {
        cabeza = null;
        colita = null;
        
                
        
    
    
        }
        
        
        
        
        ////////
        
        
        public void eliminarPorPlaca(String placaBuscada) {
        Nodo_Doble actual = cabeza;

        while (actual != null) {
            if (actual.Placa.equalsIgnoreCase(placaBuscada)) {
               
                if (actual == cabeza) {
                    cabeza = actual.Siguiente;
                    if (cabeza != null) {
                        cabeza.anterio = null;
                    } else {
                        colita = null; 
                    }

                // Nodo al final
                } else if (actual == colita) {
                    colita = actual.anterio;
                    colita.Siguiente = null;

                // Nodo en medio
                } else {
                    actual.anterio.Siguiente = actual.Siguiente;
                    actual.Siguiente.anterio = actual.anterio;
                }

                System.out.println("Nodo con placa " + placaBuscada + " eliminado.");
                return;
            }

            actual = actual.Siguiente;
        }

        System.out.println("Placa no encontrada en la lista.");
    }

   
    public Nodo_Doble buscarPorPlaca(String placaBuscada) {
        Nodo_Doble actual = cabeza;

        while (actual != null) {
            if (actual.Placa.equalsIgnoreCase(placaBuscada)) {
                return actual; 
            }
            actual = actual.Siguiente;
        }

        return null; 
    }

        // Recorrer e imprimir (solo para depuración)
    public void imprimir() {
        Nodo_Doble aux = cabeza;
        while (aux != null) {
            System.out.println(aux.Placa + " | " + aux.fecha + " | " + aux.Descripcion + " | " + aux.monto);
            aux = aux.Siguiente;
        }
    }
        
}
