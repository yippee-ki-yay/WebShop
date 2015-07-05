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