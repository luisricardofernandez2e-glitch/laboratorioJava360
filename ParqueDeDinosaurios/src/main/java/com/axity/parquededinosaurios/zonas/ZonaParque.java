package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.model.Turistas;

public interface ZonaParque {
    String getNombre();
    boolean tieneCapacidad();
    int getOcupacionActual();
    int getCapacidadMaxima();
    void entar(Turistas turista);
    void salir(Turistas turista);
}
