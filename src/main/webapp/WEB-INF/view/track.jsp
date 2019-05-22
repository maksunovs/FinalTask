<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
      integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <div class="track">
        <ul>

            <li class="track info"><a href="music?command=view_artist&id=${track.getArtistId()}"><c:out value="${track.getArtist()}"/></a></li>
            <li class="track info"><c:out value="${track.getTitle()}"/></li>
        </ul>
        <c:if test="${track.getState().getValue()=='in_store'}">
            <span><ctg:currencystamp value="${track.getPrice()}"/></span>
        </c:if>
        <div class="buttons">

            <c:choose>
                <c:when test="${user.getRole().getValue()=='client'}">
                    <c:choose>

                        <c:when test="${track.getState().getValue()=='in_store'}">
                            <form  action="music?command=buy_track&track_id=${track.getId()}" method="post">
                                <button value="${param.track.getPrice()}" id="buy" type="submit" onclick="check(this)"
                                        title="<fmt:message key="button.title.buy"/>"><i
                                        class='fas fa-cart-arrow-down'></i>
                                </button>
                            </form>
                            <button onclick="location.href='music?command=add_to_cart&track_id=${track.getId()}'"
                                    title="<fmt:message key="button.title.addToCart"/>"><i
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
                            title="<fmt:message key="button.title.delete.track"/>"><i
                            class='fas fa-trash-alt'></i>
                    </button>
                </c:when>
            </c:choose>

        </div>
    </div>



<c:if test="${user.getRole().getValue() == 'client'}">
    <script>

        function check(obj) {
            var price = obj.value;
            var cash = ${user.getCash()};
            if (price > cash) {
                obj.setCustomValidity("<fmt:message key="notification.text"/>");
            }

        }

    </script>
</c:if>