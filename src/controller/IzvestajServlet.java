package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RacuniDAO;
import dao.SaloniDAO;
import dao.TipNamestajaDAO;

/**
 * Servlet implementation class IzvestajPoSalonima
 */
@WebServlet("/IzvestajServlet")
public class IzvestajServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzvestajServlet() {
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
		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");
		String opseg = request.getParameter("op");
		String tip = request.getParameter("tip");
		
		RacuniDAO racuni = (RacuniDAO) getServletContext().getAttribute("racuni");
		TipNamestajaDAO tipovi = (TipNamestajaDAO) getServletContext().getAttribute("tipoviNamestaja");
		SaloniDAO saloni = (SaloniDAO) getServletContext().getAttribute("saloni");
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date startDate = null;
		Date endDate = null;
		Integer op = 0;
		
		try {
			startDate = df.parse(startDateString);
			endDate = df.parse(endDateString);
			op = Integer.parseInt(opseg);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		racuni.racuniTrenutni();
		
		String json = "";
		
		if(tip == "" || tip == null)
			json = racuni.poSalonima(saloni, startDate, endDate, op);
		else
			json = racuni.poKategorijama(startDate, endDate, op, tip, tipovi);
		
		response.getWriter().print(json);
		
	}

}
