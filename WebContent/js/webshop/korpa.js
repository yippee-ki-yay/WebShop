   	$(document).ready(function()
    	{
    		$("#racun").click(function()
    		{
    			$.post("AddRacun", {}, function(data, status)
            	{
    				if(data === "prazan")
    				{
    					toastr.error("Vasa korpa je prazna");
    					return;
    				}
    				
    				if(data === "prijava")
    				{
    					toastr.error("Morate biti registrovan korisnik");
    					return;
    				}
    				
    				$("#content").empty();
    				toastr.success("Artikli su uspesno kupljeni i izdat je racun");
            	});
    		});
    	});
    	
    	$(document).on("click", ".otkazi", function() 
        		{
        			var id = $(this).data("id");
        			
        			$.post("OtkaziKupovinuServlet", {sifra: id}, function(data, status)
        			{
        				if(data === "success")
        				{
        					
        					$("#"+id).parent().parent().remove();
        					return;
        				}
        			});
        			
        		});