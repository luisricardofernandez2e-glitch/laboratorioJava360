package com.axity.parquededinosaurios.model;

public class Tecnico extends Trabajador{
    @Override
    public String getRol() {
        return "Tecnico";
    }

    public void repararPlanta(){
    }
}
