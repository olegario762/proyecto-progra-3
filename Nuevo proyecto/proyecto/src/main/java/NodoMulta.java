/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class NodoMulta {
    String departamento, placa, fecha, descripcion;
    double monto;
    NodoMulta siguiente, anterior;

    public NodoMulta(String departamento, String placa, String fecha, String descripcion, double monto) {
        this.departamento = departamento;
        this.placa = placa;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getPlaca() {
        return placa;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public NodoMulta getSiguiente() {
        return siguiente;
    }

    public NodoMulta getAnterior() {
        return anterior;
    }
    

    @Override
    public String toString() {
        return placa + "," + fecha + "," + descripcion + "," + monto;
    }

    
    
}
