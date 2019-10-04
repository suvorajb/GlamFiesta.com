<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta | About Us </title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content=glamfiesta about us">

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
          <li class="selectd"><a href="/aboutus.htm"><span class="fa fa-chevron-circle-right"></span>&nbsp;About Us</a></li>
          <li><a href="/contactus.htm"><span class="fa fa-envelope"></span>&nbsp;Contact Us</a></li>
          <li><a href="/how-to-post-look.htm"><span class="fa fa-question-circle"></span>&nbsp;How 2 Post</a></li>
          <li><a href="/faq.htm"><span class="fa fa-question"></span>&nbsp;FAQ</a></li>
          <li><a href="/giveaway.htm"><span class="fa fa-trophy"></span>&nbsp;Giveaway</a></li>
          
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
                 <p class="lead">About Us</p>  
              </div>
            </div>
            
            <div class="row">
        		<div class="col-md-9">
        			<div class="panel panel-default">
						<div class="panel-body">
				
							<P><font size="4">glamfiesta.com is a platform for all people who love trying new outfits and love to share with others. We developed this social platform for all fashion lovers around the 
							world where anyone can upload photo, provide some outfit information like where you have bought it from and inspire others.</font></P>
							<P><font size="4">Through GlamFiesta, Fashionistas can connect with other Fashionistas and like or comments their photos. We try to help you discover amazing lookbook from around the world 
							and get latest fashion trend, Outfit of the Day (OOTD) in your area.</font></P>
			        		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					                <!-- Indicators -->
					                <ol class="carousel-indicators">
					                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
					                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
					                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
					                </ol>
					                <!-- Wrapper for slides -->
					                <div class="carousel-inner">
					                    <div class="item active">
								              <a href="https://www.flickr.com/photos/lojacupcake/5391757188" title="Especial Lookbook by Loja Cupcake, on Flickr">
								              	<img src="https://farm6.staticflickr.com/5137/5391757188_c62214220e_o.jpg" class="img-responsive" alt="Especial Lookbook">
								              </a>
					                    </div>
					                    <div class="item">
					                        <a href="https://www.flickr.com/photos/bobbivie/8463315462" title="FASHION : BV LOOKBOOK #114 + POCKETING POSSE by bobbi vie, on Flickr">
								              	<img src="https://farm9.staticflickr.com/8369/8463315462_f4c4d8d181_h.jpg" class="img-responsive"  alt="FASHION : BV LOOKBOOK #114 + POCKETING POSSE">
								            </a>
					                    </div>
					                    <div class="item">
								              <a href="https://www.flickr.com/photos/lojacupcake/5469375462" title="Especial Lookbook by Loja Cupcake, on Flickr">
								              	<img src="https://farm6.staticflickr.com/5137/5469375462_62706abe4d_o.jpg" class="img-responsive"  alt="Especial Lookbook">
								              </a>
					                    </div>
					                    <div class="item">
								              <a href="https://www.flickr.com/photos/bobbivie/6110123759" title="Bobbi Vie : Lookbook By Alex Bauzon by bobbi vie, on Flickr">
								              	<img src="https://farm7.staticflickr.com/6065/6110123759_8cbe03d4aa_b.jpg" class="img-responsive"  alt="Bobbi Vie : Lookbook By Alex Bauzon">
								              </a>
					                    </div>
					                </div>
					                <!-- Controls -->
					                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
					                    <span class="glyphicon glyphicon-chevron-left"></span></a><a class="right carousel-control"
					                        href="#carousel-example-generic" data-slide="next"><span class="glyphicon glyphicon-chevron-right">
					                    </span>
					                </a>
					        </div>
					        <h3>We are proudly running on <img src="https://cloud.google.com/_static/images/gcp-logo.png" /></h3>
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