package com.boreas.vista;



import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Box;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.boreas.controlador.Controlador;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Clase que contiene la vista principal
 * @author Manuel Quesada Segura
 * @version 0.0
 *
 */
public class VistaPrincipal {

	private JFrame frame;
	private JTextField textNombre;
	private JTextField textAnyo;
	private JTextField textMax;
	private JTextField textMin;
	private JTextField textRanking;
	private JTextField textRating;
	private JTextField textTiempo;
	private JMenuItem mntmAbrir;
	private JPanel panelIzquierdo;
	private JTable tabla;
	private JMenuBar menu;
	private JMenu mnArchivo;
	private JMenuItem mntmGuardar;
	private JMenuItem mntmCerrar;
	private JPanel panelTitulo;
	private FlowLayout flowLayout;
	private JLabel lblBarraTitulo;
	private JSplitPane splitPane;
	private JPanel panelDerecho;
	private Box verticalBox;
	private Box verticalBox_1;
	private Box horizontalBox;
	private Box horizontalBox_1;
	private JPanel panel;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private GroupLayout gl_panelDerecho;
	private JLabel lblNewLabel;
	private JLabel lblAo;
	private JLabel lblMax;
	private JLabel lblMin;
	private JLabel lblJugadores;
	private JLabel lblRanking;
	private JLabel lblRating;
	private JLabel lblTiempoDeJuego;
	private GroupLayout gl_panel;
	private Component horizontalGlue_1;
	private Component horizontalGlue;
	private Component verticalGlue;
	private GroupLayout gl_panelIzquierdo;
	private JScrollPane scrollPane;
	private JLabel imagen;
	private JButton atras;
	private JButton adelante;
	private JButton btnEliminar;
	private JMenu mnInforme;
	private JMenuItem mntmGenerarPdf;
	private JMenu mnAyuda;
	private JMenuItem mntmCreditos;
	
	public VistaPrincipal(){
		initialize();
		Controlador controlador = new Controlador(this);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		mnArchivo = new JMenu("Archivo");
		menu.add(mnArchivo);
		
		mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		
		mntmGuardar = new JMenuItem("Guardar");
		mnArchivo.add(mntmGuardar);
		
		mntmCerrar = new JMenuItem("Cerrar");
		mnArchivo.add(mntmCerrar);
		
		mnInforme = new JMenu("Informe");
		menu.add(mnInforme);
		
		mntmGenerarPdf = new JMenuItem("Generar PDF");
		mnInforme.add(mntmGenerarPdf);
		
		mnAyuda = new JMenu("Ayuda");
		menu.add(mnAyuda);
		
		mntmCreditos = new JMenuItem("Créditos");
		mnAyuda.add(mntmCreditos);
		
		panelTitulo = new JPanel();
		flowLayout = (FlowLayout) panelTitulo.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frame.getContentPane().add(panelTitulo, BorderLayout.SOUTH);
		
		lblBarraTitulo = new JLabel("");
		lblBarraTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		panelTitulo.add(lblBarraTitulo);
		
		splitPane = new JSplitPane();
		splitPane.setDividerLocation(300);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		
		panelDerecho = new JPanel();
		splitPane.setRightComponent(panelDerecho);
		
		verticalBox = Box.createVerticalBox();
		
		verticalBox_1 = Box.createVerticalBox();
		
		horizontalBox = Box.createHorizontalBox();
		
		horizontalBox_1 = Box.createHorizontalBox();
		
		panel = new JPanel();
		
		btnGuardar = new JButton("Guardar");
		
		btnNuevo = new JButton("Nuevo");
		
		imagen = new JLabel("Juego");
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		
		atras = new JButton("<<");
		
		adelante = new JButton(">>");
		
		gl_panelDerecho = new GroupLayout(panelDerecho);
		gl_panelDerecho.setHorizontalGroup(
			gl_panelDerecho.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addGap(304)
					.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(173, Short.MAX_VALUE))
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnGuardar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(atras, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
					.addComponent(adelante, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNuevo)
					.addContainerGap())
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addContainerGap()
					.addComponent(horizontalBox, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addGap(70)
					.addComponent(horizontalBox_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addGap(240)
					.addComponent(verticalBox_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(206, Short.MAX_VALUE))
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addGap(212)
					.addComponent(imagen, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addGap(207))
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addGap(78)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
					.addGap(69))
		);
		gl_panelDerecho.setVerticalGroup(
			gl_panelDerecho.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDerecho.createSequentialGroup()
							.addContainerGap()
							.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDerecho.createSequentialGroup()
							.addGap(81)
							.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDerecho.createSequentialGroup()
							.addGap(83)
							.addComponent(horizontalBox_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGap(17)
					.addComponent(imagen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(verticalBox_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnNuevo)
						.addComponent(atras)
						.addComponent(adelante))
					.addContainerGap())
		);
		
		lblNewLabel = new JLabel("Nombre");
		
		lblAo = new JLabel("Año");
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		textAnyo = new JTextField();
		textAnyo.setColumns(10);
		
		lblMax = new JLabel("max");
		
		textMax = new JTextField();
		textMax.setColumns(10);
		
		lblMin = new JLabel("min");
		
		textMin = new JTextField();
		textMin.setColumns(10);
		
		lblJugadores = new JLabel("Jugadores");
		
		textRanking = new JTextField();
		textRanking.setColumns(10);
		
		lblRanking = new JLabel("Ranking");
		
		lblRating = new JLabel("Rating");
		
		textRating = new JTextField();
		textRating.setColumns(10);
		
		lblTiempoDeJuego = new JLabel("Tiempo de Juego");
		
		textTiempo = new JTextField();
		textTiempo.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textNombre, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textAnyo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(lblRanking)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textRanking, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRating)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textRating, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblJugadores)
									.addGap(18)
									.addComponent(lblMin)
									.addGap(4)
									.addComponent(textMin, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addGap(24))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTiempoDeJuego)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMax)
									.addGap(4)
									.addComponent(textMax, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
								.addComponent(textTiempo, 0, 0, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblAo)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textAnyo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMin)
								.addComponent(lblJugadores)))
						.addComponent(textMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblMax)))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textTiempo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTiempoDeJuego))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textRanking, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textRating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRanking)
								.addComponent(btnEliminar)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblRating)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_1);
		
		horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		verticalGlue = Box.createVerticalGlue();
		verticalBox_1.add(verticalGlue);
		panelDerecho.setLayout(gl_panelDerecho);
		
		panelIzquierdo = new JPanel();
		splitPane.setLeftComponent(panelIzquierdo);
		
		tabla = new JTable();
		scrollPane = new JScrollPane(tabla);
		gl_panelIzquierdo = new GroupLayout(panelIzquierdo);
		gl_panelIzquierdo.setHorizontalGroup(
			gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzquierdo.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_panelIzquierdo.setVerticalGroup(
			gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
		);
		panelIzquierdo.setLayout(gl_panelIzquierdo);

		

	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JMenuItem getMntmAbrir() {
		return mntmAbrir;
	}
	
	public JTable getTabla() {
		return tabla;
	}
	
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public void setTextNombre(JTextField textNombre) {
		this.textNombre = textNombre;
	}

	public JTextField getTextAnyo() {
		return textAnyo;
	}

	public void setTextAnyo(JTextField textAnyo) {
		this.textAnyo = textAnyo;
	}

	public JTextField getTextMax() {
		return textMax;
	}

	public void setTextMax(JTextField textMax) {
		this.textMax = textMax;
	}

	public JTextField getTextMin() {
		return textMin;
	}

	public void setTextMin(JTextField textMin) {
		this.textMin = textMin;
	}

	public JTextField getTextRanking() {
		return textRanking;
	}

	public void setTextRanking(JTextField textRanking) {
		this.textRanking = textRanking;
	}

	public JTextField getTextRating() {
		return textRating;
	}

	public void setTextRating(JTextField textRating) {
		this.textRating = textRating;
	}

	public JTextField getTextTiempo() {
		return textTiempo;
	}

	public void setTextTiempo(JTextField textTiempo) {
		this.textTiempo = textTiempo;
	}
	
	public JLabel getImagen() {
		return imagen;
	}
	
	public JButton getAtras() {
		return atras;
	}
	
	public JButton getAdelante() {
		return adelante;
	}
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}
	
	public JButton getBtnNuevo() {
		return btnNuevo;
	}
	
	public JMenuItem getMntmCerrar() {
		return mntmCerrar;
	}
	
	public JLabel getLblBarraTitulo() {
		return lblBarraTitulo;
	}
	
	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	
	public JMenuItem getMntmGenerarPdf() {
		return mntmGenerarPdf;
	}
	
	public JMenuItem getMntmCreditos() {
		return mntmCreditos;
	}
}
