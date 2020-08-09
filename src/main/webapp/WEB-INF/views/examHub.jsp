<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ge.exen.models.Upload" %>
<body>
    <div style="margin-left: 3px" class="row">
        <a>პირობა: </a>
        <a href="${statement}" download="statement" style="width: 400px;">
            <div class="col-sm-7">
                <button  class="btn btn-lg btn-block btn-success">ჩამოტვირთვა</button>
            </div>
        </a>
    </div>
    <ul id="history" class="list-group border" style="max-height: 200px; max-width: 550px; overflow: auto; border: #1e7e34">

        <%--@elvariable id="links" type="java.util.List"--%>
        <%--@elvariable id="link" type="ge.exen.models.Upload"--%>
        <%--@elvariable id="name" type="java.lang.String"--%>
        <%--@elvariable id="index" type="java.lang.Integer"--%>
        <c:set var="index" value="0"/>

        <c:forEach items="${links}" var="link">
            <c:set var="name" value="${link.time.toString()}"/>
            <c:set var="index" value="${index+1}"/>
            <li class="list-group-item"><a href="${link.fileLink}" download="upload" >ატვირთვა #${index}</a></li>
        </c:forEach>
    </ul>
    <div style="width: 548px">
        <div class="text-center">ატვირთე ნაშრომი:</div>
        <form enctype="multipart/form-data">

            <div class="form-group row" style="margin-top: 3px; margin-left: 3px;">
                <input type="file" name="sol" id="sol" style="margin-top: 10px; margin-right: 76px">
                <div class="col-sm-offset-2 col-sm-4" style="float: right">
                    <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">ატვირთვა</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        function scrollBottom(){
            var objDiv = document.getElementById("history");
            objDiv.scrollTop = objDiv.scrollHeight;
        }
        scrollBottom();
    </script>

</body>