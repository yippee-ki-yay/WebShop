package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Kupovina implements Serializable
{
	private String naziv;
	private String cenom;
	private String kolicinom;
	private String naziv_salona;
	private String sifra;
	
	public Kupovina(String naziv_namestaja, String cenom, String kolicinom,
			String naziv_salona, String sifra) {
		super();
		this.naziv = naziv_namestaja;
		this.cenom = cenom;
		this.kolicinom = kolicinom;
		this.naziv_salona = naziv_salona;
		this.sifra = sifra;
	}
	public synchronized String getNaziv() {
		return naziv;
	}
	public synchronized void setNaziv(String naziv_namestaja) {
		this.naziv = naziv_namestaja;
	}
	public synchronized String getCenom() {
		return cenom;
	}
	public synchronized void setCenom(String cenom) {
		this.cenom = cenom;
	}
	public synchronized String getKolicinom() {
		return kolicinom;
	}
	public synchronized void setKolicinom(String kolicinom) {
		this.kolicinom = kolicinom;
	}
	public synchronized String getNaziv_salona() {
		return naziv_salona;
	}
	public synchronized void setNaziv_salona(String naziv_salona) {
		this.naziv_salona = naziv_salona;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
}
