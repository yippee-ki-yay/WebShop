package model;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class IzvestajSalona 
{
	private String salon;
	private LinkedHashMap<String, String> mapa = new LinkedHashMap<String, String>();
	private String ukupno;
	
	public synchronized String getSalon() {
		return salon;
	}
	public synchronized void setSalon(String salon) {
		this.salon = salon;
	}
	public synchronized HashMap<String, String> getMapa() {
		return mapa;
	}
	public synchronized void setMapa(LinkedHashMap<String, String> mapa) {
		this.mapa = mapa;
	}
	public String getUkupno() {
		return ukupno;
	}
	public void setUkupno(String ukupno) {
		this.ukupno = ukupno;
	}
	
	
}
