<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            ><fmt:message key="artist.button.back"/>
    </button>
    <br/>
    <span style="font-size: 20px;  border-bottom: 1px solid; border-color: #ccc;"><c:out
            value="${artist.getName()}"/></span>
    <c:if test="${user.getRole().getValue()=='admin'}">
        <button id="delete-button" onclick="location.href='music?command=delete_artist&id=${artist.getId()}'"
                title="<fmt:message key="button.title.delete.artist"/>"><i
                style="font-size:13px;" class='fas fa-trash-alt'></i></button>

    </c:if>
    <br/> <span style="font-size: 14px; color: #888888"><c:out value="${artist.getCountry()}"/></span>
    <br/><br/>
    <div class="page-nav">
        <a href="music?command=view_artist&id=${artist.getId()}"><fmt:message key="artist.link.tracks"/></a>
        <a style="border-bottom: 2px solid #ff6200"
           href="music?command=view_albums&artist_id=${artist.getId()}"><fmt:message key="artist.link.albums"/></a>
    </div>
    <button id="back_button" onclick="location.href='music?command=view_albums&artist_id=${artist.getId()}'"
            ><fmt:message key="album.button.back"/>
    </button>
    <br/>
    <span style="font-size: 20px;  border-bottom: 1px solid; border-color: #ccc;"><c:out
            value="${album.getTitle()}"/></span>
    <c:if test="${user.getRole().getValue()=='admin'}">
        <button id="delete-button"
                onclick="location.href='music?command=delete_album&album_id=${album.getId()}&artist_id=${artist.getId()}'"
                title="<fmt:message key="button.title.delete.album"/>"><i
                style="font-size:13px;" class='fas fa-trash-alt'></i></button>
        <br/><br/>
        <button id="edit_button"
                onclick="location.href='music?command=edit_album&album_id=${album.getId()}&artist_id=${artist.getId()}'"
                ><fmt:message key="button.addTrack"/>
        </button>

    </c:if><br/>
    <c:forEach var="track" items="${tracks}">
        <div style="display: flex; height: 37px;">
            <c:set var="track" value="${track}" scope="request"/>
            <jsp:include page="track.jsp"/>
            <c:if test="${user.getRole().getValue()=='admin'}">
                <button id="edit_button"
                        onclick="location.href='music?command=remove_track_from_album&track_id=${track.getId()}'"
                        title="<fmt:message key="button.title.remove.fromAlbum"/>"><fmt:message key="button.remove"/>
                </button>
            </c:if>
        </div>
    </c:forEach>
</div>
<c:if test="${user.getRole().getValue() == 'client'}">
    <script>
        function check(obj){
            var price= obj.value;
            var cash = ${user.getCash()};
            if(price>cash){
                obj.setCustomValidity("<fmt:message key="notification.text"/>");
            }
        }

    </script></c:if>
<jsp:include page="footer.jsp"/>
</body>
</html>

