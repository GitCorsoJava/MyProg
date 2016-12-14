package it.corso.mercury;

public class Ente extends Utente
{
	public Ente(){}
	
	public String getNomeEnte() {
		return nomeEnte;
	}
	public void setNomeEnte(String nomeEnte) {
		this.nomeEnte = nomeEnte;
	}

	public String getPiCf() {
		return piCf;
	}

	public void setPiCf(String piCf) {
		this.piCf = piCf;
	}

	private String nomeEnte;
	private String piCf; //Per piCf si intende la sola partita Iva
	
	
}
