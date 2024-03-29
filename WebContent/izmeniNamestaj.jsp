<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="saloni" class="dao.SaloniDAO" scope="application"></jsp:useBean>
<jsp:useBean id="tipnamestaja" class="dao.TipNamestajaDAO" scope="application"></jsp:useBean>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <a href="lista.jsp">Pretrazi</a>
                    </li>
                    <li>
                        <a href="login_test.html">Prijava</a>
                    </li>
                    <li>
                        <a href="admin_login.html">Admin</a>
                    </li>
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

              
        <form action="AddNamestajServlet" method="post">
       <div class="col-md-4 col-md-offset-4">
    <label for="user"><h2>Naziv:</h2></label>
    <input class="form-control" name="naziv"> 
    
    <label for="user"><h2>Boja:</h2></label>
    <select class="form-control" name="boja"> 
    	<option>Black</option>
    	<option>White</option>
    	<option>Blue</option>
    	<option>Red</option>
    	<option>Green</option>
    </select>
    
    <label for="user"><h2>Zemlja proizvodnje:</h2></label>
    <div class="bfh-selectbox bfh-countries" data-country="US" name="zemlja"></div>
    <label for="user" ><h2>Naziv proizvodjaca:</h2></label>
    
    <input class="form-control" name="proizvodjac"> 
    <label for="user" name="cena"><h2>Cena:</h2></label>
    
    <input class="form-control"  name="cena"> 
    <label for="user" name="kolicina"><h2>Kolicina:</h2></label>
    
    <input class="form-control" name="kolicina"> 
    <label for="user"><h2>Tip namestaja:</h2></label>
       <select class="form-control" name="tip_namestaja"> 
    <c:forEach var="tip" items="${tipnamestaja.items}">
    <option>${tip.key}</option>
    </c:forEach>
    </select>
    
    <label for="user"><h2>Godina prozvodnje:</h2></label>
    
    <input class="form-control"  name="godina"> 
    <label for="user"><h2>Prodajni salon:</h2></label>
    
    <select class="form-control" name="salon"> 
    <c:forEach var="salon" items="${ saloni.items}">
    <option>${salon.naziv}</option>
    </c:forEach>
    </select>
    <label for="user" ><h2>Slika:</h2></label>
    
    <input type="file" accept="image/*" name="slika">
    <button type="submit" class="btn btn-primary" id="sub">Dodaj</button>
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
    