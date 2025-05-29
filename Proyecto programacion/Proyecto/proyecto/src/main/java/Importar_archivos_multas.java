
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
public class Importar_archivos_multas {
    
    public static void cargarTodosLosArchivos(DefaultTableModel modelo_multas, Lista_Doble list_multas, Lista_Vehiculos listaVehiculos) {
        // Lista de nombres de departamentos
        String[] departamentos = {
            "Antigua_Guatemala", "Guatemala", "Huehuetenango", "Suchitepequez",
            "San_Marcos", "Quetzaltenango", "Peten", "Escuintla", "Chiquimula", "Chimaltenango"
        };

        // Crear arreglo de archivos
        File[] archivos = new File[departamentos.length];
        for (int i = 0; i < departamentos.length; i++) {
            archivos[i] = new File("C:\\Users\\Ixtamer\\Desktop\\Proyecto programacion\\Proyecto\\proyecto\\src\\main\\java\\"
                    + departamentos[i] + "\\" + departamentos[i] + "_multas.txt");
        }

        // Leer cada archivo
        for (File archivo : archivos) {
            if (!archivo.exists()) {
                System.out.println("Archivo no encontrado: " + archivo.getPath());
                continue;
            }

            String departamento = archivo.getParentFile().getName(); // Nombre del departamento

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");

                    if (datos.length == 4) {
                        if (datos[3].trim().equalsIgnoreCase("Monto")) {
                            continue; // Saltar encabezado
                        }

                        String placa = datos[0].trim();
                        String fecha = datos[1].trim();
                        String descripcion = datos[2].trim();
                        int monto;

                        try {
                            monto = Integer.parseInt(datos[3].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Error al convertir monto: " + datos[3]);
                            continue;
                        }

                        // Agregar multa al vehículo si existe
                        Nodo_Vehiculo nodo = listaVehiculos.buscarPorPlaca(placa);
                        if (nodo != null) {
                            nodo.multas.insetarfinal(departamento, placa, fecha, descripcion, monto);
                        }

                        // Agregar multa a la lista global
                        list_multas.insetarfinal(departamento, placa, fecha, descripcion, monto);

                        // Agregar multa a la tabla
                        modelo_multas.addRow(new Object[]{departamento, placa, fecha, descripcion, monto});
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer archivo: " + archivo.getPath());
            }
        }
    }

        
    public static void cargarTraspasos(DefaultTableModel modelo_traspasos, Lista_Traspasos listaTraspasos, Lista_Vehiculos listaVehiculos) {
        // Lista de nombres de departamentos
        String[] departamentos = {
            "Antigua_Guatemala", "Guatemala", "Huehuetenango", "Suchitepequez",
            "San_Marcos", "Quetzaltenango", "Peten", "Escuintla", "Chiquimula", "Chimaltenango"
        };

        // Crear arreglo de archivos
        File[] archivos = new File[departamentos.length];
        for (int i = 0; i < departamentos.length; i++) {
            archivos[i] = new File("C:\\Users\\Ixtamer\\Desktop\\Proyecto programacion\\Proyecto\\proyecto\\src\\main\\java\\"
                    + departamentos[i] + "\\" + departamentos[i] + "_traspasos.txt");
        }

        // Leer cada archivo
        for (File archivo : archivos) {
            if (!archivo.exists()) {
                System.out.println("Archivo no encontrado: " + archivo.getPath());
                continue;
            }

            String departamento = archivo.getParentFile().getName(); // Nombre del departamento

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;

                // Leer línea por línea
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");

                    // Validar cantidad de columnas
                    if (datos.length == 6) {
                        if (datos[1].trim().equalsIgnoreCase("DPI_ANTERIOR")) {
                            continue; // Saltar encabezado
                        }

                        String placa = datos[0].trim();
                        String dpiAnterior = datos[1].trim();
                        String nombreAnterior = datos[2].trim();
                        String fecha = datos[3].trim();
                        String dpiNuevo = datos[4].trim();
                        String nombreNuevo = datos[5].trim();

                        // Agregar al vehículo si existe
                        Nodo_Vehiculo nodo = listaVehiculos.buscarPorPlaca(placa);
                        if (nodo != null) {
                            nodo.traspaso.agregarFinal(departamento, placa, dpiAnterior, nombreAnterior, fecha, dpiNuevo, nombreNuevo);
                        }

                        // Agregar a la lista global
                        listaTraspasos.agregarFinal(departamento, placa, dpiAnterior, nombreAnterior, fecha, dpiNuevo, nombreNuevo);

                        // Agregar a la tabla
                        modelo_traspasos.addRow(new Object[]{
                            departamento, placa, dpiAnterior, nombreAnterior, fecha, dpiNuevo, nombreNuevo
                        });
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer archivo: " + archivo.getPath());
            }
        }
    }

   public static void cargarVehiculos(DefaultTableModel modelo_ve, Lista_Vehiculos listaVehiculos) {
        // Lista de departamentos
        String[] departamentos = {
            "Antigua_Guatemala", "Guatemala", "Huehuetenango", "Suchitepequez",
            "San_Marcos", "Quetzaltenango", "Peten", "Escuintla", "Chiquimula", "Chimaltenango"
        };

        // Crear arreglo de archivos
        File[] archivos = new File[departamentos.length];
        for (int i = 0; i < departamentos.length; i++) {
            archivos[i] = new File("C:\\Users\\Ixtamer\\Desktop\\Proyecto programacion\\Proyecto\\proyecto\\src\\main\\java\\"
                    + departamentos[i] + "\\" + departamentos[i] + "_vehiculos.txt");
        }

        // Leer cada archivo
        for (File archivo : archivos) {
            if (!archivo.exists()) {
                System.out.println("Archivo no encontrado: " + archivo.getPath());
                continue;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                String departamento = archivo.getParentFile().getName();

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");

                    if (datos.length == 8) {
                        if (datos[1].trim().equalsIgnoreCase("DPI")) {
                            continue; // Saltar encabezado
                        }

                        String placa = datos[0].trim();
                        String dpi = datos[1].trim();
                        String nombre = datos[2].trim();
                        String marca = datos[3].trim();
                        String modelo = datos[4].trim();
                        int anio;
                        int multas;
                        int traspasos;

                        try {
                            anio = Integer.parseInt(datos[5].trim());
                            multas = Integer.parseInt(datos[6].trim());
                            traspasos = Integer.parseInt(datos[7].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Error al convertir datos numéricos en archivo: " + archivo.getPath());
                            continue;
                        }

                        // Insertar en lista
                        listaVehiculos.agregarFinal(departamento, placa, dpi, nombre, marca, modelo, anio, multas, traspasos);

                        // Agregar a la tabla
                        modelo_ve.addRow(new Object[]{
                            departamento, placa, dpi, nombre, marca, modelo, anio, multas, traspasos
                        });
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer archivo: " + archivo.getPath());
            }
        }
    }
   public static void guardarTodosLosArchivos(Lista_Doble list_multas) {
        
        String[] departamentos = {
            "Antigua_Guatemala", "Guatemala", "Huehuetenango", "Suchitepequez",
            "San_Marcos", "Quetzaltenango", "Peten", "Escuintla", "Chiquimula", "Chimaltenango"
        };

        // Crear un BufferedWriter por departamento
        BufferedWriter[] escritores = new BufferedWriter[departamentos.length];

        try {
       
            for (int i = 0; i < departamentos.length; i++) {
                File archivo = new File("C:\\Users\\Ixtamer\\Desktop\\Proyecto programacion\\Proyecto\\proyecto\\src\\main\\java\\"
                        + departamentos[i] + "\\" + departamentos[i] + "_multas.txt");

                escritores[i] = new BufferedWriter(new FileWriter(archivo, false));
                // Escribir encabezado
                escritores[i].write("Placa,Fecha,Descripcion,Monto");
                escritores[i].newLine();
            }

            // Recorrer la lista de multas
            Nodo_Doble actual = list_multas.cabeza;
            while (actual != null) {
                String departamento = actual.departamento.trim();

                // Encontrar el índice del departamento
                int index = -1;
                for (int i = 0; i < departamentos.length; i++) {
                    if (departamentos[i].equalsIgnoreCase(departamento)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1 && escritores[index] != null) {
                    escritores[index].write(actual.Placa + "," + actual.fecha + "," + actual.Descripcion + "," + actual.monto);
                    escritores[index].newLine();
                }

                actual = actual.Siguiente;
            }

            System.out.println("Multas guardadas correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir archivos: " + e.getMessage());
        } finally {
            // Cerrar todos los escritores
            for (BufferedWriter bw : escritores) {
                try {
                    if (bw != null) bw.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar archivo: " + e.getMessage());
                }
            }
        
    }
}
   public static void guardarTraspasosPorDepartamento(Lista_Traspasos lista) {
        String[] departamentos = {
            "Antigua_Guatemala", "Guatemala", "Huehuetenango", "Suchitepequez",
            "San_Marcos", "Quetzaltenango", "Peten", "Escuintla", "Chiquimula", "Chimaltenango"
        };

        BufferedWriter[] escritores = new BufferedWriter[departamentos.length];

        try {
            for (int i = 0; i < departamentos.length; i++) {
                File archivo = new File("C:\\Users\\Ixtamer\\Desktop\\Proyecto programacion\\Proyecto\\proyecto\\src\\main\\java\\"
                        + departamentos[i] + "\\" + departamentos[i] + "_traspasos.txt");

                escritores[i] = new BufferedWriter(new FileWriter(archivo, false));
                escritores[i].write("Placa,DPI,NuevoPropietario,Fecha");
                escritores[i].newLine();
            }

            Nodo_Traspaso actual = lista.cabeza;
            while (actual != null) {
                String dep = actual.departamento.trim();
                int index = -1;
                for (int i = 0; i < departamentos.length; i++) {
                    if (departamentos[i].equalsIgnoreCase(dep)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1 && escritores[index] != null) {
                    escritores[index].write(
                        actual.placa + "," +
                        actual.dpiNuevo + "," +
                        actual.nombreNuevo + "," +
                        actual.fecha
                    );
                    escritores[index].newLine();
                }

                actual = actual.siguiente;
            }

            System.out.println("Traspasos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar traspasos: " + e.getMessage());
        } finally {
            for (BufferedWriter bw : escritores) {
                try {
                    if (bw != null) bw.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar archivo: " + e.getMessage());
                }
            }
        }
    }
   public static void guardarVehiculosPorDepartamento(Lista_Vehiculos listaVehiculos) {
        String[] departamentos = {
            "Antigua_Guatemala", "Guatemala", "Huehuetenango", "Suchitepequez",
            "San_Marcos", "Quetzaltenango", "Peten", "Escuintla", "Chiquimula", "Chimaltenango"
        };

        BufferedWriter[] escritores = new BufferedWriter[departamentos.length];

        try {
            for (int i = 0; i < departamentos.length; i++) {
                File archivo = new File("C:\\Users\\Ixtamer\\Desktop\\Proyecto programacion\\Proyecto\\proyecto\\src\\main\\java\\"
                        + departamentos[i] + "\\" + departamentos[i] + "_vehiculos.txt");

                escritores[i] = new BufferedWriter(new FileWriter(archivo, false));
                escritores[i].write("Placa,DPI,Nombre,Marca,Modelo,Año,Multas,Traspasos");
                escritores[i].newLine();
            }

            Nodo_Vehiculo actual = listaVehiculos.cabeza;
            while (actual != null) {
                String dep = actual.departamento.trim();
                int index = -1;
                for (int i = 0; i < departamentos.length; i++) {
                    if (departamentos[i].equalsIgnoreCase(dep)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1 && escritores[index] != null) {
                    escritores[index].write(
                        actual.placa + "," +
                        actual.dpi + "," +
                        actual.nombre + "," +
                        actual.marca + "," +
                        actual.modelo + "," +
                        actual.anio + "," +
                        actual.cantidadMultas + "," +
                        actual.cantidadTraspasos
                    );
                    escritores[index].newLine();
                }

                actual = actual.siguiente;
            }

            System.out.println("Vehículos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar vehículos: " + e.getMessage());
        } finally {
            for (BufferedWriter bw : escritores) {
                try {
                    if (bw != null) bw.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar archivo: " + e.getMessage());
                }
            }
        }
    }
   

}