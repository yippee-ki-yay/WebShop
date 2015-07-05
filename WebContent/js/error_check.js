
function numberCheck(num, min, max, msg)
{
	var regex = /^[0-9]+$/;
	
	if(!regex.test(num))
	{
		toastr.error(msg + " mora biti pozitivna brojcana vrednost");
		return false;
	}
	
	var c = parseInt(num);
	
	if(c > 2147483647)
	{
		toastr.error(msg + " mora biti pozitivna brojcana vrednost manja od 2147483647");
		return false;
	}
	
	if(min != null && max != null)
	if(c < min)
	{
		toastr.error(msg + " mora veca od " + min);
		return false
	}
	else if(c > max)
	{
		toastr.error(msg + " mora manja od " + max);
		return false;
	}
	
	return true;

}