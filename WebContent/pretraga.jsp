<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/jput.min.js"></script>

<script>
$(document).ready(function()
		{
			$("button").click(function()
			{
				$.get("SearchServlet", function(data, status)
						{ 
							$("#lista").jPut({
								jsonData: data,
								name: "template",
							});
						});
			});
	
		});
</script>

</head>
<body>


<button type="button" class="btn btn-success" id="trazi"> Prikazi </button>

<div jput="template" id="t" >
<li><b>{{ime}}</b>"   " {{cena}}</li>
</div>

<ul id="lista">

</ul>

</body>
</html>