package com.boreas.modelo;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */
public class List2Array {
	
	/**
	 * Este método permite la carga de un array bidimensional para crear la tabla
	 * Mi decisión es que la tabla no mostrase todos los datos al usuario para hacer funcional
	 * el fomulario.
	 * @param lista
	 * @return array que contiene los campos nombre, ranking y rating de cada juego
	 */
	public static String[][] getListaReducida(List<Juego> lista){
		String[][] array = new String[lista.size()][lista.size()*3];
		List<String> cadenas = new ArrayList<String>();
		for (int i=0; i<lista.size(); i++){
			Juego juego = (Juego) lista.get(i);
			for (int j=0; j<3; j++){
				switch (j){
				case 0:
					cadenas.add(juego.getNombre());
					break;
				case 1:
					cadenas.add(juego.getRanking()+"");
					break;
				case 2:
					cadenas.add(juego.getRating()+"");
					break;
				}
				array[i][j] = cadenas.get(j);
			}
			cadenas.clear();
		}
		
		return array;

	}
	
}
