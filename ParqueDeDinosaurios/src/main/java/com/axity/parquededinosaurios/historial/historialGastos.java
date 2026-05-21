package com.axity.parquededinosaurios.historial;

import java.time.LocalDateTime;

public record historialGastos(long id, String tipo, double cantidad,
                              String descripcion, LocalDateTime timestamp) {
    public String toCSVLine(){
        return id+","
                +tipo+","
                +cantidad+","
                +descripcion+","
                +timestamp.toString();
    }
}
