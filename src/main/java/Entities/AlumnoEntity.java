package Entities;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name="Alumnos")
public class AlumnoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlumno")
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAlumno")
    List<MatriculaEntity> matriculas;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    public List<MatriculaEntity> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<MatriculaEntity> matriculas) {
        this.matriculas = matriculas;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "fechaNacimiento")
    private String fechaNacimiento;


    public AlumnoEntity(int id, String nombre, String apellidos, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public AlumnoEntity(int id){
        this.id = id;
    }

    public AlumnoEntity() {
        this.id = 0;
        this.nombre = "";
        this.apellidos = "";
        this.fechaNacimiento = "";
    }

    public AlumnoEntity( String nombre, String apellidos, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }


    @Override
    public String toString() {
        return String.format("""
                ========================================
                id = %d
                
                nombre = %s
                
                apellidos = %s
                
                fechaNacimiento = %s
                ========================================""", id, nombre, apellidos, fechaNacimiento.toString());

    }

}
