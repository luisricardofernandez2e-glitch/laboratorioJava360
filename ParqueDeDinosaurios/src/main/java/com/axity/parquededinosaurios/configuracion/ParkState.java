package com.axity.parquededinosaurios.configuracion;

import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.model.Dinosaurio;
import com.axity.parquededinosaurios.model.Turistas;
import com.axity.parquededinosaurios.zonas.PlantaEnergia;
import com.axity.parquededinosaurios.zonas.ZonaParque;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkState {

    private List<Dinosaurio> dinosaurios;
    private List<Turistas> turistas;
    private List<ZonaParque> zonas;
    private PlantaEnergia powerPlant;
    private double dinero;
    private CsvWriter writer;
    private long currentStep;

}
