package com.aluracursos.albert.challenge.literatura.principal;

import com.aluracursos.albert.challenge.literatura.modelo.Autor;
import com.aluracursos.albert.challenge.literatura.modelo.AutorDataBase;
import com.aluracursos.albert.challenge.literatura.modelo.LibroDataBase;
import com.aluracursos.albert.challenge.literatura.repository.AutorRepository;
import com.aluracursos.albert.challenge.literatura.repository.LibroRepository;
import com.aluracursos.albert.challenge.literatura.servicio.TraerDatosApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class Principal{
    private final Scanner leer = new Scanner( System.in );

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public Principal( LibroRepository libroRepository, AutorRepository autorRepository ){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
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
                 \n
                 \s
                 ***************************
                 *****   LITERATURA    *****
                 ***************************
                \s
                 Favor digite una opción
                \s
                 1. Buscar libro por título
                 2. Mostrar libros registrados
                 3. Mostrar autores registrados
                 4. Mostrar autores vivos por año
                 5. Mostrar libros por idioma
                 6. SALIR
                \s""";

            System.out.println( menu );
            System.out.print( "> " );

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
                    mostrarAutoresPorAno();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println( "**************************" );
                    System.out.println( "***  PROGRAMA CERRADO  ***" );
                    System.out.println( "**************************" );
                    break;
                default:
                    System.out.println();
                    System.out.println( "\tElija una opción válida" );
                    System.out.println();
                    break;
            }

        }while( opcion != 6 );

    }

    private void mostrarLibrosPorIdioma(){
    }

    private void mostrarAutoresPorAno(){
        System.out.print( "Digite el año que desea consultar: \n> " );

        var valorDigitado = leer.nextLine();
        var aho = Integer.parseInt( valorDigitado );

        var autores = this.autorRepository.findAll();

        var autorEncontrado = false;

        for( AutorDataBase autorDataBase : autores ){
            var nacimimento = autorDataBase.getNacimiento();
            var fallecimiento = autorDataBase.getFallecimiento();

            if( nacimimento != null && nacimimento <= aho && ( fallecimiento == null || fallecimiento > aho ) ){
                System.out.println( autorDataBase );
                autorEncontrado = true;
            }
        }

        if( !autorEncontrado ){
            System.out.println( "No se encontró autor vivo para el año: " + aho );
        }
    }

    private void mostrarAutoresRegistrados(){
        var autores = this.autorRepository.findAll();

        autores.forEach( System.out::println );

    }

    private void mostrarLibrosRegistrados(){
        var libros = this.libroRepository.findAll();

        libros.forEach( System.out::println );
    }

    private void buscarLibroPorTitulo()
        throws IOException, InterruptedException{

        System.out.print( "\tEscriba el título del libro a buscar\n\t> " );

        var tituloLibro = this.leer.nextLine();

        var datosLibroSolicitado = new TraerDatosApi();

        var librosEncontrados = datosLibroSolicitado.solicitudRespuesta( tituloLibro );

        var primerLibro = librosEncontrados.libroList().stream().findFirst();

        if( primerLibro.isPresent() ){
            var libro = primerLibro.get();

            var titulo = libro.titulo();

            System.out.println( "\n\t****************" +
                "\n\tEl libro buscado es:\n\t" +
                "Titulo: " + titulo +
                "\n\tAutor: " + libro.autoresList().get( 0 ) +
                "\n\t****************" );

            var libroDataBase = this.libroRepository.findByTitulo( titulo );

            if( libroDataBase == null ){
                libroDataBase = new LibroDataBase( libro );

                for( Autor autor : libro.autoresList() ){
                    var autorDataBases = this.autorRepository.findByNombre( autor.nombre() );

                    if( autorDataBases == null ){
                        autorDataBases = new AutorDataBase( autor );
                    }

                    libroDataBase.addAutorDataBase( autorDataBases );
                }

                libroRepository.save( libroDataBase );
            }
            else{
                System.out.println( "El Libro ya se encontraba en la base de datos" );
            }

        }
        else{
            System.out.println( "\n\tLibro no encontrado" );
        }
    }
}


