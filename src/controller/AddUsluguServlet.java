package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DodatneUsluge;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UslugeDao;

/**
 * Servlet implementation class AddUsluguServlet
 */
@WebServlet("/AddUsluguServlet")
public class AddUsluguServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUsluguServlet() {
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
		
		String jsonUsluga = request.getParameter("u");
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println(jsonUsluga);
		
		DodatneUsluge usluga = mapper.readValue(jsonUsluga, DodatneUsluge.class);
		
		UslugeDao usluge = (UslugeDao)getServletContext().getAttribute("usluge");
		
		usluge.addUsluge(usluga);
		
		//getServletContext().setAttribute("usluge", usluga);
		
		pw.print("success");
	}

}
