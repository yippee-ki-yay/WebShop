<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
       <jsp:useBean id="namestaji" class="dao.NamestajiDAO" scope="application"></jsp:useBean>
       <jsp:useBean id="usluge" class="dao.UslugeDao" scope="application"></jsp:useBean>
       <jsp:useBean id="saloni" class="dao.SaloniDAO" scope="application"></jsp:useBean>
       
          <c:if test="${!korisnik.isAdmin()}">
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
    
    

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
     <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">

</head>

<script>
	$(document).ready(function()
	{
		//prikaz namestaja nekog salona, kad izaberemo neki
		$("#saloni").change(function() 
		{
			var current = $( "#saloni option:selected" ).text();
			
			$.get("SearchServlet", {poSalonu : current}, function(data, status)
			{
				namestaji = $.parseJSON(data);
				
				//prodji kroz sve podatke i generi html
				$.each(namestaji, function(index, value)
				{
					$('.podaci').append(namestajiHtml(value));
				});
			});
		});
		
		
		
		$("#dodaj").click(function()
		{
			
					
		});
		
			function namestajiHtml(value)
			{
				var str = '<div class="col-sm-3 col-lg-3 col-md-3">'+
                '<div class="thumbnail">'+
                '<img src="http://placehold.it/320x150" alt="">'+
                '<div class="caption">'+
                   '<h4 class="pull-right">$' + value.jedinicnaCena + '</h4>'+
                    '<h4><a href="#">'+ value.naziv +'</a>'+
                   '</h4>'+
                   '<p>Proizvodjac: '+ value.nazivProizvodjaca + '</p>'+
                    '<p>Zemlja porekla: '+ value.zemljaProizvodje +'</p>'+
                    '<p>Naziv proizvodjaca: ' + value.nazivProizvodjaca +'</p>'+
                '</div>'+
                '<div class="ratings">'+
                    '<p class="pull-right">' + value.kolicina + ' komada</p>'+
                    '<p>'+
                    		'<h3>Popust </h3>'+
							'<input type="text" class="form-control">'+
                    '</p>'+
                    '</div>'+
                  '</div>'+
               '</div>';
				
				return str;
			}
			
			
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
                        <a href="lista.jsp">Pretrazi</a>
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
         		<c:forEach var="salon" items="${saloni.saloniLista}">
        			<option>${salon.naziv}</option>
        		</c:forEach>
        	</select> 
         </div>
        <div class="col-md-1 panel-label">
        	<label>Pocetak: </label>
        </div>
         <div class="col-md-2">
         	<div class="bfh-datepicker"></div>
         </div>
         <div class="col-md-1 panel-label">
        	<label>Kraj: </label>
        </div>
         <div class="col-md-2">
          	<div class="bfh-datepicker"></div>
         </div>
         <div class="col-md-2">
  			<button class="btn btn-primary" id="dodaj">Dodaj akciju</button>
  		</div>
        </div>
        </div>
        
        <div class="panel-body">
           <div class="col-md-4 col-md-offset-4">
          		
          		
           </div>
        </div>
        
        </div>
        </div>
        </div>

	<div class="container">
		<div class="row podaci">
			

    	</div>
    </div>  


    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Prodavnica namestaja 2015</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
    