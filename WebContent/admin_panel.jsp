<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
     <jsp:useBean id="usluge" class="dao.UslugeDao" scope="application"></jsp:useBean>
     <jsp:useBean id="tipoviNamestaja" class="dao.TipNamestajaDAO" scope="application"></jsp:useBean>
     <jsp:useBean id="akcije" class="dao.AkcijeDAO" scope="application"></jsp:useBean>
     
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

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
   		 var current = "Namestaji"; //koji je trenutni prikaz, treba nam da servlet zna sta da brise i menja
    
    	$(document).ready(function()
    	{
    		$(".box").hover(function()
    		{
    			//$(this).toggleClass("box_selected");
    		});
    		
    		//TODO: da li mozemo izbeci globalne promenjive?
    		var usluge = null;
			var namestaji = null;
			var tipovi_namestaja = null;
    		
    		$(".selekcija").change(function() 
    		{
    			//uzmemo sta se nalazi u select kontroli  			
    			current = $( ".selekcija option:selected" ).text();
    			
    			//ispraznimo sav trenutan prikaz
    			$(".podaci").empty();
    			
    			
    			if(current === "Dodatne usluge")
    			{
    				//ako pre toga vec nismo dobavili podatke sa servera salji request
    				if(usluge == null)
  					{
    					$.post("UslugeServlet", function(data, status) 
    					{
    						usluge = $.parseJSON(data);
    						
    						//prodji kroz sve podatke i generi html
    						$.each(usluge, function(index, value)
    						{
    							$('.podaci').append(uslugaHtml(value));
    						});
    					});
  					}
    				else
    				{
    					$.each(usluge, function(index, value)
        						{
        							$('.podaci').append(uslugaHtml(value));
        						});
    				}
    			}
    			else if(current === "Namestaji")
    			{
    				//TODO: tamo je post ovde je get kakva je ovo anarhija
    				
    				if(namestaji == null)
    				{
    						$.get("SearchServlet", function(data, status) 
    	    					{
    	    						namestaji = $.parseJSON(data);
    	    					
    	    						$.each(namestaji, function(index, value)
    								{
    										$('.podaci').append(namestajiHtml(value));
    								});
    	    					});
    				}
    				else
    				{
    					$.each(namestaji, function(index, value)
								{
										$('.podaci').append(namestajiHtml(value));
								});
    				}
    			}
    			else
    			{
    				if(tipovi_namestaja == null)
  					{
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
    					$.each(tipovi_namestaja, function(index, value)
        						{
        							$('.podaci').append(tipoviNamestajaHtml(value));
        						});
    				}
    			}
    		});
    		
    		function uslugaHtml(value)
    		{
    			var str = '<div class="col-sm-4 col-lg-4 col-md-4" id="'+value.naziv + '">' +
    			 '<div class="thumbnail">'+
                '<div class="caption">'+
                   '<h4 class="pull-right">' + value.cena + '</h4>'+
                    '<h4><a href="#">'+ value.naziv +'</a>'+
                   '</h4>'+
                   '<p>Proizvodjac: '+ value.opis + '</p>'+
                '</div>'+
                '<div class="ratings">'+
                   '<p class="pull-right">' +
                   '<button class="btn btn-success btn_padding del" data-id="' +value.naziv + '">Obrisi</button></p>' +
                   '<button class="btn btn-success btn_padding izm" data-id="' + value.naziv + '">Izmeni</button>' +
                  '</div>'+
                  '</div>' +
               '</div>';
				
				return str;
    		}
    		
    		function namestajiHtml(value)
			{
				var str = '<div class="col-sm-4 col-lg-4 col-md-4" id="' + value.sifra + '">'+
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
                '<p class="pull-right"> <button class="btn btn-success btn_padding del" data-id="' + value.sifra + '">Obrisi</button></p>'+
                '<button class="btn btn-success btn_padding izm" data-id="' + value.sifra + '">Izmeni</button>' +
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
    			
    			
    			var str = '<div class="col-sm-4 col-lg-4 col-md-4" id="'+value.naziv + '">' +
   			 	'<div class="thumbnail">'+
               '<div class="caption">'+
                   '<h4><a href="#">'+ value.naziv +'</a>'+
                  '</h4>'+
                  '<p>Proizvodjac: '+ value.opis + '</p>'+
                  '<p>Podkatgorija: '+ podkategorija + '</p>'+
               '</div>'+
               '<div class="ratings">'+
                  '<p class="pull-right">' +
                  '<button class="btn btn-success btn_padding del" data-id="' + value.naziv + '">Obrisi</button></p>' +
                  '<button class="btn btn-success btn_padding izm" data-id="' + value.naziv + '"">Izmeni</button>' +
                 '</div>'+
                 '</div>' +
              '</div>';
				
				return str;
    		}
    	});
    	
    	//moramo staviti na on, zato sto dinamicki generismo html pa moramo delegated binding
    	$(document).on("click",".del", function(){
    		var id = $(this).data("id");
			
			$.get("DeleteServlet", {sifra: id, type: current}, function(data, status)
			{
				if(data === "success")
				{
					alert("uspesno obrisan");
	    			$("#" + id).remove();
				}
				else
				{
					alert("des poso");
				}
			});
    	});
    	
    	$(document).on("click",".izm", function(){
    		var id = $(this).data("id");
			
			if(current === "Dodatne usluge")
				window.location.replace("dodajUslugu.jsp?id="+id);
			else if(current === "Namestaji")
				window.location.replace("dodajNamestaj.jsp?id="+id);
			else
				window.location.replace("dodajTipNamestaja.jsp?id="+id);
			
    	});
    
    </script>

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
                
                <div class="list-group">
                	<h4>Akcije</h4>
                    <a href="novaAkcija.jsp" class="list-group-item">Nova akcija</a>
                </div>
                
                <div class="list-group">
                	<h4>Izvestaj</h4>
                    <a href="dodajNamestaj.jsp" class="list-group-item">Po danima</a>
                    <a href="dodajNamestaj.jsp" class="list-group-item">Po kategoriji</a>
                </div>
            </div>

            <div class="col-md-9">

                <div class="row podaci">
                
				<c:forEach var="n" items="${namestaji.items}">
                    <div class="col-sm-4 col-lg-4 col-md-4 box" id="${n.sifra}">
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
                                <p class="pull-right"> <button class="btn btn-success btn_padding del" data-id="${n.sifra}">Obrisi</button></p>
                                <button class="btn btn-success btn_padding izm" data-id="${n.sifra}">Izmeni</button>
                            </div>
                        </div>
                    </div>
					</c:forEach>

                   
                    </div>

                    
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
    