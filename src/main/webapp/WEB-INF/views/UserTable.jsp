<%@ page import="ge.exen.models.StudentExam" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.exen.models.User" %>
<%@ page import="ge.exen.controllers.lecturerExamList" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/2/2020
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<User> users = (List<User>) session.getAttribute("students"); %>

<div class="card-hover-shadow-2x mb-3 card" style="height: 65%!important;">
    <div id="box" class="scroll-area-sm">
        <perfect-scrollbar class="ps-show-limits">
            <div style="position: static;" class="ps ps--active-y">
                <div class="ps-content">
                    <ul class=" list-group list-group-flush">
                        <% for (int i = 0; i < users.size(); i++) { %>

                        <li class="list-group-item">
                            <div class="todo-indicator bg-warning"></div>
                            <div class="widget-content p-0">
                                <div class="widget-content-wrapper row">
                                    <div class="widget-content-left mr-2">
                                        <div class="custom-checkbox custom-control"><input class="custom-control-input"
                                                                                           id="exampleCustomCheckbox <%=i%>"
                                                                                           type="checkbox"><label
                                                class="custom-control-label"
                                                for="exampleCustomCheckbox <%=i%>">&nbsp;</label></div>
                                    </div>
                                    <div class="widget-content-left">
                                        <div class="widget-heading"><%=users.get(i).getEmail()%>
                                        </div>
                                        <div class="widget-subheading">
                                            <i><%= users.get(i).getName() + " " + users.get(i).getLastName()%>
                                            </i></div>
                                    </div>
                                    <div class="widget-content-right" style="margin-left: auto">
                                        <button onclick="window.location.href='/admin/seeStudentExam?studentId=<%=
                                                            users.get(i).getId()%>&examId=<%=request.getParameter("examId")%>'"
                                                style="background-color: dodgerblue; margin-bottom: 2vh" class="btn"><i></i> მონაცემები</button>

                                        <button onclick="window.location.href='/admin/removeUser?index=<%=users
.get(i).getId()%>&examId=<%=request.getParameter("examId")%>'"<%-- examId=<%=request.getParameter("examId")%>--%>
                                                class="border-0 btn-transition btn btn-outline-danger"><i
                                                class="fa fa-times" aria-hidden="true"></i></button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <%}%>
                    </ul>
                </div>
            </div>
        </perfect-scrollbar>
    </div>

</div>

<div id="upBox" class="d-block card-footer fixed-bottom row">

    <div id="addStudexam" style="display: inline">
        <button onclick="window.location.href='/admin/newStudentToExam'"
                style="background-color: dodgerblue; margin-bottom: 2vh" class="btn"><i
                class="fa fa-plus"></i> სტუდენტის დამატება
        </button>
        <button onclick="window.location.href='/admin/newLecturerToExam'" style="background-color: chartreuse; margin-bottom: 2vh" class="btn"><i
                class="fa fa-plus"></i> ლექტორის დამატება</button>
    </div>

    <div style="float: right;display:inline;margin-right: 20px;">
        <button onclick="window.location.href='/admin/users?examId=<%=request.getParameter("examId")%>&ageNum=<%=Math.max(1,(Integer)request.getAttribute("current")-1)%>'"
                                             class="mr-2 btn btn-link btn-sm row"><i class="fa fa-arrow-left fa-2x" aria-hidden="true"></i></button>
        <form action="/admin/users?examId=<%=request.getParameter("examId")%>&ageNum=<%=(Integer)request.getAttribute("current")%>"
              style="display:inline; width: 15%;">
            <input type="number" min="1" max="<%=request.getAttribute("pageNum")%>" name="pageNum"
                   value="<%=request.getAttribute("current")%>">
        </form>
        <button onclick="window.location.href='/admin/users?examId=<%=request.getParameter("examId")%>&pageNum=<%=Math.min((Integer)request.getAttribute("pageNum"),(Integer)request.getAttribute("current")+1)%>'"
                class="mr-2 btn btn-link btn-sm row" style="margin-left: 10px"><i class="fa fa-arrow-right fa-2x"
                                                                                  aria-hidden="true"></i></button>
        <button onclick="window.location.href='/admin/sendMails?examId=<%=request.getParameter("examId")%>'"
                class="btn btn-primary row">მეილების გაგზავნა
        </button>
    </div>
</div>
<script>
    function setW() {

        document.getElementById("upBox").style.marginLeft = (document.getElementById("sidebar-wrapper").offsetWidth).toString() + 'px';
    }

    function getHeight() {
        return (document.getElementById("upBox").offsetHeight.toString()) + "px";
    }

    function setMargin() {
        document.getElementById("box").style.marginBottom = getHeight();
    }

    // setW();
    setMargin();
</script>