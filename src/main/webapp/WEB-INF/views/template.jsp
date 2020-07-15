<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


  <title>${title}</title>

  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <!-- Custom styles for this template -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sidebar/0.2.2/css/sidebar.css" rel="stylesheet">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

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
    <!-- Page Content-->
    <jsp:include page="${content}"></jsp:include>

</div>

</body>

</html>
