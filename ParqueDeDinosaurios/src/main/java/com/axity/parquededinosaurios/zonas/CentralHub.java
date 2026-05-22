package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.historial.historialGanancias;
import com.axity.parquededinosaurios.model.Turistas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CentralHub implements ZonaParque{
    private ParqueConfiguracion configuracion = ParqueConfiguracion.getInstancia();
    private final List<Turistas> turistas =
            new ArrayList<>();

    @Override
    public String getNombre() {
        return "Central Hub";
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
        return configuracion.getInt("hub.maxCapacity",30);
    }

    @Override
    public void entar(Turistas turista) {

        if (!tieneCapacidad()) {
            return;
        }

        turistas.add(turista);
    }

    @Override
    public void salir(Turistas turista) {
        turistas.remove(turista);
    }

    public void visit(
            Turistas turista,
            Random random,
            CsvWriter writer
    ) {

        double probabilidad =
                configuracion.getDouble(
                        "hub.souvenirPurchaseProbability",
                        0.4
                );


        double valorAleatorio =
                random.nextDouble();


        if (valorAleatorio < probabilidad) {

            double precioSouvenir =
                    configuracion.getDouble(
                            "hub.souvenirPrice",
                            15
                    );

            writer.appendGanancia(
                    new historialGanancias(
                            writer.generarIdGanancia(),
                            "SOUVENIR",
                            precioSouvenir,
                            1,
                            getNombre(),
                            LocalDateTime.now()
                    )
            );
        }
    }

}
