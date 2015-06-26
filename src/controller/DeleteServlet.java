package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NamestajiDAO;
import dao.TipNamestajaDAO;
import dao.UslugeDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		
		NamestajiDAO namestaji = (NamestajiDAO)getServletContext().getAttribute("namestaji");
		UslugeDao usluge = (UslugeDao)getServletContext().getAttribute("usluge");
		TipNamestajaDAO tipoviNamestaja = (TipNamestajaDAO)getServletContext().getAttribute("tipoviNamestaja");
		
		String sifra = request.getParameter("sifra");
		String type = request.getParameter("type");
		
		if(type.equals("Namestaji"))
		{
			if(namestaji.remove(sifra))
			{
				pw.print("success");
			}
			else
			{
				pw.print("fail");
			}
		}
		else if(type.equals("Dodatne usluge"))
		{
			if(usluge.remove(sifra))
			{
				pw.print("success");
			}
			else
			{
				pw.print("fail");
			}
		}
		else
		{
			if(tipoviNamestaja.remove(sifra))
			{
				pw.print("success");
			}
			else
			{
				pw.print("fail");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
