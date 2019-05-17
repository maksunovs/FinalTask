<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>500</title>
    <style>
        *{
            color: #ccc;
            font-weight: bold ;
            text-align: center;
        }
        body{
            background: #f8f8f8;
            font-family:  sans-serif;
        }
        button{
            color: white;
            font-weight: normal;
            outline: none;
            border: none;
            cursor: pointer;
            padding: 5px 10px;
        }
        button:hover{
            background: #ff6200;
        }
    </style>
</head>
<body>
<span style="font-size: 150px; ">500</span><br/>
<span style="font-size: 45px;"><fmt:message key="error.text.serverError"/></span><br/><br/><br/>
<button onclick="location.href='music?command=home'"><fmt:message key="error.button.toHome"/></button>
</body>
</html>
