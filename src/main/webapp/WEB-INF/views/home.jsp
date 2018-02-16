<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c2" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <title>Remote Robot | Dashboard</title>
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
	  <!-- AdminLTE Skins. Choose a skin from the css/skins
	       folder instead of downloading all of them to reduce the load. -->
	  <link rel="stylesheet" href="<c:url value="/resources/dist/css/skins/_all-skins.min.css" />">
	
	  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	  <!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	  <![endif]-->
	
	  <!-- Google Font -->
	  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="../../index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>bb</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Robot</b>ABB</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="<c:url value="/resources/dist/img/user2-160x160.jpg" />" class="user-image" alt="User Image">
              <span class="hidden-xs"><sec:authentication property="name"/></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="<c:url value="/resources/dist/img/user2-160x160.jpg" />" class="img-circle" alt="User Image">

                <p>
                  <sec:authentication property="name"/>
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">Option 1</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Option 2</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Option 3</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="<c:url value='/logout' />" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>

  <!-- =============================================== -->

  <!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<c:url value="/resources/dist/img/user2-160x160.jpg" />" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><sec:authentication property="name"/></p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MAIN NAVIGATION</li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../../index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        RobotABB
        <small><c2:out value="${info.state}" /></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">RobotABB</a></li>
        <li class="active">Console</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">Console - Local IP:${localip}</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                    title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        
        <div class="row">
        		<div class="col-md-6">
          	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Desired position</h3>
            </div>
	        <div class="box-body">
	            <!-- form start -->
	            <sf:form method="POST" action="move" commandName="Move">
	              <div class="box-body">
	                <div class="form-group">
	                  <sf:label path="s" >Message</sf:label>: 
	  			      <sf:input path="s" class="form-control"/>
	  			      <sf:label path="x" >x</sf:label>: 
	  			      <sf:input path="x" class="form-control"/>
	  			      <sf:label path="y" >y</sf:label>: 
	  			      <sf:input path="y" class="form-control"/>
	  			      <sf:label path="z" >z</sf:label>: 
	  			      <sf:input path="z" class="form-control"/>
	                </div>
	              </div>
	              <!-- /.box-body -->
	
	              <div class="box-footer">
		              <c:if test="${ not empty info.action && info.action == 'Disconnect'}">
		                <button type="submit" class="btn btn-primary" >Move</button>
		              </c:if>
		              <c:if test="${ not empty info.action && info.action == 'Connect'}">
		                <button type="submit" class="btn btn-primary" disabled>Move</button>
		              </c:if>
	              </div>
	            </sf:form>
	            <div class="box-body">
		            <c:if test="${ not empty info.action && info.action == 'Connect'}">
						<sf:form method="GET" action="connect" >
							<button type="submit" class="btn btn-primary">${info.action}</button>
		            		</sf:form>
					</c:if>
		            <c:if test="${ not empty info.action && info.action == 'Disconnect'}">
						<sf:form method="GET" action="disconnect" >
							<button type="submit" class="btn btn-primary">${info.action}</button>
		            		</sf:form>
					</c:if>
				</div>
	        </div>
	        <!-- /.box-body -->
        
        		</div>
        		</div>
        		
        		<div class="col-md-6">
	          	<div class="box">
		            <div class="box-header with-border">
		              <h3 class="box-title">Preceding position</h3>
		            </div>
	            </div>
	              <div class="box-body">
	                <form class="form-horizontal">
		              <div class="box-body">
		                <div class="form-group">
		                  <label for="pre_x" class="col-sm-2 control-label">x:</label>
		
		                  <div class="col-sm-10">
		                    <input type="text" class="form-control" id="pre_x" placeholder="${pre_x}">
		                  </div>
		                </div>
		                <div class="form-group">
		                  <label for="pre_y" class="col-sm-2 control-label">y:</label>
		
		                  <div class="col-sm-10">
		                    <input type="text" class="form-control" id="pre_y" placeholder="${pre_y}">
		                  </div>
		                </div>
		                <div class="form-group">
		                  <label for="pre_z" class="col-sm-2 control-label">z:</label>
		
		                  <div class="col-sm-10">
		                    <input type="text" class="form-control" id="pre_z" placeholder="${pre_z}">
		                  </div>
		                </div>
		              </div>
		              <!-- /.box-body -->
		            </form>
	              </div>
	              <!-- /.box-body -->
            </div>
        </div>
        <div class="box-footer">
          Footer
        </div>
        <!-- /.box-footer-->
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.4.0
    </div>
    <strong>Copyright &copy; 2018 <a href="">SD</a>.</strong> All rights
    reserved.
  </footer>
  
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

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
