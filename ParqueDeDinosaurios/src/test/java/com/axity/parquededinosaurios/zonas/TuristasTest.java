package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.model.Turistas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TuristasTest {

    @Test
    void testConstructorYIDIncremental() {
        Turistas t1 = new Turistas();
        Turistas t2 = new Turistas();
        
        assertTrue(t2.getIdTurista() > t1.getIdTurista(), "El ID debe ser incremental");
        assertNotNull(t1.getZonasVisitadas());
        assertEquals(0.0, t1.getDineroGastado());
    }

    @Test
    void testRegistrarGasto() {
        Turistas turista = new Turistas();
        turista.registrarGasto(100.0);
        turista.registrarGasto(50.5);
        
        assertEquals(150.5, turista.getDineroGastado(), "La suma de gastos es incorrecta");
    }

    @Test
    void testLugarEnVisita() {
        Turistas turista = new Turistas();
        String lugar = "T-Rex Enclosure";
        
        String registrado = turista.lugarEnVisita(lugar);
        
        assertEquals(lugar, registrado);
        assertEquals(1, turista.getZonasVisitadas().size());
        assertEquals(lugar, turista.getZonasVisitadas().get(0));
    }
}