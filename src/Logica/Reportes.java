package Logica;

import Modelo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** La clase Reportes genera reportes por pantalla y por archivo de texto.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Reportes {

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