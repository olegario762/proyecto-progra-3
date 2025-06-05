
import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;





public class Importar_archivos_multas {
    
   
    

   
    



        
    
     public class EscritorArchivos {

    private static final String[] DEPARTAMENTOS = {
        "Antigua_Guatemala", "Guatemala", "Huehuetenango", "Suchitepequez",
        "San_Marcos", "Quetzaltenango", "Peten", "Escuintla", "Chiquimula", "Chimaltenango"
    };

    private static final String RUTA_BASE = "C:\\Users\\Ixtamer\\Desktop\\Proyecto programacion\\Proyecto\\proyecto\\src\\main\\java\\";

    private static int obtenerIndiceDepartamento(String nombreDep) {
        for (int i = 0; i < DEPARTAMENTOS.length; i++) {
            if (DEPARTAMENTOS[i].equalsIgnoreCase(nombreDep.trim())) {
                return i;
            }
        }
        return -1;
    }

    private static void asegurarCarpeta(String nombreDep) {
        File carpeta = new File(RUTA_BASE + nombreDep);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    public static void guardarTodosLosArchivos_multas(Lista_Multas list_multas) {
        BufferedWriter[] escritores = new BufferedWriter[DEPARTAMENTOS.length];

        try {
            for (int i = 0; i < DEPARTAMENTOS.length; i++) {
                asegurarCarpeta(DEPARTAMENTOS[i]);
                File archivo = new File(RUTA_BASE + DEPARTAMENTOS[i] + "\\" + DEPARTAMENTOS[i] + "_multas.txt");
                escritores[i] = new BufferedWriter(new FileWriter(archivo, false));
                escritores[i].write("Placa,Fecha,Descripcion,Monto");
                escritores[i].newLine();
            }

            Nodo_Multas actual = list_multas.getCabeza();
            while (actual != null) {
                int index = obtenerIndiceDepartamento(actual.departamento);
                if (index != -1 && escritores[index] != null) {
                    escritores[index].write(actual.Placa + "," + actual.fecha + "," + actual.Descripcion + "," + actual.monto);
                    escritores[index].newLine();
                }
                actual = actual.Siguiente;
            }

            System.out.println(" Multas guardadas correctamente.");

        } catch (IOException e) {
            System.out.println(" Error al escribir archivos de multas: " + e.getMessage());
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

    public static void guardarTraspasosPorDepartamento(Lista_Traspasos lista) {
        BufferedWriter[] escritores = new BufferedWriter[DEPARTAMENTOS.length];

        try {
            for (int i = 0; i < DEPARTAMENTOS.length; i++) {
                asegurarCarpeta(DEPARTAMENTOS[i]);
                File archivo = new File(RUTA_BASE + DEPARTAMENTOS[i] + "\\" + DEPARTAMENTOS[i] + "_traspasos.txt");
                escritores[i] = new BufferedWriter(new FileWriter(archivo, false));
                escritores[i].write("Placa,DPI,NuevoPropietario,Fecha");
                escritores[i].newLine();
            }

            Nodo_Traspaso actual = lista.cabeza;
            while (actual != null) {
                int index = obtenerIndiceDepartamento(actual.departamento);
                if (index != -1 && escritores[index] != null) {
                    escritores[index].write(actual.placa + "," + actual.dpiNuevo + "," + actual.nombreNuevo + "," + actual.fecha);
                    escritores[index].newLine();
                }
                actual = actual.siguiente;
            }

            System.out.println(" Traspasos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar traspasos: " + e.getMessage());
        } finally {
            for (BufferedWriter bw : escritores) {
                try {
                    if (bw != null) bw.close();
                } catch (IOException e) {
                    System.out.println(" Error al cerrar archivo: " + e.getMessage());
                }
            }
        }
    }


        
    }
    }

