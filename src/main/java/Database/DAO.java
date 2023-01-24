package Database;

import Entities.AlumnoEntity;
import Entities.MatriculaEntity;
import Entities.ProfesorEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static SessionFactory sessionFactory = null;

    private Transaction transaction;

    private Session sesion;


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



    public void conectar(){
        try {

            setUp();
            sesion = sessionFactory.openSession();
            transaction = sesion.beginTransaction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void desconectar(){
        try{
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("No se pudo realizar la transacción");
        }
    }



    /**
     * Método que se encarga de introducir un objeto Profesor
     * en la base de datos.
     *
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param antiguedad
     */
    public void guardarProfesor(String nombre, String apellidos, String fechaNacimiento, int antiguedad) {
        conectar();
        ProfesorEntity persona = new ProfesorEntity(nombre, apellidos, fechaNacimiento, antiguedad);
        int id = (int) sesion.save(persona);
        System.out.println(id);
        desconectar();
    }


    /**
     * Método que se encarga de introducir un objeto de
     * tipo Alumno en la base de datos.
     *
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     */
    public void guardarAlumno(String nombre, String apellidos, String fechaNacimiento) {
        conectar();
        AlumnoEntity persona = new AlumnoEntity(nombre, apellidos, fechaNacimiento);
        int id = (int) sesion.save(persona);
        System.out.println(id);
        desconectar();
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
    public void guardarMatricula(int alumno_id, int profesor_id, String asignatura, int curso) {
        conectar();
        MatriculaEntity materia = new MatriculaEntity(profesor_id, alumno_id, asignatura, curso);
        int id = (int) sesion.save(materia);
        System.out.println(id);
        desconectar();
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
    public AlumnoEntity leerALumno(int id) throws Exception {
        conectar();
        AlumnoEntity persona = sesion.load(AlumnoEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(persona);
        desconectar();
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
    public ProfesorEntity leerProfesor(int id) throws Exception {
        conectar();
        ProfesorEntity persona = sesion.load(ProfesorEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(persona);
        desconectar();
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
    public MatriculaEntity leerMatricula(int id) throws Exception {
         conectar();
        MatriculaEntity matricula = sesion.load(MatriculaEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(matricula);
        desconectar();
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
    public void actualizarAlumno(int id, String nombre, String apellidos, String fechaNacimiento) throws Exception {
        conectar();

        AlumnoEntity persona = sesion.get(AlumnoEntity.class, id);
        persona.setNombre(nombre);
        persona.setApellidos(apellidos);
        persona.setFechaNacimiento(fechaNacimiento);
        // session.saveOrUpdate(persona);       // session.merge(persona);
        sesion.update(persona);
        desconectar();
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
    public void actualizarProfesor(int id, String nombre, String apellidos, String fechaNacimiento, int tiempoTrabajado) throws Exception {

       conectar();
        ProfesorEntity persona = sesion.get(ProfesorEntity.class, id);
        persona.setNombre(nombre);
        persona.setApellidos(apellidos);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setAntiguedad(tiempoTrabajado);
        // session.saveOrUpdate(persona);       // session.merge(persona);
        sesion.update(persona);

        desconectar();
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
    public void actualizarMatricula(int id, int idAlumno, int idProfesor, String asignatura, int curso) throws Exception {
       conectar();


        MatriculaEntity matricula = sesion.get(MatriculaEntity.class, id);


        matricula.setProfesor(new ProfesorEntity(idProfesor));
        matricula.setAlumno(new AlumnoEntity(idAlumno));
        matricula.setAsignatura(asignatura);
        matricula.setCurso(curso);
        sesion.update(matricula);

        desconectar();
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
    public void eliminarProfesor(int idProfesor) throws Exception{
        conectar();
        ProfesorEntity profe = sesion.get(ProfesorEntity.class, idProfesor);
        sesion.delete(profe);
        desconectar();
    }




    /**
     * Método que se encarga de eliminar un registro de la tabla Alumnos dado un entero
     * que coincida con el id del alumno.
     *
     * @param idAlumno
     * @throws Exception: Se lanzará siempre que no se encuentre un alumno que concuerde con el entero introducido como parámetro.
     */
    public void eliminarALumno(int idAlumno) throws Exception{
        conectar();
        AlumnoEntity alumno = sesion.get(AlumnoEntity.class, idAlumno);
        sesion.delete(alumno);
        desconectar();
    }



    /**
     * Método que se encarga de eliminar un registro de la tabla Matriculas dado un entero
     * que coincida con el id de una matrícula.
     *
     * @param idMatricula
     * @throws Exception: Se lanzará siempre que no se encuentre una matricula que concuerde con el entero introducido como parámetro.
     */
    public void eliminarMatricula(int idMatricula) throws Exception{
        conectar();
        MatriculaEntity matricula = sesion.get(MatriculaEntity.class, idMatricula);
        sesion.delete(matricula);
        desconectar();
    }


    /**
     * End Region Eliminar
     */


    /**
     * Region Get
*/


    /**
     * Método que se encarga de recoger una lista de el servidor y devolverla en forma de lista de objetos AlumnoEntity
     *
     * @return
     */
    public List<MatriculaEntity> getMatriculas(){
        conectar();
        Query query = sesion.createQuery("FROM MatriculaEntity ");
        List<MatriculaEntity> lista = query.list();
        desconectar();
        return lista;
    }

    /**
     * Método que se encarga de rescatar una lista del servidor, castearla a objetos ProfesorEntity
     * y la devuelve
     * @return
     */
        public List<ProfesorEntity> getProfesores(){
            conectar();
            Query query = sesion.createQuery("FROM ProfesorEntity ");
            List<ProfesorEntity> lista = query.list();
            desconectar();
            return lista;
        }


    /**
     * Método que se encarga de recoger una lista de el servidor y devolverla en forma de lista de objetos AlumnoEntity
     *
     * @return
     */
    public List<AlumnoEntity> getAlumnos(){
            conectar();
            Query query = sesion.createQuery("FROM AlumnoEntity ");
            List<AlumnoEntity> lista = query.list();
            desconectar();
            return lista;
        }


    /**
     * Recoge todas las matriculas de un alumno dado su id
     * @param _idAlumno
     * @return
     */
    public List<MatriculaEntity> getMatriculasDeAlumno(int _idAlumno){
        conectar();
        Query query = sesion.createQuery("FROM MatriculaEntity WHERE alumno =: _idAlumno");
        query.setInteger("_idAlumno", _idAlumno);
        List<MatriculaEntity> list = query.list();
        desconectar();
        return list;
    }


    /**
     * Método qué, dado un entero que coincida con un registro de la tabla profesores de la base de datos
     * devuelve una lista de objetos Matriculaentity
     * @param _idProfesor
     * @return
     */
    public List<MatriculaEntity> getMatriculasDeProfesor(int _idProfesor){
        conectar();
        SQLQuery sqlQuery = sesion.createSQLQuery("FROM MatriculaEntity Where profesor = :_idProfesor");
        sqlQuery.setInteger("_idProfesor",_idProfesor);
        List<MatriculaEntity> list = sqlQuery.list();
        desconectar();
        return list;
    }


    /**
     * Método que se encarga de recoger un profesor de la base de datos y lo castea a un objeto ProfesorEntity
     * @param idProf
     * @return
     */
    public ProfesorEntity getProfesor(int idProf){
        conectar();
        ProfesorEntity pr = sesion.load(ProfesorEntity.class, idProf);
        desconectar();
        return pr;
    }

    /**
     * Método que se encarga de recoger un alumno de la base de datos y lo castea a un objeto AlumnoEntity
     * @param idAlumno
     * @return
     */
    public AlumnoEntity getAlumno(int idAlumno){
        conectar();
        AlumnoEntity pr = sesion.load(AlumnoEntity.class, idAlumno);
        desconectar();
        return pr;
    }


    /**
     * Método que se encarga de recoger una matricula de la base de datos y lo castea a un objeto MatriculaEntity
     * @param idMatr
     * @return
     */
    public MatriculaEntity getMatricula(int idMatr){
        conectar();
        MatriculaEntity pr = sesion.load(MatriculaEntity.class, idMatr);
        desconectar();
        return pr;
    }

/*
     * End Region Get
     */
}
