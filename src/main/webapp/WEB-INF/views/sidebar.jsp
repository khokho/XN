<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<style>
    span {
    display: inline-block;
    width: 180px;
    white-space: nowrap;
    overflow: hidden !important;
    text-overflow: ellipsis;
    }
</style>
<div class="my-sidebar bg-light border-right" id="sidebar-wrapper" style="position: fixed; z-index: 10; overflow: auto;" >
    <div class="sidebar-heading" ><span title="${username}">${username}</span> </div>
    <div class="list-group list-group-flush">
        <div id="sidebar-elements">
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/sidebar.js" />" type="text/babel"></script>
