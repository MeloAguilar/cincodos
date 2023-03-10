package Entities;

import org.hibernate.mapping.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="Profesores")
public class ProfesorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idProfesor")
    //Atributos
    private int id;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProfesor")
    private List<MatriculaEntity> matriculas;


    public List<MatriculaEntity> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<MatriculaEntity> matriculas) {
        this.matriculas = matriculas;
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

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fechaNacimiento")
    private String fechaNacimiento;

    @Column(name = "antiguedad")
    private int antiguedad;
    //End Atributos

    //Constructores
    public ProfesorEntity(int _id, String _nombre, String _apellidos, String _fechaNac, int _antiguedad){
        this.id = _id;
        this.nombre = _nombre;
        this.apellidos = _apellidos;
        this.fechaNacimiento = _fechaNac;
        this.antiguedad = _antiguedad;
    }

    public ProfesorEntity(String _nombre, String _apellidos, String _fechaNac, int _antiguedad){
        this.nombre = _nombre;
        this.apellidos = _apellidos;
        this.fechaNacimiento = _fechaNac;
        this.antiguedad = _antiguedad;
    }

    public ProfesorEntity() {
        this.id = 0;
        this.nombre = "";
        this.apellidos = "";
        this.fechaNacimiento = Date.from(Instant.now()).toString();
        this.antiguedad = 0;
    }

    public ProfesorEntity(int id){
        this.id = id;
    }
    //End Constructores


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("""
                ========================================
                id = %d
                
                nombre = %s
                
                apellidos = %s
                
                fechaNacimiento = %s
                
                antig??edad = %d
                ========================================""", id, nombre, apellidos, fechaNacimiento, antiguedad);
    }
}
