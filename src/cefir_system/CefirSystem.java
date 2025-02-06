package cefir_system;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CefirSystem {
	
	public CefirSystem() {
		JFrame frameMain = new JFrame();
		frameMain.setSize(600, 500);
		frameMain.setVisible(true);
		frameMain.setLocationRelativeTo(null);
		frameMain.setResizable(false);
		frameMain.setLayout(null);
		
		JLabel labelTittle = new JLabel("Cefir System");
		labelTittle.setFont(new Font("Arial", Font.BOLD, 30));
		labelTittle.setBounds(200, 20, 300, 50);
		labelTittle.setVisible(true);
		frameMain.add(labelTittle);
		
		JButton btnRegister = new JButton();
		btnRegister.setVisible(true);
		btnRegister.setBounds(160, 300, 100, 30);
		btnRegister.setText("Cadastro");
		btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JButton btnConsulta = new JButton();
		btnConsulta.setVisible(true);
		btnConsulta.setBounds(300, 300, 100, 30);
		btnConsulta.setText("Consulta");
		btnConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConsulta.setEnabled(true);
		
		frameMain.add(btnRegister);
		frameMain.add(btnConsulta);
		
		btnRegister.addActionListener(new ActionListener() {	
			//button action for Register
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterPerson().run();;
				frameMain.dispose();;
			}
		});
		
		btnConsulta.addActionListener(new ActionListener() {	
			//button action for Consulta
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConsultaPerson().run();;
				frameMain.dispose();
			}
		});
	}
	 
	public static void main(String[] args) {
		new CefirSystem();
	}
}
