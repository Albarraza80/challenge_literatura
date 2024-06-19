package com.aluracursos.albert.challenge.literatura.modelo;

import com.google.gson.annotations.SerializedName;

public record Autor(
    @SerializedName( "name" ) String nombre,
    @SerializedName( "birth_year" ) Integer nacimiento,
    @SerializedName( "death_year" ) Integer fallecimiento
){
}
