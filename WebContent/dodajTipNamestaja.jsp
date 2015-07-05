<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <c:if test="${!korisnik.isAdmin()}">
     	<c:redirect url="index.jsp"></c:redirect>
  </c:if>
  
   	<c:forEach var="n" items="${tipoviNamestaja.items}">
  		<c:if test="${n.naziv == param['id']}">
  			<c:set var="trenutni" value="${tipoviNamestaja.getJSON(n)}"></c:set>
  		</c:if>
  	</c:forEach>

<!DOCTYPE html>
<html lang="en">

<head>

    <title>Dodaj tip namestaja</title>

    <script src="js/jquery.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
     <script src="js/bootstrap-formhelpers.min.js"></script>
     
      <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>
     <!--   <script src="js/webshop/dodajTipNamestaja.js"></script>-->
     
     <script>
    	$(document).ready(function()
    	    	{
    				var jup = new Object(${trenutni});
    				var izmena = false;
    	    		
    	    		$("#naziv").val(jup.naziv);
    	    		$("#opis").val(jup.opis);
    	    		$("#podkategorija").val(jup.podkategrija);
    	    		
    	    		if(jup.naziv != undefined)
    	    		{
    	    			$('#naziv').prop('readonly', true);
    	    			izmena = true;
    	    		}
    	    		
    	    		$("#otkazi_btn").click(function(e) 
    	    	    {
    	    	    			e.preventDefault();
    	    	    			window.location.replace("admin_panel.jsp");
    	    					return;
    	    	    });
    	    		
    	    		$("#naziv").blur(function()
    	    	    {
    	    	    	if(!izmena)
    	    	    	{
    	    	    		if($("#naziv").val() == "")
    	    	    		{
    	    	    			toastr.error("Morate uneti naziv kategorije/tipa");
    	    	    			return;
    	    	    		}
    	    	    			
    	    	    		$.post("CheckUniqueServlet", {tip:"kategorija", id:$("#naziv").val()}, function(data, status)
    	    	    		{
    	    	    			if(data === "not_unique")
    	    	    			{
    	    	    					toastr.error("Naziv nije jednistven, unesite neki drugi naziv");
    	    	    					$("#naziv").focus();
    	    	    			}
    	    	    		});
    	    	    	}
    	    	    });
    	    		
    	    		$("#sub").click(function(e) 
    	    		{
    	    			
    	    			 e.preventDefault();
    	    			
    	    			var tip = {};
    	    			tip.naziv = $("#naziv").val();
    	    			tip.opis = $("#opis").val();
    	    			tip.podkategrija = $("#podkategorija").val();
    	    			
    	    			if(tip.naziv == "")
    	    			{
    	    				toastr.error("Morate popuniti naziv");
    	    				$("#naziv").focus();
    	    				return;
    	    			}
    	    			
    	    			if(tip.opis == "")
    	    			{
    	    				toastr.error("Morate popuniti opis");
    	    				$("#opis").focus();
    	    				return;
    	    			}
    	    			
    	    			$.post("AddTipNamestajaServlet", {u: JSON.stringify(tip)}, function(data, status)
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

              
    
   		<div class="col-md-4 col-md-offset-4">
   		<h2>Dodavanje nove kategorije</h2>
   		<form>
  			<label>Naziv</label>
  			<input type="text" class="form-control" id="naziv">
  			
  			<label>Opis</label>
  			<input type="text" class="form-control" id="opis">
  			
  			 <label for="user">Podkategorija: (Opciono)</label>
      		 <select class="form-control" id="podkategorija"> 
      			 <option value=""></option>
    			<c:forEach var="tip" items="${tipoviNamestaja.items}">
   				 <option>${tip.naziv}</option>
    			</c:forEach>
    		 </select>
  			
    		<button class="btn btn-primary sub_btn pull-right" id="sub">Dodaj</button>
    		<button class="btn btn-danger pull-right otkazi_btn" id="otkazi_btn">Otkazi</button>
    		
    		<h4 id="valid"></h4>
    		 </form>
    	</div>

</body>

</html>
    