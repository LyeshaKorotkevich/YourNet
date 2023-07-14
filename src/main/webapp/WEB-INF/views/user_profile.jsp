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

    <title>Profile</title>

    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
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
                            <li><div class="for_hover"><a href="/friends">Friends</a></div></li>
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

        <div style="max-width: 500px; margin-left: auto; margin-right: auto; padding: 15px;">

            <form method="get" action="/user/profile" enctype="multipart/form-data">

            <h1>Profile of ${userProfile.username}</h1>
            <div>
                <img src="data:image/jpeg;base64,${userProfile.convertPhoto()}" alt="" class = "profile-logo">
            </div>
            <div class="text-field">
                <label class="text-field__label" for="info">Information about him</label>
                <input class="text-field__input" type="text" name="info" id="info" placeholder="Information" value="${userProfile.info}" readonly>
            </div>
            <div class="text-field">
                <label class="text-field__label" for="country">Country</label>
                <input class="text-field__input" type="text" name="country" id="country" placeholder="Country" value="${userProfile.country}" readonly>
            </div>
            <div class="text-field">
              <label class="text-field__label" for="city">City</label>
              <input class="text-field__input" type="text" name="city" id="city" placeholder="City" value="${userProfile.city}" readonly>
            </div>
            <div class="text-field">
              <label class="text-field__label" for="birthday">Birthday</label>
              <input class="text-field__input" type="date" name="birthday" id="birthday" value="${userProfile.birthday}" readonly>
            </div>
             <div class="text-field">
                <label class="text-field__label" for="club">Favorite club</label>
                <input class="text-field__input" type="text" name="club" id="club" placeholder="Club" value="${userProfile.club}" readonly>
             </div>
             <div class="text-field">
                <label class="text-field__label" for="player">Favorite player</label>
                <input class="text-field__input" type="text" name="player" id="player" placeholder="Player" value="${userProfile.player}" readonly>
             </div>
            </form>

          </div>
    </c:if>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>