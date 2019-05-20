<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <title><fmt:message key="text.topUpBalance"/></title>
    <style>
        <%@include file="/WEB-INF/css/add.css" %>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div align=center class="content">
    <br/>

    <form action="music?command=top_up_balance" method="post" accept-charset="UTF-8">
        <h3 style="margin: 0"><fmt:message key="text.topUpBalance"/></h3><br/>
        USD
        <input type="number" name="amount" min="0.1" step="0.1" max="1000000" required><br/>
        <button name="submit" type="submit"><fmt:message key="button.topUpBalance"/></button>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
