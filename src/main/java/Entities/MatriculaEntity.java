package Entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Matriculas")
public class MatriculaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Matricula_id")
    private int id;



    @Column(name = "profesor_id")
    @JoinColumn(name = "idProfesor")
    private ProfesorEntity idProfesor;

    @Column(name = "alumno_id")
    @JoinColumn(name="FK_AlumnoId")
    private int idAlumno;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "curso")
    private int curso;


    public MatriculaEntity(int id, int idProfesor, int idAlumno, String asignatura, int curso) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.idAlumno = idAlumno;
        this.asignatura = asignatura;
        this.curso = curso;
    }

    public MatriculaEntity( int idProfesor, int idAlumno, String asignatura, int curso) {
        this.idProfesor = idProfesor;
        this.idAlumno = idAlumno;
        this.asignatura = asignatura;
        this.curso = curso;
    }

    public MatriculaEntity() {
        this.id = 0;
        this.idProfesor = 0;
        this.idAlumno = 0;
        this.asignatura = "asignatura";
        this.curso = 0;
    }

    @Override
    public String toString() {
        return String.format("""
                ========================================
                id = %d
                
                idProfesor = %d
                
                idAlumno = %d
                
                asignatura = %s
                
                curso = %d
                ========================================""", id, idProfesor, idAlumno, asignatura, curso);

    }


}
