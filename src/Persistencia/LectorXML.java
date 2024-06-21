package Persistencia;

import Modelo.*;
import Excepciones.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LectorXML {
    public void cargarDatos(String rutaArchivo, Universidad universidad) throws ExcepcionArchivoInvalido
    {

        try
        {
            File archivo = new File(rutaArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Leer y agregar Aulas
            NodeList nListAulas = doc.getElementsByTagName("Aula");
            for (int temp = 0; temp < nListAulas.getLength(); temp++) {
                Element elemento = (Element) nListAulas.item(temp);
                int ID = Integer.parseInt(elemento.getElementsByTagName("ID").item(0).getTextContent());
                int capacidad = Integer.parseInt(elemento.getElementsByTagName("capacidad").item(0).getTextContent());
                Aula aula = new Aula(ID, capacidad);
                validarAula(aula);  // Validación
                universidad.agregarAula(aula);
            }

            // Leer y agregar Asignaturas
            NodeList nListAsignaturas = doc.getElementsByTagName("Asignatura");
            for (int temp = 0; temp < nListAsignaturas.getLength(); temp++) {
                Element elemento = (Element) nListAsignaturas.item(temp);
                LocalDate fechaI = LocalDate.parse(elemento.getElementsByTagName("fechaI").item(0).getTextContent(), dateFormatter);
                LocalDate fechaF = LocalDate.parse(elemento.getElementsByTagName("fechaF").item(0).getTextContent(), dateFormatter);
                LocalTime horaI = LocalTime.parse(elemento.getElementsByTagName("horaI").item(0).getTextContent(), timeFormatter);
                LocalTime horaF = LocalTime.parse(elemento.getElementsByTagName("horaF").item(0).getTextContent(), timeFormatter);
                String diaSemana = elemento.getElementsByTagName("diaSemana").item(0).getTextContent();
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Asignatura asignatura = new Asignatura(fechaI, fechaF, horaI, horaF, diaSemana, codigo,descripcion, cantParticipantes);
                validarAsignatura(asignatura);
                universidad.agregarAsignatura(asignatura);
            }

            // Leer y agregar Cursos
            NodeList nListCursos = doc.getElementsByTagName("Curso");
            for (int temp = 0; temp < nListCursos.getLength(); temp++) {
                Element elemento = (Element) nListCursos.item(temp);
                int cantClases = Integer.parseInt(elemento.getElementsByTagName("cantClases").item(0).getTextContent());
                double costo = Double.parseDouble(elemento.getElementsByTagName("costo").item(0).getTextContent());
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Curso curso = new Curso (cantClases, costo, codigo, descripcion, cantParticipantes);
                validarCurso(curso);
                universidad.agregarCurso(curso);
            }

            // Leer y agregar Eventos internos
            NodeList nListInternos = doc.getElementsByTagName("Interno");
            for (int temp = 0; temp < nListInternos.getLength(); temp++) {
                Element elemento = (Element) nListInternos.item(temp);
                LocalDate fecha = LocalDate.parse(elemento.getElementsByTagName("fecha").item(0).getTextContent(), dateFormatter);
                LocalTime horaI = LocalTime.parse(elemento.getElementsByTagName("horaI").item(0).getTextContent(), timeFormatter);
                LocalTime horaF = LocalTime.parse(elemento.getElementsByTagName("horaF").item(0).getTextContent(), timeFormatter);
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Evento interno = new Evento (fecha, horaI, horaF, codigo, descripcion, cantParticipantes);
                validarEvento(interno);
                universidad.agregarEvento(interno);
            }

            // Leer y agregar Eventos externos
            NodeList nListExternos = doc.getElementsByTagName("Externo");
            for (int temp = 0; temp < nListExternos.getLength(); temp++) {
                Element elemento = (Element) nListExternos.item(temp);
                double cobroAlq = Double.parseDouble(elemento.getElementsByTagName("cobroAlq").item(0).getTextContent());
                String org = elemento.getElementsByTagName("org").item(0).getTextContent();
                LocalDate fecha = LocalDate.parse(elemento.getElementsByTagName("fecha").item(0).getTextContent(), dateFormatter);
                LocalTime horaI = LocalTime.parse(elemento.getElementsByTagName("horaI").item(0).getTextContent(), timeFormatter);
                LocalTime horaF = LocalTime.parse(elemento.getElementsByTagName("horaF").item(0).getTextContent(), timeFormatter);
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Externo externo = new Externo (cobroAlq, org, fecha, horaI, horaF, codigo, descripcion, cantParticipantes);
                validarEvento(externo);
                universidad.agregarEvento(externo);
            }

        } catch (Exception e) {
            throw new ExcepcionArchivoInvalido("Error al cargar el archivo XML: " + e.getMessage());}

    }

    private void validarAula(Aula aula) throws ExcepcionArchivoInvalido {
        if (aula.getID() <= 0) {
            throw new ExcepcionArchivoInvalido("El ID del aula debe ser un número positivo.");
        }
        if (aula.getCapacidad() <= 0) {
            throw new ExcepcionArchivoInvalido("La capacidad debe ser un número positivo.");
        }
    }

    private void validarAsignatura(Asignatura asig) throws ExcepcionArchivoInvalido {
        if (asig.getCodigo() == null || asig.getCodigo().isEmpty()) {
            throw new ExcepcionArchivoInvalido("El código de la asignatura no puede estar vacío.");
        }
        if (asig.getFechaInicio() == null) {
            throw new ExcepcionArchivoInvalido("La fecha no puede ser nula.");
        }
        if (asig.getFechaFin() == null) {
            throw new ExcepcionArchivoInvalido("La fecha no puede ser nula.");
        }
        if (asig.getHoraInicio() == null) {
            throw new ExcepcionArchivoInvalido("La hora no puede ser nula.");
        }
        if (asig.getHoraFin() == null) {
            throw new ExcepcionArchivoInvalido("La hora no puede ser nula.");
        }
        if (asig.getCantParticipantes() <= 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de participantes debe ser un número positivo.");
        }
        if (asig.getDiaSemana() == null || asig.getDiaSemana().isEmpty()) {
            throw new ExcepcionArchivoInvalido("El día de la semana no puede estar vacío.");
        }
        if (asig.getDescripcion() == null || asig.getDescripcion().isEmpty()) {
            throw new ExcepcionArchivoInvalido("La descripción no puede estar vacía.");
        }
    }

    private void validarCurso(Curso curso) throws ExcepcionArchivoInvalido {
        if (curso.getCodigo() == null || curso.getCodigo().isEmpty()) {
            throw new ExcepcionArchivoInvalido("El código de la asignatura no puede estar vacío.");
        }
        if (curso.getCantParticipantes() <= 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de participantes debe ser un número positivo.");
        }
        if (curso.getDescripcion() == null || curso.getDescripcion().isEmpty()) {
            throw new ExcepcionArchivoInvalido("La descripción no puede estar vacía.");
        }
        if (curso.getCantClases() <= 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de clases debe ser un número positivo.");
        }
        if (curso.getCosto() <= 0) {
            throw new ExcepcionArchivoInvalido("El costo de clases debe ser un número positivo.");
        }
    }

    private void validarEvento(Evento evento) throws ExcepcionArchivoInvalido {
        if (evento.getCodigo() == null || evento.getCodigo().isEmpty()) {
            throw new ExcepcionArchivoInvalido("El código de la asignatura no puede estar vacío.");
        }
        if (evento.getCantParticipantes() <= 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de participantes debe ser un número positivo.");
        }
        if (evento.getDescripcion() == null || evento.getDescripcion().isEmpty()) {
            throw new ExcepcionArchivoInvalido("La descripción no puede estar vacía.");
        }
        if (evento.getFecha() == null) {
            throw new ExcepcionArchivoInvalido("La fecha no puede ser nula.");
        }
        if (evento.getHoraInicio() == null) {
            throw new ExcepcionArchivoInvalido("La hora no puede ser nula.");
        }
        if (evento.getHoraFin() == null) {
            throw new ExcepcionArchivoInvalido("La hora no puede ser nula.");
        }

    }


}
