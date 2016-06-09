package com.boreas.modelo;

import java.util.List;

import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ColeccionMongoDB {
		
	public static void crearColeccion(MongoClient conexion, List<Juego> lista){
		
		MongoDatabase db = conexion.getDatabase("test");
		MongoCollection<Document> coleccion = db.getCollection("juego");
		int indice = 0;
		
		Document doc; 
		for(Juego juego:lista){
			doc = new Document("ident", indice)
				.append("nombre", juego.getNombre())
				.append("imagen", juego.getImagen())
				.append("minimo_jugadores", juego.getMinimoJugadores())
				.append("maximo_jugadores", juego.getMaximoJugadores())
				.append("tiempo_juego", juego.getTiempoJuego())
				.append("ranking", juego.getRanking())
				.append("rating", juego.getRating())
				.append("anyo_publicacion", juego.getAnyoPublicacion());
			coleccion.insertOne(doc);
			indice ++;
		}	
		
	}
	
	public static long comprobarColeccion(MongoClient conexion){
		
		MongoDatabase db = conexion.getDatabase("test");
		MongoCollection<Document> coleccion = db.getCollection("juego");
		
		return coleccion.count();
	}
		
}
