package com.aluracursos.albert.challenge.literatura.repository;

import com.aluracursos.albert.challenge.literatura.modelo.AutorDataBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutorRepository extends CrudRepository<AutorDataBase, Long>{
    AutorDataBase findByNombre( String nombre );

    @Query( "SELECT aut " +
        "FROM AutorDataBase aut " +
        "WHERE aut.nacimiento <= ?1 AND (aut.fallecimiento IS NULL OR aut.fallecimiento > ?1)" )
    List<AutorDataBase> findByAutorVivo( Integer aho );
}
