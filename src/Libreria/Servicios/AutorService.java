package Libreria.Servicios;

import Libreria.Entidades.Autor;
import Libreria.persistencia.AutorDAO;
import java.util.List;
import java.util.Scanner;

public class AutorService {

    private final AutorDAO DAO;

    public AutorService() {
        this.DAO = new AutorDAO();
    }
    GeneralesService gs = new GeneralesService();
    Scanner sc = new Scanner(System.in);

    public void modificarNombre() throws Exception {
        try {
            System.out.println("Va a modificar el nombre de un autor.");
            Autor autor = buscarPorID(gs.ingresaCodigo());
            System.out.println("Ingrese el nuevo nombre del autor.");
            String nombre = sc.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del autor.");
            }
            autor.setNombre(nombre);
            DAO.modificarAutor(autor);
            System.out.println("El nombre del autor ha cambiado exitosamente a '" + nombre + "'.");
        } catch (Exception e) {
            throw e;
        }
    }

    public Autor crearAutor(String nombre) {
        Autor autor = new Autor();
        try {
            autor.setNombre(nombre);
            autor.setAlta(Boolean.TRUE);

            DAO.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Autor> listarAutores() {
        try {
            return DAO.listarAutores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void mostrarAutores(List<Autor> autores) {
        if (autores.isEmpty()) {
            System.out.println("No hay autores cargados");
        } else {
            int cont = 1;
            for (Autor autor : autores) {
                System.out.println(" " + cont + ").- " + "Código: "
                        + autor.getId() + ", Nombre: " + autor.getNombre()
                        + ", Alta: " + autor.getAlta());
                cont++;
            }
        }
        System.out.println("\n");
    }

    public void mostrarAutor(Autor autor) {
        if (autor == null) {
            System.out.println("No hay autores cargados");
        } else {
            int cont = 1;
            System.out.println(" " + cont + ").- " + ", Código: "
                    + autor.getId() + ", Nombre: " + autor.getNombre()
                    + ", Alta: " + autor.getAlta());
        }
        System.out.println("\n");
    }

    public Autor buscarPorNombre(String nombre) {
        try {
            Autor autor = DAO.buscarAutorPorNombre(nombre);
            return autor;
        } catch (Exception e) {
            System.out.println("El nombre ingresado no existe en la base de datos.");
            return null;
        }

    }

    public List<Autor> buscarAutoresPorNombre(String nombre) {
        List<Autor> autores;
        autores = DAO.buscarAutoresPorNombre(nombre);
        return autores;
    }

    public List<Autor> buscarAutoresPorEditorial(String nombre) {
        List<Autor> autores;
        autores = DAO.buscarAutoresPorEditorial(nombre);
        return autores;
    }

    public Autor buscarPorID(Integer id) {
        try {
            Autor autor = DAO.buscarAutorPorID(id);
            return autor;
        } catch (Exception e) {
            System.out.println("El nombre ingresado no existe en la base de datos.");
            return null;
        }
    }

    public List<Autor> buscarAutoresPorLibro(String titulo) {
        List<Autor> autores;
        autores = DAO.buscarAutoresPorLibro(titulo);
        return autores;
    }

    public Autor altaAutor() {
        Autor autor, autor1;
        Boolean validacion = false;
        String nombre;
        String opcion;
        try {
            do {
                nombre = gs.ingresaNombre();
                if (buscarPorNombre(nombre) == null) {
                    validacion = true;
                    opcion = "s";
                } else {
                    System.out.println("   ¡¡¡ El Autor ingresado ya existe !!!");
                    System.out.println("Presione cualquier tecla para probar con otro nombre o s para salir");
                    Scanner leer = new Scanner(System.in);
                    opcion = leer.nextLine();
                }
            } while (!opcion.equalsIgnoreCase("s"));

            if (validacion) {
                autor = crearAutor(nombre);
                autor1 = buscarPorNombre(nombre);
                System.out.println("Se ha dado de alta a " + autor1.toString());
                return autor1;
            } else {
                System.out.println("No se ha dado de alta ningún Autor");
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
