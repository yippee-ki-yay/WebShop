<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
       
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
	
	$(document).on("click", "#izvestaj", function()
	{
		
		$(".podaci").empty();
		
		function parseDate(str) 
		{
		    var mdy = str.split('-')
		    return new Date(mdy[2], mdy[1], mdy[0]);
		}

		function daydiff(first, second) 
		{
		    return (second-first)/(1000*60*60*24);
		}
		
		var start_date = parseDate($('#start_date').val());
		var end_date = parseDate($('#end_date').val());
		
		var opseg = daydiff(start_date, end_date);
		
		if(opseg <= 0)
		{
			toastr.error("Krajnji datum mora biti veci od pocetnog");
			return;
		}
		
		$.post("IzvestajServlet", 
				{startDate:$('#start_date').val(),
				 endDate:$('#end_date').val(),
				 op: opseg
				},
			function(data, status)
			{
					var tabele = $.parseJSON(data);
					
					$(".podaci").empty();
					
					$.each(tabele, function(index, value)
    				{
						
						var str = '<h3>'+ value.salon+ '</h3><table class="table table-striped">'+
					    '<thead>'+
					      '<tr>'+
					        '<th>Datum</th>'+
					        '<th>Zarada</th>'+
					      '</tr>'+
					     '</thead>'+
					     '<tbody>';
					     
					     var ukupna_zarada = 0;
					     
					     $.each(value.mapa, function(index, v)
				    	 {
					    	 str += '<tr>';
					    	 str += '<td>' + index +'</td>';
					    	 str += '<td>' + v +'</td>';
					    	 str += '</tr>';
					    	 ukupna_zarada += parseInt(v);
				    	 });
					     
						str += '</tbody></table>';
						str += '<h2>Ukupna zarada: ' + ukupna_zarada + '</h2>';
						
						$(".podaci").append(str);
						
    				});
			});
		
		
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
       
         
             
         
        <div class="col-md-1 col-md-offset-2 panel-label">
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
  			<button class="btn btn-primary" id="izvestaj">Izvestaj</button>
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
    