
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

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

    public void agregarFinal(String placa, String dpiAnterior, String nombreAnterior, String fecha,
                              String dpiNuevo, String nombreNuevo, String departamento, Nodo_Vehiculo vehiculo) {
        Nodo_Traspaso nuevo = new Nodo_Traspaso(placa, dpiAnterior, nombreAnterior, fecha,
                                                dpiNuevo, nombreNuevo, departamento, vehiculo);
        if (cabeza == null) {
            cabeza = nuevo;
            colita = nuevo;
        } else {
            colita.siguiente = nuevo;
            nuevo.anterior = colita;
            colita = nuevo;
        }
    }

    public boolean estaVacia() {
        return cabeza == null;
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

    public boolean eliminarPorPlaca(String placaBuscada) {
        Nodo_Traspaso actual = cabeza;
        while (actual != null) {
            if (actual.placa.equalsIgnoreCase(placaBuscada)) {
                if (actual == cabeza && actual == colita) {
                    cabeza = null;
                    colita = null;
                } else if (actual == cabeza) {
                    cabeza = actual.siguiente;
                    if (cabeza != null) cabeza.anterior = null;
                } else if (actual == colita) {
                    colita = actual.anterior;
                    if (colita != null) colita.siguiente = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void mostrar() {
        Nodo_Traspaso aux = cabeza;
        while (aux != null) {
            System.out.println(aux.placa + " " + aux.dpiAnterior + " " + aux.nombreAnterior + " " + aux.fecha +
                               " " + aux.dpiNuevo + " " + aux.nombreNuevo);
            aux = aux.siguiente;
        }
    }

    public void cargarTraspasosDesdeCarpetas(String rutaPrincipal) {
        File carpeta = new File(rutaPrincipal);

        if (!carpeta.exists() || !carpeta.isDirectory()) return;

        File[] subcarpetas = carpeta.listFiles(File::isDirectory);
        if (subcarpetas == null) return;

        for (File sub : subcarpetas) {
            String nombreDepto = sub.getName();
            File archivo = new File(sub, nombreDepto + "_traspasos.txt");

            if (!archivo.exists()) continue;

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length != 6) continue;

                    String placa = datos[0].trim();
                    String dpiAnterior = datos[1].trim();
                    String nombreAnterior = datos[2].trim();
                    String fecha = datos[3].trim();
                    String dpiNuevo = datos[4].trim();
                    String nombreNuevo = datos[5].trim();

                    agregarFinal(placa, dpiAnterior, nombreAnterior, fecha, dpiNuevo, nombreNuevo, nombreDepto, null);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error en archivo " + archivo.getPath());
            }
        }
    }

    public void guardarTraspasosEnArchivosExistentes(String rutaPrincipal) {
        if (cabeza == null) {
            System.out.println("La lista de traspasos está vacía.");
            return;
        }

        try {
            Nodo_Traspaso actual = cabeza;

            while (actual != null) {
                String departamento = actual.departamento;
                File carpeta = new File(rutaPrincipal + File.separator + departamento);
                if (!carpeta.exists()) carpeta.mkdirs();

                File archivo = new File(carpeta, departamento + "_traspasos.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false));

                Nodo_Traspaso aux = cabeza;
                while (aux != null) {
                    if (aux.departamento.equals(departamento)) {
                        bw.write(aux.placa + "," + aux.dpiAnterior + "," + aux.nombreAnterior + "," +
                                 aux.fecha + "," + aux.dpiNuevo + "," + aux.nombreNuevo);
                        bw.newLine();
                    }
                    aux = aux.siguiente;
                }

                bw.close();
                System.out.println("Archivo de " + departamento + " sobrescrito correctamente.");

                // saltar al siguiente departamento distinto
                while (actual != null && actual.departamento.equals(departamento)) {
                    actual = actual.siguiente;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar los archivos: " + e.getMessage());
        }
    }

    public DefaultTableModel obtenerModeloTabla() {
        String[] columnas = {"Placa", "DPI Anterior", "Nombre Anterior", "Fecha", "DPI Nuevo", "Nombre Nuevo", "Departamento"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        Nodo_Traspaso actual = cabeza;
        while (actual != null) {
            modelo.addRow(new Object[]{
                actual.placa,
                actual.dpiAnterior,
                actual.nombreAnterior,
                actual.fecha,
                actual.dpiNuevo,
                actual.nombreNuevo,
                actual.departamento
            });
            actual = actual.siguiente;
        }

        return modelo;
    }
}