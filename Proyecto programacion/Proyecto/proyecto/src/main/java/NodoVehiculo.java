
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class NodoVehiculo {
       String placa;
    ArrayList<Vehiculo> vehiculos; // lista de vehículos con la misma placa
    NodoVehiculo hijoIzquierdo;
    NodoVehiculo hijoDerecho;

    public NodoVehiculo(String placa, Vehiculo vehiculo) {
        this.placa = placa;
        this.vehiculos = new ArrayList<>();
        this.vehiculos.add(vehiculo);
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}