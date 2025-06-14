import java.awt.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ArbolAVL {
    NodoAVL raiz;

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
   
    limpiarArchivosPorDepartamento(raiz, rutaBase);
  
    guardarVehiculosRec(raiz, rutaBase);
}

private void guardarVehiculosRec(NodoAVL nodo, String rutaBase) {
    if (nodo != null) {
        guardarVehiculosRec(nodo.izquierdo, rutaBase);

        Vehiculo v = nodo.vehiculo;
        String departamento = v.getDepartamento();
        String nombreArchivo = departamento + "_vehiculos.txt";
        File carpeta = new File(rutaBase + File.separator + departamento);
        carpeta.mkdirs();
        File archivo = new File(carpeta, nombreArchivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            String linea = String.join(",",
                    safe(v.getPlaca()),
                    safe(v.getDpi()),
                    safe(v.getNombre()),
                    safe(v.getMarca()),
                    safe(v.getModelo()),
                    String.valueOf(v.getAnio()),
                    String.valueOf(v.getMultas()),
                    String.valueOf(v.getTraspasos()));
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        guardarVehiculosRec(nodo.derecho, rutaBase);
    }
}

private void limpiarArchivosPorDepartamento(NodoAVL nodo, String rutaBase) {
    if (nodo != null) {
        limpiarArchivosPorDepartamento(nodo.izquierdo, rutaBase);

        Vehiculo v = nodo.vehiculo;
        String departamento = v.getDepartamento();
        File carpeta = new File(rutaBase + File.separator + departamento);
        carpeta.mkdirs();
        File archivo = new File(carpeta, departamento + "_vehiculos.txt");

     
        if (archivo.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        limpiarArchivosPorDepartamento(nodo.derecho, rutaBase);
    }
}

private String safe(String valor) {
    return valor == null ? "" : valor;
}

   


    

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Departamento", "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos"});
        return modelo;
    }

    public void insertar(Vehiculo vehiculo) {
        raiz = insertarRec(raiz, vehiculo);
    }

    private NodoAVL insertarRec(NodoAVL nodo, Vehiculo vehiculo) {
        if (nodo == null) return new NodoAVL(vehiculo);

        int cmp = vehiculo.getPlaca().compareTo(nodo.vehiculo.getPlaca());

        if (cmp < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, vehiculo);
        } else if (cmp > 0) {
            nodo.derecho = insertarRec(nodo.derecho, vehiculo);
        } else {
          
            nodo.vehiculo.setMultas(vehiculo.getMultas());
            nodo.vehiculo.setTraspasos(vehiculo.getTraspasos());
            return nodo;
        }

        nodo.actualizarAltura();
        int balance = nodo.obtenerBalance();

        if (balance > 1 && vehiculo.getPlaca().compareTo(nodo.izquierdo.vehiculo.getPlaca()) < 0) return rotacionDerecha(nodo);
        if (balance < -1 && vehiculo.getPlaca().compareTo(nodo.derecho.vehiculo.getPlaca()) > 0) return rotacionIzquierda(nodo);
        if (balance > 1 && vehiculo.getPlaca().compareTo(nodo.izquierdo.vehiculo.getPlaca()) > 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && vehiculo.getPlaca().compareTo(nodo.derecho.vehiculo.getPlaca()) < 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.actualizarAltura();
        x.actualizarAltura();

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.actualizarAltura();
        y.actualizarAltura();

        return y;
    }

    public NodoAVL buscar(String placa) {
        return buscarRec(raiz, placa);
    }

    private NodoAVL buscarRec(NodoAVL nodo, String placa) {
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

    private void recorrerInOrdenRec(NodoAVL nodo, DefaultTableModel modelo) {
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

    private void recorrerPreOrdenRec(NodoAVL nodo, DefaultTableModel modelo) {
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

    private void recorrerPostOrdenRec(NodoAVL nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            recorrerPostOrdenRec(nodo.izquierdo, modelo);
            recorrerPostOrdenRec(nodo.derecho, modelo);
            Vehiculo v = nodo.vehiculo;
            modelo.addRow(new Object[]{v.getDepartamento(), v.getPlaca(), v.getDpi(), v.getNombre(), v.getMarca(), v.getModelo(), v.getAnio(), v.getMultas(), v.getTraspasos()});
        }
    }
    
    public List<Vehiculo> obtenerListaVehiculos() {
    List<Vehiculo> lista = new ArrayList<>();
    recorrerInOrden(raiz, lista);
    return lista;
}

private void recorrerInOrden(NodoAVL nodo, List<Vehiculo> lista) {
    if (nodo != null) {
        recorrerInOrden(nodo.izquierdo, lista);
        if (nodo.vehiculo != null) {
            lista.add(nodo.vehiculo);
        }
        recorrerInOrden(nodo.derecho, lista);
    }
}

    public NodoAVL getRaiz() {
        return raiz;
    
}
    public void mostrarGraficaAVL() {
    try {
      
        String dotCode = generarDot();

       
        File archivoDot = new File("avl.dot");
        try (PrintWriter writer = new PrintWriter(archivoDot)) {
            writer.println(dotCode);
        }

        File archivoPng = new File("avl.png");
        ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", archivoDot.getAbsolutePath(), "-o", archivoPng.getAbsolutePath());
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor();

    
        Image imagen = ImageIO.read(archivoPng);
        JLabel etiqueta = new JLabel(new ImageIcon(imagen));
        JScrollPane scrollPane = new JScrollPane(etiqueta);

        JFrame frame = new JFrame("Visualización del Árbol AVL");
        frame.getContentPane().add(scrollPane);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al generar gráfica: " + e.getMessage());
    }
}
    private String generarDot() {
    StringBuilder dot = new StringBuilder();
    dot.append("digraph AVL {\n");
    dot.append("    node [shape=box, style=filled, fillcolor=lightblue];\n");
    generarDotRec(raiz, dot);
    dot.append("}\n");
    return dot.toString();
}

private void generarDotRec(NodoAVL nodo, StringBuilder dot) {
    if (nodo != null) {
        String id = "\"" + nodo.vehiculo.getPlaca() + "\"";
        String label = nodo.vehiculo.getPlaca() + "\\nM:" + nodo.vehiculo.getMultas() + "\\nT:" + nodo.vehiculo.getTraspasos();
        dot.append("    ").append(id).append(" [label=\"").append(label).append("\"];\n");

        if (nodo.izquierdo != null) {
            dot.append("    ").append(id).append(" -> \"").append(nodo.izquierdo.vehiculo.getPlaca()).append("\";\n");
            generarDotRec(nodo.izquierdo, dot);
        }
        if (nodo.derecho != null) {
            dot.append("    ").append(id).append(" -> \"").append(nodo.derecho.vehiculo.getPlaca()).append("\";\n");
            generarDotRec(nodo.derecho, dot);
        }
    }
}
    public NodoAVL buscarConTiempo(String placa, javax.swing.JLabel etiquetaTiempo) {
    long inicio = System.nanoTime();
    NodoAVL nodo = buscar(placa);   
    long fin = System.nanoTime();   

    long tiempo = fin - inicio;
    etiquetaTiempo.setText("Tiempo de búsqueda: " + tiempo + " ns");

    return nodo;
}







}
