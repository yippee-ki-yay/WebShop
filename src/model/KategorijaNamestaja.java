package model;

import java.io.Serializable;

import dao.Idao;

@SuppressWarnings("serial")
public class KategorijaNamestaja implements Serializable, Idao
{

	private String naziv; //jedinstven
	private String opis;
	private String podkategrija;
	
	public KategorijaNamestaja(){}

	public KategorijaNamestaja(String naziv, String opis,
			String podkategrija) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.podkategrija = podkategrija;
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

	public String getPodkategrija() {
		return podkategrija;
	}

	public void setPodkategrija(String podkategrija) {
		this.podkategrija = podkategrija;
	}

	@Override
	public String getId() {
		return naziv;
	}
	
}
