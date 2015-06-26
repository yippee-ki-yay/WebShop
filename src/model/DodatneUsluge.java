package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DodatneUsluge implements Serializable, Purchasable
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

	
	@Override
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

	@Override
	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	@Override
	public String getKolicina() {
		return "1"; //uvek mozes kupiti jednu uslugu
	}

	@Override
	public String getNazivSalona() {
		// TODO Auto-generated method stub
		return ""; //usluge ne pripadaju salonu ?
	}
	
}
