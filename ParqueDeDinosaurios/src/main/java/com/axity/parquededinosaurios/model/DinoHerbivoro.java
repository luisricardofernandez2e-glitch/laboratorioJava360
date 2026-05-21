package com.axity.parquededinosaurios.model;

public class DinoHerbivoro extends Dinosaurio{
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
