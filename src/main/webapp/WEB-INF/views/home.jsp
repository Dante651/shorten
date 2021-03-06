<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header.jsp" />

<div>
	
	<div class="main">
	<h1>Shortener</h1>
		<div class="content">
		<h2>Generate new address</h2>
		<div id="form">
		<form:form _method="PUT"  modelAttribute="simpleUrl" action="/add">
			<form:input path="url" id="url"/> <form:errors path="url" />
			<br />
			<input type="button" value="Generate" onclick="doAjax()" id="button"/>
		</form:form>
		</div>
		<p id="resp"></p>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />