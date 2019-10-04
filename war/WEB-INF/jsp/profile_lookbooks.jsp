<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta | ${usrbo.account_username } 's Lookbook Collection</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="glamfiesta, ${usrbo.account_username } profile">
<meta name="description" content="${usrbo.account_username }'s lookbook collection">

<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
</head>

<body>
	<div class="container">

		<div class="row">

			<nav class="col-sm-2">
				<!--nav-->
				<div class="row">
					<div class="col-sm-12">
						<!--logo-->
						<img src="/resources/img/logo_v2_1.png" alt="GlamFiesta"
							class="img-responsive center" />
					</div>
					<!--/logo-->
					<ul class="nav nav-pills nav-stacked menu">
						<li>&nbsp;</li>
						<li><a href="/"><span class="fa fa-leaf"></span>&nbsp;Fresh
								Looks</a>
						</li>
						<li><a href="/toplooks"><span class="fa fa-thumbs-o-up"></span>&nbsp;Top
								Looks</a>
						</li>
						<li><a href="/fashionistas"><span class="fa fa-users"></span>&nbsp;Fashionistas</a>
						</li>
						<c:if test="${ul.loginstatus == true }">
							<li><a href="/dashboard"><span class="fa fa-tachometer"></span>&nbsp;Dashboard</a>
							</li>
							<li><a href="/logout"><span class="fa fa-sign-in"></span>&nbsp;Sign
									Out</a>
							</li>
						</c:if>
						<c:if test="${ul.loginstatus !=true }">
							<li><a href="/login"><span class="fa fa-sign-in"></span>&nbsp;Sign
									In</a>
							</li>
							<li><a href="/signup"><span class="fa fa-user"></span>&nbsp;Sign
									Up</a>
							</li>
						</c:if>
						<li><a href="/aboutus.htm"><span
								class="fa fa-chevron-circle-right"></span>&nbsp;About Us</a>
						</li>
						<li><a href="/contactus.htm"><span class="fa fa-envelope"></span>&nbsp;Contact
								Us</a>
						</li>
						<li><a href="/how-to-post-look.htm"><span
								class="fa fa-question-circle"></span>&nbsp;How 2 Post</a>
						</li>
						<li><a href="/faq.htm"><span class="fa fa-question"></span>&nbsp;FAQ</a>
						</li>
						<li><a href="/giveaway.htm"><span class="fa fa-trophy"></span>&nbsp;Giveaway</a>
						</li>

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
			</nav>
			<!--/nav-->

			<div class="col-sm-10">

				<div class="row">
					<div class="col-xs-12">

						<div class="row">
							<div class="col-md-12">
								<!--  // main content here -->

								<div class="row">
									<div class="col-lg-12 col-md-12 col-xs-12">
										<div class="well panel panel-default">
											<div class="panel-body">
												<div class="row">

													<div class="col-xs-12 col-sm-2 text-center">
														<img src="${usrbo.uavatar}" alt=""
															class="center-block img-thumbnail img-responsive"
															style="width: 120px;">
													</div>
													<!--/col-->

													<div class="col-xs-12 col-sm-10">
														<h2>
															${usrbo.account_username }
															<button type="button" class="btn btn-sm btn-red" href="#"
																id="usr_follow_actn">
																<i class="fa fa-arrow-right"></i> Follow
															</button>
														</h2>
														<h4>
															<i class="fa fa-1x fa-map-marker"></i>
															<c:out value="${usrbo.citynm }" />
														</h4>
														<h4>
															<b>Abount Me: </b> ${usrbo.aboutme}...
														</h4>
														<h4>
															<b>Connect ${usrbo.account_username }: </b> <small>
																<c:if test="${not empty usrbo.twitter_ac}">
																	<a href="${usrbo.twitter_ac}" target="_blank" rel="nofollow"><i
																		class="fa fa-2x fa-twitter"></i>
																	</a> | </c:if> <c:if test="${not empty usrbo.facebook_ac}">
																	<a href="${usrbo.facebook_ac}" target="_blank" rel="nofollow"><i
																		class="fa fa-2x fa-facebook"></i>
																	</a> | </c:if> <c:if test="${not empty usrbo.youtube_ac}">
																	<a href="${usrbo.youtube_ac}" target="_blank"><i
																		class="fa fa-2x fa-youtube"></i>
																	</a> | </c:if> <c:if test="${not empty usrbo.linkedin_ac}">
																	<a href="${usrbo.linkedin_ac}" target="_blank" rel="nofollow"><i
																		class="fa fa-2x fa-linkedin"></i>
																	</a> | </c:if> <c:if test="${not empty usrbo.pinterest_ac}">
																	<a href="${usrbo.pinterest_ac}" target="_blank" rel="nofollow"><i
																		class="fa fa-2x fa-pinterest"></i>
																	</a> | </c:if> <c:if test="${not empty usrbo.instagram_ac}">
																	<a href="${usrbo.instagram_ac}" target="_blank" rel="nofollow"><i
																		class="fa fa-2x fa-instagram"></i>
																	</a> | </c:if> <c:if test="${not empty usrbo.tumblr_ac}">
																	<a href="${usrbo.tumblr_ac}" target="_blank" rel="nofollow"><i
																		class="fa fa-2x fa-tumblr"></i>
																	</a> | </c:if> <c:if test="${not empty usrbo.website_blog_url}">
																	<a href="${usrbo.website_blog_url}" target="_blank"><i
																		class="fa fa-2x fa-wordpress"></i>
																	</a>
																</c:if> </small>
														</h4>
														<input type="hidden" value="${usrbo.account_username}"
															id="uname" /> <input type="hidden"
															value="${usrbo.userid}" id="uid" />
													</div>
													<!--/col-->

													<div class="clearfix"></div>

													<div class="col-xs-12 col-sm-4 text-center">
														<h4>
															<strong> ${usrbo.total_followers } </strong>
														</h4>
														<h4>CONNECTIONS</h4>
														<a href="/fashionistas/${usrbo.account_username}/followers"
															class="btn btn-success btn-block"><i
															class="fa fa-user"></i> Connections</a>
													</div>
													<!--/col-->
													<div class="col-xs-12 col-sm-4 text-center">
														<h4>
															<strong> ${usrbo.total_glampoints } </strong>
														</h4>
														<h4>GLAMPOINTS</h4>
														<a href="/fashionistas" class="btn btn-info btn-block"><i
															class="fa fa-users"></i> Fashionistas</a>
													</div>
													<!--/col-->
													<div class="col-xs-12 col-sm-4 text-center">
														<h4>
															<strong> ${usrbo.total_lookbooks } </strong>
														</h4>
														<h4>LOOKBOOKS</h4>
														<a href="/fashionistas/${usrbo.account_username}/lookbook"
															class="btn btn-primary btn-block"><i
															class="fa fa-gear"></i> Lookbooks</a>
													</div>
													<!--/col-->
												</div>
												<!--/row-->
											</div>
											<!--/panel-body-->
										</div>
										<!--/panel-->
									</div>
									<!--/col-->
								</div>
								<!--/row-->

								<div class="row">
									<div class="alert alert-danger alert-dismissible" role="alert"
										id="err_box" style="display: none;"></div>
								</div>

								<div class="spinner" style="display: none;">
									<img id="img-spinner" src="/resources/img/spinner.gif"
										alt="Loading..." />
								</div>

								<div class="row">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
										<div class="row">
											<h4 class="headline">
												<span>${usrbo.account_username }'s Lookbook</span>
											</h4>
											<c:if test="${not empty lookbooks }">

												<c:forEach items="${lookbooks}" var="lookbook">

													<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
														<div class="thumbnail card">
															<div>
																<p>
																	<a
																		href="/fashion/${lookbook.lookbook_seo_url}"><img
																		src="${lookbook.gcs_photo_url1 }"
																		class="img-responsive">
																	</a>
																</p>
																<h4>
																	<a
																		href="/fashion/${lookbook.lookbook_seo_url}">${lookbook.lookbookname
																		}</a>
																</h4>
																<p>
																	${lookbook.total_comments } <span
																		class="fa fa-comment text-blue"></span>
																	${lookbook.total_likes } <span
																		class="fa fa-heart text-red"></span>
																</p>
															</div>
														</div>
													</div>

												</c:forEach>
											</c:if>
											<c:if test="${empty lookbooks }">
												<div class="row">
													<div class="col-lg-12">
														<h1>User has not posted any lookbook yet</h1>
													</div>
												</div>
											</c:if>
										</div>

										<c:if test="${not empty visitedlooks }">
											<div class="row">
												<div class="col-sm-12">
													<h4 class="headline">
														<span>Recently Visited Lookbook...</span>
													</h4>
													<div class="row">
														<c:forEach items="${visitedlooks}" var="recentvisit">
															<div class="col-sm-6 col-xs-6 col-md-3 col-lg-3">
																<div class="panel-thumbnail">
																	<a
																		href="/fashion/${recentvisit.lookbook_seo_url}">
																		<img src="${recentvisit.gcs_photo_url1}"
																		class="img-responsive" onContextMenu="return false;"
																		style="width: 177px; height: auto; margin-top: 1px;">
																	</a>
																</div>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</c:if>

										<div class="row" ng-app="UsrCmmntApp"
											ng-controller="UsrCmmntController">
											<div class="col-md-12">
												<h4 class="headline">
													<span><i class="fa fa-1x fa-comment"></i>Recent
														Comments on Posted Lookbook(s)</span>
												</h4>
												<div class="spinner-cmmnt">
													<img id="img-spinner-cmmnt"
														src="/resources/img/spinner.gif" alt="Loading Comments..." />
												</div>
												<div class="row">
													<ul class="list-group" ng-repeat="comment in commentslist">
														<li class="list-group-item">
															<div class="row">
																<div class="col-xs-2 col-md-2">
																	<a href="/fashionistas/{{comment.username}}"><img
																		src="{{comment.uavatar_thumb}}"
																		class="img-responsive image-circle" />
																	</a>
																</div>
																<div class="col-xs-8 col-md-8">
																	<div class="mic-info">
																		<small>By: <a href="/fashionistas/{{comment.username}}">{{comment.username}}</a>
																			on {{comment.commentdtstr}} </small>
																	</div>
																	<div class="comment-text">
																		<small>{{comment.commenttxt}}</small><br /> <a
																			href="{{comment.lookbookurl}}">View</a>
																	</div>

																</div>
															</div></li>
													</ul>
												</div>
											</div>
										</div>
										<!-- //row -->

									</div>
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<h4 class="headline">
													<span>Share Love</span>
												</h4>
												<p>
													<a
														href="http://twitter.com/?status=http://www.glamfiesta.com/fashionistas/${usrbo.account_username}"
														title="Share on Twitter" target="_blank"
														data-toggle="tooltip" data-placement="top"
														class="btn btn-twitter"><i class="fa fa-2x fa-twitter"></i>
													</a> <a
														href="http://www.facebook.com/sharer.php?u=http://www.glamfiesta.com/fashionistas/${usrbo.account_username}"
														title="${usrbo.account_username}" target="_blank"
														data-toggle="tooltip" data-placement="top"
														class="btn btn-facebook"><i
														class="fa fa-2x fa-facebook"></i>
													</a> <a
														href="https://plus.google.com/share?url=http://www.glamfiesta.com/fashionistas/${usrbo.account_username}"
														title="Share on Google+" target="_blank"
														data-toggle="tooltip" data-placement="top"
														class="btn btn-googleplus"><i
														class="fa fa-2x fa-google-plus"></i>
													</a> <a
														href="http://www.pinterest.com/pin/create/button/?url=${usrbo.uavatar_thumb_profile}&media=${usrbo.uavatar_thumb_profile}&description=Fashionista ${usrbo.account_username}"
														title="Share on Pinterest" target="_blank"
														data-toggle="tooltip" data-placement="top"
														class="btn btn-googleplus"><i
														class="fa fa-2x fa-pinterest"></i>
													</a>
												</p>
											</div>
										</div>
										<div class="row">
											<div>
												<script async
													src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
												<!-- static_pg_glamfiesta_rect1 -->
												<ins class="adsbygoogle"
													style="display: inline-block; width: 300px; height: 250px"
													data-ad-client="ca-pub-9745887657365691"
													data-ad-slot="7625793253"></ins>
												<script>
													(adsbygoogle = window.adsbygoogle
															|| []).push({});
												</script>
											</div>
										</div>
										
										<div class="row">
											
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												
												<h4 class="headline">
													<span>People You May Like</span>
												</h4>

												<div class="row">
													<c:if test="${not empty rndmusrs }">
														<ul class="widget-fashionistas">
															<c:forEach items="${rndmusrs}" var="rnduser">
																<li>
																	<div class="row">
																		<div class="col-xs-3">
																			<a href="/fashionistas/${rnduser.account_username }"> <span
																				class="img"> <img
																					class="img-responsive img-thumbnail"
																					src="${rnduser.uavatar_thumb_profile }"
																					alt="${rnduser.account_username }"> </span> </a>
																		</div>
																		<div class="col-xs-9">
																			<span class="fashionista clearfix"> 
																				<span class="name"> <a
																						href="/fashionistas/${rnduser.account_username }"><strong>${rnduser.account_username}</strong>
																					</a>
																						<p>
																							<small><i class="fa fa-map-marker"></i>
																								${rnduser.location }</small>
																						</p>
																				</span> 
																			</span>
																		</div>
																	</div>
																</li>
															</c:forEach>
														</ul>
													</c:if>
												</div><!-- //row -->
												
											</div><!-- //col-lg-12 col-md-12 col-sm-12 col-xs-12 -->
											
										</div><!-- //row -->
										
									</div>
									<!--/col-->

								</div>
								<!--  // row -->
								<!--  // end main content here -->
							</div>
							<!-- //col-md-12 -->

						</div>
						<!-- //row -->

					</div>
				</div>
			</div>
		</div>

	</div>
	<!--/.content-->

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
	<script src="/resources/js/angular.min.js"></script>
	<script>
		$(".spinner").hide();

		var recentcmmntapp = angular.module('UsrCmmntApp', []);

		recentcmmntapp.controller('UsrCmmntController', [ '$scope', '$http',
				function($scope, $http) {
					// set the default values of the comment form input fields
					$scope.cmmnt = {
						uid : "${usrbo.userid}",
						comment_txt : ""
					};
					$scope.commentslist = [];

					// refresh the comments list on first load
					$http({
						method : 'GET',
						url : '/comments/u/list/' + $scope.cmmnt.uid,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						}
					}).success(function(results, status, headers, config) {
						//console.log(JSON.stringify(results))
						$(".spinner-cmmnt").hide();
						$scope.commentslist = results;
					}).error(function(err) {
						"ERR", console.log(err)
					});

				} ]);
	</script>
	<script>
		$(document)
				.ready(
						function() {
							$("#usr_follow_actn")
									.click(
											function() {
												var uid = $("#uid").val();
												//alert("uname-" + uname);
												$(".spinner").show();
												$
														.get(
																"/followers/save?u2fllw="
																		+ uid,
																function(mssg) {
																	//alert(JSON.stringify(data));
																	//alert("status- " + status);
																	$(
																			"usr_follow_actn")
																			.hide();
																	$(
																			"usr_following")
																			.show();
																	$(
																			".spinner")
																			.hide();
																	if (mssg.mssg_type == "SCS") {
																		//$("#tot_likes").html(mssg.total_likes + " Likes");
																	} else {
																		var err_mssg = '<button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>'
																				+ '<h4><strong>Oh Snap!</strong> '
																				+ mssg.mssg
																				+ '</h4>';
																		$(
																				"#err_box")
																				.html(
																						err_mssg);
																		$(
																				"#err_box")
																				.show();
																	}
																});
											});
						});
	</script>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-60905111-1', 'auto');
		ga('send', 'pageview');
	</script>
</body>
</html>