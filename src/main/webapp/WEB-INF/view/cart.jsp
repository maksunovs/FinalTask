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
    <title><fmt:message key="text.cart"/></title>
    <style>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content" style="padding: 0 10px">
    <br/>
    <span style="font-size: 20px;  border-bottom: 1px solid; border-color: #ccc;"><fmt:message key="text.cart"/></span>
    <br/><br/>
    <c:forEach var="track" items="${tracks}">
    <div style="display: flex; height: 37px; ">
        <div class="track">
            <ul>
                <li class="track info"><a href="music?command=view_artist&id=${track.getArtistId()}"><c:out value="${track.getArtist()}"/></a></li>
                <li class="track info"><c:out value="${track.getTitle()}"/></li>
            </ul>
            <span><ctg:currencystamp value="${track.getPrice()}"/></span>
            <div class="buttons">
                <form action="music?command=buy_track&track_id=${track.getId()}" method="post">
                    <button value="${track.getPrice()}" id="buy" type="submit" onclick="check(this)"
                            title="<fmt:message key="button.title.buy"/>"><i
                            class='fas fa-plus'></i>
                    </button>
                </form>
            </div>
        </div>
        <button id="edit_button"
                onclick="location.href='music?command=remove_from_cart&track_id=${track.getId()}'"
                ><fmt:message key="button.remove"/>
        </button>
    </div>
    </c:forEach>
    <br/>
    <div style="width: 90%; display: flex;">
        <form action="music?command=pay_cart" method="post">

        <button value="${value}" style=" padding: 4px 10px; " type="submit" id="add_button" onclick=check(this)><fmt:message key="button.pay"/></button>
        </form>
        <div style="padding: 4px 20px;"><ctg:currencystamp value="${value}"/></div>
    </div>

</div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
<script>
    function check(obj){
        var price= obj.value;
        var cash = ${user.getCash()};
        if(price>cash){
            obj.setCustomValidity("<fmt:message key="notification.text"/>");
        }
    }

</script>