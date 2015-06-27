package model;

import java.io.Serializable;
import java.util.ArrayList;

import dao.Idao;

@SuppressWarnings("serial")
public class Akcija implements Serializable, Idao
{
	@SuppressWarnings("unused")
	private static class NamestajPopust
	{
		private String naziv;
		private String procenat;
		
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

	public void setPopust(ArrayList<KomadNamestaja> komadi)
	{
		for(NamestajPopust popust : namestaji)
		{
			for(KomadNamestaja komad : komadi)
			{
				if(komad.getId().equals(popust.getNaziv()))
				{
					komad.setProcenat(popust.getProcenat());
					
					int proc = Integer.parseInt(popust.getProcenat());
					int originalna = Integer.parseInt(komad.getJedinicnaCena());
					
					String novaCena = Integer.toString((originalna - ((proc/100)*originalna)));
					System.out.println(novaCena + "Org: " + originalna + "Proc: "+((proc/100)*originalna));
					
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
