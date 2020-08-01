<%--@elvariable id="title" type="java.lang.String"--%>
<%--@elvariable id="loggedin" type="ge.exen.configuration.InterceptConfig"--%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<link href="<c:url value="/resources/css/simple-sidebar.css" />"  rel="stylesheet"/>

<!DOCTYPE html>
<html lang="en">

<head>
  <jsp:include page="includes.jsp" />
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>${title}</title>

</head>
<style>

</style>
  <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
  <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
  <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <%--@elvariable id="sidebar" type="java.lang.String"--%>

    <jsp:include page="sidebar.jsp"/>



    <div class="full-width" id="page-content-wrapper">
      <!-- Navbar -->
      <nav class="sidebar-wrapper expandable navbar navbar-expand-lg navbar-light bg-light border-bottom" id="nav" style="z-index: 9;position: fixed; width: 100%" >
          <ul class="navbar-nav">
            <li class="nav-item">
              <span class="navbar-brand mb-0 h1">${title}</span>
            </li>
          </ul>
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" id="logout" href="${pageContext.request.contextPath}/logout">გასვლა</a>
            </li>
          </ul>

      </nav>
      <div id="content" class="container-fluid sidebar-wrapper expandable" style="padding: 0;right: 0!important;">
        <jsp:include page="${content}"/>
      </div>
      <!-- Page Content-->
    </div>

  </div>

<script>
  hide();
  setMargin();
  function setMargin(){
     //document.getElementById("content").style.marginLeft=(document.getElementById("sidebar-wrapper").offsetWidth).toString() + 'px';
     document.getElementById("content").style.marginTop=(document.getElementById("nav").offsetHeight + 10).toString() + 'px';
     //document.getElementById("nav").style.marginLeft=(document.getElementById("sidebar-wrapper").offsetWidth).toString() + 'px';

  }

  function hide() {
    if(${loggedin == 0}) document.getElementById("logout").hidden=true;
  }
</script>


