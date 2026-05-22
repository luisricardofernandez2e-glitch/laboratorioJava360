package com.axity.parquededinosaurios.simulacion;

import com.axity.parquededinosaurios.configuracion.ParkState;
import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.historial.historialGastos;
import com.axity.parquededinosaurios.model.Guardia;
import com.axity.parquededinosaurios.model.Tecnico;
import com.axity.parquededinosaurios.model.Trabajador;
import com.axity.parquededinosaurios.model.Turistas;
import com.axity.parquededinosaurios.monitoring.ParkMonitor;
import com.axity.parquededinosaurios.zonas.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class SimulationEngine {

    private final ParqueConfiguracion config ;
    private final int totalSteps;


    public SimulationEngine(ParqueConfiguracion config) {

        this.config = config;
        this.totalSteps = config.getTotalSteps();
    }

    public void run(){

        long seed = config.getSeed();
        Random rng = new Random(seed);
        CsvWriter writer = new CsvWriter("output");
        int batchSize = config.getInt("arrival.batchSize", 5);
        ParkState state = new ParkState();
        state.setRng(rng);
        state.setWriter(writer);

        ZonaDeArribo arrivalZone = new ZonaDeArribo();
        CentralHub centralHub = new CentralHub();
        ZonaBanios bathroomZone = new ZonaBanios();
        RecintoObservacion basicEnclosure = new RecintoObservacion(TipoExperiencia.BASIC);
        RecintoObservacion premiumEnclosure = new RecintoObservacion(TipoExperiencia.PREMIUM);
        RecintoObservacion vipEnclosure = new RecintoObservacion(TipoExperiencia.VIP);
        PlantaEnergia powerPlant = new PlantaEnergia();
        state.setPlantaEnergia(powerPlant);

        Guardia guardia =
                new Guardia(
                        1,
                        "Carlos",
                        5000.0
                );

        Tecnico tecnico =
                new Tecnico(
                        2,
                        "Luis",
                        6000.0
                );

        state.getTrabajadores()
                .add(guardia);

        state.getTrabajadores()
                .add(tecnico);

        EventScheduler scheduler =
                new EventScheduler(
                        seed,
                        totalSteps
                );
        for (int step = 0; step < totalSteps; step++) {
            state.incrementStep();


            List<Turistas> arrived = arrivalZone.ventaBoletos(batchSize,state.getWriter());

            state.getTuristas().addAll(arrived);


            if (state.getPlantaEnergia().isFuncionando()) {

                for (Turistas turista : state.getActiveTourists()) {


                    centralHub.visit(
                            turista,
                            state.getRng(),
                            state.getWriter()
                    );

                    bathroomZone.tryEnter(
                            turista,state.getWriter(),
                            state.getRng()

                    );


                    if (turista.getIdTurista() % 3 == 0) {

                        vipEnclosure.visit(
                                turista,
                                state.getRng(),
                                state.getWriter()
                        );

                    } else if (turista.getIdTurista() % 2 == 0) {

                        premiumEnclosure.visit(
                                turista,
                                state.getRng(),
                                state.getWriter()
                        );

                    } else {

                        basicEnclosure.visit(
                                turista,
                                state.getRng(),
                                state.getWriter()
                        );
                    }
                }
            }

            bathroomZone.tick();

            state.getPlantaEnergia().tick(
                    state.getRng(),
                    state.getWriter()
            );


            scheduler.checkForEvent(
                            (int) state.getCurrentStep()
                    )
                    .ifPresent(event ->
                            event.execute(
                                    state,
                                    state.getRng()
                            )
                    );


            for (Trabajador trabajador
                    : state.getTrabajadores()) {

                if (trabajador instanceof Guardia guard) {

                    guard.recapturarDinosaurios(
                            state.getDinosaurios()
                    );
                }


                if (trabajador instanceof Tecnico tecnic) {

                    if (!state.getPlantaEnergia().isFuncionando()) {

                        tecnic.repararPlanta(
                                state.getPlantaEnergia(),
                                state.getWriter()
                        );
                    }
                }


                state.getWriter()
                        .appendGastos(
                                new historialGastos(
                                        state.getWriter().generarIdGasto(),
                                        "SALARIO",
                                        trabajador.getSalario(),
                                        trabajador.getNombre(),
                                        LocalDateTime.now()
                                )
                        );

                state.addExpense(trabajador.getSalario());
            }
            
            ParkMonitor.displaySnapshot(
                    state
            );
        }
    }

}
