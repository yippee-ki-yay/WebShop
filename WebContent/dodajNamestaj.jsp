<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <c:if test="${!korisnik.isAdmin()}">
     	<c:redirect url="index.jsp"></c:redirect>
  </c:if>
  
    	<c:forEach var="n" items="${namestaji.items}">
  		<c:if test="${n.sifra == param['id']}">
  			<c:set var="trenutni" value="${namestaji.getJSON(n)}"></c:set>
  		</c:if>
  	</c:forEach>

<!DOCTYPE html>
<html lang="en">

<head>

    <title>Dodaj namestaj</title>

    <script src="js/jquery.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">

     <script src="js/bootstrap-formhelpers.min.js"></script>
     
     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>
     
      <script src="js/error_check.js"></script>
      <!--  <script src="js/webshop/dodajNamestaj.js"></script>  -->
      
      <script>
      $(document).ready(function()
    	    	{
    			    var jup = new Object(${trenutni});
    	    		var izmena = false;
    			    
    	    		$("#sifra").val(jup.sifra);
    	    		$("#naziv").val(jup.naziv);
    	    		$("#boja").val(jup.boja);
    	    		$("#zemlja option:selected").text(jup.zemljaProizvodje);
    	    		$("#proizvodjac").val(jup.nazivProizvodjaca);
    	    		$("#cena").val(jup.jedinicnaCena);
    	    		$("#kolicina").val(jup.kolicina);
    	    		$("#tip_namestaja").val(jup.tipNamestaja);
    	    		$("#godina").val(jup.godinaProizvodnje);
    	    		$("#salon").val(jup.prodajniSalon);
    	    		$("#ucitana_slika").val(jup.putanjaSlike);
    	    		
    	    		if(jup.putanjaSlike != undefined)
    	    		{
    	    			$(".slika").text("Slika: " + jup.putanjaSlike + " je ucitana! Mozete dole izmeniti sliku.");
    	    			izmena = true;
    	    		}
    	    		
    	    		if(jup.sifra != undefined)
    	    		{
    	    			$('#sifra').prop('readonly', true);
    	    			izmena = true;
    	    		}
    	    		
    	    		
    	    		$("#sifra").blur(function()
    	    	    {
    	    	    	if(!izmena)
    	    	    	{
    	    	    	   if($("#sifra").val() == "")
    	    	    	    {
    	    	    	    	toastr.error("Morate uneti sifru namestaja");
    	    	    	    	return;
    	    	    	    }
    	    	    	    			
    	    	    	    $.post("CheckUniqueServlet", {tip:"namestaj", id:$("#sifra").val()}, function(data, status)
    	    	    	    {
    	    	    	    	if(data === "not_unique")
    	    	    	    	{
    	    	    	    			toastr.error("Sifra nije jednistven, unesite neki drugi naziv");
    	    	    	    			$("#sifra").focus();
    	    	    	    	}
    	    	    	    });
    	    	    	  }
    	    	     });
    	    		
    	    		$("#otkazi_btn").click(function(e) 
    	    	    {
    	    	    	e.preventDefault();
    	    	    	window.location.replace("admin_panel.jsp");
    	    			return;
    	    	    });
    	    		
    	    		$("form").submit(function()
    	    		{
    	    			if($("#sifra").val() == "")
    	    			{
    	    				toastr.error("morate izabrati sifru");
    	    				$("#sifra").focus();
    	    				return false;
    	    			}
    	    			
    	    			if($("#boja").val() == null)
    	    			{
    	    				toastr.error("morate izabrati boju");
    	    				$("#boja").focus();
    	    				return false;
    	    			}
    	    			
    	    			if($("#salon").val() == null)
    	    			{
    	    				toastr.error("morate izabrati salon");
    	    				$("#salon").focus();
    	    				return false;
    	    			}
    	    			
    	    			if($("#tip_namestaja").val() == null)
    	    			{
    	    				toastr.error("morate izabrati tip namestaja");
    	    				$("#tip_namestaja").focus();
    	    				return false;
    	    			}
    	    			
    	    			if($("#zemlja option:selected").text() == "")
    	    			{
    	    				toastr.error("morate izabrati zemlju proizvodnje");
    	    				$("#zemlja").focus();
    	    				return false;
    	    			}
    	    			
    	    			if($("#slika").val() == "" && izmena == false)
    	    			{
    	    				toastr.error("morate izabrati sliku");
    	    				return false;
    	    			}
    	    			
    	    			if(!numberCheck($("#cena").val(), null, null, "Cena"))
    	    				return false;
    	    			
    	    			if(!numberCheck($("#kolicina").val(), null, null, "Kolicina"))
    	    				return false;
    	    			
    	    			if(!numberCheck($("#godina").val(), 0, 2015, "Godina"))
    	    				return false;
    	    		});
    				
    	    	});
      </script>

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Prodavnica namestaja</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                     <li>
                        <a href="lista.jsp">Namestaji</a>
                    </li>
                     <li>
                        <a href="usluge.jsp">Usluge</a>
                    </li>
                     <c:if test="${!korisnik.isUlogovan()}">
                    <li>
                        <a href="login_test.jsp">Korisnik</a>
                    </li>
                    <li>
                        <a href="admin_login.jsp">Admin</a>
                    </li>
                     <li>
                        <a href="manadzer_login.jsp">Manadzer</a>
                    </li>
                    </c:if>
                       <c:if test="${korisnik.isAdmin()}">
                     	 <li>
                      		 <a href="admin_panel.jsp">Panel</a>
                   		 </li>
                    </c:if>
                     <c:if test="${korisnik.isUlogovan() }">
                        <li> 
                        	<a href="LogoutServlet">Odloguj se</a>
                        </li>
                      </c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

        
    <div class="col-md-4 col-md-offset-4">
    <h2>Dodavanje novog namestaja</h2>
      <form action="AddNamestajServlet" method="post" enctype="multipart/form-data">  
       <label for="user">Sifra:</label>
       <input class="form-control" id="sifra" name="sifra" required /> 
     
    <label for="user">Naziv:</label>
    <input class="form-control" id="naziv" name="naziv" required /> 
    
    <label for="user">Boja:</label>
    <select class="form-control" id="boja" name="boja"> 
    	<option>Black</option>
    	<option>White</option>
    	<option>Blue</option>
    	<option>Red</option>
    	<option>Green</option>
    </select>
    
    <label for="user">Zemlja proizvodnje:</label>
    <select class="bfh-selectbox bfh-countries form-control" id="zemlja" name="zemlja"></select>
    <label for="user" >Naziv proizvodjaca:</label>
    
    <input class="form-control" id="proizvodjac" name="proizvodjac" required /> 
    <label for="user">Cena:</label>
    
    <input class="form-control"  id="cena" name="cena" required /> 
    <label for="user">Kolicina:</label>
    
    <input class="form-control" id="kolicina"  name="kolicina" required /> 
    <label for="user">Tip namestaja:</label>
       <select class="form-control" id="tip_namestaja" name="tip_namestaja"> 
    <c:forEach var="tip" items="${tipoviNamestaja.items}">
    <option>${tip.naziv}</option>
    </c:forEach>
    </select>
    
    <label for="user">Godina prozvodnje:</label>
    
    <input class="form-control"  id="godina" required name="godina" /> 
    <label for="user">Prodajni salon:</label>
    
    <select class="form-control" id="salon" name="salon"> 
    <c:forEach var="salon" items="${saloni.items}">
    <option>${salon.naziv}</option>
    </c:forEach>
    </select>
    <label for="user" >Slika:</label>
    
    <div class="slika"></div>
    <input type="hidden" name="ucitana_slika" id="ucitana_slika" value="">
    
    <input type="file" accept="image/*" id="slika" name="slika" value="Ucitaj">
   
   	<button class="btn btn-primary sub_btn pull-right" id="sub">Dodaj</button>
    <button class="btn btn-danger pull-right otkazi_btn" id="otkazi_btn">Otkazi</button>
   
    </form> 
	</div>

</body>

</html>
    