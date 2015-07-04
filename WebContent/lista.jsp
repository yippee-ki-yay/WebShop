<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
        <jsp:useBean id="korisnik" class="model.Korisnik" scope="session"></jsp:useBean>
    
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
     
     <link href="css/toastr.css" rel="stylesheet"/>
     <script src="js/toastr.js"></script>

</head>

<script>
	$(document).ready(function()
	{
		$("#search").click(function()
				{
					var p_naziv = $("#searchBox").val();
					var p_boja = $("#boja").val();
					var p_godina = $("#god").val();
					var p_drzava = $("#drzava").val();
					var p_tip_namestaja = $("#tip_namestaja").val();
					var p_tip = "namestaj";
					
					var p_cena_od = $("#cena_od").val();
					var p_cena_do = $("#cena_do").val();
					var p_kapacitet_od = $("#kapacitet_od").val();
					var p_kapacitet_do = $("#kapacitet_do").val();
					var p_proizvodjac = $("#proizvodjac").val();
					
					if(p_boja === "default")
						p_boja = "";
					
					$.get("SearchServlet", 
							{naziv: p_naziv,
							 boja: p_boja,
							 godina: p_godina,
							 drzava: p_drzava,
							 tip: p_tip,
							 tipNamestaja: p_tip_namestaja,
							 cena_od: p_cena_od,
							 cena_do: p_cena_do,
							 kapacitet_od: p_kapacitet_od,
							 kapacitet_do: p_kapacitet_do,
							 proizvodjac: p_proizvodjac,
							}, 
							function(data, status)
							{ 
								if(data === "")
								{
									toastr.info("No items match your search query");
									$(".namestaji > .row").empty();
									return;
								}
								
								var namestajiList = $.parseJSON(data);
								
								$(".namestaji > .row").empty();
								
								$.each(namestajiList, function(index, value)
										{
											$('.namestaji > .row').append(namestajiHTML(value));
										});
							});
				});
		
			function namestajiHTML(value)
			{
				var str = '<div class="col-sm-3 col-lg-3 col-md-3">'+
                '<div class="thumbnail">'+
                '<img src="img/' + value.putanjaSlike + '"  alt="">'+
                '<div class="caption">'+
                   '<h4 class="pull-right">' + value.jedinicnaCena + '</h4>'+
                    '<h4><a href="#">'+ value.naziv +'</a>'+
                   '</h4>'+
                   '<p>Proizvodjac: '+ value.nazivProizvodjaca + '</p>'+
                    '<p>Zemlja porekla: '+ value.zemljaProizvodje +'</p>'+
                    '<p>Naziv proizvodjaca: ' + value.nazivProizvodjaca +'</p>'+
                '</div>'+
                '<div class="ratings">'+
                    '<p class="pull-right">'
                    '<button class="btn btn-success btn_padding del" data-id="' + value.naziv + '">Obrisi</button></p>' +
                    '<p>'+
							value.godinaProizvodnje +
                    '</p>'+
                    '</div>'+
                  '</div>'+
               '</div>';
				
				return str;
			}
	});
	
	$(document).on("click",".kup", function(){
		
		var id = $(this).data("id");
		var kol = $(this).prev().val();
		var tip = "Namestaj"; //TODO: kad imam usluge moras promeniti
		
		$.post("KupovinaServlet", {sifra: id, type :tip, kolicina: kol}, function(data, status)
				{
					if(data === "success")
					{
						toastr.success("Artikal je dodat u korpu");
						 $(this).prev().val("1");
					}
					else if(data === "nije_registrovan")
					{
						toastr.info("morate biti korisnik da mozete izvrsiti kupovinu");
					}
					else if(data === "admin_manadzer")
					{
						toastr.info("Admin/Manadzer nalozima je zabranjena kupovina");
					}
					else if(data === "previse")
					{
						toastr.error("Nema dovoljno proizvoda na lageru");
					}
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
        
        <div class="panel-body">
        
        <div class="col-md-1">
        	<label>Boja: </label>
        </div>
        
           <div class="col-md-2">
          		<select class="form-control" id="boja">
          			<option value=""></option>
          			<option>Black</option>
    				<option>White</option>
    				<option>Blue</option>
    				<option>Red</option>
    				<option>Green</option>
          		</select>
          	</div>
          
         <div class="col-md-1">
        	<label>Godina: </label>
        </div>
          
          <div class="col-md-2" >
          		<input type="text" class="form-control" id="god">
          </div>
          
         <div class="col-md-1">
        	<label>Drzava: </label>
        </div>
          
          <div class="col-md-2">
          		<div class="bfh-selectbox bfh-countries" data-country="" id="drzava"></div>
          </div>
          
           <div class="col-md-1">
        	<label>Kategorije: </label>
        	</div>
        
           <div class="col-md-2">
             <select class="form-control" id="tip_namestaja"> 
             	<option value=""></option>
    			<c:forEach var="tip" items="${tipoviNamestaja.items}">
    				<option>${tip.naziv}</option>
    			</c:forEach>
    		  </select>
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
         		 
         		 <div class="col-md-1">
        			<label>Kapacitet od: </label>
        		</div>
        		
        		<div class="col-md-1">
          			<input type="text" class="form-control" id="kapacitet_od">
         		 </div>
         		 
         		 	 <div class="col-md-1">
        			<label>Do: </label>
        		</div>
        		
        		<div class="col-md-1">
          			<input type="text" class="form-control" id="kapacitet_do">
         		 </div>
         		 
         		 <div class="col-md-1">
        			<label>Proizvodjaci: </label>
        		</div>
        		
        		<div class="col-md-2">
          			<input type="text" class="form-control" id="proizvodjac">
         		 </div>
        		
        	</div>
           
        </div>
        
        </div>
 

	<div class="namestaji">
	<div class="row">
				<c:forEach var="n" items="${namestaji.items}">
                    <div class="col-sm-3 col-lg-3 col-md-3">
                        <div class="thumbnail">
                            <img src="img/${n.putanjaSlike } " alt="">
                            <div class="caption">
                                <h4 class="pull-right">$${n.jedinicnaCena }</h4>
                                <h4><a href="#">${ n.naziv}</a>
                                </h4>
                                <p>Proizvodjac: ${n.nazivProizvodjaca}</p>
                                <p>Zemlja porekla: ${n.zemljaProizvodje }</p>
                                <p>Naziv proizvodjaca: ${n.nazivProizvodjaca }</p>
                                <p>Na lageru: ${n.kolicina }</p>
                            </div>
                       
        					 <p>
        					 	Kolicina:
         					   <input class="kolicina" type="number" value="1">
            					<a href="#" class="btn btn-primary btn_padding kup" role="button" data-id="${n.sifra }">
              								 Kupi
            				</a>
         					</p>
                           
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
    