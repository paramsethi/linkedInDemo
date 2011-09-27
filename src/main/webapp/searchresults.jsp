<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="result" class="com.ln.model.SearchResult"
	scope="request" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page errorPage="error.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/lndemo/css/lndemo.css" />
<script type="text/javascript" src="/lndemo/js/lndemo.js"></script>
<title>"${result.query}" Search Results | Linkedin Demo</title>
</head>
<body>
	<div id="main">

		<div class="header">
			<div style="float: left">
				<span> <a href="/lndemo/profile">View Profile</a> </span> | <span>
					<a href="javascript:history.back();">Go Back</a> </span> | <span> <a
					href="/lndemo/signout">Sign out</a> </span>
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
		<c:choose>
			<c:when test='${result.totalCount<=0 }'>
				<h2>No results found for : "${result.query}"</h2>
			</c:when>
			<c:otherwise>
				<div id="header">
					<h2>Search results for : "${result.query}"</h2>
					Showing ${result.start +1} - ${result.start + result.count } of
					${result.totalCount} Total.
				</div>

				<div id="results">
					<c:set var="userResults" value="${result.users}" scope="request"></c:set>
					<c:if test='${fn:length(userResults)>1}'>
						<div>
							<c:if test='${result.start+10<result.totalCount }'>
								<a
									href="/lndemo/search?query=${result.query}&start=${result.start+10}">Next
								</a>
							</c:if>
							<c:if test='${result.start-10 >= 0 }'>
								<a
									href="/lndemo/search?query=${result.query}&start=${result.start-10}">
									Prev </a>
							</c:if>
						</div>
						<table>
							<c:forEach var="user" items="${userResults}">
								<tr>
									<td width="50px;"><c:if
											test='${not empty user.pictureUrl}'>
											<div class="imgholder">
												<img src="${user.pictureUrl }"
													alt="${user.firstName} ${user.lastName}" />
											</div>
										</c:if>
									</td>
									<td>
										<div id="user-data">
											<a href="/lndemo/profile?id=${user.id }"
												title="Detailed profile of ${user.firstName} ${user.lastName}">${user.firstName}
												${user.lastName} <c:if test='${user.distance>0}'>
													<sup>${user.distance }</sup>
												</c:if> </a>
											<c:if test='${not empty user.headline }'>
												<br />
												<b>${user.headline }</b>
											</c:if>
											<c:if test='${not empty user.city}'>
												<br />${user.city},</c:if>
											<c:if test='${not empty user.country}'>
										${user.country }</c:if>
											<c:if test='${not empty user.industry}'>| ${user.industry }</c:if>
										</div></td>
								</tr>
								<tr></tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
