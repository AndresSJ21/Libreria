package Libreria.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIBROS")
public class Libro {

    @Id
    @Column(name = "ISBN")
    public Long isbn;

    @Column(name = "TITULO")
    public String titulo;

    @Column(name = "ANIO")
    public Integer anio;

    @Column(name = "EJEMPLARES")
    public Integer ejemplares;

    @Column(name = "EJEMPLARES_PRESTADOS")
    public Integer ejemplaresPrestados;

    @Column(name = "EJEMPLARES_RESTANTES")
    public Integer ejemplaresRestantes;

    @Column(name = "ALTA")
    //declaramos Boolean (objeto) porque así tiene la posibilidad de ser nulo, si lo declaramos como tipo primitivo
    //boolean no podremos hacerlo
    public Boolean alta;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    // para relaciones OneToMany o ManyToMany se puede utilizar la propiedad FetchType.LAZY (perezoso)
    // que permite traer todos los datos de libros de la tabla pero no trae los datos de Autor.
    // esta opción es muy útil cuando los atributos tienen muchos datos relacionados para no sobrecargar
    // la operación del sistema, sobre todo cuando se realizan múltiples consultas en simultáneo con gran
    // cantidad de datos a recuperar. En caso de requerir un ddato específico no recuperado con la consulta
    // se lo puede invocar específicamente y luego utilizarlo, de lo contrario dará error
    // La otra opción es indicar mediante FetchType.EAGER (ansioso)
    @JoinColumn(name = "AUTOR_ID")
    public Autor autor;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "EDITORIAL_ID")
    public Editorial editorial;

    public Libro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    //constructor vacio: para poder mapear la entidad en base de datos
    public Libro() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares + ", ejemplaresPrestados=" + ejemplaresPrestados + ", ejemplaresRestantes=" + ejemplaresRestantes + ", alta=" + alta + ", autor=" + autor + ", editorial=" + editorial + '}';
    }

}
