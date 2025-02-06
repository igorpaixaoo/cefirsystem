package cefir_system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Credenciais{
	
	private String[] letters = {"A", "B", "C", "D", "E", "F", 
			"G", "H", "I", "J", "K", "L", "M", "N", "O", 
		    "P", "Q", "R", "S", "T", "U",
		    "V", "W", "X", "Y", "Z"
	};
	
	private Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	private String[] chars = {"@", "%", "#", "$"};
	
	private String letterFinal;
	private String numberFinal;
	private String charFinal;
	
	
	String login;
	Integer numLogin;
	
	//instanciando a classe
	RegisterPerson rp = new RegisterPerson();
	
	public Credenciais(){
		
	}
	
	//method for login and password
	public void generatorLoginAndPassword(String nameLogin, File file) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		
		Random random = new Random();
		
		//login
		this.login = nameLogin.strip().toLowerCase();
		rp.setLogin("." + login + sdf.format(c.getTime()));
		
		//password
		
		String char1, char2, char3, char4;
		Integer num1, num2, num3;
		String chars1;
		
		//for letters
		char1 = letters[random.nextInt(0, 24)];
		char2 = letters[random.nextInt(0, 24)].toLowerCase();
		char3 = letters[random.nextInt(0, 24)].toLowerCase();
		char4 = letters[random.nextInt(0, 24)];
		
		//for numbers
		num1 = numbers[random.nextInt(0, 9)];
		num2 = numbers[random.nextInt(0, 9)];
		num3 = numbers[random.nextInt(0, 9)];
		
		//for chars
		chars1 = chars[random.nextInt(0, 3)];
		
		setLetterFinal(char1 + char2 + char3 + char4);
		setNumberFinal(num1.toString() + num2.toString() + num3.toString());
		setCharFinal(chars1);
		
		//concatenando variáveis para formação da senha
		rp.setPassword(getLetterFinal() + getNumberFinal() + getCharFinal());
		
		System.out.println(rp.getLogin());
		System.out.println(rp.getPassword());

		
		try {
			savePersonAndFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//method save person with file 'credenciais.txt'
	public void savePersonAndFile(File file) throws IOException  {
		FileWriter fw = new FileWriter(file.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("login SEIA: " + rp.getLogin());
		bw.newLine();
		bw.write("senha SEIA: " + rp.getPassword());
		bw.flush();
		bw.close();

	}

	public String getLetterFinal() {
		return letterFinal;
	}

	public void setLetterFinal(String letterFinal) {
		this.letterFinal = letterFinal;
	}

	public String getNumberFinal() {
		return numberFinal;
	}

	public void setNumberFinal(String numberFinal) {
		this.numberFinal = numberFinal;
	}

	public String getCharFinal() {
		return charFinal;
	}

	public void setCharFinal(String charFinal) {
		this.charFinal = charFinal;
	}

	
	
	
}
