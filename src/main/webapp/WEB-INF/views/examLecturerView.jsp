<%@ page import="java.util.List" %>
<%@ page import="ge.exen.models.Exam" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ge.exen.controllers.lecturerExamList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!--<div class="row d-flex justify-content-center container">
<div class="col-md-8">-->
<% List<lecturerExamList.ExamInfo> exams = (List<lecturerExamList.ExamInfo>) request.getAttribute("list");%>

<div class="card-hover-shadow-2x mb-3 card">
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
                                        <button onclick= "window.location.href='/lecturer/students/<%=exams.get(i).getExam().getID()%>'" class="border-0 btn-transition btn btn-outline-success"><i
                                                class= "fa fa-users" aria-hidden="true"></i></button>



                                        <button onclick= "window.location.href='/lecturer/exam/<%=exams.get(i).getExam().getID()%>'" class="border-0 btn-transition btn btn-outline-warning"><i
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
<div id="upBox" class="d-block card-footer fixed-bottom row"
     style="opacity: 100%;background-color: white!important;">
    <form>
        <button onclick = "window.location.href='/lecturer/exams?pageNum=<%=Math.max(1,(Integer)request.getAttribute("current")-1)%>'" class="mr-2 btn btn-link btn-sm row"><i class="fa fa-arrow-left fa-2x" aria-hidden="true"></i></button>

        <input type="number" min="1" max="<%=request.getAttribute("pageNum")%>" name="pageNum"
               value="<%=request.getAttribute("current")%>">


        <button onclick= "window.location.href='/lecturer/exams?pageNum=<%=Math.min((Integer)request.getAttribute("pageNum"),(Integer)request.getAttribute("current")+1)%>'" class="mr-2 btn btn-link btn-sm row" style="margin-left: 10px"><i class="fa fa-arrow-right fa-2x" aria-hidden="true"></i></button>
    </form>
</div>
<script>
    console.log(<%=request.getAttribute("current")%>);

</script>
<!-- </div> -->
<!--</div>-->