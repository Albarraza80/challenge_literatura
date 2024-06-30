package com.aluracursos.albert.challenge.literatura.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "autores" )
public class AutorDataBase {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nombre;

    private Integer nacimiento;

    private Integer fallecimiento;

    @ManyToOne
    private LibroDataBase libroDataBase;

    public AutorDataBase(){
    }

    public AutorDataBase( Autor autor, LibroDataBase libroDataBase ){
        this.nombre = autor.nombre();
        this.nacimiento = autor.nacimiento();
        this.fallecimiento = autor.fallecimiento();
        this.libroDataBase = libroDataBase;
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

    public LibroDataBase getLibroDataBase(){
        return libroDataBase;
    }

    public void setLibroDataBase( LibroDataBase libroDataBase ){
        this.libroDataBase = libroDataBase;
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
}


