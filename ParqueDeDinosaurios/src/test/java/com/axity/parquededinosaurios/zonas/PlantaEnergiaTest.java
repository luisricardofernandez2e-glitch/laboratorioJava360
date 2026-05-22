package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.historial.CsvWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlantaEnergiaTest {

    private PlantaEnergia plant;

    @BeforeEach
    void setUp() {
        plant = new PlantaEnergia();
    }

    @Test
    void shouldStartWorking() {
        assertTrue(plant.isFuncionando());
    }

    @Test
    void shouldHaveInitialEnergyGreaterThanZero() {
        assertTrue(plant.getEnergiaActual() > 0);
    }

    @Test
    void shouldReduceEnergyOnTick() {

        double initial = plant.getEnergiaActual();

        plant.tick(new Random(1), new CsvWriter("test-output"));

        assertTrue(plant.getEnergiaActual() < initial);
    }


    @Test
    void shouldStopWorkingWhenFailureTriggered() {

        plant.triggerFailure(new CsvWriter("test-output"));

        assertFalse(plant.isFuncionando());
    }


    @Test
    void shouldRepairPlantAndSetWorkingTrue() {

        plant.triggerFailure(new CsvWriter("test-output"));

        assertFalse(plant.isFuncionando());

        plant.repair(new CsvWriter("test-output"));

        assertTrue(plant.isFuncionando());
    }

    @Test
    void shouldNotConsumeEnergyWhenNotWorking() {

        plant.triggerFailure(new CsvWriter("test-output"));

        double energyAfterFailure = plant.getEnergiaActual();

        plant.tick(new Random(1), new CsvWriter("test-output"));

        assertEquals(energyAfterFailure, plant.getEnergiaActual());
    }

    @Test
    void shouldThrowExceptionForTourists() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> plant.entar(null)
        );
    }
}