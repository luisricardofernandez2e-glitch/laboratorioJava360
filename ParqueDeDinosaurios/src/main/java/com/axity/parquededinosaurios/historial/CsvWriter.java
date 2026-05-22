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

    private static long idGanancia = 1;
    private static long idGasto = 1;
    private static long idEvento = 1;

    public CsvWriter(String outputDir) {

        try {


            Files.createDirectories(Path.of(outputDir));


            ganaciasFile = Path.of(outputDir, "ganancias.csv");
            gastosFile = Path.of(outputDir, "gastos.csv");
            eventoFile = Path.of(outputDir, "eventos.csv");


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


    public long generarIdGanancia() {
        return idGanancia++;
    }

    public long generarIdGasto() {
        return idGasto++;
    }

    public long generarIdEvento() {
        return idEvento++;
    }

    public void appendGanancia(historialGanancias r) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(ganaciasFile.toFile(), true))) {

            writer.println(r.toCSVLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void appendGastos(historialGastos e) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(gastosFile.toFile(), true))) {

            writer.println(e.toCSVLine());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void appendEvento(historialEvento ev) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(eventoFile.toFile(), true))) {

            writer.println(ev.toCSVLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
