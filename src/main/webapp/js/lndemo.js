function validateForm() {
	var x = document.forms["search"]["query"].value;
	if (x == null || x == "" || x=="Search...") {
		alert("Search query must be filled out and should contain valid alpha numeric characters.");
		return false;
	}
}

function checkSearchQuery(qry){
	if (qry != null && qry != "") {
		if(qry.indexOf(" ") > -1)
			document.getElementById("queryMsg").display="";
	}
}
