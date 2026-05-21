package com.axity.parquededinosaurios.model;

import lombok.Data;

@Data
public abstract class Trabajador {
    private int id;
    private String nombre;
    private Double Salario;

    public abstract String getRol();
}
