<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/hello.tld" prefix="custom"%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fir=no">
    <title>Error Page</title>
</head>
<body>

Message:
<%=exception.getMessage()%>

<form action="index" method="GET">
    <input type="submit" value="Go to Start Page">
</form>
</body>