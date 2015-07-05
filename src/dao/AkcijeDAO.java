package dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Akcija;
import model.Akcija.NamestajPopust;
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
	
	public synchronized void  updateAkcije(ArrayList<KomadNamestaja> komadi)
	{
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		Date curr = new Date();
		Date endDate = new Date();
		
		for(Akcija a : items)
		{
			try {
				curr = format.parse(format.format(new Date()));
				endDate = format.parse(a.getEndDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(curr.after(endDate))
		for(NamestajPopust popust : a.getNamestaji())
		{
			for(KomadNamestaja komad : komadi)
			{
				if(komad.getId().equals(popust.getNaziv()))
				{
					double proc = Double.parseDouble(popust.getProcenat());
					double procTrenutno = Double.parseDouble(komad.getProcenat());
					
					String uklonjenProcenat = Double.toString(procTrenutno - proc);
					
					komad.setProcenat(uklonjenProcenat);
					
					if((procTrenutno - proc) == 0)
					{
						komad.setJedinicnaCena(komad.getOriginalnaCena());
						continue;
					}
					
					double originalna = Double.parseDouble(komad.getOriginalnaCena());
					
					String novaCena = Double.toString((originalna / (1 - (procTrenutno - proc))));
					
					komad.setJedinicnaCena(novaCena);
				}
			}
		}
		}
			
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
