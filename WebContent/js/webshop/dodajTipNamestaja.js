   	$(document).ready(function()
    	{
			var jup = new Object(${trenutni});
			var izmena = false;
    		
    		$("#naziv").val(jup.naziv);
    		$("#opis").val(jup.opis);
    		$("#podkategorija").val(jup.podkategrija);
    		
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
    	    			toastr.error("Morate uneti naziv kategorije/tipa");
    	    			return;
    	    		}
    	    			
    	    		$.post("CheckUniqueServlet", {tip:"kategorija", id:$("#naziv").val()}, function(data, status)
    	    		{
    	    			if(data === "not_unique")
    	    			{
    	    					toastr.error("Naziv nije jednistven, unesite neki drugi naziv");
    	    					$("#naziv").focus();
    	    			}
    	    		});
    	    	}
    	    });
    		
    		$("#sub").click(function(e) 
    		{
    			
    			 e.preventDefault();
    			
    			var tip = {};
    			tip.naziv = $("#naziv").val();
    			tip.opis = $("#opis").val();
    			tip.podkategrija = $("#podkategorija").val();
    			
    			if(tip.naziv == "")
    			{
    				toastr.error("Morate popuniti naziv");
    				$("#naziv").focus();
    				return;
    			}
    			
    			if(tip.opis == "")
    			{
    				toastr.error("Morate popuniti opis");
    				$("#opis").focus();
    				return;
    			}
    			
    			$.post("AddTipNamestajaServlet", {u: JSON.stringify(tip)}, function(data, status)
    			{
    				if(data === "success")
    				{
    					window.location.replace("admin_panel.jsp");
    				}
    				else
    				{
    					alert("dis is your falut");
    				}
    			});
    		});
    	});