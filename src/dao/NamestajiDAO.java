package dao;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;
import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.KategorijaNamestaja;
import model.KomadNamestaja;
import model.Salon;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NamestajiDAO 
{
	
	private ArrayList<KomadNamestaja> namestajiList = new ArrayList<KomadNamestaja>();
	private DB db; //THREAD SAFE?
	
	public NamestajiDAO()
	{
		/*
	    Options options = new Options();
		options.createIfMissing(true);
		
		try {
			db = factory.open(new File("namestaji"), options);	
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		String kat = "stolovi";
		String s = "simpo";
		
		namestajiList.add(new KomadNamestaja("234","Mega astal", "Black", "srbija", "simpo", "20", "2", kat, "1990" ,s, "slika"));
		namestajiList.add(new KomadNamestaja("1344","Sto za komp", "Red", "srbija", "simpo", "33", "2", kat, "1990" ,s, "slika"));
		namestajiList.add(new KomadNamestaja("2342","Lampa", "plava", "White", "simpo", "208", "8", kat, "1990" ,s, "slika"));
		namestajiList.add(new KomadNamestaja("2341","Krevet", "plava", "Black", "simpo", "20", "2", kat, "1990" ,s, "slika"));
		
		
		Options options = new Options();
		options.createIfMissing(true);
		
		try {
			db = factory.open(new File("namestaji"), options);
			
			//DAJEMO SAMO JEDNOM
			
			for(KomadNamestaja n : namestajiList)
			{
				db.put(bytes(n.getSifra()), serialize(n));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//readFile();
		

	}
	
	public synchronized void addNamestaj(KomadNamestaja komad)
	{
		for(int i = 0; i< namestajiList.size();++i)
		{
			if(namestajiList.get(i).getNaziv().equals(komad.getNaziv()))
			{
				namestajiList.set(i, komad);
				db.put(bytes(komad.getSifra()), serialize(komad));
				return;
			}
		}
		namestajiList.add(komad);
		
	}
	
	public synchronized String searchBySalon(String salon)
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		ArrayList<KomadNamestaja> tmpList = new ArrayList<KomadNamestaja>();
		
		for(KomadNamestaja n : namestajiList)
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
	
	public synchronized void izmeniNamestaj(KomadNamestaja komad)
	{
		for(KomadNamestaja n : namestajiList)
		{
			if(n.getSifra().equals(komad.getSifra()))
				n = komad;
		}
		
		db.put(bytes(komad.getSifra()), serialize(komad));
	}
	
	public synchronized boolean izbrisiNamestaj(String sifra)
	{
		
		System.out.println("SIZE: " + namestajiList.size());
		for(int i = 0; i < namestajiList.size(); ++i)
		{
			if(namestajiList.get(i).getSifra().equals(sifra))
			{
				System.out.println("NASAO");
				namestajiList.remove(i);
				db.delete(bytes(sifra));
				return true;
			}
		}
			
		return false;	
	}
	
	
	public synchronized String search(String text, String type)
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		ArrayList<KomadNamestaja> tmpList = new ArrayList<KomadNamestaja>();
		
		for(KomadNamestaja n : namestajiList)
		{
			String tip = n.getNaziv();
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
	public String getJSON()
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		try {
			String namestajiJSON = objectMap.writeValueAsString(namestajiList);
			
			return namestajiJSON;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "";
	}
	
	public String getJSON(KomadNamestaja n)
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		try {
			String namestajiJSON = objectMap.writeValueAsString(n);
			
			return namestajiJSON;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "";
	}
	
	public void readFile()
	{
		DBIterator iterator = db.iterator();
		try {
		  for(iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
			 
		    KomadNamestaja tmp = (KomadNamestaja) deserialize(db.get(iterator.peekNext().getKey()));
		    namestajiList.add(tmp);
		    System.out.println("one");
		  }
		} finally {
		  // Make sure you close the iterator to avoid resource leaks.
		  try {
			iterator.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	public synchronized ArrayList<KomadNamestaja> getNamestajiList() {
		return namestajiList;
	}

	public  synchronized void setNamestajiList(ArrayList<KomadNamestaja> namestajiList) {
		this.namestajiList = namestajiList;
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
	
	private synchronized Object deserialize(byte[] data)
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
