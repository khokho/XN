<%--
  Created by IntelliJ IDEA.
  User: MyLaptop
  Date: 10-Aug-20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Long examId = (Long) session.getAttribute("examId");
%>


<div class="container" style="padding: 15px;">

    <form id="form" accept-charset="UTF-8" role="form">
        <input type="number" name="examId" id="examId" style="display: none" value="<%=examId%>"/>

        <div class="form-group row">
            <label for="lecturerMail" class="col-sm-3 control-label">მეილი: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="lecturerMail" class="form-control" id="lecturerMail" placeholder="შეიყვანეთ სტუდენტის მეილი">
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-4">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">დამატება</button>
            </div>
        </div>
    </form>
</div>