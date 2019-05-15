<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <title><c:out value="${artist.getName()}"/></title>
    <style>
        <%@include file="/WEB-INF/css/main.css"%>
        <%@include file="/WEB-INF/css/view.css"%>
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content" style="padding: 0 10px">
    <button id="back_button" onclick="location.href='music?command=view_artists'"
            title="Back"><fmt:message key="artist.button.back"/>
    </button>
    <br/>
    <span style="font-size: 20px;  border-bottom: 1px solid; border-color: #ccc;"><c:out
            value="${artist.getName()}"/></span>
    <c:if test="${user.getRole().getValue()=='admin'}">
        <button id="delete-button" onclick="location.href='music?command=delete_artist&id=${artist.getId()}'"
                title="Delete artist"><i
                style="font-size:13px;" class='fas fa-trash-alt'></i></button>
    </c:if>
    <br/><br/>
    <div class="page-nav">
        <a href="music?command=view_artist&id=${artist.getId()}"><fmt:message key="artist.link.tracks"/></a>
        <a style="border-bottom: 2px solid #ff6200" href="music?command=view_albums&artist_id=${artist.getId()}"><fmt:message key="artist.link.albums"/></a>
    </div>
    <br/>
    <c:if test="${user.getRole().getValue()=='admin'}">
        <form action="music?command=save_album&artist_id=${artist.getId()}" method="post">
            <input type="text" name="title" placeholder="<fmt:message key="form.newAlbum.input"/>" required pattern="\s*[A-Za-zА-Яа-яЁё]([A-Za-zА-Яа-яЁё]|\s)+"/>
            <button id="add_button" type="submit" title="New album"><fmt:message key="button.add"/></button>
        </form>
    </c:if>

    <c:forEach var="album" items="${albums}">
        <button id="open_button"
                onclick="location.href='music?command=view_album&album_id=${album.getId()}&artist_id=${artist.getId()}'"><c:out
                value="${album.getTitle()}"/></button>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
