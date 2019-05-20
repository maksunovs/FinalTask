<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="text.login"/></title>
    <style>

        .login-page {
            width: 360px;
            padding: 8% 0 0;
            margin: auto;
        }

        .form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            max-width: 360px;
            margin: 0 auto 100px;
            padding: 45px 45px 10px 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }

        .form input {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
        }

        .form button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #ff6200;
            width: 100%;
            border: 0;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            cursor: pointer;
        }

        .form button:hover, .form button:active, .form button:focus {
            background: #ff4b00;
        }


        body {
            background: #424242;
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        footer {
            text-align: center;
            padding: 14px;
            color: white;
            position: absolute;
            bottom: 0;
            background: none;
            width: 96%;
        }

        footer a {
            text-decoration: none;
            color: white;
        }

        footer a:hover {
            color: #ff6200;
        }

        .current {
            color: #ff6200;
        }
    </style>
</head>
<body>
<div class="login-page">
    <div class="form">
        <form method="post" action="music?command=login">
            <input type="text" name="login" placeholder="<fmt:message key="form.authorization.input.login"/>" required/>
            <input type="password" name="password" placeholder="<fmt:message key="form.authorization.input.password"/>"
                   required/>
            <button type="submit"><fmt:message key="button.login"/></button>
        </form>

        <div style="margin-top:15px; height:35px; color: red;">
            <c:if test="${not empty param.login}">
                <fmt:message
                        key="${param.login}"/>
            </c:if>
        </div>

    </div>
</div>
<footer>
    Laguage:
    <a href="music?command=change_language&language=EN">EN</a>
    <a href="music?command=change_language&language=RU">RU</a>
</footer>
<script>
    var header = document.getElementsByTagName("footer");
    var links = header[0].getElementsByTagName("a");
    for (var i = 0; i < links.length; i++) {
        if (links[i].textContent === "${pageContext.session.getAttribute("language").name()}") {
            links[i].className += " current";
        }
    }
</script>
</body>
</html>