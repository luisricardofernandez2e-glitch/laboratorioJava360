package com.axity.parquededinosaurios.zonas;

import com.axity.parquededinosaurios.configuracion.ParkState;
import com.axity.parquededinosaurios.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkStateTest {

    private ParkState state;

    @BeforeEach
    void setUp() {
        state = new ParkState();
    }


    @Test
    void shouldIncrementStepCorrectly() {

        assertEquals(0, state.getCurrentStep());

        state.incrementStep();
        state.incrementStep();

        assertEquals(2, state.getCurrentStep());
    }


    @Test
    void shouldAddRevenueCorrectly() {

        state.addRevenue(100);
        state.addRevenue(50);

        assertEquals(150, state.getTotalRevenue());
    }

    @Test
    void shouldAddExpenseCorrectly() {

        state.addExpense(40);
        state.addExpense(10);

        assertEquals(50, state.getTotalExpenses());
    }

    @Test
    void shouldCalculateBalanceCorrectly() {

        state.addRevenue(200);
        state.addExpense(75);

        assertEquals(125, state.getBalance());
    }


    @Test
    void shouldCountActiveTouristsCorrectly() {

        Turistas t1 = new Turistas();
        Turistas t2 = new Turistas();
        Turistas t3 = new Turistas();

        t1.setEstado(EstadoTurista.EN_PARQUE);
        t2.setEstado(EstadoTurista.EN_PARQUE);
        t3.setEstado(EstadoTurista.AFUERA_PARQUE);

        state.getTuristas().addAll(List.of(t1, t2, t3));

        assertEquals(2, state.countActiveTourists());
        assertEquals(2, state.getActiveTourists().size());
    }


    @Test
    void shouldCountDinosaursInEnclosure() {

        Dinosaurio d1 = new DinoCarnivoro();
        Dinosaurio d2 = new DinoCarnivoro();
        Dinosaurio d3 = new DinoCarnivoro();

        d1.setEstado(EstadoDinosaurio.ENCERRADO);
        d2.setEstado(EstadoDinosaurio.ENCERRADO);
        d3.setEstado(EstadoDinosaurio.ESCAPADO);

        state.getDinosaurios().addAll(List.of(d1, d2, d3));

        assertEquals(2, state.countDinosaursInEnclosure());
    }

    @Test
    void shouldReturnOnlyActiveTourists() {

        Turistas t1 = new Turistas();
        Turistas t2 = new Turistas();

        t1.setEstado(EstadoTurista.EN_PARQUE);
        t2.setEstado(EstadoTurista.AFUERA_PARQUE);

        state.getTuristas().addAll(List.of(t1, t2));

        List<Turistas> active = state.getActiveTourists();

        assertEquals(1, active.size());
        assertEquals(EstadoTurista.EN_PARQUE, active.get(0).getEstado());
    }
}