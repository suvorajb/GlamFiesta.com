<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="p:domain_verify" content="123cd15c5227a9a956340395d86808b3"/>
<title>Glam Fiesta - Social Network for YouTube Fashionistas and fashion bloggers |
	LOOKBOOK | OOTD | Style Inspiration </title>
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/widget.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
</head>

<body>
<!-- Go to www.addthis.com/dashboard to customize your tools -->
<!-- script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-5504b4a773806ccf" async="async"></script -->

	<div class="spinner">
	    <img id="img-spinner" src="/resources/img/spinner.gif" alt="Loading Looks..."/>
	</div>
<div class="container">
  
  <div class="row">
    
    <nav class="col-sm-2"><!--nav-->
      <div class="row">    
        <div class="col-sm-12"><!--logo-->
          <img src="/resources/img/logo_v2_1.png" alt="GlamFiesta" class="img-responsive center"/>
        </div><!--/logo-->
        <ul class="nav nav-pills nav-stacked menu">
          <li>&nbsp;</li>
          <li><a href="/"><span class="fa fa-thumbs-o-up"></span>&nbsp;Our Picks</a></li>
          <li class="selectd"><a href="/newlooks"><span class="fa fa-leaf"></span>&nbsp;New Looks</a></li>
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
          <li><a href="/giveaway.htm"><span class="fa fa-trophy"></span>&nbsp;Giveaway</a></li>
        </ul>
        
      </nav><!--/nav-->
      
      <div class="col-sm-10">
        
        <div class="row">
          <div class="col-xs-12">
            
            <div class="row">
              <div class="col-md-12">
                <h1>GlamFiesta</h1> <p class="lead">Celebrate the fashionista in you</p>  
              </div>
            </div>
            
            <div class="row">
        		<div class="col-md-8">
					<!-- div class="alert alert-info alert-dismissible" role="alert">
 							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
 							<p class="lead">GlamFiesta Giveaway !! Post 3 looks to win $10 Amazon or Macy's eGift card. <a href="/giveaway.htm">Click Here</a> to learn more...</p>
					</div -->
					<c:if test="${not empty lookbookwrapper }">
						<c:if test="${not empty lookbookwrapper.lookbooklist }">
							<% int i = 0; %>
							<c:forEach items="${lookbookwrapper.lookbooklist}" var="lookbook">
										<% 
											if(i==3 || i==8) {
										%>
										<div class="row">
							                <div class="col-md-12 post">
													<div class="row">
											            <div class="col-md-12">
												            <div>
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
										<% 
											}
										%>
							
										<div class="row">
							                <div class="col-md-12 post">
							                    <div class="row">
							                        <div class="col-lg-12">
							                            <h4 class="headline"><span>${lookbook.lookbookname}</span></h4>
														<div class="row">
															<div class="col-xs-2 col-md-1">
									                            <p><a href="/u/${lookbook.usrinfo.account_username}"><img src="${lookbook.usrinfo.uavatar}" class="img-responsive image-circle" alt=""></a></p>
															</div>
															<div class="col-xs-10 col-md-11">
																<p>Posted By <a href="/u/${lookbook.usrinfo.account_username}">${lookbook.usrinfo.account_username}</a>, from <b>${lookbook.usrinfo.location}</b>
																 | <small class="text-right"><b>${lookbook.published_dttm_str}</b></small>
																</p>
															</div>
														</div>
							                        </div>
							                    </div>
							
							                    <div class="row">
							                    
							                        <div class="col-md-5">
														<div class="thumbnail card">
															<div>
																<p><a href="/u/${lookbook.usrinfo.account_username}/lookbook/view/${lookbook.lookbookid}/${lookbook.lookbook_seo_url}"><img src="${lookbook.gcs_photo_url1}" class="img-responsive"></a></p>
																<p> ${lookbook.total_comments} <span class="fa fa-comment text-blue"></span>  
																	${lookbook.total_likes} <span class="fa fa-heart text-red"></span></p>
															</div>
														</div>
							                        </div><!-- //col-md-5 -->
							                        <div class="col-md-7">
							                            <p>${lookbook.lookbookstory} </p>						
							                            <p><span class="text-right"><a class="btn btn-red" href="/u/${lookbook.usrinfo.account_username}/lookbook/view/${lookbook.lookbookid}/${lookbook.lookbook_seo_url}">Read more</a></span></p>
														<p>&nbsp;</p>
														<p>
															<div id="icons">
																<h4 class="headline"><span>Love & Share</span></h4>
																<ul>
																	<li><a href="http://www.facebook.com/sharer.php?u=http://www.glamfiesta.com/u/${lookbook.usrinfo.account_username}/lookbook/view/${lookbook.lookbookid}/${lookbook.lookbook_seo_url}" target="_blank"><i class="fa fa-facebook"></i></a></li>
																	<li><a href="http://twitter.com/?status=http://www.glamfiesta.com/u/${lookbook.usrinfo.account_username}/lookbook/view/${lookbook.lookbookid}/${lookbook.lookbook_seo_url}" target="_blank"><i class="fa fa-twitter"></i></a></li>
																	<li><a href="https://plus.google.com/share?url=http://www.glamfiesta.com/u/${lookbook.usrinfo.account_username}/lookbook/view/${lookbook.lookbookid}/${lookbook.lookbook_seo_url}" target="_blank"><i class="fa fa-google-plus"></i></a></li>
																	<li><a href="http://www.linkedin.com/shareArticle?mini=true&url=http://www.glamfiesta.com/u/${lookbook.usrinfo.account_username}/lookbook/view/${lookbook.lookbookid}/${lookbook.lookbook_seo_url}" target="_blank"><i class="fa fa-linkedin"></i></a></li>
																</ul>
															</div>
														</p>
							                        </div><!-- //col-md-7 -->
				
							                    </div>
							                </div>
							            </div><!-- // row -->
							            <% i++; %>
							</c:forEach>
							<div class="row">
				                <div class="col-md-3">
				                </div>
				                <div class="col-md-6">&nbsp;</div>
				                <div class="col-md-3">
				                	<c:if test="${not empty lookbookwrapper.next_page}"><a href="/?next_page=${lookbookwrapper.next_page}">Load Next Page &gt;</a></c:if>
				                </div>
				            </div>
						</c:if>
						<c:if test="${empty lookbookwrapper.lookbooklist }"> <b>${usrmssg }</b> </c:if>
					</c:if>
					
            	</div>
		        <div class="col-md-4">
					
					<div class="row">
			            <div class="col-md-12">
					        	<h4 class="headline"><span>Top Leaders...</span></h4>
								    <div class="row">     
									  <c:if test="${not empty leaders }">
									        <ul class="widget-fashionistas">
													<c:forEach items="${leaders}" var="leader">
								                    	<li>
															 <div class="row">
										                            <div class="col-xs-3">
											                            <a href="/u/${leader.account_username }">
																			 <span class="img">
																			 	<img class="img-responsive img-thumbnail" src="${leader.uavatar_thumb_profile }" alt="${leader.account_username }">
																			 </span>
																		</a>
																	</div>
																	<div class="col-xs-9">
																		<span class="fashionista clearfix">
                     														<span class="name">
                     															<a href="/u/${leader.account_username }"><strong>${leader.account_username }</strong></a>
																			 	<p><small><i class="fa fa-map-marker"></i> ${leader.location }</small></p>
																			 	<p><a href="/u/${leader.account_username }" class="btn btn-xs btn-primary">View Profile</a></p>                     															
                     														</span>
																		</span>
																	</div>
															 </div>
														</li>        
													</c:forEach>
											</ul>
											<a href="/fashionistas/topleaders" class="btn btn-blue">See All</a>
										</c:if>	
								    </div>
					    </div>
					</div> <!-- //row -->					
					<div class="row">
			            <div class="col-md-12">
					        	<h4 class="headline"><span>Looks By Season</span></h4>
					            <p><h4><strong><a href="/search?season=all">All Season</a></strong></h4></p>
					            <p><h4><strong><a href="/search?season=summer">Summer</a></strong></h4></p>
					            <p><h4><strong><a href="/search?season=fall">Fall</a></strong></h4></p>
					            <p><h4><strong><a href="/search?season=winter">Winter</a></strong></h4></p>
					            <p><h4><strong><a href="/search?season=spring">Spring</a></strong></h4></p>
					    </div>
					</div>   
					<div class="row">
			            <div class="col-md-12">
				            <div>
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
					
					<div class="row">
			            <div class="col-md-12">
					        	<h4 class="headline"><span>New Rising Star...</span></h4>
								    <div class="row">     
									  <c:if test="${not empty newusers }">
									        <ul class="widget-fashionistas">
													<c:forEach items="${newusers}" var="newuser">
								                    	<li>
															 <div class="row">
										                            <div class="col-xs-3">
											                            <a href="/u/${newuser.account_username }">
																			 <span class="img">
																			 	<img class="img-responsive img-thumbnail" src="${newuser.uavatar_thumb_profile }" alt="${newuser.account_username }">
																			 </span>
																		</a>
																	</div>
																	<div class="col-xs-9">
																		<span class="fashionista clearfix">
                     														<span class="name">
                     															<a href="/u/${newuser.account_username }"><strong>${newuser.account_username }</strong></a>
																			 	<p><small><i class="fa fa-map-marker"></i> ${newuser.location }</small></p>
																			 	<p><a href="/u/${newuser.account_username }" class="btn btn-xs btn-primary">View Profile</a></p>                     															
                     														</span>
																		</span>
																	</div>
															 </div>
														</li>        
													</c:forEach>
											</ul>
											<a href="/fashionistas/risingstars" class="btn btn-red">See All</a>
										</c:if>	
								    </div>
					    </div>
					</div> <!-- //row -->					

					         
		        </div><!-- // col-md-3 -->
		        
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
				Copyright 2014-2015 by <a href="">GlamFiesta</a> | All Rights Reserved | Social: <a href="https://plus.google.com/103308390077136813867" rel="publisher"><i class="fa fa-1x fa-google-plus"></i></a>
			</div>
        </div>
      </div>
    </footer>



<!-- JavaScript -->
<!-- JavaScript -->
<script src="/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script>
	$(".spinner").hide();
</script>
<%
/*
	var recentcmmntapp = angular.module('RecentCmmntApp', []);
	
	recentcmmntapp.controller('RecentCmmntController', ['$scope', '$http', function ($scope, $http)  {
		// set the default values of the comment form input fields
		$scope.cmmnt = {
				comment_txt: ""
		};
		$scope.commentslist = [];
		
		// refresh the comments list on first load
		$http({
				method: 'GET',
				url: '/comments/recent/',
		        headers : { 'Accept': 'application/json', 'Content-Type': 'application/json' }
			})
			.success(function (results, status, headers, config) {
				//console.log(JSON.stringify(results))
				$(".spinner-cmmnt").hide();
				$scope.commentslist = results;
			})
			.error(function(err){"ERR", console.log(err)});
		
	}]);
*/
	 %>

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