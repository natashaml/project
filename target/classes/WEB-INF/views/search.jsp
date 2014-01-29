<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Booksjournal - Search</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<link href="../resources/css/foundation.css" rel="stylesheet">
<link href="../resources/css/normalize.css" rel="stylesheet">
<link href="../resources/css/Site.css" rel="stylesheet">

<body>
	<jsp:include page="header.jsp" />
	
	<div class="container-fluid">
		<button type="submit" class="btn btn-navbar"
			onclick="javascript:window.location.href = '?searchBy=title';return false;">
			<spring:message code="label.byTitle"></spring:message>
		</button>
		<button type="submit" class="btn btn-navbar"
			onclick="javascript:window.location.href = '?searchBy=tags';return false;">
			<spring:message code="label.byTags"></spring:message>
		</button>
				<button type="submit" class="btn btn-navbar"
			onclick="javascript:window.location.href = '?searchBy=creative';return false;">
			<spring:message code="label.byCreative"></spring:message>
		</button>

	</div>
	
	<hr>

	<div class="container-fluid">

		<c:forEach items="${books}" var="book">
			<div class="span4">
				<h2>${book.name}</h2>
				<p>${book.synopsis}</p>
				<p>
					<a class="btn" href="../read/${book.id}"><spring:message code="label.read"></spring:message> &raquo;</a>
				</p>
			</div>

		</c:forEach>
		<hr>

	</div>
	<!--/.fluid-container-->
	
	<jsp:include page="footer.jsp" />
	<script src="../resources/js/jquery.js"></script>
	<script src="../resources/js/jquery.js"></script>
	<script src="../resources/js/foundation.min.js"></script>
	<script>
				function redirectToSignUp() {
					window.location = "signup?id=0";
				}
			</script>
			<script type="text/javascript"
				src="../resources/js/search.js"> </script>
</body>
</html>
