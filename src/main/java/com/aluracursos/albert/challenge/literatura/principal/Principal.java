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
                    
                    Favor digite al opción
                    
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
            }
        }while( opcion != 6 );

    }

    private void buscarLibroPorTitulo()
        throws IOException, InterruptedException{

        System.out.print( "   Escriba el título del libro a buscar\n > " );

        var tituloLibro = leer.nextLine();

        var datosLibroSolicitado = new TraerDatosApi();

        var librosEncontrados = datosLibroSolicitado.solicitudRespuesta( tituloLibro );

        var primerLibroEncontrado = librosEncontrados.libroList().stream().findFirst();

        if( primerLibroEncontrado.isPresent() ){
            var libroEncontrado = primerLibroEncontrado.get();
            System.out.println( "El libro buscado es:\nTitulo: " + libroEncontrado.titulo() );

            var libro = new LibroDataBase( primerLibroEncontrado );

            repositorio.save( libro );

            List<LibroDataBase> libros = repositorio.findAll();

            libros.stream()
                .forEach( System.out::println );
        }
        else{
            System.out.println( "Libro no encontrado" );
        }
    }
}


