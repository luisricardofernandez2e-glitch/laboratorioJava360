package com.axity.parquededinosaurios.model;

public class DinoCarnivoro extends Dinosaurio{
    @Override
    public Double getPobabilidadAtaque() {
        return 0.9;
    }

    @Override
    public String getDieta() {
        return "CARNIVORO";
    }

    @Override
    public Double getCostoAlimenticio() {
        return 500.0;
    }

}
