package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.NamestajiDAO;
import dao.UslugeDao;
import model.DodatneUsluge;
import model.KomadNamestaja;

/**
 * Servlet implementation class AddNamestajServlet
 */
@WebServlet("/AddNamestajServlet")
public class AddNamestajServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNamestajServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		
		String jsonNamestaj = request.getParameter("u");
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println(jsonNamestaj);
		
		KomadNamestaja namestaj = mapper.readValue(jsonNamestaj, KomadNamestaja.class);
		
		NamestajiDAO namestaji = (NamestajiDAO)getServletContext().getAttribute("namestaji");
		
		namestaji.add(namestaj);
		
		pw.print("success");
		
	}

}
