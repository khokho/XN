<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MyLaptop
  Date: 28-Jul-20
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>


<style>
    .card {
        /*margin-top: 5px;*/
        margin: 10px;
        -webkit-box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
    }

    .card-header {
        color: #ffab40;
        background-color: #26404c;
        size: 23cm;
    }

    .card-body {
        background-color: #546e7a;
        color: #ffffff;
    }

    .card-body footer {
        color: #fff3cd;
    }

    .card-body p {
        white-space: pre;
    }
</style>


<div>
    <div>
        <div class="card">
            <div class = "card-header">შავი ფურცლის მოთხოვნა </div>
            <div id ="paper-admin" class="card-body"></div>
        </div>

    </div>

    <div>
        <div class="card">
            <div class = "card-header">დამკვირვებლის დაძახება </div>
            <div id ="examer-admin"  class="card-body"></div>
        </div>

    </div>

    <div>
        <div class="card">
            <div class = "card-header">WC</div>
            <div id ="wc-admin" class="card-body"></div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/queues_admin_buttons.js" />" type="text/babel"></script>

