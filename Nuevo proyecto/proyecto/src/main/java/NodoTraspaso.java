/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class NodoTraspaso {
    String placa;
    String dpiAnterior;
    String nombreAnterior;
    String fecha;
    String dpiNuevo;
    String nombreNuevo;
    String departamento;

    NodoTraspaso siguiente;

    public NodoTraspaso(String placa, String dpiAnterior, String nombreAnterior, String fecha,
                        String dpiNuevo, String nombreNuevo, String departamento) {
        this.placa = placa;
        this.dpiAnterior = dpiAnterior;
        this.nombreAnterior = nombreAnterior;
        this.fecha = fecha;
        this.dpiNuevo = dpiNuevo;
        this.nombreNuevo = nombreNuevo;
        this.departamento = departamento;
        this.siguiente = null;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDpiAnterior() {
        return dpiAnterior;
    }

    public String getNombreAnterior() {
        return nombreAnterior;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDpiNuevo() {
        return dpiNuevo;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public NodoTraspaso getSiguiente() {
        return siguiente;
    }
    

    @Override
    public String toString() {
        return placa + "," + dpiAnterior + "," + nombreAnterior + "," + fecha + "," +
               dpiNuevo + "," + nombreNuevo ;
    }
}
