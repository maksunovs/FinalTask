<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>
<footer>
    Language:
    <a href="music?command=change_language&language=EN">EN</a>
    <a href="music?command=change_language&language=RU">RU</a>
</footer>
<script>
    var footer = document.getElementsByTagName("footer");
    var links = footer[0].getElementsByTagName("a");
    for (var i = 0; i < links.length; i++) {
        if (links[i].textContent === "${pageContext.session.getAttribute("language").name()}") {
            links[i].className += " current";
        }
    }
</script>
