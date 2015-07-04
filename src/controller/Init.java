package controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.KomadNamestaja;
import dao.AkcijeDAO;
import dao.KorisnikDAO;
import dao.NamestajiDAO;
import dao.RacuniDAO;
import dao.SaloniDAO;
import dao.TipNamestajaDAO;
import dao.UslugeDao;

@WebListener
public class Init implements ServletContextListener
{
	private ScheduledExecutorService scheduler;
	
	private AkcijeDAO akcije = new AkcijeDAO();
	private NamestajiDAO namestaji = new NamestajiDAO();
	
	private class UpdateAkcije implements Runnable
	{
		@Override
		public void run() 
		{
			//System.out.println("wut bitch");
			akcije.updateAkcije(namestaji.getItems());
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		//upisemo sve potrebne podatke u app context da mozemo posle koristiti
		
		arg0.getServletContext().setAttribute("korisnici", new KorisnikDAO());
		arg0.getServletContext().setAttribute("namestaji", namestaji);
		arg0.getServletContext().setAttribute("tipoviNamestaja", new TipNamestajaDAO());
		
		arg0.getServletContext().setAttribute("usluge", new UslugeDao());
		arg0.getServletContext().setAttribute("saloni", new SaloniDAO());
		arg0.getServletContext().setAttribute("racuni", new RacuniDAO());
		arg0.getServletContext().setAttribute("akcije", akcije);
		
		//postavimo scheduler da svaki dan updejtuje akcije
		
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new UpdateAkcije(), 0, 1, TimeUnit.HOURS);
		
	}

}
