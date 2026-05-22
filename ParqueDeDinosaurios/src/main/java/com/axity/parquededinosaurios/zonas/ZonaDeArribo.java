package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.historial.historialGanancias;
import com.axity.parquededinosaurios.model.EstadoTurista;
import com.axity.parquededinosaurios.model.Turistas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ZonaDeArribo implements ZonaParque {

    private final List<Turistas> turistas =
            new ArrayList<>();


    private ParqueConfiguracion configuracion = ParqueConfiguracion.getInstancia();
    @Override
    public String getNombre() {
        return "Zona de Arribo";
    }

    @Override
    public boolean tieneCapacidad() {
        if(getCapacidadMaxima()>getOcupacionActual()){return true;}
        return false;
    }

    @Override
    public int getOcupacionActual() {
        return turistas.size();
    }

    @Override
    public int getCapacidadMaxima() {
        return configuracion.getInt("arrival.maxCapacity",30);
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

    public List<Turistas> ventaBoletos(
            int cantidad,
            CsvWriter writer
    ) {

        List<Turistas> nuevos = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {

            Turistas turista = new Turistas();

            turista.setEstado(EstadoTurista.EN_PARQUE);

            nuevos.add(turista);
        }

        writer.appendGanancia(
                new historialGanancias(
                        writer.generarIdGanancia(),
                        "Venta boleto",
                        cantidad * configuracion.getDouble(
                                "arrival.ticketPrice",
                                25.0
                        ),
                        cantidad,
                        getNombre(),
                        LocalDateTime.now()
                )
        );

        return nuevos;
    }
}
