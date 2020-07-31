<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MyLaptop
  Date: 28-Jul-20
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul class="list-group">
    <li class="list-group-item d-flex justify-content-between align-items-center">
        <label class="list-group-item list-group-item-action bg-light">შავი ფურცლის მოთხოვნა </label>
        <span class="badge badge-primary badge-pill">14</span>
    </li>
    <li class="list-group-item d-flex justify-content-between align-items-center">
        <div id ='paper-dequeue'></div>
        <div id ='paper-clear'></div>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <label class="list-group-item list-group-item-action bg-light">დამკვირვებლის დაძახება</label>
        <span class="badge badge-primary badge-pill">3</span>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <div id ='examer-dequeue'></div>
        <div id ='examer-clear'></div>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <label class="list-group-item list-group-item-action bg-light">WC</label>
        <span class="badge badge-primary badge-pill">2</span>
    </li>

    <li class="list-group-item d-flex justify-content-between align-items-center">
        <div id ='wc-dequeue'></div>
        <div id ='wc-clear'></div>
    </li>
</ul>

<!-- Load React. -->
<!-- Note: when deploying, replace "development.js" with "production.min.js". -->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<script src="<c:url value="/resources/js/queues_admin_buttons.js" />" type="text/babel"></script>


</body>
</html>
