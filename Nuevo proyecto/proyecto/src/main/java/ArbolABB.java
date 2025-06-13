import java.io.*;
import javax.swing.table.DefaultTableModel;

public class ArbolABB {
    NodoABB raiz;

    public DefaultTableModel cargarVehiculosDesdeCarpetas(String rutaPrincipal) {
        DefaultTableModel modelo = crearModeloTabla();

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

                    Vehiculo v = new Vehiculo(nombreDepto, placa, dpi, nombre, marca, modeloStr, anio, multas, traspasos);
                    insertar(v);
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return recorrerInOrden();
    }

    public void guardarVehiculosEnArchivos(String rutaBase) {
        guardarVehiculosRec(raiz, rutaBase);
    }

    private void guardarVehiculosRec(NodoABB nodo, String rutaBase) {
        if (nodo != null) {
            guardarVehiculosRec(nodo.izquierdo, rutaBase);

            Vehiculo v = nodo.vehiculo;
            String nombreArchivo = v.getDepartamento() + "_vehiculos.txt";
            File archivo = new File(rutaBase + File.separator + v.getDepartamento(), nombreArchivo);

            archivo.getParentFile().mkdirs();

            try (FileWriter fw = new FileWriter(archivo, true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                String linea = String.join(",", v.getPlaca(), v.getDpi(), v.getNombre(),
                        v.getMarca(), v.getModelo(), String.valueOf(v.getAnio()),
                        String.valueOf(v.getMultas()), String.valueOf(v.getTraspasos()));
                bw.write(linea);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            guardarVehiculosRec(nodo.derecho, rutaBase);
        }
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Departamento", "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos"});
        return modelo;
    }

    public void insertar(Vehiculo vehiculo) {
        raiz = insertarRec(raiz, vehiculo);
    }

    private NodoABB insertarRec(NodoABB nodo, Vehiculo vehiculo) {
        if (nodo == null) return new NodoABB(vehiculo);

        int cmp = vehiculo.getPlaca().compareTo(nodo.vehiculo.getPlaca());

        if (cmp < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, vehiculo);
        } else if (cmp > 0) {
            nodo.derecho = insertarRec(nodo.derecho, vehiculo);
        } else {
            nodo.vehiculo.setMultas(vehiculo.getMultas());
            nodo.vehiculo.setTraspasos(vehiculo.getTraspasos());
        }

        return nodo;
    }

    public NodoABB buscar(String placa) {
        return buscarRec(raiz, placa);
    }

    private NodoABB buscarRec(NodoABB nodo, String placa) {
        if (nodo == null) return null;

        int comparacion = placa.compareTo(nodo.vehiculo.getPlaca());
        if (comparacion == 0) return nodo;
        else if (comparacion < 0) return buscarRec(nodo.izquierdo, placa);
        else return buscarRec(nodo.derecho, placa);
    }

    public DefaultTableModel recorrerInOrden() {
        DefaultTableModel modelo = crearModeloTabla();
        recorrerInOrdenRec(raiz, modelo);
        return modelo;
    }

    private void recorrerInOrdenRec(NodoABB nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            recorrerInOrdenRec(nodo.izquierdo, modelo);
            Vehiculo v = nodo.vehiculo;
            modelo.addRow(new Object[]{v.getDepartamento(), v.getPlaca(), v.getDpi(), v.getNombre(), v.getMarca(), v.getModelo(), v.getAnio(), v.getMultas(), v.getTraspasos()});
            recorrerInOrdenRec(nodo.derecho, modelo);
        }
    }

    public DefaultTableModel recorrerPreOrden() {
        DefaultTableModel modelo = crearModeloTabla();
        recorrerPreOrdenRec(raiz, modelo);
        return modelo;
    }

    private void recorrerPreOrdenRec(NodoABB nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            Vehiculo v = nodo.vehiculo;
            modelo.addRow(new Object[]{v.getDepartamento(), v.getPlaca(), v.getDpi(), v.getNombre(), v.getMarca(), v.getModelo(), v.getAnio(), v.getMultas(), v.getTraspasos()});
            recorrerPreOrdenRec(nodo.izquierdo, modelo);
            recorrerPreOrdenRec(nodo.derecho, modelo);
        }
    }

    public DefaultTableModel recorrerPostOrden() {
        DefaultTableModel modelo = crearModeloTabla();
        recorrerPostOrdenRec(raiz, modelo);
        return modelo;
    }

    private void recorrerPostOrdenRec(NodoABB nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            recorrerPostOrdenRec(nodo.izquierdo, modelo);
            recorrerPostOrdenRec(nodo.derecho, modelo);
            Vehiculo v = nodo.vehiculo;
            modelo.addRow(new Object[]{v.getDepartamento(), v.getPlaca(), v.getDpi(), v.getNombre(), v.getMarca(), v.getModelo(), v.getAnio(), v.getMultas(), v.getTraspasos()});
        }
    }
}
