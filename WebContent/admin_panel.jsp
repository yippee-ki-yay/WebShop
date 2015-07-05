<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
     <c:if test="${!korisnik.isAdminOrManadzer()}">
     	<c:redirect url="index.jsp"></c:redirect>
     </c:if>
<!DOCTYPE html>
<html lang="en">

<head>

    <title>Administratoski panel</title>

    <script src="js/jquery.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">

    
     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>

    <link href="css/shop-homepage.css" rel="stylesheet">
     <script src="js/webshop/admin_panel.js"></script>

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
                    <c:if test="${korisnik.isAdminOrManadzer()}">
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

    <!-- Page Content -->
    <div class="container">
    
    	<c:if test="${korisnik.isAdmin()}">
    	<div class="row">
    		<div class="col-md-3 col-md-offset-9 top_part">
    			<select class="form-control selekcija">
    				<option>Namestaji</option>
    				<option>Kategorije namestaja</option>
    				<option>Dodatne usluge</option>
    			</select>
    		</div>
    	</div>
    	

        <div class="row">

            <div class="col-md-3">
                <div class="list-group">
                	<h4>Dodaj</h4>
                    <a href="dodajNamestaj.jsp" class="list-group-item">Namestaj</a>
                    <a href="dodajTipNamestaja.jsp" class="list-group-item">Kategorija namestaja</a>
                    <a href="dodajUslugu.jsp" class="list-group-item">Dodatna usluga</a>
                </div>
                </c:if>
                
                <div class="list-group">
                	<h4>Akcije</h4>
                    <a href="novaAkcija.jsp" class="list-group-item">Nova akcija</a>
                    <a href="prikazAkcija.jsp" class="list-group-item">Prikaz akcija</a>
                </div>
                
                <div class="list-group">
                	<h4>Izvestaj</h4>
                    <a href="izvestajDatumi.jsp" class="list-group-item">Po salonima</a>
                    <a href="izvestajKategorija.jsp" class="list-group-item">Po kategoriji</a>
                </div>
            </div>
           

            <div class="col-md-9">

                <div class="row podaci">
                
                <c:if test="${korisnik.isAdmin()}">
				<c:forEach var="n" items="${namestaji.items}">
                    <div class="col-sm-4 col-lg-4 col-md-4 box" id="${n.sifra}">
                        <div class="thumbnail">
                             <img src="img/${n.putanjaSlike } " alt="">
                            <div class="caption">
                                <h4 class="pull-right">
                             <%--    <c:if test="${n.procenat == ''}">
                                	<span class="popust">Popust :</span>
                                </c:if>
                                --%>
                                
                                $${n.jedinicnaCena }</h4>
                                <h4><a href="#">${ n.naziv}</a>
                                </h4>
                                <p>Proizvodjac: ${n.nazivProizvodjaca}</p>
                                <p>Zemlja porekla: ${n.zemljaProizvodje }</p>
                                <p>Naziv proizvodjaca: ${n.nazivProizvodjaca }</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right"> <button class="btn btn-success btn_padding del" data-id="${n.sifra}">Obrisi</button></p>
                                <button class="btn btn-success btn_padding izm" data-id="${n.sifra}">Izmeni</button>
                            </div>
                        </div>
                    </div>
					</c:forEach>
					</c:if>
                   
                    </div>

                    
                    </div>

                </div>

            </div>

</body>

</html>
    