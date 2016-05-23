package com.boreas.modelo;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Esta clase incluye la extensión a los ficheros pdf
 * Puede ser reutilizada para incluir extensiones a otros ficheros.
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */
public class Extension {
	/**
	 * Método para incluir la extensión
	 * @param jF JFileChooser que va a permitir el guardado del fichero
	 * @return el fichero con la extensión
	 */
	public static File obtenerExtension(JFileChooser jF){
		File archivo = jF.getSelectedFile();
		if (jF.getFileFilter() instanceof FileNameExtensionFilter){
			String[] extension = ((FileNameExtensionFilter)jF.getFileFilter()).getExtensions();
			String nombreMinusculas = archivo.getName().toLowerCase();
			for (String ext : extension) {
				if (nombreMinusculas.endsWith('.'+ ext.toLowerCase())){
					return archivo;
				}
			}
			archivo = new File(archivo.toString()+'.'+extension[0].toString());
		}
		return archivo;
	}
}
