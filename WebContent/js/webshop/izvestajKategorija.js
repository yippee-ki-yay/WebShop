	$(document).ready(function()
	{
	
			
	});
	
	$(document).on("click", "#izvestaj", function()
	{
		
		$(".podaci").empty();
		
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
		
		var opseg = daydiff(start_date, end_date);
		
		if(opseg <= 0)
		{
			toastr.error("Krajnji datum mora biti veci od pocetnog");
			return;
		}
		
		var kategorija = $("#kategorija").val();
		
		if(kategorija === "default")
		{
			toastr.error("Morate izabrati kategoriju");
			return;
		}
		
		$.post("IzvestajServlet", 
				{startDate:$('#start_date').val(),
				 endDate:$('#end_date').val(),
				 op: opseg,
				 tip: kategorija
				},
			function(data, status)
			{
					var tabele = $.parseJSON(data);
					
					$(".podaci").empty();
					
					$.each(tabele, function(index, value)
    				{
						
						var str = '<h3>'+ value.salon+ '</h3><table class="table table-striped">'+
					    '<thead>'+
					      '<tr>'+
					        '<th>Datum</th>'+
					        '<th>Kolicina</th>'+
					      '</tr>'+
					     '</thead>'+
					     '<tbody>';

					     
					     $.each(value.mapa, function(index, v)
				    	 {
					    	 str += '<tr>';
					    	 str += '<td>' + index +'</td>';
					    	 str += '<td>' + v +'</td>';
					    	 str += '</tr>';
				    	 });
					     
					     
						str += '</tbody></table>';
						str += '<h3>Ukupna zarada: ' + value.ukupno + '</h3>';
						
						$(".podaci").append(str);
						
    				});
			});
		
		
	});