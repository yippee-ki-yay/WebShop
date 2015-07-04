package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NamestajiDAO;
import dao.SaloniDAO;
import dao.UslugeDao;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO: refector this shit
		
		PrintWriter out = response.getWriter();
		
		String tekst = request.getParameter("naziv");
		String tip = request.getParameter("tip");
		String cena_od = request.getParameter("cena_od");
		String cena_do = request.getParameter("cena_do");
		String opis = request.getParameter("opis");
		String drzava = request.getParameter("drzava");
		String godina = request.getParameter("godina");
		String boja = request.getParameter("boja");
		String kapacitet_od = request.getParameter("kapacitet_od");
		String kapacitet_do = request.getParameter("kapacitet_do");
		String proizvodjac = request.getParameter("proizvodjac");
		String tipNamestaja = request.getParameter("tipNamestaja");
		
		NamestajiDAO namestaji = (NamestajiDAO)getServletContext().getAttribute("namestaji");
		UslugeDao usluge = (UslugeDao)getServletContext().getAttribute("usluge");
		
		String json = "";
		
		boolean op = false;
		
		if(opis != null)
		if(opis.equals("true"))
			op = true;
		
		double price_min = -1;
		double price_max = -1;
		
		if(cena_od != "" || cena_do != "")
		{
			price_min = Double.parseDouble(cena_od);
			price_max = Double.parseDouble(cena_do);
		}
		
		
		//vidimo da li pretrazujemo usluge ili namestaje
		if(tip != null)
		if(tip.equals("usluge"))
		{
			
			
			json = usluge.search(tekst, price_max, price_min, op);
		}
		else
		{
			
			double kapacitet_min = -1;
			double kapacitet_max = -1;
			
			if(kapacitet_od != "" || kapacitet_do != "")
			{
				kapacitet_min = Double.parseDouble(kapacitet_od);
				kapacitet_max = Double.parseDouble(kapacitet_do);
			}
			
			json = namestaji.search(tekst, price_max, price_min, drzava, godina, boja, kapacitet_min, kapacitet_max, proizvodjac, tipNamestaja);
		}
		
		/*
		//ako ovde imamo nesto znaci da trazim namestaje po nekom salonu
		if(poSalonu != null || poSalonu == "")
		{
			String json = namestaji.searchBySalon(poSalonu);
			out.print(json);
			return;
		}
		
		//ako nema nista u tekst parametru daj mi sve namestaje
		if(tekst == null || tekst == "")
		{
			out.print(namestaji.getJSON());
			return;
		}
		
		String json = namestaji.search(tekst, "name");
		//String json = namestaji.getJSON();*/
		
		out.print(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
