package it.corso.mercury;

import java.util.Date;

public class Evento {
	private long id_evento;
	private String nome;
	private String descrizione;
	private String linkImmagine;
	private String tipologia;
	private Date dataInizio;
	private Date dataFine;
	private String comune;
	private String provincia;
	private String regione;
	
	public Evento(){}
	public long getId_evento() {
		return id_evento;
	}
	public void setId_evento(long id_evento) {
		this.id_evento = id_evento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getLinkImmagine() {
		return linkImmagine;
	}
	public void setLinkImmagine(String linkImmagine) {
		this.linkImmagine = linkImmagine;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}

	
}
