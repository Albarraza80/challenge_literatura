package com.aluracursos.albert.challenge.literatura.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "libros" )
public class LibroDataBase{

    private static final String SEPARADOR = ",";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( unique = true )
    private String titulo;

    @OneToMany( mappedBy = "libroDataBase", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<AutorDataBase> autorList;

    private String idiomas;

    private Integer descargas;

    public LibroDataBase( Libro libro ){
        this.descargas = libro.descargas();
        this.titulo = libro.titulo();
        this.idiomas = transformarListadoIdiomasAString( libro.idiomasList() );
        this.autorList = new ArrayList<>();

        for( Autor escritor : libro.autoresList() ){
            var autorDataBase = new AutorDataBase( escritor, this );

            this.autorList.add( autorDataBase );
        }
    }

    private String transformarListadoIdiomasAString( List<String> idiomaList ){
        var longitud = idiomaList.size();
        var resultado = new StringBuilder();

        for( int i = 0; i < longitud; i++ ){
            resultado.append( idiomaList.get( i ) );

            if( i < longitud - 1 ){
                resultado.append( SEPARADOR );
            }
        }

        return resultado.toString();
    }

    public LibroDataBase(){
    }

    public Long getId(){
        return id;
    }

    public void setId( Long id ){
        this.id = id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo( String titulo ){
        this.titulo = titulo;
    }

    public List<AutorDataBase> getAutorList(){
        return autorList;
    }

    public void setAutorList( List<AutorDataBase> autorList ){
        this.autorList = autorList;
    }

    public String getIdiomas(){
        return idiomas;
    }

    public void setIdiomas( String idiomas ){
        this.idiomas = idiomas;
    }

    public Integer getDescargas(){
        return descargas;
    }

    public void setDescargas( Integer descargas ){
        this.descargas = descargas;
    }

    @Override
    public String toString(){
        return "\n\n****** LIBRO ******\n" +
            "Titulo: " + titulo + "\n\n" +
            imprimirAutores() + "\n" +
            "Número de descargas: " + descargas + "\n" +
            "*******************";
    }

    private String imprimirAutores(){
        String infoAutores;

        infoAutores = "Autor(es): ";

        for( AutorDataBase autorDb : this.autorList ){
            infoAutores += autorDb.toString() + ".\n";
        }

        return infoAutores;
    }

}
