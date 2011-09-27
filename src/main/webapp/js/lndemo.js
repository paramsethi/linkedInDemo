function validateForm() {
	var x = document.forms["search"]["query"].value;
	if (x == null || x == "" || x=="Search...") {
		alert("Please enter a valid search query.");
		return false;
	}
}

