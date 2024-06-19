package com.aluracursos.albert.challenge.literatura.principal;

import com.aluracursos.albert.challenge.literatura.modelo.Libro;
import com.aluracursos.albert.challenge.literatura.modelo.LibroDataBase;
import com.aluracursos.albert.challenge.literatura.repository.LibroRepository;
import com.aluracursos.albert.challenge.literatura.servicio.TraerDatosApi;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal{
    private Scanner leer = new Scanner( System.in );
    //private Scanner lectura = new Scanner( System.in );

    private LibroRepository repositorio;

    public Principal( LibroRepository repository ){
        this.repositorio = repository;
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
                    
                    1. Buscar libro por títilo
                    2. Mostrar libros registrados
                    3. Mostrar autores regisrados
                    4. Mostrar autores vivos por año
                    5. Mostrar libros por idioma
                    6. SALIR         
                """;
            System.out.println( menu );
            System.out.print( "    > " );
            opcion = leer.nextInt();

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
                    System.out.println( "    **************************" );
                    System.out.println( "    ***  PROGRAMA CERRADO  ***" );
                    System.out.println( "    **************************" );
                    break;
                default:
                    System.out.println( "" );
                    System.out.println( "    Elija una opción válida" );
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
        var librosDataBase = new LibroDataBase();
        System.out.println(librosDataBase);
    }

    private void buscarLibroPorTitulo()
        throws IOException, InterruptedException{

        Scanner lectura = new Scanner( System.in );
        String tituloLibro = "";

        System.out.print( "    Escriba el título del libro a buscar\n    > " );

        tituloLibro = lectura.nextLine();

        var datosLibroSolicitado = new TraerDatosApi();

        var librosEncontrados = datosLibroSolicitado.solicitudRespuesta( tituloLibro );

        var primerLibroEncontrado = librosEncontrados.libroList().stream().findFirst();

        if( primerLibroEncontrado.isPresent() ){
            var libroEncontrado = primerLibroEncontrado.get();
            System.out.println( "\n    El libro buscado es:\n  Titulo: " + libroEncontrado.titulo() );

            var libro = new LibroDataBase( primerLibroEncontrado );

            repositorio.save( libro );

//            List<LibroDataBase> libros = repositorio.findAll();
//            libros.stream()
//               .forEach( System.out::println );
        }
        else{
            System.out.println( "\n    Libro no encontrado" );
        }
    }
}


