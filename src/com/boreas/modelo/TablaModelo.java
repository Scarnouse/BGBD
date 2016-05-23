package com.boreas.modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;
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

	@Override
	public int getRowCount() {
		return array.length;
	}
	@Override
	public int getColumnCount() {
		return 3;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return array[rowIndex][columnIndex];
	}
	//La sobreescritura de este método permite la inserción de una cabecera en la tabla
	@Override
	public String getColumnName(int column) {
		return CABECERA[column];
	}
	
}
