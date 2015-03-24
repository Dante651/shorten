<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header.jsp" />

<div>
	
	<div class="main">
	<h1>Shortener</h1>
		<div class="content">
		<h2>Users List</h2>
		<p id="resp"></p>
		<table id="contentList">

		</table>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />