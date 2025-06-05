
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class ArbolAVL {
     private NodoAVL raiz;

    public void agregarVehiculo(String depto, String placa, String dpi, String nombre,
                                String marca, String modelo, int anio, int multas, int traspasos) {
        Vehiculo vehiculo = new Vehiculo(depto, placa, dpi, nombre, marca, modelo, anio, multas, traspasos);
        raiz = insertar(raiz, placa, vehiculo);
    }

    private int altura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    private int getBalance(NodoAVL n) {
        return (n == null) ? 0 : altura(n.izquierdo) - altura(n.derecho);
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    private NodoAVL insertar(NodoAVL nodo, String placa, Vehiculo vehiculo) {
        if (nodo == null)
            return new NodoAVL(placa, vehiculo);

        int cmp = placa.compareTo(nodo.placa);
        if (cmp < 0) {
            nodo.izquierdo = insertar(nodo.izquierdo, placa, vehiculo);
        } else if (cmp > 0) {
            nodo.derecho = insertar(nodo.derecho, placa, vehiculo);
        } else {
            nodo.vehiculos.add(vehiculo);
            return nodo;
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        int balance = getBalance(nodo);

        // Rotaciones
        if (balance > 1 && placa.compareTo(nodo.izquierdo.placa) < 0)
            return rotarDerecha(nodo);
        if (balance < -1 && placa.compareTo(nodo.derecho.placa) > 0)
            return rotarIzquierda(nodo);
        if (balance > 1 && placa.compareTo(nodo.izquierdo.placa) > 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && placa.compareTo(nodo.derecho.placa) < 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public DefaultTableModel cargarVehiculosDesdeCarpetas(String rutaPrincipal) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Departamento", "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos"});

        File carpeta = new File(rutaPrincipal);
        if (!carpeta.exists() || !carpeta.isDirectory()) return modelo;

        File[] subcarpetas = carpeta.listFiles(File::isDirectory);
        if (subcarpetas == null) return modelo;

        for (File sub : subcarpetas) {
            String nombreDepto = sub.getName();
            File archivo = new File(sub, nombreDepto + "_vehiculos.txt");

            if (!archivo.exists()) continue;

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length != 8) continue;

                    String placa = datos[0].trim();
                    String dpi = datos[1].trim();
                    String nombre = datos[2].trim();
                    String marca = datos[3].trim();
                    String modeloStr = datos[4].trim();
                    int anio = Integer.parseInt(datos[5].trim());
                    int multas = Integer.parseInt(datos[6].trim());
                    int traspasos = Integer.parseInt(datos[7].trim());

                    agregarVehiculo(nombreDepto, placa, dpi, nombre, marca, modeloStr, anio, multas, traspasos);
                    modelo.addRow(new Object[]{nombreDepto, placa, dpi, nombre, marca, modeloStr, anio, multas, traspasos});
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return modelo;
    }
    public ArrayList<Vehiculo> recorridoPreorden() {
    ArrayList<Vehiculo> lista = new ArrayList<>();
    preorden(raiz, lista);
    return lista;
    }

    private void preorden(NodoAVL nodo, ArrayList<Vehiculo> lista) {
        if (nodo != null) {
            lista.addAll(nodo.vehiculos); // Primero el nodo actual
            preorden(nodo.izquierdo, lista); // Luego el subárbol izquierdo
            preorden(nodo.derecho, lista);   // Luego el subárbol derecho
        }
    }
    public ArrayList<Vehiculo> recorridoPostorden() {
    ArrayList<Vehiculo> lista = new ArrayList<>();
    postorden(raiz, lista);
    return lista;
}

private void postorden(NodoAVL nodo, ArrayList<Vehiculo> lista) {
    if (nodo != null) {
        postorden(nodo.izquierdo, lista); // Primero el subárbol izquierdo
        postorden(nodo.derecho, lista);   // Luego el subárbol derecho
        lista.addAll(nodo.vehiculos);     // Por último el nodo actual
    }
}



    public ArrayList<Vehiculo> recorridoInorden() {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        inorden(raiz, lista);
        return lista;
    }

    private void inorden(NodoAVL nodo, ArrayList<Vehiculo> lista) {
        if (nodo != null) {
            inorden(nodo.izquierdo, lista);
            lista.addAll(nodo.vehiculos);
            inorden(nodo.derecho, lista);
        }
    }
    public Vehiculo buscarPorPlacaConTiempo(String placaABuscar) {
    long inicio = System.nanoTime(); // Comenzar a contar el tiempo

    NodoAVL nodo = buscarNodoPorPlaca(raiz, placaABuscar);

    long fin = System.nanoTime(); // Fin del tiempo

    long duracion = fin - inicio;
    System.out.println("Tiempo de búsqueda: " + duracion + " nanosegundos (" + (duracion / 1_000_000.0) + " milisegundos)");

    if (nodo != null && !nodo.vehiculos.isEmpty()) {
        return nodo.vehiculos.get(0); // Devolver el primer vehículo asociado a la placa
    } else {
        return null; // No se encontró la placa
    }
}

private NodoAVL buscarNodoPorPlaca(NodoAVL nodo, String placa) {
    if (nodo == null) return null;

    int cmp = placa.compareTo(nodo.placa);
    if (cmp == 0) {
        return nodo;
    } else if (cmp < 0) {
        return buscarNodoPorPlaca(nodo.izquierdo, placa);
    } else {
        return buscarNodoPorPlaca(nodo.derecho, placa);
    }
}


    public String generarDOT() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph AVL {\nnode [shape=box];\n");
        generarDOTRec(raiz, dot);
        dot.append("}\n");
        return dot.toString();
    }

    private void generarDOTRec(NodoAVL nodo, StringBuilder dot) {
        if (nodo != null) {
            dot.append("\"").append(nodo.placa).append("\" [label=\"").append(nodo.placa).append("\"];\n");

            if (nodo.izquierdo != null) {
                dot.append("\"").append(nodo.placa).append("\" -> \"").append(nodo.izquierdo.placa).append("\";\n");
                generarDOTRec(nodo.izquierdo, dot);
            }

            if (nodo.derecho != null) {
                dot.append("\"").append(nodo.placa).append("\" -> \"").append(nodo.derecho.placa).append("\";\n");
                generarDOTRec(nodo.derecho, dot);
            }
        }
    }
}