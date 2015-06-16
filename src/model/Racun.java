package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Racun implements Serializable
{
	private KomadNamestaja namestaj;
	private String porez;
	private String ukupnaCena;
	private String datumVreme;
	private Korisnik kupac;
	
	public Racun(){}

	public KomadNamestaja getNamestaj() {
		return namestaj;
	}

	public void setNamestaj(KomadNamestaja namestaj) {
		this.namestaj = namestaj;
	}

	public String getPorez() {
		return porez;
	}

	public void setPorez(String porez) {
		this.porez = porez;
	}

	public String getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(String ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}

	public Korisnik getKupac() {
		return kupac;
	}

	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}
	
}
