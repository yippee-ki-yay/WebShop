<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="tipoviNamestaja" class="dao.TipNamestajaDAO" scope="application"></jsp:useBean>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <c:if test="${!korisnik.isAdmin()}">
     	<c:redirect url="index.jsp"></c:redirect>
  </c:if>
  
  	<c:forEach var="n" items="${usluge.items}">
  		<c:if test="${n.naziv == param['id']}">
  			<c:set var="trenutni" value="${usluge.getJSON(n)}"></c:set>
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

	     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>


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
    		
    		$("#naziv").val(jup.naziv);
    		$("#opis").val(jup.opis);
    		$("#cena").val(jup.cena);
    		
    		if(jup.naziv != undefined)
    			$('#naziv').prop('readonly', true);
    		
    		$("#sub").click(function() 
    		{
    			var usluga = {};
    			usluga.naziv = $("#naziv").val();
    			usluga.opis = $("#opis").val();
    			usluga.cena = $("#cena").val();
    			
    			if(usluga.naziv == "" || usluga.opis == "" || usluga.cena == "")
    			{
    				$("#valid").text("Fill all the fields");
    				return;
    			}
    			
    			$.post("AddUsluguServlet", {u: JSON.stringify(usluga)}, function(data, status)
    			{
    				console.log("wtf!");
    				
    				if(data === "success")
    				{
    				
    					window.location.replace("admin_panel.jsp");
    					return;
    				}
    				else
    				{
    					toastr.error("morate biti korisnik da mozete izvrsiti kupovinu");
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
                      
                      <li>
                      	<a href="">${ param['id']}</a>
                      </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

              
    
   		<div class="col-md-4 col-md-offset-4">
   		<form>
  			<label>Naziv</label>
  			<input type="text" class="form-control" id="naziv" value="asas" required />
  			
  			<label>Opis</label>
  			<input type="text" class="form-control" id="opis" required />
  			
  			<label>Cena</label>
  			<input type="text" class="form-control" id="cena" required />
  			
    		<button class="btn btn-primary" id="sub">Dodaj</button>
    		</form>
    		<h4 id="valid"></h4>
    		
    	</div>
    
   


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
    