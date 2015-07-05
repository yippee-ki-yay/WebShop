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
			var jeste_broj = true;
			
			
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
					if(!numberCheck(popust, 0, 100, "Popust"))
					{
	    				jeste_broj = false;
					}
					
					item = {}
			        item ["naziv"] = sifra;
			        item ["procenat"] = popust;
			        item["ime"] = "";
					akcija.namestaji.push(item);
				}
			});
			
			if(!jeste_broj)
				return;
			
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