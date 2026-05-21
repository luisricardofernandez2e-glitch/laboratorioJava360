package com.axity.parquededinosaurios.model;

import lombok.Data;

@Data
public abstract class Dinosaurio {
    private int id;
    private String nombre,especie;
    private EstadoDinosaurio estado;

    abstract public Double getPobabilidadAtaque();
    abstract public String getDieta();
    abstract public Double getCostoAlimenticio();
    
}
