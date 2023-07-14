<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Friends</title>

    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <!-- <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> -->
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">

    <nav>
                <div class="nav-left">
                    <a href="/welcome"><img src="${contextPath}/resources/images/logo.png" class="logo"></a>
                    <ul>
                        <li><div class="for_hover"><a href="/friends">Matches</a></div></li>
                        <li><div class="for_hover"><a href="/friends">News</a></div></li>
                        <li><div class="for_hover"><a href="/friends">Predictions</a></div></li>
                        <li><div class="for_hover"><a href="/friends">Statistics</a></div></li>
                        <li><div class="for_hover"><a href="/friends" class="visited">Friends</a></div></li>
                        <li> <a onclick="document.forms['logoutForm'].submit()"><img src="${contextPath}/resources/images/logout.png" alt="" style="height: 30px;"></a></li>
                    </ul>
                </div>
                <div class="nav-right">
                    <div class="nav-user-nick">
                        ${pageContext.request.userPrincipal.name}
                    </div>
                    <div class="nav-user-icon">
                        <a href="/profile"><img src="data:image/jpeg;base64,${photo}" alt=""></a>
                    </div>
                </div>
            </nav>

        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>


        <h2>Add Friend:</h2>
        <form method="POST" action="${contextPath}/friends">
            <input type="text" name="friends"/>
            <input type="submit" value="Add"/>
        </form>

        <h2>Friends:</h2>
        
    </c:if>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>