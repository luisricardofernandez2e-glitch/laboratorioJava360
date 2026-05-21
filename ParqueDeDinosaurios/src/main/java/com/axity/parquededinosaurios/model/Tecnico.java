package com.axity.parquededinosaurios.model;

public class Tecnico extends Trabajador{
    public Tecnico(int id, String nombre, Double Salario) {
        super(id, nombre, Salario);
    }

    @Override
    public String getRol() {
        return "Tecnico";
    }

    public void repararPlanta(){
    }
}
