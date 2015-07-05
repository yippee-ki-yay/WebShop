package model;

import java.io.Serializable;
import java.util.ArrayList;

import dao.Idao;
import dao.NamestajiDAO;

@SuppressWarnings("serial")
public class Akcija implements Serializable, Idao
{
	@SuppressWarnings("unused")
	public static class NamestajPopust implements Serializable
	{
		private String naziv;
		private String procenat;
		private String ime;
		
		public NamestajPopust(){}
		
		public String getNaziv() {
			return naziv;
		}
		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}
		public String getProcenat() {
			return procenat;
		}
		public void setProcenat(String procenat) {
			this.procenat = procenat;
		}

		public String getIme() {
			return ime;
		}

		public void setIme(String ime) {
			this.ime = ime;
		}
		
		
	}
	
	private ArrayList<NamestajPopust> namestaji = new ArrayList<NamestajPopust>();
	private String startDate;
	private String endDate;
	private String salon;
	
	public Akcija(){}
	
	public Akcija(ArrayList<NamestajPopust> namestaji, String startDate,
			String endDate, String salon) {
		super();
		this.namestaji = namestaji;
		this.startDate = startDate;
		this.endDate = endDate;
		this.salon = salon;
	}

	public void setPopust(NamestajiDAO komadi)
	
	{
		for(NamestajPopust popust : namestaji)
		{
			for(KomadNamestaja komad : komadi.getItems())
			{
				if(komad.getId().equals(popust.getNaziv()))
				{
					popust.setIme(komad.getNaziv()); //setujemo ime da mozemo lepo prikazati
					
					komad.setProcenat(popust.getProcenat());
					
					double proc = Double.parseDouble(popust.getProcenat());
					double originalna = Double.parseDouble(komad.getJedinicnaCena());
					
					String novaCena = Double.toString((originalna - ((proc/100)*originalna)));
					
					komad.setJedinicnaCena(novaCena);
					
					komadi.add(komad); //ovo bi trebalo da i u bazi updejtuje nas komad
					
				}
				
			}
		}
	}
	
	public void removePopust(ArrayList<KomadNamestaja> komadi)
	
	{
		for(NamestajPopust popust : namestaji)
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
	
	public synchronized ArrayList<NamestajPopust> getNamestaji() {
		return namestaji;
	}

	public synchronized void setNamestaji(ArrayList<NamestajPopust> namestaji) {
		this.namestaji = namestaji;
	}

	public synchronized String getStartDate() {
		return startDate;
	}

	public synchronized void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public synchronized String getEndDate() {
		return endDate;
	}

	public synchronized void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public synchronized String getSalon() {
		return salon;
	}

	public synchronized void setSalon(String salon) {
		this.salon = salon;
	}

	@Override
	public String getId() {
		// TODO FIX this shit
		return startDate+endDate;
	}
	
	
}
