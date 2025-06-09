import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ArbolVehiculos {
    private NodoVehiculo nodoRaiz;
   
    public ArbolVehiculos() {
       
    }

    public void agregarVehiculo(String depto, String placa, String dpi, String nombre, String marca, String modelo,
                                int anio, int numMultas, int numTraspasos) {
        Vehiculo vehiculo = new Vehiculo(depto, placa, dpi, nombre, marca, modelo, anio, numMultas, numTraspasos);
        nodoRaiz = insertar(nodoRaiz, placa, vehiculo);
    }

    private NodoVehiculo insertar(NodoVehiculo nodo, String placa, Vehiculo vehiculo) {
        if (nodo == null) {
            return new NodoVehiculo(placa, vehiculo);
        }

        int comparacion = placa.compareTo(nodo.placa);
        if (comparacion < 0) {
            nodo.hijoIzquierdo = insertar(nodo.hijoIzquierdo, placa, vehiculo);
        } else if (comparacion > 0) {
            nodo.hijoDerecho = insertar(nodo.hijoDerecho, placa, vehiculo);
        } else {
            nodo.vehiculos.add(vehiculo);
        }

        return nodo;
    }

  
    public DefaultTableModel cargarVehiculosDesdeCarpetas(String rutaPrincipal) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Departamento", "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos"});

        File carpeta = new File(rutaPrincipal);

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            JOptionPane.showMessageDialog(null, "Ruta inválida: " + rutaPrincipal);
            return modelo;
        }

        File[] subcarpetas = carpeta.listFiles(File::isDirectory);
        if (subcarpetas == null) {
            JOptionPane.showMessageDialog(null, "No se encontraron subcarpetas.");
            return modelo;
        }

        for (File sub : subcarpetas) {
            String nombreDepto = sub.getName();
            File archivo = new File(sub, nombreDepto + "_vehiculos.txt");

            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + archivo.getPath());
                continue;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length != 8) {
                        JOptionPane.showMessageDialog(null, "Línea malformada en " + archivo.getName() + ":\n" + linea);
                        continue;
                    }

                    try {
                        String placa = datos[0].trim();
                        String dpi = datos[1].trim();
                        String nombre = datos[2].trim();
                        String marca = datos[3].trim();
                        String modeloStr = datos[4].trim();
                        int anio = Integer.parseInt(datos[5].trim());
                        int multas = Integer.parseInt(datos[6].trim());
                        int traspasos = Integer.parseInt(datos[7].trim());

                        // Agregamos al árbol
                        agregarVehiculo(nombreDepto, placa, dpi, nombre, marca, modeloStr, anio, multas, traspasos);

                        // Y agregamos a la tabla
                        modelo.addRow(new Object[]{nombreDepto, placa, dpi, nombre, marca, modeloStr, anio, multas, traspasos});

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error numérico en archivo " + archivo.getName() + ":\n" + linea);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo " + archivo.getName() + ":\n" + e.getMessage());
            }
        }

        return modelo;
    }
    
    
    
    

  
    
    public ArrayList<Vehiculo> recorridoInorden() {
    ArrayList<Vehiculo> lista = new ArrayList<>();
    recorridoInorden(nodoRaiz, lista);
    return lista;
}

    private void recorridoInorden(NodoVehiculo nodo, ArrayList<Vehiculo> lista) {
        if (nodo != null) {
            recorridoInorden(nodo.hijoIzquierdo, lista);
            lista.addAll(nodo.getVehiculos());
            recorridoInorden(nodo.hijoDerecho, lista);
    }
}
    public ArrayList<Vehiculo> recorridoPreorden() {
    ArrayList<Vehiculo> lista = new ArrayList<>();
    recorridoPreorden(nodoRaiz, lista);
    return lista;
}

    private void recorridoPreorden(NodoVehiculo nodo, ArrayList<Vehiculo> lista) {
        if (nodo != null) {
            lista.addAll(nodo.getVehiculos());
            recorridoPreorden(nodo.hijoIzquierdo, lista);
            recorridoPreorden(nodo.hijoDerecho, lista);
        }
    }
    public ArrayList<Vehiculo> recorridoPostorden() {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        recorridoPostorden(nodoRaiz, lista);
        return lista;
    }

    private void recorridoPostorden(NodoVehiculo nodo, ArrayList<Vehiculo> lista) {
        if (nodo != null) {
            recorridoPostorden(nodo.hijoIzquierdo, lista);
            recorridoPostorden(nodo.hijoDerecho, lista);
            lista.addAll(nodo.getVehiculos());
        }
    }
    public Vehiculo buscarVehiculoConTiempo(String placa) {
    long inicio = System.nanoTime();
    NodoVehiculo nodo = nodoRaiz;

    while (nodo != null) {
        int comparacion = placa.compareTo(nodo.placa);
        if (comparacion == 0) {
            long fin = System.nanoTime();
            double tiempoMs = (fin - inicio) / 1_000_000.0;
            JOptionPane.showMessageDialog(null, "Vehículo encontrado en ");
            return nodo.getVehiculos().get(0); // Devuelve el primero (puedes cambiar esto)
        } else if (comparacion < 0) {
            nodo = nodo.hijoIzquierdo;
        } else {
            nodo = nodo.hijoDerecho;
        }
    }

    long fin = System.nanoTime();
    double tiempoMs = (fin - inicio) / 1_000_000.0;
    JOptionPane.showMessageDialog(null, "Vehículo no encontrado. Tiempo de búsqueda: " + tiempoMs + " ms");
    return null;
}
    
    
    private NodoVehiculo buscarNodoPorPlaca(String placaBuscada) {
        NodoVehiculo nodo = nodoRaiz;
        String placaLimpia = placaBuscada.trim().toUpperCase();

        while (nodo != null) {
            String placaNodo = nodo.placa.trim().toUpperCase();
            int comparacion = placaLimpia.compareTo(placaNodo);

            if (comparacion == 0) {
                return nodo;
            } else if (comparacion < 0) {
                nodo = nodo.hijoIzquierdo;
            } else {
                nodo = nodo.hijoDerecho;
            }
        }

        return null;
    }

    public void incrementarMultasPorPlaca(String placa) {
        NodoVehiculo nodo = buscarNodoPorPlaca(placa);
        if (nodo != null) {
            for (Vehiculo v : nodo.getVehiculos()) {
                v.setMultas(v.getMultas() + 1);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el vehículo con placa: " + placa);
        }
    }


    



   

    public String generarDOT() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph ArbolVehiculos {\n");
        dot.append("node [shape=box, style=filled, color=lightgray];\n");

        if (nodoRaiz != null) {
            generarDOTRec(nodoRaiz, dot);
        }

        dot.append("}\n");
        return dot.toString();
    }

    private void generarDOTRec(NodoVehiculo nodo, StringBuilder dot) {
        for (Vehiculo v : nodo.getVehiculos()) {
            dot.append("\"").append(nodo.placa).append("\" [label=\"Placa: ").append(nodo.placa)
               .append("\\nNombre: ").append(v.getNombre())
               .append("\\nDPI: ").append(v.getDpi())
               .append("\\nMarca: ").append(v.getMarca())
               .append("\\nModelo: ").append(v.getModelo())
               .append("\\nAño: ").append(v.getAno())
               .append("\\nMultas: ").append(v.getMultas())
               .append("\\nTraspasos: ").append(v.getTraspasos()).append("\"];\n");
            break; // solo mostrar uno por nodo en el DOT
        }

        if (nodo.hijoIzquierdo != null) {
            dot.append("\"").append(nodo.placa).append("\" -> \"").append(nodo.hijoIzquierdo.placa).append("\";\n");
            generarDOTRec(nodo.hijoIzquierdo, dot);
        }

        if (nodo.hijoDerecho != null) {
            dot.append("\"").append(nodo.placa).append("\" -> \"").append(nodo.hijoDerecho.placa).append("\";\n");
            generarDOTRec(nodo.hijoDerecho, dot);
        }
    }
}
