package cefir_system;

public abstract class Person {
	//Atributtes for persons
	private String name;
	private String lastName;
	private String cpf;
	private String login;
	private String password;
	
	public Person(String name, String lastName, String cpf, String login, String password) {
		this.name = name;
		this.lastName = lastName;
		this.cpf = cpf;
		this.login = login;
		this.password = password;
	}
	
	public Person(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Person() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}
