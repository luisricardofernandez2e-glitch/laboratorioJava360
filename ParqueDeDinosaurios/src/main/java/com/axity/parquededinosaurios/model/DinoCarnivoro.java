package com.axity.parquededinosaurios.model;

public class DinoCarnivoro extends Dinosaurio{
    public DinoCarnivoro(int id, String nombre, String especie, EstadoDinosaurio estado) {
        super(id, nombre, especie, estado);
    }

    public DinoCarnivoro() {
        super();
    }

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
