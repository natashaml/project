<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html xmlns:jsp="http://java.sun.com/JSP/Page" class="wrapper"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags" version="2.0">


	<c:url value='./j_spring_security_logout' var="securityLogout" />
	
				<div class="nav-collapse collapse">

					</form>
					<ul class="nav">
						<li class="active"><a href="http://localhost:8080/booksjournal/"><spring:message
									code="label.home"></spring:message></a></li>

						<li><a href="search/a?searchBy=gallery"><spring:message
									code="label.gallery"></spring:message></a></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	</html>>