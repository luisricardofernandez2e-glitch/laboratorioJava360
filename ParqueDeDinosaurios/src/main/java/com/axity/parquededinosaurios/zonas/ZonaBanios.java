package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.historial.historialGanancias;
import com.axity.parquededinosaurios.model.Turistas;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class ZonaBanios implements ZonaParque{
    private ParqueConfiguracion configuracion = ParqueConfiguracion.getInstancia();
    private final Map<Turistas, Integer> turistas =
            new HashMap<>();

    @Override
    public String getNombre() {
        return "Zona de Baños";
    }

    @Override
    public boolean tieneCapacidad() {
        return getCapacidadMaxima() > getOcupacionActual();
    }

    @Override
    public int getOcupacionActual() {
        return turistas.size();
    }

    @Override
    public int getCapacidadMaxima() {
        return configuracion.getInt("bathroom.maxCapacity",10);
    }

    @Override
    public void entar(Turistas turista) {
        if (!tieneCapacidad()) {
            return;
        }

        int duracion =
                configuracion.getInt(
                        "bathroom.useDurationSteps",
                        3
                );

        turistas.put(
                turista,
                duracion
        );
    }

    @Override
    public void salir(Turistas turista) {
        turistas.remove(turista);
    }


    public boolean tryEnter(Turistas turista, CsvWriter writer, Random random) {
        if (!tieneCapacidad()) {
            return false;
        }

        entar(turista);

        double probabilidad =
                configuracion.getDouble(
                        "bathroom.spaPurchaseProbability",
                        0.2
                );

        if (random.nextDouble() < probabilidad) {

            double precio =
                    configuracion.getDouble(
                            "bathroom.spaPrice",
                            100
                    );

            writer.appendGanancia(
                    new historialGanancias(
                            writer.generarIdGanancia(),
                            "SPA",
                            precio,
                            1,
                            getNombre(),
                            LocalDateTime.now()
                    )
            );
        }

        return true;
    }

    public void tick() {

        Iterator<Map.Entry<Turistas, Integer>> iterator =
                turistas.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Turistas, Integer> entry =
                    iterator.next();

            int restante =
                    entry.getValue() - 1;

            if (restante > 0) {

                entry.setValue(restante);

            } else {

                iterator.remove();
            }
        }
    }


}
