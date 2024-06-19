package com.aluracursos.albert.challenge.literatura.repository;

import com.aluracursos.albert.challenge.literatura.modelo.LibroDataBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<LibroDataBase, Long>{
}
