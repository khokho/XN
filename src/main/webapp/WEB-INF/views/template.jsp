<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


  <title>${title}</title>

  <!-- Bootstrap core CSS -->
  <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../../resources/css/simple-sidebar.css" rel="stylesheet">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>

<body>

<div class="d-flex" id="wrapper">

  <!-- Sidebar -->
  <jsp:include page="${sidebar}"></jsp:include>


  <div id="page-content-wrapper">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom align-content-center" >
      <div><b>${title}</b></div>

      <div class="ml-auto" id="navbarSupportedContent">
        <ul class="navbar-nav">

          <li class="nav-item">
            <a class="nav-link" href="#">გასვლა</a>
          </li>
        </ul>
      </div>
    </nav>

    <!-- Page Content-->
    <jsp:include page="${content}"></jsp:include>
  </div>

</div>

</body>

</html>
