package com.aluracursos.albert.challenge.literatura.repository;

import com.aluracursos.albert.challenge.literatura.modelo.LibroDataBase;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<LibroDataBase, Long>{

    LibroDataBase findByTitulo( String titulo );

}

