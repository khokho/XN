<%@ page import="ge.exen.models.StudentExam" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.exen.models.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/2/2020
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<User> users = (List<User>)session.getAttribute("students"); %>

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
                                        <div class="widget-subheading"><i><%= users.get(i).getName() + " " + users.get(i).getLastName()%></i></div>
                                    </div>
                                    <div class="widget-content-right" style="margin-left: auto">
                                        <button onclick="window.location.href='/admin/newExam?index=<%=i%>'"
                                                class="border-0 btn-transition btn btn-outline-danger"><i
                                                class="fa-times" aria-hidden="true"></i></button>
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

<script>
    function setW() {

        document.getElementById("bottomboy").style.marginLeft = (document.getElementById("sidebar-wrapper").offsetWidth).toString() + 'px';
    }

    function getHeight() {
        return (document.getElementById("bottomboy").offsetHeight.toString()) + "px";
    }

    function setMargin() {
        document.getElementById("box").style.marginBottom = getHeight();
    }

   // setW();
    //setMargin();
</script>
<!-- </div> -->
<!--</div>-->
