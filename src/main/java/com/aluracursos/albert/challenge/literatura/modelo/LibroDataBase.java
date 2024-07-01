package com.aluracursos.albert.challenge.literatura.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "libros" )
public class LibroDataBase{

    private static final String SEPARADOR = ",";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( unique = true, length = 1000 )
    private String titulo;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable( name = "libro_autor",
        joinColumns = @JoinColumn( name = "libro_id" ),
        inverseJoinColumns = @JoinColumn( name = "autor_id" )
    )
    private List<AutorDataBase> autorList;

    private String idiomas;

    private Integer descargas;

    public LibroDataBase(){
    }

    public LibroDataBase( Libro libro ){
        this.descargas = libro.descargas();
        this.titulo = libro.titulo();
        this.idiomas = transformarListadoIdiomasAString( libro.idiomasList() );
        this.autorList = new ArrayList<>();
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
            "Titulo: " + titulo + "\n" +
            "Número de descargas: " + descargas + "\n" +
            "*******************";
    }

    public StringBuilder imprimirInfoLibro(){
        var infoLibro = new StringBuilder();

        infoLibro.append( "\n\n****** LIBRO ******\nTitulo: " ).append( titulo ).append( "\nAutor(es): " );

        for( AutorDataBase autorDb : this.autorList ){
            infoLibro.append( autorDb ).append( ".\n" );
        }

        infoLibro.append(
            "\nNúmero de descargas: " ).append( descargas ).append( "\n*******************" );

        return infoLibro;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( o == null || getClass() != o.getClass() ){
            return false;
        }
        LibroDataBase that = ( LibroDataBase ) o;
        return Objects.equals( titulo, that.titulo );
    }

    @Override
    public int hashCode(){
        return Objects.hashCode( titulo );
    }
}
