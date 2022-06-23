/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC1
 * @param <T>
 */
public class DAO<T> {

    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("Guia14_Ejercicio01_LibreriaPU");
    protected EntityManager em = EMF.createEntityManager();

    protected void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }

    protected void guardar(T objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    protected void editar(T objeto) {
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    protected void eliminar(T objeto) throws Exception {
        try {
            conectar();
            
            // con la siguiente linea hacemos que el entity manager reconozca al objeto a eliminar
            T object1 = em.merge(objeto);
            
            em.getTransaction().begin();
            /*
            otra forma de que em reconozca al objeto a eliminar
            if(!em.contains(objeto)){
                objeto = em.merge(objeto);
            }
            */          
            em.remove(objeto);
            em.getTransaction().commit();
            desconectar();
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            desconectar();
            throw new Exception("La entidad de tipo " + objeto.getClass().getSimpleName() + " no se ha podido eliminar");
        }
    }
}
