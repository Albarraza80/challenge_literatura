package com.aluracursos.albert.challenge.literatura.servicio;

import com.aluracursos.albert.challenge.literatura.modelo.LibroEncontrado;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TraerDatosApi{
    private String urlGutendex;

    public LibroEncontrado solicitudRespuesta( String libroBuscado )
        throws IOException, InterruptedException{
        String direccion;
        HttpClient cliente;
        HttpRequest solicitud;
        HttpResponse<String> respuesta;

        var libroBuscadoCorregido = libroBuscado.replace( " ", "%20" );

        direccion = "https://gutendex.com/books/?search=" + libroBuscadoCorregido;

        cliente = HttpClient.newHttpClient();
        solicitud = HttpRequest.newBuilder()
            .uri( URI.create( direccion ) )
            .build();
        cliente.sendAsync( solicitud, HttpResponse.BodyHandlers.ofString() )
            .thenApply( HttpResponse::body )
            /*.thenAccept( System.out::println )*/
            .join();

        respuesta = cliente
            .send( solicitud, HttpResponse.BodyHandlers.ofString() );

        var conversion = new Gson().fromJson( respuesta.body(), LibroEncontrado.class );

        return conversion;

    }
}
