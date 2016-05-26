package com.boreas.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.boreas.modelo.Coleccion;
import com.boreas.modelo.ConectarBD;
import com.boreas.modelo.CreadorPDF;
import com.boreas.modelo.CrearTablasBD;
import com.boreas.modelo.Extension;
import com.boreas.modelo.Juego;
import com.boreas.modelo.JuegoDAOImpSQLite;
import com.boreas.modelo.JuegoIlegalException;
import com.boreas.modelo.LecturaFichero;
import com.boreas.vista.AcercaDe;
import com.boreas.vista.TablaModelo;
import com.boreas.vista.VistaPrincipal;

/**
 * Clase Controlador
 * @author Manuel Quesada Segura
 * @vesion 0.0
 *
 */

public class Controlador {
	
	private VistaPrincipal vista;
	private File fichero;
	private LecturaFichero lFichero = new LecturaFichero();
	private int indice = 0; //indice que ocupa el juego en la lista
	private boolean modificar = false; //indica si se trata de un juego cargado en el formulario
	private Connection c = ConectarBD.getConexion();
	private JuegoDAOImpSQLite jSQLite = new JuegoDAOImpSQLite();
	private int id = -1; //identificador obtenido de la Base Datos. Es la primary key del objeto juego
	
	/**
	 * Contructor de la clase Controlador
	 * @param vista Vista de la aplicación
	 */
	
	public Controlador(VistaPrincipal vista){
		this.vista = vista;
		inicializar();
	}
	
	/**
	 * Clase que inicializa los eventos relacionados con la vista.
	 */
	public void inicializar(){
		
		//Esta condición filtra si se ha abierto la base de datos y contiene filas en su tabla juegos
		//Si es true, impide la carga de más ficheros, carga los juegos desde la tabla de la BD y activa
		//el trigger de borrado
		
		if (new File("database.db").exists() && jSQLite.obtenerFilas()>0){
			vista.getMntmAbrir().setEnabled(false);
			vista.getTabla().setModel(new TablaModelo(jSQLite.leerTodosJuegos()));
			CrearTablasBD.triggerBorrado(c);
		}
		
		//Evento carga de datos a la tabla

		vista.getMntmAbrir().addActionListener(l->{
			//Filtra que por defecto solo muestre los arcivos de extensión json.
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
			JFileChooser fC = new JFileChooser();
			fC.setFileFilter(filter);
			int seleccion = fC.showOpenDialog(vista.getMntmAbrir());
			if (seleccion == JFileChooser.APPROVE_OPTION){
				fichero = fC.getSelectedFile();
				lFichero.leerFichero(fichero);
				vista.getTabla().setModel(new TablaModelo(Coleccion.getLista()));
				CrearTablasBD.cargarTablasLotes(c, Coleccion.getLista());
				vista.getMntmAbrir().setEnabled(false);
			}			
			if (seleccion == JFileChooser.CANCEL_OPTION){
				vista.getLblBarraTitulo().setText("No hay fichero cargado");
			}
			
		});
		
		//Evento de carga de datos al formulario haciendo click en una fila
		
		vista.getTabla().getSelectionModel().addListSelectionListener(l->{			
			if (vista.getTabla().getSelectedRow() != -1){
				indice = vista.getTabla().getSelectedRow();
				modificarTrasSeleccion();
				rellenarFormulario(indice);
				id = jSQLite.obtenerID(Coleccion.getLista().get(indice));
				vista.getTabla().setModel(new TablaModelo(jSQLite.leerTodosJuegos()));
			}
		});
		
		//Evento para retroceder en la tabla
		
		vista.getAtras().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (indice!=0){
					indice -= 1;
					rellenarFormulario(indice);
				} else {
					vista.getLblBarraTitulo().setText("No tiene cargado ningún archivo");
				}
			}
		});
		
		//Evento para avanzar en la tabla
		
		vista.getAdelante().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(indice == 0 && lFichero!=null){
					rellenarFormulario(indice);
				} else if (indice == 0 && lFichero == null) {
					vista.getLblBarraTitulo().setText("No tiene cargado ningún archivo");
				} else {
					indice += 1;
					rellenarFormulario(indice);
				}
			}
		});
		
		//Evento de cierre desde el menú
		vista.getMntmCerrar().addActionListener(l->{
			System.exit(0);
		});
		
		//Evento que borra el formulario en espera de que el usuario lo rellene
		vista.getBtnNuevo().addActionListener(l->{
			limpiarFormulario();
			modificar = false;
		});
		
		//Evento para guardar
		vista.getBtnGuardar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esJuegoValido()){
					Juego juego;
					try {
						juego = new Juego(vista.getTextNombre().getText(), "resources/dado.png", 
								Integer.parseInt(vista.getTextMin().getText()), Integer.parseInt(vista.getTextMax().getText()), 
								Integer.parseInt(vista.getTextTiempo().getText()), Integer.parseInt(vista.getTextRanking().getText()), 
								Double.parseDouble(vista.getTextRating().getText()), Integer.parseInt(vista.getTextAnyo().getText()));
						if (esJuegoIgual(juego)){
							JOptionPane.showMessageDialog(vista.getFrame(), "Juego repetido", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							if(modificar){
								int lugarBD = jSQLite.obtenerID(Coleccion.getLista().get(indice));
								jSQLite.actualizarJuego(new Juego(vista.getTextNombre().getText(), 
										Coleccion.getLista().get(indice).getImagen(), 
										Integer.parseInt(vista.getTextMin().getText()), 
										Integer.parseInt(vista.getTextMax().getText()), 
										Integer.parseInt(vista.getTextTiempo().getText()), 
										Integer.parseInt(vista.getTextRanking().getText()), 
										Double.parseDouble(vista.getTextRating().getText()), 
										Integer.parseInt(vista.getTextAnyo().getText())),lugarBD);
								vista.getTabla().setModel(new TablaModelo(jSQLite.leerTodosJuegos()));
							} else {
								jSQLite.insertarJuego(juego);
								vista.getTabla().setModel(new TablaModelo(jSQLite.leerTodosJuegos()));
							}
						}
					} catch (NumberFormatException e1) {
						System.err.println("Formato de número incorrecto");
					} catch (JuegoIlegalException e1) {
						System.err.println("El juego no ha podido ser creado");
					}
					
				}
				
			}
		});
		
		//Evento de eliminación de elementos de la tabla
		
		vista.getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = "¿Desea borrar "+ Coleccion.getLista().get(indice).getNombre() + "?";
				if(lFichero!=null){
					if (confirmar(texto)==0){
						jSQLite.borrarJuego(Coleccion.getLista().get(indice).getNombre());
						vista.getTabla().setModel(new TablaModelo(jSQLite.leerTodosJuegos()));
						limpiarFormulario();
					}
				} else if (lFichero == null) {
					vista.getLblBarraTitulo().setText("No tiene cargado ningún archivo");
				}
			}
		});
		
		//Evento para generar PDF
		
		vista.getMntmGenerarPdf().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos PDF", "pdf");
				if (!Coleccion.getLista().isEmpty()){
					String texto = "¿Desea generar un informe con todos los elementos?";
					if(vista.getTabla().getSelectedRowCount() == 0){
						if(confirmar(texto)==0){
							JFileChooser jFPDF = new JFileChooser();
							jFPDF.setFileFilter(filtro);
							if(jFPDF.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
								CreadorPDF.crearPDF(Coleccion.getLista(), Extension.obtenerExtension(jFPDF));
							}
						}
						//Esta parte del código no está a pleno rendimiento aún. 
						//La idea es que el usuario pueda hacer una selección de 
						//filas para realizar el informe.
					} else {
						texto = "¿Desea generar un informe con los elementos seleccionados?";
						if (lFichero!=null){
							if (confirmar(texto)==0){
								JFileChooser jFPDF = new JFileChooser();
								jFPDF.setFileFilter(filtro);
								int seleccionSave = jFPDF.showSaveDialog(null);
								if (seleccionSave == JFileChooser.APPROVE_OPTION){
									List<Juego> lista = new ArrayList<Juego>();
									int[] arraySeleccion = vista.getTabla().getSelectedRows();
									for (int i = 0; i < arraySeleccion.length; i++ ){
										lista.add(Coleccion.getLista().get(arraySeleccion[i]));
									}
									CreadorPDF.crearPDF(lista, Extension.obtenerExtension(jFPDF));
								}
							} 
						}
					} 	
				} else vista.getLblBarraTitulo().setText("No tiene cargado ningún archivo");
			}
		});
		
		//Evento de acceso a Créditos
		
		vista.getMntmCreditos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe ad = new AcercaDe();
				ad.setLocationRelativeTo(vista.getFrame());
				ad.setVisible(true);
			}
		});
		
	}
	
	/**
	 * Método que permite la carga del formulario y rellenado de la barra de título
	 * He considerado oportuno hacerlo dentro del controlador ya que ninguna otra clase
	 * va a poder usarla de ahí a que su encapsulamiento sea privado
	 * @param indice número que indica qué elemento va a ser cargado en el formulario
	 */
	private void rellenarFormulario(int indice){
		modificar = true;
		vista.getTextNombre().setText(Coleccion.getLista().get(indice).getNombre());
		vista.getTextAnyo().setText(Coleccion.getLista().get(indice).getAnyoPublicacion()+"");
		vista.getTextMax().setText(Coleccion.getLista().get(indice).getMaximoJugadores()+"");
		vista.getTextMin().setText(Coleccion.getLista().get(indice).getMinimoJugadores()+"");
		vista.getTextRanking().setText(Coleccion.getLista().get(indice).getRanking()+"");
		vista.getTextRating().setText(Coleccion.getLista().get(indice).getRating()+"");
		vista.getTextTiempo().setText(Coleccion.getLista().get(indice).getTiempoJuego()+"");
		String cadena = "Juego "+(indice+1)+" de "+Coleccion.getLista().size();
		vista.getLblBarraTitulo().setText(cadena);
		//El siguiente código introduce una imagen del juego seleccionado en el programa
		//En defecto de imagen muestra una imagen de un dado que se conserva en /resources
		Image imagen = null;
		if (Coleccion.getLista().get(indice).getImagen().equals("resources/dado.png")){
			vista.getImagen().setText("");
			//Esta variable recoge la imagen almacenada en /resources
			ImageIcon ico = new ImageIcon(Main.class.getResource("/dado.png"));
			vista.getImagen().setIcon(ico);
		} else {
			try {
				URL url = new URL("http:"+Coleccion.getLista().get(indice).getImagen());
				imagen = ImageIO.read(url);
			} catch (IOException e1) {
				System.err.println("URL de imagen no encontrada");
				//e1.printStackTrace();
			}
			vista.getImagen().setText("");
			vista.getImagen().setIcon(new ImageIcon(imagen));
		}
	}

	/**
	 * Método que valida si los campos introducidos por el usuario son correctos.
	 * Hago notar que hay campos que se introducen desde el archivo json que son incorrectos
	 * @return true si el objeto Juego cumple todos las restrigciones expuestas
	 */
	private boolean esJuegoValido(){
		boolean valido = false;
		if (vista.getTextNombre().getText().isEmpty() || vista.getTextAnyo().getText().isEmpty() || vista.getTextMax().getText().isEmpty() || vista.getTextMin().getText().isEmpty() || vista.getTextTiempo().getText().isEmpty() || vista.getTextRanking().getText().isEmpty() || vista.getTextRating().getText().isEmpty()){
			JOptionPane.showMessageDialog(vista.getFrame(), "Comprueba que todos los campos estén rellenos.","Error", JOptionPane.ERROR_MESSAGE);
		} else if (!vista.getTextRating().getText().matches("[-+]?[0-9]*\\.?[0-9]*")){
			JOptionPane.showMessageDialog(vista.getFrame(), "Formato de rating incorrecto","Error", JOptionPane.ERROR_MESSAGE);
		} else if (Double.parseDouble(vista.getTextRating().getText())>10.0 || Double.valueOf(vista.getTextRating().getText())<0){
			JOptionPane.showMessageDialog(vista.getFrame(), "El rating será un valor entre 0 y 10.","Error", JOptionPane.ERROR_MESSAGE);
		} else if (!vista.getTextTiempo().getText().matches("[0-9]*")){
			JOptionPane.showMessageDialog(vista.getFrame(), "Formato de tiempo incorrecto","Error", JOptionPane.ERROR_MESSAGE);
		} else if (Integer.parseInt(vista.getTextTiempo().getText())<=0){
			JOptionPane.showMessageDialog(vista.getFrame(), "El tiempo de juego no puede ser menor o igual a 0.","Error", JOptionPane.ERROR_MESSAGE);
		} else if (!vista.getTextMin().getText().matches("[0-9]*")){
			JOptionPane.showMessageDialog(vista.getFrame(), "Formato de numero de jugadores mínimo incorrecto","Error", JOptionPane.ERROR_MESSAGE);
		} else if(Integer.parseInt(vista.getTextMin().getText())<=0){
			JOptionPane.showMessageDialog(vista.getFrame(), "El número mínimo de jugadores debe de ser mayor que 0.","Error", JOptionPane.ERROR_MESSAGE);
		} else if (!vista.getTextMax().getText().matches("[0-9]*")){
			JOptionPane.showMessageDialog(vista.getFrame(), "Formato de numero de jugadores máximo incorrecto","Error", JOptionPane.ERROR_MESSAGE);
		} else if (Integer.parseInt(vista.getTextMax().getText())<Integer.parseInt(vista.getTextMin().getText())){
			JOptionPane.showMessageDialog(vista.getFrame(), "El número de máximo de jugadores no puede ser inferior al mínimo.","Error", JOptionPane.ERROR_MESSAGE);
		} else if (!vista.getTextAnyo().getText().matches("(\\d){4}")||vista.getTextAnyo().getText().matches("/D")){
			JOptionPane.showMessageDialog(vista.getFrame(), "Formato de año incorrecto","Error", JOptionPane.ERROR_MESSAGE);
		} else if (LocalDate.now().getYear()<Integer.parseInt(vista.getTextAnyo().getText())){
			JOptionPane.showMessageDialog(vista.getFrame(), "¿Tienes una máquina del tiempo? No puedes insertar juegos de año superior al actual","Error", JOptionPane.ERROR_MESSAGE);
		} else if (!vista.getTextRanking().getText().matches("-?[0-9]*")){
			JOptionPane.showMessageDialog(vista.getFrame(), "Formato de ranking máximo incorrecto","Error", JOptionPane.ERROR_MESSAGE);
		} else {
			valido = true;
		}
		return valido;
	}
	/**
	 * El método .equals está sobre escrito pero me ha parecido razonable extraer el método aquí
	 * para no saturar el evento que controla la modificación de datos en el fomulario.
	 * @param juego que ha introducido el usuario y será comparado con la lista de juegos.
	 * @return true si el usuario está tratando de introducir un juego que ya está en la lista.
	 */
	private boolean esJuegoIgual(Juego juego){
		boolean igual = false;
		for (Juego j : Coleccion.getLista()) {
			if(j.equals(juego)) igual = true;
		}
		return igual;
	}
	/**
	 * Se utiliza en varios lugares del código un elemento JOptionPane que permite la selección entre "sí y no".
	 * Me ha parecido oportuno crear un método que gestione dicha selección en la cual sólo se le introduce un texto
	 * que aparecerá en el cuerpo de la ventana.
	 * @param texto cadena de texto que aparecerá en el cuerpo de la ventana
	 * @return la selección del usuario en forma de valor numérico.
	 */
	private int confirmar(String texto){
		int seleccion = JOptionPane.showOptionDialog(vista.getFrame(), texto , "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, "No");
		return seleccion;
	}
	

	/**
	 * Método que notificará que hay cambios realizados en el formulario si el usuario pasa a otra fila
	 * 
	 */
	private void modificarTrasSeleccion(){
		if (id != -1 && id != jSQLite.obtenerID(Coleccion.getLista().get(vista.getTabla().getSelectedRow()))){
			if (jSQLite.obtenerJuego(id) != null && (!(vista.getTextNombre().getText().isEmpty() && vista.getTextAnyo().getText().isEmpty() && vista.getTextMax().getText().isEmpty() && vista.getTextMin().getText().isEmpty() && vista.getTextRanking().getText().isEmpty() && vista.getTextRating().getText().isEmpty() && vista.getTextTiempo().getText().isEmpty()))){
				if(!(vista.getTextNombre().getText().equals(jSQLite.obtenerJuego(id).getNombre()) && vista.getTextAnyo().getText().equals(jSQLite.obtenerJuego(id).getAnyoPublicacion()+"") && vista.getTextMax().getText().equals(jSQLite.obtenerJuego(id).getMaximoJugadores()+"") && vista.getTextMin().getText().equals(jSQLite.obtenerJuego(id).getMinimoJugadores()+"") && vista.getTextRanking().getText().equals(jSQLite.obtenerJuego(id).getRanking()+"") && vista.getTextRating().getText().equals(jSQLite.obtenerJuego(id).getRating()+"") && vista.getTextTiempo().getText().equals(jSQLite.obtenerJuego(id).getTiempoJuego()+""))){
					String texto = "Ha realizado cambios. ¿Desea guardar?";
					if (confirmar(texto)==0){
						try {
							jSQLite.actualizarJuego(new Juego(vista.getTextNombre().getText(), Coleccion.getLista().get(indice).getImagen(), Integer.parseInt(vista.getTextMin().getText()), Integer.parseInt(vista.getTextMax().getText()), Integer.parseInt(vista.getTextTiempo().getText()), Integer.parseInt(vista.getTextRanking().getText()), Double.parseDouble(vista.getTextRating().getText()), Integer.parseInt(vista.getTextAnyo().getText())),id);
							vista.getTabla().setModel(new TablaModelo(jSQLite.leerTodosJuegos()));
						} catch (NumberFormatException | JuegoIlegalException e) {
							System.err.println("El juego tiene un formato incorrecto");
						}

					}
					
				}
			}
		}
	}
	
	/**
	 * Método que limpia el formulario
	 */
	
	private void limpiarFormulario(){
		vista.getTextNombre().setText("");
		vista.getTextAnyo().setText("");
		vista.getTextMin().setText("");
		vista.getTextMax().setText("");
		vista.getTextRanking().setText("");
		vista.getTextRating().setText("");
		vista.getTextTiempo().setText("");
		modificar = false;
	}
	
}
