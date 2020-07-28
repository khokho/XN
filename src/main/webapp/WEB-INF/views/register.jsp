
<%@ page contentType="text/html;charset=UTF-8"%>
<div class="align-content-center" style="vertical-align: middle;">

    <form id="form" accept-charset="UTF-8" role="form" style="margin-top: 100px">
        <div class="form-group row justify-content-center">
            <label for="email" class="col-sm-1 control-label">ფოსტა: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="email" class="form-control" id="email" placeholder="შეიყვანეთ ფოსტა">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <label for="password" class="col-sm-1 control-label">პაროლი: </label>
            <div class="col-sm-4 input-group">
                <input type="password" name="password" class="form-control" id="password" placeholder="შეიყვანეთ პაროლი">
            </div>
        </div>


        <div class="form-group row justify-content-center">
            <label for="status" class="col-sm-1 control-label">სტატუსი: </label>
            <div class="col-sm-4 input-group">
                <select name="status" class="form-control" id="status">
                    <option value="student">სტუდენტი</option>
                    <option value="lector">ლექტორი</option>
                    <option value="admin">ადმინისტრატორი</option>
                </select>
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <label for="name" class="col-sm-1 control-label">სახელი: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="name" class="form-control" id="name" placeholder="შეიყვანეთ სახელი">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <label for="lastName" class="col-sm-1 control-label">გვარი: </label>
            <div class="col-sm-4 input-group">
                <input type="text" name="lastName" class="form-control" id="lastName" placeholder="შეიყვანეთ გვარი">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <div class="col-sm-4">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">დამატება</button>
            </div>
        </div>
    </form>
</div>

