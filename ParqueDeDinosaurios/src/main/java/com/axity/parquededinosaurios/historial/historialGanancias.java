package com.axity.parquededinosaurios.historial;

import java.time.LocalDateTime;

public record historialGanancias(long id, String type, double precio,
                                 int cantidadTuristas, String zona, LocalDateTime timestamp) {
    public String toCSVLine(){
        return id+","
                +type+","
                +precio+","
                +cantidadTuristas+","
                +zona+","
                +timestamp.toString();
    }
}
