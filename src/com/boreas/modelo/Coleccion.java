package com.boreas.modelo;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que agrupa los juegos en una lista
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */

public class Coleccion {
	
	private static List<Juego> lista = new ArrayList<Juego>();
	/**
	 * Método para añadir juegos a la lista
	 * @param juego Juego a añadir
	 * @return true si se ha añadido el juego correctamente
	 */
	public boolean addLista(Juego juego){
		return lista.add(juego);
	}
	
	/**
	 * Método que permite la obtención de la lista de juegos
	 * @return lista de juegos cargada en memoria
	 */
	
	public static List<Juego> getLista() {
		return lista;
	}
	
}
