<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<style>
    .my-sidebar{
        position: fixed;
        height: 100%;
        top: 0;
    }
</style>

<div class="bg-light border-right my-sidebar" id="sidebar-wrapper">
    <div class="sidebar-heading"  style="margin: 20px 10px; top: 0px"><a> <b>ადმინისტრატორი</b></a> </div>
    <div class="list-group list-group-flush">
        <a href="#" class="list-group-item list-group-item-action bg-light">ახალი გამოცდა</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">გამოცდები</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">სტუდენტები</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">ლექტორები</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">შეკითხვები</a>
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
</script>
</div>