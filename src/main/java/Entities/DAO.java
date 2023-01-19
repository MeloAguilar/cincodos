package Entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DAO {

    private static SessionFactory sessionFactory = null;


    /**
     * Método que se encarga de registrar la conexion e inicia la sessionfactory
     *
     * @throws Exception
     */
    public static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Region Guardar
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
    public static void guardarProfesor(String nombre, String apellidos, String fechaNacimiento, int antiguedad) {
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
    public static void guardarAlumno(String nombre, String apellidos, String fechaNacimiento) {

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
     *
     * @param alumno_id
     * @param profesor_id
     * @param asignatura
     * @param curso
     */
    public static void guardarMatricula(int alumno_id, int profesor_id, String asignatura, int curso) {
        MatriculaEntity materia = new MatriculaEntity(profesor_id, alumno_id, asignatura, curso);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(materia);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }


    /**
     * End Region Guardar
     */




    /**
     * Region Leer
     */


    /**
     * Método que se encarga de leer un objeto tipo AlumnoEntity de la base de datos
     * dado un entero que corresponda con su id. Devolverá un objeto de tipo AlumnoEntity si id coincide con un id de la tabla Alumnos
     * de la base de datos
     *
     * @param id
     * @return
     * @throws Exception: Saltará cuando no exista un alumno dentro de la base de datos con el id proporcionado.
     */
    public static AlumnoEntity leerALumno(int id) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AlumnoEntity persona = session.load(AlumnoEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(persona);
        transaction.commit();
        sessionFactory.close();
        return persona;
    }


    /**
     * Método que se encarga de leer un objeto tipo ProfesorEntity de la base de datos
     * dado un entero que corresponda con su id. Devolverá un objeto de tipo ProfesorEntity si id coincide con un id de la tabla Profesores
     * de la base de datos
     *
     * @param id
     * @return
     * @throws Exception: Saltará cuando no exista un alumno dentro de la base de datos con el id proporcionado.
     */
    public static ProfesorEntity leerProfesor(int id) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProfesorEntity persona = session.load(ProfesorEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(persona);
        transaction.commit();
        sessionFactory.close();
        return persona;
    }

    /**
     * Método que se encarga de leer un objeto tipo MAtriculaEntity de la base de datos
     * dado un entero que corresponda con su id. Devolverá un objeto de tipo MatriculaEntity si id coincide con un id de la tabla Matriculas
     * de la base de datos
     *
     * @param id
     * @return
     * @throws Exception: Saltará cuando no exista un alumno dentro de la base de datos con el id proporcionado.
     */
    public static MatriculaEntity leerMatricula(int id) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MatriculaEntity matricula = session.load(MatriculaEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(matricula);
        transaction.commit();
        sessionFactory.close();
        return matricula;
    }


    /**
     * End Region Leer
     */


    /**
     * Region Acualizar
     */

    /**
     * Método que se encarga de actualizar un registro de la tabla
     * Alumnos dentro de la base de datosdando como parámetros el id
     * del alumno, su nombre, apellidos y fecha de nacimiento.
     *
     * @param id
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @throws Exception: se lanzará cuando el id del alumno no
     *                    corresponda con ningun registro sde la base de datos.
     */
    public static void actualizarAlumno(int id, String nombre, String apellidos, String fechaNacimiento) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AlumnoEntity persona = session.get(AlumnoEntity.class, id);
        persona.setNombre(nombre);
        persona.setApellidos(apellidos);
        persona.setFechaNacimiento(fechaNacimiento);
        // session.saveOrUpdate(persona);       // session.merge(persona);
        session.update(persona);
        transaction.commit();
        sessionFactory.close();
    }


    /**
     * Método que se encarga de actualizar un registro de la tabla
     * Ptrofesores dentro de la base de datosdando como parámetros el id
     * del profesor, su nombre, apellidos, fecha de nacimiento y antigüedad trabajando.
     *
     * @param id
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param tiempoTrabajado
     *
     * @throws Exception: se lanzará cuando el id del alumno no
     *                    corresponda con ningun registro sde la base de datos.
     */
    public static void actualizarProfesor(int id, String nombre, String apellidos, String fechaNacimiento, int tiempoTrabajado) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProfesorEntity persona = session.get(ProfesorEntity.class, id);
        persona.setNombre(nombre);
        persona.setApellidos(apellidos);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setAntiguedad(tiempoTrabajado);
        // session.saveOrUpdate(persona);       // session.merge(persona);
        session.update(persona);
        transaction.commit();
        sessionFactory.close();
    }

    /**
     *
     *
     * Método que se encarga de actualizar un registro de la tabla
     * Matriculas dentro de la base de datos, dando como parámetros el id
     * del profesor, el id del alumno, la asignatura y el curso al que pertenece esta asignatura.
     *
     * @param id
     * @param idAlumno
     * @param idProfesor
     * @param asignatura
     * @param curso
     *
     *
     * @throws Exception: Se lanzará cuando el id de la matricula no coincida con ningun registro de la tabla matriculas de la base de datos o
     *  alguno de los datos introducidos sean de tipo diferente al que se especifica en la base de datos.
     */
    public static void actualizarMatricula(int id, int idAlumno, int idProfesor, String asignatura, int curso) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MatriculaEntity matricula = session.get(MatriculaEntity.class, id);
        matricula.setProfesor(new ProfesorEntity(idProfesor));
        matricula.setAlumno(new AlumnoEntity(idAlumno));
        matricula.setAsignatura(asignatura);
        matricula.setCurso(curso);// session.saveOrUpdate(persona);       // session.merge(persona);
        session.update(matricula);
        transaction.commit();
        sessionFactory.close();
    }

    /**
     * End Region Actualizar
     */


    /**
     * Region Eliminar
     */


    /**
     * Método que se encarga de eliminar un registro de la tabla Profesores dado un entero
     * que coincida con el id del profesor.
     *
     * @param idProfesor
     * @throws Exception: Se lanzará siempre que no se encuentre un profesor que concuerde con el entero introducido como parámetro.
     */
    public static void eliminarProfesor(int idProfesor) throws Exception{
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();
        ProfesorEntity profe = sesion.get(ProfesorEntity.class, idProfesor);
        sesion.delete(profe);
    }



    /**
     * Método que se encarga de eliminar un registro de la tabla Alumnos dado un entero
     * que coincida con el id del alumno.
     *
     * @param idAlumno
     * @throws Exception: Se lanzará siempre que no se encuentre un alumno que concuerde con el entero introducido como parámetro.
     */
    public static void eliminarALumno(int idAlumno) throws Exception{
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();
        AlumnoEntity alumno = sesion.get(AlumnoEntity.class, idAlumno);
        sesion.delete(alumno);
    }



    /**
     * Método que se encarga de eliminar un registro de la tabla Matriculas dado un entero
     * que coincida con el id de una matrícula.
     *
     * @param idMatricula
     * @throws Exception: Se lanzará siempre que no se encuentre una matricula que concuerde con el entero introducido como parámetro.
     */
    public static void eliminarMatricula(int idMatricula) throws Exception{
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();
        MatriculaEntity matricula = sesion.get(MatriculaEntity.class, idMatricula);
        sesion.delete(matricula);
    }


    /**
     * End Region Eliminar
     */
}
