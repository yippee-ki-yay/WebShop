package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Salon;

public class SaloniDAO 
{
	
	private ArrayList<Salon> listaSalona = new ArrayList<Salon>();
	
	public SaloniDAO()
	{
	
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
