<%@ page import="ge.exen.models.StudentExam" %><%--
  Created by IntelliJ IDEA.
  User: MyLaptop
  Date: 10-Aug-20
  Time: 03:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StudentExam data = (StudentExam) session.getAttribute("data");
    Long examId = data.getExamId();
    Long studentId = data.getStudentId();
    Long variant = data.getVariant();
    Long compIndex = data.getCompIndex();
%>

<div class="container" style="padding: 15px;">
    <form id="form" accept-charset="UTF-8" role="form">


        <div class="form-group row">
            <label for="studentMail" class="col-sm-1 control-label">ვარიანტი: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="studentMail" class="form-control" id="studentMail" value = "<%=variant%>" readonly>
            </div>
        </div>


        <div class="form-group row">
            <label for="compIndex" class="col-sm-1 control-label">ადგილი: </label>
            <div class="col-sm-4 input-group">
                <input type="number" name="compIndex" class="form-control" id="compIndex" value = "<%=compIndex%>">
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-4">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">ადგილის შეცვლა</button>
            </div>
        </div>
    </form>
</div>
