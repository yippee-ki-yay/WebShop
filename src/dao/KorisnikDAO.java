package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Korisnik;
import model.Korisnik.Uloga;

/*
 * Posto nemamo registraciju korisnika, dodao sam ja neke korisnike i to upisao u fajl,
 * tako da klasa uvek samo procita serijalizovanu datoteku popuni i koristi te podatke
 * tako da nema potreve da te operacije synchronizujemo
 */

//TODO: cuvaj md5 hasheve ne direktno sifre

public class KorisnikDAO 
{
	private ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
			
	
	public KorisnikDAO()
	{
		listaKorisnika.add(new Korisnik("user", "shone", "shonovic", "user", Uloga.KUPCI, "06323422", "user@gmail.com"));
		listaKorisnika.add(new Korisnik("admin", "petar", "petrovic", "admin", Uloga.PRODAVCI, "06942342", "admin@gmail.com"));
		listaKorisnika.add(new Korisnik("man", "marko", "markovic", "man", Uloga.MENADZERI, "06402342", "man@gmail.com"));
		
		//writeFile();
		readFile();
	}
	
	@SuppressWarnings("unchecked")
	public void readFile()
	{
		
		try {
			
			FileInputStream fileIn = new FileInputStream("korisnici.ser");
			
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			listaKorisnika = (ArrayList<Korisnik>) in.readObject();
			
			in.close();
			fileIn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * ova metoda proverava da li taj korisnik postoji u listi
	 * ako postoji vraca objekat Korisnik
	 */
	public synchronized Korisnik postoji(String username, String sifra, Uloga u)
	{
		for (Korisnik korisnik : listaKorisnika) 
		{
			if(korisnik.getUsername().equals(username) && 
			   korisnik.getSifra().equals(sifra) && korisnik.getUloga() == u)
			{
				return korisnik;
			}
		}
		
		return null;
	}
	
	/*
	 * Serijalizujemo listu korisnika, pozivamo samo jedan da napravimo podatke
	 */
	public void writeFile()
	{	
			try {
				
				FileOutputStream fileOut = new FileOutputStream("korisnici.ser");
				
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				
				out.writeObject(listaKorisnika);
				
				out.close();
				fileOut.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	
	}
	
	private synchronized byte[] serialize(Object obj)
	{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(out);
		    os.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return out.toByteArray();
	}
	
	private  synchronized Object deserialize(byte[] data)
	{
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is;
	    Object o = null;
		try {
			is = new ObjectInputStream(in);
			o = is.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return o;
	}
}
