package Modelo;

import java.io.Serializable;

/** La clase Reservador representa una asignatura, un curso o un evento.
 * Es abstracta.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public abstract class Reservador implements Serializable {
    //Atributos:
    private String codigo;
    private String descripcion;
    private int cantParticipantes;

    //Métodos:

    //Constructor
    public Reservador (String cod, String desc, int cantpart)
    {
        codigo=cod;
        descripcion=desc;
        cantParticipantes=cantpart;
    }

    //getters
    public String getCodigo() { return codigo; }
    public String getDescripcion(){ return descripcion; }
    public int getCantParticipantes(){ return cantParticipantes; }

    public double getMonto() { // calcula el monto basado en el tipo de reserva (Reportes)
        if (this instanceof Externo) {
            return ((Externo) this).getCobroAl();
        } else if (this instanceof Curso) {
            Curso curso = (Curso) this;
            return curso.getCosto() * cantParticipantes; // costo por alumno y la cantidad de alumnos
        } else { // En caso de otros tipos de reserva
            return 0.0;
        }
    }

}
