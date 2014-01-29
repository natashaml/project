<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Graph</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="./resources/css/foundation.css" rel="stylesheet">
<link href="./resources/css/normalize.css" rel="stylesheet">
<link href="./resources/css/Site.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>

<body>

	<jsp:include page="header.jsp" />


	<div id="chartContainer" style="max-width: 700px; height: 300px;"></div>
	<!-- /container -->
	<jsp:include page="footer.jsp" />

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./resources/js/jquery.js"></script>
	<script type="text/javascript" src="./resources/js/vendor/modernizr.js"></script>
	<script src="./resources/js/foundation.min.js"></script>

	<script>
		function redirectToSignUp() {
			window.location = "signup?id=0";
		}
	</script>
	<script type="text/javascript"
		src="./resources/js/search.js">
		
	</script>
	<script type="text/javascript">
	$(document).ready(
			$(function ()  
				{
				var dataSource=[];
				<c:forEach items="${books}" var="book">
				dataSource.push({creative:'${book.name}',count:${book.rating}});
					
				

			</c:forEach>
 
</script>
</body>
</html>
