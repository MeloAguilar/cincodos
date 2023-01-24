package UI;

import Database.DAO;
import Entities.AlumnoEntity;
import Entities.MatriculaEntity;
import Entities.ProfesorEntity;

import java.util.List;
import java.util.Scanner;

public class MainMenu {


    static Scanner sc = new Scanner(System.in);

    public static final String SUBMENU = """
            1 - Crear
            2 - Modificar
            3 - Eliminar
            0 - Salir
            """;
    public static final String MENU = """
            1 - Cosas con Profesores
            2 - Cosas con alumnos
            3 - Cosas con Matrículas
            0 - Salir
            """;

    public static final String SUBMENUMATRICULAS = """
            1 - Crear
            2 - Modificar
            3 - Eliminar
            4 - Listar Matriculas de Profesor
            5 - Listar MMatriculas de Alumno
            0 - Salir
            """;


    /**
     * Método que se encarga de convertir un objeto String a entero si es posible,
     * si no lo es devuelve -1
     *
     * pre: toParse debe ser distinto de -1, ya que esta es la salida de error
     * @param toParse
     * @return un entero válido o -1
     */
    private static int StringToInt(String toParse) {
        int number;
        try {
            number = Integer.parseInt(toParse);
        } catch (Exception e) {
            number = -1;
        }
        return number;
    }


    /**
     *
     * Menu principal de la aplicacion
     *
     * @param sc
     */
    public static void menuPrincipal() {

        boolean salir = false;
        while (!salir) {
            System.out.println(MENU);
            String opcion = sc.next();
            int eleccion = StringToInt(opcion);
            switch (eleccion) {
                case 1:
                    accionesProfesores();

                    break;
                case 2:
                    accionesAlumnos();
                    break;
                case 3:
                    accionesMatriculas();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    salir = false;
                    break;
            }
        }
    }


    /**
     *
     * Menu que muestra al usuario las opciones de insercion, edicion y eliminacion de profesores
     *
     * @param sc
     */
    public static void accionesProfesores() {

        boolean salir = false;
        while (!salir) {
            System.out.println(SUBMENU);
            String opcion = sc.next();
            int eleccion = StringToInt(opcion);
            switch (eleccion) {
                case 1:
                    insertarProfesor();
                    break;
                case 2:
                    break;
                case 3:
                    editarProfesor();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    eliminarProfesor();
                    salir = false;
                    break;
            }
        }
    }


    /**
     * @param sc
     */
    private static void eliminarProfesor() {
        boolean salir = false;
        DAO dao = new DAO();
        showProfesores();
        while (!salir) {
            System.out.println("Introduzca el id del profesor que desea eliminar");
            int idProf = StringToInt(sc.next());
            try {
                dao.eliminarProfesor(idProf);
                salir = true;
            } catch (Exception e) {
                System.out.println("No existe ese profesor");
            }
        }
    }


    /**
     * @param sc
     */
    private static void editarProfesor() {
        DAO dao = new DAO();

        showProfesores();

        System.out.println("Escribe el numero de identificacion del profesor que deseas editar");

        boolean salir = false;
        while (!salir) {
            int idProf = StringToInt(sc.next());
            try {
                ProfesorEntity profesor = dao.getProfesor(idProf);
                System.out.printf("Profesor %s seleccionado", "%s %s".formatted(profesor.getNombre(), profesor.getApellidos()));
                profesor = getProfesorData();
                dao.actualizarProfesor(profesor.getId(), profesor.getNombre(), profesor.getApellidos(), profesor.getFechaNacimiento(), profesor.getAntiguedad());
                System.out.println("Se actualizó con éxito");
            } catch (Exception e) {
                System.out.println("No existe ese profesor");
            }
        }
    }


    /**
     *
     * Método que se encarga de realizar las llamadas necesarias a la clase de acceso a datos
     * para insertar un Profesor en la base de datos
     *
     */
    private static void insertarProfesor() {
        DAO dao = new DAO();
        ProfesorEntity profe = getProfesorData();
        dao.guardarProfesor(profe.getNombre(), profe.getApellidos(), profe.getFechaNacimiento(), profe.getAntiguedad());
        System.out.println("Se guardo el Profesor");
    }


    /**
     * Método que obtiene los datos de un profesor mediante peticiones por consola al usuario
     *
     * @param sc
     * @return
     */
    private static ProfesorEntity getProfesorData() {
        String nombre, apellidos, fechaNacimiento;
        int anios = 0;
        System.out.println("Escribe el nombre del profesor");
        nombre = sc.next();
        System.out.println("Escribe los apellidos del profesor");
        apellidos = sc.next();
        System.out.println("Escribe la fecha de nacimiento del profesor");
        fechaNacimiento = sc.next();
        System.out.println("Escribe los años de antigüedad del profesor");
        boolean esNumero = false;
        while (!esNumero) {
            int num;
            if ((num = StringToInt(sc.next())) != -1) {
                anios = num;
                esNumero = true;
            } else {
                System.out.println("Debe introducir un numero");
            }
        }
        return new ProfesorEntity(nombre, apellidos, fechaNacimiento, anios);
    }


    /**
     *
     * Menu que muestra al usuario las opciones de insercion, edicion y eliminacion de Alumnos
     *
     */
    public static void accionesAlumnos() {

        boolean salir = false;
        while (!salir) {
            System.out.println(SUBMENU);
            String opcion = sc.next();
            int eleccion = StringToInt(opcion);
            switch (eleccion) {
                case 1:
                    insertarAlumno();
                    break;
                case 2:
                    break;
                case 3:
                    editarALumno();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    eliminarAlumno();
                    salir = false;
                    break;
            }
        }


    }


    /**
     *
     * @return
     */
    private static AlumnoEntity getAlumnoData() {
        AlumnoEntity alumno;
        String nombre, apellidos, fechaNacimiento;
        System.out.println("Escribe los datos del alumno a continuacion");

        boolean salir = false;


        System.out.println("Escribe el nombre a continuacion");
        nombre = sc.next();

        System.out.println("Escribe los apellidos a continuacion");
        apellidos = sc.next();

        System.out.println("Escribe la fecha de nacimiento a continuacion");
        fechaNacimiento = sc.next();

        alumno = new AlumnoEntity(nombre, apellidos, fechaNacimiento);


        return alumno;
    }


    /**
     *
     */
    private static void showProfesores() {
        DAO dao = new DAO();
        List<ProfesorEntity> profesores = dao.getProfesores();
        for (ProfesorEntity en :
                profesores) {
            System.out.println(en);
        }
    }


    /**
     *
     */
    private static void showMatriculas() {
        DAO dao = new DAO();
        List<MatriculaEntity> matriculas = dao.getMatriculas();
        for (MatriculaEntity en :
                matriculas) {
            System.out.println(en);
        }
    }


    /**
     *
     */
    private static void showAlumnos() {
        DAO dao = new DAO();
        List<AlumnoEntity> alumnos = dao.getAlumnos();
        for (AlumnoEntity en :
                alumnos) {
            System.out.println(en);
        }
    }


    /**
     *
     * Método que se encarga de realizar las llamadas necesarias a la clase de acceso a datos
     * para insertar un Alumno en la base de datos
     *
     */
    private static void insertarAlumno() {
        AlumnoEntity alumno = getAlumnoData();
        DAO dao = new DAO();
        dao.guardarAlumno(alumno.getNombre(), alumno.getApellidos(), alumno.getFechaNacimiento());

        System.out.println("Se insertó el alumno");
    }

    /**
     * @param sc
     */
    private static void eliminarAlumno() {
        DAO dao = new DAO();

        showAlumnos();

        boolean salir = false;
        int idAlumno = -1;
        while (!salir) {
            try {
                System.out.println("Escribe el numero identificador del alumno al que deseas eliminar de la base de datos");
                idAlumno = StringToInt(sc.next());
                salir = true;
                dao.eliminarALumno(idAlumno);
                System.out.printf("Se eliminó el registro %d", idAlumno);
            } catch (Exception e) {
                idAlumno = 0;
                System.out.println("El registro no se pudo eliminar o no existe en la base de datos");
            }

        }

    }


    /**
     *
     */
    private static void editarALumno() {

        DAO dao = new DAO();
        List<AlumnoEntity> alumnos = dao.getAlumnos();
        System.out.println("Escribe el numero identificador del alumno al que deseas editar");
        boolean salir = false;
        int idAlumno = -1;
        while (!salir) {
            try {

                idAlumno = StringToInt(sc.next());
                salir = true;
                AlumnoEntity alumnio = getAlumnoData();
                dao.actualizarAlumno(alumnio.getId(), alumnio.getNombre(), alumnio.getApellidos(), alumnio.getFechaNacimiento());

            } catch (Exception e) {
                idAlumno = 0;
                System.out.println("El registro no se pudo eliminar o no existe en la base de datos");
            }

        }
    }


    /**
     *
     * @param id
     * @return
     */
    private static boolean testIfExistsProfesor(int id) {
        DAO dao = new DAO();
        boolean exists = false;
        var profesores = dao.getProfesores();
        for (int i = 0; i < profesores.size() && !exists; i++) {
            if (profesores.get(i).getId() == id) {
                exists = true;
            } else if (i == profesores.size() - 1 && !exists) {
                System.out.println("El alumno no existe");
            }
        }

        return exists;
    }

    private static boolean testIfExistsAlumno(int id) {
        DAO dao = new DAO();
        boolean exists = false;
        var alumnos = dao.getAlumnos();
        for (int i = 0; i < alumnos.size() && !exists; i++) {
            if (alumnos.get(i).getId() == id) {
                exists = true;
            }
            if (i == alumnos.size() - 1 && !exists) {
                System.out.println("El alumno no existe");
            }
        }

        return exists;
    }











    /**
     *
     * Menu que muestra al usuario las opciones de insercion, edicion y eliminacion de Matriculas
     *
     */
    public static void accionesMatriculas() {
        boolean salir = false;
        while (!salir) {
            System.out.println(SUBMENUMATRICULAS);
            String opcion = sc.next();
            int eleccion = StringToInt(opcion);
            switch (eleccion) {
                case 1:
                    insertarMatricula();
                    break;
                case 2:
                    editarMatricula();
                    break;
                case 3:
                    eliminarMatricula();

                    break;

                case 4:
                    listarMatriculasDeProfesor();
                    break;
                case 5:
                    listarMatriculasDeAlumno();
                case 0:
                    salir = true;
                    break;
                default:

                    salir = false;
                    break;
            }
        }
    }

    /**
     * @param sc
     */
    private static void insertarMatricula() {
        DAO dao = new DAO();
        try {
            MatriculaEntity matricula = getMatriculaData();
            dao.guardarMatricula(matricula.getAlumno().getId(), matricula.getProfesor().getId(), matricula.getAsignatura(), matricula.getCurso());
            System.out.println("Se inserto la Matricula con exito");
        } catch (Exception e) {
            System.out.println("No se puede introducir la matricula... Puede que el servidor no esté mú fino hoy...");
        }


    }


    /**
     *
     * @param id
     * @return
     */
    private static boolean testIfExistsMatricula(int id) {
        DAO dao = new DAO();
        boolean exists = false;
        var matriculas = dao.getMatriculas();
        for (int i = 0; i < matriculas.size() && !exists; i++) {
            if (matriculas.get(i).getId() == id) {
                exists = true;
            }
            if (i == matriculas.size() - 1 && !exists) {
                System.out.println("La matricula no existe");
            }
        }

        return exists;
    }




    /**
     *
     */
    private static void listarMatriculasDeProfesor() {
        showProfesores();
        DAO dao = new DAO();
        System.out.println("Escriba el numero de identificacion del profesor sobre el que quiere indagar");
        int idProfesor = 0;
        do {
            idProfesor = StringToInt(sc.next());
        } while (!testIfExistsProfesor(idProfesor));

        ProfesorEntity profesor = dao.getProfesor(idProfesor);
        System.out.printf("Mostrando las matriculas del profesor %s %s,%n con numero de identificacion %d", profesor.getNombre(), profesor.getApellidos(), profesor.getId());
        for (var matricula :
                profesor.getMatriculas()) {
            System.out.println(matricula);
        }

    }


    /**
     *
     */
    private static void listarMatriculasDeAlumno() {
        showAlumnos();
        DAO dao = new DAO();
        System.out.println("Escriba el numero de identificacion del alumno sobre el que quiere indagar");
        int idAlumno = 0;
        do {
            idAlumno = StringToInt(sc.next());
        } while (!testIfExistsAlumno(idAlumno));

        ProfesorEntity profesor = dao.getProfesor(idAlumno);
        System.out.printf("Mostrando las matriculas del profesor %s %s,%n con numero de identificacion %d", profesor.getNombre(), profesor.getApellidos(), profesor.getId());
        for (var matricula :
                profesor.getMatriculas()) {
            System.out.println(matricula);
        }

    }


    /**
     *
     */
    private static void eliminarMatricula() {
        DAO dao = new DAO();
        showMatriculas();
        System.out.println("Escribe el id de la matricula que deseas eliminar");
        int idMatricula = 0;
        do {
            idMatricula = StringToInt(sc.next());
        } while (!testIfExistsMatricula(idMatricula));
        try {
            dao.eliminarMatricula(idMatricula);
        } catch (Exception e) {
            System.out.println("No se pudo eliminar la matricula.... Puede que el servidor necesite que alguien llame a alguien");
        }


    }


    /**
     *
     * @return
     */
    private static MatriculaEntity getMatriculaData() {

        DAO dao = new DAO();
        boolean salir = false;
        int idalumno = 0;
        int idprofe = 0;
        int curso = 0;

        String asignatura;


        while (!salir) {
            showAlumnos();
            System.out.println("Escribe el numero identificador de un alumno");
            idalumno = StringToInt(sc.next());
            if (testIfExistsAlumno(idalumno)) {
                salir = true;
            }
        }

        salir = false;
        while (!salir) {
            showProfesores();
            System.out.println("Escribe el numero identificador de un profesor");
            idprofe = StringToInt(sc.next());
            if (testIfExistsAlumno(idprofe)) {
                salir = true;
            }
        }


        System.out.println("Escribe el nombre de la asignatura que impartida");

        asignatura = sc.next();

        System.out.println("Escribe el numero del curso");

        while (curso < 1) {

            curso = StringToInt(sc.next());
            if (curso < 1) {
                System.out.println("Debe introducir un numero mayor o igual a 1");
            }
        }

        return new MatriculaEntity(idalumno, idprofe, asignatura, curso);
    }


    /**
     *
     */
    private static void editarMatricula() {
        DAO dao = new DAO();
        boolean salir = false;
        int idalumno = 0;
        int idprofe = 0;
        int curso = 0;

        String asignatura;
        showMatriculas();
        System.out.println("Escribe el id de la matricula que deseas editar");
        int idMatricula = 0;
        do {
            idMatricula = StringToInt(sc.next());
        } while (!testIfExistsMatricula(idMatricula));

        MatriculaEntity matricula = getMatriculaData();

        try {
            dao.actualizarMatricula(matricula.getId(), matricula.getAlumno().getId(), matricula.getProfesor().getId(), matricula.getAsignatura(), matricula.getCurso());
            System.out.println("Que alguien llame a alguien,ALGUIEN INSERTÓ UNA NUEVA MATRÍCULA");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar 3el registro.. Puede que el servidor necesite que alguien llame a alguien");
        }


    }
}
