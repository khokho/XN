<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<div class="d-flex" id="wrapper">
<div class="bg-light border-right my-sidebar" id="sidebar-wrapper" style="width: 200px">
    <div class="sidebar-heading"  style="margin: 20px 10px; top: 0px"><a> <b>ადმინისტრატორი</b></a> </div>
    <div class="list-group list-group-flush" style="overflow: auto; height: 90%">
        <a href="#" class="list-group-item list-group-item-action bg-light">ახალი გამოცდა</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">გამოცდები</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">სტუდენტები</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">ლექტორები</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">შეკითხვები</a>
        <a href="/queues-admin" class="list-group-item list-group-item-action bg-light">რიგები</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ლეკვა</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: ია</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">chat: კობი</a>
        <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>
    </div>
</div>
    <!-- Menu Toggle Script -->
    <!--<script>

      $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
      });
    </script>
    -->
</div>
<div class="bg-light border-right " id="sidebar-wrapper">
    <div class="sidebar-heading" id="strut" > </div>
<script>
    function setW() {
        document.getElementById("strut").style.width=(document.getElementById("sidebar-wrapper").offsetWidth-1).toString() + 'px';
    }
    setW();



    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });


</script>
</div>