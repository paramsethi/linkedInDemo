<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page errorPage="error.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/lndemo/css/lndemo.css" />
<title>Welcome | Linkedin Demo</title>
<script type="javascript">
function are_cookies_enabled()
{
	var cookieEnabled = (navigator.cookieEnabled) ? true : false;

	if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled)
	{ 
		document.cookie="testcookie";
		cookieEnabled = (document.cookie.indexOf("testcookie") != -1) ? true : false;
	}
	return (cookieEnabled);
}
function init(){
	if(!are_cookies_enabled){
		document.getElementById('error').display='block';
	}
}

</script>
</head>
<body onload="javascript:init();">
	<div id="main">
		<div style="padding: 10px;">
			Welcome to LinkedinDemo Application. This application demonstrates
			the usage of Linkedin authenticate, authorize, View profile and
			search API calls. Please click the button shown below to authenticate
			with Linkedin. <br />
		</div>

		<div id='error' style="display: none; font-color: red; padding: 15px;">
			<b>ERROR: Please enable cookies.</b>
		</div>

		<div style="padding: 10px;">
			<br /> <a href="/lndemo/authenticate"
				alt="Authenticate with LinkedIn"><img
				src="/lndemo/img/linkedin-small.png" atl="linkedin login button" />
			</a>
		</div>
		<br />
		<br />
		<div style="padding: 10px;">
			On the authorize application page, please click on "Ok I'll allow it"
			button as shown in screen shot. <br />
			<div>
				<br /> <img src="/lndemo/img/linkedin-authorize.png"
					alt="Linkedin authorize screenshot" width="600px" />
			</div>
		</div>
	</div>
</body>
</html>