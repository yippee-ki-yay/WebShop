 var current = "Namestaji"; //koji je trenutni prikaz, treba nam da servlet zna sta da brise i menja
    
    	$(document).ready(function()
    	{
    		$(".box").hover(function()
    		{
    			//$(this).toggleClass("box_selected");
    		});
    		
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
    			else if(current === "Namestaji")
    			{
    				
    						$.post("NamestajiServlet", function(data, status) 
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
    			var popust = "";
    			if(value.procenat != 0)
    				popust = "Popust : "
    					
				var str = '<div class="col-sm-4 col-lg-4 col-md-4" id="' + value.sifra + '">'+
                '<div class="thumbnail">'+
                '<img src="img/' + value.putanjaSlike + '"  alt="">'+
                '<div class="caption">'+
                   '<h4 class="pull-right"><span class="popust">' + popust +"</span>$"+ value.jedinicnaCena + '</h4>'+
                    '<h4><a href="#">'+ value.naziv +'</a>'+
                   '</h4>'+
                   '<p>Sifra: '+ value.sifra + '</p>'+
                    '<p>Zemlja porekla: '+ value.zemljaProizvodje +'</p>'+
                    '<p>Naziv salona: ' + value.prodajniSalon +'</p>'+
                    '<p>Tip namestaja: '+ value.tipNamestaja +'</p>'+
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
                  '<p>Opis: '+ value.opis + '</p>'+
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
					toastr.success("Uspesno obrisan");
	    			$("#" + id).remove();
				}
				else
				{
					toastr.error("Nije moguce obrisati! Kategorija " + id + " je povezana sa namestajima.");
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
    