package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Salon implements Serializable
{
	private String naziv;
	private String adresa;
	private String telefon;
	private String email;
	private String adresaSajta;
	private String pib; //Jedinstven, sta je PIB?
	private String maticniBroj; //jedinstven
	private String ziroRacun; //jedinstven
	
	public Salon(){}
	
	public Salon(String naziv, String adresa, String telefon, String emial,
			     String adresaSajta, String pib, String maticniBroj, String ziroRacun)
	{
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = emial;
		this.adresaSajta = adresaSajta;
		this.pib = pib;
		this.maticniBroj = maticniBroj;
		this.ziroRacun = ziroRacun;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresaSajta() {
		return adresaSajta;
	}

	public void setAdresaSajta(String adresaSajta) {
		this.adresaSajta = adresaSajta;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getMaticniBroj() {
		return maticniBroj;
	}

	public void setMaticniBroj(String maticniBroj) {
		this.maticniBroj = maticniBroj;
	}

	public String getZiroRacun() {
		return ziroRacun;
	}

	public void setZiroRacun(String ziroRacun) {
		this.ziroRacun = ziroRacun;
	}
	
}
