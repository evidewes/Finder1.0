package com.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.controller.Controller;


public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public Frame() {
		initComponents();
	}
	
	private Controller controller = Controller.getInstance();

	private void initComponents() {
		// Configurações GERAIS
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setSize(new Dimension(600,500));
		setVisible(true);
		getContentPane().setLayout(null);
		
		JLabel lblProcurarPor = new JLabel("Procurar por:");
		lblProcurarPor.setBounds(51, 18, 104, 15);
		getContentPane().add(lblProcurarPor);
		
		textField = new JTextField();
		textField.setText("SAM_1706.JPG");
		textField.setBounds(156, 16, 421, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setBounds(61, 47, 526, 226);
		fileChooser.setControlButtonsAreShown(false);
		getContentPane().add(fileChooser);
		
		JList<String> list = new JList<String>();
		list.setBounds(51, 327, 526, 133);
		JScrollPane menuScrollPane  = new JScrollPane(list);
		menuScrollPane.setBounds(51, 327, 526, 133);
		getContentPane().add(menuScrollPane);
		
		JButton btnNewButton = new JButton("Procurar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.search(fileChooser.getSelectedFile(), textField.getText(), list);
			}
		});
		btnNewButton.setBounds(377, 265, 200, 50);
		getContentPane().add(btnNewButton);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Frame();
			}
		});
	}
}
