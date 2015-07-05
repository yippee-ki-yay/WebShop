$(document).ready(function()
	{
		$("#search").click(function()
				{
					var p_naziv = $("#searchBox").val();
					var p_cena_od = $("#cena_od").val();
					var p_cena_do = $("#cena_do").val();
					
					if(!numberCheck(p_cena_od, null, null, "Cena od"))
						return;
					
					if(!numberCheck(p_cena_do, null, null, "Cena do"))
						return;
					
					
					var p_opis = $("#check").is(':checked').toString();
					
					$.get("SearchServlet", 
							{naziv: p_naziv,
							 cena_od: p_cena_od,
							 cena_do: p_cena_do,
							 opis: p_opis,
							 tip: "usluge"
							}, 
							function(data, status)
							{ 
								
								if(data === "")
								{
									toastr.info("No items match your search query");
									$(".namestaji > .row").empty();
								}
								
								var uslugeList = $.parseJSON(data);
								
								console.log(data);
								
								$(".namestaji > .row").empty();
								
								$.each(uslugeList, function(index, value)
										{
											$('.namestaji > .row').append(uslugaHtml(value));
										});
							});
				});
		
		
	});
	
		function uslugaHtml(value)
		{
			var str = '<div class="col-sm-3 col-lg-3 col-md-3">'+
           '<div class="thumbnail">'+
            '<div class="caption">'+
      		'<h4 class="pull-right"> $'+ value.cena + '</h4>'+
      		 '<h4><a href="#">'+ value.naziv +'</a>'+
     		 '</h4>'+
     		 '<p>Opis:'+ value.opis + '</p>'+
  		    '</div>'+
  		     '<div class="usluga_btn">' +
			'<a href="#" class="btn btn-success btn-block kup" role="button" data-id="'+ value.naziv + '">'+
  	            'Kupi'+
				'</a>'+
				'</div>'+
               
            '</div>'+
              
           '</div>'+
       	'</div>';
			
			return str;
		}
	
	$(document).on("click",".kup", function(){
		
		var id = $(this).data("id");
		//var kol = $(this).prev().val();
		var tip = "Namestaj";
		
		/*if(kol == "")
		{
			toastr.error("Morate uneti kolicinu");
			return;
		}
		
		if(!numberCheck(kol, null, null, "Kolicina"))
			return;*/
		
		$.post("KupovinaServlet", {sifra: id, type :"usluga"}, function(data, status)
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
					else if(/^povezana/.test(data))
					{
						var ime = data.substring(8, data.length)
						toastr.info("Ovu uslugu mozete kupiti samo uz namestaj: " + ime);
					}
				});
		
		
		
	});