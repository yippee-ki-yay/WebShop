<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
     <jsp:useBean id="racuni" class="dao.RacuniDAO" scope="application"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">

<head>

    <title>Korisnicka korpa</title>

	
    <script src="js/jquery.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/shop-homepage.css" rel="stylesheet">
    
     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>
     
      <script src="js/webshop/korpa.js"></script>

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
                         <c:if test="${korisnik == null }">
                    <li>
                        <a href="login_test.jsp">Korisnik</a>
                    </li>
                    <li>
                        <a href="admin_login.jsp">Admin</a>
                    </li>
                     <li>
                        <a href="manadzer_login.jsp">Manadzer</a>
                    </li>
                      <c:if test="${korisnik.isAdmin()}">
                     	 <li>
                      		 <a href="admin_panel.jsp">Panel</a>
                   		 </li>
                    </c:if>
                   </c:if>
                   <c:if test="${korisnik != null }">
                        <li> 
                        	<a href="LogoutServlet">Odloguj se</a>
                        </li>
                        </c:if>
                </ul>
                
                    <ul class="nav navbar-nav navbar-right">
                        	<li>
                        		<a href="korpa.jsp" class="korpa">
                        			<img src="img/shop_cart.png"></img>
                        		</a>
                        	</li>
                        </ul>
                
                
            </div>
            <!-- /.navbar-collapse -->
            
            
            
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
		<table class="table table-striped">
    <thead>
      <tr>
        <th>Naziv</th>
        <th>Kolicina</th>
        <th>Cena</th>
         <th>Salon</th>
         <th> </th>
      </tr>
    </thead>
    <tbody id="content">
    <c:forEach var="item" items="${korisnik.korpa.items}">
    <c:if test="${item.kupljen == 'ne' }">
      <tr>
        <td>${item.naziv}</td>
        <td>${item.kolicinom}</td>
        <td>${item.cenom}</td>
        <td>${item.naziv_salona}</td>
        <td> <button class="btn btn-danger otkazi"  id="${item.sifra }" data-id="${item.sifra }">Otkazi kupovinu</button></td>
      </tr>
      </c:if>
    </c:forEach>
    </tbody>
  </table>
  
  	<div class="col-md-2 col-md-offest-8">
  		<button class="btn btn-success" id="racun">Izdaj racun</button>
  	</div>
              
    </div>

</body>

</html>
    