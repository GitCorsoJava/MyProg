package it.corso.mercury;

public class Utente {
	
	public Utente(){}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getTipologia() {
		return tipologia;
	}

	public void setTipologia(char tipologia) {
		this.tipologia = tipologia;
	}

	public char getStato() {
		return stato;
	}

	public void setStato(char stato) {
		this.stato = stato;
	}

	private String email;
	private String password;
	private char tipologia;
	private char stato;
	
	

}
