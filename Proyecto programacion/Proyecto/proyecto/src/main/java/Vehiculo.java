/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Vehiculo {
     private String placa;
    private String dpi;
    private String nombre;
    private String marca;
    private String modelo;
    private int anio;
    private int multas;
    private int traspasos;
    private String departamento;

    public Vehiculo(String placa, String dpi, String nombre, String marca, String modelo, int anio, int multas, int traspasos, String departamento) {
        this.placa = placa;
        this.dpi = dpi;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.multas = multas;
        this.traspasos = traspasos;
        this.departamento=departamento;
    }

    public String getPlaca() {
        return placa;
        
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public int getMultas() {
        return multas;
    }

    public int getTraspasos() {
        return traspasos;
    }
    

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", dpi='" + dpi + '\'' +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", multas=" + multas +
                ", traspasos=" + traspasos +
                '}';
    }
}