package com.axity.parquededinosaurios.configuracion;

import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.model.*;
import com.axity.parquededinosaurios.zonas.PlantaEnergia;
import com.axity.parquededinosaurios.zonas.ZonaParque;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkState {

    private List<Turistas> turistas = new ArrayList<>();
    private List<Dinosaurio> dinosaurios = new ArrayList<>();
    private List<Trabajador> trabajadores = new ArrayList<>();
    private List<ZonaParque> zonas = new ArrayList<>();
    private CsvWriter writer;
    private Random rng;
    private double totalRevenue;
    private double totalExpenses;
    private long currentStep;

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

    public void addExpense(double amount) {
        totalExpenses += amount;
    }

    public double getBalance() {
        return totalRevenue - totalExpenses;
    }

}
