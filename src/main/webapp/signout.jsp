<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page errorPage="error.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/lndemo/css/lndemo.css" />
<title><%=request.getParameter("msg")%> | Linkedin Demo</title>
</head>
<body>
	<div id="main">
		<div style="float: left; padding: 10px;">
			<span> <a href="/lndemo">Login</a> </span>
		</div>
		<br /> <div style="padding:15 px;">Thank you for using Linkedin Demo. <br /></div>
		<div style="padding: 10px;">
			<h2><%=request.getParameter("msg")%></h2>
		</div>
	</div>
</body>
</html>



