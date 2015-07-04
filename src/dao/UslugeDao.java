package dao;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;
import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.iq80.leveldb.Options;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.DodatneUsluge;
import model.KomadNamestaja;

public class UslugeDao extends GenericDAO<DodatneUsluge>
{
	
	public UslugeDao()
	{
		//openDb("usluge");
		
		addData();
		
		//readFile();
	}
	
	
	private void addData()
	{
		items.add(new DodatneUsluge("prevoz", "dovozimo namestaj do vase lokacije", "300"));
		items.add(new DodatneUsluge("ugradnja", "sklopimo namestaj i ugradimo ga na licu mesta", "700"));
		items.add(new DodatneUsluge("samaranje", "bacimo par samara", "100"));
		items.add(new DodatneUsluge("resturacija", "obnova starog namestaja", "1000"));
		
		Options options = new Options();
		options.createIfMissing(true);
		
		try {
			db = factory.open(new File("usluge"), options);
			
			for(DodatneUsluge n : items)
			{
				db.put(bytes(n.getId()), serialize(n));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String search(String text, double price_max, double price_min, boolean opis)
	{
		ArrayList<DodatneUsluge> tmpList = new ArrayList<DodatneUsluge>();
		ObjectMapper objectMap = new ObjectMapper();
		
		for(DodatneUsluge u : items)
		{
			boolean condition = false;
			
			if(price_max != -1 && price_min != -1)
			{	
				double cena = Double.parseDouble(u.getCena());
				
				if(cena >= price_min && cena <= price_max)
				{
					condition = true;
				}
			}
			
		
			if(text != "")
			{
				if(u.getNaziv().contains(text))
					condition = true;
				else
					condition = false;
				
				if(opis)
				if(u.getOpis().contains(text))
					condition = true;
					
				
				
			}
			
				if(condition)
					tmpList.add(u);
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
	
}
