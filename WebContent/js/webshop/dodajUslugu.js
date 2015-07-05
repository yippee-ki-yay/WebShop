$(document).ready(function()
    	{
    		var jup = new Object(${trenutni});
    		var izmena = false;
    		
    		
    		$("#naziv").val(jup.naziv);
    		$("#opis").val(jup.opis);
    		$("#cena").val(jup.cena);
    		
    		if(jup.naziv != undefined)
    		{
    			$('#naziv').prop('readonly', true);
    			izmena = true;
    		}
    		
    		$("#otkazi_btn").click(function(e) 
    	    {
    			e.preventDefault();
    			window.location.replace("admin_panel.jsp");
				return;
    	    });
    		
    		$("#naziv").blur(function()
    		{
    			if(!izmena)
    			{
    			if($("#naziv").val() == "")
    			{
    				toastr.error("Morate uneti naziv usluge");
    				return;
    			}
    			
    			$.post("CheckUniqueServlet", {tip:"usluga", id:$("#naziv").val()}, function(data, status)
    			{
    				if(data === "not_unique")
    				{
    					toastr.error("Naziv nije jednistven, unesite neki drugi naziv");
    					$("#naziv").focus();
    				}
    			});
    			}
    		});
    		
    		$("#sub_btn").click(function(e) 
    		{
    			e.preventDefault();
    			
    			var usluga = {};
    			usluga.naziv = $("#naziv").val();
    			usluga.opis = $("#opis").val();
    			usluga.cena = $("#cena").val();
    			usluga.namestaj = $("#namestaji").val();
    			
    			if(usluga.naziv == "")
    			{
    				toastr.error("Morate popuniti naziv");
    				$("#naziv").focus();
    				return;
    			}
    			
    			if(usluga.opis == "")
    			{
    				toastr.error("Morate popuniti opis");
    				$("#opis").focus();
    				return;
    			}
    			
    			if(usluga.cena == "")
    			{
    				toastr.error("Morate popuniti cenu");
    				$("#cena").focus();
    				return;
    			}
    			
    			if(!numberCheck(usluga.cena, null, null, "Cena"))
    				return;
    			
    			$.post("AddUsluguServlet", {u: JSON.stringify(usluga)}, function(data, status)
    			{
    				console.log("wtf!");
    				
    				if(data === "success")
    				{
    				
    					window.location.replace("admin_panel.jsp");
    					return;
    				}
    				else
    				{
    					toastr.error("morate biti korisnik da mozete izvrsiti kupovinu");
    				}
    			});
    		});
    	});