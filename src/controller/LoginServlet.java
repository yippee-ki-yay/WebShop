package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Korisnik;
import model.Korisnik.Uloga;
import dao.KorisnikDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//not thread safe
	KorisnikDAO korisnici = new KorisnikDAO();
	    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String sifra = request.getParameter("sifra");
		
		//ako to korisnicko ime i sifra postoje
		Korisnik k = korisnici.postoji(username, sifra, Uloga.KUPCI);
		
		if(k != null)
		{
			//postavi u session naseg korisnika
			request.getSession().setAttribute("korisnik", k);
		}
		
		//prebaci korisnika na pocetnu stranu
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
