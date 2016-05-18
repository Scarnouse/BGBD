package com.boreas.controlador;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AcercaDe extends JDialog implements ActionListener{
	
	private JButton salir;
	
	public AcercaDe() {
		super();
		iniciar();
		this.setTitle("Juegos");
		this.setSize(293, 217);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
	}
	
	private void iniciar(){
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelDescripcion = new JPanel(new GridLayout(5, 1));
		JPanel panelSalida = new JPanel(new FlowLayout());
		JPanel panelIcono = new JPanel(new FlowLayout());
		
		JLabel titulo = new JLabel("GameBoard",JLabel.CENTER);
		titulo.setFont(new Font("Arial",Font.BOLD,15));
		ImageIcon ico = new ImageIcon("resources/dado.png");
		JLabel icono = new JLabel(ico);
		panelIcono.add(icono);
		
		JLabel nombre = new JLabel("Manuel Quesada Segura");
		nombre.setHorizontalAlignment(JLabel.LEFT);
		JLabel fecha = new JLabel("26/05/2016");
		fecha.setHorizontalAlignment(JLabel.LEFT);
		salir = new JButton("Salir");
		salir.addActionListener(this);
		
		panelSalida.add(salir);
		panelDescripcion.add(nombre);
		panelDescripcion.add(fecha);
		panelDescripcion.add(panelSalida);
		
		panelPrincipal.add(titulo,BorderLayout.NORTH);
		panelPrincipal.add(panelIcono,BorderLayout.WEST);
		panelPrincipal.add(panelDescripcion,BorderLayout.EAST);
		
		this.add(panelPrincipal);
		this.pack();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
	}

}
