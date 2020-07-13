<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title></title>
</head>
<body>
	<main> <!-- Navigation bar --> <jsp:include page="header.jsp" />

	<div class="border border-primary rounded m-3">
		<h3>${text.selectedEvent}</h3>
		<span> ${event.title}</span> <span> ${event.type}</span> <span>
			${event.date}</span>
	</div>

	<section class="border border-primary rounded m-3">
		<h5 class="text-left pl-2 pb-3 pt-2 bg-primary text-white">${text.bets}</h5>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">${text.betType}</th>
					<th scope="col">${text.description}</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bet" items="${bets}">
					<tr>
						<td>${bet.type}</td>
						<td>${bet.description}</td>
						<td><a href="<c:url value="${bet.url}"/>">
								<button class="btn btn-primary" type="submit">${text.newWager}</button>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	</main>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>