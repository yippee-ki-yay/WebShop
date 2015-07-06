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
		
		//readFile();
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void  updateAkcije(NamestajiDAO komadi)
	{
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		Date curr = new Date();
		Date endDate = new Date();
		Date startDate = new Date();
		for(Akcija a : items)
		{
			try {
				curr = format.parse(format.format(new Date()));
				endDate = format.parse(a.getEndDate());
				startDate = format.parse(a.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(startDate);
			cal2.setTime(curr);
			boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
			                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
			
			if(sameDay)
				a.setPopust(komadi);
			
		if(curr.after(endDate))
		for(NamestajPopust popust : a.getNamestaji())
		{
			for(KomadNamestaja komad : komadi.getItems())
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
