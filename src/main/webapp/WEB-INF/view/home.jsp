<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Music store</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <div class="row">
        <div class="column menu" style="position: fixed">
            <div style="background: #424242; color: white;"><fmt:message key="text.genre"/></div>
            <ul id="current">
                <li>
                    <button onclick="location.href='music?command=home&genre=Pop'">Pop</button>
                </li>
                <li>
                    <button onclick="location.href='music?command=home&genre=Rap'">Rap</button>
                </li>
                <li>
                    <button onclick="location.href='music?command=home&genre=Rock'">Rock</button>
                </li>
                <li>
                    <button onclick="location.href='music?command=home&genre=Metal'">Metal</button>
                </li>
                <li>
                    <button onclick="location.href='music?command=home&genre=Jazz'">Jazz</button>
                </li>
                <li>
                    <button onclick="location.href='music?command=home&genre=Dance'">Dance</button>
                </li>
            </ul>
            <button id="all_tracks" onclick="location.href='music?command=home'"><fmt:message key="button.all"/></button>
        </div>
        <script>
            var header = document.getElementById("current");
            var btns = header.getElementsByTagName("li");
            for (var i = 0; i < btns.length; i++) {
                if (btns[i].getElementsByTagName("button")[0].textContent === "${pageContext.request.getParameter("genre")}") {
                    btns[i].className += " active";
                }
            }
        </script>
        <div style="margin-right: 10px" class="column menu"></div>
        <div class="column main">
            <c:forEach var="track" items="${tracks}">
                <c:set var="track" value="${track}" scope="request"/>
                <jsp:include page="track.jsp"/>
            </c:forEach>
        </div>

    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>