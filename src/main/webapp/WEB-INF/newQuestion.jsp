<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Question</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="/css/main.css"> change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<a href="/questions">Questions Dashboard</a>
		<div>
			<c:out value="${message}"/>
		</div>
		<h1>What is your question?</h1>
		<form:form class="form" method="POST" action="/questions/new" modelAttribute="item">
			<div class="form-group row">
				<form:label class="form-control" path="question">
					Question:
					<form:textarea class="form-control" type="text" path="question" rows="4"></form:textarea>
					<form:errors path="question"></form:errors>
				</form:label>
			</div>
			<div class="form-group row">
				<label for="question_tags">Tags (Limit to 3 tags separated by commas):</label>
				<input class="form-control" type="text" name="question_tags" id="question_tags">
			</div>
			<button class="btn btn-primary mt-2">Submit</button>
		</form:form>		
	</div>
</body>
</html>
