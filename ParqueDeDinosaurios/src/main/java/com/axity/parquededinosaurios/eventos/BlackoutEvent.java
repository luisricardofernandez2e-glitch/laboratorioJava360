package com.axity.parquededinosaurios.eventos;

import com.axity.parquededinosaurios.configuracion.ParkState;
import com.axity.parquededinosaurios.historial.historialEvento;
import com.axity.parquededinosaurios.historial.historialGastos;

import java.time.LocalDateTime;
import java.util.Random;

public class BlackoutEvent implements SimulationEvent{
    @Override
    public String getName() {
        return "APAGON_MASIVO";
    }

    @Override
    public String getDescription() {
        return "La planta eléctrica sufrió un apagón";
    }

    @Override
    public void execute(ParkState state, Random rng) {

        state.getPlantaEnergia()
                .triggerFailure(
                        state.getWriter()
                );
        state.getWriter()
                .appendGastos(
                        new historialGastos(
                                state.getWriter().generarIdGasto(),
                                "APAGON",
                                2000,
                                "Apagón masivo",
                                LocalDateTime.now()
                        )
                );

        state.getWriter()
                .appendEvento(
                        toRecord(
                                state.getCurrentStep()
                        )
                );
    }

    @Override
    public historialEvento toRecord(long step) {
        return new historialEvento(
                step,
                getName(),
                getDescription(),
                "PowerPlant",
                LocalDateTime.now()
        );
    }
}
