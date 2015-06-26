package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DodatneUsluge;
import model.KategorijaNamestaja;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.TipNamestajaDAO;
import dao.UslugeDao;

/**
 * Servlet implementation class AddTipNamestajaServlet
 */
@WebServlet("/AddTipNamestajaServlet")
public class AddTipNamestajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTipNamestajaServlet() {
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
		
		String jsonTip = request.getParameter("u");
		
		ObjectMapper mapper = new ObjectMapper();
		
		KategorijaNamestaja tipNamestaja = mapper.readValue(jsonTip, KategorijaNamestaja.class);
		
		TipNamestajaDAO tipoviNamestaja = (TipNamestajaDAO)getServletContext().getAttribute("tipoviNamestaja");
		
		tipoviNamestaja.add(tipNamestaja);
		
		pw.print("success");
	}

}
