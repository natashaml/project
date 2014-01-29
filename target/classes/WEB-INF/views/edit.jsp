<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Booksjournal - Edit Book</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">


<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/booksjournal/resources/css/skins/markitup/style.css" />
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/booksjournal/resources/css/sets/markdown/style.css" />

<style type="text/css">
#markdown {
	text-align: center;
	margin: 10px;
	height: 300px;
	width: 500px;
	min-width: 100px;
	max-width: 750px;
	border: 1px solid #008cba;
}

</style>

<link href="http://localhost:8080/booksjournal/resources/css/foundation.css" rel="stylesheet">
<link href="http://localhost:8080/booksjournal/resources/css/normalize.css" rel="stylesheet">
<link href="http://localhost:8080/booksjournal/resources/css/Site.css" rel="stylesheet">

</head>

<body>
	<jsp:include page="header.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header"><h2>${book.name}</h2></li>
						<li class="nav-header"><spring:message code="label.chapters"></spring:message></li>
					</ul>
					<ul id="sortable">
						<c:forEach items="${book.chapters}" var="chapter">
							<li>
								<button type="submit" class="btn btn-success"
									onClick="javascript:window.location.href ='${chapter.id}';return false;">${chapter.number}</button>
							</li>
							<script></script>
						</c:forEach>
					</ul>


				</div>
				<!--/.well -->

				<button id="biber" type="submit" class="btn btn-success"
					onClick="javascript:window.location.href ='?action=addChapter';return false;">
					<spring:message code="label.addChapter"></spring:message>
				</button>
				<button id="bi" type="submit" class="btn btn-danger"
					onClick="javascript:window.location.href ='?action=delete';return false;">
					<spring:message code="label.deleteChapter"></spring:message>
				</button>
				<label class="lab"><spring:message code="label.currentChapter"></spring:message>:
					${currentChapter.number}</label>
			</div>

			<!--/span-->
			<form:form method="post" commandName="saveChapterForm">
				<input id="chapterTitle" type="text" placeholder="Chapter name"
					name="title" value="${currentChapter.title}">
				<p align="center">
					<textarea id="markdown" class="span1" placeholder="Chapter text"
						name="chapterText">${currentChapter.text}</textarea>
				</p>
				<button id="saveChapter" type="submit" class="btn btn-primary">
					<spring:message code="label.save"></spring:message>
				</button>
			</form:form>
		</div>
		<!--/row-->

		<jsp:include page="footer.jsp" />

	</div>
	<!--/.fluid-container-->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://localhost:8080/booksjournal/resources/js/jquery.js"></script>
	<script
		src="http://localhost:8080/booksjournal/resources/js/foundation.min.js"></script>
	<script
		src="http://localhost:8080/booksjournal/resources/js/showdown.js"></script>
	<script>
		function redirectToSignUp() {
			window.location = "signup?id=0";
		}
	</script>
	<script>
		$(function() {
			$("#sortable").sortable({
				handle : 'button',
				cancel : ''

			});
			$("#sortable").disableSelection();
		});
	</script>
	<script type="text/javascript"
		src="http://localhost:8080/booksjournal/resources/js/search.js">
		
	</script>

	<script type="text/javascript"
		src="http://localhost:8080/booksjournal/resources/js/jquery-1.9.1.js">
		
	</script>

	<script type="text/javascript"
		src="http://localhost:8080/booksjournal/resources/js/jquery-ui.js">
		
	</script>

	<script type="text/javascript"
		src="http://localhost:8080/booksjournal/resources/js/jquery.markitup.js"></script>
	<script type="text/javascript"
		src="http://localhost:8080/booksjournal/resources/js/set.js"></script>
	<script type="text/javascript"
		src="http://localhost:8080/booksjournal/resources/js/vendor/modernizr.js"></script>
	<script>
		$(document).ready(function() {
			$('#markdown').markItUp(mySettings);
		});
	</script>
</body>
</html>
