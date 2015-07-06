$(document).ready(function()
	{
		$("#search").click(function()
				{
					var p_naziv = $("#searchBox").val();
					var p_boja = $("#boja").val();
					var p_godina = $("#god").val();
					var p_drzava = $("#drzava option:selected").text();
					var p_tip_namestaja = $("#tip_namestaja").val();
					var p_tip = "namestaj";
					
					var p_cena_od = $("#cena_od").val();
					var p_cena_do = $("#cena_do").val();
					var p_kapacitet_od = $("#kapacitet_od").val();
					var p_kapacitet_do = $("#kapacitet_do").val();
					var p_proizvodjac = $("#proizvodjac").val();
					
					if(p_cena_od != "")
					if(!numberCheck(p_cena_od, null, null, "Cena od"))
						return;
					
					if(p_cena_do != "")
					if(!numberCheck(p_cena_do, null, null, "Cena do"))
						return;
					
					if(p_kapacitet_od != "")
					if(!numberCheck(p_kapacitet_od, null, null, "Kolicina od"))
						return;
					
					if(p_kapacitet_do != "")
					if(!numberCheck(p_kapacitet_do, null, null, "Kolicina do"))
						return;
					
					if(p_godina != "")
					if(!numberCheck(p_godina, 0, 2015, "Godina"))
						return;
					
					
					
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
                   '<h4 class="pull-right"> $' + value.jedinicnaCena + '</h4>'+
                    '<h4><a href="#">'+ value.naziv +'</a>'+
                   '</h4>'+
                   '<p>Godina: '+ value.godinaProizvodnje + '</p>'+
                    '<p>Zemlja porekla: '+ value.zemljaProizvodje +'</p>'+
                    '<p>Kategorija: ' + value.tipNamestaja +'</p>'+
                    '<p>Na lageru:'+ value.kolicina + '</p>'+
                '</div>'+
           	 	'<p class="kup_namestaj">'+
			 	'Kolicina:'+
				   '<input class="kolicina" type="number" value="1">'+
				'<a href="#" class="btn btn-primary btn_padding kup" role="button" data-id="'+ value.sifra + '">'+
								'Kupi'+
						 '</a>'+
						'</p>'+
                  '</div>'+
               '</div>';
				
				return str;
			}
	});
	
	$(document).on("click",".kup", function(){
		
		var id = $(this).data("id");
		var kol = $(this).prev().val();
		
		if(kol == "")
		{
			toastr.error("Morate uneti kolicinu");
			return;
		}
		
		if(!numberCheck(kol, null, null, "Kolicina"))
			return;
		
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
					else if(/^ima/.test(data))
					{
						toastr.success("Artikal je dodat u korpu");
						var naziv_usluge = data.substring(3, data.length);
						if (confirm('Uz ovaj namestaj ide i usluga '+ naziv_usluge + ' da li zelite kupiti i uslugu')) 
						{
							$.post("KupovinaServlet", {dodatna_usluga: naziv_usluge}, function(data, status)
							{
								toastr.success("Usluga je dodata u korpu");
							});
						} else {
						    // Do nothing!
						}
					}
				});
		
	});