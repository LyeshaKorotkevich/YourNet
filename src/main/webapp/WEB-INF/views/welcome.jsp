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

    <title>Welcome</title>

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

        <h1>Create Post</h1>
            <form action="/posts" method="POST" class="post-form">
                <label for="title">Title:</label>
                <input type="text" name="title" required/></td>

                <label for="content">Content:</label>
                <textarea id="content" name="content" rows="5" cols="40" required></textarea>
                <input type="submit" value="Post" /></td>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>


        <h1>Forum</h1>
        <c:forEach items="${posts}" var="post">
            <div class="post">
                <a href="${contextPath}/post/${post.id}">
                        <h2 class="post-title">${post.title}<img src="${contextPath}/resources/images/comments.png" style="width:20px;height:18px;margin-left:30px;"> <span class="comment-count">${post.commentCount}</span></h2>
                        <p class="post-content">${post.content}</p>
                        <p class="post-author">
                            <img src="data:image/jpeg;base64,${post.user.convertPhoto()}" alt=""> <a href="${contextPath}/user/profile?username=${post.user.username}">${post.user.username}</a>
                            <span class="post-date">${post.getTimeAgo()}</span>
                        </p>
                </a>
                <c:if test="${post.user.username == pageContext.request.userPrincipal.name}">
                    <a href="${contextPath}/post/delete?id=${post.id}">
                        <img src="${contextPath}/resources/images/bucket.png" class="delete-post" title="Delete Post">
                    </a>
                </c:if>
            </div>
        </c:forEach>
    </c:if>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>