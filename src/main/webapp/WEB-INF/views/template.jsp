<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

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
    top: inherit;
    left: inherit;
    height: fit-content;
  }
</style>
<body>

<div class="full-height d-flex" id="wrapper">

  <!-- Sidebar -->
  <jsp:include page="${sidebar}"></jsp:include>


  <div class="full-width" id="page-content-wrapper">
    <!-- Navbar -->
    <nav class="my-fixed-top navbar navbar-light bg-light border-bottom" >

        <div><b>${title}</b></div>


      <a class="nav-link navbar-nav" style="margin-right: 190px" href="#">გასვლა</a>

    </nav>
    <div style="height: 60px"></div>
    <!-- Page Content-->
    <jsp:include page="${content}"></jsp:include>

</div>

</body>

</html>
