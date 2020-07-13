<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>

<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark ">
		<div class="navbar-brand" href="#">SportsBetting</div>
		<div class="collapse navbar-collapse">
			<div class="navbar-nav">

				<a class="nav-item nav-link active" href="<c:url value="/home" />">
					${text.header.home} </a> <a class="nav-item nav-link active"
					href="<c:url value="/events" />"> ${text.header.events}</a>


				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">${text.header.language}</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="<c:url value="${text.header.engUrl}" />">English</a> 
						<a class="dropdown-item" href="<c:url value="${text.header.hunUrl}" />">Magyar</a>
						
					</div>
				</div>

			</div>
		</div>
		<div class="navbar-nav navbar-right">
			<a href="<c:url value="/logout" />">
				<button
					class="btn btn-outline-secondary btn-nav text-white border border-white"
					type="submit">${text.header.logout}</button>
			</a>
		</div>
	</nav>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"
		integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm"
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
