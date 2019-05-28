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
    <title><fmt:message key="text.newArtist"/></title>
    <style>
        <%@include file="/WEB-INF/css/add.css" %>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div align=center class="content">
    <br/>

    <form action="music?command=save_artist" method="post" accept-charset="UTF-8">
        <h3 style="margin: 0"><fmt:message key="text.newArtist"/></h3>
        <input placeholder="<fmt:message key="form.newArtist.input.name"/>" type="text" name="name" tabindex="1" pattern="\s*([0-9]|[A-Za-zА-Яа-яЁё])([0-9]|[A-Za-zА-Яа-яЁё!\./'-]|\s){1,89}" required autofocus><br/>
        <input placeholder="<fmt:message key="form.newArtist.input.country"/>" type="text" name="country" tabindex="2" pattern="\s*[A-Za-zА-Яа-яЁё]([A-Za-zА-Яа-яЁё]|\s){1,89}" required><br/>
        <button name="submit" type="submit"><fmt:message key="button.save"/></button>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
