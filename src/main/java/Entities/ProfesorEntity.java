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

    //Atributos
    private int id;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProfesor")
    List<MatriculaEntity> matriculas;


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

    //End Constructores


    @Override
    public String toString() {
        return String.format("""
                ========================================
                id = %d
                
                nombre = %s
                
                apellidos = %s
                
                fechaNacimiento = %s
                
                antigüedad = %d
                ========================================""", id, nombre, apellidos, fechaNacimiento, antiguedad);
    }
}
