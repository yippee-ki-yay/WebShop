package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.KomadNamestaja;
import static org.fusesource.leveldbjni.JniDBFactory.bytes;
import static org.fusesource.leveldbjni.JniDBFactory.*;

import org.iq80.leveldb.Options;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NamestajiDAO extends GenericDAO<KomadNamestaja>
{
	public NamestajiDAO()
	{
		openDb("namestaji");
		
		//addData();
		
		readFile();
	}

	
	public synchronized String searchBySalon(String salon)
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		ArrayList<KomadNamestaja> tmpList = new ArrayList<KomadNamestaja>();
		
		for(KomadNamestaja n : items)
		{
			if(n.getProdajniSalon().equals(salon))
			{
				tmpList.add(n);
			}
		}
		
		if(tmpList.size() > 0)
		{
			try {
				return objectMap.writeValueAsString(tmpList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}
	
	public synchronized String search(String text, String type)
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		ArrayList<KomadNamestaja> tmpList = new ArrayList<KomadNamestaja>();
		
		String tip = "";
		
		for(KomadNamestaja n : items)
		{
			if(type.equals("naziv"))
				tip = n.getNaziv();
			else if(type.equals("godina"))
				tip = n.getGodinaProizvodnje();
			else if(type.equals("zemlja"))
				tip = n.getZemljaProizvodje();
			else if(type.equals("nazivProzvodjaca"))
				tip = n.getNazivProizvodjaca();
			else if(type.equals("boji"))
				tip = n.getBoja();
			else if(type.equals("tipnamestaja"))
				tip = n.getTipNamestaja();
				
			
			if(text.equals(tip))
			{
				tmpList.add(n);
			}
		}
		
		try {
			if(tmpList.size() > 0)
			{
				String json =  objectMap.writeValueAsString(tmpList);
				return json;
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void addData()
	{
		String kat = "stolovi";
		String s = "simpo";
		
		items.add(new KomadNamestaja("234","Mega astal", "Black", "srbija", "simpo", "20", "2", kat, "1990" ,s, "slika"));
		items.add(new KomadNamestaja("1344","Sto za komp", "Red", "srbija", "simpo", "33", "2", kat, "1990" ,s, "slika"));
		items.add(new KomadNamestaja("2342","Lampa", "plava", "White", "simpo", "208", "8", kat, "1990" ,s, "slika"));
		items.add(new KomadNamestaja("2341","Krevet", "plava", "Black", "simpo", "20", "2", kat, "1990" ,s, "slika"));
		
		System.out.println("LEngth: " + items.size());
		
		Options options = new Options();
		options.createIfMissing(true);
		
		try {
			db = factory.open(new File("namestaji"), options);
			
			for(KomadNamestaja n : items)
			{
				db.put(bytes(n.getSifra()), serialize(n));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
