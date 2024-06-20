package com.aluracursos.albert.challenge.literatura.principal;

import com.aluracursos.albert.challenge.literatura.modelo.Libro;
import com.aluracursos.albert.challenge.literatura.modelo.LibroDataBase;
import com.aluracursos.albert.challenge.literatura.repository.LibroRepository;
import com.aluracursos.albert.challenge.literatura.servicio.TraerDatosApi;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal{
    private Scanner leer = new Scanner( System.in );

    private LibroRepository repositorio;

    public Principal( LibroRepository repository ){
        this.repositorio = repository;
    }

    private Integer leerEntero(){
        int opcion;

        try{
            opcion = Integer.parseInt( leer.nextLine() );
        }
        catch( NumberFormatException exe ){
            opcion = -1;
        }

        return opcion;
    }

    public void muestraMenu()
        throws IOException, InterruptedException{
        Integer opcion;

        do{
            var menu = """
                    
                    
                    ***************************
                    *****   LITERATURA    *****
                    ***************************
                    
                    Favor digite una opción
                    
                    1. Buscar libro por título
                    2. Mostrar libros registrados
                    3. Mostrar autores regisrados
                    4. Mostrar autores vivos por año
                    5. Mostrar libros por idioma
                    6. SALIR         
                """;
            System.out.println( menu );
            System.out.print( "    > " );

            opcion = leerEntero();

            switch( opcion ){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAotiresVivosPorAno();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println( "\t**************************" );
                    System.out.println( "\t***  PROGRAMA CERRADO  ***" );
                    System.out.println( "\t**************************" );
                    break;
                default:
                    System.out.println( "" );
                    System.out.println( "\tElija una opción válida" );
                    System.out.println( "" );
                    break;
            }

        }while( opcion != 6 );

    }

    private void mostrarLibrosPorIdioma(){
    }

    private void mostrarAotiresVivosPorAno(){
    }

    private void mostrarAutoresRegistrados(){
    }

    private void mostrarLibrosRegistrados(){
        List<LibroDataBase> libros = repositorio.findAll();

        libros.stream()
            .forEach( System.out::println );
    }

    private void buscarLibroPorTitulo()
        throws IOException, InterruptedException{

        System.out.print( "\tEscriba el título del libro a buscar\n\t> " );

        var tituloLibro = this.leer.nextLine();

        var datosLibroSolicitado = new TraerDatosApi();

        var librosEncontrados = datosLibroSolicitado.solicitudRespuesta( tituloLibro );

        var primerLibroEncontrado = librosEncontrados.libroList().stream().findFirst();

        if( primerLibroEncontrado.isPresent() ){
            var libroEncontrado = primerLibroEncontrado.get();
            System.out.println( "\n\t****************" +
                                "\n\tEl libro buscado es:\n\t" +
                                "Titulo: " + libroEncontrado.titulo() +
                                "\n\t****************");

            var libro = new LibroDataBase( primerLibroEncontrado );

            repositorio.save( libro );
        }
        else{
            System.out.println( "\n\tLibro no encontrado" );
        }
    }
}


