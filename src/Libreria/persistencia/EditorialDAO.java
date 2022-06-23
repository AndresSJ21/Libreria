package Libreria.persistencia;

import Libreria.Entidades.Editorial;
import java.util.List;

public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }

    public List<Editorial> listarEditoriales() {
        conectar();
        List<Editorial> editoriales;
        editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();
        return editoriales;
    }

    public void modificarEditorial(Editorial editorial) {
        editar(editorial);
    }

    public Editorial buscarEditorialPorNombre(String nombre) {
        conectar();
        Editorial editorial;
        editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return editorial;
    }

    public List<Editorial> buscarEditorialesPorNombre(String nombre) {
        conectar();
        List<Editorial> editoriales;
        String nombre1 = "%" + nombre + "%";
        editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre")
                .setParameter("nombre", nombre1).getResultList();
        desconectar();
        return editoriales;
    }

    public Editorial buscarEditorialPorID(Integer id) throws Exception {
        conectar();
        Editorial editorial;
        try {
            editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id LIKE :id")
                    .setParameter("id", id).getSingleResult();
            desconectar();
        } catch (Exception e) {
            throw new Exception("no encontramos ninguna editorial");
        }
        return editorial;
    }

    public List<Editorial> buscarEditorialesPorLibro(String titulo) throws Exception {
        conectar();
        List<Editorial> editorial;
        String titulo1 = "%"+titulo+"%";
        try {
            editorial = em.createQuery("SELECT e FROM Libro l JOIN l.editorial e WHERE l.titulo LIKE :titulo ORDER BY e.nombre")
                    .setParameter("titulo", titulo1).getResultList();
            desconectar();
        } catch (Exception e) {
            throw new Exception("no encontramos ninguna editorial para ese fragmento de título");
        }
        return editorial;
    }

    public List<Editorial> buscarEditorialesPorAutor(String nombre) throws Exception {
        conectar();
        List<Editorial> editorial;
        String nombre1 = "%"+nombre+"%";
        try {
            editorial = em.createQuery("SELECT e FROM Libro l JOIN l.editorial e JOIN l.autor a WHERE a.nombre LIKE :nombre ORDER BY e.nombre")
                    .setParameter("nombre", nombre1).getResultList();
            desconectar();
        } catch (Exception e) {
            throw new Exception("no encontramos ninguna editorial para ese fragmento de nombre de autor");
        }
        return editorial;
    }

    public void altaEditorial(Boolean alta, Integer id) throws Exception {
        Editorial editorial = buscarEditorialPorID(id);
        if (editorial.getAlta() == true) {
            System.out.println("La editorial " + editorial.getNombre() + " ya estaba activa");
        } else {
            editorial.setAlta(true);
            modificarEditorial(editorial);
            System.out.println("La editorial " + editorial.getNombre() + " se ha dado de alta nuevamente");
        }
    }

    public void bajaEditorial(Boolean alta, Integer id) throws Exception {
        Editorial editorial = buscarEditorialPorID(id);
        if (editorial.getAlta() == false) {
            System.out.println("La editorial " + editorial.getNombre() + " ya estaba dada de baja.");
        } else {
            editorial.setAlta(false);
            modificarEditorial(editorial);
            System.out.println("La editorial " + editorial.getNombre() + " se ha dado de baja.");
        }
    }

    // En vez de borrar se prefiere un soft delete, dando de baja elemento de las tablas
    // mediante una desactivación de los mismos para evitar problemas de ruptura de código
    // por eliminaciones de objetos relacionados. Sobre todo en tablas muy grandes donde 
    // las relaciones pueden estar muy escondidas y no ser simples de rastrear.
//  public void borrarEditorial(Integer id) {
//        Editorial editorial = buscarEditorialPorID(id);
//        eliminar(editorial);
//        System.out.println("La editorial " + editorial.getNombre() + " ha sido eliminada.");
//    }
}
