<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<title></title>
</head>
<body>
	<main> <!-- Save player detalils form -->
	<form class="border border-primary rounded m-3" method="POST">
		<h6 class="text-left pl-2 pb-3 pt-2 bg-primary text-white">
			Account Details</h6>
		<div class="m-3">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">Name</span>
				</div>
				<input type="text" class="form-control" name="name" value="${player.name}" />
			</div>
		</div>
		<div class="m-3">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">Date of Birth</span>
				</div>
				<input type="date" class="form-control" name="date" value="${player.birth}" />
			</div>
		</div>
		<div class="m-3">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">Account Number</span>
				</div>
				<input type="text" class="form-control" name="account" value="${player.accountNumber}" />
			</div>
		</div>
		<div class="m-3">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">Currency</span>
				</div>
				<input type="text" class="form-control" readonly value="${player.currency}" />
			</div>
		</div>
		<div class="m-3">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">Balance</span>
				</div>
				<input type="number" class="form-control" name="balance" value="${player.balance}" />
			</div>
		</div>
		<div class="m-3 mb-0">
			<input class="btn btn-primary" type="submit" value="Save"/>
		</div>
	</form>
	<!-- Wagers -->
	<section class="border border-primary rounded m-3">
		<h6 class="text-left pl-2 pb-3 pt-2 bg-primary text-white">Wagers</h6>
		<table class="table">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">#</th>
					<th scope="col">Title</th>
					<th scope="col">Type</th>
					<th scope="col">Bet Type</th>
					<th scope="col">Outcome</th>
					<th scope="col">Odd</th>
					<th scope="col">Amount</th>
					<th scope="col">Win</th>
					<th scope="col">Processed</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="wager" items="${wagers}" varStatus="loop">
					<tr>
						<td><c:if test="${wager.processed == '-'}">
								<a href="<c:url value="${wager.deleteURL}"/>">
									<button class="btn btn-primary" type="submit">${text.remove}</button>
								</a>
							</c:if></td>
						<td><c:out value="${loop.count}" /></td>
						<td>${wager.eventTitle}</td>
						<td>${wager.eventType}</td>
						<td>${wager.betType}</td>
						<td>${wager.outcomeValue}</td>
						<td>${wager.outcomeOdd}</td>
						<td>${wager.wagerAmount}</td>
						<td>${wager.win}</td>
						<td>${wager.processed}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>


	</main>
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