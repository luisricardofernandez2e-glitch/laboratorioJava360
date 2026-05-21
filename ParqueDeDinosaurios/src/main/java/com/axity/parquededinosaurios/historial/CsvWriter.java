package com.axity.parquededinosaurios.historial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class CsvWriter {
    private final Path ganaciasFile;
    private final Path gastosFile;
    private final Path eventoFile;

    public CsvWriter(String outputDir) {

        try {

            // Crear carpeta
            Files.createDirectories(Path.of(outputDir));

            // Crear rutas
            ganaciasFile = Path.of(outputDir, "ganancias.csv");
            gastosFile = Path.of(outputDir, "gastos.csv");
            eventoFile = Path.of(outputDir, "eventos.csv");

            // Inicializar archivos con headers
            initFile(
                    ganaciasFile,
                    "id,tipo,cantidad,idTourista,zona,timestamp"
            );

            initFile(
                    gastosFile,
                    "id,tipo,cantidad,descripcion,timestamp"
            );

            initFile(
                    eventoFile,
                    "step,eventName,description,affectedEntities,timestamp"
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Crear archivo y escribir header
    // false = sobrescribir
    private void initFile(Path path, String header) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(path.toFile(), false))) {

            writer.println(header);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // =========================
    // Agregar ingresos
    // =========================

    public void appendRevenue(historialGanancias r) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(ganaciasFile.toFile(), true))) {

            writer.println(r.toCSVLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // =========================
    // Agregar gastos
    // =========================

    public void appendExpense(historialGastos e) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(gastosFile.toFile(), true))) {

            writer.println(e.toCSVLine());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // =========================
    // Agregar eventos
    // =========================

    public void appendEvent(historialEvento ev) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(eventoFile.toFile(), true))) {

            writer.println(ev.toCSVLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
