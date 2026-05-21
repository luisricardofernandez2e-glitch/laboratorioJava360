package com.axity.parquededinosaurios.configuracion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ParqueConfiguracion {

    private static final ParqueConfiguracion instancia = new ParqueConfiguracion();
    private final Properties prop;

    private ParqueConfiguracion(){
        prop = new Properties();

        try (InputStream input =
                     getClass()
                             .getClassLoader()
                             .getResourceAsStream("dino.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "No se encontró dino.properties"
                );
            }

            prop.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ParqueConfiguracion getInstancia() {
        return instancia;
    }

    public int getInt(String key, int defaultValue) {

        String value = prop.getProperty(key);

        if (value == null) {
            return defaultValue;
        }

        return Integer.parseInt(value);
    }

    public double getDouble(String key, double defaultValue) {

        String value = prop.getProperty(key);

        if (value == null) {
            return defaultValue;
        }

        return Double.parseDouble(value);
    }

    public String getString(String key, String defaultValue) {

        return prop.getProperty(key, defaultValue);
    }

    public long getSeed() {

        String value =
                prop.getProperty("simulation.seed");

        if (value == null) {
            return 0L;
        }

        return Long.parseLong(value);
    }

    public int getTotalSteps() {

        return getInt("simulation.totalSteps", 100);
    }
}
