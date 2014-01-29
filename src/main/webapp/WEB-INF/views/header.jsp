<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html xmlns:jsp="http://java.sun.com/JSP/Page" class="wrapper"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags" version="2.0">


	<c:url value='./j_spring_security_logout' var="securityLogout" />
		<div class="promo-head">
			<div class="error">
				<script type="text/javascript" src="http://localhost:8080/booksjournal/resources/js/orphus.js"></script>
					<script src="http://localhost:8080/booksjournal/resources/js/foundation.min.js"></script>
				<a href="http://orphus.ru" id="orphus" target="_blank"><img alt="System Orphus" src="http://localhost:8080/booksjournal/resources/img/orph.gif" border="0" width="197" height="39" /></a>
			</div>
			<div class="banner"></div>
		</div>		
				<div class="nav-collapse collapse">

					<form class="navbar-form pull-right">
					  <input id="search" type="text"
							class="search-query"
							placeholder=<spring:message code="label.search"></spring:message>>

						<c:if test="${person eq 'Guest'}">
							<button type="submit" class="btn btn-success"
								onClick="javascript:window.location.href ='login';return false;">
								<spring:message code="label.login"></spring:message>
							</button>

							<button type="submit" class="btn btn-warning"
								onclick="javascript:redirectToSignUp();return false;">
								<spring:message code="label.registration"></spring:message>
							</button>
						</c:if>

						<c:if test="${(person eq 'User') || (person eq 'Admin')}">
							<button type="submit" class="btn btn-success"
								onClick="javascript:window.location.href ='${securityLogout}';return false;">
								<spring:message code="label.logout"></spring:message>
							</button>
						</c:if>

					</form>
					<ul class="nav">
						<li class="active"><a href="http://localhost:8080/booksjournal"><spring:message
									code="label.home"></spring:message></a></li>

						<c:if test="${person eq 'Admin'}">
							<li><a href="http://localhost:8080/booksjournal/admin"><spring:message
										code="label.adminPage"></spring:message></a></li>
						</c:if>

						<c:if test="${(person eq 'User') || (person eq 'Admin')}">
							<li><a href="http://localhost:8080/booksjournal/users/${user.id}"><spring:message
										code="label.myPage"></spring:message></a></li>
						</c:if>

						<li><a href="http://localhost:8080/booksjournal/gallery"><spring:message
									code="label.gallery"></spring:message></a></li>

					</ul>
					<div class="time"></div>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>