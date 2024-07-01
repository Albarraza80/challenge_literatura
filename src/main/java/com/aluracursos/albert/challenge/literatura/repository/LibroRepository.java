package com.aluracursos.albert.challenge.literatura.repository;

import com.aluracursos.albert.challenge.literatura.modelo.LibroDataBase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibroRepository extends CrudRepository<LibroDataBase, Long>{

    LibroDataBase findByTitulo( String titulo );

    List<LibroDataBase> findByIdiomasContaining( String idiomas );
}


