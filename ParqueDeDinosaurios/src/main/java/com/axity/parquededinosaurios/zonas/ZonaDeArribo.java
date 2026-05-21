package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.model.Turistas;

public class ZonaDeArribo implements ZonaParque {
    @Override
    public String getNombre() {
        return "Zona de Arribo";
    }

    @Override
    public boolean tieneCapacidad() {
        return false;
    }

    @Override
    public int getOcupacionActual() {
        return 0;
    }

    @Override
    public int getCapacidadMaxima() {
        return 0;
    }

    @Override
    public void entar(Turistas turista) {
        
    }

    @Override
    public void salir(Turistas turista) {

    }

    public void ventaBoletos(int cantidad, ){

    }
}
