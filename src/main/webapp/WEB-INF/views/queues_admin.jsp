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
    <jsp:include page="includes.jsp"/>
    <title>რიგები</title>
    <style>
        body{
            background: darkseagreen;
            position:fixed;
            top:0;
            bottom:0;
            left:0;
            right:0;
        }

        html{
            position:fixed;
            top:0;
            bottom:0;
            left:0;
            right:0;
        }

        .wrapperdiv {
            text-align: center;
            height: 33vh;
            position: relative;
            border: 3px solid green;
            margin:0;
            padding: 3vh;
        }

        .innerdiv {
            height: 16vh;
            margin: 0;
            position: absolute;
            top: 11%;
            left: 8%;

        }

        label {
            color:black;
            font-size: 8vh;
        }
        .buttonsdiv {
            height: 20vh;
            color: green;
        }

    </style>
</head>
<body >

<div id = 'admin-queues'>

    <div class="wrapperdiv" >
        <div class="innerdiv">
            <div class = "labeldiv"><label>შავი ფურცლის რიგი</label> </div>
            <div id ='paper-admin' class='buttonsdiv' style="margin-top:22px;"></div>
        </div>

    </div>


    <div class="wrapperdiv" >
        <div class="innerdiv">
            <div class = "labeldiv"><label>დამკვირვებლის რიგი</label> </div>
            <div id ='examer-admin'  class='buttonsdiv' style="margin-top:22px;"></div>
        </div>

    </div>

    <div class="wrapperdiv">
        <div class="innerdiv">
            <div class = "labeldiv"><label>WC</label> </div>

            <div id ='wc-admin' class='buttonsdiv' style="margin-top:22px;"></div>
        </div>

    </div>
</div>


<!-- Load React. -->
<!-- Note: when deploying, replace "development.js" with "production.min.js". -->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<script src="<c:url value="/resources/js/queues_admin_buttons.js" />" type="text/babel"></script>


</body>
</html>
