<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
       <jsp:useBean id="namestaji" class="dao.NamestajiDAO" scope="application"></jsp:useBean>
    
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
    
    <script src="js/bootstrap-formhelpers-countries.js"></script>
    <script src="js/bootstrap-formhelpers-countries.en_US.js"></script>

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">

</head>

<script>
	$(document).ready(function()
	{
		$("#search").click(function()
				{
			
					$.get("SearchServlet?searchText=" + $("#searchBox").val(), function(data, status)
							{ 
								var namestajiList = data;
								
								$(".namestaji > .row").empty();
								
								$.each(namestajiList, function(index, value)
										{
											$('.namestaji > .row').append(namestajiHTML(value));
										});
							});
				});
		
			function namestajiHTML(value)
			{
				var str = '<div class="col-sm-4 col-lg-4 col-md-4">'+
                '<div class="thumbnail">'+
                '<img src="http://placehold.it/320x150" alt="">'+
                '<div class="caption">'+
                   '<h4 class="pull-right">' + value.jedinicnaCena + '</h4>'+
                    '<h4><a href="#">'+ value.naziv +'</a>'+
                   '</h4>'+
                   '<p>Proizvodjac: '+ value.nazivProizvodjaca + '</p>'+
                    '<p>Zemlja porekla: '+ value.zemljaProizvodje +'</p>'+
                    '<p>Naziv proizvodjaca: ' + value.nazivProizvodjaca +'</p>'+
                '</div>'+
                '<div class="ratings">'+
                    '<p class="pull-right">' + value.kolicina + ' komada</p>'+
                    '<p>'+
							value.godinaProizvodnje +
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
                    <li>
                        <a href="login_test.html">Prijava</a>
                    </li>
                    <li>
                        <a href="admin_login.html">Admin</a>
                    </li>
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
        <div class="col-md-4 col-md-offset-4">
        <input type="text" class="form-control" id="searchBox">
         </div>
        <button class="btn btn-primary" id="search">Search</button>
        </div>
        </div>
        
        <div class="panel-body">
           <div class="col-md-4 col-md-offset-4">
          		<div class="checkbox">
          			<label> <input type="checkbox"> Naziv</label>
          		</div>
          		<div class="col-xs-4">
          			<input type="text" class="form-control">
          		</div>
          		<select data-country="US"></select>
          		
           </div>
        </div>
        
        </div>
        </div>

	<div class="namestaji">
	<div class="row">
				<c:forEach var="n" items="${namestaji.namestajiList}">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/320x150" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$${n.jedinicnaCena }</h4>
                                <h4><a href="#">${ n.naziv}</a>
                                </h4>
                                <p>Proizvodjac: ${n.nazivProizvodjaca}</p>
                                <p>Zemlja porekla: ${n.zemljaProizvodje }</p>
                                <p>Naziv proizvodjaca: ${n.nazivProizvodjaca }</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right">${n.kolicina } komada</p>
                                <p>
  										${n.godinaProizvodnje }
                                </p>
                            </div>
                        </div>
                    </div>
					</c:forEach>

            </div>
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
    