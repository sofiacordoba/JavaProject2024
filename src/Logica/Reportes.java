package Logica;

import Modelo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        double totalInstitucion = 0;
        Map<Integer, Double> montoPorPiso = new HashMap<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Aula aula : aulas) {
                double montoPorAula = 0;
                for (Modelo.Reserva reserva : aula.getReservas()) {
                    montoPorAula += reserva.getReservador().getMonto();
                }

                totalInstitucion += montoPorAula;
                int piso = aula.getID()/100;
                montoPorPiso.put(piso, montoPorPiso.getOrDefault(piso, 0.0) + montoPorAula);

                writer.write("Aula " + aula.getID() + " recaudó: $" + montoPorAula);
                writer.newLine();
            }

            for (Map.Entry<Integer, Double> entry : montoPorPiso.entrySet()) {
                writer.write("Piso " + entry.getKey() + " recaudó: $" + entry.getValue());
                writer.newLine();
            }

            writer.write("Total recaudado por la institución: $" + totalInstitucion);
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

            double promedioReservas = (double) totalReservas;
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    public void reporteMontoRecaudadoPantalla(List<Aula> aulas) {
        double totalInstitucion = 0;
        Map<Integer, Double> montoPorPiso = new HashMap<>();

        for (Aula aula : aulas) {
            double montoPorAula = 0;
            for (Modelo.Reserva reserva : aula.getReservas()) {
                montoPorAula += reserva.getReservador().getMonto(); //monto de la reserva curso o evento ext.
            }

            totalInstitucion += montoPorAula;
            int piso = aula.getID()/100;
            montoPorPiso.put(piso, montoPorPiso.getOrDefault(piso, 0.0) + montoPorAula);

            System.out.println("Aula " + aula.getID() + " recaudó: $" + montoPorAula);
        }

        for (Map.Entry<Integer, Double> entry : montoPorPiso.entrySet()) {
            System.out.println("Piso " + entry.getKey() + " recaudó: $" + entry.getValue());
        }

        System.out.println("Total recaudado por la institución: $" + totalInstitucion);
    }

    public void reporteMontoRecaudadoArchivo(List<Aula> aulas, String fileName) {
        double totalInstitucion = 0;
        Map<Integer, Double> montoPorPiso = new HashMap<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Aula aula : aulas) {
                double montoPorAula = 0;
                for (Modelo.Reserva reserva : aula.getReservas()) {
                    montoPorAula += reserva.getReservador().getMonto();
                }

                totalInstitucion += montoPorAula;
                int piso = aula.getID()/100;
                montoPorPiso.put(piso, montoPorPiso.getOrDefault(piso, 0.0) + montoPorAula);

                writer.write("Aula " + aula.getID() + " recaudó: $" + montoPorAula);
                writer.newLine();
            }

            for (Map.Entry<Integer, Double> entry : montoPorPiso.entrySet()) {
                writer.write("Piso " + entry.getKey() + " recaudó: $" + entry.getValue());
                writer.newLine();
            }

            writer.write("Total recaudado por la institución: $" + totalInstitucion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reporteAulasPorReservasPantalla(List<Aula> aulas) {
        aulas.sort((a1, a2) -> Integer.compare(a2.getCantidadReservas(), a1.getCantidadReservas()));

        int totalReservas = 0;
        for (Aula aula : aulas) {
            totalReservas += aula.getCantidadReservas();
            System.out.println("Aula " + aula.getID() + " tiene " + aula.getCantidadReservas() + " reservas.");
        }

        double promedioReservas = (double) totalReservas / aulas.size();
        System.out.println("Promedio de reservas por aula: " + promedioReservas);
    }

    public void reporteAulasPorReservasArchivo(List<Aula> aulas, String fileName) {
        aulas.sort((a1, a2) -> Integer.compare(a2.getCantidadReservas(), a1.getCantidadReservas()));

        int totalReservas = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Aula aula : aulas) {
                totalReservas += aula.getCantidadReservas();
                writer.write("Aula " + aula.getID() + " tiene " + aula.getCantidadReservas() + " reservas.");
                writer.newLine();
            }

            double promedioReservas = (double) totalReservas;
        }catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}