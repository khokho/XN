<%@ page import="java.util.List" %>
<%@ page import="ge.exen.models.Exam" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ge.exen.controllers.ExamControllerForAdmin" %>
<!--<div class="row d-flex justify-content-center container">
<div class="col-md-8">-->
<% List<ExamControllerForAdmin.Pair> exams = (List<ExamControllerForAdmin.Pair>) request.getAttribute("list");%>

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
                                                                                           id="exampleCustomCheckbox12"
                                                                                           type="checkbox"><label
                                                class="custom-control-label"
                                                for="exampleCustomCheckbox12">&nbsp;</label></div>
                                    </div>
                                    <div class="widget-content-left">
                                        <div class="widget-heading">Call Sam For payments
                                            <div class="badge badge-danger ml-2">Rejected</div>
                                        </div>
                                        <div class="widget-subheading"><i>By Bob</i></div>
                                    </div>
                                    <div class="widget-content-right" style="margin-left: auto">
                                        <button class="border-0 btn-transition btn btn-outline-success"><i
                                                <% String icon =  "fa fa-hourglass-half";
                                                    if (!exams.get(i).isCurrentlyOn()) {
                                                        icon = "fa fa-history";
                                                    }
                                                %>
                                                 class= "<%=icon%>" aria-hidden="true"></i></button>



                                        <button class="border-0 btn-transition btn btn-outline-warning"><i
                                                class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <%}%>
                        <li class="list-group-item">
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
                                        <div class="widget-subheading">By CEO</div>
                                    </div>
                                    <div class="widget-content-right" style="margin-left: auto">
                                        <button class="border-0 btn-transition btn btn-outline-success"><i
                                                class="fa fa-history" aria-hidden="true"></i></button>
                                        <button class="border-0 btn-transition btn btn-outline-warning"><i
                                                class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </perfect-scrollbar>
    </div>

</div>
<div id="bottomboy" class="d-block card-footer fixed-bottom row"
     style="opacity: 100%;background-color: white!important;">
    <button class="mr-2 btn btn-link btn-sm row">previous</button>
    <form action="/admin">
        <input type="number" min="0" max="<%=request.getAttribute("pageNum")%>"
               value="<%=request.getAttribute("pageNum")%>">
    </form>
    <button class="mr-2 btn btn-link btn-sm row">next</button>
    <button class="btn btn-primary row">Add Task</button>
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