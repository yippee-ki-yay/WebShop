package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NamestajiDAO;
import model.KomadNamestaja;

/**
 * Servlet implementation class AddNamestajServlet
 */
@WebServlet("/AddNamestajServlet")
public class AddNamestajServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNamestajServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//pokupimo sve podatke i napravimo novi namestaj, ocekujem da js radi validaciju
		
		//sad je sve string, eventualno posle prebacuj u druge podatke
		KomadNamestaja noviKomad = new KomadNamestaja();
		noviKomad.setNaziv(request.getParameter("naziv"));
		noviKomad.setBoja(request.getParameter("boja"));
		noviKomad.setZemljaProizvodje(request.getParameter("zemlja"));
		noviKomad.setNazivProizvodjaca(request.getParameter("proizvodjac"));
		noviKomad.setJedinicnaCena(request.getParameter("cena"));
		noviKomad.setKolicina(request.getParameter("kolicina"));
		/*noviKomad.setNaziv(request.getParameter("tip_namestaja"));
		noviKomad.setNaziv(request.getParameter("godina"));
		noviKomad.setNaziv(request.getParameter("salon"));
		noviKomad.setNaziv(request.getParameter("slika"));*/
		
		NamestajiDAO n = (NamestajiDAO) getServletContext().getAttribute("namestaji");
		
		n.addNamestaj(noviKomad);
		
		getServletContext().setAttribute("namestaji", n);
		
		getServletContext().getRequestDispatcher("/lista.jsp").forward(request, response);
		
	}

}
