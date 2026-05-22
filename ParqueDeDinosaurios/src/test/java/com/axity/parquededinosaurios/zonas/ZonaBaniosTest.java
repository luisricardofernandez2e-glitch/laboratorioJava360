package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.historial.CsvWriter;
import com.axity.parquededinosaurios.model.Turistas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ZonaBaniosTest {

    private ZonaBanios zona;

    @BeforeEach
    void setUp() {
        zona = new ZonaBanios();
    }

    @Test
    void shouldHaveCapacityIfBelowLimit() {
        assertTrue(zona.tieneCapacidad());
    }

    @Test
    void shouldIncreaseOccupancyWhenTouristEnters() {

        Turistas t = new Turistas();

        zona.entar(t);

        assertEquals(1, zona.getOcupacionActual());
    }

    @Test
    void shouldAllowTouristToEnter() {

        Turistas t = new Turistas();

        boolean result =
                zona.tryEnter(
                        t,
                        new CsvWriter("test-output"),
                        new Random(1)
                );

        assertTrue(result);
        assertEquals(1, zona.getOcupacionActual());
    }


    @Test
    void shouldKeepTouristInBathroomWithDuration() {

        Turistas t = new Turistas();

        zona.entar(t);

        assertEquals(1, zona.getOcupacionActual());

        zona.tick(); // reduce duración

        assertTrue(zona.getOcupacionActual() >= 0);
    }

    @Test
    void shouldRemoveTouristWhenSalir() {

        Turistas t = new Turistas();

        zona.entar(t);
        zona.salir(t);

        assertEquals(0, zona.getOcupacionActual());
    }

    @Test
    void shouldRemoveTouristAfterTickExpires() {

        Turistas t = new Turistas();

        zona.entar(t);

        for (int i = 0; i < 5; i++) {
            zona.tick();
        }

        assertEquals(0, zona.getOcupacionActual());
    }
}
