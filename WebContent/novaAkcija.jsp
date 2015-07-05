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
	var namestaji;

	$(document).ready(function()
	{
		$("#check").prop("disabled", true);
		
		//prikaz namestaja nekog salona, kad izaberemo neki
		$("#saloni").change(function() 
		{
			var current = $( "#saloni option:selected" ).text();
			
			if(current != "")
			{
				$("#check").prop("disabled", false);
			}
			
			$.post("NamestajiServlet", {poSalonu : current}, function(data, status)
			{
				$(".podaci").empty();
				
				namestaji = $.parseJSON(data);
				
				//prodji kroz sve podatke i generi html
				$.each(namestaji, function(index, value)
				{
					$('.podaci').append(namestajiHtml(value));
				});
			});
		});
		
		$("#check").change(function()
		{
			if ($(this).is(':checked'))
			{
				$(".podaci").empty();
				
				$.post("TipNamestajaServlet", function(data, status) 
    			{
    				tipovi_namestaja = $.parseJSON(data);
    						
    				//prodji kroz sve podatke i generi html
    				$.each(tipovi_namestaja, function(index, value)
    				{
    					$('.podaci').append(tipoviNamestajaHtml(value));
    				});
    			});
		
			}
			else
			{
				$(".podaci").empty();
				
				//prodji kroz sve podatke i generi html
				$.each(namestaji, function(index, value)
				{
					$('.podaci').append(namestajiHtml(value));
				});
			}
			
		});
		
		$(document).on("click","#dodaj", function()
		{
			var akcija = {}
			akcija.startDate = "";
			akcija.endDate = "";
			akcija.salon = $("#saloni").val();
			akcija.namestaji = [];
			
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
			
			akcija.startDate = $('#start_date').val();
			akcija.endDate = $('#end_date').val();
			
			var opseg = daydiff(start_date, end_date);
			
			if(opseg <= 0)
			{
				toastr.error("Krajnji datum mora biti veci od pocetnog");
				return;
			}
			
			$(".namestaj").each(function() 
			{
				var sifra = $(this).data("id");
				var popust = $(this).val();
				
				if(popust != "")
				{
					item = {}
			        item ["naziv"] = sifra;
			        item ["procenat"] = popust;
			        item["ime"] = "";
					akcija.namestaji.push(item);
				}
			});
			
			$(".kategorija").each(function() 
					{
						var sifra = $(this).data("id");
						var popust = $(this).val();
						
						$.each(namestaji, function(index, value)
						{
							if(popust != "")
							if(value.tipNamestaja === sifra)
							{
								item = {}
						        item ["naziv"] = value.sifra;
						        item ["procenat"] = popust;
						        item["ime"] = "";
								akcija.namestaji.push(item);
							}
						});
					});
			
			$.post("AkcijaServlet", {json: JSON.stringify(akcija)}, function(data, status)
			{
				if(data === "success")
				{
					window.location.replace("admin_panel.jsp");
				}
			});
			
		});
		
			function namestajiHtml(value)
			{
				var str = '<div class="col-sm-3 col-lg-3 col-md-3">'+
                '<div class="thumbnail">'+
                '<img src="img/' + value.putanjaSlike + '"  alt="">'+
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
							'<input type="text" class="form-control namestaj" data-id="' +value.sifra +'">'+
                    '</p>'+
                    '</div>'+
                  '</div>'+
               '</div>';
				
				return str;
			}
			
			function tipoviNamestajaHtml(value)
    		{
    			var podkategorija = "Nema";
    			
    			if(value.podkategrija !== 'null')
    				podkategorija = value.podkategrija;
    			
    			
    			var str = '<div class="col-sm-3 col-lg-3 col-md-3" id="'+value.naziv + '">' +
   			 	'<div class="thumbnail">'+
               '<div class="caption">'+
                   '<h4><a href="#">'+ value.naziv +'</a>'+
                  '</h4>'+
                  '<p>Proizvodjac: '+ value.opis + '</p>'+
                  '<p>Podkatgorija: '+ podkategorija + '</p>'+
               '</div>'+
               '<div class="ratings">'+
                  '<p>'+
                  '<h3>Popust </h3>'+
					'<input type="text" class="form-control kategorija" data-id="' +value.naziv +'">'+
				  '</p>'
                 '</div>'+
                 '</div>' +
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
    