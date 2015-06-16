package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KategorijaNamestaja implements Serializable
{

	private String naziv; //jedinstven
	private String opis;
	private KategorijaNamestaja podkategrija;
	
	public KategorijaNamestaja(){}

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

	public KategorijaNamestaja getPodkategrija() {
		return podkategrija;
	}

	public void setPodkategrija(KategorijaNamestaja podkategrija) {
		this.podkategrija = podkategrija;
	}
	
}
