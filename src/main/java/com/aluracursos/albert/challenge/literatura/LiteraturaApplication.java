package com.aluracursos.albert.challenge.literatura;

import com.aluracursos.albert.challenge.literatura.principal.Principal;
import com.aluracursos.albert.challenge.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

import java.lang.annotation.*;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner{

	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run( String... args )
		throws Exception{
		Principal principal = new Principal(repository);
		principal.muestraMenu();
	}

}
