package Logica;

import Excepciones.ExcepcionArchivoInvalido;
import Modelo.*;
import InterfazGrafica.VentanaPrincipal;
import Persistencia.*;


/** La clase Main.
 * Une al dominio con la interfaz gráfica, la carga de datos, la serialización y los reportes.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Main {
    public static void main(String[] args) {
        Universidad uni = Persistencia.DeserializarUniversidad();

        if (uni == null) {
            // Cargar datos desde el archivo XML si no hay datos serializados
            uni = new Universidad();
            LectorXML lector = new LectorXML();
            try {
                lector.cargarDatos("src/persistencia/datos_universidad.xml", uni);
                System.out.println("Datos cargados desde XML correctamente.");
            } catch (ExcepcionArchivoInvalido e) {
                System.err.println("Error: " + e.getMessage());
                return; // Termina el programa si hay un error
            }
        } else {
            System.out.println("Datos deserializados correctamente.");
        }

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

        //interfaz gráfca
        VentanaPrincipal ventana = new VentanaPrincipal(uni);
        ventana.setVisible(true);

        // Guardar reportes en archivo de texto
        Reportes reportes = new Reportes();
        reportes.reporteMontoRecaudadoArchivo(uni.getAulas(), "reporteMontoRecaudado.txt");
        reportes.reporteAulasPorReservasArchivo(uni.getAulas(), "reporteAulasPorReservas.txt");

        // Agregar un shutdown hook para serializar datos al cerrar la aplicación
        Universidad finalUni = uni;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            boolean result = Persistencia.SerializarUniversidad(finalUni);
            if (result) {
                System.out.println("Datos serializados correctamente.");
            }
        }));

            }
    }
