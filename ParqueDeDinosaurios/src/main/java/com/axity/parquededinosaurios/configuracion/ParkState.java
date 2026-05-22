package com.axity.parquededinosaurios.configuracion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.model.Dinosaurio;
import com.axity.parquededinosaurios.model.EstadoDinosaurio;
import com.axity.parquededinosaurios.model.EstadoTurista;
import com.axity.parquededinosaurios.model.Trabajador;
import com.axity.parquededinosaurios.model.Turistas;
import com.axity.parquededinosaurios.zonas.PlantaEnergia;
import com.axity.parquededinosaurios.zonas.ZonaParque;

public class ParkState {

    private List<Turistas> turistas = new ArrayList<>();
    private List<Dinosaurio> dinosaurios = new ArrayList<>();
    private List<Trabajador> trabajadores = new ArrayList<>();
    private List<ZonaParque> zonas = new ArrayList<>();
    private PlantaEnergia plantaEnergia;
    private CsvWriter writer;
    private Random rng;
    private double totalRevenue;
    private double totalExpenses;
    private long currentStep;

    public ParkState() {}

    public List<Turistas> getTuristas() { return turistas; }
    public List<Dinosaurio> getDinosaurios() { return dinosaurios; }
    public List<Trabajador> getTrabajadores() { return trabajadores; }
    public PlantaEnergia getPlantaEnergia() { return plantaEnergia; }
    public void setPlantaEnergia(PlantaEnergia plantaEnergia) { this.plantaEnergia = plantaEnergia; }
    public CsvWriter getWriter() { return writer; }
    public void setWriter(CsvWriter writer) { this.writer = writer; }
    public Random getRng() { return rng; }
    public void setRng(Random rng) { this.rng = rng; }
    public double getTotalRevenue() { return totalRevenue; }
    public double getTotalExpenses() { return totalExpenses; }
    public long getCurrentStep() { return currentStep; }

    public List<Turistas> getActiveTourists() {

        return turistas.stream()
                .filter(t ->
                        t.getEstado()
                                == EstadoTurista.EN_PARQUE
                )
                .collect(Collectors.toList());
    }

    public int countActiveTourists() {
        int total = 0;

        for (Turistas turista : turistas) {

            if (turista.getEstado() == EstadoTurista.EN_PARQUE) {
                total++;
            }
        }
        return total;
    }

    public int countDinosaursInEnclosure() {
        int total = 0;

        for (Dinosaurio dinosaurio : dinosaurios) {

            if (dinosaurio.getEstado() == EstadoDinosaurio.ENCERRADO) {
                total++;
            }
        }
        return total;
    }

    public void addRevenue(double amount) {
        totalRevenue += amount;
    }

    public void incrementStep() {
        currentStep++;
    }

    public void addExpense(double amount) {
        totalExpenses += amount;
    }

    public double getBalance() {
        return totalRevenue - totalExpenses;
    }

}
