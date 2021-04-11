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
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
   <ul class="navbar-nav mr-auto"></ul>
    <ul class="navbar-nav">
    <li class="nav-item">
        <a class="nav-link" href="/homenew">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/drugs">Drugs Service</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Refill Service</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/subscription">Subscription Service<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/logout">Logout</a>
      </li>
    </ul>
  </div>
</nav>
<div class="container">
<br>
<br>
<h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Search Refill by Id</h1>
<form action="/viewRefillStatus" method="get">
<div class="row">
<div class="col-sm-2">
<h4><label>Refill Id</label></h4>
</div>
<div class="col-sm-3">
<input type="number" id="refillId" name="refillId" placeholder="Enter the Refill Id" min=1 required/>
</div>
<div class="col-sm-3">
<input type="submit" class="btn btn-success btn-rounded" value="find" name="find">
</div>
</div>
</form>

<c:if test="${not empty id}">
    <table  border="1" class="table">
    <caption>Refill Details By Id</caption>
    <tr style="background-color:black;color:white;"><th scope="col">Refill ID</th><th scope="col">Cost</th><th scope="col">Payment</th><th scope="col">Quantity</th><th scope="col">Refill Date</th><th scope="col">Order Id</th><th scope="col">Subscription ID</th></tr>
    <tr id="res"><td> ${id} </td><td> ${cost} </td><td> ${payment} </td><td> ${quantity} </td><td> ${rdate} </td><td> ${oid} </td><td> ${sid} </td></tr>
    </table>
   </c:if>
   <h1 style="color:red">${refillerror}</h1>
   <br>
<h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Check Payment Status</h1>
<form action="getRefillDuesAsOfDate" method="get">
<div class="row">
<div class="col-sm-2">
<h4><label>Subscription Id</label></h4>
</div>
<div class="col-sm-3">
<input type="number" id="subId" name="subId" placeholder="Enter the Subscribtion Id" min=1 required/>
</div>
<div class="col-sm-3">
<input type="submit" class="btn btn-success btn-rounded" value="find" name="find">
</div>
</div>
</form>
<h1 style="color:red">${paid}</h1>
   <h1 style="color:red">${refillerror1}</h1>
   <br>
<h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Refill Request</h1>
<form action="requestAdhocRefill" method="post">
<div class="row">
<div class="col-sm-2">
<h4><label>Subscription Id</label></h4>
</div>
<div class="col-sm-3">
<input type="number" id="subId" name="subId" placeholder="Enter the Subscribtion Id" min=1 required/>
</div>
</div>
<div class="row">
<div class="col-sm-2">
<h4><label>Location</label></h4>
</div>
<div class="col-sm-3">
<input type="text" id="location" name="location" placeholder="Enter the location"  required/>
</div>
</div>
<div class="row">
<div class="col-sm-1"></div>
<div class="col-sm-3">
<input type="submit" class="btn btn-success btn-rounded" value="find" name="find">
</div>
</div>
</form>
<h1 style="color:green">${available}</h1>
<h1 style="color:red">${notavailable}</h1>
<br>
<h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Payment</h1>
<form action="payment" method="post">
<div class="row">
<div class="col-sm-2"><h4><label>Refill Order Id</label></h4></div>
<div class="col-sm-3">
<input type="number" id="refillID" name="refillID" placeholder="Enter the Refill Id" min=1 required/>
</div>
</div>
<div class="row">
<div class="col-sm-2"><h4><label>Subscription Id</label></h4></div>
<div class="col-sm-3">
<input type="number" id="subId" name="subId" placeholder="Enter the Subscription Id" min=1 required/>
</div>
</div>
<div class="row">
<div class="col-sm-2"><h4><label>Amount</label></h4></div>
<div class="col-sm-3">
<input type="number" id="amount" name="amount" placeholder="Enter the amount" min=1 required/>
</div>
</div>
<div class="row">
<div class="col-sm-1"></div>
<div class="col-sm-3">
<input type="submit" class="btn btn-success btn-rounded" value="find" name="find">
</div>
</div>
</form>
<h1 style="color:green">${balance}</h1>
</div>
</body>
</html>