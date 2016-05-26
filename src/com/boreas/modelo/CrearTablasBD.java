package com.boreas.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Clase que crea las tablas de la BD
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */
public class CrearTablasBD {
	
	private static Statement sentencia;
	/**
	 * Método por lotes que carga e inserta los juegos
	 * @param c Conexión
	 * @param lista lista de la que extrae los juegos
	 */
	public static void cargarTablasLotes(Connection c, List<Juego> lista){
		String sqlCreaTabla = "CREATE TABLE IF NOT EXISTS JUEGO ("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "NOMBRE TEXT,"
				+ "IMAGEN TEXT,"
				+ "MINIMO INTEGER,"
				+ "MAXIMO INTEGER,"
				+ "TIEMPO INTEGER,"
				+ "RANKING INTEGER,"
				+ "RATING REAL,"
				+ "ANYO TEXT"
				+ ");";
		try {
			sentencia = c.createStatement();
			sentencia.addBatch(sqlCreaTabla);
			for (Juego juego:lista){
				String inserta = "INSERT INTO JUEGO VALUES (null,'"+juego.getNombre()+"','"+juego.getImagen()+"','"+juego.getMinimoJugadores()+"','"+juego.getMaximoJugadores()+"','"+juego.getTiempoJuego()+"','"+juego.getRanking()+"','"+juego.getRating()+"','"+juego.getAnyoPublicacion()+"')";
				sentencia.addBatch(inserta);
			}
			sentencia.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Trigger que inserta tras borrar un elemento de la tabla en la tabla
	 * Historial. Esta se creará si no existe.
	 * @param c Conexión
	 */
	public static void triggerBorrado(Connection c){
		String sqlHistorial = "CREATE TABLE IF NOT EXISTS HISTORIAL("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "NOMBRE TEXT,"
				+ "FECHABORRADO DATE);";
		String sqlTrigger= "CREATE TRIGGER IF NOT EXISTS BORRADO "
				+ "AFTER DELETE ON JUEGO FOR EACH ROW "
				+ "BEGIN "
				+ "INSERT INTO HISTORIAL VALUES (null, old.NOMBRE, date('now'));"
				+ "END";
		try {
			sentencia = c.createStatement();
			sentencia.addBatch(sqlHistorial);
			sentencia.addBatch(sqlTrigger);
			sentencia.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * Método para crear una vista compuesta por los campos nombre, ranking, rating
	 * @param c Conexión
	 */
	public static void crearVista(Connection c){
		String sql = "CREATE VIEW IF NOT EXISTS VISTAJUEGO("
				+ "NOMBRE, RANKING, RATING) "
				+ "AS SELECT NOMBRE, RANKING, RATING FROM JUEGO;";
		try {
			sentencia = c.createStatement();
			sentencia.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	/**
	 * Método que crea un indice del campo nombre.
	 * @param c Conexion
	 */
	public static void crearIndice(Connection c){
		String sql = "CREATE INDEX IF NOT EXISTS INDICEJUEGO ON JUEGO(NOMBRE);";
		try {
			sentencia = c.createStatement();
			sentencia.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
}

