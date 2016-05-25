package com.boreas.vista;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.boreas.modelo.Juego;
import com.boreas.modelo.List2Array;
/**
 * 
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */

public class TablaModelo extends AbstractTableModel{
	
	private static final String[] CABECERA = {"Nombre","Ranking","Rating"};
	private String[][] array;
	/**
	 * Constructor de la clase
	 * @param lista Lista de la que va a obtener los datos para posteriormente mostrarlos en la tabla
	 */
	public TablaModelo(List<Juego> lista) {
		 array = List2Array.getListaReducida(lista);
	}
	
	//Devuelve el número de filas de la Tabla
	@Override
	public int getRowCount() {
		return array.length;
	}
	
	//Devuelve el número de columnas de la Tabla
	@Override
	public int getColumnCount() {
		return 3;
	}
	
	//Obtine el valor que corresponde a cada celda
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return array[rowIndex][columnIndex];
	}
	//Permite la inserción de una cabecera en la tabla
	@Override
	public String getColumnName(int column) {
		return CABECERA[column];
	}
	
}
