<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp" />

<div>

	<div class="main">
		<h1>Shortener</h1>
		<div class="content">
			<h2></h2>
			<form>
				<input type="text" id="email" /><br /><input type="text" id="password" /><br />
				<input type="button"
					value="Update" onclick="doAjax()" id="bt" />
			</form>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />