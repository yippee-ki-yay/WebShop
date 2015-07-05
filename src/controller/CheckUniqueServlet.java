package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NamestajiDAO;
import dao.TipNamestajaDAO;
import dao.UslugeDao;

/**
 * Servlet implementation class CheckUniqueServlet
 */
@WebServlet("/CheckUniqueServlet")
public class CheckUniqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUniqueServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String tip = request.getParameter("tip");
		String id = request.getParameter("id");
		
		if(tip != null && id != null)
		if(tip.equals("namestaj"))
		{
			NamestajiDAO namestaji = (NamestajiDAO) getServletContext().getAttribute("namestaji");
			
			if(namestaji.findById(id) != null)
			{
				response.getWriter().print("not_unique");
				return;
			}
		}
		else if(tip.equals("usluga"))
		{
			UslugeDao usluge = (UslugeDao) getServletContext().getAttribute("usluge");
			
			if(usluge.findById(id) != null)
			{
				response.getWriter().print("not_unique");
				return;
			}
		}
		else if(tip.equals("kategorija"))
		{
			TipNamestajaDAO tipovi = (TipNamestajaDAO) getServletContext().getAttribute("tipoviNamestaja");
			
			if(tipovi.findById(id) != null)
			{
				response.getWriter().print("not_unique");
				return;
			}
		}
	}

}
