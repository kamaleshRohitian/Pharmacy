<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Bootstrap for css-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<!-- Bootstrap for js-->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Mail Order Pharmacy</title>

<style type="text/css">

body {
  background-image: url("https://images.unsplash.com/photo-1542736667-069246bdbc6d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1351&q=80");
  background-position: center center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
}
#res:hover{
background-color:white;
font-weight:bold;
color:black;
}
</style>

</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <h2 class="navbar-brand">Mail Order Pharmacy </h2>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent" >
   <ul class="navbar-nav mr-auto"></ul>
    <ul class="navbar-nav">
    <li class="nav-item">
        <a class="nav-link" href="/homenew">Home</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#">Drugs Service <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/refill">Refill Service <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/subscription">Subscription Service <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/logout">Logout</a>
      </li>
    </ul>
  </div>
</nav>
<br>
<br>
<div class="container">
   <h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Search Drug Details By Id</h1>
   <form action="drugbyid" method="get">
      <div class="row">
        <div class="col-sm-3"><input type="number" name="drugid" id="drugid" class="form-control" required="required" min=1 max=100 placeholder="Enter the drug id"></div>
        <div class="col-sm-3"><input type="submit" class="btn btn-success" value="find"> </div>
      </div>
   </form>
   <h3 style="color:red">${notfoundid}</h3>
   <br>
   <c:if test="${not empty DrugName}">
    <table  border="1" class="table">
    <caption>Drugs details By ID</caption>
    <tr style="background-color:black;color:white;"><th scope="col">Drug Name</th><th scope="col">Units</th><th scope="col">Cost</th><th scope="col">Location</th><th scope="col">Manufacturer</th><th scope="col">ManufacturedDate</th><th scope="col">ExpiryDate</th><th scope="col">MedicalComposition</th></tr>
    <tr  id="res"><td> ${DrugName} </td><td> ${Units} </td><td> ${Cost} </td><td> ${Location} </td><td> ${Manufacturer} </td><td> ${ManufacturedDate} </td><td> ${ExpiryDate} </td><td> ${MedicalComposition} </td></tr>
    </table>
   </c:if>
   <br>
   <h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Search Drug Details By Name</h1>
   <form action="DrugsByName" method="get">
      <div class="row">
        <div class="col-sm-3"><input type="text" name="drugName" id="drugName" class="form-control" required="required" placeholder="Enter the drug Name"></div>
        <div class="col-sm-3"><input type="submit" class="btn btn-success" value="find"> </div>
      </div>
   </form>
   <h3 style="color:red">${notfoundname}</h3>
    <br>
   <c:if test="${not empty DrugName1}">
    <table  border="1" class="table">
    <caption>Drugs details By Name</caption>
    <tr style="background-color:black;color:white;"><th scope="col">Drug Name</th><th scope="col">Units</th><th scope="col">Cost</th><th scope="col">Location</th><th scope="col">Manufacturer</th><th scope="col">ManufacturedDate</th><th scope="col">ExpiryDate</th><th scope="col">MedicalComposition</th></tr>
    <tr id="res"><td> ${DrugName1} </td><td> ${Units1} </td><td> ${Cost1} </td><td> ${Location1} </td><td> ${Manufacturer1} </td><td> ${ManufacturedDate1} </td><td> ${ExpiryDate1} </td><td> ${MedicalComposition1} </td></tr>
    </table>
   </c:if>
   <br>
   <h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Get all available drugs here!..</h1>
   <form action="getallDrugs" method="get">
      <div class="row">
        <div class="col-sm-3"><input type="submit" class="btn btn-success" value="find"> </div>
      </div>
   </form>
    <br>
      <c:if test="${not empty drugmap}">
    <table  border="1" class="table">
    <caption>Drugs details </caption>
    <tr style="background-color:black;color:white;"><th scope="col">Drug Id</th><th scope="col">Drug Name</th></tr>
    <c:forEach items="${drugmap}" var="map">
    <tr id="res"><td> ${map.key} </td><td> ${map.value} </td></tr>
    </c:forEach>
    
    </table>
   </c:if>
   <br>
   <h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Get all available stocks here!..</h1>
   <form action="searchStock" method="get">
      <div class="row">
        <div class="col-sm-3"><input type="number" name="drugId" id="drugId" class="form-control" required="required"  min=1 max=100 placeholder="Enter the drug id"></div>
        <div class="col-sm-3"><input type="text" name="location" id="location" class="form-control" required="required" placeholder="Enter the drug Location"></div>
        <div class="col-sm-3"><input type="submit" class="btn btn-success" value="find"> </div>
      </div>
   </form>
   <h1 style="color:green"> ${quantity} </h1>
   <h1 style="color:red">${drugidloc}</h1>
 </div>
 </body>
 </html>