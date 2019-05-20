<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="text.playlistList"/></title>
    <style>
        <%@include file="/WEB-INF/css/view.css" %>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content" style="padding: 0 10px">
    <br/>
    <span style="font-size: 20px; margin-right: 10px;float: left;"><fmt:message key="text.playlistList"/></span>
    <c:if test="${user.getRole().getValue()=='admin'}">
        <form action="music?command=save_playlist" method="post">
            <input type="text" name="title" placeholder="<fmt:message key="form.newPlaylist.input"/>" required
                   pattern="\s*([0-9]|[A-Za-zА-Яа-яЁё])([0-9]|[A-Za-zА-Яа-яЁё]|\s){1,90}"/>
            <button id="add_button" type="submit" ><fmt:message key="button.add"/></button>
        </form>
    </c:if>
    <br/><br/>
    <c:forEach var="playlist" items="${playlists}">
        <button id="open_button"
                onclick="location.href='music?command=view_playlist&id=${playlist.getId()}'"><c:out
                value="${playlist.getTitle()}"/></button>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>





