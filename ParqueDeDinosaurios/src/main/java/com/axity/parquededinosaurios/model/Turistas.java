package com.axity.parquededinosaurios.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Turistas {
    private int idTurista;
    private EstadoTurista estado;
    private List<String> zonasVisitadas;
    private Double dineroGastado;

    public String lugarEnVisita(String lugar){
        zonasVisitadas.add(lugar);
        return zonasVisitadas.get(zonasVisitadas.size()-1);
    }

    public Double dineroGastado(Double dinero){
        return dineroGastado + dinero;
    }
}
