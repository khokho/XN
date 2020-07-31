<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ge.exen.models.Upload" %>
<body>
    <ul id="history" class="list-group border" style="max-height: 200px; max-width: 550px; overflow: auto; border: #1e7e34">

        <%--@elvariable id="links" type="java.util.List"--%>
        <%--@elvariable id="link" type="ge.exen.models.Upload"--%>
        <%--@elvariable id="name" type="java.lang.String"--%>
        <%--@elvariable id="index" type="java.lang.Integer"--%>
        <c:set var="index" value="0"/>

        <c:forEach items="${links}" var="link">
            <c:set var="name" value="${link.time.toString()}"/>
            <c:set var="index" value="${index+1}"/>
            <li class="list-group-item"><a href="${link.fileLink}" download=${"upload_"}${name.substring(name.length()-10)}>ატვირთვა #${index}</a></li>
        </c:forEach>
    </ul>
    <form enctype="multipart/form-data">

        <div class="form-group row" style="margin-left: 20px">
            <input type="file" name="sol" id="sol" style="margin-top: 10px;">
            <div class="col-sm-offset-1 col-sm-3">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">ატვირთვა</button>
            </div>
        </div>
    </form>

    <script>
        function scrollBottom(){
            var objDiv = document.getElementById("history");
            objDiv.scrollTop = objDiv.scrollHeight;
        }
        scrollBottom();
    </script>

</body>