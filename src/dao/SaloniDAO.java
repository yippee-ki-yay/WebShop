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

import org.iq80.leveldb.Options;

import model.KategorijaNamestaja;
import model.Salon;


public class SaloniDAO extends GenericDAO<Salon>
{
	
	public SaloniDAO()
	{
		openDb("saloni");
		
		//addData();
		
		readFile();
	}
	
	private void addData()
	{
		items.add(new Salon("ikea", "Futoska 24", "062435232", "ikea@gmail.com", "ikea.com", "2342329874", "2205188723883", "870000000012345600"));
		items.add(new Salon("simpo", "Jevrjeska 34", "060342353", "simpo@gmail.com", "simpo.com", "3467564567", "7305458793863", "200000000065432100"));
		items.add(new Salon("forma ideale", "Ivo andrica 12", "060234267", "forma@gmail.com", "forma.com", "7246349042", "1851787238756", "700000000012785600"));
		
		Options options = new Options();
		options.createIfMissing(true);
		
		try {
			db = factory.open(new File("saloni"), options);
			
			for(Salon n : items)
			{
				db.put(bytes(n.getId()), serialize(n));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
