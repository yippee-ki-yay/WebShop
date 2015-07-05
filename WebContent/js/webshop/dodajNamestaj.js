$(document).ready(function()
    	{
		    var jup = new Object(${trenutni});
    		var izmena = false;
		    
    		$("#sifra").val(jup.sifra);
    		$("#naziv").val(jup.naziv);
    		$("#boja").val(jup.boja);
    		$("#zemlja option:selected").text(jup.zemljaProizvodje);
    		$("#proizvodjac").val(jup.nazivProizvodjaca);
    		$("#cena").val(jup.jedinicnaCena);
    		$("#kolicina").val(jup.kolicina);
    		$("#tip_namestaja").val(jup.tipNamestaja);
    		$("#godina").val(jup.godinaProizvodnje);
    		$("#salon").val(jup.prodajniSalon);
    		$("#ucitana_slika").val(jup.putanjaSlike);
    		
    		if(jup.putanjaSlike != undefined)
    		{
    			$(".slika").text("Slika: " + jup.putanjaSlike + " je ucitana! Mozete dole izmeniti sliku.");
    			izmena = true;
    		}
    		
    		if(jup.sifra != undefined)
    		{
    			$('#sifra').prop('readonly', true);
    			izmena = true;
    		}
    		
    		
    		$("#sifra").blur(function()
    	    {
    	    	if(!izmena)
    	    	{
    	    	   if($("#sifra").val() == "")
    	    	    {
    	    	    	toastr.error("Morate uneti sifru namestaja");
    	    	    	return;
    	    	    }
    	    	    			
    	    	    $.post("CheckUniqueServlet", {tip:"namestaj", id:$("#sifra").val()}, function(data, status)
    	    	    {
    	    	    	if(data === "not_unique")
    	    	    	{
    	    	    			toastr.error("Sifra nije jednistven, unesite neki drugi naziv");
    	    	    			$("#sifra").focus();
    	    	    	}
    	    	    });
    	    	  }
    	     });
    		
    		$("#otkazi_btn").click(function(e) 
    	    {
    	    	e.preventDefault();
    	    	window.location.replace("admin_panel.jsp");
    			return;
    	    });
    		
    		$("form").submit(function()
    		{
    			if($("#sifra").val() == "")
    			{
    				toastr.error("morate izabrati sifru");
    				$("#sifra").focus();
    				return false;
    			}
    			
    			if($("#boja").val() == null)
    			{
    				toastr.error("morate izabrati boju");
    				$("#boja").focus();
    				return false;
    			}
    			
    			if($("#salon").val() == null)
    			{
    				toastr.error("morate izabrati salon");
    				$("#salon").focus();
    				return false;
    			}
    			
    			if($("#tip_namestaja").val() == null)
    			{
    				toastr.error("morate izabrati tip namestaja");
    				$("#tip_namestaja").focus();
    				return false;
    			}
    			
    			if($("#zemlja option:selected").text() == "")
    			{
    				toastr.error("morate izabrati zemlju proizvodnje");
    				$("#zemlja").focus();
    				return false;
    			}
    			
    			if($("#slika").val() == "" && izmena == false)
    			{
    				toastr.error("morate izabrati sliku");
    				return false;
    			}
    			
    			if(!numberCheck($("#cena").val(), null, null, "Cena"))
    				return false;
    			
    			if(!numberCheck($("#kolicina").val(), null, null, "Kolicina"))
    				return false;
    			
    			if(!numberCheck($("#godina").val(), 0, 2015, "Godina"))
    				return false;
    		});
			
    	});