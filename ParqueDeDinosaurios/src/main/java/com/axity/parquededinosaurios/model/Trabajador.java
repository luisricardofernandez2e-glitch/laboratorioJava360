package com.axity.parquededinosaurios.model;

public abstract class Trabajador {
    private int id;
    private String nombre;
    private Double Salario;

    public Trabajador(int id, String nombre, Double Salario) {
        this.id = id;
        this.nombre = nombre;
        this.Salario = Salario;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public Double getSalario() { return Salario; }

    public abstract String getRol();
}
