<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="user" class="com.ln.model.User" scope="request" />
<%@ page errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/lndemo/css/lndemo.css" />
<script type="text/javascript" src="/lndemo/js/lndemo.js"></script>
<title>${user.firstName} ${user.lastName} | Linkedin Demo</title>
</head>
<body>
	<div id="main">
		<div class="header">
			<div style="float: left">

				<span> <c:choose>
						<c:when test='${user.distance>0 }'>
							<a href="/lndemo/profile">View Profile</a>
						</c:when>
						<c:otherwise>View Profile</c:otherwise>
					</c:choose> </span> | <span> <a href="/lndemo/signout">Sign out</a> </span>
			</div>
			<div class="search-box">
				<form name="search" action="/lndemo/search" method="post"
					accept-charset="UTF-8" onsubmit="javascript:return validateForm();">
					<input type="text" name="query" value="Search..."
						onclick="javascript:this.value='';"
						onblur="javascript:if(this.value=='')this.value='Search...'" />&nbsp;<input
						type="submit" name="submit" value="Search" /> <input
						type="hidden" name="firstname" value="${user.firstName}" /> <input
						type="hidden" name="lastname" value="${user.lastName}" /> <input
						type="hidden" name="headline" value="${user.headline}" />
				</form>
			</div>
		</div>
		<br />
		<div class="profile-header">
			<c:if test='${not empty user.pictureUrl}'>
				<div class="imgholder">
					<img src="${user.pictureUrl }"
						alt="${user.firstName} ${user.lastName}" />
				</div>
			</c:if>
			<h1>${user.firstName} ${user.lastName}</h1>
			<p>${user.headline}</p>
			<p>${user.city }, ${user.country } | ${user.industry }</p>

			<div>
				<table>
					<tr>
						<td>Current</td>
						<td>${user.currPosition }</td>
					</tr>
					<tr>
						<td>Recommendations</td>
						<td>${user.numRecommenders } people have recommended
							${user.firstName}</td>
					</tr>
					<tr>
						<td>Connections</td>
						<td>${user.numConnections } connections</td>
					</tr>
					<tr>
						<td>Public Profile</td>
						<td><a href="${user.publicProfileUrl }"
							alt="public profile for ${user.firstName}">${user.publicProfileUrl}</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<c:if test='${not empty user.summary}'>
			<div>
				<h2>Summary</h2>
				<div>${user.summary }</div>
			</div>
		</c:if>
		<c:if test='${not empty user.honors}'>
			<div>
				<h2>Honors and Awards</h2>
				<div>${user.honors }</div>
			</div>
		</c:if>
		<c:if test='${not empty user.associations}'>
			<div>
				<h2>Associations</h2>

				<div>${user.associations }</div>

			</div>
		</c:if>
	</div>

</body>
</html>



