package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Korisnik;
import model.Racun;
import dao.RacuniDAO;

/**
 * Servlet implementation class AddRacun
 */
@WebServlet("/AddRacun")
public class AddRacun extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRacun() {
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
		RacuniDAO racuni = (RacuniDAO) getServletContext().getAttribute("racuni");
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		String currDate = format.format(new Date());
		
		k.getKorpa().setDatumVreme(currDate);
		
		Racun r = k.getKorpa();
		
		racuni.add(k.getKorpa());
		//k.getKorpa().removeAll();
	}

}
