package com.boreas.modelo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.boreas.vista.VistaPrincipal;
import com.google.gson.stream.JsonReader;
/**
 * Clase de lectura del fichero
 * @author Manuel Quesada Segura
 *
 */
public class LecturaFichero {
	
	private Coleccion coleccion = new Coleccion();


	/**
	 * Método que permite la lectura del fichero JSON y su conservación en una lista dinámica.
	 * @param file Fichero del que se va a extraer la información.
	 */
	public void leerFichero(File file){
		
		try (JsonReader jReader = new JsonReader(new FileReader(file));){
			
			jReader.beginArray();
			int minimoJugadores = 0, maximoJugadores = 0, tiempoJuego = 0, ranking = 0, anyoPublicacion = 0;
			double rating = 0;
			String nombre = "", imagen = "";
			while (jReader.hasNext()){
				jReader.beginObject();
				if (jReader.nextName().equals("gameId")) jReader.skipValue();
				//El campo nombre contenía apóstrofes de ahí que haya tenido que reemplazarlos por espacios en blanco
				//El problema residía que si un archivo contenía un apóstrofe la sentencia SQL se veía afectada a la
				//hora de realizar INSERTS.
				if (jReader.nextName().equals("name")) nombre = jReader.nextString().replace("'","");
				if (jReader.nextName().equals("image")) jReader.skipValue();
				if (jReader.nextName().equals("thumbnail")) imagen = jReader.nextString();
				if (jReader.nextName().equals("minPlayers")) minimoJugadores = jReader.nextInt();
				if (jReader.nextName().equals("maxPlayers")) maximoJugadores = jReader.nextInt();
				if (jReader.nextName().equals("playingTime")) tiempoJuego = jReader.nextInt();
				if (jReader.nextName().equals("isExpansion")) jReader.skipValue();
				if (jReader.nextName().equals("yearPublished")) anyoPublicacion = jReader.nextInt();
				if (jReader.nextName().equals("bggRating")) jReader.skipValue();
				if (jReader.nextName().equals("averageRating")) jReader.skipValue();
				if (jReader.nextName().equals("rank")) ranking = jReader.nextInt();
				if (jReader.nextName().equals("numPlays")) jReader.skipValue();
				if (jReader.nextName().equals("rating")) rating = jReader.nextDouble();
				if (jReader.nextName().equals("owned")) jReader.skipValue();
				if (jReader.nextName().equals("preOrdered")) jReader.skipValue();
				if (jReader.nextName().equals("forTrade")) jReader.skipValue();
				if (jReader.nextName().equals("previousOwned")) jReader.skipValue();
				if (jReader.nextName().equals("want")) jReader.skipValue();
				if (jReader.nextName().equals("wantToPlay")) jReader.skipValue();
				if (jReader.nextName().equals("wantToBuy")) jReader.skipValue();
				if (jReader.nextName().equals("wishList")) jReader.skipValue();
				if (jReader.nextName().equals("userComment")) jReader.skipValue();
				
				coleccion.addLista(new Juego(nombre, imagen, minimoJugadores, maximoJugadores, tiempoJuego, ranking, rating, anyoPublicacion));
				jReader.endObject();
			}
			jReader.endArray();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(VistaPrincipal.getFrame(), "Ha introducido un archivo erróneo");
		} catch (JuegoIlegalException e) {
			System.err.println("El juego no ha podido ser creado");
		}
		
		//Este bucle me permitía ver si la carga desde el JSON se había hecho correctamente.
		/*for (Juego juego : coleccion.getLista()) {
			System.out.println(juego);
		}*/
		
	}
	
}



