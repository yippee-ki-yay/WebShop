package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DodatneUsluge implements Serializable
{
	private String naziv; //jedinstven
	private String opis;
	private String cena;
	
	public DodatneUsluge(){}

	public DodatneUsluge(String naziv, String opis, String cena) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
	}

	

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}
	
}
