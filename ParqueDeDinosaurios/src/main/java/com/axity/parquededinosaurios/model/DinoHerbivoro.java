package com.axity.parquededinosaurios.model;

public class DinoHerbivoro extends Dinosaurio{
    public DinoHerbivoro(int id, String nombre, String especie, EstadoDinosaurio estado) {
        super(id, nombre, especie, estado);
    }

    @Override
    public Double getPobabilidadAtaque() {
        return 0.2;
    }

    @Override
    public String getDieta() {
        return "HERBIVORO";
    }

    @Override
    public Double getCostoAlimenticio() {
        return 200.0;
    }
}
