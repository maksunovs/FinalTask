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
    <title><fmt:message key="text.newTrack"/></title>
    <style>
        <%@include file="/WEB-INF/css/add.css" %>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div align=center class="content">
    <br/>

    <form action="music?command=save_audiotrack" method="post" accept-charset="UTF-8">
        <h3 style="margin: 0"><fmt:message key="text.newTrack"/></h3>
        <select name="artist_id" required>
            <option hidden><fmt:message key="form.newTrack.input.artist"/></option>
            <c:forEach var="artist" items="${artists}">
                <option value="${artist.getId().toString()}"><c:out value="${artist.getName()}"/></option>
            </c:forEach>
        </select><br/>
        <input placeholder="<fmt:message key="form.newTrack.input.title"/>" type="text" name="title" pattern="\s*([0-9]|[A-Za-zА-Яа-яЁё])([0-9]|[A-Za-zА-Яа-яЁё]|\s)+" tabindex="2" required><br/>
        <select name="genre" required>
            <option hidden><fmt:message key="form.newTrack.input.genre"/></option>
            <option value="Pop">Pop</option>
            <option value="Rock">Rock</option>
            <option value="Metal">Metal</option>
            <option value="Rap">Rap</option>
            <option value="Dance">Dance</option>
            <option value="Indie">Indie</option>
            <option value="Jazz">Jazz</option>
            <option value="Punk">Punk</option>
        </select><br/>
        <input placeholder="<fmt:message key="form.newTrack.input.price"/>" name="price" type="number" min="0" max="100000" step="0.01" tabindex="4" required><br/>
        <button name="submit" type="submit"><fmt:message key="button.save"/></button>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>