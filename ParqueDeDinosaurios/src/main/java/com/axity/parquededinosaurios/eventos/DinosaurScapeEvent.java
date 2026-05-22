package com.axity.parquededinosaurios.eventos;

import com.axity.parquededinosaurios.configuracion.ParkState;
import com.axity.parquededinosaurios.historial.historialEvento;
import com.axity.parquededinosaurios.model.Dinosaurio;
import com.axity.parquededinosaurios.model.EstadoDinosaurio;
import com.axity.parquededinosaurios.model.EstadoTurista;
import com.axity.parquededinosaurios.model.Turistas;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DinosaurScapeEvent implements SimulationEvent{
    @Override
    public String getName() {
        return "Escape de dinosaurios";
    }

    @Override
    public String getDescription() {
        return "Un dinosaurio escapó";
    }

    @Override
    public void execute(ParkState state, Random rng) {

        List<Dinosaurio> disponibles =
                state.getDinosaurios()
                        .stream()
                        .filter(d ->
                                d.getEstado()
                                        == EstadoDinosaurio.ENCERRADO
                        )
                        .collect(Collectors.toList());

        if (disponibles.isEmpty()) {
            return;
        }

        Dinosaurio dinosaurio =
                disponibles.get(
                        rng.nextInt(
                                disponibles.size()
                        )
                );


        dinosaurio.escapar();

        double dangerLevel =
                dinosaurio.getPobabilidadAtaque();

        if (rng.nextDouble() < dangerLevel) {

            List<Turistas> turistas =
                    state.getTuristas()
                            .stream()
                            .filter(t ->
                                    t.getEstado()
                                            == EstadoTurista.EN_PARQUE
                            )
                            .collect(Collectors.toList());

            if (!turistas.isEmpty()) {

                Turistas victima =
                        turistas.get(
                                rng.nextInt(
                                        turistas.size()
                                )
                        );

                victima.setEstado(
                        EstadoTurista.ATACADO
                );
            }
        }
    }

    @Override
    public historialEvento toRecord(long step) {
        return new historialEvento(
                step,
                getName(),
                getDescription(),
                "Dinosaurio",
                java.time.LocalDateTime.now()
        );
    }
}
