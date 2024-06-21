package Persistencia;

import Modelo.*;
import Excepciones.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LectorXML {
    public Universidad cargarDatos(String rutaArchivo) throws ExcepcionArchivoInvalido
    {
        Universidad universidad = new Universidad();

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
            NodeList nList = doc.getElementsByTagName("Aula");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element elemento = (Element) nList.item(temp);
                int ID = Integer.parseInt(elemento.getElementsByTagName("ID").item(0).getTextContent());
                int capacidad = Integer.parseInt(elemento.getElementsByTagName("capacidad").item(0).getTextContent());
                Aula aula = new Aula(ID, capacidad);
                universidad.agregarAula(aula);
            }

            // Leer y agregar Asignaturas
            nList = doc.getElementsByTagName("Asignatura");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element elemento = (Element) nList.item(temp);
                LocalDate fechaI = LocalDate.parse(elemento.getElementsByTagName("fechaI").item(0).getTextContent(), dateFormatter);
                LocalDate fechaF = LocalDate.parse(elemento.getElementsByTagName("fechaF").item(0).getTextContent(), dateFormatter);
                LocalTime horaI = LocalTime.parse(elemento.getElementsByTagName("horaI").item(0).getTextContent(), timeFormatter);
                LocalTime horaF = LocalTime.parse(elemento.getElementsByTagName("horaF").item(0).getTextContent(), timeFormatter);
                String diaSemana = elemento.getElementsByTagName("diaSemana").item(0).getTextContent();
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Asignatura asignatura = new Asignatura(fechaI, fechaF, horaI, horaF, diaSemana, codigo,descripcion, cantParticipantes);
                universidad.agregarAsignatura(asignatura);
            }

            // Leer y agregar Cursos
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element elemento = (Element) nList.item(temp);
                int cantClases = Integer.parseInt(elemento.getElementsByTagName("cantClases").item(0).getTextContent());
                double costo = Double.parseDouble(elemento.getElementsByTagName("costo").item(0).getTextContent());
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Curso curso = new Curso (cantClases, costo, codigo, descripcion, cantParticipantes);
                universidad.agregarCurso(curso);
            }

            // Leer y agregar Eventos internos
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element elemento = (Element) nList.item(temp);
                LocalDate fecha = LocalDate.parse(elemento.getElementsByTagName("fecha").item(0).getTextContent(), dateFormatter);
                LocalTime horaI = LocalTime.parse(elemento.getElementsByTagName("horaI").item(0).getTextContent(), timeFormatter);
                LocalTime horaF = LocalTime.parse(elemento.getElementsByTagName("horaF").item(0).getTextContent(), timeFormatter);
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Evento interno = new Evento (fecha, horaI, horaF, codigo, descripcion, cantParticipantes);
                universidad.agregarEvento(interno);
            }

            // Leer y agregar Eventos externos
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element elemento = (Element) nList.item(temp);
                double cobroAlq = Double.parseDouble(elemento.getElementsByTagName("cobroAlq").item(0).getTextContent());
                String org = elemento.getElementsByTagName("org").item(0).getTextContent();
                LocalDate fecha = LocalDate.parse(elemento.getElementsByTagName("fecha").item(0).getTextContent(), dateFormatter);
                LocalTime horaI = LocalTime.parse(elemento.getElementsByTagName("horaI").item(0).getTextContent(), timeFormatter);
                LocalTime horaF = LocalTime.parse(elemento.getElementsByTagName("horaF").item(0).getTextContent(), timeFormatter);
                String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                int cantParticipantes = Integer.parseInt(elemento.getElementsByTagName("cantParticipantes").item(0).getTextContent());
                Externo externo = new Externo (cobroAlq, org, fecha, horaI, horaF, codigo, descripcion, cantParticipantes);
                universidad.agregarEvento(externo);
            }

        } catch (Exception e) {
            throw new ExcepcionArchivoInvalido("Error al cargar el archivo XML: " + e.getMessage());}

        return universidad;
    }

}
