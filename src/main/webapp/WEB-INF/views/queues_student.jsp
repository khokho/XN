<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MyLaptop
  Date: 28-Jul-20
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"
            integrity="sha512-wMmlpbPCY778BJObxSz1tBYZTaz8q6eAJGiQke+r0AtqqgYPfAmwcip5p1HAmWKA7pxcqNeY8hz1mkHgZhqIiQ=="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
            integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
            crossorigin="anonymous"></script>
</head>
<body>
<ul class="list-group">
    <li class="list-group-item d-flex justify-content-between align-items-center">
        <label class="list-group-item list-group-item-action bg-light">შავი ფურცლის მოთხოვნა </label>
    </li>
    <li class="list-group-item d-flex justify-content-between align-items-center">
        <div id ='paper-student'></div>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <label class="list-group-item list-group-item-action bg-light">დამკვირვებლის დაძახება</label>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <div id ='examer-student'></div>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <label class="list-group-item list-group-item-action bg-light">WC</label>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <div id ='wc-student'></div>
    </li>
</ul>

<!-- Load React. -->
<!-- Note: when deploying, replace "development.js" with "production.min.js". -->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<script src="<c:url value="/resources/js/queues_student_buttons.js" />" type="text/babel"></script>
</body>
</html>
