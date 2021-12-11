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
    <title>View All Tags</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<a href="/questions">Questions Dashboard</a>		
		<div id="page_header">
			<div id="page_header__message">
				<c:out value="${message}"/>
			</div>
		</div>
		<h1>Tags Dashboard</h1>
		<div id="active_area">
			<table class="table">
				<tr class="table-info">
					<th>Tags</th>
					<th>Actions</th>
				</tr>
			<c:forEach var="tag" items="${tags}">
				<tr>
					<td>${tag.subject}</td>
					<td><a href="/tags/delete/<c:out value="${tag.id}" />">DELETE</a></td>
				</tr>
				
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>