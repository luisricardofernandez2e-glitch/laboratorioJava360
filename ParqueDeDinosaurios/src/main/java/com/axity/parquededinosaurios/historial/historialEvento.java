package com.axity.parquededinosaurios.historial;

import java.time.LocalDateTime;

public record historialEvento(long paso, String nombreEvento, String descripcion,
                              String entidadesAfectadas, LocalDateTime timestamp) {
    public String toCSVLine(){
        return paso+","
                +nombreEvento+","
                +descripcion+","
                +entidadesAfectadas+","
                +timestamp.toString();
    }
}
