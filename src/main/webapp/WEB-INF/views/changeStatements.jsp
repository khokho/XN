<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="alert alert-info text-center" style="margin-right: 10px;">
    ატვირთეთ ის პირობები, რომელთა შეცვლა, ან დამატებაც გსურთ. თუ პირობის უცვლელად დატოვება გინდათ, შესაბამისი ველი შეგიძლიათ დატოვოთ ცარიელი.
</div>
<div style="margin-top: 30px">
    <form enctype="multipart/form-data">
            <%--@elvariable id="uploaded" type="java.util.List"--%>
        <c:forEach items="${uploaded}" var="isUploaded" varStatus="status">
        <c:set value="${status.index+1}" var="i"/>
        <div class="row" style="margin-bottom: 5px; margin-left: 5px;">
            <label for="statement_${i}" style="margin-right: 10px"> ვარიანტი #${i}:</label>
            <c:if test="${isUploaded}">
                <i class="fa fa-check fa-lg" aria-hidden="true" style="color: green; opacity: 100"></i>
            </c:if>
            <c:if test="${!isUploaded}">
                <i class="fa fa-check fa-lg" aria-hidden="true" style="color: green; opacity: 0"></i>
            </c:if>
                <input name="statement_${i}" type="file" style="margin-left: 3px;">
            </div>
        </c:forEach>
        <div class="form-group row">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" formmethod="post" class="btn btn-lg btn-block btn-success">ცვლილებების შეტანა</button>
            </div>
        </div>
    </form>
</div>
