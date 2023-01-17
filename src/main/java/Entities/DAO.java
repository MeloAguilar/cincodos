package Entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DAO {

    private static SessionFactory sessionFactory = null;


    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    /**
     * Guardar
     */


    /**
     * Método que se encarga de introducir un objeto Profesor
     * en la base de datos.
     *
      * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param antiguedad
     */
    private static void guardarProfesor(String nombre, String apellidos, String fechaNacimiento, int antiguedad) {
        ProfesorEntity persona = new ProfesorEntity(nombre, apellidos, fechaNacimiento, antiguedad);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }


    /**
     * Método que se encarga de introducir un objeto de
     * tipo Alumno en la base de datos.
     *
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     */
    private static void guardarAlumno(String nombre, String apellidos, String fechaNacimiento) {

        AlumnoEntity persona = new AlumnoEntity(nombre, apellidos, fechaNacimiento);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }


    /**
     * Método que se encarga de introducir un objeto de
     * tipo alumno en la base de datos.
     * @param alumno_id
     * @param profesor_id
     * @param asignatura
     * @param curso
     */
    private static void guardarMatricula(int alumno_id, int profesor_id, String asignatura, int curso) {
        MatriculaEntity materia = new MatriculaEntity(profesor_id, alumno_id, asignatura, curso);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(materia);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }

}
