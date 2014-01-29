<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Booksjournal</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<link href="../resources/css/Site.css" rel="stylesheet">
<link href="../resources/css/foundation.css" rel="stylesheet">
<link href="../resources/css/normalize.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="header.jsp" />
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header"><h2>${book.name}</h2></li>
						<li class="nav-header"><spring:message code="label.chapters"></spring:message></li>
						<c:forEach items="${book.chapters}" var="chapter">
							<button type="submit" class="btn btn-success"
								onClick="javascript:var converter = new Showdown.converter();											
  $('#markdown').html('<h2>${chapter.title}</h2><hr><p>'+converter.makeHtml('${chapter.text}')+'</p>')">${chapter.number}</button>
						</c:forEach>
					</ul>

			<!--/span-->
			<div id="markdown" class="span1"></div>
		</div>
	</div>
	<!--/.fluid-container-->

	<!-- javascript
    ================================================== -->
 	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../resources/js/jquery.js"></script>
	<script type="text/javascript" src="../resources/js/vendor/modernizr.js"></script>
	<script src="../resources/js/foundation.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script src="../resources/js/showdown.js"></script>
	<script>
				function redirectToSignUp() {
					window.location = "signup?id=0";
				}
			</script>
	<script type="text/javascript"
		src="../resources/js/search.js"> </script>
	<script>
		$('#sizeTen').click(function() {
			$('#markdown').css("font-size", "10px");
		});
	</script>
	<script>
		$('#sizeDefault').click(function() {
			$('#markdown').css("font-size", "14px");
		});
	</script>
	<script>
		$('#sizeTwenty').click(function() {
			$('#markdown').css("font-size", "20px");
		});
	</script>

	<script>
		$('#widthPlus').click(function() {
			$('#markdown').width($('#markdown').width() * 1.05);
		});
	</script>
	<script>
		$('#widthMinus').click(function() {
			$('#markdown').width($('#markdown').width() * 0.95);
		});
	</script>
</body>
</html>
