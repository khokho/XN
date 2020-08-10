<%--
  Created by IntelliJ IDEA.
  User: MyLaptop
  Date: 10-Aug-20
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Long examId = (Long) session.getAttribute("examId");
    Long variants = (Long) session.getAttribute("variants");
%>

<div class="container" style="top:40%; left:40%; padding: 15px;">

    <form id="form" accept-charset="UTF-8" role="form">

        <div class="form-group row justify-content-center">
            <label for="studentMail" class="col-sm-1 control-label">მეილი: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="studentMail" class="form-control" id="studentMail" placeholder="შეიყვანეთ სტუდენტის მეილი">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <label for="examId" class="col-sm-1 control-label">გამოცდის აიდი: </label>
            <div class="col-sm-4 input-group">
                <input type="number" name="examId" class="form-control" id="examId" value="<%=examId%>" readonly>
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <label for="variant" class="col-sm-1 control-label">ვარიანტი: </label>
            <div class="col-sm-4 input-group">
                <select name="variant" class="form-control" id="variant">
                    <% for (int i = 1; i <= variants; i++) { %>
                        <option value="<%=i%>"> <%=i%></option>
                    <%}%>
                </select>
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <label for="compIndex" class="col-sm-1 control-label">ადგილი: </label>
            <div class="col-sm-4 input-group">
                <input type="number" name="compIndex" class="form-control" id="compIndex" placeholder="შეიყვანეთ კომპიუტერის ნომერი">
            </div>
        </div>


        <div class="form-group row justify-content-center">
            <div class="col-sm-4">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">დამატება</button>
            </div>
        </div>
    </form>
</div>


