package com.aluracursos.albert.challenge.literatura.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public record Libro(
    @SerializedName( "title" ) String titulo,
    @SerializedName( "authors" ) ArrayList<Autor> autoresList,
    @SerializedName( "languages" ) ArrayList<String> idiomasList,
    @SerializedName( "download_count" ) Integer descargas
){
}
