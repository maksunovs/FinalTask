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
    <title><c:out value="${playlist.getTitle()}"/></title>
    <style>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content" style="padding: 0 10px">
    <button id="back_button" onclick="location.href='music?command=view_playlist&id=${playlist.getId()}'"
            ><fmt:message key="playlist.edit.button.back"/>
    </button>
    <br/>
    <span style="font-size: 20px; margin: 0 10px; border-bottom: 1px solid; border-color: #ccc;"><c:out
            value="${playlist.getTitle()}"/></span>
    <br/><br/>
    <c:forEach var="audiotrack" items="${audiotracks}">
        <div style="display: flex; height: 37px;">
            <c:set var="track" value="${audiotrack}" scope="request"/>
            <jsp:include page="track.jsp"/>
            <button id="edit_button"
                    onclick="location.href='music?command=add_track_to_playlist&playlist_id=${playlist.getId()}&audiotrack_id=${audiotrack.getId()}'"
                    title="<fmt:message key="button.title.add.toPlaylist"/>"><fmt:message key="button.add"/>
            </button>
        </div>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>