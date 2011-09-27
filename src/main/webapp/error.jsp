<%@ page isErrorPage="true"%>

<html>
<head>
<title>Error | Linkedin demo</title>
</head>
<body>
	<div id="main">
		<div class="header">
			<div style="float: left">

				<span> <a href="/lndemo/">Login</a> </span>
			</div>
			
		</div>
		<br />

		<p>
		<h1>Error details :</h1>
		<br />

		<%
			if(request.getParameter("error")!=null)
				out.println(request.getParameter("error"));
			
			if(exception!=null)
				out.println(exception.toString());
				%>
		</p>
		<div>
			<a href="javascript:history.back();">Go Back</a> to try again.
		</div>
	</div>
</body>
</html>