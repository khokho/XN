<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<div class="align-content-center" style="vertical-align: middle;">
        <form id="form" accept-charset="UTF-8" role="form" style="margin-top: 100px">
            <div class="form-group row justify-content-center">

                <label for="email" class="col-sm-1 control-label">სახელი: </label>
                <div class="col-sm-4 input-group">
                    <input type="text" name="email" class="form-control" id="email" placeholder="შეიყვანეთ სახელი">
                </div>
            </div>

            <div class="form-group row justify-content-center">
                <label for="password" class="col-sm-1 control-label">პაროლი: </label>
                <div class="col-sm-4 input-group">
                    <input type="password" name="password" class="form-control" id="password" placeholder="შეიყვანეთ პაროლი">
                </div>
            </div>

            <div class="form-group row justify-content-center">
                <div class="col-sm-4">
                    <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">შესვლა</button>
                </div>
            </div>
        </form>
</div>

<script>
    firstTry();
    function firstTry() {

        let attr = "false";
        attr = <%=request.getAttribute("bad_attempt")%>;
        console.log("&"+attr+"&");
        if(attr.toString() === "true") {console.log("!!!");
           let bad = document.createElement("div");
            bad.classList.add("alert");
            bad.classList.add("alert-danger");
            bad.classList.add("text-center");
            bad.style.width="50%";
            bad.style.margin="auto";
            bad.style.marginBottom= "10px";
            bad.innerHTML = "არასწორი სახელი ან პაროლი";
            document.getElementById("form").prepend(bad);
            document.getElementById("form").style.marginTop = "40px";
        }
    }
</script>