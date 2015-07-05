<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
       
          <c:if test="${!korisnik.isAdminOrManadzer()}">
     	<c:redirect url="index.jsp"></c:redirect>
     </c:if>
    
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
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    
    <script src="js/bootstrap-formhelpers.min.js"></script>
    
     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>
    

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
     <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">

</head>

<script>
	$(document).ready(function()
	{
	
	});
</script>

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

	<div class="container">
		<div class="row podaci">
			<c:forEach var="akcija" items="${akcije.items}">
			<div class="panel-group">
  				<div class="panel panel-info">
  				<div class="panel-heading">
   					 <div class="panel-body">
   					 <div class="col-md-2">
   					 <h4>Akcija</h4>
   					 </div>
   					 
   					 <div class="col-md-2">
   					 	 <h4><b>Od:   ${akcija.startDate}</b></h4>
   					 </div>
   					 
   					 <div class="col-md-2">
   					 	 <h4><b>Do:  ${akcija.endDate} </b></h4>
   					 </div>
   					 
   					 <div class="col-md-2 col-md-offset-1">
   					 	 <h4>Salon: ${akcija.salon}</h4>
   					 </div>
   					 </div>
  				</div>
  				
   			 <div class="panel-body">
   			<table class="table table-striped">
   			 <thead>
     			 <tr>
       		     <th>Naziv namestaja</th>
       			 <th>Popust</th>
      			</tr>
   		     </thead>
  			  <c:forEach var="item" items="${akcija.namestaji}">
     		 <tr>
     		   <td>${item.ime}</td>
     		   <td>${item.procenat} %</td>
     		 </tr>
   			 </c:forEach>
  		
  			 </tbody>
 			 </table>
 			 </div>
 			 </div>
			</div>
			</c:forEach>

    	</div>
    </div>  



    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
    