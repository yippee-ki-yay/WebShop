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
	private KategorijaNamestaja tipNamestaja;
	private String godinaProizvodnje;
	private Salon prodajniSalon;
	private String putanjaSlike;
	
	public KomadNamestaja(){}
	
	

	public KomadNamestaja(String sifra, String naziv, String boja,
			String zemljaProizvodje, String nazivProizvodjaca,
			String jedinicnaCena, String kolicina,
			KategorijaNamestaja tipNamestaja, String godinaProizvodnje,
			Salon prodajniSalon, String putanjaSlike) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.boja = boja;
		this.zemljaProizvodje = zemljaProizvodje;
		this.nazivProizvodjaca = nazivProizvodjaca;
		this.jedinicnaCena = jedinicnaCena;
		Kolicina = kolicina;
		this.tipNamestaja = tipNamestaja;
		this.godinaProizvodnje = godinaProizvodnje;
		this.prodajniSalon = prodajniSalon;
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

	public KategorijaNamestaja getTipNamestaja() {
		return tipNamestaja;
	}

	public void setTipNamestaja(KategorijaNamestaja tipNamestaja) {
		this.tipNamestaja = tipNamestaja;
	}

	public String getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(String godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public Salon getProdajniSalon() {
		return prodajniSalon;
	}

	public void setProdajniSalon(Salon prodajniSalon) {
		this.prodajniSalon = prodajniSalon;
	}

	public String getPutanjaSlike() {
		return putanjaSlike;
	}

	public void setPutanjaSlike(String putanjaSlike) {
		this.putanjaSlike = putanjaSlike;
	}
	
	
	
}
