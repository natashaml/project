<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html xmlns:jsp="http://java.sun.com/JSP/Page" class="wrapper"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags" version="2.0">


<head>
<meta charset="utf-8">
<title>Booksjournal</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="resources/js/clock.js"></script>

<link href="resources/css/foundation.css" rel="stylesheet">
<link href="resources/css/Site.css" rel="stylesheet">
<link href="resources/css/<spring:theme code='style'/>" rel="stylesheet">
<link href="resources/css/Site.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header"><spring:message code="label.creative"></spring:message></li>
						<li><a href="search/Essay?searchBy=creative"><spring:message
									code="label.essay"></spring:message></a></li>
						<li><a href="search/Sketch?searchBy=creative"><spring:message
									code="label.sketch"></spring:message></a></li>
						<li><a href="search/Comedy?searchBy=creative"><spring:message
									code="label.comedy"></spring:message></a></li>
						<li><a href="search/Study?searchBy=creative"><spring:message
									code="label.study"></spring:message></a></li>
						<li><a href="search/Novel?searchBy=creative"><spring:message
									code="label.novel"></spring:message></a></li>
						<li><a href="search/Myth?searchBy=creative"><spring:message
									code="label.myth"></spring:message></a></li>
						<li><a href="search/Drama?searchBy=creative"><spring:message
									code="label.drama"></spring:message></a></li>
						<li><a href="search/Pastoral?searchBy=creative"><spring:message
									code="label.pastoral"></spring:message></a></li>
						<li><a href="search/Epigram?searchBy=creative"><spring:message
									code="label.epigram"></spring:message></a></li>
						<li><a href="search/Fable?searchBy=creative"><spring:message
									code="label.fable"></spring:message></a></li>
					</ul>
				</div>

				<!--/.well -->
					

			</div>
			<!--/span-->
			<div class="span9">
				<div class="hero-unit">
					<p align="center" id="con" name="${tags}"></p>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/foundation.min.js"></script>
	
	<script type="text/javascript" src="resources/js/search.js"></script>
	<script>
		function redirectToSignUp() {
			window.location = "signup?id=0";
		}
	</script>
	<script type="text/javascript" src="http://freeviral.ru/swfobject.js"></script>
<script src="./resources/js/tagsCloud.js"></script>
<script type="text/javascript">
	function digitalClock() {
	var tag = 'div.time';
	var dots = digits = '';
	var digit = tag+' span';
	var span = digit+':nth-child';
	for (i=1; i<6; i++) for (k=1; k<6; k++) dots += '<b class="c'+i+k+'"/>';
	for (i=0; i<8; i++) digits += '<span/>';
	$(tag).append(digits);
	$(digit).append(dots);
	$(span+'(3), '+span+'(6)').removeAttr('class').addClass('colon');
	function time() {
		var date = new Date();
		var hou = date.getHours().toString();
		var min = date.getMinutes().toString();
		var sec = date.getSeconds().toString();
		hou = (hou<10)?0+hou:hou;
		min = (min<10)?0+min:min;
		sec = (sec<10)?0+sec:sec;
		$(digit+'.colon').css({opacity: 1}).each(function() {
			$(this).delay(400).animate({opacity: 0},250);
		})
		$(digit).removeAttr('class');
		$(span+'(1)').addClass('d'+hou.slice(0,1));
		$(span+'(2)').addClass('d'+hou.slice(1,2));
		$(span+'(3)').addClass('colon');
		$(span+'(4)').addClass('d'+min.slice(0,1));
		$(span+'(5)').addClass('d'+min.slice(1,2));
		$(span+'(6)').addClass('colon');
		$(span+'(7)').addClass('d'+sec.slice(0,1));
		$(span+'(8)').addClass('d'+sec.slice(1,2));
		setTimeout(time,1000);
	}
	time();
} /* end of digitalClock() */

(function($) {
$(function() {

digitalClock();

})
})(jQuery)
</script>
	

</body>
	</html>