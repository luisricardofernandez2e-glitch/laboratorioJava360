package com.axity.parquededinosaurios.model;

import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.zonas.PlantaEnergia;

public class Tecnico extends Trabajador{
    public Tecnico(int id, String nombre, Double Salario) {
        super(id, nombre, Salario);
    }

    @Override
    public String getRol() {
        return "Tecnico";
    }

    public void repararPlanta(PlantaEnergia planta, CsvWriter writer){
        planta.repair(writer);
    }
}
