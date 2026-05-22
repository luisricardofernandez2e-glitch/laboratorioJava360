package com.axity.parquededinosaurios.monitoring;

import com.axity.parquededinosaurios.configuracion.ParkState;
import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;

public class ParkMonitor {


    public static void displaySnapshot(ParkState state) {
        ParqueConfiguracion config = ParqueConfiguracion.getInstancia();
        int interval = config.getInt("monitoring.intervalSteps", 10);

       
        if (state.getCurrentStep() % interval != 0 && state.getCurrentStep() != config.getTotalSteps()) {
            return;
        }

        double energiaPct = (state.getPlantaEnergia().getEnergiaActual() / 
                             state.getPlantaEnergia().getCapacidadEnergia()) * 100;

        System.out.println("\n==================================================");
        System.out.println("   DINO-PARK MONITOR - STEP " + state.getCurrentStep());
        System.out.println("==================================================");
        System.out.printf("  [POBLACIÓN]   Turistas: %d | Dinos en encierro: %d\n", 
                state.countActiveTourists(), 
                state.countDinosaursInEnclosure());
        System.out.printf("  [ENERGÍA]     Nivel: %.1f%% | Estado: %s\n", 
                energiaPct, 
                state.getPlantaEnergia().isFuncionando() ? "OPERATIVO" : "!!! FALLO !!!");
        System.out.printf("  [FINANZAS]    Ingresos: $%.2f | Gastos: $%.2f\n", 
                state.getTotalRevenue(), 
                state.getTotalExpenses());
        System.out.printf("  [BALANCE]     Neto: $%.2f\n", state.getBalance());
        System.out.println("==================================================\n");
    }
}
