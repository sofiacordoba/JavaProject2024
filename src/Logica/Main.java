package Logica;

import Excepciones.ExcepcionArchivoInvalido;
import Modelo.*;
import InterfazGrafica.VentanaPrincipal;
import Persistencia.*;

import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Main.
 * Une al dominio con la interfaz gráfica, la carga de datos y los reportes.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Main {
    public static void main(String[] args) {
        Universidad uni = new Universidad();

        //Cargar datos
        LectorXML lector = new LectorXML();
        try {
            lector.cargarDatos("src/persistencia/datos_universidad.xml", uni);
            // Mostrar los datos cargados
            System.out.println("Aulas:");
            for (Aula aula : uni.getAulas()) {
                System.out.println(aula.getID());
            }
            System.out.println("Asignaturas:");
            for (Asignatura asig : uni.getAsignaturas()) {
                System.out.println(asig.getCodigo());
            }
            System.out.println("Cursos:");
            for (Curso curso : uni.getCursos()) {
                System.out.println(curso.getCodigo());
            }
            System.out.println("Eventos:");
            for (Evento evento : uni.getEventos()) {
                System.out.println(evento.getCodigo());
            }
        } catch (ExcepcionArchivoInvalido e) {
            System.err.println("Error: " + e.getMessage());
        }

/*
        //EJEMPLO de reserva

        Reserva reservaW = new Reserva(LocalDate.of(2024, 6, 13), LocalTime.of(15, 0), LocalTime.of(16, 0), asignatura1);
        aula1.agregarReserva(reservaW);
*/

        // Guardar reportes en archivo de texto
        Reportes reportes = new Reportes();
        reportes.reporteMontoRecaudadoArchivo(uni.getAulas(), "reporteMontoRecaudado.txt");
        reportes.reporteAulasPorReservasArchivo(uni.getAulas(), "reporteAulasPorReservas.txt");

        //interfaz gráfca
        VentanaPrincipal ventana = new VentanaPrincipal(uni);
        ventana.setVisible(true);

            }
    }
