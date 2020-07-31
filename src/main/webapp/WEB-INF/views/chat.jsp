<%--@elvariable id="chatId" type="java.lang.String"--%>
<%--@elvariable id="userId" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: khokho
  Date: 7/31/20
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"
            integrity="sha512-wMmlpbPCY778BJObxSz1tBYZTaz8q6eAJGiQke+r0AtqqgYPfAmwcip5p1HAmWKA7pxcqNeY8hz1mkHgZhqIiQ=="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
            integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
            crossorigin="anonymous"></script>
    <link href="<c:url value="/resources/css/chat.css" />" type="text/css" rel="stylesheet">
    <jsp:include page="includes.jsp" />
</head>
<body>

<div id="chat"></div>


<div class="container">
    <div class="messaging">
        <div class="inbox_msg" style="max-height: 70%">
            <div class="mesgs mybox">
                <div id ="messages"></div>
            </div>
        </div>
        <div class="type_msg mytextfield">
            <div class="input_msg_write" id="MessageInput">
            </div>
        </div>

    </div>
</div>

<script>
    window.chatId = ${chatId}
    window.userId = ${userId}

</script>
<!-- Load React. -->
<!-- Note: when deploying, replace "development.js" with "production.min.js". -->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>  <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<!-- Load our React component. -->
<script src="<c:url value="/resources/js/chat.js" />" type="text/babel"></script>

</body>
</html>
