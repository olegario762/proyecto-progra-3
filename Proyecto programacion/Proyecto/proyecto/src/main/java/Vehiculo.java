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
    private String departamento;
    private int ano;
    private int multas;
    private int traspasos;

    public Vehiculo(String departamento,String placa, String dpi, String nombre, String marca, String modelo, int ano, int multas, int traspasos) {
        this.placa = placa;
        this.dpi = dpi;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.multas = multas;
        this.traspasos = traspasos;
        this.departamento=departamento;
    }

    public String getPlaca() {
        return placa;
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

    public int getAno() {
        return ano;
    }

    public int getMultas() {
        return multas;
    }

    public int getTraspasos() {
        return traspasos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setMultas(int multas) {
        this.multas = multas;
    }
    
   public String[] toArray() {
    return new String[]{
        departamento,
        placa,
        dpi,
        nombre,
        marca,
        modelo,
        String.valueOf(ano),
        String.valueOf(multas),
        String.valueOf(traspasos)
    };
}

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", dpi=" + dpi + ", nombre=" + nombre + ", marca=" + marca + ", modelo=" + modelo + ", departamento=" + departamento + ", ano=" + ano + ", multas=" + multas + ", traspasos=" + traspasos + '}';
    }
   

  
  
    
    
    
}

   