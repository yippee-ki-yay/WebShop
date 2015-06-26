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
		
		String tekst = request.getParameter("searchText");
		
		String poSalonu = request.getParameter("poSalonu");
		
		NamestajiDAO namestaji = (NamestajiDAO)getServletContext().getAttribute("namestaji");
		
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
		//String json = namestaji.getJSON();
		
		out.print(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
