package Entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Matriculas")
public class MatriculaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMatricula")
    private int id;


    @ManyToOne
    @JoinColumn(name = "idAlumno")
    private AlumnoEntity alumno;

    @ManyToOne
    @JoinColumn(name = "idProfesor")
    private ProfesorEntity profesor;




    public ProfesorEntity getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorEntity profesor) {
        this.profesor = profesor;
    }

    public AlumnoEntity getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoEntity alumno) {
        this.alumno = alumno;
    }






    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "curso")
    private int curso;


    public MatriculaEntity(int id, int idProfesor, int idAlumno, String asignatura, int curso) {
        this.id = id;
        this.profesor = new ProfesorEntity(idProfesor) ;
        this.alumno = new AlumnoEntity(idAlumno);
        this.asignatura = asignatura;
        this.curso = curso;
    }

    public MatriculaEntity( int idProfesor, int idAlumno, String asignatura, int curso) {
        this.profesor = new ProfesorEntity(idProfesor) ;
        this.alumno = new AlumnoEntity(idAlumno);
        this.asignatura = asignatura;
        this.curso = curso;
    }

    public MatriculaEntity() {
        this.id = 0;
        this.profesor = new ProfesorEntity(0) ;
        this.alumno = new AlumnoEntity(0);
        this.asignatura = "asignatura";
        this.curso = 0;
    }

    @Override
    public String toString() {
        return String.format("""
                ========================================
                id = %d
                
                Nombre Completo Profesor = %s
                
                Nombre completo Alumno = %s
                
                asignatura = %s
                
                curso = %d
                ========================================""", id, "%s %s".formatted(profesor.getNombre(), profesor.getApellidos()),"%s %s".formatted(alumno.getNombre(), alumno.getApellidos()), asignatura, curso);

    }


}
