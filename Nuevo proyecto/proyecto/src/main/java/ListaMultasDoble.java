
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class ListaMultasDoble {
    NodoMulta cabeza, cola;

    public void agregarMulta(String departamento, String placa, String fecha, String descripcion, double monto) {
        NodoMulta nueva = new NodoMulta(departamento, placa, fecha, descripcion, monto);
        if (cabeza == null) {
            cabeza = cola = nueva;
        } else {
            cola.siguiente = nueva;
            nueva.anterior = cola;
            cola = nueva;
        }
    }

    public void guardarMultasEnArchivosExistentes(String rutaPrincipal) {
    if (cabeza == null) return;

    // Usamos un conjunto para evitar procesar el mismo departamento más de una vez
    HashSet<String> departamentosProcesados = new HashSet<>();
    NodoMulta actual = cabeza;

    while (actual != null) {
        String departamento = actual.getDepartamento();

        if (!departamentosProcesados.contains(departamento)) {
            departamentosProcesados.add(departamento);

            // Crear el archivo del departamento
            File archivo = new File(rutaPrincipal + File.separator + departamento, departamento + "_multas.txt");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                NodoMulta temp = cabeza;
                while (temp != null) {
                    if (temp.getDepartamento().equals(departamento)) {
                        bw.write(temp.toString());
                        bw.newLine();
                    }
                    temp = temp.siguiente;
                }
            } catch (IOException e) {
                System.out.println("Error al guardar multas de " + departamento + ": " + e.getMessage());
            }
        }

        actual = actual.siguiente;
    }
}
    
    

    public void cargarMultasDesdeCarpetas(String rutaPrincipal) {
    File carpetaPrincipal = new File(rutaPrincipal);
    if (!carpetaPrincipal.exists() || !carpetaPrincipal.isDirectory()) return;

    File[] subcarpetas = carpetaPrincipal.listFiles(File::isDirectory);
    if (subcarpetas == null) return;

    for (File subcarpeta : subcarpetas) {
        String nombreDepto = subcarpeta.getName();
        File archivoMultas = new File(subcarpeta, nombreDepto + "_multas.txt");

        if (!archivoMultas.exists()) continue;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoMultas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 4) continue;

                String placa = datos[0].trim();
                String fecha = datos[1].trim();
                String descripcion = datos[2].trim();
                double monto = Double.parseDouble(datos[3].trim());

                this.agregarMulta(nombreDepto, placa, fecha, descripcion, monto);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar multas de " + nombreDepto + ": " + e.getMessage());
        }
    }
}
    

    

    public int contarMultas() {
        int conteo = 0;
        NodoMulta temp = cabeza;
        while (temp != null) {
            conteo++;
            temp = temp.siguiente;
        }
        return conteo;
    }
    public void eliminarMultaDeArchivo(String rutaPrincipal, NodoMulta multaEliminar) {
    File archivo = new File(rutaPrincipal + File.separator + multaEliminar.getDepartamento(), multaEliminar.getDepartamento() + "_multas.txt");
    if (!archivo.exists()) return;

    File archivoTemp = new File(archivo.getParent(), "temp_multas.txt");

    try (BufferedReader br = new BufferedReader(new FileReader(archivo));
         BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length != 4) continue;

            String placa = datos[0].trim();
            String fecha = datos[1].trim();

            if (!placa.equalsIgnoreCase(multaEliminar.getPlaca()) || !fecha.equals(multaEliminar.getFecha())) {
                bw.write(linea);
                bw.newLine();
            }
        }
    } catch (IOException e) {
        System.out.println("Error al eliminar multa del archivo: " + e.getMessage());
        return;
    }

    if (archivo.delete()) {
        archivoTemp.renameTo(archivo);
    }
}

    



    

    public void mostrarMultas() {
        NodoMulta temp = cabeza;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.siguiente;
        }
    }
    
    
}
