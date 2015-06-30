package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NamestajiDAO;
import dao.UslugeDao;
import model.DodatneUsluge;
import model.KomadNamestaja;
import model.Korisnik;
import model.Purchasable;

/**
 * Servlet implementation class KupovinaServlet
 */
@WebServlet("/KupovinaServlet")
public class KupovinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KupovinaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Korisnik trenutni = (Korisnik)request.getSession().getAttribute("korisnik");
		
		NamestajiDAO namestaji = (NamestajiDAO) getServletContext().getAttribute("namestaji");
		UslugeDao usluge = (UslugeDao) getServletContext().getAttribute("usluge");
		
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String kolicina = request.getParameter("kolicina");
		
		Purchasable p;
		
		if(type.equals("Namestaj"))
		{
			p = namestaji.findById(id);
		}
		else
		{
			p = usluge.findById(id);
		}
		
		trenutni.getKorpa().addItem(p, type, kolicina);
		
	}

}
