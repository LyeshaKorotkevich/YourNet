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

    <title>Post</title>

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

    <div class="post">
        <h2 class="post-title">${post.title}</h2>
        <p class="post-content">${post.content}</p>
        <p class="post-author">
            <img src="data:image/jpeg;base64,${post.user.convertPhoto()}" alt="">
            <a href="${pageContext.request.contextPath}/user/profile?username=${post.user.username}">${post.user.username}</a>
            <span class="post-date">${post.getTimeAgo()}</span>
        </p>
    </div>

    <form action="${contextPath}/post/${post.id}/comment" method="POST" class="comment-form">
        <textarea id="content" name="content" rows="3" cols="40" placeholder="Your comment..." required></textarea>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="Send" />
    </form>

    <h3 style="margin-left:10%;margin-bottom:8px;">Comments(${post.commentCount}):</h3>
    <c:forEach items="${comments}" var="comment">
        <d2r">
                <img src="data:image/jpeg;base64,${comment.user.convertPhoto()}" alt="">
                <a href="${pageContext.request.contextPath}/user/profile?username=${comment.user.username}">${comment.user.username}</a>
                <span class="post-date">${comment.getTimeAgo()}</span>
            </p>
        </div>
    </c:forEach>
    </c:if>

    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
