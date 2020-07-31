<%--@elvariable id="chat_id" type=""--%>
<style>
    .mybox {
        position: relative;
        width: 100%;
    }

    .mytextfield {
        position: fixed !important;
        bottom: 0;
        width: 100% !important;
        right: 0px;
        left: inherit;
        z-index: 1;
        background-color: rgba(365, 365,365, 1);
    }
</style>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/chat.css" type="text/css" rel="stylesheet">
</head>
<body onload="initWebSocket()">
<div class="container">
    <div class="messaging">
        <div class="inbox_msg" style="max-height: 70%">

            <div class="mesgs mybox">
                    <div id ="messages"></div>

            </div>
        </div>
        <div class="type_msg mytextfield">
            <div class="input_msg_write">
                <input type="text" id="chat-id" style="display: none" value="${chat_id}">

                <input type="text" class="write_msg" id="field" style="padding-right: 100px" placeholder="Type a message" />
                <button class="msg_send_btn" type="button" style="margin-right: 40px" onclick="document.getElementById('image-input').click();"><i class="fa fa-camera" aria-hidden="true"></i></button>

                <input id="image-input" type="file" name="name" style="display: none;">
                <button class="msg_send_btn" type="button" onclick="sendMessage()" onkeypress="checkPress(e)"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/resources/js/vanochat.js" charset="utf-8"></script>
        </div></div>
</body>
</html>