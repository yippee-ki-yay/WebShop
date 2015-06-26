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
	
	private ArrayList<Salon> saloniLista = new ArrayList<Salon>();
	
	public SaloniDAO()
	{
		saloniLista.add(new Salon("ikea", "Futoska 24", "062435232", "ikea@gmail.com", "ikea.com", "2342329874", "2205188723883", "870000000012345600"));
		saloniLista.add(new Salon("simpo", "Jevrjeska 34", "060342353", "simpo@gmail.com", "simpo.com", "3467564567", "7305458793863", "200000000065432100"));
		saloniLista.add(new Salon("forma ideale", "Ivo andrica 12", "060234267", "forma@gmail.com", "forma.com", "7246349042", "1851787238756", "700000000012785600"));

	}
	
	public synchronized ArrayList<Salon> getSaloniLista() {
		return saloniLista;
	}



	public synchronized void setSaloniLista(ArrayList<Salon> saloniLista) {
		this.saloniLista = saloniLista;
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
