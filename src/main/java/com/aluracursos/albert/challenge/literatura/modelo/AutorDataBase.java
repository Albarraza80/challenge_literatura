package com.aluracursos.albert.challenge.literatura.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "autores" )
public class AutorDataBase{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nombre;

    private Integer nacimiento;

    private Integer fallecimiento;

    @ManyToMany( mappedBy = "autorList" )
    private List<LibroDataBase> librosList;

    public AutorDataBase(){
    }

    public AutorDataBase( Autor autor ){
        this.nombre = autor.nombre();
        this.nacimiento = autor.nacimiento();
        this.fallecimiento = autor.fallecimiento();
        this.librosList = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre( String nombre ){
        this.nombre = nombre;
    }

    public Integer getNacimiento(){
        return nacimiento;
    }

    public void setNacimiento( Integer nacimiento ){
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento(){
        return fallecimiento;
    }

    public void setFallecimiento( Integer fallecimiento ){
        this.fallecimiento = fallecimiento;
    }

    public List<LibroDataBase> getLibrosList(){
        return librosList;
    }

    public void setLibrosList( List<LibroDataBase> librosList ){
        this.librosList = librosList;
    }

    public Long getId(){
        return id;
    }

    public void setId( Long id ){
        this.id = id;
    }

    @Override
    public String toString(){
        return "\nNombre: " + nombre +
            "\nFecha de nacimiento: " + nacimiento +
            "\nFecha de fallecimiento: " + fallecimiento;
    }

    private StringBuilder imprimirLibros(){
        var infoLibros = new StringBuilder();

        infoLibros.append( "Libro(s): " );

        for( LibroDataBase libroDataBase : this.librosList ){
            infoLibros.append( libroDataBase.toString() ).append( ".\n" );
        }

        return infoLibros;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( o == null || getClass() != o.getClass() ){
            return false;
        }
        AutorDataBase that = ( AutorDataBase ) o;
        return Objects.equals( nombre, that.nombre );
    }

    @Override
    public int hashCode(){
        return Objects.hashCode( nombre );
    }
}


