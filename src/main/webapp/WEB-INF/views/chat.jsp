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
<link href="<c:url value="/resources/css/chat.css" />" type="text/css" rel="stylesheet">

<div class="container" id="messageBox" style="margin-left: 0; padding: 0; width: 100%; max-width: 100000px;">
    <div class="messaging">
        <div class="inbox_msg" style="max-height: 100%">
            <div class="mesgs mybox">
                <%--        აქ ჩაჯდება რეაქტიდან კომპონენტი        --%>
                <div id ="messages"></div>
            </div>
        </div>
        <div id="messagebar" class="type_msg mytextfield">
            <div class="input_msg_write" id="MessageInput" >
            </div>
        </div>

    </div>
</div>

<!-- Load React. -->
<!-- Note: when deploying, replace "development.js" with "production.min.js". -->

<!-- Load our React component. -->
<script src="<c:url value="/resources/js/chat.js" />" type="text/babel"></script>

<script>
    window.chatId = ${chatId}

</script>