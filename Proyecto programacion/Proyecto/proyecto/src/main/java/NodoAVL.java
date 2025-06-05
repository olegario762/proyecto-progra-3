
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class NodoAVL {
    String placa;
    ArrayList<Vehiculo> vehiculos;
    NodoAVL izquierdo, derecho;
    int altura;

    public NodoAVL(String placa, Vehiculo vehiculo) {
        this.placa = placa;
        this.vehiculos = new ArrayList<>();
        this.vehiculos.add(vehiculo);
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

}
