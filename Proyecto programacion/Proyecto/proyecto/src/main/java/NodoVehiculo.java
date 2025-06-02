/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class NodoVehiculo {
    Vehiculo dato;
    NodoVehiculo hijoIzquierdo;
    NodoVehiculo hijoDerecho;

    public NodoVehiculo(Vehiculo dato) {
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
}
