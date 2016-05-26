package com.boreas.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.sqlite.SQLiteConfig;

/**
 * 
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */

public class ConectarBD {
	private static Connection conexion;
	
	private ConectarBD () {} ;
	
	/**
	 * Getter de Conexion. Esta clase sigue el patrón singleton y sólo permite ser instanciada un única vez
	 * @return la conexión abierta de la base de datos
	 */
	
	public static Connection getConexion() {
		if (conexion==null){
			Runtime.getRuntime().addShutdownHook(new MiShutdown());
			ResourceBundle rb = ResourceBundle.getBundle("sqlite");
			String url = rb.getString("url");
			String driver = rb.getString("driver");
			try {
				//carga del driver
				Class.forName(driver);
				//establecemos configuarcion de SQlite particular.
				SQLiteConfig sqConfig = new SQLiteConfig();
				sqConfig.enforceForeignKeys(true);
				//carga de url
				conexion = DriverManager.getConnection(url,sqConfig.toProperties());
			} catch (ClassNotFoundException e) {
				System.err.println("Driver no encontrado");
				//e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}
	
	/**
	 * Clase interna que cierra la conexión de la Base de Datos
	 * @author Manuel Quesada Segura
	 * @vesion 0.0
	 */
	
	static class MiShutdown extends Thread{
		@Override
		public void run() {
			Connection con = ConectarBD.getConexion();
		    if (con!=null){
		    	try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		}
	}
	
}
