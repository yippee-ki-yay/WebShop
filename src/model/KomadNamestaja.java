package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KomadNamestaja implements Serializable
{

	private String sifra; //jedinstven
	private String naziv;
	private String boja;
	private String zemljaProizvodje;
	private String nazivProizvodjaca;
	private String jedinicnaCena;
	private String Kolicina;
	private String tipNamestaja_naziv; //Foreign
	private String godinaProizvodnje;
	private String prodajniSalon_pib; //Foreign
	private String putanjaSlike;
	
	public KomadNamestaja(){}
	
	

	public KomadNamestaja(String sifra, String naziv, String boja,
			String zemljaProizvodje, String nazivProizvodjaca,
			String jedinicnaCena, String kolicina,
			String tipNamestaja, String godinaProizvodnje,
			String prodajniSalon, String putanjaSlike) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.boja = boja;
		this.zemljaProizvodje = zemljaProizvodje;
		this.nazivProizvodjaca = nazivProizvodjaca;
		this.jedinicnaCena = jedinicnaCena;
		Kolicina = kolicina;
		this.tipNamestaja_naziv = tipNamestaja;
		this.godinaProizvodnje = godinaProizvodnje;
		this.prodajniSalon_pib = prodajniSalon;
		this.putanjaSlike = putanjaSlike;
	}



	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public String getZemljaProizvodje() {
		return zemljaProizvodje;
	}

	public void setZemljaProizvodje(String zemljaProizvodje) {
		this.zemljaProizvodje = zemljaProizvodje;
	}

	public String getNazivProizvodjaca() {
		return nazivProizvodjaca;
	}

	public void setNazivProizvodjaca(String nazivProizvodjaca) {
		this.nazivProizvodjaca = nazivProizvodjaca;
	}

	public String getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(String jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public String getKolicina() {
		return Kolicina;
	}

	public void setKolicina(String kolicina) {
		Kolicina = kolicina;
	}

	public String getTipNamestaja() {
		return tipNamestaja_naziv;
	}

	public void setTipNamestaja(String tipNamestaja) {
		this.tipNamestaja_naziv = tipNamestaja;
	}

	public String getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(String godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public String getProdajniSalon() {
		return prodajniSalon_pib;
	}

	public void setProdajniSalon(String prodajniSalon) {
		this.prodajniSalon_pib = prodajniSalon;
	}

	public String getPutanjaSlike() {
		return putanjaSlike;
	}

	public void setPutanjaSlike(String putanjaSlike) {
		this.putanjaSlike = putanjaSlike;
	}
	
	
	
}
