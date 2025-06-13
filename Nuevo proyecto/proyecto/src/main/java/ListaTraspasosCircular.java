import java.io.*;
import java.util.HashSet;

public class ListaTraspasosCircular {
    NodoTraspaso inicio;

    public void agregarFinal(String placa, String dpiAnterior, String nombreAnterior, String fecha,
                              String dpiNuevo, String nombreNuevo, String departamento) {
        NodoTraspaso nuevo = new NodoTraspaso(placa, dpiAnterior, nombreAnterior, fecha, dpiNuevo, nombreNuevo, departamento);
        if (inicio == null) {
            inicio = nuevo;
            inicio.siguiente = inicio;
        } else {
            NodoTraspaso temp = inicio;
            while (temp.siguiente != inicio) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
            nuevo.siguiente = inicio;
        }
    }

    public int contarTraspasos() {
        if (inicio == null) return 0;
        int conteo = 1;
        NodoTraspaso temp = inicio;
        while (temp.siguiente != inicio) {
            conteo++;
            temp = temp.siguiente;
        }
        return conteo;
    }

    public void mostrarTraspasos() {
        if (inicio == null) {
            System.out.println("No hay traspasos registrados.");
            return;
        }

        NodoTraspaso temp = inicio;
        do {
            System.out.println(temp);
            temp = temp.siguiente;
        } while (temp != inicio);
    }

    public void guardarTraspasosPorDepartamento(String rutaPrincipal) {
        if (inicio == null) {
            System.out.println("Lista de traspasos vacía.");
            return;
        }

        HashSet<String> procesados = new HashSet<>();
        NodoTraspaso temp = inicio;

        do {
            String departamento = temp.departamento;

            if (!procesados.contains(departamento)) {
                procesados.add(departamento);

                File carpeta = new File(rutaPrincipal + File.separator + departamento);
                if (!carpeta.exists()) carpeta.mkdirs();

                File archivo = new File(carpeta, departamento + "_traspasos.txt");

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {  // Append = true
                    NodoTraspaso aux = inicio;
                    do {
                        if (aux.departamento.equals(departamento)) {
                            bw.write(aux.toString());
                            bw.newLine();
                        }
                        aux = aux.siguiente;
                    } while (aux != inicio);

                    System.out.println("Archivo de traspasos para '" + departamento + "' guardado.");
                } catch (IOException e) {
                    System.out.println("Error guardando archivo: " + e.getMessage());
                }
            }

            temp = temp.siguiente;
        } while (temp != inicio);
    }

    
    
    
    public void cargarTraspasosDesdeCarpetas(String rutaPrincipal) {
    File carpetaPrincipal = new File(rutaPrincipal);
    if (!carpetaPrincipal.exists() || !carpetaPrincipal.isDirectory()) return;

    File[] subcarpetas = carpetaPrincipal.listFiles(File::isDirectory);
    if (subcarpetas == null) return;

    for (File subcarpeta : subcarpetas) {
        String nombreDepto = subcarpeta.getName();
        File archivoTraspasos = new File(subcarpeta, nombreDepto + "_traspasos.txt");

        if (!archivoTraspasos.exists()) continue;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoTraspasos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 6) continue;

                String placa = datos[0].trim();
                String dpiAnt = datos[1].trim();
                String nombreAnt = datos[2].trim();
                String fecha = datos[3].trim();
                String dpiNuevo = datos[4].trim();
                String nombreNuevo = datos[5].trim();

                this.agregarFinal(placa, dpiAnt, nombreAnt, fecha, dpiNuevo, nombreNuevo, nombreDepto);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar traspasos de " + nombreDepto + ": " + e.getMessage());
        }
    }
}

}
