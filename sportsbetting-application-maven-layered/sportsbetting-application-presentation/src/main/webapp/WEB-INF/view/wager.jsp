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

	<form:form modelAttribute="form"
		class="border border-primary rounded m-3">
		<h6 class="text-left pl-2 pb-3 pt-2 bg-primary text-white">
			${text.pleaseSelect }</h6>

		<div class="m-3">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">Outcome (select option)</span>
				</div>
				<form:select items="${form.outcomes}" path="outcomeId"
					itemValue="id" itemLabel="description"></form:select>
			</div>
		</div>

		<div class="m-3">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">Amount</span>
				</div>
				<form:input type="number" path="amount" class="form-control" />
			</div>
		</div>

		<form:button class="btn btn-primary" type="submit" name="save"
			value="true">Save</form:button>
		<form:button class="btn btn-primary" type="submit" name="save"
			value="false">Cancel</form:button>
	</form:form> </main>
</body>
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

</html>