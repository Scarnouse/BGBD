package com.boreas.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Clase que elabora los pdf con la selección de campos del usuario.
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */

public class CreadorPDF {
	
	private static final String[] header = {"Nombre", "Año", "Max Jug", "Min Jug", "Ranking", "Rating"};
	/**
	 * método para crear los pdf
	 * @param lista selección de juegos del usuario
	 * @param archivo donde se va a guardar el archivo
	 */
	public static void crearPDF(List<Juego> lista, File archivo){
		try {
			Document documento = new Document();
			PdfWriter.getInstance(documento, new FileOutputStream(archivo));
			documento.open();
			PdfPTable fila = new PdfPTable(6);
			fila.setHeaderRows(1);
			for (String cadena: header){
				fila.addCell(cadena);
			}
			for (Juego juego : lista) {				
				
				fila.setSpacingAfter(5);
				fila.addCell(new Phrase(juego.getNombre(), FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,8)));
				fila.addCell(new Phrase(Integer.toString(juego.getAnyoPublicacion()), FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10)));
				fila.addCell(new Phrase(Integer.toString(juego.getMaximoJugadores()), FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10)));
				fila.addCell(new Phrase(Integer.toString(juego.getMinimoJugadores()), FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10)));
				fila.addCell(new Phrase(Integer.toString(juego.getRanking()), FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10)));
				fila.addCell(new Phrase(Double.toString(juego.getRating()), FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10)));;
				
			}
			documento.add(fila);
			documento.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
	
}
