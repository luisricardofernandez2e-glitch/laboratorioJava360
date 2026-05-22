package com.axity.parquededinosaurios.model;

import java.util.ArrayList;
import java.util.List;

public class Turistas {
    private static int nextId = 1;

    private int idTurista;
    private EstadoTurista estado;
    private List<String> zonasVisitadas = new ArrayList<>();
    private Double dineroGastado = 0.0;

    public Turistas() {
        this.idTurista = nextId++;
        this.zonasVisitadas = new ArrayList<>();
        this.dineroGastado = 0.0;
    }

    public int getIdTurista() { return idTurista; }
    public void setIdTurista(int idTurista) { this.idTurista = idTurista; }

    public EstadoTurista getEstado() { return estado; }
    public void setEstado(EstadoTurista estado) { this.estado = estado; }

    public List<String> getZonasVisitadas() { return zonasVisitadas; }
    public void setZonasVisitadas(List<String> zonasVisitadas) { this.zonasVisitadas = zonasVisitadas; }

    public Double getDineroGastado() { return dineroGastado; }
    public void setDineroGastado(Double dineroGastado) { this.dineroGastado = dineroGastado; }

    public String lugarEnVisita(String lugar){
        zonasVisitadas.add(lugar);
        return zonasVisitadas.get(zonasVisitadas.size()-1);
    }

    public void registrarGasto(Double dinero){
        this.dineroGastado += dinero;
    }
}
