package model;

import java.io.Serializable;
import java.util.ArrayList;

import dao.Idao;

@SuppressWarnings("serial")
public class Racun implements Serializable, Idao
{
	private ArrayList<Kupovina> items = new ArrayList<Kupovina>();
	private String namestaj_sifra; //Foreign
	private String porez;
	private String ukupnaCena;
	private String datumVreme;
	private String kupac_username; //Foreign
	
	public Racun(){}

	public String getNamestaj() {
		return namestaj_sifra;
	}
	
	public void addItem(Purchasable p, String type)
	{
		items.add(new Kupovina(p.getNaziv(), p.getCena(), p.getKolicina(), p.getNazivSalona()));
	}

	public void setNamestaj(String namestaj) {
		this.namestaj_sifra = namestaj;
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

	public String getKupac() {
		return kupac_username;
	}

	public void setKupac(String kupac) {
		this.kupac_username = kupac;
	}

	@Override
	public String getId() 
	{
		return namestaj_sifra;
	}
	
}
