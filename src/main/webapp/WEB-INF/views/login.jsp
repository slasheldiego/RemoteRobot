<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Remote Robot | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" />">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css" />">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<c:url value="/resources/bower_components/Ionicons/css/ionicons.min.css" />">
  <!-- Theme style -->
  <link rel="stylesheet" href="<c:url value="/resources/dist/css/AdminLTE.min.css" />">
  <!-- iCheck -->
  <link rel="stylesheet" href="<c:url value="/resources/plugins/iCheck/square/blue.css" />">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../../index2.html"><b>Remote</b>Robot</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
  	<c:if test="${empty error && empty msg && empty expired}">
		<p class="login-box-msg">Sign in to start your session</p>
	</c:if>
    <c:if test="${not empty error}">
		<p class="login-box-msg text-red">${error}</p>
	</c:if>
	<c:if test="${not empty msg}">
		<p class="login-box-msg text-aqua">${msg}</p>
	</c:if>
	<c:if test="${not empty expired}">
		<p class="login-box-msg text-red">${expired}</p>
	</c:if>

    <form name="myForm" action="<c:url value='/login' />" method="POST">
      <div class="form-group has-feedback">
        <input type="text" id="username" name="username" class="form-control" placeholder="username">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" id="password" name="password" class="form-control" placeholder="password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
	<script src="<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="<c:url value="/resources/bower_components/jquery-ui/jquery-ui.min.js" />"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
	  $.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.7 -->
	<script src="<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />"></script>
	<!-- Morris.js charts -->
	<script src="<c:url value="/resources/bower_components/raphael/raphael.min.js" />"></script>
	<script src="<c:url value="/resources/bower_components/morris.js/morris.min.js" />"></script>
	<!-- Sparkline -->
	<script src="<c:url value="/resources/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js" />"></script>
	<!-- jvectormap -->
	<script src="<c:url value="/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" />"></script>
	<script src="<c:url value="/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" />"></script>
	<!-- jQuery Knob Chart -->
	<script src="<c:url value="/resources/bower_components/jquery-knob/dist/jquery.knob.min.js" />"></script>
	<!-- daterangepicker -->
	<script src="<c:url value="/resources/bower_components/moment/min/moment.min.js" />"></script>
	<script src="<c:url value="/resources/bower_components/bootstrap-daterangepicker/daterangepicker.js" />"></script>
	<!-- datepicker -->
	<script src="<c:url value="/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js" />"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script src="<c:url value="/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" />"></script>
	<!-- Slimscroll -->
	<script src="<c:url value="/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js" />"></script>
	<!-- FastClick -->
	<script src="<c:url value="/resources/bower_components/fastclick/lib/fastclick.js" />"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/resources/dist/js/adminlte.min.js" />"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="<c:url value="/resources/dist/js/pages/dashboard.js" />"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<c:url value="/resources/dist/js/demo.js" />"></script>
	<script>
	  $(document).ready(function () {
	    $('.sidebar-menu').tree()
	  })
	</script>
</body>
</html>
