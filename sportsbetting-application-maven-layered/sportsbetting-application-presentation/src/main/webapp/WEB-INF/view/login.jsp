<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- <link rel="stylesheet" type="text/css" href="login.css"> -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>SportsBetting</title>
</head>
<body>
<body>
	<header id="header" class="p-3 mb-2 bg-dark text-white text-center">
		<h1>${text.title}</h1>
		<div id="description"> ${text.description} </div>
	</header>
	<main>
	<h3 class="pb-3 text-center">
		<a href="#" class="card-link">${text.login}</a> ${text.orText} <a href=""
			class="card-link m-0">${text.register}</a> ${text.toStart}
	</h3>
	<div class="d-flex justify-content-center">

		<form action='<spring:url value="/login"/>' method="post"
			class="border border-primary rounded form-signin">
			<h4 class="text-left pl-2 pb-3 pt-2 bg-primary text-white">${text.loginButton}</h4>

			<div class="form-group mt-3 mx-2">
				<input type="text" name="username" class="form-control"
					placeholder="Email" />
			</div>
			<div class="form-group form-group mt-3 mx-2">
				<input type="password" name="password" class="form-control"
					placeholder="${text.passwordField}" />
			</div>
			<div class="form-group form-group mx-2 float-left">
				<button type="submit" class="btn btn-primary">${text.loginButton}</button>
			</div>
		</form>
	</div>
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