package cefir_system;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class ConsultaPerson {
	
	
	public void run() {
		JFrame frameConsulta = new JFrame();
		frameConsulta.setSize(600, 500);
		frameConsulta.setVisible(true);
		frameConsulta.setLocationRelativeTo(null);
		frameConsulta.setResizable(false);
		frameConsulta.setLayout(null);
		
		JLabel labelTittle = new JLabel("Consulta");
		labelTittle.setFont(new Font("Arial", Font.BOLD, 30));
		labelTittle.setBounds(230, 15, 300, 50);
		labelTittle.setVisible(true);
		frameConsulta.add(labelTittle);
		
		JLabel labelName = new JLabel("Nome:");
		labelName.setFont(new Font("Arial", Font.BOLD, 15));
		labelName.setBounds(15, 105, 50, 20);
		labelName.setVisible(true);
		frameConsulta.add(labelName);
		
		JLabel labelLogin = new JLabel("login:");
		labelLogin.setFont(new Font("Arial", Font.BOLD, 15));
		labelLogin.setBounds(15, 205, 50, 20);
		labelLogin.setVisible(false);
		frameConsulta.add(labelLogin);
		
		JLabel labelSenha = new JLabel("senha:");
		labelSenha.setFont(new Font("Arial", Font.BOLD, 15));
		labelSenha.setBounds(15, 245, 50, 20);
		labelSenha.setVisible(false);
		frameConsulta.add(labelSenha);
		
		JLabel labelCpf = new JLabel("CPF:");
		labelCpf.setFont(new Font("Arial", Font.BOLD, 15));
		labelCpf.setBounds(310, 105, 50, 20);
		labelCpf.setVisible(true);
		frameConsulta.add(labelCpf);
		
		JLabel labelData = new JLabel("Ano:");
		labelData.setFont(new Font("Arial", Font.BOLD, 15));
		labelData.setBounds(15, 160, 50, 20);
		labelData.setVisible(true);
		frameConsulta.add(labelData);
		
		JLabel labelDataCredenciais = new JLabel();
		labelDataCredenciais.setFont(new Font("Arial", Font.BOLD, 15));
		labelDataCredenciais.setBounds(250, 200, 300, 20);
		labelDataCredenciais.setVisible(true);
		frameConsulta.add(labelDataCredenciais);
		
		JTextField textName = new JTextField();
		textName.setBounds(70, 100, 200, 35);
		textName.setFont(new Font("Arial", Font.BOLD,  15));
		textName.setVisible(true);
		frameConsulta.add(textName);
		
		MaskFormatter cpf = null;
		try {
			cpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JTextField textCpf = new JFormattedTextField(cpf);
		textCpf.setBounds(350, 100, 180, 35);
		textCpf.setFont(new Font("Arial", Font.BOLD,  17));
		textCpf.setVisible(true);
		frameConsulta.add(textCpf);
		
		MaskFormatter data = null;
		try {
			data = new MaskFormatter("####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JTextField textData = new JFormattedTextField(data);
		textData.setBounds(70, 150, 100, 35);
		textData.setFont(new Font("Arial", Font.BOLD,  17));
		textData.setVisible(true);
		frameConsulta.add(textData);
		
		JTextField textLogin = new JTextField();
		textLogin.setBounds(70, 200, 150, 30);
		textLogin.setFont(new Font("Arial", Font.BOLD,  17));
		textLogin.setVisible(false);
		frameConsulta.add(textLogin);
		
		JTextField textSenha = new JTextField();
		textSenha.setBounds(70, 240, 150, 30);
		textSenha.setFont(new Font("Arial", Font.BOLD,  17));
		textSenha.setVisible(false);
		frameConsulta.add(textSenha);
		
		JButton btnConsulta = new JButton();
		btnConsulta.setVisible(true);
		btnConsulta.setBounds(200, 300, 100, 30);
		btnConsulta.setText("Consultar");
		btnConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frameConsulta.add(btnConsulta);
		
		btnConsulta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					consulta(textName.getText(), textCpf.getText(), textData.getText(), textLogin, textSenha, labelDataCredenciais, labelLogin, labelSenha);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void consulta(String name, String cpf, String data, JTextField jTextLogin, JTextField jTextSenha, JLabel labelData, JLabel labelLogin, JLabel labelSenha) throws IOException {
		File pasta = new File(RegisterPerson.DIR_DEFAULT + data + "\\" + name + " " + "-" + " " + cpf);
		if(pasta.exists()) {
			File pastaDocumento = new File(pasta, "documentos");
			File fileCredenciais = new File(pastaDocumento, "credenciais.txt");
		
			List<String> linhas = Files.readAllLines(Paths.get(fileCredenciais.toPath().toUri()));
			
			//regexs para encontrar o login e senha em credenciais.txt
	        String regexLogin1 = "\\.[a-zA-Z0-9_]+";
	        String regexLogin2 = "[a-zA-Z0-9_]+";
	        String regexSenha = "[a-zA-Z0-9@#\\$%^&*!]+";
	        Pattern patternLogin = Pattern.compile(regexLogin1);
	        Pattern patternSenha = Pattern.compile(regexSenha);
	  
            // Percorre cada linha da lista e busca
            for (String linha : linhas) {
            	String regexLogin;
            	
                // Lê todas as linhas do arquivo

            	//resolver bug de procurar padrões regex
                Matcher matcherLogin = patternLogin.matcher(linha);
                while (matcherLogin.find()) {          
                    jTextLogin.setText(matcherLogin.group());
                    if(matcherLogin.find()) {
                    	patternLogin = Pattern.compile(regexLogin2);
                        jTextLogin.setText(matcherLogin.group());
                    }
                }
                
                Matcher matcherSenha = patternSenha.matcher(linha);
                while (matcherSenha.find()) {
                	jTextSenha.setText(matcherSenha.group());
                }
                
            }
			
            labelLogin.setVisible(true);
            labelSenha.setVisible(true);
			jTextLogin.setVisible(true);
			jTextSenha.setVisible(true);
			
			//ultima modificação do arquivo
			BasicFileAttributes basic = Files.readAttributes(fileCredenciais.toPath(), BasicFileAttributes.class);
			FileTime ft = basic.lastModifiedTime();
			
			String dataFile = "dd/MM/yyyy";
			String horaFile = "HH:mm:ss";
			String data1, hora1;
			
			SimpleDateFormat formatDate = new SimpleDateFormat(dataFile);
			data1 = formatDate.format(ft.toMillis());
			formatDate = new SimpleDateFormat(horaFile);
			hora1 = formatDate.format(ft.toMillis());
			
			labelData.setText("Ultima modificação: " + data1 + " "+ hora1);

		}
	}
	
}
