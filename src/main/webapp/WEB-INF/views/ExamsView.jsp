<%@ page import="java.util.List" %>
<%@ page import="ge.exen.models.Exam" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ge.exen.controllers.ExamControllerForAdmin" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!--<div class="row d-flex justify-content-center container">
<div class="col-md-8">-->
<% List<ExamControllerForAdmin.ExamInfo> exams = (List<ExamControllerForAdmin.ExamInfo>) session.getAttribute("list");%>

<div class="card-hover-shadow-2x mb-3 card" style="width: 100%!important; height: 65%!important;">
    <div id="box" class="scroll-area-sm">
        <perfect-scrollbar class="ps-show-limits">
            <div style="position: static;" class="ps ps--active-y">
                <div class="ps-content">
                    <ul class=" list-group list-group-flush">
                        <% for (int i = 0; i < exams.size(); i++) { %>

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
                                        <div class="widget-heading"><%=exams.get(i).getExam().getFullName()%>
                                            <% if (!exams.get(i).isCurrentlyOn()) {%>
                                            <div class="badge badge-danger ml-2">დასრულებული</div>
                                            <% }
                                                else { %>
                                            <div class="badge badge-success ml-2">მიმდინარეობს</div>
                                                    <%
                                                }
                                            %>

                                        </div>
                                        <div class="widget-subheading"><i>By Bob</i></div>
                                    </div>
                                    <div class="widget-content-right" style="margin-left: auto">
                                        <button class="border-0 btn-transition btn btn-outline-success"><i
                                                <% String icon =  "fa fa-hourglass-half";
                                                    if (exams.get(i).isCurrentlyOn()) {
                                                        icon = "fa fa-history";
                                                    }
                                                %>
                                                 class= "<%=icon%>" aria-hidden="true"></i></button>



                                        <button onclick= "window.location.href='/admin/newExam?index=<%=i%>'" class="border-0 btn-transition btn btn-outline-warning"><i
                                                class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <%}%>
                     <%--   <li class="list-group-item">
                            <div class="todo-indicator bg-success"></div>
                            <div class="widget-content p-0">
                                <div class="widget-content-wrapper row">
                                    <div class="widget-content-left mr-2">
                                        <div class="custom-checkbox custom-control"><input class="custom-control-input"
                                                                                           id="exampleCustomCheckbox10"
                                                                                           type="checkbox"><label
                                                class="custom-control-label"
                                                for="exampleCustomCheckbox10">&nbsp;</label></div>
                                    </div>
                                    <div class="widget-content-left flex2">
                                        <div class="widget-heading">Client Meeting at 11 AM</div>
                                        <div class="badge badge-success ml-2">მიმდინარეობს</div>
                                        <div class="widget-subheading">By CEO</div>
                                    </div>
                                    <div class="widget-content-right" style="margin-left: auto">
                                        <button class="border-0 btn-transition btn btn-outline-success"><i
                                                class="fa fa-hourglass-half" aria-hidden="true"></i></button>
                                        <button class="border-0 btn-transition btn btn-outline-warning"><i
                                                class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                    </div>
                                </div>
                            </div>
                        </li>--%>
                    </ul>
                </div>
            </div>
        </perfect-scrollbar>
    </div>

</div>
<div id="bottomboy" class="d-block card-footer fixed-bottom row"
     style="opacity: 100%;background-color: white!important;">
    <button onclick = "window.location.href='/l?pageNum=<%=Math.max(1,(Integer)request.getAttribute("current")-1)%>'" class="mr-2 btn btn-link btn-sm row">წინა გვერდი</button>
    <form action="/l">
        <input type="number" min="1" max="<%=request.getAttribute("pageNum")%>" name="pageNum"
        value="<%=request.getAttribute("current")%>">

    </form>
    <button onclick= "window.location.href='/l?pageNum=<%=Math.min((Integer)request.getAttribute("pageNum"),(Integer)request.getAttribute("current")+1)%>'" class="mr-2 btn btn-link btn-sm row">შემდეგი გვერდი</button>
    <button onclick= "window.location.href='/admin/newExam'" class="btn btn-primary row">გამოცდის შექმნა</button>
</div>
<script>
    function setW() {

        document.getElementById("bottomboy").style.marginLeft = (document.getElementById("sidebar-wrapper").offsetWidth - 1).toString() + 'px';
    }

    function getHeight() {
        return document.getElementById("bottomboy").offsetHeight.toString() + "px";
    }

    function setMargin() {
        document.getElementById("box").style.marginBottom = getHeight();
    }

    setW();
    setMargin();
</script>
<!-- </div> -->
<!--</div>-->