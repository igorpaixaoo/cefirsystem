package cefir_system;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class RegisterPerson extends Person{
	public static final String DIR_DEFAULT = "D:\\OneDrive - MSFT\\Medições de Terras\\";
	private String year;
	
	File fileCredenciais;
	
	public void run() {
		JFrame frameRegister = new JFrame();
		frameRegister.setSize(600, 500);
		frameRegister.setVisible(true);
		frameRegister.setLocationRelativeTo(null);
		frameRegister.setResizable(false);
		frameRegister.setLayout(null);
		
		JLabel labelTittle = new JLabel("Cadastro");
		labelTittle.setFont(new Font("Arial", Font.BOLD, 30));
		labelTittle.setBounds(230, 15, 300, 50);
		labelTittle.setVisible(true);
		frameRegister.add(labelTittle);
		
		JLabel labelName = new JLabel("Nome:");
		labelName.setFont(new Font("Arial", Font.BOLD, 15));
		labelName.setBounds(15, 110, 50, 20);
		labelName.setVisible(true);
		frameRegister.add(labelName);
		
		JLabel labelLastName = new JLabel("Sobrenome:");
		labelLastName.setFont(new Font("Arial", Font.BOLD, 12));
		labelLastName.setBounds(280, 110, 80, 20);
		labelLastName.setVisible(true);
		frameRegister.add(labelLastName);
		
		JLabel labelCpf = new JLabel("CPF:");
		labelCpf.setFont(new Font("Arial", Font.BOLD, 15));
		labelCpf.setBounds(15, 180, 50, 20);
		labelCpf.setVisible(true);
		frameRegister.add(labelCpf);
		
		JTextField textName = new JTextField();
		textName.setBounds(70, 100, 200, 40);
		textName.setFont(new Font("Arial", Font.BOLD,  15));
		textName.setVisible(true);
		frameRegister.add(textName);
		
		JTextField textLastName = new JTextField();
		textLastName.setBounds(350, 100, 200, 40);
		textLastName.setFont(new Font("Arial", Font.BOLD,  15));
		textLastName.setVisible(true);

		frameRegister.add(textLastName);
		
		MaskFormatter cpf = null;
		try {
			cpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JTextField textCpf = new JFormattedTextField(cpf);
		textCpf.setBounds(70, 170, 180, 40);
		textCpf.setFont(new Font("Arial", Font.BOLD,  17));
		textCpf.setVisible(true);
		frameRegister.add(textCpf);
		
		JButton btnSave = new JButton();
		btnSave.setVisible(true);
		btnSave.setBounds(200, 300, 100, 30);
		btnSave.setText("Salvar");
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frameRegister.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {	
			//button action for save
			@Override
			public void actionPerformed(ActionEvent e) {
				setName(textName.getText());
				setLastName(textLastName.getText());
				setCpf(textCpf.getText());		
				
				try {
					createPasta();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frameRegister.dispose();
				
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
				
				new CefirSystem();

			}
		});
	}
	
	public void createPasta() throws IOException {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		setYear(sdf.format(c.getTime()));
		
		//criando pasta principal
		File pastaMain = new File(DIR_DEFAULT + getYear() + "\\" + getName() + " " + getLastName() + " " + "-" + " " + getCpf());
		pastaMain.mkdir();
		//criando a pasta documentos
		File pastaDocumento = new File(pastaMain, "documentos");
		pastaDocumento.mkdir();
		//criando o arquivo credenciais onde ficarão os logins e senhas
		File fileCredenciais = new File(pastaDocumento, "credenciais.txt");
		
		try {
			fileCredenciais.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Credenciais().generatorLoginAndPassword(getName(), fileCredenciais);

	}

	public String getYear() {
		return year;
	}

	public void setYear(String string) {
		this.year = string;
	}
	
	

}
