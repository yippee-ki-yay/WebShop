package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UslugeDao;

/**
 * Servlet implementation class IzmeniServlet
 */
@WebServlet("/IzmeniServlet")
public class IzmeniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmeniServlet() {
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
		
		//pokupimo id koji si mi poslao i setujemo trenutni elem koji menjam
		/*String id = request.getParameter("id");
		
		UslugeDao usluge = (UslugeDao) getServletContext().getAttribute("usluge");
		
		String json = usluge.getJSON(id);
		
		System.out.println("izmeniservlet:  " + json);
		
	    getServletContext().setAttribute("curr", json);*/
	}

}
