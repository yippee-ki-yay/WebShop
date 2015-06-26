package dao;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.DodatneUsluge;
import model.KategorijaNamestaja;

public class TipNamestajaDAO 
{

	private ArrayList<KategorijaNamestaja> tipNamestajaList = new ArrayList<KategorijaNamestaja>();
	
	public TipNamestajaDAO()
	{
		tipNamestajaList.add(new KategorijaNamestaja("kreveti", "legnes na njih da spavas", "null"));
		tipNamestajaList.add(new KategorijaNamestaja("stolovi", "ono bas su ok", "null"));
		tipNamestajaList.add(new KategorijaNamestaja("ormani", "legnes na njih da spavas", "null"));
		tipNamestajaList.add(new KategorijaNamestaja("kompjuterski stolovi", "stavis komp na njih", "stolovi"));
		tipNamestajaList.add(new KategorijaNamestaja("fotelje", "sednes uzivas pijes", "null"));
	}

	public synchronized ArrayList<KategorijaNamestaja> getTipNamestajaList() {
		return tipNamestajaList;
	}

	public synchronized void setTipNamestajaList(
			ArrayList<KategorijaNamestaja> tipNamestajaList) {
		this.tipNamestajaList = tipNamestajaList;
	}
	
	public synchronized boolean izbrisiTipNamestaja(String naziv)
	{
		for(int i = 0; i < tipNamestajaList.size(); ++i)
		{
			if(tipNamestajaList.get(i).getNaziv().equals(naziv))
			{
				tipNamestajaList.remove(i);
				return true;
			}
		}
			
		return false;	
	}
	
	public synchronized void addTipNamestaja(KategorijaNamestaja tip)
	{
		for(int i = 0; i< tipNamestajaList.size();++i)
		{
			if(tipNamestajaList.get(i).getNaziv().equals(tip.getNaziv()))
			{
				tipNamestajaList.set(i, tip);
				return;
			}
		}
		tipNamestajaList.add(tip);
		
	}
	
	public String getJSON()
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		try {
			String namestajiJSON = objectMap.writeValueAsString(tipNamestajaList);
			
			return namestajiJSON;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String getJSON(KategorijaNamestaja kn)
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		try {
			String namestajiJSON = objectMap.writeValueAsString(kn);
			
			return namestajiJSON;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
