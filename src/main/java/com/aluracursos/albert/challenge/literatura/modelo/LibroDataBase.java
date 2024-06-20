package com.aluracursos.albert.challenge.literatura.modelo;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table( name = "libros")
public class LibroDataBase{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column( unique = true )
    private String titulo;

    @Transient
    private List<Autor> autorList;

    @Transient
    private List<String> idiomas;

    private Integer descargas;

    public LibroDataBase( Optional<Libro> libro){
        this.descargas = libro.get().descargas();
        this.titulo = libro.get().titulo();
    }

    public LibroDataBase(){
    }

    public Long getId(){
        return Id;
    }

    public void setId( Long id ){
        Id = id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo( String titulo ){
        this.titulo = titulo;
    }

    public List<Autor> getAutorList(){
        return autorList;
    }

    public void setAutorList( List<Autor> autorList ){
        this.autorList = autorList;
    }

    public List<String> getIdiomas(){
        return idiomas;
    }

    public void setIdiomas( List<String> idiomas ){
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
        return "\n\n\t****** LIBRO ******\n" +
            "\tTitulo: " + titulo + "\n" +
            "\tNúmero de descargas: " + descargas + "\n" +
            "\t*******************";
    }
}
