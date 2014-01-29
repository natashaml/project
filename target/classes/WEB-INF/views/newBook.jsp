<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<style type="text/css">
span.error {
	color: red;
}
</style>

<html>
<head>
<meta charset="utf-8">
<title><spring:message code="label.creatingBook"></spring:message></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">



<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.container {
	max-width: 300px;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin textarea {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<link href="../../resources/css/foundation.css" rel="stylesheet">
<link href="../../resources/css/normalize.css" rel="stylesheet">
<link href="../../resources/css/Site.css" rel="stylesheet">
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h2 class="form-signing-heading">
			<spring:message code="label.creatingBook"></spring:message>
		</h2>
		<form:form method="post" commandName="addBookForm">
			<input type="text" class="input-block-level"
				placeholder=<spring:message code="label.bookName"></spring:message>
				name="title">
			<input id="tags" type="text" class="input-block-level"
				placeholder=<spring:message code="label.tags" ></spring:message>
				name="tags" data-provide="typeahead" />
			<textarea class="input-block-level" name="synopsis"
				placeholder=<spring:message code="label.synopsis"></spring:message>></textarea>
			<label><spring:message code="label.creative"></spring:message></label>
			<select name="creative">
				<option><spring:message code="label.essay"></spring:message></option>
				<option><spring:message code="label.sketch"></spring:message>
				<option><spring:message code="label.comedy"></spring:message></option>
				<option><spring:message code="label.study"></spring:message></option>
				<option><spring:message code="label.novel"></spring:message></option>
				<option><spring:message code="label.myth"></spring:message></option>
				<option><spring:message code="label.drama"></spring:message></option>
				<option><spring:message code="label.pastoral"></spring:message></option>
				<option><spring:message code="label.epigram"></spring:message></option>
				<option><spring:message code="label.fable"></spring:message></option>
			</select>
			<button class="btn btn-large btn-success" type="submit">
				<spring:message code="label.crBook"></spring:message>
			</button>
		</form:form>
	</div>
	<jsp:include page="footer.jsp" />

	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../../resources/js/jquery.js"></script>
	<script src="../../resources/js/jquery.js"></script>
	<script type="text/javascript" src="../../resources/js/vendor/modernizr.js"></script>
	<script src="../../resources/js/foundation.min.js"></script>
	<script>
		function redirectToSignUp() {
			window.location = "signup?id=0";
		}
	</script>
	<script type="text/javascript"
		src="../../resources/js/search.js">
		
	</script>
	<input type="text" id="test" data-provide="typeahead" />

	<script>
		$('.tags').typeahead({
			source : function(query, process) {
				return $.get('/autocomplete', {
					query : query
				}, function(data) {
					return process(data);
				});
			}
		});
	</script>
</body>
</html>