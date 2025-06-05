
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
public class Lista_Multas {
     private Nodo_Multas cabeza;

    public Lista_Multas() {
        this.cabeza = null;
    }

    public void agregarMulta(String departamento, String placa, String fecha, String descripcion, int monto) {
        Nodo_Multas nueva = new Nodo_Multas(departamento, placa, fecha, descripcion, monto);

        if (cabeza == null) {
            cabeza = nueva;
            cabeza.Siguiente = cabeza;
            cabeza.anterio = cabeza;
        } else {
            Nodo_Multas ultimo = cabeza.anterio;
            ultimo.Siguiente = nueva;
            nueva.anterio = ultimo;
            nueva.Siguiente = cabeza;
            cabeza.anterio = nueva;
        }
    }

    public void cargarMultasDesdeCarpetas(String rutaPrincipal) {
        File carpeta = new File(rutaPrincipal);

        if (!carpeta.exists() || !carpeta.isDirectory()) return;

        File[] subcarpetas = carpeta.listFiles(File::isDirectory);
        if (subcarpetas == null) return;

        for (File sub : subcarpetas) {
            String nombreDepto = sub.getName();
            File archivo = new File(sub, nombreDepto + "_multas.txt");

            if (!archivo.exists()) continue;

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                br.readLine(); // Saltar encabezado

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length != 4) continue;

                    String placa = datos[0].trim();
                    String fecha = datos[1].trim();
                    String descripcion = datos[2].trim();
                    int monto = Integer.parseInt(datos[3].trim());

                    agregarMulta(nombreDepto, placa, fecha, descripcion, monto);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error en archivo " + archivo.getPath());
            }
        }
    }
    public boolean estaVacia() {
    return cabeza == null;
}

    public DefaultTableModel obtenerModeloTabla() {
        String[] columnas = {"Departamento", "Placa", "Fecha", "Descripción", "Monto"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        if (cabeza == null) return modelo;

        Nodo_Multas actual = cabeza;
        do {
            modelo.addRow(new Object[]{
                actual.departamento,
                actual.Placa,
                actual.fecha,
                actual.Descripcion,
                actual.monto
            });
            actual = actual.Siguiente;
        } while (actual != cabeza);

        return modelo;
    }

    public Nodo_Multas getCabeza() {
        return cabeza;
    }
    
    
    
    
    public Nodo_Multas buscarPorPlaca(String placaBuscada) {
    if (cabeza == null) {
        return null; // lista vacía
    }
    
    Nodo_Multas actual = cabeza;
    do {
        if (actual.Placa.equalsIgnoreCase(placaBuscada)) {
            return actual; // encontrada
        }
        actual = actual.Siguiente;
    } while (actual != cabeza);
    
    return null; // no encontrada
}
    
    
    
    public boolean eliminarPorPlaca(String placaBuscada) {
    if (cabeza == null) return false;

    Nodo_Multas actual = cabeza;

    do {
        if (actual.Placa.equalsIgnoreCase(placaBuscada)) {
            // Solo hay un nodo
            if (actual == cabeza && actual.Siguiente == cabeza) {
                cabeza = null;
            }
            // Nodo a eliminar es la cabeza pero hay más elementos
            else if (actual == cabeza) {
                Nodo_Multas ultimo = cabeza.anterio;
                cabeza = cabeza.Siguiente;
                cabeza.anterio = ultimo;
                ultimo.Siguiente = cabeza;
            }
            // Nodo intermedio o final
            else {
                actual.anterio.Siguiente = actual.Siguiente;
                actual.Siguiente.anterio = actual.anterio;
            }
            return true; // Eliminado exitosamente
        }
        actual = actual.Siguiente;
    } while (actual != cabeza);

    return false; // No se encontró
}

    
    
    public void guardarMultasEnArchivosExistentes(String rutaPrincipal) {
    if (cabeza == null) {
        System.out.println("La lista de multas está vacía.");
        return;
    }

    try {
        // Primero, recorremos la lista para obtener los departamentos únicos
        Nodo_Multas actual = cabeza;
        do {
            String departamento = actual.departamento;
            File carpeta = new File(rutaPrincipal + File.separator + departamento);
            File archivo = new File(carpeta, departamento + "_multas.txt");

            // Abrimos el archivo en modo de sobreescritura
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false));

            // Escribimos encabezado
            bw.write("Placa,Fecha,Descripcion,Monto");
            bw.newLine();

            // Recorremos de nuevo la lista, pero solo escribimos las multas de este departamento
            Nodo_Multas aux = cabeza;
            do {
                if (aux.departamento.equals(departamento)) {
                    bw.write(aux.Placa + "," + aux.fecha + "," + aux.Descripcion + "," + aux.monto);
                    bw.newLine();
                }
                aux = aux.Siguiente;
            } while (aux != cabeza);

            bw.close();
            System.out.println("Archivo de " + departamento + " sobrescrito correctamente.");

            // Saltamos al siguiente departamento distinto
            do {
                actual = actual.Siguiente;
            } while (actual != cabeza && actual.departamento.equals(departamento));

        } while (actual != cabeza);

    } catch (IOException e) {
        System.out.println("Error al guardar los archivos: " + e.getMessage());
    }
}
   
}