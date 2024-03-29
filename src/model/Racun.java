package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	public void addItem(Purchasable p, String type, String kolicina, String id)
	{
		for(Kupovina k : items)
		{
			if(k.getSifra().equals(id))
			{
				k.setKolicinom(Integer.toString(Integer.parseInt(k.getKolicinom()) + Integer.parseInt(kolicina)));
				k.setCenom((Integer.toString(Integer.parseInt(k.getCenom()) + Integer.parseInt(p.getCena()))));
				k.setKupljen("ne");
				return;
			}
		}
		
		items.add(new Kupovina(p.getNaziv(), p.getCena(), kolicina, p.getNazivSalona(), id, p.getKategorija()));
	}
	
	public boolean checkKolicina(String kolicina, Purchasable p)
	{
		
		return true;
	}
	
	public void removeAll()
	{
		items.clear();
	}
	
	public void setKupljen()
	{
		for(Kupovina k : items)
		{
			k.setKupljen("da");
		}
	}
	
	public void removeItem(String id)
	{
		for(Kupovina k : items)
		{
			if(k.getSifra().equals(id))
			{
				items.remove(k);
				return;
			}
		}
		
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
	
	public String getUkupnaCena(String id) 
	{
		//items.get(id);
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
	

	public synchronized ArrayList<Kupovina> getItems() {
		return items;
	}

	public synchronized void setItems(ArrayList<Kupovina> items) {
		this.items = items;
	}

	@Override
	public String getId() 
	{
		return this.datumVreme + this.ukupnaCena;
	}
	
	
	
}
