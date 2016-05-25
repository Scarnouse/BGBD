package com.boreas.modelo;

public class Juego {
	private String nombre, imagen;
	private int minimoJugadores, maximoJugadores, tiempoJuego, ranking, anyoPublicacion;
	private double rating;
	/**
	 * Costructor de la clase juego. No pude encotrar un modelo adecaudo a herencia en los datos que extraía del JSON
	 * @param nombre nombre del juego
	 * @param imagen dirección url que tiene una imagen en miniatura del juego
	 * @param minimoJugadores numero mínimo de jugadores
	 * @param maximoJugadores numero máximo de jugadores
	 * @param tiempoJuego tiempo medio estimado de partida.
	 * @param ranking ranking en la BGG (página de juegos de tablero)
	 * @param rating valoración hecha por el usuario
	 * @param anyoPublicacion año de publicación
	 */
	public Juego(String nombre, String imagen, int minimoJugadores, int maximoJugadores, int tiempoJuego, int ranking,
			double rating, int anyoPublicacion) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.minimoJugadores = minimoJugadores;
		this.maximoJugadores = maximoJugadores;
		this.tiempoJuego = tiempoJuego;
		this.ranking = ranking;
		this.rating = rating;
		this.anyoPublicacion = anyoPublicacion;
	}
	
	/**
	 * Método que devuelve el nombre de un juego
	 * @return el nombre del juego
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para modificar el nombre del juego
	 * @param nombre Cadena de texto que modificará el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método que obtiene la url donde se encuentra la imagen
	 * @return una cadena que contiene la url donde está la imagen
	 */
	public String getImagen() {
		return imagen;
	}

	//el setter de imagen lo dejo para una posible ampliación del programa que permita al usuario
	//a incluir sus propias miniaturas de los juegos. Aunque lo dejo comentdado.
	/*public void setImagen(String imagen) {
		this.imagen = imagen;
	}*/
	
	/**
	 * Método que devuele el número mínimo de jugadores que pueden jugar al juego
	 * @return un integer que representa el número mínimo de juegos-
	 */
	public int getMinimoJugadores() {
		return minimoJugadores;
	}
	
	/**
	 * Método que permite la actualización del mínimo de jugadores
	 * @param minimoJugadores integer que se intercambiará por el que tenía el juego
	 */
	
	public void setMinimoJugadores(int minimoJugadores) {
		this.minimoJugadores = minimoJugadores;
	}
	
	/**
	 * Método que permite obtener el número máximo de jugadores
	 * @return integer con el número máximos de jugadores
	 */
	
	public int getMaximoJugadores() {
		return maximoJugadores;
	}

	/**
	 * Método que permite actualizar el número máximo de jugadores
	 * @param maximoJugadores integer que se intercambiará por el valor anterior
	 */
	
	public void setMaximoJugadores(int maximoJugadores) {
		this.maximoJugadores = maximoJugadores;
	}

	/**
	 * Método que devuelve el Tiempo medio de juego
	 * @return integer que representa el tiempo medio de juego.
	 */
	
	public int getTiempoJuego() {
		return tiempoJuego;
	}
	
	/**
	 * Metodo que permite la actualización del tiempo de un juego
	 * @param tiempoJuego integer que se intercambiará por el valor del juego
	 */

	public void setTiempoJuego(int tiempoJuego) {
		this.tiempoJuego = tiempoJuego;
	}
	
	/**
	 * Método que devuelve el ranking en el que se encuentra el juego en la BGG
	 * @return integer que representa la posición del juego en la BGG
	 */

	public int getRanking() {
		return ranking;
	}
	
	/**
	 * Método que permite la actualización del ranking de un juego
	 * @param ranking integer que se cambiará por el valor que ya tenía el juego
	 */

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	/**
	 * método que permite la obtención del año de publicación del juego
	 * @return integer que representa el año de publicación del juego
	 */

	public int getAnyoPublicacion() {
		return anyoPublicacion;
	}
	
	/**
	 * Método que actualiza el año de publicación de un juego
	 * @param anyoPublicacion integer que cambiaría el valor del año del juego
	 */

	public void setAnyoPublicacion(int anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}
	
	/**
	 * Método que devuelve el rating del usuario con respecto a un juego concreto
	 * @return double que representa dicho rating
	 */

	public double getRating() {
		return rating;
	}

	/**
	 * Método que permite la actualización del campo rating de un juego
	 * @param rating double que cambiará el valor del rating del juego
	 */
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	/**
	 * Sobreescrituar del método hashCode() 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anyoPublicacion;
		result = prime * result + maximoJugadores;
		result = prime * result + minimoJugadores;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + tiempoJuego;
		return result;
	}
	
	/**
	 * sobreescritura del metodo equals()
	 * Un juego será igual si tiene el mismo nombre, el mismo año, el mismo número máximo y mínimo de jugadores
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Juego other = (Juego) obj;
		if (anyoPublicacion != other.anyoPublicacion)
			return false;
		if (maximoJugadores != other.maximoJugadores)
			return false;
		if (minimoJugadores != other.minimoJugadores)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tiempoJuego != other.tiempoJuego)
			return false;
		return true;
	}

	
	//Este método toString fue usado al principio para poder ver que leía correctamente los datos del JSON
	//No está en uso.
	@Override
	public String toString() {
		return "Juego [nombre=" + nombre + ", imagen=" + imagen + ", identificador=" 
				+ ", minimoJugadores=" + minimoJugadores + ", maximoJugadores=" + maximoJugadores + ", tiempoJuego="
				+ tiempoJuego + ", ranking=" + ranking + ", rating=" + rating + ", anyoPublicacion=" + anyoPublicacion
				+ "]";
	};
	
	
	
}
