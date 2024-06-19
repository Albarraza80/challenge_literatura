package com.aluracursos.albert.challenge.literatura.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public record LibroEncontrado(
    @SerializedName( "results" ) ArrayList<Libro> libroList
){
}
