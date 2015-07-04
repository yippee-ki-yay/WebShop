package dao;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;
import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.iq80.leveldb.Options;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.DodatneUsluge;
import model.KategorijaNamestaja;
import model.KomadNamestaja;

public class TipNamestajaDAO extends GenericDAO<KategorijaNamestaja>
{
	
	public TipNamestajaDAO()
	{
		//openDb("tipnamestaja");
		
		addData();
		
		//readFile();
	}

	public void getKategorijeSalona(String salon)
	{
		/*for(KategorijaNamestaja k : items)
		{
			if(k.ge)
		}*/
	}
	
	public boolean hasNamestaj(ArrayList<KomadNamestaja> lista, String kategorija)
	{
		for(KomadNamestaja n : lista)
		{
			if(n.getKategorija().equals(kategorija))
				return false;
		}
		
		return true;
	}
	
	//ako brisemo ovu nasu npr. stolicu, svuda kome je stolica nadlezna moramo nulirati
	public void updateKategorije(String sifra)
	{
		for(KategorijaNamestaja k : items)
		{
			if(k.getPodkategrija().equals(sifra))
			{
				k.setPodkategrija("");
			}
		}
	}
	
	private void addData()
	{
		items.add(new KategorijaNamestaja("kreveti", "legnes na njih da spavas", "null"));
		items.add(new KategorijaNamestaja("stolovi", "ono bas su ok", "null"));
		items.add(new KategorijaNamestaja("ormani", "legnes na njih da spavas", "null"));
		items.add(new KategorijaNamestaja("kompjuterski stolovi", "stavis komp na njih", "stolovi"));
		items.add(new KategorijaNamestaja("fotelje", "sednes uzivas pijes", "null"));
		
		Options options = new Options();
		options.createIfMissing(true);
		
		try {
			db = factory.open(new File("tipnamestaja"), options);
			
			for(KategorijaNamestaja n : items)
			{
				db.put(bytes(n.getId()), serialize(n));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
