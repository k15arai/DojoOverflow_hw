<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Questions Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div id="page_header">
			<div id="page_header__message">
				<c:out value="${message}"/>
			</div>
		</div>
		<h1>Questions Dashboard</h1>
		<div id="active_area">
			<table class="table">
				<tr class="table-info">
					<th>Question</th>
					<th>Tags</th>
					<th>Actions</th>
				</tr>
			<c:forEach var="question" items="${questions}">
				<tr>
					<td><a href="/questions/<c:out value="${question.id}" />">${question.question}</a></td>
					<td>
					<c:forEach var="tag" items="${question.getTags()}">
						<c:out value="${tag.getTag().subject}"/> ||
					</c:forEach>
					</td>
					<td><a href="/questions/delete/<c:out value="${question.id}" />">DELETE</a> || <a href="/tags">View All Tags</a></td>
				</tr>
				
			</c:forEach>
			</table>
		</div>
			
			
		<div id="buttons">
			<a href="/questions/new">New Question</a> 
		</div>
	</div>
</body>
</html>
