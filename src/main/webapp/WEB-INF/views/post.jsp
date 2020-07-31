<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tamtatopuria
  Date: 7/31/20
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="includes.jsp"/>
    <title>Posts</title>
    <style>
        .card{
            /*margin-top: 5px;*/
            margin: 10px;
            -webkit-box-shadow: 0 2px 2px 0 rgba(0,0,0,0.14),0 3px 1px -2px rgba(0,0,0,0.12),0 1px 5px 0 rgba(0,0,0,0.2);
            box-shadow: 0 2px 2px 0 rgba(0,0,0,0.14),0 3px 1px -2px rgba(0,0,0,0.12),0 1px 5px 0 rgba(0,0,0,0.2);
        }
        .card-header{
            color: #ffab40;
            background-color: #26404c;
            size: 23cm;
        }
        .card-body {
            background-color: #546e7a;
            color: #ffffff;
        }
        .card-body footer{
            color: #fff3cd;
        }
        .card-body p{
            white-space: pre;
        }
    </style>

</head>
<body>

<div id="newPost"></div>
<div id="posts"></div>

<script>
    window.examId =${examId}
</script>

<!-- Load React. -->
<!-- Note: when deploying, replace "development.js" with "production.min.js". -->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<!-- Load our React component. -->
<script src="<c:url value="/resources/js/post.js" />" type="text/babel"></script>

</body>
</html>
