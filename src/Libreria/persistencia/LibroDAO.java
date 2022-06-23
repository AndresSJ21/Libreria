
package Libreria.persistencia;

import Libreria.Entidades.Libro;
import java.util.List;

public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    public List<Libro> listarLibros() {
        conectar();
        List<Libro> libros;
        libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        desconectar();
        return libros;
    }

    public void modificarLibro(Libro libro) {
        super.editar(libro);
    }

    public void eliminarLibro(Libro libro)throws Exception{
        super.eliminar(libro);
    }
    
//    
//        public Libro buscarLibroPorISBN(Long isbn) {
//        conectar();
//        Libro libro;
//        libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn")
//                .setParameter("isbn", isbn).getSingleResult();
//        desconectar();
//        return libro;
//    }
    
    
    public List<Libro> buscarLibroPorISBN(Long isbn) throws Exception {
        try{
        conectar();
        List<Libro> libros;
        libros = em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn")
                .setParameter("isbn", isbn).getResultList();
        desconectar();
        return libros;
        }catch(Exception e){
            throw new Exception("No encontramos ningún resultado");
        }
        
    }

    public List<Libro> buscarLibrosPorNombre(String nombre) {
        conectar();
        String titulo1 = "%" + nombre + "%";
        List<Libro> libros = em.createQuery(
                "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo1)
                .getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibrosPorAutor(String nombre) {
        conectar();
        String nombre1 = "%" + nombre + "%";
        List<Libro> libros = em.createQuery(
                "SELECT a FROM Libro a WHERE a.autor.nombre LIKE :nombre ORDER BY a.autor.nombre, a.titulo")
                .setParameter("nombre", nombre1)
                .getResultList();
        desconectar();
        System.out.println(libros.size());
        return libros;
    }

    public List<Libro> buscarLibrosPorEditorial(String nombre) {
        conectar();
        String nombre1 = "%" + nombre + "%";
        List<Libro> libros = em.createQuery(
                "SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre ORDER BY l.editorial.nombre, l.titulo")
                .setParameter("nombre", nombre1)
                .getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibrosPorAnio(Integer anio) {
        conectar();
        List<Libro> libros = em.createQuery(
                "SELECT l FROM Libro l WHERE l.anio LIKE :anio ORDER BY l.titulo")
                .setParameter("anio", anio)
                .getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibrosPorStockCero() {
        conectar();
        Integer stock = 0;
        List<Libro> libros = em.createQuery(
                "SELECT l FROM Libro l WHERE l.ejemplaresRestantes LIKE :stock ORDER BY l.titulo")
                .setParameter("stock", stock)
                .getResultList();
        desconectar();
        return libros;
    }

    public void altaLibro(Boolean alta, Long isbn) throws Exception {
        Libro libro = (Libro) buscarLibroPorISBN(isbn);
        if (libro.getAlta() == true) {
            System.out.println("El libro " + libro.getTitulo() + " ya estaba activo");
        } else {
            libro.setAlta(true);
            modificarLibro(libro);
            System.out.println("El libro " + libro.getTitulo() + " se ha dado de alta nuevamente");
        }
    }

    public void bajaLibro(Boolean alta, Long isbn) throws Exception{
        Libro libro = (Libro) buscarLibroPorISBN(isbn);
        if (libro.getAlta() == false) {
            System.out.println("El libro " + libro.getTitulo() + " ya estaba dado de baja.");
        } else {
            libro.setAlta(false);
            modificarLibro(libro);
            System.out.println("El libro " + libro.getTitulo() + " se ha dado de baja.");
        }
    }
    // En vez de borrar se prefiere un soft delete, dando de baja elemento de las tablas
    // mediante una desactivación de los mismos para evitar problemas de ruptura de código
    // por eliminaciones de objetos relacionados. Sobre todo en tablas muy grandes donde 
    // las relaciones pueden estar muy escondidas y no ser simples de rastrear.

//  public void borrarLibro(Long isbn) {
//        Libro libro = buscarLibroPorISBN(isbn);
//        eliminar(libro);
//        System.out.println("El libro " + libro.getNombre() + " ha sido eliminado.");
//    }
}
