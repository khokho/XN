<%--
  Created by IntelliJ IDEA.
  User: khokho
  Date: 7/14/20
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
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
</head>
<body>
<h1>Hi Chat</h1>
<script type="text/javascript">

    var ws;
    var stompClient;
    var stompCallback =
        $(document).ready(function () {
            ws = new SockJS("/ws");
            stompClient = Stomp.over(ws);
            stompClient.connect({}, function (frame) {
                //stompClient.debug("connected")
                console.log("connected");
                stompClient.subscribe("/topic/chat-1", function (post) {
                    //console.log("HI: "+post)
                    console.log("here broz")
                    console.log(post)
                    $('#res').html(post.body)
                }, {'chat_id':10});
            });
        });

    function send() {
        stompClient.send("/topic/post", {'chat_id':10}, $('#tex').val());
    }

</script>

</body>
</html>
