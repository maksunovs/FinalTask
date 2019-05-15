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
    <div style="display: flex; height: 35px; ">
        <div class="track">
            <ul>
                <li class="track info"><a href="#"><c:out value="${track.getArtist()}"/></a></li>
                <li class="track info"><a href="#"><c:out value="${track.getTitle()}"/></a></li>
            </ul>
            <span><ctg:currencystamp value="${track.getPrice()}"/></span>
        </div>
        <button id="edit_button"
                onclick="location.href='music?command=remove_from_cart&track_id=${track.getId()}'"
                title="Remove from cart"><fmt:message key="button.remove"/>
        </button>
    </div>
    </c:forEach>
    <br/>
    <div style="width: 90%">
        <button style=" padding: 4px 10px; margin-left: 10px;" id="add_button" onclick="location.href='music?command=pay_order'"><fmt:message key="button.pay"/></button>
        <span><ctg:currencystamp value="${value}"/></span>
    </div>

</div>
    <jsp:include page="footer.jsp"/>
</body>
</html>