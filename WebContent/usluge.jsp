<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
        <jsp:useBean id="korisnik" class="model.Korisnik" scope="session"></jsp:useBean>
    
<!DOCTYPE html>
<html lang="en">

<head>

    <title>Usluge</title>

    <script src="js/jquery.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <script src="js/bootstrap-formhelpers.min.js"></script>
    
     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>
    
	 <script src="js/error_check.js"></script>
	  <script src="js/webshop/usluge.js"></script>

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
     <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">

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
                
                <c:if test="${!korisnik.isAdminOrManadzer()}">
                    <ul class="nav navbar-nav navbar-right">
                        	<li>
                        		<a href="korpa.jsp" class="korpa">
                        			<img src="img/shop_cart.png"></img>
                        		</a>
                        	</li>
                        </ul>
                        </c:if>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

 		<div class="panel-group">
       	 <div class="panel panel-info">
         	<div class="panel-heading">
        		<div class="row">
        		<div class="col-md-4 col-md-offset-4">
        			<input type="text" class="form-control" id="searchBox">
         		</div>
       			 <button class="btn btn-primary" id="search">Search</button>
       		 </div>
       		 </div>
       		 
       		 <div class="panel-body dropdown">
           		<div class="col-md-1">
        			<label>Cena od: </label>
        		</div>
        		
        		<div class="col-md-1">
          			<input type="text" class="form-control" id="cena_od">
         		 </div>
         		 
         		<div class="col-md-1">
        			<label>Do: </label>
        		</div>
        		
        		<div class="col-md-1">
          			<input type="text" class="form-control" id="cena_do">
         		</div> 
         		
         		  
              <div class="col-md-2">
             	<div class="checkbox">
  					<label><input type="checkbox" value="" id="check">Po opisu</label>
				</div>
             
         		
         		
       	     </div>
        </div>
        
       
        </div>
        </div>
 

	<div class="namestaji">
	<div class="row">
				<c:forEach var="n" items="${usluge.items}">
                    <div class="col-sm-3 col-lg-3 col-md-3">
                        <div class="thumbnail">
                         <div class="caption_usluge">
                   		<h4 class="pull-right"> $${ n.cena}</h4>
                   		 <h4><a href="#">   ${ n.naziv}</a>
                  		 </h4>
                  		 <p>Opis:  ${ n.opis}</p>
               		 </div>
        				<div class="usluga_btn">
            			<a href="#" class="btn btn-success btn-block kup" role="button" data-id="${n.naziv }">
              								 Kupi
            				</a>
         				</div>
                           
                        </div>
                    </div>
					</c:forEach>

            </div>
            </div>
              

            </div>

</body>

</html>
    