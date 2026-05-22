package com.axity.parquededinosaurios.eventos;

import com.axity.parquededinosaurios.configuracion.ParkState;
import com.axity.parquededinosaurios.historial.historialEvento;
import com.axity.parquededinosaurios.historial.historialGastos;
import com.axity.parquededinosaurios.model.EstadoTurista;
import com.axity.parquededinosaurios.model.Turistas;

import java.time.LocalDateTime;
import java.util.Random;

public class StormEvent implements SimulationEvent{
    @Override
    public String getName() {
        return "Tormenta torrencial";
    }

    @Override
    public String getDescription() {
        return "El parque fue evacuado por tormenta";
    }

    @Override
    public void execute(ParkState state, Random rng) {
        for (Turistas turista
                : state.getTuristas()) {

            if (turista.getEstado()
                    == EstadoTurista.EN_PARQUE) {

                turista.lugarEnVisita(
                        "Evacuación"
                );
            }
        }

        state.getWriter()
                .appendGastos(
                        new historialGastos(
                                state.getWriter().generarIdGasto(),
                                "TORMENTA",
                                500,
                                "Evacuación por tormenta",
                                LocalDateTime.now()
                        )
                );

        state.getWriter().appendEvento(toRecord(state.getCurrentStep()));
    }

    @Override
    public historialEvento toRecord(long step) {
        return new historialEvento(
                step,
                getName(),
                getDescription(),
                "Turistas",
                LocalDateTime.now()
        );
    }
}
