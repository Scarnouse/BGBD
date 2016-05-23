package com.boreas.modelo;

import java.util.List;

/**
 * 
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */

public interface JuegoDAO {
	
	//métodos que serán sobreescritos <-- Es una interfaz
	
	public void insertarJuego(Juego juego);
	public int actualizarJuego(Juego juego,int indice);
	public int borrarJuego(String nombre);
	public List<Juego> leerTodosJuegos();
	public int obtenerID(Juego juego);

}
