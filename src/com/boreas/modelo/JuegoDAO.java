package com.boreas.modelo;

import java.util.List;

/**
 * 
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */

public interface JuegoDAO {
	
	/**
	 * Método que permite la realización de INSERTS en la Base de Datos
	 * @param juego
	 */
	public void insertarJuego(Juego juego);
	
	/**
	 * Método que permite la modificación de un juego a través de UPDATE
	 * @param juego Juego que va a ser modificado
	 * @param indice Indice del juego que va a ser modificado
	 * @return
	 */
	
	public int actualizarJuego(Juego juego,int indice);
	
	/**
	 * Método que permite el borrado de juegos de la tabla mediante DELETE
	 * @param nombre Nombre del juego que va a ser borrado
	 * @return devuelve el número de elementos borrados
	 */
	
	public int borrarJuego(String nombre);
	
	/**
	 * Método que realiza una consulta SELECT sobre todos los elementos de la tabla
	 * @return
	 */
	
	public List<Juego> leerTodosJuegos();
	
	/**
	 * Método que ofrece el ID del juego a través de una consulta SELECT
	 * @param juego
	 * @return
	 */
	
	public int obtenerID(Juego juego);
	
	/**
	 * Método realiza una consulta SELECT dandole un id como parámetro
	 * @param id
	 * @return
	 */
	
	public Juego obtenerJuego(int id);

}
