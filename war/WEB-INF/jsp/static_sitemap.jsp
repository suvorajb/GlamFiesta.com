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
<title>GlamFiesta | Sitemap </title>
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
          <li class="selectd"><a href="/faq.htm"><span class="fa fa-question"></span>&nbsp;FAQ</a></li>
          <li><a href="/giveaway.htm"><span class="fa fa-trophy"></span>&nbsp;Giveaway</a></li>
          
        </ul>
        
      </nav><!--/nav-->
      
      <div class="col-sm-10">
        
        <div class="row">
          <div class="col-xs-12">
            
            <div class="row">
              <div class="col-md-12">
                <h1>GlamFiesta - Get Noticed. Inspire Others.</h1>
                 <p class="lead">Sitemap</p>  
              </div>
            </div>
            
            <div class="row">
        		<div class="col-md-9">
        		<!--  // main content here -->
				
				<h3>Lookbook / #OOTD / Fashion / Street Style</h3>
        		<div class="row">
        			<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<p>
										<a href="/newlooks?refrr=sitemap">All Newly Posted Looks</a> | <a href="/toplooks">Default Top Looks</a>
										<br/><br/><b> Looks By Style</b> <br/>
										<a href="/search?style=Street+Style">Street Style</a> | 
										<a href="/search?style=Vintage">Vintage</a> | 
										<a href="/search?style=Metro+Style">Metro Style</a> | 
										<a href="/search?style=Bohemian">Bohemian Style</a> | 
										<a href="/search?style=Student,+back+to+school">Student, back to school</a> | 
										<a href="/search?style=Business+Casual">Business Casual</a> | 
										<a href="/search?style=Classic+&+Elegant">Classic & Elegant</a> | 
										<a href="/search?style=Modern+&+Trendy">Modern & Trendy</a> 									
										<a href="/search?style=Only+Denim">Only Denim</a> | 
										<a href="/search?style=Formal+wear">Formal wear</a> | 
										<a href="/search?style=Geek+&+Nerdy">Geek & Nerdy</a> | 
										<a href="/search?style=Leather">Leather</a> | 
										<a href="/search?style=Maternity">Maternity</a> | 
										<a href="/search?style=Sexy">Sexy</a> | 
										<a href="/search?style=Sports">Sports</a> | 
										<a href="/search?style=DIY+(Do+It+Yourself)+Outfit">DIY (Do It Yourself) Outfit</a> | 
										<a href="/search?style=70s+Look">70s Look</a> | 
										<a href="/search?style=80s+Look">80s Look</a> | 
										<a href="/search?style=90s+Look">90s Look</a>	
										<a href="/search?style=Corporate+&+Business Style">Corporate & Business Style</a>
										
										<br/><br/><b> Looks By Season</b> <br/>
										<a href="/search?season=all">All Season Style</a> | 
										<a href="/search?style=Summer">Summer</a> | 
										<a href="/search?style=Fall">Fall</a> | 
										<a href="/search?style=Winter">Winter</a> | 
										<a href="/search?style=Spring">Spring</a>						
									</p>
								</div>
							</div>
					</div>
        		</div>
        		
				<h3>Users / Members / Fashionistas</h3>
        		<div class="row">
        			<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<p>
										<a href="/fashionistas?refrr=sitemap">All Glamfiesta Fashionistas</a> | 
										<a href="/fashionistas/risingstars">Rising Star</a> | 
										<a href="/fashionistas/topleaders">Top Fashionistas with Highest Glam points</a>  
									</p>
								</div>
							</div>
					</div>
        		</div>        		

				<h3>General</h3>
        		<div class="row">
        			<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<p>
										<a href="/fashionistas?refrr=sitemap">About Us</a> | 
										<a href="/signup">Signup / Register at GlamFiesta</a> | 
										<a href="/login">Login</a> 
									</p>
								</div>
							</div>
					</div>
        		</div> 
        		        		
            	</div>
		        <div class="col-md-3">
		        	<h3 class="headline"><span>Advertisements...</span></h3>
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