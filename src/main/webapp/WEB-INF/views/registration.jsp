<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp" />

<div>
	
	<div class="main">
	<h1>Shortener</h1>
		<div class="content">
		
		<div id="form">
		<h2>Register new user</h2>
		<form:form _method="PUT"  modelAttribute="user" action="/">
			<form:input path="userName" id="userName"/> <form:errors path="userName" /><br />
			<form:input path="password" id="password"/> <form:errors path="password" /><br />
			<form:input path="email" id="email"/> <form:errors path="email" />
			<br />
			<input type="button" value="Create" onclick="doAjax()" id="bt" />
		</form:form>
		</div>
		<p id="resp"></p>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />