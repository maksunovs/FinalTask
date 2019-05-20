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
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content" style="padding: 0 10px">
    <button id="back_button" onclick="location.href='music?command=view_artists'"
            ><fmt:message key="album.edit.button.back"/>
    </button>
    <br/>
    <span style="font-size: 20px;  border-bottom: 1px solid; border-color: #ccc;"><c:out
            value="${artist.getName()}"/></span>
    <button id="delete-button" onclick="location.href='music?command=delete_artist&id=${artist.getId()}'"
            title="Delete artist"><i
            style="font-size:13px;" class='fas fa-trash-alt'></i></button>
    <br/><br/>
    <div class="page-nav">
        <a href="music?command=view_artist&id=${artist.getId()}"><fmt:message key="artist.link.tracks"/></a>
        <a style="border-bottom: 2px solid #ff6200"
           href="music?command=view_albums&artist_id=${artist.getId()}"><fmt:message key="artist.link.albums"/></a>
    </div>
    <button id="back_button"
            onclick="location.href='music?command=view_album&artist_id=${artist.getId()}&album_id=${album.getId()}'"
            ><fmt:message key="album.edit.button.back"/>
    </button>
    <br/>
    <span style="font-size: 20px;  border-bottom: 1px solid; border-color: #ccc;"><c:out
            value="${album.getTitle()}"/></span>

    <c:forEach var="track" items="${tracks}">
        <div style="display: flex; height: 37px;">
            <div class="track">
                <ul>
                    <li class="track info"><a href="#"><c:out value="${track.getArtist()}"/></a></li>
                    <li class="track info"><a href="#"><c:out value="${track.getTitle()}"/></a></li>
                </ul>
                <div class="buttons">
                    <button onclick="location.href='music?command=delete_audiotrack&id=${track.getId()}'"
                            title="<fmt:message key="button.title.delete.track"/>"><i
                            class='fas fa-trash-alt'></i>
                    </button>
                </div>

            </div>
                <button id="edit_button"
                        onclick="location.href='music?command=add_track_to_album&album_id=${album.getId()}&track_id=${track.getId()}'"
                        title="<fmt:message key="button.title.add.toAlbum"/>"><fmt:message key="button.add"/>
                </button>
        </div>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>


