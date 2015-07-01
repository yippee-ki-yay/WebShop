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

import model.KomadNamestaja;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class GenericDAO<T extends Idao>
{
	protected ArrayList<T> items = new ArrayList<T>();
	protected DB db;
	
	public synchronized void save()
	{
		
	}
	
	public synchronized void openDb(String dbName)
	{
		Options options = new Options();
		options.createIfMissing(true);
		
		try {
			db = factory.open(new File(dbName), options);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void add(T novi)
	{
		for(int i = 0; i< items.size();++i)
		{
			if(items.get(i).getId().equals(novi.getId()))
			{
				items.set(i, novi);
				db.put(bytes(novi.getId()), serialize(novi));
				return;
			}
		}
		items.add(novi);
	}
	
	public synchronized T findById(String id)
	{
		for(T n : items)
		{
			if(n.getId().equals(id))
			{
				return n;
			}
		}
		
		return null;
	}
	
	public synchronized ArrayList<T> getItems() {
		return items;
	}

	public  synchronized void setItems(ArrayList<T> namestajiList) {
		this.items = namestajiList;
	}
	
	public synchronized void change(T novi)
	{
		for(T n : items)
		{
			if(n.getId().equals(novi.getId()))
				n = novi;
		}
		
		db.put(bytes(novi.getId()), serialize(novi));
	}
	
	public synchronized boolean remove(String id)
	{
		for(int i = 0; i < items.size(); ++i)
		{
			if(items.get(i).getId().equals(id))
			{
				items.remove(i);
				return true;
			}
		}
			
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void readFile()
	{
		DBIterator iterator = db.iterator();
		try {
		  for(iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
			 
		    T tmp = (T) deserialize(db.get(iterator.peekNext().getKey()));
		    if(tmp == null) return;
		    System.out.println("T: " + tmp.getId());
		    items.add(tmp);
		    
		    
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
	
	public String getJSON()
	{
		ObjectMapper objectMap = new ObjectMapper();
		
		try {
			String namestajiJSON = objectMap.writeValueAsString(items);
			
			return namestajiJSON;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String getJSON(T n)
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
	
	public synchronized byte[] serialize(Object obj)
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
	
	public synchronized Object deserialize(byte[] data)
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
