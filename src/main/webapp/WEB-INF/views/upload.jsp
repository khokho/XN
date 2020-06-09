<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/9/2020
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload your files</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="upload">
    <label for="choose">ატვირთეთ ნაშრომი</label>
    <input type="file" id="choose" name="file"> <br/>
    <label for="var">ვარიანტი</label>
    <input type="number" id="var" name="var_id" min="1" max="10" value="1"><br/>
    <input type="submit" value="ატვირთვა">
</form>
</body>
</html>
