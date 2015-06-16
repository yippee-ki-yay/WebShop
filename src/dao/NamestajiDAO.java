package dao;

import java.util.ArrayList;

import model.KategorijaNamestaja;
import model.KomadNamestaja;
import model.Salon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NamestajiDAO 
{
	
	private ArrayList<KomadNamestaja> namestajiProba = new ArrayList<KomadNamestaja>();
	
	public NamestajiDAO()
	{
		KategorijaNamestaja kat = new KategorijaNamestaja();
		Salon s = new Salon();
		
		namestajiProba.add(new KomadNamestaja("234","Mega astal", "plava", "srbija", "simpo", "20", "2", kat, "1990" ,s, "slika"));
		namestajiProba.add(new KomadNamestaja("234","Sto za komp", "plava", "srbija", "simpo", "33", "2", kat, "1990" ,s, "slika"));
		namestajiProba.add(new KomadNamestaja("234","Lampa", "plava", "srbija", "simpo", "208", "8", kat, "1990" ,s, "slika"));
		namestajiProba.add(new KomadNamestaja("234","Krevet", "plava", "srbija", "simpo", "20", "2", kat, "1990" ,s, "slika"));

	}
	
	public synchronized void addNamestaj(KomadNamestaja komad)
	{
		namestajiProba.add(komad);
	}
	
	public String getJSON()
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		try {
			String namestajiJSON = objectMap.writeValueAsString(namestajiProba);
			
			return namestajiJSON;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "";
	}

	public synchronized ArrayList<KomadNamestaja> getNamestajiProba() {
		return namestajiProba;
	}

	public  synchronized void setNamestajiProba(ArrayList<KomadNamestaja> namestajiProba) {
		this.namestajiProba = namestajiProba;
	}
	
	
	
}
