package dao;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.DodatneUsluge;
import model.KomadNamestaja;

public class UslugeDao 
{
	private ArrayList<DodatneUsluge> uslugeList = new ArrayList<DodatneUsluge>();
	
	//TODO: ubaci trajno cuvanje podataka
	
	public UslugeDao()
	{
		uslugeList.add(new DodatneUsluge("prevoz", "dovozimo namestaj do vase lokacije", "300"));
		uslugeList.add(new DodatneUsluge("ugradnja", "sklopimo namestaj i ugradimo ga na licu mesta", "700"));
		uslugeList.add(new DodatneUsluge("samaranje", "bacimo par samara", "100"));
		uslugeList.add(new DodatneUsluge("resturacija", "obnova starog namestaja", "1000"));
	}
	
	public synchronized void addUsluge(DodatneUsluge komad)
	{
		for(int i = 0; i< uslugeList.size();++i)
		{
			if(uslugeList.get(i).getNaziv().equals(komad.getNaziv()))
			{
				uslugeList.set(i, komad);
				return;
			}
		}
		uslugeList.add(komad);
		
	}
	
	public synchronized boolean izbrisiUslugu(String naziv)
	{
		for(int i = 0; i < uslugeList.size(); ++i)
		{
			if(uslugeList.get(i).getNaziv().equals(naziv))
			{
				uslugeList.remove(i);
			//	db.delete(bytes(sifra));
				return true;
			}
			
		}
		
		return false;
	}
	
	public synchronized ArrayList<DodatneUsluge> getUslugeList() {
		return uslugeList;
	}

	public synchronized void setUslugeList(ArrayList<DodatneUsluge> uslugeList) {
		this.uslugeList = uslugeList;
	}

	public String getJSON()
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		try {
			String namestajiJSON = objectMap.writeValueAsString(uslugeList);
			
			return namestajiJSON;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return "";
	}
	
	public String getJSON(DodatneUsluge d)
	{
		ObjectMapper objectMap = new ObjectMapper();
		
				try {
					String namestajiJSON = objectMap.writeValueAsString(d);
					
					return namestajiJSON;
					
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return "";
	}
	
}
