<%@ page import="java.util.List" %>
<%@ page import="ge.exen.models.StudentExam" %>
<%@ page import="ge.exen.models.User" %>
<%@ page import="ge.exen.models.Upload" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!--<div class="row d-flex justify-content-center container">
<div class="col-md-8">-->
<% List<User> names = (List<User>) request.getAttribute("names");%>
<% List<Upload> uploads = (List<Upload>) request.getAttribute("uploads");%>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<div class="card-hover-shadow-2x mb-3 card">
    <div id="box" class="scroll-area-sm">
        <perfect-scrollbar class="ps-show-limits">
            <div style="position: static;" class="ps ps--active-y">
                <div class="ps-content">
                    <ul class=" list-group list-group-flush">
                        <% for (int i = 0; i < names.size(); i++) { %>

                        <li class="list-group-item">
                            <div class="todo-indicator bg-warning"></div>
                            <div class="widget-content p-0">
                                <div class="widget-content-wrapper row">

                                    <div class="widget-content-left">
                                        <div class="widget-heading"><%=names.get(i).getName()%> <%=names.get(i).getLastName()%> (<%=names.get(i).getEmail()%>)
                                            <% if (uploads.get(i) == null) {%>
                                            <div class="badge badge-danger ml-2">ნაწერი არ ფიქსირდება</div>
                                            <% }
                                            %>

                                        </div>
                                    </div>
                                    <div class="widget-content-right" style="margin-left: auto">
                                        <% if(uploads.get(i) != null) {%>
                                        <button onclick= "downloadAs('/<%=uploads.get(i).getFileLink()%>','<%=names.get(i).getEmail().substring(0, names.get(i).getEmail().indexOf("@"))%>_ვარიანტი_<%=uploads.get(i).getVarId()%>')" class="border-0 btn-transition btn btn-outline-success"><i
                                                class= "fa fa-download" aria-hidden="true"></i></button>
                                        <%}%>
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
<div id="upBox" class="d-block card-footer fixed-bottom row"
     style="opacity: 100%;background-color: white!important;">
    <button onclick= "window.location.href='/lecturer/getAll/${examID}'" class="btn btn-primary row">ყველა ნაწერის ჩამოტვირთვა</button>
</div>
<script>
    const downloadAs = (url, name) => {
        axios.get(url, {
            headers: {
                "Content-Type": "application/octet-stream"
            },
            responseType: "blob"
        })
            .then(response => {
                const a = document.createElement("a");
                const url = window.URL.createObjectURL(response.data);
                a.href = url;
                a.download = name;
                a.click();
            })
            .catch(err => {
                console.log("error", err);
            });
    };

</script>
<!-- </div> -->
<!--</div>-->