<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header.jsp" />

<div>
	
	<div class="main">
	<h1>Shortener</h1>
		<div class="content">
		<h2>Login</h2>
		<div id="form">
		<form:form _method="POST" modelAttribute="login" action="/">
			<form:input path="userName" id="userName"/> <form:errors path="userName" /><br />
			<form:input path="password" id="password"/> <form:errors path="password" />
			<br />
			<input type="button" value="Join!" onclick="doAjax()" id="bt"/>
		</form:form>
		</div>
		<p id="resp"></p>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />