package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TipNamestajaDAO;

/**
 * Servlet implementation class TipNamestajaServlet
 */
@WebServlet("/TipNamestajaServlet")
public class TipNamestajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipNamestajaServlet() {
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
		
		TipNamestajaDAO tipoviNamestaja = (TipNamestajaDAO)getServletContext().getAttribute("tipoviNamestaja");
		
		String salon = request.getParameter("salon");
		
		if(salon != null || salon != "")
		{
			tipoviNamestaja.getKategorijeSalona(salon);
		}
		
		//vratimo stranici json array svih tipova namestaja
		response.getWriter().print(tipoviNamestaja.getJSON());
		
	}

}
