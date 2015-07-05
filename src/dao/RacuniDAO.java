package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.IzvestajSalona;
import model.Kupovina;
import model.Racun;
import model.Salon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RacuniDAO extends GenericDAO<Racun>
{

	public RacuniDAO()
	{
		openDb("racuni");
		
		readFile();
	}
	
	private void addData()
	{

	}
	
	private String dateToString(Date d)
	{
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		Date today = Calendar.getInstance().getTime();        
		String reportDate = df.format(today);
		
		return reportDate;
	}
	
	private String addDay(Date curr, int i)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		c.setTime(curr);
		c.add(Calendar.DATE, i);
		
		return sf.format(c.getTime());
	}
	
	public synchronized String poSalonima(SaloniDAO saloni, Date startDate, Date endDate, int opseg)
	{
		ArrayList<IzvestajSalona> izvestaj = new ArrayList<IzvestajSalona>();
		ObjectMapper objectMap = new ObjectMapper();
		
		/*
		IzvestajSalona simpo = new IzvestajSalona();
		simpo.getMapa().put("3-7-2015", "200");
		simpo.getMapa().put("4-7-2015", "600");
		simpo.getMapa().put("5-7-2015", "100");
		simpo.setSalon("simpo");
		
		IzvestajSalona ikea = new IzvestajSalona();
		ikea.getMapa().put("3-7-2015", "800");
		ikea.getMapa().put("4-7-2015", "0");
		ikea.getMapa().put("5-7-2015", "60");
		ikea.setSalon("ikea");
		
		izvestaj.add(simpo);
		izvestaj.add(ikea);*/
		
		for(Salon s : saloni.getItems())
		{
			IzvestajSalona tmp = new IzvestajSalona();
			tmp.setSalon(s.getNaziv());
			
			double cena_ukupno = 0;
			
			for(int i = 0; i <= opseg; ++i)
			{
				String currDate = addDay(startDate, i);
				
				double cena_racun = 0;
				for(Racun r : items)
				{
					double cena_kup = 0;
					
					if(r.getDatumVreme().equals(currDate))
						for(Kupovina kup : r.getItems())
						{
							
							if(kup.getNaziv_salona().equals(s.getNaziv()))
							{
								double cena = Double.parseDouble(kup.getCenom());
								cena_kup += cena;
							}
						}
					cena_racun += cena_kup;
				}
				
				cena_ukupno += cena_racun;
				tmp.getMapa().put(currDate, Double.toString(cena_ukupno));
				cena_ukupno = 0; //resetujemo
			}
			
			izvestaj.add(tmp);
			
		}
		
		
		String rez = "";
		try {
			rez = objectMap.writeValueAsString(izvestaj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rez;
	}
	
	private ArrayList<Racun> getRacun(String date)
	{
		ArrayList<Racun> racuni = new ArrayList<Racun>();
		for(Racun r : items)
		{
			if(r.getDatumVreme().equals(date))
				racuni.add(r);
		}
		
		return racuni;
	}
	
	//prikaz racuna da vidimo da li su podaci ok
	public void racuniTrenutni()
	{
		/*System.out.println("Koliko imamo racuna u memoriji: " + items.size());
		
		for(Racun r : items)
		{
			System.out.println("Trenutni racun sadrzi: " + r.getItems().size());
			
			for(Kupovina kup: r.getItems())
			{
				System.out.println("Kategorija: " + kup.getKategorija());
			}
			
			
		}*/
	}
	
	public synchronized String poKategorijama(Date startDate, Date endDate, int opseg, String kategorija, TipNamestajaDAO tipovi)
	{
        ArrayList<IzvestajSalona> izvestaj = new ArrayList<IzvestajSalona>();
		ObjectMapper objectMap = new ObjectMapper();
		
		/*IzvestajSalona stolica = new IzvestajSalona();
		stolica.getMapa().put("3-7-2015", "20");
		stolica.getMapa().put("4-7-2015", "6");
		stolica.getMapa().put("5-7-2015", "10");
		stolica.setSalon("stolica");
		stolica.setUkupno("300");
		
		IzvestajSalona krevet = new IzvestajSalona();
		krevet.getMapa().put("3-7-2015", "80");
		krevet.getMapa().put("4-7-2015", "0");
		krevet.getMapa().put("5-7-2015", "6");
		krevet.setSalon("ikea");
		krevet.setUkupno("231");
		
		izvestaj.add(stolica);
		izvestaj.add(krevet);*/
		
			IzvestajSalona tmp = new IzvestajSalona();
			tmp.setSalon(kategorija);
			
			double ukupna_cena = 0;
			
			for(int i = 0; i <= opseg; ++i)
			{
				String currDate = addDay(startDate, i);
				
				double cena = 0;
				int broj = 0;
				
				for(Racun r : items)
				{
					double racun_cena = 0;
					int racun_broj = 0;
					
					if(r.getDatumVreme().equals(currDate))
					for(Kupovina kup : r.getItems())
					{
						//sta ako u jednoj kupovini ima 2 namestaja kupljena iste kategorije?
						if(kup.getKategorija().equals(kategorija))
						{
							double kupovina_cena = Double.parseDouble(kup.getCenom())*Double.parseDouble(kup.getKolicinom());
							int kupovina_broj = Integer.parseInt(kup.getKolicinom());
							racun_cena += kupovina_cena;
							racun_broj += kupovina_broj;
						}
					}
					
					cena += racun_cena;
					broj += racun_broj;
				}
				
				tmp.getMapa().put(currDate, Double.toString(broj));
				
				ukupna_cena += cena;
				
			}
			
			izvestaj.add(tmp);
		    tmp.setUkupno(Double.toString(ukupna_cena));
		
		
		String rez = "";
		try {
			rez = objectMap.writeValueAsString(izvestaj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rez;
	}
}
