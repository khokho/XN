<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <script src="../../resources/js/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/moment-with-locales.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/locale/ka.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />
    <script src="../../resources/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.iconify.design/1/1.0.7/iconify.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        .inputstl {
            padding: 9px;
            border: solid 1px #B3FFB3;
            outline: 0;
            background: -webkit-gradient(linear, left top, left 25, from(#FFFFFF), color-stop(4%, #A4FFA4), to(#FFFFFF));
            background: -moz-linear-gradient(top, #FFFFFF, #A4FFA4 1px, #FFFFFF 25px);
            box-shadow: rgba(0,0,0, 0.1) 0px 0px 8px;
            -moz-box-shadow: rgba(0,0,0, 0.1) 0px 0px 8px;
            -webkit-box-shadow: rgba(0,0,0, 0.1) 0px 0px 8px;

        }

    </style>


    <title></title>

</head>

<body>
<div class="container" style="padding-top: 30px">
    <form class="form-horizontal" id="form" accept-charset="UTF-8" role="form" enctype="multipart/form-data">

        <div class="form-group row">
            <label for="fullName" class="col-sm-3 control-label">გამოცდის სახელი: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="fullName" class="form-control inputstl" id="fullName" placeholder="შეიყვანეთ გამოცდის სახელი">
            </div>
        </div>

        <div class="form-group row">
            <label for="startDate" class="col-sm-3">დაწყების დრო: </label>
            <div class="col-sm-4">
                <div class="input-group">
                    <div class="input-group date" id="startDate" data-target-input="nearest">
                        <input type="text" name="startDate" class="form-control datetimepicker-input inputstl" data-target="#datetimepicker1" placeholder="შეიყვანეთ დაწყების დრო"/>
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
            <label for="duration_hr" class="col-sm-3 control-label">გამოცდის ხანგრძლივობა:</label>
            <div class="col-sm-4 input-group">
                <input type="number" name="duration_hr" class="form-control inputstl" style="width: 60px; margin-right: 5px" min="0" max="99" id="duration_hr"  placeholder="სთ.">
                <input type="number" name="duration_mn" class="form-control inputstl" id="duration_mn"  style="width: 60px" min="0" max="59" placeholder="წთ.">
            </div>
        </div>


        <div class="form-group row">
            <label for="variants" class="col-sm-3 control-label">ვარიანტები:</label>
            <div class="col-sm-4 input-group">
                <input type="number" name="variants" class="form-control inputstl" id="variants" min="0" max="99" placeholder="ვარიანტების რაოდენობა">
            </div>
        </div>

        <div id="statements"></div>

        <div class="form-group row">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">შექმნა</button>
            </div>
        </div>
        <script>
            addFiles();
            const inp = document.getElementById("variants");
            inp.addEventListener('change', () => {
                addFiles()
            });

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

</body>
</html>
