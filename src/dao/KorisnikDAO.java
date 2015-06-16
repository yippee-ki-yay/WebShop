package dao;

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
		/*listaKorisnika.add(new Korisnik("yolo", "shone", "shonovic", "1234", Uloga.KUPCI, "0642342", "yolo@gmail.com"));
		listaKorisnika.add(new Korisnik("yolo", "shone", "shonovic", "1234", Uloga.KUPCI, "0642342", "yolo@gmail.com"));
		listaKorisnika.add(new Korisnik("yolo", "shone", "shonovic", "1234", Uloga.KUPCI, "0642342", "yolo@gmail.com"));
		listaKorisnika.add(new Korisnik("yolo", "shone", "shonovic", "1234", Uloga.KUPCI, "0642342", "yolo@gmail.com"));*/
		
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
}
