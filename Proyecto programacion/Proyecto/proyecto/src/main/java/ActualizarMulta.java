/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class ActualizarMulta {
    public static boolean actualizarMulta(Lista_Multas lista, String departamento, String placa, 
                                          String nuevaFecha, String nuevaDescripcion, int nuevoMonto) {
        Nodo_Multas actual = lista.getCabeza();
        if (actual == null) return false;

        do {
            if (actual.departamento.equalsIgnoreCase(departamento) && actual.Placa.equalsIgnoreCase(placa)) {
                // Se encontró la multa, ahora actualizamos los campos
                actual.fecha = nuevaFecha;
                actual.Descripcion = nuevaDescripcion;
                actual.monto = nuevoMonto;
                return true; // Se actualizó exitosamente
            }
            actual = actual.Siguiente;
        } while (actual != lista.getCabeza());

        return false; // No se encontró la multa
    }

    
}
