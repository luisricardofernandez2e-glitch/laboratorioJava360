package com.axity.parquededinosaurios.model;


import java.util.List;

public class Guardia extends Trabajador {

    @Override
    public String getRol() {
        return "GUARDIA";
    }

    public void recapturarDinosaurios(List<Dinosaurio> dinos){
        for (Dinosaurio dinosaurio : dinos){
            if(dinosaurio.getEstado() == EstadoDinosaurio.ESCAPADO){
                dinosaurio.setEstado(EstadoDinosaurio.CAPTURADO);
            }
        }

    }

}
