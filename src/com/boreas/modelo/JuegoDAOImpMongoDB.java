package com.boreas.modelo;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class JuegoDAOImpMongoDB implements JuegoDAO{
	
	private MongoClient conexion = new MongoClient();
	private MongoDatabase db = conexion.getDatabase("test");
	private MongoCollection<Document> coleccion = db.getCollection("juego");
	private static Coleccion lista = new Coleccion();

	@Override
	public void insertarJuego(Juego juego) {
		Document doc = new Document("ident", lista.getLista().size())
				.append("nombre", juego.getNombre())
				.append("imagen", juego.getImagen())
				.append("minimo_jugadores", juego.getMinimoJugadores())
				.append("maximo_jugadores", juego.getMaximoJugadores())
				.append("tiempo_juego", juego.getTiempoJuego())
				.append("ranking", juego.getRanking())
				.append("rating", juego.getRating())
				.append("anyo_publicacion", juego.getAnyoPublicacion());
		coleccion.insertOne(doc);
	}

	@Override
	public int actualizarJuego(Juego juego, int indice) {
		
		MongoCursor<Document> cursor = coleccion.find().iterator();
		try {
			while (cursor.hasNext()){
				//System.out.println(cursor.next().toJson());
				Document doc = cursor.next();
				if (doc.get("ident").equals(indice)){
					coleccion.findOneAndReplace(doc,new Document("ident", indice)
							.append("nombre", juego.getNombre())
							.append("imagen", juego.getImagen())
							.append("minimo_jugadores", juego.getMinimoJugadores())
							.append("maximo_jugadores", juego.getMaximoJugadores())
							.append("tiempo_juego", juego.getTiempoJuego())
							.append("ranking", juego.getRanking())
							.append("rating", juego.getRating())
							.append("anyo_publicacion", juego.getAnyoPublicacion()));
				}
			}
		} finally {
			cursor.close();
		}
		
		
		/**coleccion.updateOne(new Document("ident", indice),new Document("nombre", juego.getNombre())
				.append("imagen", juego.getImagen())
				.append("minimo_jugadores", juego.getMinimoJugadores())
				.append("maximo_jugadores", juego.getMaximoJugadores())
				.append("tiempo_juego", juego.getTiempoJuego())
				.append("ranking", juego.getRanking())
				.append("rating", juego.getRating())
				.append("anyo_publicacion", juego.getAnyoPublicacion()));*/
		return 0;
	}

	@Override
	public int borrarJuego(String nombre) {
		MongoCursor<Document> cursor = coleccion.find().iterator();
		try {
			while (cursor.hasNext()){
				//System.out.println(cursor.next().toJson());
				Document doc = cursor.next();
				if (doc.get("nombre").equals(nombre)){
					coleccion.deleteOne(doc);
				}
			}
		} finally {
			cursor.close();
		}
		return 0;
	}

	@Override
	public List<Juego> leerTodosJuegos() {
		Coleccion.getLista().clear();
		MongoCursor<Document> cursor = coleccion.find().iterator();
		try {
			while (cursor.hasNext()){
				//System.out.println(cursor.next().toJson());
				Document doc = cursor.next();
				try {
					lista.addLista(new Juego(doc.getString("nombre"), doc.getString("imagen"), doc.getInteger("minimo_jugadores"), doc.getInteger("maximo_jugadores"), doc.getInteger("tiempo_juego"), doc.getInteger("ranking"), doc.getDouble("rating"), doc.getInteger("anyo_publicacion")));
				} catch (JuegoIlegalException e) {
					e.printStackTrace();
				}
			}
		} finally {
			cursor.close();
		}
		return Coleccion.getLista();
	}

	@Override
	public int obtenerID(Juego juego) {
		int identificador = -1;
		MongoCursor<Document> cursor = coleccion.find().iterator();
		try {
			while (cursor.hasNext()){
				Document doc = cursor.next();
				if (doc.get("nombre").equals(juego.getNombre())){
					identificador = Integer.parseInt(doc.get("ident").toString());
				}
			}
		} finally {
			cursor.close();
		}
		return identificador;
	}

	@Override
	public Juego obtenerJuego(int id) {
		MongoCursor<Document> cursor = coleccion.find().iterator();
		Juego juego = null; 
		try {
			while (cursor.hasNext()){
				Document doc = cursor.next();
				if (doc.get("ident").equals(id)){
					try {
						juego = new Juego(doc.getString("nombre"), doc.getString("imagen"), doc.getInteger("minimo_jugadores"), doc.getInteger("maximo_jugadores"), doc.getInteger("tiempo_juego"), doc.getInteger("ranking"), doc.getDouble("rating"), doc.getInteger("anyo_publicacion"));
					} catch (JuegoIlegalException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			cursor.close();
		}
		return juego;
	}

}
