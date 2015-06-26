package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KorisnikDAO;
import model.Korisnik;
import model.Korisnik.Uloga;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
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
	    PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("user");
		String sifra = request.getParameter("pass");
		
		KorisnikDAO korisnici = (KorisnikDAO)getServletContext().getAttribute("korisnici");
		
		if(korisnici == null)
		{
			korisnici = new KorisnikDAO();
			getServletContext().setAttribute("korisnici", korisnici);
		}
		
		//ako to korisnicko ime i sifra postoje
		Korisnik k = korisnici.postoji(username, sifra, Uloga.PRODAVCI);
		
		if(k != null)
		{
			k.setUlogovan(true);
			//postavi u session naseg korisnika
			request.getSession().setAttribute("korisnik", k);
			pw.print("success");
			System.out.println("PRINT");
		}
		else
		{
			pw.print("fail");
		}
	}

}
