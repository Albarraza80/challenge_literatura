package com.aluracursos.albert.challenge.literatura.repository;

import com.aluracursos.albert.challenge.literatura.modelo.AutorDataBase;
import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends CrudRepository<AutorDataBase, Long>{
    AutorDataBase findByNombre( String nombre );
}
