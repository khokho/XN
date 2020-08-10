<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%--@elvariable id="modifyingExam" type="ge.exen.models.Exam"--%>


<c:set var="fullName" value="შეიყვანეთ გამოცდის სახელი"/>
<c:set var="startDate" value="შეიყვანეთ გამოცდის სახელი"/>
<c:set var="hours" value="სთ"/>
<c:set var="minutes" value="წთ"/>
<c:set var="variants" value="ვარიანტების რაოდენობა"/>

<c:if test="${modifyingExam!=null}">
    <c:set var="fullName" value="${modifyingExam.fullName}"/>
    <c:set var="startDate" value="${modifyingExam.startDate}"/>
    <c:set var="hours" value="${fn:substringBefore(modifyingExam.durationInMinutes div 60, '.')}"/>
    <c:set var="minutes" value="${modifyingExam.durationInMinutes%60}"/>
    <c:set var="variants" value="${modifyingExam.variants}"/>
</c:if>



<div class="container" style="padding: 15px; ">
    <form id="form" accept-charset="UTF-8" role="form" enctype="multipart/form-data">

        <div class="form-group row">
            <label for="fullName" class="col-sm-3 control-label">გამოცდის სახელი: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="fullName" class="form-control inputstl" id="fullName" placeholder= "${fullName}">
            </div>
        </div>
        <div class="form-group row">
            <label for="startDate" class="col-sm-3">დაწყების დრო: </label>
            <div class="col-sm-4">
                <div class="input-group">
                    <div class="input-group date" id="startDate" data-target-input="nearest">
                        <input type="text" name="startDate" class="form-control datetimepicker-input inputstl"
                               data-target="#datetimepicker1"
                               placeholder="${startDate}">

                        <div class="input-group-append" data-target="#startDate" data-toggle="datetimepicker">
                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#startDate').datetimepicker({
                        format: 'YYYY-MM-DD HH:mm'
                    });
                });
            </script>
        </div>

        <div class="form-group row">
            <label for="hours" class="col-sm-3 control-label">გამოცდის ხანგრძლივობა:</label>
            <div class="col-sm-4 input-group">
                <input type="number" name="hours" class="form-control inputstl"
                       style="width: 60px; margin-right: 5px" min="0" max="99" id="hours"
                       placeholder= "${hours}">
                <input type="number" name="minutes" class="form-control inputstl" id="minutes"
                       style="width: 60px" min="0" max="59"
                       placeholder= "${minutes}">
            </div>
        </div>


        <div class="form-group row">
            <label for="variants" class="col-sm-3 control-label">ვარიანტები:</label>
            <div class="col-sm-4 input-group">
                <input type="number" name="variants" class="form-control inputstl" id="variants" min="0" max="99"
                placeholder= "${variants}">
            </div>
        </div>

        <div id="statements"></div>

        <div class="form-group row">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">შექმნა</button>
            </div>
        </div>
        <script>
            // addFiles();
            const inp = document.getElementById("variants");
            // inp.addEventListener('change', () => {
            //     addFiles()
            // });

            function addFiles() {
                let container = document.getElementById("statements");
                let newSize = document.getElementById("variants").value;

                let oldSize = (container.childNodes.length);

                if (oldSize < newSize)
                    for (let i = oldSize + 1; i <= newSize; i++) {
                        let subContainer = document.createElement("div");
                        subContainer.classList.add("form-group");
                        subContainer.classList.add("row");
                        let label = document.createElement("label");
                        label.for = "" + i;
                        label.textContent = "ვარიანტი #" + i + ":";
                        label.classList.add("col-sm-3");
                        label.classList.add("control-label");
                        subContainer.appendChild(label);
                        let input = document.createElement("input");
                        input.type = "file";
                        input.id = "statement " + i;
                        input.name = "statement " + i;
                        label.style.paddingRight = "4px";
                        subContainer.appendChild(input);
                        subContainer.appendChild(document.createElement("br"));
                        container.appendChild(subContainer);
                    }
                else {
                    while (oldSize > newSize) {
                        container.removeChild(container.lastChild);
                        oldSize--;
                    }
                }
            }
        </script>
    </form>
</div>
