package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.historial.historialGanancias;
import com.axity.parquededinosaurios.model.Turistas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecintoObservacion implements ZonaParque{

    private ParqueConfiguracion configuracion = ParqueConfiguracion.getInstancia();
    private final List<Turistas> turistas =
            new ArrayList<>();
    private final TipoExperiencia tipo;

    public RecintoObservacion(TipoExperiencia tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getNombre() {
        return "Recinto de Observacion";
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
        return switch (tipo) {

            case BASIC ->
                    configuracion.getInt(
                            "enclosure.basic.maxVisitors",
                            20
                    );

            case PREMIUM ->
                    configuracion.getInt(
                            "enclosure.premium.maxVisitors",
                            12
                    );

            case VIP ->
                    configuracion.getInt(
                            "enclosure.vip.maxVisitors",
                            5
                    );
        };
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

    public double getCostoEntrada() {

        return switch (tipo) {

            case BASIC ->
                    configuracion.getDouble(
                            "enclosure.basic.entryFee",
                            10.0
                    );

            case PREMIUM ->
                    configuracion.getDouble(
                            "enclosure.premium.entryFee",
                            30.0
                    );

            case VIP ->
                    configuracion.getDouble(
                            "enclosure.vip.entryFee",
                            75.0
                    );
        };
    }

    public void visit(
            Turistas turista,
            Random random,
            CsvWriter writer
    ) {

        if (!tieneCapacidad()) {
            return;
        }

        entar(turista);

        double precio = getCostoEntrada();

        writer.appendGanancia(
                new historialGanancias(
                        writer.generarIdGanancia(),
                        tipo.name(),
                        precio,
                        1,
                        getNombre(),
                        LocalDateTime.now()
                )
        );

        int score =
                conductSurvey(
                        turista,
                        random
                );

        System.out.println(
                "Encuesta: " + score
        );
    }

    public int conductSurvey(
            Turistas turista,
            Random random
    ) {

        int min = tipo.getMinScore();

        int max = tipo.getMaxScore();

        return random.nextInt(
                max - min + 1
        ) + min;
    }
}
