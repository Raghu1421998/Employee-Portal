function validate() {

	var fname = $("#fname").val();
	var n = fname.length;
	if (fname == "") {
		$("#fname").css("border", "1px solid red");
		$("#demo").html("Company Name Should not be empty");
		$("#fname").focus();
		return false;
	} else if (n > 50) {
		$("#fname").css("border", "1px solid red");
		$("#demo").html("Company Name should be in between 50 characters");
		$("#fname").focus();
		return false;
	} else {
		$("#fname").css("border", "1px solid green");
		$("#demo").html(" ");
	}
	if (businessType.selectedIndex == 0) {
		$("#demo1").html("Please choose BusinessType");
		$("#businessType").focus();
		return false;
	} else {
		$("#businessType").css("border", "1px solid green");
		$("#demo1").html("");
	}
	var empSize = $("#em").val();
	if (empSize == "") {
		$("#em").css("border", "1px solid red");

		$("#demo2").html("EmpSize should not be empty");
		$("#em").focus();

		return false;
	} else {
		$("#demo2").html(" ");
	}
	if (empSize.match(/^\d+$/)) {
		$("#em").css("border", "1px solid green");
		$("#demo2").html(" ");
	} else {
		$("#em").css("border", "1px solid red");

		$("#demo2").html("EmpSize should contains only numbers");
		$("#em").css("border", "1px solid red");
		$("#em").focus();
		return false;
	}
	if (empSize.length >= 1 && empSize.length <= 4 && empSize >= "1"
			&& empSize <= "9999") {
		$("#em").css("border", "1px solid green");

		$("#demo2").html(" ");

	} else {
		$("#demo2").html("Enter Valid Employee Size");
		$("#em").css("border", "1px solid red");

		$("#em").focus();
		return false;
	}

	var a = $("#g").prop('checked');
	var b = $("#h").prop('checked');
	if (a == false && b == false) {
		$("#demo3").html("Please Choose Employee Type");
		$("#g").focus();
		return false;
	} else {
		$("#demo3").html("");
	}
	var contact = $("#cn").val();
	if (contact == "") {

		$("#demo4").html(" ");
	} else if (contact
			.match(/^(?:(?:\+|0{0,2})91(\s*[\ -]\s*)?|[0]?)?[789]\d{9}|(\d[ -]?){10}\d$/)) {
		$("#cn").css("border", "1px solid green");

		$("#demo4").html(" ");
	} else {
		$("#cn").css("border", "1px solid red");
		$("#demo4").html("Enter valid Contact Number");
		$("#cn").focus();
		return false;
	}
	if (contact.length <= 15) {
		$("#demo4").html(" ");
	} else {
		$("#demo4").html("Enter  Contact Number between 15digits");
		$("#cn").focus();
		return false;
	}
	var email = $("#email").val();
	if (email == "") {
		$("#demo20").html("Email Address can not be empty");
		$("#email").css("border", "1px solid red");
		$("#email").focus();
		return false;
	} else {
		$("#demo20").html(" ");
	}
	if (email.match(/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)) {
		$("#email").css("border", "1px solid green");
		$("#demo20").html(" ");
	} else {
		$("#demo20").html("Enter valid Email");
		$("#email").css("border", "1px solid red");
		$("#email").focus();
		return false;
	}
	var sk = $(".serv:checked").map(function() {
		return $(this).val();
	}).get().join();
	if (sk.length == 0) {
		$("#demo5").html("Please Choose any Servicing Industry");
		$("#s").focus();
		return false;
	} else {
		$("#demo5").html("");
	}
	if (input.selectedIndex == 0) {
		$("#demo6").html("Please choose Country");
		$("#input").focus();
		return false;
	} else {
		$("#input").css("border", "1px solid green");
		$("output").css("border", "1px solid green");

		$("demo6").html(" ");
	}
	var addr = $("#ad").val();
	if (addr.length >= 255) {
		$("#ad").css("border", "1px solid red");
		$("#demo7").html("Enter address within 255 digits");
		$("#ad").focus();
		return false;
	}
	
	 var txtVal =  $('#doj').val();
     if(!isDate(txtVal))
     {
    	 $("#doj").css("border", "1px solid red");
 		$("#demo40").html("Enter valid Date");
 		$("#doj").focus();
    	 return false; 
     }
     else
    {
  		$("#demo40").html("");
    	$("#doj").css("border", "1px solid green");

 
    }
     var txtVal1 =  $('#dor').val();
     if(!isDate1(txtVal1))
     {
    	 $("#dor").css("border", "1px solid red");
  		$("#demo50").html("Enter valid Date");
  		$("#dor").focus();
    	 return false; 
     }
	else 
	{
		return true;
	}
}
function myFunction() {
	var x = document.getElementById("myTopnav");
	if (x.className === "topnav") {
		x.className += " responsive";
	} else {
		x.className = "topnav";
	}
}

function isDate(txtDate)
{
    var currVal = txtDate;
    if(currVal == '')
        return false;
    
    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
    var dtArray = currVal.match(rxDatePattern); // is format OK?
    
    if (dtArray == null) 
        return false;
    
    //Checks for mm/dd/yyyy format.
   var dtMonth = dtArray[1];
   var dtDay= dtArray[3];
   var dtYear = dtArray[5];        
    
    if (dtMonth < 1 || dtMonth > 12) 
        return false;
    else if (dtDay < 1 || dtDay> 31) 
        return false;
    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) 
        return false;
    else if (dtMonth == 2) 
    {
        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
        if (dtDay> 29 || (dtDay ==29 && !isleap)) 
                return false;
    }
    return true;
}


function isDate1(txtDate)
{
    var currVal = txtDate;

    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
    var dtArray = currVal.match(rxDatePattern); // is format OK?
    
   
    
    //Checks for mm/dd/yyyy format.
   var dtMonth = dtArray[1];
   var dtDay= dtArray[3];
   var dtYear = dtArray[5];        
    
    if (dtMonth < 1 || dtMonth > 12) 
        return false;
    else if (dtDay < 1 || dtDay> 31) 
        return false;
    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) 
        return false;
    else if (dtMonth == 2) 
    {
        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
        if (dtDay> 29 || (dtDay ==29 && !isleap)) 
                return false;
    }
    return true;
}