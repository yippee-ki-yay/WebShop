<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
       
          <c:if test="${!korisnik.isAdminOrManadzer()}">
     	<c:redirect url="index.jsp"></c:redirect>
     </c:if>
    
<!DOCTYPE html>
<html lang="en">

<head>

    <title>Prodavnica namestaja</title>

    <script src="js/jquery.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <script src="js/bootstrap-formhelpers.min.js"></script>
    
     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>
    
     <script src="js/error_check.js"></script>
     <script src="js/webshop/novaAkcija.js"></script>

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
                        <a href="login_test.jsp">Prijava</a>
                    </li>
                    <li>
                        <a href="admin_login.jsp">Admin</a>
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

 		<div class="panel-group">
        <div class="panel panel-info">
         <div class="panel-heading">
        <div class="row">
        <div class="col-md-1 panel-label">
        	<label>Salon: </label>
        </div>
         <div class="col-md-2">
         	<select class="form-control" id="saloni">
         		<option value="default"></option>
         		<c:forEach var="salon" items="${saloni.items}">
        			<option>${salon.naziv}</option>
        		</c:forEach>
        	</select> 
         </div>
              <div class="col-md-2">
             	<div class="checkbox">
  					<label><input type="checkbox" value="" id="check">Po kategorijama</label>
				</div>
           </div>
         
        <div class="col-md-1 panel-label">
        	<label>Pocetak: </label>
        </div>
         <div class="col-md-2">
         	<div class="bfh-datepicker" data-format="d-m-y" id="start_date"></div>
         </div>
         <div class="col-md-1 panel-label">
        	<label>Kraj: </label>
        </div>
         <div class="col-md-2">
          	<div class="bfh-datepicker" data-format="d-m-y" id="end_date"></div>
         </div>
         <div class="col-md-1">
  			<button class="btn btn-primary" id="dodaj">Dodaj</button>
  		</div>
        </div>
        </div>
        
        </div>
        </div>
        </div>

	<div class="container">
		<div class="row podaci">
			

    	</div>
    </div>  

</body>

</html>
    