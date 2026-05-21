package com.axity.parquededinosaurios.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Dinosaurio {
    private int id;
    private String nombre,especie;
    private EstadoDinosaurio estado;

    abstract public Double getPobabilidadAtaque();
    abstract public String getDieta();
    abstract public Double getCostoAlimenticio();


    public void escapar(){ estado = EstadoDinosaurio.ESCAPADO;}
    public void capturado(){ estado = EstadoDinosaurio.CAPTURADO;}
    public void encerrado(){ estado = EstadoDinosaurio.ENCERRADO;}
}
