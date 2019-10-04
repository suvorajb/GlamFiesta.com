<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta | Giveaway </title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="glamfiesta giveaway">

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
          <li><a href="/"><span class="fa fa-leaf"></span>&nbsp;Fresh Looks</a></li>
          <li><a href="/toplooks"><span class="fa fa-thumbs-o-up"></span>&nbsp;Top Looks</a></li>
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
          <li class="selectd"><a href="/giveaway.htm"><span class="fa fa-trophy"></span>&nbsp;Giveaway</a></li>
          
        </ul>
				</div>
				<!-- //row -->
				<div class="row">
					<h4 class="headline">
						<span>Lookbook By Season</span>
					</h4>
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-6">
						<a href="/search?season=Summer" target="_blank"> <img
							src="/resources/img/static/summer_fashion_img.jpg"
							class="img-responsive"> <b>Summer</b>
						</a>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<a href="/search?season=Spring" target="_blank"> <img
							src="/resources/img/static/spring_fashion_img.jpg"
							class="img-responsive"> <b>Spring</b>
						</a>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-6">
						<a href="/search?season=Fall" target="_blank"> <img
							src="/resources/img/static/fall_fashion_img.jpg"
							class="img-responsive"> <b>Fall</b>
						</a>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<a href="/search?season=Winter" target="_blank"> <img
							src="/resources/img/static/winter_fashion_img.jpg"
							class="img-responsive"> <b>Winter</b>
						</a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<a href="/search?season=All Season" target="_blank"> <img
							src="/resources/img/static/allseason_fashion_img.jpg"
							class="img-responsive"> <b>All Season Lookbook</b>
						</a>
					</div>
				</div>        
      </nav><!--/nav-->
      
      <div class="col-sm-10">
        
        <div class="row">
          <div class="col-xs-12">
            
            <div class="row">
              <div class="col-md-12">
                <h1>GlamFiesta - Get Noticed. Inspire Others.</h1>
                 <p class="lead">Giveaway & contests</p>  
              </div>
            </div>
            
            <div class="row">
        		<div class="col-md-9">
        		<!--  // main content here -->

	        		<div class="row">
	        			<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-body">
										<h1>Giveaway - $5 Amazon e-Giftcard for everyone</h2>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="alert alert-danger" role="alert">
				  							<h2>Giveaway Closed - We are no longer accepting any entry</h2>
										</div>
										<h3 class="text-center">Steps to win</h3>
										<h3>Step 1: Signup for free</h3>
										<p>
											<a href="/signup.htm">Signup</a> at GlamFiesta is free. All you need is a valid email address. Register for free with us.
											<small>We'll give you the $5 amazon e-gift card (US & UK only) for any two lookbooks you post with us and one time only.</small>
										</p>
										
										<h3>Step 2: Post your 2 different outfit/look photos</h3>
										<p>
											<ul>
												<li><small>Photos should be of good quality and outfit must be clearly visible</small></li>
												<li><small>Full outfit look only, from neck to knee should be visible (<a href="http://www.glamfiesta.com/u/rachelbkr/lookbook/view/5091203766812672/leopard-print-chiffon-maxi-dress-for-coming-summer-.htm" target="_blank">see an example</a>)</small></li>
												<li><small>No commercial photos. Only look of yourself is allowed and you own the rights.</small></li>
												<li><small>Each lookbook should have different look only</small></li>
												<li><small>Your face should be shown in look so we can understand it is your look</small></li>
												<li><small>Write at least 2 lines (30 words min) about your look including where can others buy this outfits etc.</small></li>
											</ul>
										</p>
										
										<h3>Step 3: Fill up the form below</h3>
										<p>
											<div class="col-sm-12 col-md-6 col-lg-6">
						                
													<form:form modelAttribute="contestform" method="POST" action="/giveaway">
													
														<div class="form-group">
															<div class="col-sm-12"><label>Your registered emailid</label><br/>
																<form:input type="text" path="giveaway_email" class="form-control" />
															</div>
														</div>
														<div class="form-group">
															<div class="col-sm-12"><label>Your username at GlamFiesta</label><br/>
																<form:input type="text" path="giveaway_uname" class="form-control" />
															</div>
														</div>
														<div class="form-group">
															<div class="col-sm-12"><label>Your look 1 URL(copy from browser address bar):</label><br/>
																<form:input type="text" path="giveaway_look_url1" class="form-control" />
															</div>
														</div>	
														<div class="form-group">
															<div class="col-sm-12"><label>Your look 2 URL(copy from browser address bar):</label><br/>
																<form:input type="text" path="giveaway_look_url2" class="form-control" />
															</div>
														</div>	
														<!-- div class="form-group">
															<div class="col-sm-12"><label>Your look 3 URL:</label><br/>
																<form:input type="text" path="giveaway_look_url3" class="form-control" />
															</div>
														</div-->
														<div class="form-group">
															<div class="col-sm-12"><br/><input type="submit" class="btn btn-red btn-block" value="Join Giveaway"></div>
														</div>																																										
								                    
								                	</form:form>
						            		</div><!-- col-sm-6 -->
										</p>
									</div>
								</div>
								
								<div class="panel panel-default">
									<div class="panel-body">
										<h3 class="headline"><span>Advertisements...</span></h3>
							            <div  class="col-sm-12 col-md-6">
							                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
											<!-- static_pg_glamfiesta_rect1 -->
											<ins class="adsbygoogle"
											     style="display:inline-block;width:300px;height:250px"
											     data-ad-client="ca-pub-9745887657365691"
											     data-ad-slot="7625793253"></ins>
											<script>
											(adsbygoogle = window.adsbygoogle || []).push({});
											</script>
							            </div>
							            <div  class="col-sm-12 col-md-6">
							                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
											<!-- static_pg_glamfiesta_rect1 -->
											<ins class="adsbygoogle"
											     style="display:inline-block;width:300px;height:250px"
											     data-ad-client="ca-pub-9745887657365691"
											     data-ad-slot="7625793253"></ins>
											<script>
											(adsbygoogle = window.adsbygoogle || []).push({});
											</script>
							            </div>
									</div>
								</div>
						</div>
					
        		</div>
        		
            	</div>
		        <div class="col-md-3">
					<!-- // Giveaway winner -->
					
					<div class="row">
						<c:if test="${not empty winners }">  
								<ul class="list-group">	
									<c:forEach items="${winners}" var="winner">
										
										<li class="list-group-item">
					                        <div class="row">
						                            <div class="col-xs-5 col-md-5">
						                                <a href="/u/${winner.username }"><img src="${winner.uavatar }" class="img-responsive image-circle" alt="${winner.username }" /></a></div>
						                            <div class="col-xs-7 col-md-7">
					                                    <div class="mic-info">
					                                        <b>${winner.username }</b>
					                                    </div>
						                                <div class="comment-text">
						                                    <p>
						                                    	Won <b>${winner.giveaway_rewards_amnt }</b> on ${winner.date_of_winning }
						                                    </p>
						                                </div>
		
						                            </div>
						                    </div>
						                </li>
						                
						            </c:forEach>    
					                
								</ul>
								
						</c:if>		
				    </div>
				    
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