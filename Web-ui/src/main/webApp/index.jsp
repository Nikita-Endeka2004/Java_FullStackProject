<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tag/hello.tld" prefix="custom" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.index" />

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fir=no">
    <title><fmt:message key="title_message" /></title>
</head>
<body>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>

<jsp:useBean id="now" class="java.util.Date" />
<p><fmt:message key="today_message" />: <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="long" /></p>

<fmt:message key="user_message" var="usermessage" />
<h2><fmt:message key="hello_message" /> <c:out value="${user.name}" default="usermessage" />!</h2>

<custom:hello name="${user.name}" />

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <form action="/Reg_Form/login" method="post">
            <label for="exampleInputEmail"><fmt:message key="email_message" />:</label>
            <input name="email" type="email" id="exampleInputEmail" placeholder="Input email">
            <label for="exampleInputPassword"><fmt:message key="password_message" />:</label>
            <input name="password" type="password" id="exampleInputPassword" placeholder="Password">
            <button type="submit"><fmt:message key="login_message" /></button>
        </form>
        <h3> <fmt:message key="go_to_registration_message" />: </h3>
        <form action="register" method="GET">
            <input type="submit" value="Go to Registration Page">
        </form>
    </c:when>
    <c:otherwise>
        <c:set var="user" value="${sessionScope.user}"/>
        <c:set var="publishings" value="${sessionScope.publishings}"/>
        <c:set var="followings" value="${sessionScope.followings}"/>
        <h4>Publishings</h4>
        <table border="1">
            <thead>
            <tr>
                <th>No</th>
                <th>name</th>
                <th>cost</th>
                <th>description</th>
                <th>tags</th>
                <th>publicationYear</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="1" />
            <c:forEach items="${publishings}" var="publishing" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${publishing.name}</td>
                    <td>${publishing.cost}</td>
                    <td>${publishing.description}</td>
                    <td>${publishing.tags}</td>
                    <td>${publishing.publicationYear}</td>
                </tr>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
            </tbody>
        </table>
        <h4>Followings</h4>
        <table border="1">
            name, cost, description, user_id, publishing_id, tags, publicationYear
            <thead>
            <tr>
                <th>No</th>
                <th>name</th>
                <th>cost</th>
                <th>description</th>
                <th>tags</th>
                <th>publicationYear</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="1" />
            <c:forEach items="${followings}" var="following" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${following.name}</td>
                    <td>${following.cost}</td>
                    <td>${following.description}</td>
                    <td>${following.tags}</td>
                    <td>${following.publicationYear}</td>
                </tr>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
            </tbody>
        </table>
        <c:set var="user" value="${sessionScope.user}"/>
        <div class="row">
            <h3><fmt:message key="name_message" />:</h3>
            <h3>${user.name}</h3>
        </div>
        <div class="row">
            <h3><fmt:message key="surname_message" />:</h3>
            <h3>${user.surname}</h3>
        </div>
        <div class="row">
            <h3><fmt:message key="gender_message" />:</h3>
            <h3>${user.gender}</h3>
        </div>
        <div class="row">
            <h3><fmt:message key="email_message" />:</h3>
            <h3>${user.email}</h3>
        </div>

        <form action="/Reg_Form/killsession" method="get">
            <label for="logout"><fmt:message key="logout_message" />:</label>
            <input type="submit" name="logout" id="logout" value="logout">
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>