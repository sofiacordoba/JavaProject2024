package Logica;

import Modelo.*;

import InterfazGrafica.VentanaPrincipal;

import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Main.
 * Une al dominio con la interfaz gráfica.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Main {
    public static void main(String[] args) {

        Universidad uni = new Universidad();

        //EJEMPLO
        Aula aula1 = new Aula(301, 100);
        uni.agregarAula(aula1);

        LocalDate fi = LocalDate.of(2024, 6, 13);
        LocalDate ff = LocalDate.of(2024, 8, 13);
        LocalTime hi = LocalTime.of(14, 30, 0);
        LocalTime hf = LocalTime.of(16, 30, 0);
        Asignatura asignatura1 = new Asignatura(fi, ff, hi, hf, "Lunes", "ASG101", "Matemáticas", 40);
        uni.agregarAsignatura(asignatura1);

        Reserva reserva1 = new Reserva(fi,hi,hf, asignatura1);
        aula1.agregarReserva(reserva1);

        Reserva reservaW = new Reserva(LocalDate.of(2024, 6, 13), LocalTime.of(15, 0), LocalTime.of(16, 0), asignatura1);
        aula1.agregarReserva(reservaW);

        Aula aula4 = new Aula(181, 100);
        uni.agregarAula(aula4);

        Reserva reservaZ = new Reserva(LocalDate.of(2024, 6, 13), LocalTime.of(15, 0), LocalTime.of(16, 0), asignatura1);
        aula4.agregarReserva(reservaZ);

        fi = LocalDate.of(2024, 6, 13);
        ff = LocalDate.of(2024, 8, 13);
        hi = LocalTime.of(18, 30, 0);
        hf = LocalTime.of(19, 30, 0);
        Asignatura asignatura2 = new Asignatura(fi, ff, hi, hf, "Martes", "ASG102", "FÍSICA", 40);
        uni.agregarAsignatura(asignatura2);

        Reserva reserva2 = new Reserva(fi,hi,hf, asignatura2);
        aula1.agregarReserva(reserva2);

        Aula aula2 = new Aula(302, 100);
        uni.agregarAula(aula2);

        Curso curso1 = new Curso(8, 695.3, "CUR201", "Matemáticas", 20);
        uni.agregarCurso(curso1);

        Reserva reserva7 = new Reserva(fi,hi,hf, curso1);
        aula1.agregarReserva(reserva7);

        fi = LocalDate.of(2024, 6, 13);
        hi = LocalTime.of(14, 30, 0);
        hf = LocalTime.of(16, 30, 0);
        Evento evento1 = new Evento(fi, hi, hf, "EVI102", "entrega diplomas", 40);
        uni.agregarEvento(evento1);

        Externo externo1 = new Externo(500.0, "Tech Corp", LocalDate.of(2024, 6, 13), LocalTime.of(15, 0), LocalTime.of(16, 0), "EVE001", "Exposición de Innovación", 80);
        uni.agregarExterno(externo1);


        Reserva reserva3 = new Reserva(fi,hi,hf, externo1);
        aula2.agregarReserva(reserva3);

        Aula aula3 = new Aula(221, 100);
        uni.agregarAula(aula3);

        Reserva reserva4 = new Reserva(fi,hi,hf, externo1);
        aula3.agregarReserva(reserva4);
        Reserva reserva5 = new Reserva(fi,hi,hf, externo1);
        aula3.agregarReserva(reserva5);
        Reserva reserva6 = new Reserva(fi,hi,hf, externo1);
        aula3.agregarReserva(reserva6);

        // Guardar reportes en archivo de texto
        Reportes reportes = new Reportes();
        reportes.reporteMontoRecaudadoArchivo(uni.getAulas(), "reporteMontoRecaudado.txt");
        reportes.reporteAulasPorReservasArchivo(uni.getAulas(), "reporteAulasPorReservas.txt");


/*

        System.out.println(aula1.toString());

        // Crear reportes
        Reportes reportes = new Reportes();
        reportes.reporteMontoRecaudadoPantalla(uni.getAulas());
        reportes.reporteAulasPorReservasPantalla(uni.getAulas());

        // Guardar reportes en archivo de texto
        reportes.reporteMontoRecaudadoArchivo(uni.getAulas(), "reporteMontoRecaudado.txt");
        reportes.reporteAulasPorReservasArchivo(uni.getAulas(), "reporteAulasPorReservas.txt");*/


        VentanaPrincipal ventana = new VentanaPrincipal(uni);
        ventana.setVisible(true);



        /*
        Universidad universidad = new Universidad();

        // Crear y agregar aulas de ejemplo
        Aula aula1 = new Aula(301, 100);
        Aula aula2 = new Aula(306, 100);
        Aula aula3 = new Aula(201, 100);
        universidad.agregarAula(aula1);
        universidad.agregarAula(aula2);
        universidad.agregarAula(aula3);


        // Crear y agregar instancias de Asignatura y Curso
        Calendar fechaInicioAsig = Calendar.getInstance();
        fechaInicioAsig.set(2024, Calendar.MARCH, 1, 8, 0);
        Calendar fechaFinAsig = Calendar.getInstance();
        fechaFinAsig.set(2024, Calendar.JULY, 31, 10, 0);
        Asignatura asignatura = new Asignatura(fechaInicioAsig, fechaFinAsig, "Lunes", 30, "ASG101", "Matemáticas");

        Curso curso = new Curso(20, 10, 500, "CUR202", "Curso de Java");

        // Crear y agregar reservas de ejemplo
        Calendar fechaReserva1 = Calendar.getInstance();
        fechaReserva1.set(2024, Calendar.MARCH, 1, 8, 0);
        Reserva reserva1 = new Reserva(fechaReserva1, 120, asignatura);

        Calendar fechaReserva2 = Calendar.getInstance();
        fechaReserva2.set(2024, Calendar.MARCH, 1, 10, 0);
        Reserva reserva2 = new Reserva(fechaReserva2, 120, curso);

        aula1.agregarReserva(reserva1);
        aula2.agregarReserva(reserva2);

        // Crear y agregar eventos
        Calendar fechaEvento = Calendar.getInstance();
        fechaEvento.set(2024, Calendar.MARCH, 15, 10, 0);
        Evento evento = new Evento("EVT303", "Conferencia de Tecnología", 200, fechaEvento, 120);
        Externo eventoExterno = new Externo("EXT404", "Feria de Empleo", 300, fechaEvento, 180, "TechCorp", 1500);

        // Crear y agregar reservas de eventos
        Reserva reserva3 = new Reserva(fechaEvento, 120, evento);
        Reserva reserva4 = new Reserva(fechaEvento, 180, eventoExterno);

        aula1.agregarReserva(reserva3);
        aula2.agregarReserva(reserva4);

        // Consultar aulas por número de piso
        List<Aula> aulasPiso3 = universidad.buscarAulasPorNumeroDePiso(3);
        System.out.println("Aulas en el piso 3: " + aulasPiso3.size());

        for (Aula aula : aulasPiso3) {
            System.out.println("Aula ID: " + aula.getID());
            // Mostrar todas sus reservas
            for (Reserva reserva : aula.getReservas()) {
                System.out.println("Reserva ID: " + reserva.getCodReserva() + ", Tipo: " + reserva.getReservador().getClass().getSimpleName() + ", Código Entidad: " + reserva.getReservador().getCodigo());
            }
        }

        // Consultar aulas por código de entidad (reservador)
        List<Aula> aulasAsignatura = universidad.buscarAulasPorReservador(asignatura);
        System.out.println("Aulas con la asignatura ASG101: " + aulasAsignatura.size());

        List<Aula> aulasCurso = universidad.buscarAulasPorReservador(curso);
        System.out.println("Aulas con el curso CUR202: " + aulasCurso.size());

        // Consultar aulas por evento
        List<Aula> aulasEvento = universidad.buscarAulasPorReservador(evento);
        System.out.println("Aulas con el evento EVT303: " + aulasEvento.size());

        List<Aula> aulasEventoExterno = universidad.buscarAulasPorReservador(eventoExterno);
        System.out.println("Aulas con el evento externo EXT404: " + aulasEventoExterno.size());

        // Ejemplo prototipo de llamar a cancelar reserva aula
        //universidad.cancelarReservaAula(301, 1);*/

        /*
                Universidad universidad = new Universidad(); //reportes
                // Crear aulas
                Aula aula1 = new Aula(101, 30);
                Aula aula2 = new Aula(202, 50);
                Aula aula3 = new Aula(303, 40);

                // Agregar aulas a la universidad
                universidad.agregarAula(aula1);
                universidad.agregarAula(aula2);
                universidad.agregarAula(aula3);

                // Crear reservas
                Calendar fecha1Inicio = Calendar.getInstance();
                fecha1Inicio.set(2024, Calendar.JUNE, 12, 10, 0);
                Calendar fecha1Fin = Calendar.getInstance();
                fecha1Fin.set(2024, Calendar.JUNE, 12, 12, 0);

                Asignatura asignatura = new Asignatura(fecha1Inicio, fecha1Fin, "Miércoles", 30, "MAT101", "Matemáticas");
                Reserva reserva1 = new Reserva(fecha1Inicio, 120, asignatura);

                Calendar fecha2Inicio = Calendar.getInstance();
                fecha2Inicio.set(2024, Calendar.JUNE, 12, 12, 0);

                Curso curso = new Curso(10, 5, 50.0, "CUR101", "Curso de Java");
                Reserva reserva2 = new Reserva(fecha2Inicio, 90, curso);

                // Agregar reservas a las aulas
                aula1.agregarReserva(reserva1);
                aula2.agregarReserva(reserva2);

                // Crear reportes
                Reportes reportes = new Reportes();
                reportes.reporteMontoRecaudadoPantalla(universidad.getAulas());
                reportes.reporteAulasPorReservasPantalla(universidad.getAulas());

                // Guardar reportes en archivo de texto
                reportes.reporteMontoRecaudadoArchivo(universidad.getAulas(), "reporteMontoRecaudado.txt");
                reportes.reporteAulasPorReservasArchivo(universidad.getAulas(), "reporteAulasPorReservas.txt");*/
            }
    }
