package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NamestajiDAO;
import dao.UslugeDao;
import model.DodatneUsluge;
import model.KomadNamestaja;
import model.Korisnik;
import model.Purchasable;

/**
 * Servlet implementation class KupovinaServlet
 */
@WebServlet("/KupovinaServlet")
public class KupovinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KupovinaServlet() {
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
		
		Korisnik trenutni = (Korisnik)request.getSession().getAttribute("korisnik");
		
		NamestajiDAO namestaji = (NamestajiDAO) getServletContext().getAttribute("namestaji");
		UslugeDao usluge = (UslugeDao) getServletContext().getAttribute("usluge");
		
		String type = request.getParameter("type");
		String id = request.getParameter("sifra");
		String kolicina = request.getParameter("kolicina");
		String dodatna_usluga = request.getParameter("dodatna_usluga");
		
		Purchasable p;
		
		//ako hoce korisnik i dodatnu uslugu uz namestaj
		if(dodatna_usluga != null)
		{
			p = usluge.findById(dodatna_usluga);
			if(p != null)
				trenutni.getKorpa().addItem(p, "usluga", "1", dodatna_usluga);
			return;
		}
		
		if(trenutni.isUlogovan() == false)
		{
			response.getWriter().print("nije_registrovan");
			return;
		}
		
		if(trenutni.isAdminOrManadzer() == true)
		{
			response.getWriter().print("admin_manadzer");
			return;
		}
		
		if(type.equals("Namestaj"))
		{
			p = namestaji.findById(id);
			
			if(Integer.parseInt(p.getKolicina()) - Integer.parseInt(kolicina)  < 0)
			{
				response.getWriter().print("previse");
				return;
			}
			
			DodatneUsluge du = namestaji.checkUslugu(usluge, id);
			
			if(du != null)
			{
				response.getWriter().print("ima" + du.getNaziv());
				trenutni.getKorpa().addItem(p, type, kolicina, id);
				return;
			}
			
			
			namestaji.smanjiKolicinu(id, kolicina);
			
		}
		else
		{
			String namestaj = usluge.findById(id).getNamestaj();
			if(namestaj != "")
			{
				response.getWriter().print("povezana" + namestaj);
				return;
			}
			p = usluge.findById(id);
			
		}
		
		
		
		trenutni.getKorpa().addItem(p, type, kolicina, id);

		response.getWriter().print("success");
		
		
	}

}
