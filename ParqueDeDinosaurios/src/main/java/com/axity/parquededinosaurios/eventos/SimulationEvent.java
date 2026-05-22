package com.axity.parquededinosaurios.eventos;

import com.axity.parquededinosaurios.configuracion.ParkState;
import com.axity.parquededinosaurios.historial.historialEvento;

import java.util.Random;

public interface SimulationEvent {
        String      getName();
        String      getDescription();
        void        execute(ParkState state, Random rng);
        historialEvento toRecord(long step);
}
