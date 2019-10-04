<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta | Forgot Password Page </title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Forget Password page">

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style1.css" rel="stylesheet">
<link href="resources/css/font-awesome.css" rel="stylesheet">
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
          <li><a href="/"><span class="fa fa-leaf"></span>&nbsp;Fresh Looks</a></li>
          <li><a href="/toplooks"><span class="fa fa-thumbs-o-up"></span>&nbsp;Top Looks</a></li>
          <li><a href="/fashionistas"><span class="fa fa-users"></span>&nbsp;Fashionistas</a></li>
          <c:if test="${ul.loginstatus == true }">
          	<li><a href="/dashboard"><span class="fa fa-tachometer"></span>&nbsp;Dashboard</a></li>
          	<li><a href="/logout"><span class="fa fa-sign-in"></span>&nbsp;Sign Out</a></li>
          </c:if>
          <c:if test="${ul.loginstatus !=true }">
          	<li class="selectd"><a href="/login"><span class="fa fa-sign-in"></span>&nbsp;Sign In</a></li>
          	<li><a href="/signup"><span class="fa fa-user"></span>&nbsp;Sign Up</a></li>          	
          </c:if>
          <li><a href="/aboutus.htm"><span class="fa fa-chevron-circle-right"></span>&nbsp;About Us</a></li>
          <li><a href="/contactus.htm"><span class="fa fa-envelope"></span>&nbsp;Contact Us</a></li>
          <li><a href="/how-to-post-look.htm"><span class="fa fa-question-circle"></span>&nbsp;How 2 Post</a></li>
          <li><a href="/faq.htm"><span class="fa fa-question"></span>&nbsp;FAQ</a></li>
          <li><a href="/giveaway.htm"><span class="fa fa-trophy"></span>&nbsp;Giveaway</a></li>
        </ul>
        
      </nav><!--/nav-->

      
      <div class="col-sm-10">
        
        <div class="row">
          <div class="col-xs-12">
            
            <div class="row">
              <div class="col-md-12">
                <h1>GlamFiesta - Get Noticed. Inspire Others.</h1>
                <p class="lead">Forgot Password</p>
              </div>
            </div>
            
            <div class="row">
        		<div class="col-md-9">
        		<!--  // main content here -->
						<section>
						  
						    <div class="signinpanel">
						        
						        <div class="row">
						            
						            <div class="col-sm-2 col-md-3 col-lg-3">
						                
						                <!-- div class="signin-info">
						
											<h4>Sign in below with popular social id</h4>
						                    <ul>
						                        <li><a href="" class="btn btn-primary"><i class="fa fa-facebook"></i> | Login with Facebook </a></li>
						                        <li><a href="" class="btn btn-info"><i class="fa fa-twitter"></i> | Login with Twitter </a></li>
						                        <li><a href="" class="btn btn-danger"><i class="fa fa-google-plus"></i> | Login with Google+ </a></li>
						                    </ul>
						                    <div class="mb20"></div>
						                    <strong>Already a member? <a href="/login">Sign In</a></strong>
						                </div --><!-- signin0-info -->
						            
						            </div><!-- col-sm-6 -->
						            <div class="col-sm-8 col-md-3 col-lg-6">
						                
											<form:form modelAttribute="forgotpform" method="POST" action="/forgotpwd">
											
						                    <h4 class="nomargin">Forgot Password</h4>
						                    <p><small>Enter your username and email id which you used to signup. We'll send password retreival information to your registered email id.</small></p>
						                                        
						                    <c:if test="${not empty succsmssg }">
												<div class="alert alert-success alert-dismissible" role="alert">
						  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						  							<c:out value="${succsmssg}"/>
												</div>
											</c:if>
											<c:if test="${not empty errmssg }">
												<div class="alert alert-danger alert-dismissible" role="alert">
						  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						  							<c:out value="${errmssg}"/>
												</div>
											</c:if>
											<form:input type="text" path="account_username" class="form-control" placeholder="Enter Username" />
						                    <form:input type="text" path="account_email" class="form-control" placeholder="Enter Emailid" />
						                    <input type="submit" class="btn btn-red btn-block" value="Reset My Password">
						                    
						                </form:form>
						            </div><!-- col-sm-6 -->
						            <div class="col-sm-2 col-md-3 col-lg-3">
						            </div>
						        </div><!-- row -->
						
						        
						    </div><!-- signin -->
						  
						</section>

        		
            	</div>
		        <div class="col-md-3">

		        </div>
		        
    		</div>
                
          </div>
        </div>
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
<!-- JavaScript -->
<script src="/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/resources/js/angular.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-60905111-1', 'auto');
  ga('send', 'pageview');

</script>
</body>
</html>