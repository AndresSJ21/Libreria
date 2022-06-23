package Libreria.Servicios;

import Libreria.Entidades.Autor;
import Libreria.Entidades.Editorial;
import Libreria.Entidades.Libro;
import Libreria.persistencia.AutorDAO;
import Libreria.persistencia.EditorialDAO;
import Libreria.persistencia.LibroDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibroService {

    private final LibroDAO LDAO;
    private final AutorDAO ADAO;
    private final EditorialDAO EDAO;
    private EditorialService eS = new EditorialService();
    private AutorService aS = new AutorService();
    private GeneralesService gS = new GeneralesService();
    private final Scanner sc;

    public LibroService() {
        this.LDAO = new LibroDAO();
        this.ADAO = new AutorDAO();
        this.EDAO = new EditorialDAO();
        this.sc = new Scanner(System.in).useDelimiter("\n");
    }

    public List<Libro> listarLibros() {
        try {
            return LDAO.listarLibros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("La cantidad de libros es CERO");
        } else {
            int cont = 1;
            for (Libro libro : libros) {
                System.out.println(" " + cont + ").- ,ISBN: " + libro.getIsbn() + ",    Titulo: " + libro.getTitulo() + ",    Ejemplares: " + libro.getEjemplares()
                        + ",\n" + "    " + libro.getAutor().toString() + ",\n" + "    " + libro.getEditorial().toString() + ",\n"
                        + "    Alta: " + libro.getAlta() + "\n");
                cont++;
            }
        }
        System.out.println("\n");
    }

    public Libro crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Editorial editorial, Autor autor) {
        Libro libro = new Libro();
        try {
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            //Editorial editorial = editorialService.crearEditorial(editorialNombre);
            libro.setEditorial(editorial);
            libro.setAlta(Boolean.TRUE);
            LDAO.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error creando libros");
            System.out.println(e.toString());
            return null;
        }

    }

    public List<Libro> buscarLibroPorISBN(Long isbn) {
        try {
            List<Libro> libros = LDAO.buscarLibroPorISBN(isbn);
            return libros;
        } catch (Exception e) {
            System.out.println("El ISBN ingresado no existe en la base de datos.");
            return null;
        }
    }

    public List<Libro> buscarLibrosPorAutor(String nombre) {
        List<Libro> libros;
        libros = LDAO.buscarLibrosPorAutor(nombre);
        return libros;
    }

    public List<Libro> buscarLibrosPorNombre(String nombre) {
        List<Libro> libros;
        libros = LDAO.buscarLibrosPorNombre(nombre);
        return libros;
    }

    public List<Libro> buscarLibrosPorEditorial(String nombre) {
        List<Libro> libros;
        libros = LDAO.buscarLibrosPorEditorial(nombre);
        return libros;
    }

    public List<Libro> buscarLibrosPorAnio(Integer anio) {
        List<Libro> libros;
        libros = LDAO.buscarLibrosPorAnio(anio);
        return libros;
    }

    public List<Libro> buscarLibrosPorStockCero() {
        List<Libro> libros;
        libros = LDAO.buscarLibrosPorStockCero();
        return libros;
    }

    public void altaLibro() throws Exception {
        Libro libro = new Libro();
        Libro libro1;
        Boolean validacion1 = false, validacion2 = false;
        String opcion;
        Long isbn;
        try {
            do {
                System.out.println("Ingrese el ISBN");
                isbn = gS.ingresaISBN();
                if (buscarLibroPorISBN(isbn) == null) {
                    validacion1 = true;
                    opcion = "s";
                } else {
                    System.out.println("   ¡¡¡ El ISBN ingresado ya existe !!!");
                    System.out.println((buscarLibroPorISBN(isbn)));
                    System.out.println("Presione cualquier tecla para probar con otro ISBN o s para salir");
                    Scanner leer = new Scanner(System.in);
                    opcion = leer.nextLine();
                }
            } while (!opcion.equalsIgnoreCase("s"));

            if (validacion1) {
                String option1, option2;
                Boolean autorE = false, editorialE = false;

                //verificamos la existencia del autor y si está cargado lo usamos para crear el libro
                System.out.println("Verifique que el Autor y la Editorial estén Cargados");
                System.out.println("De no estarlo deberá cargarlos desde el menú correspondiente");
                System.out.println("Ingrese nombre del autor");
                String nombreA = gS.ingresaNombre();
                List<Autor> autores = aS.buscarAutoresPorNombre(nombreA);
                aS.mostrarAutores(autores);
                Autor autor = new Autor();
                if (!autores.isEmpty()) {
                    Scanner lee = new Scanner(System.in);
                    System.out.println("Si el autor está en la lista ingrese s o cualquier tecla para salir");
                    option1 = lee.nextLine();
                    if (option1.equalsIgnoreCase("s")) {
                        System.out.println("Ingrese el codigo del autor");
                        Integer codigo = lee.nextInt();
                        autor = ADAO.buscarAutorPorID(codigo);
                        autorE = true;
                    }
                } else {
                    System.out.println("El Autor no está cargado, deberá ingresarlo antes de cargar el libro");
                }

                //verificamos la existencia de la editorial y si está cargada la usamos para crear el libro
                System.out.println("Ingrese nombre de la Editorial");
                String nombreE = gS.ingresaNombre();
                List<Editorial> editoriales = eS.buscarEditorialesPorNombre(nombreE);
                eS.mostrarEditoriales(editoriales);
                Editorial editorial = new Editorial();
                if (!editoriales.isEmpty()) {
                    Scanner lee = new Scanner(System.in);
                    System.out.println("Si la editorial está en la lista ingrese s o cualquier tecla para salir");
                    option2 = lee.nextLine();
                    if (option2.equalsIgnoreCase("s")) {
                        System.out.println("Ingrese el codigo de la editorial");
                        Integer codigo = lee.nextInt();
                        editorial = EDAO.buscarEditorialPorID(codigo);
                        editorialE = true;
                    }
                } else {
                    System.out.println("La Editorial no está cargada, deberá ingresarla antes de cargar el libro");
                }

                if (editorialE && autorE) {
                    System.out.println("Ingrese el Titulo");
                    String titulo = gS.ingresaTitulo();
                    System.out.println("Ingrese el año de la Edición");
                    Integer anio = gS.ingresaAnio();
                    System.out.println("Ingrese la cantidad inicial de Ejemplares");
                    Integer ejemplares = gS.ingresaEjemplares();

                    crearLibro(isbn, titulo, anio, ejemplares, editorial, autor);
                    List<Libro> libros = buscarLibroPorISBN(isbn);
                    System.out.println("Se ha dado de alta a: ");
                    mostrarLibros((List<Libro>) libros);
                } else {
                    System.out.println("No se ha dado de alta ningún Libro");

                }
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public Libro seleccionarLibro(List<Libro> libros) throws Exception {
        List<Libro> libros1 = new ArrayList();
        Libro libro = new Libro();
        Long isbn;
        boolean validacion = true;
        String opcion = null;
        do {
            mostrarLibros(libros);
            if (libros.size() == 1) {
                for (Libro libro1 : libros) {
                    libro = libro1;
                    System.out.println("El libro a editar es " + libro.toString());
                }
                opcion = "s";
            } else if (libros.size() > 1) {
                for (Libro libro1 : libros) {
                    libros1.add(libro1);
                }
                String option = null;
                do {
                    System.out.println("Ingrese el ISBN del libro que desea editar");
                    Scanner lea = new Scanner(System.in);
                    isbn = lea.nextLong();

                    //convierto los long a comparar a strign porque no encontré la forma de compararlos, me daba error
                    String ingresado = Long.toString(isbn);
                    String leido;

                    //si el isbn ingresado es igual al isbn de alguno de los libros mostrados asignamos el objeto correspondientes a la variable libro a editar
                    for (Libro libro1 : libros1) {
                        leido = Long.toString(libro1.isbn);
                        if (leido.equals(ingresado)) {
                            libro = libro1;
                            break;
                        }
                    }
                    System.out.println("El libro a editar es " + libro.toString());
                    System.out.println("Si la selección es correcta presione 'S' o 'N' para reingresar el código");
                    opcion = "s";
                    Scanner lee = new Scanner(System.in);
                    option = lee.nextLine().toLowerCase();
                } while (!option.equalsIgnoreCase("s"));

            } else {
                System.out.println("No hay ningún libro con ese ISBN, se cancela la edición");
                libro = null;
                validacion = false;
            }
            if (libro != null) {
                boolean correcta = false;
                do {
                    if (!opcion.equalsIgnoreCase("s")) {
                        System.out.println("Si la selección es correcta presione 'S' o 'N' para cancelar edicion");
                        Scanner lee = new Scanner(System.in);
                        opcion = lee.nextLine().toLowerCase();
                    }
                    if (opcion == null) {
                        System.out.println("Debe ingresar una opción válida");
                        correcta = false;
                    }
                    if (opcion.equalsIgnoreCase("s")) {
                        validacion = false;
                        correcta = true;
                    }
                    if (opcion.equalsIgnoreCase("n")) {
                        validacion = false;
                        correcta = true;
                        libro = null;
                    }
                } while (!correcta);
            }
        } while (validacion);
        return libro;
    }

    public Libro editarLibro(Libro libro) {
        Libro libro1 = new Libro();
        if (libro == null) {
            libro1 = libro;
        } else {
            try {
                String titulo = null;
                Editorial editorial1 = new Editorial();
                String option;
                do {
                    System.out.println(" _____ MENU EDITAR LIBRO _____\n"
                            + "     a)- Editar Título\n"
                            + "     b)- Editar Autor\n"
                            + "     c)- Editar Editorial\n"
                            + "     s)- Salir\n");
                    Scanner leer = new Scanner(System.in);
                    option = leer.nextLine().toUpperCase();
                    switch (option) {
                        case "A":
                            System.out.println("Ingrese nuevo Titulo");
                            libro1 = libro;
                            libro1.setTitulo(gS.ingresaTitulo());
                            break;
                        case "B":
                            Autor autor;
                            System.out.println("Ingrese nuevo Autor según listado");
                            String nombreA = gS.ingresaNombre();
                            List<Autor> autores = aS.buscarAutoresPorNombre(nombreA);
                            aS.mostrarAutores(autores);
                            if (!autores.isEmpty()) {
                                Scanner lee = new Scanner(System.in);
                                System.out.println("Si el autor está en la lista ingrese s o cualquier tecla para salir");
                                String option1 = lee.nextLine();
                                if (option1.equalsIgnoreCase("s")) {
                                    System.out.println("Ingrese el codigo del autor");
                                    Integer codigo = lee.nextInt();
                                    autor = ADAO.buscarAutorPorID(codigo);
                                    libro1.setAutor(autor);
                                } else {
                                    libro1 = libro;
                                }
                            } else {
                                System.out.println("El Autor no está cargado, deberá ingresarlo antes de cargar el libro");
                            }
                            break;
                        case "C":
                            System.out.println("Ingrese nueva Editorial");
                            String nombreE = gS.ingresaNombre();
                            List<Editorial> editoriales = eS.buscarEditorialesPorNombre(nombreE);
                            eS.mostrarEditoriales(editoriales);
                            Editorial editorial = new Editorial();
                            if (!editoriales.isEmpty()) {
                                Scanner lee = new Scanner(System.in);
                                System.out.println("Si la editorial está en la lista ingrese s o cualquier tecla para salir");
                                String option2 = lee.nextLine();
                                if (option2.equalsIgnoreCase("s")) {
                                    System.out.println("Ingrese el codigo de la editorial");
                                    Integer codigo = lee.nextInt();
                                    editorial = EDAO.buscarEditorialPorID(codigo);
                                    libro1.setEditorial(editorial);
                                } else {
                                    libro1 = libro;
                                }
                            } else {
                                System.out.println("La Editorial no está cargada, deberá ingresarla antes de cargar el libro");
                            }
                            break;
                        case "S":
                            libro1 = null;
                            break;
                        default:
                            System.out.println("----- La opcion ingresada no es correcta -----\n"
                                    + "      Ingrese una opción válida\n\n");
                            break;
                    }
                } while (!option.equals("S"));
            } catch (Exception e) {
                e.getStackTrace();
                System.out.println("Error al ejecutar el MENU EDITAR LIBRO, vea el siguiente mensaje\n" + e.toString());
            }
            if (libro1 != null) {
                LDAO.modificarLibro(libro1);
            }
        }
        return libro1;
    }

    public Libro darAltaBajaLibro(Libro libro) {
        Libro libro1 = new Libro();
        libro1 = libro;
        if (libro1 != null) {
            try {

                String option;
                do {
                    List<Libro> libros = new ArrayList();
                    System.out.println(" _____ MENU ALTA/BAJA LIBRO _____\n"
                            + "     a)- Dar de Alta\n"
                            + "     b)- Dar de Baja\n"
                            + "     s)- Salir\n");
                    Scanner leer = new Scanner(System.in);
                    option = leer.nextLine().toUpperCase();
                    switch (option) {
                        case "A":
                            if (libro1.alta) {
                                System.out.println("El libro ya estaba dado de alta");
                            } else {
                                libro1.setAlta(true);
                                LDAO.modificarLibro(libro1);
                                System.out.println("**********************************************************");
                                System.out.println("*******      El LIBRO SE HA DADO DE ALTA      ************");
                                System.out.println("**********************************************************");
                            }
                            libros.add(libro1);
                            mostrarLibros(libros);
                            break;
                        case "B":
                            if (libro1.alta) {
                                libro1.setAlta(false);
                                LDAO.modificarLibro(libro1);
                                System.out.println("**********************************************************");
                                System.out.println("*******      El LIBRO SE HA DADO DE BAJA      ************");
                                System.out.println("**********************************************************");

                            } else {
                                System.out.println("El libro ya estaba dado de baja");
                            }
                            libros.add(libro1);
                            mostrarLibros(libros);
                            break;
                        case "S":
                            break;
                        default:
                            System.out.println("----- La opcion ingresada no es correcta -----\n"
                                    + "      Ingrese una opción válida\n\n");
                            break;
                    }
                } while (!option.equals("S"));
            } catch (Exception e) {
                e.getStackTrace();
                System.out.println("Error al ejecutar el MENU ALTA/BAJA LIBRO, vea el siguiente mensaje\n" + e.toString());
            }
        }
        return libro1;
    }

    public void eliminarLibro(Libro libro) throws Exception {
        Libro libro1 = new Libro();
        if (libro != null) {
            try {

                String option;
                do {
                    List<Libro> libros = new ArrayList();
                    System.out.println(" _____ MENU ELIMINAR LIBRO _____\n"
                            + "     a)- Eliminar\n"
                            + "     s)- Salir\n");
                    Scanner leer = new Scanner(System.in);
                    option = leer.nextLine().toUpperCase();
                    switch (option) {
                        case "A":
                            String opcion;
                            Scanner lea = new Scanner(System.in);
                            System.out.println("***************************************************************************");
                            System.out.println("*******      CUIDADO!!!!! VA A ELIMINAR EL SIGUIENT LIBRO      ************");
                            System.out.println("***************************************************************************");
                            libros.add(libro);
                            mostrarLibros(libros);

                            System.out.println("Si está de seguro de eliminar este ítem presione 'S'");
                            opcion = lea.nextLine();
                            if (opcion.equalsIgnoreCase("S")) {
                                LDAO.eliminarLibro(libro);
                                System.out.println("El libro ha sido borrado de la base de datos");
                            } else {
                                System.out.println("HA CANCELADO LA ELIMINACIÓN, EL LIBRO NO SERÁ BORRADO");
                            }

                            break;

                        case "S":
                            break;
                        default:
                            System.out.println("----- La opcion ingresada no es correcta -----\n"
                                    + "      Ingrese una opción válida\n\n");
                            break;

                    }
                } while (!option.equals("S"));
            } catch (Exception e) {
                e.getStackTrace();
                System.out.println("Error al ejecutar el MENU ALTA/BAJA LIBRO, vea el siguiente mensaje\n" + e.toString());

            }
        }

    }
}
