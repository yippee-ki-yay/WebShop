package dao;

import java.io.IOException;
import java.util.ArrayList;

import model.Akcija;
import model.KomadNamestaja;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AkcijeDAO extends GenericDAO<Akcija>
{

	public AkcijeDAO()
	{
		openDb("akcije");
		
		readFile();
	}
	
	
	public Akcija fromJson(String json)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		Akcija a = null;
		try {
			a = (Akcija)mapper.readValue(json, Akcija.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}
	
	
}
