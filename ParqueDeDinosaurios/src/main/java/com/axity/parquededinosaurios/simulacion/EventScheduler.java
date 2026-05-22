package com.axity.parquededinosaurios.simulacion;

import com.axity.parquededinosaurios.eventos.BlackoutEvent;
import com.axity.parquededinosaurios.eventos.DinosaurScapeEvent;
import com.axity.parquededinosaurios.eventos.SimulationEvent;
import com.axity.parquededinosaurios.eventos.StormEvent;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class EventScheduler {
    private Map<Integer, SimulationEvent> scheduledEvents ;

    public EventScheduler(long seed, int totalSteps) {
        Random rng = new Random(seed);
        SimulationEvent dinosaurEscape = new DinosaurScapeEvent();

        SimulationEvent blackout = new BlackoutEvent();

        SimulationEvent storm = new StormEvent();


        int escapeStep = rng.nextInt(totalSteps);

        int blackoutStep = rng.nextInt(totalSteps);

        int stormStep = rng.nextInt(totalSteps);


        scheduledEvents.put(
                escapeStep,
                dinosaurEscape
        );

        scheduledEvents.put(
                blackoutStep,
                blackout
        );

        scheduledEvents.put(
                stormStep,
                storm
        );
    }

    public Optional<SimulationEvent> checkForEvent(int step) {

        return Optional.ofNullable(
                scheduledEvents.get(step)
        );
    }
}
