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
  background-image: url("https://images.pexels.com/photos/4021803/pexels-photo-4021803.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
  background-position: center center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
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
        <a class="nav-link" href="/refill">Refill Service</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Subscription Service<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/logout">Logout</a>
      </li>
    </ul>
  </div>
</nav>
<br>
<div class="container">
<form action="subscribe" method="post">
<h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Drugs Subscription</h1>

<div class="row">
<div class="col-sm-3">
<h4><label>Prescription Id</label></h4>
</div>
<div class="col-sm-4">
<input type="number" id="prescriptionId" name="prescriptionId" min=1  required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Your Location</label></h4>
</div>
<div class="col-sm">
<input type="text" id="memberLocation" name="memberLocation" placeholder="Enter your location" required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Policy Number</label></h4>
</div>
<div class="col-sm-4">
<input type="text" id="policyNumber" name="policyNumber" placeholder="Enter the Policy Number" required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Insurance Provider</label></h4>
</div>
<div class="col-sm">
<input type="text" id="insurance" name="insurance" placeholder="Enter the Insurance Provider" required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Prescription Date</label></h4>
</div>
<div class="col-sm-4">
<input type="date" id="prescriptionDate" name="prescriptionDate"  required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Drug Name</label></h4>
</div>
<div class="col-sm">
 <input type="text" id="drugName" name="drugName" placeholder="Enter the Drug Name" required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Doctor Name</label></h4>
</div>
<div class="col-sm">
<input type="text" id="doctorName" name="doctorName" placeholder="Enter the Doctor Name" required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Dosage Definition</label></h4>
</div>
<div class="col-sm">
<input type="text" id="dosage" name="dosage" placeholder="Enter the Dosage" required/>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Course Duration</label></h4>
</div>
<div class="col-sm">
 <select name="courseDuration" id="courseDuration">
       <option value="Monthly">Monthly</option>
       <option value="Weekly">Weekly</option>
 </select>
</div>
</div>

<div class="row">
<div class="col-sm-3">
<h4><label>Quantity</label></h4>
</div>
<div class="col-sm-4">
<input type="number" id="quantity" name="quantity"  min=1 required/>
</div>
</div>
<br>
<div class="row">
<div class="col-sm-2"></div>
<div class="col-sm">
<input type="submit" class="btn btn-success btn-rounded" value="Subscribe" name="Subscribe">
</div>
</div>
</form>
<h1 style="color:green"> ${success} </h1>
<h1 style="color:red"> ${error} </h1>
<br>

<form method="get" action="unsubscribe">
<h1 style="text-shadow: 2px 2px #FF0000; font-weight:bold;">Drugs Unsubscription</h1>
<div class="row">
<div class="col-sm-3"><h4><label>Subscription ID</label></h4></div>
<div class="col-sm-4">
<input type="number" id="subscriptionId" name="subscriptionId"  min=1 required/>
</div>
</div>
<div class="row">
<div class="col-sm-3"><h4><label>Prescription ID</label></h4></div>
<div class="col-sm-4">
<input type="number" id="prescriptionId" name="prescriptionId"  min=1 required/>
</div>
</div>
<div class="row">
<div class="col-sm-2"></div>
<div class="col-sm">
<input type="submit" class="btn btn-success btn-rounded" value="Unsubscibe" name="Unsubscibe">
</div>
</div>
</form>
<h1 style="color:red">${unsubscribe}</h1>
<h1 style="color:red;background-color:white;"> ${error1} </h1>
</div>
</body>
</html>