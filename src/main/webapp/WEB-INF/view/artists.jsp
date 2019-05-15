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
    <title><fmt:message key="text.artistList"/></title>
    <style>
        <%@include file="/WEB-INF/css/view.css" %>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content" style="padding: 0 10px">
    <br/>
    <span style="font-size: 20px; margin-right: 10px; float: left;"><fmt:message key="text.artistList"/></span>
    <c:if test="${user.getRole().getValue()=='admin'}">
            <button id="add_button" onclick="location.href='music?command=add_artist'" title="New artist"><fmt:message key="button.add"/></button>
    </c:if>
    <br/><br/>
    <c:forEach var="artist" items="${artists}">
        <button id="open_button"
                onclick="location.href='music?command=view_artist&id=${artist.getId()}'"><c:out value="${artist.getName()}"/></button>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>





