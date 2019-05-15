<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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
    <button id="back_button" onclick="location.href='music?command=view_playlists'"
            title="Back"><fmt:message key="playlist.button.back"/>
    </button>
    <br/>
    <span style="font-size: 20px;  border-bottom: 1px solid; border-color: #ccc;"><c:out
            value="${playlist.getTitle()}"/></span>
    <c:if test="${user.getRole().getValue()=='admin'}">
        <button id="delete-button" onclick="location.href='music?command=delete_playlist&id=${playlist.getId()}'"
                title="Delete playlist"><i
                style="font-size:13px;" class='fas fa-trash-alt'></i></button>
        <br/><br/>
        <button id="edit_button"
                onclick="location.href='music?command=add_tracks_to_playlist&id=${playlist.getId()}'"
                title="Add track"><fmt:message key="button.addTrack"/>
        </button>

    </c:if><br/><br/>
    <c:forEach var="track" items="${tracks}">
        <div style="display: flex; height: 35px; ">
            <div class="track">
                <ul>
                    <li class="track info"><a href="#"><c:out value="${track.getArtist()}"/></a></li>
                    <li class="track info"><a href="#"><c:out value="${track.getTitle()}"/></a></li>
                </ul>
                <c:if test="${track.getState().getValue()=='in_store'}">
                    <span><ctg:currencystamp value="${track.getPrice()}"/></span>
                </c:if>
                <div class="buttons">
                    <c:choose>
                        <c:when test="${user.getRole().getValue()=='client'}">
                            <c:choose>
                                <c:when test="${track.getState().getValue()=='in_store'}">
                                    <form action="music?command=buy_track&track_id=${track.getId()}" method="post">
                                        <button value="${track.getPrice()}" id="buy" type="submit" onclick="check(this)" title="Buy now"><i
                                                class='fas fa-cart-arrow-down'></i>
                                        </button>
                                    </form>
                                    <button  onclick="location.href='music?command=add_to_cart&track_id=${track.getId()}'" title="Add to cart"><i
                                            class='fas fa-plus'></i>
                                    </button>
                                </c:when>
                                <c:when test="${track.getState().getValue()=='ordered'}">
                                    <span><fmt:message key="text.inCart"/></span>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${user.getRole().getValue()=='admin'}">
                            <button onclick="location.href='music?command=delete_audiotrack&id=${track.getId()}'"
                                    title="delete"><i
                                    class='fas fa-trash-alt'></i>
                            </button>
                        </c:when>
                    </c:choose>
                </div>
            </div>

            <c:if test="${user.getRole().getValue()=='admin'}">
                <button id="edit_button"
                        onclick="location.href='music?command=remove_audiotrack_from_playlist&playlist_id=${playlist.getId()}&track_id=${track.getId()}'"
                        title="remove from playlist"><fmt:message key="button.remove"/>
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