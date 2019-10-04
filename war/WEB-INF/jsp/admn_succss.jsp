<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>GlamFiesta - Social Network for YouTube Fashionistas and fashion bloggers |
	LOOKBOOK | OOTD | Style Inspiration</title>
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
</head>

<body>
<div class="container">
  
  <div class="row">
    
    <nav class="col-sm-2"><!--nav-->
      <div class="row">    
        <div class="col-sm-12"><!--logo-->
          <img src="/resources/img/logo_v2_1.png" alt="GlamFiesta" class="img-responsive center"/>
        </div><!--/logo-->
        <ul class="nav nav-pills nav-stacked menu">
          <li>&nbsp;</li>
          <li class="selectd"><a href="/"><span class="fa fa-leaf"></span>&nbsp;Fresh Looks</a></li>
          <li><a href="/fashionistas"><span class="fa fa-users"></span>&nbsp;Fashionistas</a></li>
          <c:if test="${ul.loginstatus == true }">
          	<li><a href="/dashboard"><span class="fa fa-tachometer"></span>&nbsp;Dashboard</a></li>
          	<li><a href="/logout"><span class="fa fa-sign-in"></span>&nbsp;Sign Out</a></li>
          </c:if>
          <c:if test="${ul.loginstatus !=true }">
          	<li><a href="/login"><span class="fa fa-sign-in"></span>&nbsp;Sign In</a></li>
          	<li><a href="/signup"><span class="fa fa-user"></span>&nbsp;Sign Up</a></li>          	
          </c:if>
          <li><a href="/aboutus.htm"><span class="fa fa-chevron-circle-right"></span>&nbsp;About Us</a></li>
          <li><a href="/contactus.htm"><span class="fa fa-envelope"></span>&nbsp;Contact Us</a></li>
          <li><a href="/how-to-post-look.htm"><span class="fa fa-question-circle"></span>&nbsp;How 2 Post</a></li>
          <li><a href="/faq.htm"><span class="fa fa-question"></span>&nbsp;FAQ</a></li>
        </ul>
        
      </nav><!--/nav-->
      
      <div class="col-sm-10">
        
        <div class="row">
          <div class="col-xs-12">

        		<!-- dashboard main content page -->
				    
				    <!-- Topic Header -->
				      <div class="topic">
				        <div class="container">
				          <div class="row">
				            <div class="col-sm-6"><h3>Post A New Look</h3></div>
				            <div class="col-sm-6">
				            	<ol class="breadcrumb">
				            		<li><a href="/dashboard"><b><c:out value="${usrbo.account_username }"/>'s Dashboard</b></a></li>
					                <li class="active">Update Profile</li>
					            </ol>
				            </div>
				          </div>
				        </div>
				      </div>   
				      
				      
						    <div class="profile-header">
						    	<c:if test="${not empty succsmssg }">
									<div class="alert alert-success alert-dismissible" role="alert">
			  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			  							<h3 class="profile-name"><c:out value="${succsmssg}"/></h3>
									</div>
								</c:if>
								<c:if test="${not empty errormssg }">
									<div class="alert alert-danger alert-dismissible" role="alert">
			  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			  							<h3 class="profile-name"><c:out value="${errormssg}"/></h3>
									</div>
								</c:if>	
							</div><!-- profile-header -->

						
				<!-- // dashboard main content page -->
          </div><!--  //col-xs-12 -->
        </div><!-- // row -->
        
      </div>
    </div>   
        
 </div><!--/.content-->
 
	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="copyright">
					Copyright 2015-2016 by 
					<a href="">GlamFiesta</a> | All Rights Reserved | 
					<a href="/privacy_policy.htm">Privacy Policy</a> | 
					<a href="/tos.htm">Terms of Service</a> | 
					<a href="/sitemap.htm">Sitemap</a>
				</div>
			</div>
		</div>
	</footer>

<!-- JavaScript -->
<script src="/resources/js/jquery-1.10.2.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/validate.min.js"></script>
<script src="/resources/js/bootstrap-tagsinput.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript">
	new FormValidator('lookbookform', [
	{
	    name: 'lookbookname',
	    display: 'Looks Title',
	    rules: 'required|max_length[499]|min_length[6]'
	},
	{
	    name: 'outfit1',
	    display: 'Photo1',
	    rules: 'required|is_file_type[gif,png,jpg,jpeg]'
	}
	], function(errors, evt) {
	
	    /*
	     * DO NOT COPY AND PASTE THIS CALLBACK. THIS IS CONFIGURED TO WORK ON THE DOCUMENTATION PAGE ONLY.
	     * YOU MUST CUSTOMIZE YOUR CALLBACK TO WORK UNDER YOUR ENVIRONMENT.
	     */
	
	    var SELECTOR_ERRORS = $('.error_box')
	    
	    if (errors.length > 0) {
	        SELECTOR_ERRORS.empty();
	
	        for (var i = 0, errorLength = errors.length; i < errorLength; i++) {
	            SELECTOR_ERRORS.append('<h4><font color=red>' + errors[i].message + '</font></h4>');
	        }
	
	    }else {
	    	return true;
	    }
	});
</script>
</body>
</html>