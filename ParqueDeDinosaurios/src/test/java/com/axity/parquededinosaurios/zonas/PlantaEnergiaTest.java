package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.historial.CsvWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlantaEnergiaTest {

    @Mock
    private CsvWriter writer;

    @Test
    void testRepairSetsFuncionandoTrue() {
        PlantaEnergia planta = new PlantaEnergia();
        // Forzar fallo inicial
        planta.triggerFailure(writer);
        assertFalse(planta.isFuncionando());

        planta.repair(writer);
        
        assertTrue(planta.isFuncionando(), "La planta debería estar funcionando tras la reparación");
        verify(writer).appendGastos(any());
    }

    @Test
    void testTriggerFailureSetsFuncionandoFalse() {
        PlantaEnergia planta = new PlantaEnergia();
        assertTrue(planta.isFuncionando());

        planta.triggerFailure(writer);
        
        assertFalse(planta.isFuncionando(), "La planta debería dejar de funcionar tras un trigger de fallo");
        verify(writer).appendEvento(any());
    }
}