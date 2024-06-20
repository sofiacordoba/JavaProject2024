package Logica;

import Modelo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** La clase Reportes genera reportes por pantalla y por archivo de texto.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Reportes {

    public String reporteMontoRecaudadoPantalla(List<Aula> aulas) {

        StringBuilder sb = new StringBuilder();

        double recaudacionTotalInstitucion = 0; // Variable para la recaudación total de la institución
        double recaudacionPisoActual = 0; // Variables para la recaudación por piso
        int pisoActual = -1;

        for (Aula aula : aulas) {
            int piso = aula.getID() / 100; // Obtener el piso a partir del ID del aula
            double recaudacionAula = aula.calcularRecaudacion();

            // Si cambiamos de piso, mostramos la recaudación del piso anterior
            if (piso != pisoActual) {
                if (pisoActual != -1) { // Si no es el primer piso que procesamos
                    sb.append("Recaudación total del piso " + pisoActual + ": $" + recaudacionPisoActual).append("\n");
                }
                recaudacionPisoActual = 0; // Reiniciamos la recaudación del piso actual
                pisoActual = piso;  // Actualizamos el piso actual
            }
            /*
            // Si cambiamos de piso, mostramos la recaudación del piso anterior
            if (piso != pisoActual && pisoActual != -1) {
                sb.append("Recaudación total del piso " + pisoActual + ": $" + recaudacionPisoActual).append("\n");
                recaudacionPisoActual = 0; // Reiniciamos la recaudación del piso actual
            }*/

            pisoActual = piso;  // Actualizamos el piso actual
            recaudacionPisoActual += recaudacionAula; // Sumamos la recaudación del aula al piso actual
            recaudacionTotalInstitucion += recaudacionAula; // Sumamos la recaudación del aula a la recaudación total de la institución
            sb.append("Recaudación del aula " + aula.getID() + ": $" + recaudacionAula).append("\n");// Mostramos la recaudación del aula

        }

        sb.append("Recaudación total del piso " + pisoActual + ": $" + recaudacionPisoActual).append("\n");// Mostrar la recaudación del último piso procesado
        sb.append("Recaudación total de la institución: $" + recaudacionTotalInstitucion).append("\n");// Mostrar la recaudación total de la institución

        return sb.toString();
    }

    public void reporteMontoRecaudadoArchivo(List<Aula> aulas, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            double recaudacionTotalInstitucion = 0;
            double recaudacionPisoActual = 0;
            int pisoActual = -1;

            for (Aula aula : aulas) {
                int piso = aula.getID() / 100; // Obtener el piso a partir del ID del aula
                double recaudacionAula = aula.calcularRecaudacion();

                if (piso != pisoActual) {
                    if (pisoActual != -1) {
                        writer.write("Recaudación total del piso " + pisoActual + ": $" + recaudacionPisoActual);
                        writer.newLine();
                    }
                    recaudacionPisoActual = 0;
                    pisoActual = piso;
                }

                recaudacionPisoActual += recaudacionAula;
                recaudacionTotalInstitucion += recaudacionAula;
                writer.write("Recaudación del aula " + aula.getID() + ": $" + recaudacionAula);
                writer.newLine();
            }

            // Agregar la recaudación del último piso procesado
            writer.write("Recaudación total del piso " + pisoActual + ": $" + recaudacionPisoActual);
            writer.newLine();
            // Agregar la recaudación total de la institución
            writer.write("Recaudación total de la institución: $" + recaudacionTotalInstitucion);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String reporteAulasPorReservasPantalla(List<Aula> aulas) {
        StringBuilder sb = new StringBuilder();

        List<Aula> aulasAux = new ArrayList<>(aulas);
        aulasAux.sort((a1, a2) -> Integer.compare(a2.getCantidadReservas(), a1.getCantidadReservas())); // Ordenar las aulas por cantidad de reservas en orden descendente

        int totalReservas = 0;
        for (Aula aula : aulasAux) {
            totalReservas += aula.getCantidadReservas();
            sb.append("Aula " + aula.getID() + " tiene " + aula.getCantidadReservas() + " reservas.").append("\n");
        }

        double promedioReservas = (double) totalReservas / aulas.size();
        sb.append("Promedio de reservas por aula: " + promedioReservas).append("\n");

        return sb.toString();
    }

    public void reporteAulasPorReservasArchivo(List<Aula> aulas, String fileName) {
        List<Aula> aulasAux = new ArrayList<>(aulas);
        aulasAux.sort((a1, a2) -> Integer.compare(a2.getCantidadReservas(), a1.getCantidadReservas())); // Ordenar las aulas por cantidad de reservas en orden descendente

        int totalReservas = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Aula aula : aulasAux) {
                totalReservas += aula.getCantidadReservas();
                writer.write("Aula " + aula.getID() + " tiene " + aula.getCantidadReservas() + " reservas.");
                writer.newLine();
            }

            double promedioReservas = (double) totalReservas / aulas.size();;
            writer.write("Promedio de reservas por aula: " + promedioReservas);
            writer.newLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}