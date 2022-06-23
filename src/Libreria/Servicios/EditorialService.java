package Libreria.Servicios;

import Libreria.Entidades.Editorial;
import Libreria.persistencia.EditorialDAO;
import java.util.List;
import java.util.Scanner;

public class EditorialService {

    private final EditorialDAO DAO;

    public EditorialService() {
        this.DAO = new EditorialDAO();
    }
    GeneralesService gs = new GeneralesService();
    Scanner sc = new Scanner(System.in);

    public void modificarNombre() throws Exception {
        try {
            System.out.println("Va a modificar el nombre de una editorial.");
            Editorial editorial = buscarPorID(gs.ingresaCodigo());
            System.out.println("Ingrese el nuevo nombre de la editorial.");
            String nombre = sc.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre de la editorial.");
            }
            editorial.setNombre(nombre);
            DAO.modificarEditorial(editorial);
            System.out.println("El nombre de la editorial ha cambiado exitosamente a '" + nombre + "'.");
        } catch (Exception e) {
            throw e;
        }
    }

    public Editorial crearEditorial(String nombre) {
        Editorial editorial = new Editorial();
        try {
            editorial.setNombre(nombre);
            editorial.setAlta(Boolean.TRUE);

            DAO.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Editorial> listarEditoriales() {
        try {
            return DAO.listarEditoriales();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void mostrarEditoriales(List<Editorial> editoriales) {
        int cont = 1;
        if (editoriales.isEmpty()) {
            System.out.println("No hay editoriales cargadas");
        } else {
            for (Editorial editorial : editoriales) {
                System.out.println(" " + cont + ").- " + "Código: "
                        + editorial.getId() + ", Nombre: " + editorial.getNombre()
                        + ", Alta: " + editorial.getAlta());
                cont++;
            }
        }
        System.out.println("\n");
    }

    public Editorial buscarPorNombre(String nombre) {
        try {
            Editorial editorial = DAO.buscarEditorialPorNombre(nombre);
            return editorial;
        } catch (Exception e) {
            System.out.println("El nombre ingresado no existe en la base de datos.");
            return null;
        }
    }

    public List<Editorial> buscarEditorialesPorNombre(String nombre) {
        List<Editorial> editoriales;
        editoriales = DAO.buscarEditorialesPorNombre(nombre);
        return editoriales;
    }

    public Editorial buscarPorID(Integer id) {
        try {
            Editorial editorial = DAO.buscarEditorialPorID(id);
            return editorial;
        } catch (Exception e) {
            System.out.println("El nombre ingresado no existe en la base de datos.");
            return null;
        }
    }

    public List<Editorial> buscarEditorialesPorLibro(String titulo) {
        try {
            List<Editorial> editoriales = DAO.buscarEditorialesPorLibro(titulo);
            return editoriales;
        } catch (Exception e) {
            System.out.println("El titulo o texto ingresado no existe en la base de datos.");
            return null;
        }
    }

    public List<Editorial> buscarEditorialesPorAutor(String nombre) {
        try {
            List<Editorial> editoriales = DAO.buscarEditorialesPorAutor(nombre);
            return editoriales;
        } catch (Exception e) {
            System.out.println("No existe editorial que tenga títulos del autor o fragmento de nombre ingresado.");
            return null;
        }
    }

    public void altaEditorial() {
        Editorial editorial;
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
                    System.out.println("   ¡¡¡ La Editorial ingresada ya existe !!!");
                    System.out.println("Presione cualquier tecla para probar con otro nombre o s para salir");
                    Scanner leer = new Scanner(System.in);
                    opcion = leer.nextLine();
                }
            } while (!opcion.equalsIgnoreCase("s"));

            if (validacion) {
                editorial = crearEditorial(nombre);
                System.out.println("Se ha dado de alta a " + buscarPorNombre(nombre).toString());
            } else {
                System.out.println("No se ha dado de alta ningún Autor");
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
