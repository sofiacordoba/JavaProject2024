package Persistencia;

import Modelo.*;
import java.io.FileOutputStream;

/** La clase Persistencia se usa para cargar los datos del sistema.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Persistencia {

    public static boolean SerializarUniversidad(Universidad universidad){
        try {
            FileOutputStream file = new FileOutputStream("Universidad.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            ObjectOut.writeObject(universidad);
            objectOut.close();
            file.close();
        } catch(Exception ex) {
            System.out.println("Se ha presentado un error al serializar la universidad");
            return false;
        }

        return true;
    }

    public static Universidad DeserializarUniversidad(){
        Universidad uni = null;

        try {
            FileInputStream file = new FileInputStream("Universidad.dat");
            ObjectInputStream objectIn = new ObjectInputStream(file);
            uni = (Universidad)objectIn.readObject();
            objectIn.close();
            file.close();
        } catch(Exception ex) {
            System.out.println("Se ha presentado un error al deserializar la universidad");
            return null;
        }
        return uni;
    }

    public static void main(String[] args) {

        Universidad uni = new Universidad();

        //EJEMPLO 1
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

        //EJEMPLO 2
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

        fi = LocalDate.of(2024, 6, 13);
        hi = LocalTime.of(14, 30, 0);
        hf = LocalTime.of(16, 30, 0);
        Evento evento1 = new Evento(fi, hi, hf, "EVI102", "Entrega diplomas", 40);
        uni.agregarEvento(evento1);

        Externo externo1 = new Externo(500.0, "Tech Corp", LocalDate.of(2024, 6, 13), LocalTime.of(15, 0), LocalTime.of(16, 0), "EVE001", "Exposición de Innovación", 80);
        uni.agregarExterno(externo1);

        Reserva reserva3 = new Reserva(fi,hi,hf, externo1);
        aula2.agregarReserva(reserva3);

        boolean result = SerializarUniversidad(uni);
        if(result) {
            Universidad uni2 = DeserializarUniversidad();
            if (uni2 != null) {
                System.out.println(uni2.ToString());
            }
        }
    }
}
