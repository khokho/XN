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
          <ul class="navbar-nav ml-auto" style="margin-right: 30px">
            <li class="nav-item row">
                <div style="padding: 8px 0">
                  <a class="hours"></a> :
                  <a class="min"></a> :
                  <a class="sec"></a>
                </div>

                <a class="nav-link" style="margin-left: 30px;" id="logout" href="${pageContext.request.contextPath}/logout">გასვლა</a>
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
     document.getElementById("content").style.marginTop=(document.getElementById("nav").offsetHeight + 10).toString() + 'px';
  }

  function hide() {
    if(${loggedin == 0}) document.getElementById("logout").hidden=true;
  }

  $(document).ready(function() {
    if(<%=request.getAttribute("time") == null%>){
      setInterval( function() {

        var hours = new Date().getHours();
        $(".hours").html(( hours < 10 ? "0" : "" ) + hours);

        var minutes = new Date().getMinutes();
        $(".min").html(( minutes < 10 ? "0" : "" ) + minutes);

        var seconds = new Date().getSeconds();
        $(".sec").html(( seconds < 10 ? "0" : "" ) + seconds);
      },1000);
    }
    else{
      setInterval( function() {
        console.log(${time});

        var hours = Math.floor((${time} - new Date().getTime()) / 3600 / 1000);
        $(".hours").html(( hours < 10 ? "0" : "" ) + hours);

        var minutes = Math.floor(((${time} - new Date().getTime()) % 3600000) / 60000);
        $(".min").html(( minutes < 10 ? "0" : "" ) + minutes);

        var seconds = Math.floor(((${time} - new Date().getTime()) % 60000) / 1000);
        $(".sec").html(( seconds < 10 ? "0" : "" ) + seconds);
      },1000);
    }

  });
</script>


