<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    
 <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.1.js" type="text/javascript"></script>
 
  <script src="js/jquery.validate.js"></script>
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script>
    	$(document).ready(function()
    	{
    		
    		
    		
    		$("#sub").click(function()
    		{
    			var $user = $("#username").val();
    			var $pass = $("#pass").val();
    			
    			if($user == "" || $pass == "")
    			{
    				$("#valid").text("Fill out all the fields");
    				$('#username').css({ "border": '#FF0000 1px solid'});
    				return;
    			}
    			
    			$.post( "MenadzerLoginServlet", 
    					{
    						user: $user,
    						pass: $pass
    					}, 
    					function( data ) 
    					{
    						if(data == "fail")
    						{
    							$("#valid").text("Wrong user/pass");
    							return;
    						}
    							
    						window.location.replace("admin_panel.jsp");
    					});
    		});	
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
                        <a href="lista.jsp">Namestaji</a>
                    </li>
                     <li>
                        <a href="usluge.jsp">Usluge</a>
                    </li>
                     <c:if test="${korisnik == null }">
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
                  
                            <ul class="nav navbar-nav navbar-right">
                        	<li>
                        		<a href="korpa.jsp" class="korpa">
                        			<img src="img/shop_cart.png"></img>
                        		</a>
                        	</li>
                        </ul>
                
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

       <div class="col-md-4 col-md-offset-4">
       <h1>Menadzeri login</h1>
       <form id="reg">
    <label for="user"><h3>Korisnicko ime:</h3></label>
 
    <input class="form-control" id="username" name="username"> 
    
    <label for="user"><h3>Sifra:</h3></label>
    <input class="form-control" type="password" id="pass" name="pass"> 
    <button class="btn btn-primary sub_btn" id="sub">Submit</button>
    <h4 id="valid"></h4>
    </form>
    </div>
              
            </div>


    <!-- /.container -->

    <div class="container">

        <hr>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
    