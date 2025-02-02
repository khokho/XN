<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- JS-s -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- Core JQuery -->
<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>

<!-- Core WebSockets -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"
        integrity="sha512-wMmlpbPCY778BJObxSz1tBYZTaz8q6eAJGiQke+r0AtqqgYPfAmwcip5p1HAmWKA7pxcqNeY8hz1mkHgZhqIiQ=="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
        integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
        crossorigin="anonymous"></script>


<!--
    Moment.js - calendar, date and time handler. Delete locale include to use english one.
    FIXME english calendar uses AM/PM format.
-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/moment-with-locales.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/locale/ka.min.js"></script>

<!-- Bootstrap 4 date and time picker js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- datetimepicker helper js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- CSS-s -->

<!-- Font-awesome Icon font css -->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Datetimepicker css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />

<!-- Bootstrap 4 core css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- Bootstrap 4 sidebar css -->
<%--<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sidebar/0.2.2/css/sidebar.css" rel="stylesheet">--%>


<link href="<c:url value="/resources/css/simple-sidebar.css" />"  rel="stylesheet"/>
<script src="<c:url value="/resources/js/websockets.js" />"></script>
<script src="<c:url value="/resources/js/notifications.js" />"></script>

<!-- Styles -->

<%--<style>--%>
<%--    .my-sidebar{--%>
<%--        position: fixed;--%>
<%--        height: 100%;--%>
<%--        top: 0;--%>
<%--    }--%>
<%--</style>--%>

<!--
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
-->
