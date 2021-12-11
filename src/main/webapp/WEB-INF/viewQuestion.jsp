<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Profile</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<a href="/questions">Questions Dashboard</a>
		<div>
			<div id="page_header__message">
				<c:out value="${message}"/>
			</div>
		</div>
		<h1>${question.question}</h1>
		<div class="row">
		<div class="col-sm">
			<h2>Tags</h2>
			<c:forEach var="tag" items="${question.getTags()}">
				<c:out value="${tag.getTag().subject}"/> ||
			</c:forEach>
			
			<table class="table">
				<tr class="table-info">
					<th>Answers</th>
					<th>Actions</th>
				</tr>
			<c:forEach var="answer" items="${answers}">
				<tr>
					<td><c:out value="${answer.answer}" /></td>
					<td><a href="/answers/delete/<c:out value="${answer.id}" />">DELETE</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		<div class="col-sm">
			<h2>Add your Answer</h2>
			<form:form method="POST" action="/answers/add" modelAttribute="submission">
				<input type="hidden" name="questionId" id="questionId" value="${question.id}"/>
				<div class="form-group row">
					<form:label class="form-control" path="answer">
						Answer:
						<form:textarea class="form-control" type="text" path="answer" rows="4"></form:textarea>
						<form:errors path="answer"></form:errors>
					</form:label>
				</div>	
				<button>Answer It</button>
			</form:form>
		</div>
	</div>
	</div>  
</body>
</html>
