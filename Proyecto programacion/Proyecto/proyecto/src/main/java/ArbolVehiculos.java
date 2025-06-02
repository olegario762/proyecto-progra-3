
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class ArbolVehiculos {
    private NodoVehiculo raiz;

    public ArbolVehiculos() {
        raiz = null;
    }

    public void agregarNodo(Vehiculo v) {
        NodoVehiculo nuevo = new NodoVehiculo(v);

        if (raiz == null) {
            raiz = nuevo;
            return;
        }

        NodoVehiculo auxiliar = raiz;
        NodoVehiculo padre;

        while (true) {
            padre = auxiliar;

            int cmp = v.getPlaca().compareTo(auxiliar.dato.getPlaca());

            if (cmp < 0) {
                auxiliar = auxiliar.hijoIzquierdo;
                if (auxiliar == null) {
                    padre.hijoIzquierdo = nuevo;
                    return;
                }
            } else if (cmp > 0) {
                auxiliar = auxiliar.hijoDerecho;
                if (auxiliar == null) {
                    padre.hijoDerecho = nuevo;
                    return;
                }
            } else {
                System.out.println("Vehículo con placa " + v.getPlaca() + " ya existe.");
                return;
            }
        }
    }

    // ---------- Recorrido InOrden ----------
    public void imprimirInOrden() {
        imprimirInOrdenRec(raiz);
    }

    private void imprimirInOrdenRec(NodoVehiculo nodo) {
        if (nodo != null) {
            imprimirInOrdenRec(nodo.hijoIzquierdo);
            System.out.println(nodo.dato);
            imprimirInOrdenRec(nodo.hijoDerecho);
        }
    }

    // ---------- Recorrido PreOrden ----------
    public void imprimirPreOrden() {
        System.out.println("Recorrido PreOrden:");
        imprimirPreOrdenRec(raiz);
    }

    private void imprimirPreOrdenRec(NodoVehiculo nodo) {
        if (nodo != null) {
            System.out.println(nodo.dato);
            imprimirPreOrdenRec(nodo.hijoIzquierdo);
            imprimirPreOrdenRec(nodo.hijoDerecho);
        }
    }

    // ---------- Recorrido PostOrden ----------
    public void imprimirPostOrden() {
        System.out.println("Recorrido PostOrden:");
        imprimirPostOrdenRec(raiz);
    }

    private void imprimirPostOrdenRec(NodoVehiculo nodo) {
        if (nodo != null) {
            imprimirPostOrdenRec(nodo.hijoIzquierdo);
            imprimirPostOrdenRec(nodo.hijoDerecho);
            System.out.println(nodo.dato);
        }
    }

    // ---------- Buscar por placa ----------
    public Vehiculo buscarPorPlaca(String placa) {
        return buscarRecursivo(raiz, placa);
    }

    private Vehiculo buscarRecursivo(NodoVehiculo nodo, String placa) {
        if (nodo == null) {
            return null;
        }

        int cmp = placa.compareTo(nodo.dato.getPlaca());
        if (cmp == 0) {
            return nodo.dato;
        } else if (cmp < 0) {
            return buscarRecursivo(nodo.hijoIzquierdo, placa);
        } else {
            return buscarRecursivo(nodo.hijoDerecho, placa);
        }
    }

    // ---------- NUEVO: Retornar lista PreOrden ----------
    public ArrayList<Vehiculo> obtenerListaPreOrden() {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        llenarListaPreOrden(raiz, lista);
        return lista;
    }

    private void llenarListaPreOrden(NodoVehiculo nodo, ArrayList<Vehiculo> lista) {
        if (nodo != null) {
            lista.add(nodo.dato); // Raíz
            llenarListaPreOrden(nodo.hijoIzquierdo, lista); // Izquierda
            llenarListaPreOrden(nodo.hijoDerecho, lista);   // Derecha
        }
    }

}
    
    
       