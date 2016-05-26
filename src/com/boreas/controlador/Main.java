package com.boreas.controlador;
import java.awt.EventQueue;

import com.boreas.vista.VistaPrincipal;
/**
 * 
 * @author Manuel Quesada Segura
 * @version 0.0
 * Esta es la clase main que inicia la aplicación
 *
 */
public class Main {
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal window = new VistaPrincipal();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					System.err.println("No ha sido posible cargar la vista");
					//e.printStackTrace();
				}
			}
		});
		
	}

}
