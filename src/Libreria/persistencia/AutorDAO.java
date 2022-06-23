package Libreria.persistencia;

import Libreria.Entidades.Autor;
import java.util.List;

public class AutorDAO extends DAO<Autor> {

    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }

    public List<Autor> listarAutores() {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a")
                .getResultList();
        desconectar();
        return autores;
    }

    public void modificarAutor(Autor autor) {
        editar(autor);
    }

    public List<Autor> buscarAutoresPorNombre(String nombre) {
        conectar();
        String nombre1 = "%" + nombre + "%";
        List<Autor> autores = em.createQuery(
                "SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                .setParameter("nombre", nombre1)
                .getResultList();
        desconectar();
        return autores;
    }

    public Autor buscarAutorPorNombre(String nombre) {
        conectar();
        Autor autor = (Autor) em.createQuery(
                "SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                .setParameter("nombre", nombre)
                .getSingleResult();
        desconectar();
        return autor;
    }

    public Autor buscarAutorPorID(Integer id) throws Exception {
        conectar();
        Autor autor;
        try {
            autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id LIKE :id")
                    .setParameter("id", id).getSingleResult();
            desconectar();
        } catch (Exception e) {
            throw new Exception("no encontramos ningún autor");
        }
        return autor;
    }

    public List<Autor> buscarAutoresPorLibro(String titulo) {
        conectar();
        String titulo1 = "%" + titulo + "%";
        List<Autor> autores = em.createQuery(
                "SELECT b  FROM Libro l JOIN l.autor b WHERE l.titulo LIKE :titulo ORDER BY b.nombre, l.titulo")
                .setParameter("titulo", titulo1)
                .getResultList();
        desconectar();
        return autores;
    }

    //solo a fines de hacer un join con las 3 tablas para vincular datos indirectamente
    public List<Autor> buscarAutoresPorEditorial(String nombre) {
        conectar();
        String nombre1 = "%" + nombre + "%";
        List<Autor> autores = em.createQuery(
                "SELECT b FROM Libro l JOIN l.autor b JOIN l.editorial c WHERE c.nombre LIKE :nombre ORDER BY b.nombre, c.nombre")
                .setParameter("nombre", nombre1)
                .getResultList();
        desconectar();
        return autores;
    }

    public void altaAutor(Boolean alta, Integer id) throws Exception {
        Autor autor = buscarAutorPorID(id);
        if (autor.getAlta() == true) {
            System.out.println("El autor " + autor.getNombre() + " ya estaba dado de alta.");
        } else {
            autor.setAlta(true);
            modificarAutor(autor);
            System.out.println("El autor " + autor.getNombre() + " se ha dado de alta nuevamente.");
        }
    }

    public void bajaAutor(Boolean alta, Integer id) throws Exception {
        Autor autor = buscarAutorPorID(id);
        if (autor.getAlta() == false) {
            System.out.println("El autor " + autor.getNombre() + " ya estaba dado de baja.");
        } else {
            autor.setAlta(false);
            modificarAutor(autor);
            System.out.println("El autor " + autor.getNombre() + " se ha dado de baja.");
        }
    }

    public void borrarAutor(Integer id) throws Exception {
        Autor autor = buscarAutorPorID(id);
        eliminar(autor);
    }
}
