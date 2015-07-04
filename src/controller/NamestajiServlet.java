package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NamestajiDAO;

/**
 * Servlet implementation class NamestajiServlet
 */
@WebServlet("/NamestajiServlet")
public class NamestajiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NamestajiServlet() {
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
		
		NamestajiDAO namestaji = (NamestajiDAO) getServletContext().getAttribute("namestaji");
		
		String poSalonu = request.getParameter("poSalonu");
		
		if(poSalonu != null || poSalonu == "")
		{
			String json = namestaji.searchBySalon(poSalonu);
			response.getWriter().print(json);
			return;
		}
		
		String json = namestaji.getJSON();
		response.getWriter().print(json);
	}

}
