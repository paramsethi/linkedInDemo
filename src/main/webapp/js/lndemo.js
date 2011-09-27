function validateForm() {
	var x = document.forms["search"]["query"].value;
	if (x == null || x == "" || x=="Search...") {
		alert("Search query must be filled out.");
		return false;
	}
}

