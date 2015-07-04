package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.NamestajiDAO;
import model.KomadNamestaja;

/**
 * Servlet implementation class AddNamestajServlet
 */
@WebServlet("/AddNamestajServlet")
@MultipartConfig
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
		
		NamestajiDAO namestaji = (NamestajiDAO) getServletContext().getAttribute("namestaji");
		
		PrintWriter pw = response.getWriter();
		
		String sifra = request.getParameter("sifra");
		String naziv = request.getParameter("naziv");
		String zemlja = request.getParameter("zemlja");
		String boja = request.getParameter("boja");
		String proizvodjac = request.getParameter("proizvodjac");
		String cena = request.getParameter("cena");
		
		String kolicina = request.getParameter("kolicina");
		String tip_namestaja = request.getParameter("tip_namestaja");
		String godina = request.getParameter("godina");
		String salon = request.getParameter("salon");
		
		Part filePart = request.getPart("slika");
		String fileName = getFileName(filePart);
		
		InputStream fileContent = filePart.getInputStream();
		
		File path = new File("WebContent/img/");
		
		File file = new File(path, fileName);
		
		try (InputStream input = fileContent) {
		    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		
		KomadNamestaja novi = new KomadNamestaja(sifra, naziv, boja, zemlja, proizvodjac, 
				                   cena, kolicina, tip_namestaja, godina, salon, fileName);
		
		namestaji.add(novi);
		
		getServletContext().getRequestDispatcher("/admin_panel.jsp").forward(request, response);
		
	}
	
	private static String getFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}

}
