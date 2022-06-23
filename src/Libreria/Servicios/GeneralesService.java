package Libreria.Servicios;

import java.util.Scanner;

public class GeneralesService {

    public String ingresaNombre() {
        Scanner leer = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("--- Ingrese nombre ---:\n");
            nombre = leer.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("--- ¡¡¡Ingrese un nombre válido!!! ---\n");
            }
        } while (nombre.isEmpty());
        return nombre;
    }

    public Long ingresaISBN() {
        Scanner leer = new Scanner(System.in);
        Long isbn;  
        do {
            System.out.println("--- Ingrese ISBN ---:\n");
            isbn = leer.nextLong();
            if (isbn == 0 || isbn==(null)) {
                System.out.println("--- ¡¡¡Ingrese un ISBN válido!!! ---\n");
            }
        } while (isbn == 0 || isbn==(null));
        return isbn;
    }

    public String ingresaTitulo() {
        Scanner leer = new Scanner(System.in);
        String titulo;
        do {
            System.out.println("--- Ingrese Titulo ---:\n");
            titulo = leer.nextLine();
            if (titulo.isEmpty()) {
                System.out.println("--- ¡¡¡Ingrese un Titulo válido!!! ---\n");
            }
        } while (titulo.isEmpty());
        return titulo;
    }

    public Integer ingresaAnio() {
        Scanner leer = new Scanner(System.in);
        Integer anio;
        do {
            System.out.println("--- Ingrese Año ---:\n");
            anio = leer.nextInt();
            if (anio <= 0 || anio==(null)) {
                System.out.println("--- ¡¡¡Ingrese un Año válido!!! ---\n");
            }
        } while (anio <= 0 || anio==(null));
        return anio;

    }

    public Integer ingresaEjemplares() {
        Scanner leer = new Scanner(System.in);
        Integer ejemplares;
        do {
            System.out.println("--- Ingrese Cantidad de Ejemplares Disponible ---:\n");
            ejemplares = leer.nextInt();
            if (ejemplares <= 0 || ejemplares==(null)) {
                System.out.println("--- ¡¡¡Ingrese una Cantidad de Ejemplares válida!!! ---\n");
            }
        } while (ejemplares <= 0 || ejemplares==(null));
        return ejemplares;
    }

    public Integer ingresaEjemplaresPrestados() {
        Scanner leer = new Scanner(System.in);
        Integer ejemplaresPrestados;
        do {
            System.out.println("--- Ingrese Cantidad de Ejemplares Prestados ---:\n");
            ejemplaresPrestados = leer.nextInt();
            if (ejemplaresPrestados <= 0 || ejemplaresPrestados==(null)) {
                System.out.println("--- ¡¡¡Ingrese una Cantidad de Ejemplares Prestados válida!!! ---\n");
            }
        } while (ejemplaresPrestados <= 0 || ejemplaresPrestados==(null));
        return ejemplaresPrestados;
    }

//valido para autor y editorial
    public Integer ingresaCodigo() {
        Scanner leer = new Scanner(System.in);
        Integer codigo;
        do {
            System.out.println(" Ingrese un Codigo:\n");
            codigo = leer.nextInt();
            if (codigo <= 0 || codigo==(null)) {
                System.out.println("--- ¡¡¡Ingrese un Código válido!!! ---\n");
            }
        } while (codigo <= 0 || codigo==(null));
        return codigo;
    }

}
