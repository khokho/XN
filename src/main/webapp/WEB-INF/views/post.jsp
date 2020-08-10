<%--@elvariable id="examId" type="java.lang.Integer"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tamtatopuria
  Date: 7/31/20
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="includes.jsp"/>
    <title>Posts</title>
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

        .form-group .shadow-textarea {
            color: #546e7a;
        }

        input[type=text], select {
            width: 130px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: #546e7ac4;
            /*background-image: url('searchicon.png');*/
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 8px;
            transition: width 0.4s ease-in-out;
            margin: 10px;
            -webkit-box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }

        input[type=text]:focus {
            /*border: 3px solid #555;*/
            width: 100%;
        }

        input[type=submit], button, select {
            background-color: #ffc107;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;
        }

        input[type=submit]:hover, button:hover {
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
        }

        input[type=submit], select {
            position: absolute;
            right: 10px;
            top: 1.5px;
        }
        /*input[type=button]:hover {*/
        /*    box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);*/
        /*}*/

        #newPostForm{
         resize: none;
        }
    </style>

</head>
<body>

<div id="newPost"></div>
<div id="posts"></div>

<script>
    window.examId = ${examId}
</script>

<!-- Load our React component. -->
<script src="<c:url value="/resources/js/post.js" />" type="text/babel"></script>

<script type="text/babel">
    const postsContainer = document.querySelector('#posts');

    ReactDOM.render(<Posts/>, postsContainer);
    <%--@elvariable id="status" type="java.lang.String"--%>
    <c:if test="${status.equals(\"lector\")}">
        const newPost = document.querySelector('#newPost');
        ReactDOM.render(<NewPosts/>, newPost);
    </c:if>

</script>

<iframe style="display:none" name="hidden-form"></iframe>
<iframe style="display:none" name="hidden-remove"></iframe>
<iframe style="display:none" name="hidden-edit"></iframe>

</body>
</html>
