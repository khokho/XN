<%--@elvariable id="loggedin" type="ge.exen.configuration.InterceptConfig"--%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<body lang="en">

<head>
    <jsp:include page="includes.jsp" />
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


  <title>${title}</title>

</head>
<style>
  .full-width {
    width: 100%;
  }

  html, body{
    height: 100%;
  }
  .full-height{
    height: 100%;
  }
  .my-fixed-top{
    width: 100%;
    z-index: 1;
    position: fixed !important;
    top: 0;
    left: 0;
    height: fit-content;
  }
</style>

  <div class="full-height d-flex" id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar">
      <jsp:include page="${sidebar}"/>
    </div>



    <div class="full-width" id="page-content-wrapper">
      <!-- Navbar -->
      <nav class="my-fixed-top navbar navbar-light bg-light border-bottom" id="nav" >

          <div><b>${title}</b></div>


        <a class="nav-link navbar-nav" id="out" style="margin-right: 190px" href="${pageContext.request.contextPath}/logout">გასვლა</a>

      </nav>
      <div id="content" style="top: 0px">
        <jsp:include page="${content}"/>
      </div>
      <!-- Page Content-->
    </div>

  </div>

<script>
  hide();
  setMargin();
  function setMargin(){
    document.getElementById("content").style.marginLeft=(document.getElementById("sidebar-wrapper").offsetWidth + 15).toString() + 'px';
    document.getElementById("content").style.marginTop=(document.getElementById("nav").offsetHeight + 10).toString() + 'px';
    document.getElementById("nav").style.marginLeft=(document.getElementById("sidebar-wrapper").offsetWidth).toString() + 'px';

  }

  function hide() {
    if(${loggedin == 0}) document.getElementById("out").hidden=true;
  }
</script>


