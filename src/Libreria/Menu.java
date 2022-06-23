/*
1) Crear base de datos Librería *
2) Crear unidad de persistencia *
3) Crear entidades previamente mencionadas ( excepto Préstamo ) *
4) Generar las tablas con JPA *
5) Crear servicios previamente mencionados. *
6) Crear los métodos para persistir entidades en la base de datos librería *
7) Crear los métodos para dar de alta/bajo o editar dichas entidades.
8) Búsqueda de un Autor por nombre.
9) Búsqueda de un libro por ISBN.
10) Búsqueda de un libro por Título.
11) Búsqueda de un libro/s por nombre de Autor.
12) Búsqueda de un libro/s por nombre de Editorial.
13) Agregar las siguientes validaciones a todas las funcionalidades de la aplicación:
• Validar campos obligatorios.
• No ingresar datos duplicados.
 */
package Libreria;

import Libreria.Entidades.Editorial;
import Libreria.Entidades.Libro;
import Libreria.Servicios.AutorService;
import Libreria.Servicios.EditorialService;
import Libreria.Servicios.GeneralesService;
import Libreria.Servicios.LibroService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final LibroService libroServicio = new LibroService();
    private final AutorService autorServicio = new AutorService();
    private final EditorialService editorialServicio = new EditorialService();
    private final GeneralesService gS = new GeneralesService();

    public void menu() {

        try {
            String option = "";
            do {
                System.out.println("=====================================");
                System.out.println("=          MENU BIBLIOTECA          =");
                System.out.println("=====================================");
                // System.out.println("\n");
                System.out.println(
                        "     A)- LISTAR/BUSCAR\n"
                        + "     B)- CREAR\n"
                        + "     C)- MODIFICAR\n"
                        + "     D)- BAJA / ALTA DE ÍTEMS\n"
                        + "     E)- ELIMINAR\n"
                        + "     S)- SALIR\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        menuListar();
                        break;
                    case "B":
                        menuCrear();
                        break;
                    case "C":
                        menuModificar();
                        break;
                    case "D":
                        menuBajaAlta();
                        break;
                    case "E":
                        menuEliminar();
                        break;
                    case "S":
                        System.out.println("Hasta pronto!\n\n");
                        break;
                    default:
                        System.out.println("----- La opcion ingresada no es correcta -----\n"
                                + "      Ingrese una opción válida\n\n");
                        break;
                }
            } while (!option.equals("S"));
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("Error al ejecutar el menú principal, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuListar() {
        try {
            String option = "";
            do {
                System.out.println(" +++++++ MENU LISTAR/BUSCAR +++++++\n"
                        + "     a)- Listar/Buscar Libros\n"
                        + "     b)- Listar/Buscar Autores\n"
                        + "     c)- Listar/Buscar Editoriales\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("listar/buscar libros");
                        menuLBLibros();
                        break;
                    case "B":
                        System.out.println("listar/buscar autores");
                        menuLBAutores();
                        break;
                    case "C":
                        System.out.println("listar/buscar editoriales");
                        menuLBEditoriales();
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
            System.out.println("Error al ejecutar el MENU LISTAR/BUSCAR, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuLBLibros() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU LISTAR/BUSCAR LIBROS _____\n"
                        + "     a)- Listar/Buscar Todos\n"
                        + "     b)- Listar/Buscar Por ISBN\n"
                        + "     c)- Listar/Buscar Por Nombre\n"
                        + "     d)- Listar/Buscar Por Autor\n"
                        + "     e)- Listar/Buscar Por Editorial\n"
                        + "     f)- Listar/Buscar Por Año\n"
                        + "     g)- Listar/Buscar Por Stock Cero\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("listar/buscar libros cargados");
                        libroServicio.mostrarLibros(libroServicio.listarLibros());
                        break;
                    case "B":
                        System.out.println("listar/buscar libros por ISBN");
                        libroServicio.mostrarLibros((List<Libro>) libroServicio.buscarLibroPorISBN(gS.ingresaISBN()));
                        break;
                    case "C":
                        System.out.println("listar/buscar libros por Nombre");
                        libroServicio.mostrarLibros(libroServicio.buscarLibrosPorNombre(gS.ingresaTitulo()));
                        break;
                    case "D":
                        System.out.println("listar/buscar libros por Autor");
                        libroServicio.mostrarLibros(libroServicio.buscarLibrosPorAutor(gS.ingresaNombre()));
                        break;
                    case "E":
                        System.out.println("listar/buscar libros por Editorial");
                        libroServicio.mostrarLibros(libroServicio.buscarLibrosPorEditorial(gS.ingresaNombre()));
                        break;
                    case "F":
                        System.out.println("listar/buscar libros por Año");
                        libroServicio.mostrarLibros(libroServicio.buscarLibrosPorAnio(gS.ingresaAnio()));
                        break;
                    case "G":
                        System.out.println("listar/buscar libros por Stock Cero");
                        libroServicio.mostrarLibros(libroServicio.buscarLibrosPorStockCero());
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
            System.out.println("Error al ejecutar el MENU LISTAR/BUSCAR LIBROS, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuLBAutores() {

        try {
            String option = "";
            do {
                System.out.println(" _____ MENU LISTAR/BUSCAR AUTORES _____\n"
                        + "     a)- Listar/Buscar Todos\n"
                        + "     b)- Listar/Buscar Por ID\n"
                        + "     c)- Listar/Buscar Por Nombre\n"
                        + "     d)- Listar/Buscar Por Libro\n"
                        + "     e)- Listar/Buscar Por Editorial\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("listar/buscar autores existentes");
                        autorServicio.mostrarAutores(autorServicio.listarAutores());
                        break;
                    case "B":
                        System.out.println("listar/buscar autores por ID");
                        autorServicio.mostrarAutor(autorServicio.buscarPorID(gS.ingresaCodigo()));
                        break;
                    case "C":
                        System.out.println("listar/buscar autores por Nombre");
                        autorServicio.mostrarAutores(autorServicio.buscarAutoresPorNombre(gS.ingresaNombre()));
                        break;
                    case "D":
                        System.out.println("listar/buscar autores por Libro");
                        autorServicio.mostrarAutores(autorServicio.buscarAutoresPorLibro(gS.ingresaTitulo()));
                        break;
                    case "E":
                        System.out.println("listar/buscar autores por Editorial");
                        autorServicio.mostrarAutores(autorServicio.buscarAutoresPorEditorial(gS.ingresaNombre()));
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
            System.out.println("Error al ejecutar el MENU LISTAR/BUSCAR AUTORES, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuLBEditoriales() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU LISTAR/BUSCAR EDITORIALES _____\n"
                        + "     a)- Listar/Buscar Todas\n"
                        + "     b)- Listar/Buscar Por ID\n"
                        + "     c)- Listar/Buscar Por Nombre\n"
                        + "     d)- Listar/Buscar Por Libro\n"
                        + "     e)- Listar/Buscar Por Autor\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("listar/buscar editoriales existentes");
                        editorialServicio.mostrarEditoriales(editorialServicio.listarEditoriales());
                        break;
                    case "B":
                        System.out.println("listar/buscar editoriales por ID");
                        List<Editorial> editoriales = new ArrayList();
                        editoriales.add(editorialServicio.buscarPorID(gS.ingresaCodigo()));
                        editorialServicio.mostrarEditoriales(editoriales);
                        break;
                    case "C":
                        System.out.println("listar/buscar editoriales por Nombre");
                        editorialServicio.mostrarEditoriales(editorialServicio.buscarEditorialesPorNombre(gS.ingresaNombre()));
                        break;
                    case "D":
                        System.out.println("listar/buscar editoriales por Libro");
                        editorialServicio.mostrarEditoriales(editorialServicio.buscarEditorialesPorLibro(gS.ingresaTitulo()));
                        break;
                    case "E":
                        System.out.println("listar/buscar editoriales por Autor");
                        editorialServicio.mostrarEditoriales(editorialServicio.buscarEditorialesPorAutor(gS.ingresaNombre()));
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
            System.out.println("Error al ejecutar el MENU LISTAR/BUSCAR EDITORIALES, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuCrear() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU CARGAR _____\n"
                        + "     a)- Cargar Autor\n"
                        + "     b)- Cargar Editorial\n"
                        + "     c)- Cargar Libro\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println(" ________ Alta Autor ________");
                        autorServicio.altaAutor();
                        break;
                    case "B":
                        System.out.println(" ________ Alta Editorial ________");
                        editorialServicio.altaEditorial();
                        break;
                    case "C":
                        System.out.println(" ________ Alta Libro ________");
                        libroServicio.altaLibro();
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
            System.out.println("Error al ejecutar el MENU CARGAR, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuModificar() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU MODIFICAR _____\n"
                        + "     a)- Modificar Autor\n"
                        + "     b)- Modificar Editorial\n"
                        + "     c)- Modificar Libro\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Modificar Autor");
                        menuModificarAutor();
                        break;
                    case "B":
                        System.out.println("Modificar Editorial");
                        menuModificarEditorial();
                        break;
                    case "C":
                        System.out.println("Modificar Libro");
                        menuModificarLibro();
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
            System.out.println("Error al ejecutar el MENU MODIFICAR, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuModificarAutor() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU MODIFICAR AUTOR _____\n"
                        + "     a)- Buscar Autor por ID\n"
                        + "     b)- Buscar Autor por Nombre\n"
                        + "     c)- Buscar Autor por Editorial\n"
                        + "     d)- Buscar Autor por Libro\n"
                        + "     e)- Modificar Autor\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Buscar Autor por ID");
                        break;
                    case "B":
                        System.out.println("Buscar Autor por Nombre");
                        break;
                    case "C":
                        System.out.println("Buscar Autor por Editorial");
                        break;
                    case "D":
                        System.out.println("Buscar Autor por Libro");
                        break;
                    case "E":
                        System.out.println("Modificar Autor");
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
            System.out.println("Error al ejecutar el MENU MODIFICAR AUTOR, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuModificarEditorial() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU MODIFICAR EDITORIAL _____\n"
                        + "     a)- Buscar Editorial por ID\n"
                        + "     b)- Buscar Editorial por Nombre\n"
                        + "     c)- Buscar Editorial por Libro\n"
                        + "     d)- Buscar Editorial por Autor\n"
                        + "     e)- Modificar Editorial\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Buscar Editorial por ID");
                        break;
                    case "B":
                        System.out.println("Buscar Editorial por Nombre");
                        break;
                    case "C":
                        System.out.println("Buscar Editorial por Libro");
                        break;
                    case "D":
                        System.out.println("Buscar Editorial por Autor");
                        break;
                    case "E":
                        System.out.println("Modificar Editorial");
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
            System.out.println("Error al ejecutar el MENU MODIFICAR EDITORIAL, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuModificarLibro() throws Exception {
        try {
            String option = "";
            do {
                Scanner leer = new Scanner(System.in);
                Libro libro = new Libro();
                libro = null;
                //List<Libro> libros = new ArrayList();
                System.out.println(" _____ MENU MODIFICAR LIBRO _____\n"
                        + "     a)- Editar Libro Buscando por ISBN\n"
                        + "     b)- Editar Libro Buscando por Título\n"
                        + "     c)- Editar Libro Buscando por Autor\n"
                        + "     d)- Editar Libro Buscando por Editorial\n"
                        + "     e)- Modificar Libro\n"
                        + "     s)- Salir\n");
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Buscar Libro por ISBN");
                        libroServicio.editarLibro(libroServicio.seleccionarLibro(libroServicio.buscarLibroPorISBN(gS.ingresaISBN())));
                        //libro = libroServicio.seleccionarLibro(libroServicio.buscarLibroPorISBN(gS.ingresaISBN()));
                        break;
                    case "B":
                        System.out.println("Buscar Libro por Titulo");
                        libroServicio.editarLibro(libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorNombre(gS.ingresaTitulo())));
                        //libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorNombre(gS.ingresaTitulo()));
                        //libroServicio.editarLibro(libroServicio.seleccionarLibro(libros));
                        break;
                    case "C":
                        System.out.println("Buscar Libro por Autor");
                        libroServicio.editarLibro(libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorAutor(gS.ingresaNombre())));
                        //libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorAutor(gS.ingresaNombre()));
                        break;
                    case "D":
                        System.out.println("Buscar Libro por Editorial");
                        libroServicio.editarLibro(libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorEditorial(gS.ingresaNombre())));
                        break;
                    case "E":
                        System.out.println("Modificar Libro");
//                        
//                        if (libro == null) {
//                            System.out.println("No se ha seleccionado ningún ejemplar");
//                            
//                        } else {
//                            System.out.println("El libro a editar es: " + libro.toString());
//                            libroServicio.editarLibro(libro);
//                            libro = null;
//                        }
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
            System.out.println("Error al ejecutar el MENU MODIFICAR LIBRO, vea el siguiente mensaje\n" + e.toString());
            throw e;
        }
    }

    public void menuBajaAlta() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU BAJA / ALTA ÍTEMS _____\n"
                        + "     a)- Baja/Alta Autor\n"
                        + "     b)- Baja/Alta Editorial\n"
                        + "     c)- Baja/Alta Libro\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Baja/Alta Autor");
                        menuBajaAltaAutor();
                        break;
                    case "B":
                        System.out.println("Baja/Alta Editorial");
                        menuBajaAltaEditorial();
                        break;
                    case "C":
                        System.out.println("Baja/Alta Libro");
                        menuBajaAltaLibro();
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
            System.out.println("Error al ejecutar el MENU BAJA/ALTA ÍTEMS, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuBajaAltaAutor() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU BAJA/ALTA AUTOR _____\n"
                        + "     a)- Buscar Autor por ID\n"
                        + "     b)- Buscar Autor por Nombre\n"
                        + "     c)- Buscar Autor por Editorial\n"
                        + "     d)- Buscar Autor por Libro\n"
                        + "     e)- Baja/Alta Autor\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Buscar Autor por ID");
                        break;
                    case "B":
                        System.out.println("Buscar Autor por Nombre");
                        break;
                    case "C":
                        System.out.println("Buscar Autor por Editorial");
                        break;
                    case "D":
                        System.out.println("Buscar Autor por Libro");
                        break;
                    case "E":
                        System.out.println("Baja/Alata Autor");
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
            System.out.println("Error al ejecutar el MENU BAJA/ALTA AUTOR, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuBajaAltaEditorial() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU BAJA/ALTA EDITORIAL _____\n"
                        + "     a)- Buscar Editorial por ID\n"
                        + "     b)- Buscar Editorial por Nombre\n"
                        + "     c)- Buscar Editorial por Libro\n"
                        + "     d)- Buscar Editorial por Autor\n"
                        + "     e)- Baja/Alta Editorial\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Buscar Editorial por ID");
                        break;
                    case "B":
                        System.out.println("Buscar Editorial por Nombre");
                        break;
                    case "C":
                        System.out.println("Buscar Editorial por Libro");
                        break;
                    case "D":
                        System.out.println("Buscar Editorial por Autor");
                        break;
                    case "E":
                        System.out.println("Baja/Alta Editorial");
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
            System.out.println("Error al ejecutar el MENU BAJA/ALTA EDITORIAL, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuBajaAltaLibro() {
        try {
            Libro libro = new Libro();
            String option = "";
            do {
                System.out.println(" _____ MENU BAJA/ALTA LIBRO _____\n"
                        + "     a)- Buscar Libro por ISBN\n"
                        + "     b)- Buscar Libro por Título\n"
                        + "     c)- Buscar Libro por Autor\n"
                        + "     d)- Buscar Libro por Editorial\n"
                        + "     e)- Baja/Alta Libro\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Buscar Libro por ISBN");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibroPorISBN(gS.ingresaISBN()));
                        libroServicio.darAltaBajaLibro(libro);
                        break;
                    case "B":
                        System.out.println("Buscar Libro por Titulo");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorNombre(gS.ingresaTitulo()));
                        libroServicio.darAltaBajaLibro(libro);
                        break;
                    case "C":
                        System.out.println("Buscar Libro por Autor");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorAutor(gS.ingresaNombre()));
                        libroServicio.darAltaBajaLibro(libro);
                        break;
                    case "D":
                        System.out.println("Buscar Libro por Editorial");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorEditorial(gS.ingresaNombre()));
                        libroServicio.darAltaBajaLibro(libro);
                        break;
                    case "E":
                        System.out.println("Baja/Alta Libro");
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
            System.out.println("Error al ejecutar el MENU BAJA/ALTA LIBRO, vea el siguiente mensaje\n" + e.toString());
        }
    }

    public void menuEliminar() {
        try {
            String option = "";
            do {
                System.out.println(" _____ MENU ELIMINAR ÍTEMS _____\n"
                        + "     a)- Eliminar Autor\n"
                        + "     b)- Eliminar Editorial\n"
                        + "     c)- Eliminar Libro\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Eliminar Autor");
                        menuBajaAltaAutor();
                        break;
                    case "B":
                        System.out.println("Eliminar Editorial");
                        menuBajaAltaEditorial();
                        break;
                    case "C":
                        System.out.println("Eliminar Libro");
                        menuEliminarLibro();
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
            System.out.println("Error al ejecutar el MENU BAJA/ALTA ÍTEMS, vea el siguiente mensaje\n" + e.toString());
        }
    }
    
     public void menuEliminarLibro() {
        try {
            Libro libro = new Libro();
            String option = "";
            do {
                System.out.println(" _____ MENU ELIMINAR LIBRO _____\n"
                        + "     a)- Buscar Libro por ISBN\n"
                        + "     b)- Buscar Libro por Título\n"
                        + "     c)- Buscar Libro por Autor\n"
                        + "     d)- Buscar Libro por Editorial\n"
                        + "     s)- Salir\n");
                Scanner leer = new Scanner(System.in);
                option = leer.nextLine().toUpperCase();
                switch (option) {
                    case "A":
                        System.out.println("Buscar Libro por ISBN");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibroPorISBN(gS.ingresaISBN()));
                        libroServicio.eliminarLibro(libro);
                        break;
                    case "B":
                        System.out.println("Buscar Libro por Titulo");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorNombre(gS.ingresaTitulo()));
                        libroServicio.eliminarLibro(libro);
                        break;
                    case "C":
                        System.out.println("Buscar Libro por Autor");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorAutor(gS.ingresaNombre()));
                        libroServicio.eliminarLibro(libro);
                        break;
                    case "D":
                        System.out.println("Buscar Libro por Editorial");
                        libro = libroServicio.seleccionarLibro(libroServicio.buscarLibrosPorEditorial(gS.ingresaNombre()));
                        libroServicio.eliminarLibro(libro);
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
            System.out.println("Error al ejecutar el MENU ELIMINAR LIBRO, vea el siguiente mensaje\n" + e.toString());
        }
    }
}
