package com.axity.parquededinosaurios.zonas;

import java.time.LocalDateTime;
import java.util.Random;

import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.historial.historialEvento;
import com.axity.parquededinosaurios.historial.historialGastos;
import com.axity.parquededinosaurios.model.Turistas;

public class PlantaEnergia implements ZonaParque{

    private ParqueConfiguracion configuracion = ParqueConfiguracion.getInstancia();
    private boolean funcionando = true;
    private double energiaActual;
    private double capacidadEnergia;

    public PlantaEnergia() {
        this.capacidadEnergia = configuracion.getDouble("powerplant.initialEnergy", 100.0);
        this.energiaActual = capacidadEnergia;
    }

    public boolean isFuncionando() { return funcionando; }
    public double getEnergiaActual() { return energiaActual; }
    public double getCapacidadEnergia() { return capacidadEnergia; }

    @Override
    public String getNombre() {
        return "Planta de Energia";
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
        throw new UnsupportedOperationException(
                "La planta eléctrica no acepta turistas"
        );
    }

    @Override
    public void salir(Turistas turista) {
        throw new UnsupportedOperationException(
                "La planta eléctrica no acepta turistas"
        );
    }

    public void tick(
            Random random,
            CsvWriter writer
    ) {

        if (!funcionando) {
            return;
        }

        // Consumir energía
        double consumo =
                configuracion.getDouble(
                        "powerplant.consumptionPerStep",
                        1.5
                );

        energiaActual -= consumo;

        System.out.println(
                "Energía restante: "
                        + energiaActual
        );

        // Verificar fallo
        double probabilidad =
                configuracion.getDouble(
                        "powerplant.failureProbability",
                        0.05
                );

        if (random.nextDouble() < probabilidad) {

            triggerFailure(writer);
        }
    }

    public void triggerFailure(
            CsvWriter writer
    ) {

        funcionando = false;

        writer.appendEvento(
                new historialEvento(
                        writer.generarIdEvento(),
                        "POWER_FAILURE",
                        "La planta eléctrica falló",
                        getNombre(),
                        LocalDateTime.now()
                )
        );

        System.out.println(
                "Planta eléctrica dañada"
        );
    }


    public void repair(
            CsvWriter writer
    ) {

        funcionando = true;

        double costo =
                configuracion.getDouble(
                        "powerplant.repairCost",
                        500.0
                );

        writer.appendGastos(
                new historialGastos(
                        writer.generarIdGasto(),
                        "REPARACION",
                        costo,
                        "Reparación planta eléctrica",
                        LocalDateTime.now()
                )
        );

        System.out.println(
                "PowerPlant reparada"
        );
    }
}
