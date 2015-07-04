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

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Prodavnica namestaja</title>



    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
     <script src="js/bootstrap-formhelpers.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script>
    	$(document).ready(function()
    	{
		var jup = new Object(${trenutni});
    		
    		$("#sifra").val(jup.sifra);
    		$("#naziv").val(jup.naziv);
    		$("#boja").val(jup.boja);
    		$("#zemlja").val(jup.zemljaProizvodje);
    		$("#proizvodjac").val(jup.nazivProizvodjaca);
    		$("#cena").val(jup.jedinicnaCena);
    		$("#kolicina").val(jup.kolicina);
    		$("#tip_namestaja").val(jup.tipNamestaja);
    		$("#godina").val(jup.godinaProizvodnje);
    		$("#salon").val(jup.prodajniSalon);
    		$("#slika").val("");
    		
    		if(jup.sifra != undefined)
    			$('#sifra').prop('readonly', true);
    		
    		$("#sub").click(function() 
    	    {
    			//popunimo objekat namestaj koji saljemo serveru
    			var namestaj = {};
    			namestaj.sifra = $("#sifra").val();
				namestaj.naziv = $("#naziv").val();
				namestaj.boja = $("#boja").val();
				namestaj.zemljaProizvodje = $("#zemlja").val();
				namestaj.nazivProizvodjaca = $("#proizvodjac").val();
				namestaj.jedinicnaCena = $("#cena").val();
				namestaj.kolicina = $("#kolicina").val();
				namestaj.tipNamestaja = $("#tip_namestaja").val();
				namestaj.godinaProizvodnje = $("#godina").val();
				namestaj.prodajniSalon = $("#salon").val();
				namestaj.putanjaSlike = $("#slika").val();
			
			//prodjemo kroz sve i proverimo da li je popunjeno
			/*if(namestaj == null) return;
			
			for (var prop in namestaj) 
			{
			    if (object.hasOwnProperty(prop)) 
			    {
			       if(prop == "")return;
			    }
			}*/
			
			 $.ajax({
			     url: 'fileUpload',
			     type: 'POST',
			     data: formData,
			     async: false,
			     cache: false,
			     contentType: false,
			     enctype: 'multipart/form-data',
			     processData: false,
			     success: function (response) {
			       alert(response);
			     }
			   });
			
				$.post("AddNamestajServlet", {u: JSON.stringify(namestaj)}, function(data, status) 
				{
					if(data === "success")
					{
						window.location.replace("admin_panel.jsp");
					}
					else
					{
						alert("dis is your falut");
					}
				});
			
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
                        <a href="login_test.jsp">Prijava</a>
                    </li>
                    <li>
                        <a href="admin_login.jsp">Admin</a>
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

     <form>       
    <div class="col-md-4 col-md-offset-4">
       <label for="user">Sifra:</label>
       <input class="form-control" id="sifra"> 
     
    <label for="user">Naziv:</label>
    <input class="form-control" id="naziv"> 
    
    <label for="user">Boja:</label>
    <select class="form-control" id="boja"> 
    	<option>Black</option>
    	<option>White</option>
    	<option>Blue</option>
    	<option>Red</option>
    	<option>Green</option>
    </select>
    
    <label for="user">Zemlja proizvodnje:</label>
    <div class="bfh-selectbox bfh-countries" data-country="US" id="zemlja"></div>
    <label for="user" >Naziv proizvodjaca:</label>
    
    <input class="form-control" id="proizvodjac"> 
    <label for="user">Cena:</label>
    
    <input class="form-control"  id="cena"> 
    <label for="user">Kolicina:</label>
    
    <input class="form-control" id="kolicina"> 
    <label for="user">Tip namestaja:</label>
       <select class="form-control" id="tip_namestaja"> 
    <c:forEach var="tip" items="${tipoviNamestaja.items}">
    <option>${tip.naziv}</option>
    </c:forEach>
    </select>
    
    <label for="user">Godina prozvodnje:</label>
    
    <input class="form-control"  id="godina"> 
    <label for="user">Prodajni salon:</label>
    
    <select class="form-control" id="salon"> 
    <c:forEach var="salon" items="${ saloni.items}">
    <option>${salon.naziv}</option>
    </c:forEach>
    </select>
    <label for="user" >Slika:</label>
    
    <input type="file" accept="image/*" id="slika">
    <button class="btn btn-primary" id="sub">Dodaj</button>
	</div>
	</form>  

    <!-- /.container -->

    <div class="container">

        <hr>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
    