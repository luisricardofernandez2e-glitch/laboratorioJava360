package com.axity.parquededinosaurios;

import com.axity.parquededinosaurios.configuracion.ParqueConfiguracion;
import com.axity.parquededinosaurios.simulacion.SimulationEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParqueDeDinosauriosApplication {

    public static void main(String[] args) {
        ParqueConfiguracion config = ParqueConfiguracion.getInstancia();
        new SimulationEngine(config).run();
    }

}
