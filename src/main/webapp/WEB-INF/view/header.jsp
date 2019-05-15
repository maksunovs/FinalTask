<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language.name().toLowerCase()}"/>
<fmt:setBundle basename="locale"/>

<header id="header">


    <ul class="horizontal-menu user" style="float: left">
        <li><a href="music?command=home"><fmt:message key="button.home"/></a></li>
        <li><a href="music?command=view_artists"><fmt:message key="button.artists"/></a></li>
        <li><a href="music?command=view_playlists"><fmt:message key="button.playlist"/></a></li>
        <c:if test="${user.getRole().getValue()=='client'}">
            <li><a href="music?command=purchases"><fmt:message key="button.purchases"/></a></li>
            <li><a href="music?command=cart"><fmt:message key="button.cart"/></a></li>
            <li style="padding: 5px 10px;"><fmt:message key="text.cash"/>: <ctg:currencystamp value="${user.getCash()}"/>
                <button id="top_up_button" onclick="location.href='music?command=top_up_balance_page'"
                        title="Top up balance"><i class='fas fa-plus'></i></button><br/>
                <fmt:message key="text.currency"/>:
                <button class="currency" onclick="location.href='music?command=change_currency&currency=USD'">USD</button>
                <button class="currency" onclick="location.href='music?command=change_currency&currency=BYN'">BYN</button>
            </li>
        </c:if>
        <c:if test="${user.getRole().getValue()=='admin'}">
            <li><a href="music?command=add_audiotrack"><fmt:message key="button.addTrack"/></a></li>
        </c:if>
        <li style="float:right"><a href="music?command=logout"><fmt:message key="button.logout"/></a></li>
    </ul>

    <div class="dropdown">
        <c:if test="${user.getRole().getValue()=='client'}">
          <div style="color: white; padding: 6px;"><fmt:message key="text.cash"/>: <c:out value="${user.getCash()}"/>$
                <button id="top_up_button" onclick="location.href='music?command=top_up_balance_page'"
                        title="Top up balance"><i class='fas fa-plus'></i></button><br/>
              <fmt:message key="text.currency"/>:
              <button class="currency" onclick="location.href='music?command=change_currency&currency=USD'">USD</button>
              <button class="currency" onclick="location.href='music?command=change_currency&currency=USD'">BYN</button>
          </div>
        </c:if>
        <button onclick="myFunction()" class="dropbtn"><fmt:message key="button.menu"/></button>

        <div id="myDropdown" class="dropdown-content">
            <a href="music?command=home"><fmt:message key="button.home"/></a>
            <a href="music?command=view_artists"><fmt:message key="button.artists"/></a>
            <a href="music?command=view_playlists"><fmt:message key="button.playlist"/></a>
            <c:if test="${user.getRole().getValue()=='client'}">
                <a href="music?command=purchases"><fmt:message key="button.purchases"/></a>
                <a href="music?command=cart"><fmt:message key="button.cart"/></a>
            </c:if>
            <c:if test="${user.getRole().getValue()=='admin'}">
                <a href="music?command=add_audiotrack"><fmt:message key="button.addTrack"/></a>
            </c:if>
            <a href="music?command=logout"><fmt:message key="button.logout"/></a>
        </div>


    </div>
    <script>
        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }

        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
    </script>
    <c:if test="${user.getRole().getValue()=='client'}">
    <script>
        var buttons = document.getElementsByClassName("currency");
        for (var i = 0; i < buttons.length; i++) {
            if (buttons[i].textContent === "${pageContext.session.getAttribute("currency").name()}") {
                buttons[i].className += " current";
            }
        }
    </script>
    </c:if>
</header>