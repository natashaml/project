<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Booksjournal - ${owner.name}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">



<link href="../resources/css/foundation.css" rel="stylesheet">
<link href="../resources/css/normalize.css" rel="stylesheet">
<link href="../resources/css/Site.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="header.jsp" />
	<c:if test="${isMyPage eq 'YES'}">
		<div class="container-fluid">
			<p>
				<a class="btn"
					href="../users/${owner.id}/newBook"><spring:message
						code="label.newBook"></spring:message> &raquo;</a>
			</p>

		</div>
	</c:if>
	<hr>

	<div class="container-fluid gradient">
	

		<c:forEach items="${owner.books}" var="book">
			<div class="span4">
				<h2>${book.name}</h2>
				<p>${book.synopsis}</p>
				<p>
					<a class="btn" href="../read/${book.id}"><spring:message
							code="label.read"></spring:message> &raquo;</a>
				</p>
				<c:if test="${isMyPage eq 'YES'}">
					<p>
						<a class="btn"
							href="../users/${owner.id}/${book.id}"><spring:message
								code="label.edit"></spring:message> &raquo;</a>
					</p>
					<p>
						<a class="btn" href="../delete/${book.id}"><spring:message code="label.delete"></spring:message>
							&raquo;</a>
					</p>
				</c:if>
			</div>

		</c:forEach>


	</div>
	<jsp:include page="footer.jsp" />

	<script src="../resources/js/jquery.js"></script>
		<script type="text/javascript" src="../resources/js/vendor/modernizr.js"></script>
		<script src="../resources/js/foundation.min.js"></script>
	<script>
		function redirectToSignUp() {
			window.location = "signup?id=0";
		}
	</script>
	<script type="text/javascript"
		src="../resources/js/search.js">
		
	</script>
</body>
</html>
