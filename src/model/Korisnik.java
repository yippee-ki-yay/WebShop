package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Korisnik implements Serializable
{
	
	public enum Uloga {KUPCI, MENADZERI, PRODAVCI};
	
	private String username; //jedinstveno
	private String ime;
	private String prezime;
	private String sifra;
	private Uloga uloga;
	private String telefon;
	private String email;
	private Racun korpa;
	
	private boolean ulogovan = false;
	
	public Korisnik(){}
	
	public Korisnik(String username, String ime, String prezime, String sifra,
			        Uloga uloga, String telefon, String email)
	{
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.sifra = sifra;
		this.uloga = uloga;
		this.telefon = telefon;
		this.email = email;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
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

	public boolean isUlogovan() {
		return ulogovan;
	}

	public void setUlogovan(boolean ulogovan) {
		this.ulogovan = ulogovan;
	}
	
	public boolean isAdmin()
	{
		if(ulogovan && uloga == Uloga.PRODAVCI)
			return true;
		
		return false;
	}
	
	
	public boolean isManadzer()
	{
		if(ulogovan && uloga == Uloga.MENADZERI)
			return true;
		
		return false;
	}

	public Racun getKorpa() {
		return korpa;
	}

	public void setKorpa(Racun korpa) {
		this.korpa = korpa;
	}
	
}
