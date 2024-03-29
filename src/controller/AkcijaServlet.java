package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Akcija;
import dao.AkcijeDAO;
import dao.NamestajiDAO;

/**
 * Servlet implementation class AkcijaServlet
 */
@WebServlet("/AkcijaServlet")
public class AkcijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AkcijaServlet() {
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
		
		AkcijeDAO akcije = (AkcijeDAO)getServletContext().getAttribute("akcije");
		NamestajiDAO namestaji = (NamestajiDAO)getServletContext().getAttribute("namestaji");
		
		String buduce = request.getParameter("buducnost");
		
		String json = request.getParameter("json");
		
		Akcija a = akcije.fromJson(json);
		
		if(buduce.equals("false"))
			a.setPopust(namestaji);
		else
			a.setNaziv(namestaji);
			
		akcije.add(a);
		
		//ako do ovde nije prslo sve to je uspeh good enough
		response.getWriter().print("success");
	}

}
