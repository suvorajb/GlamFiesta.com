<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta - Fashion Social Network for trendy street
	style</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="fashion, street style, street fashion, lookbook, chic, women fashion, women style, fashion network, youtube fashionistas">
<meta name="description"
	content="Unique fashion community where bloggers & vloggers connect, share & promote their lookbook, makeup ideas, fashion inspiration, street style & clothing hauls">
<meta name="p:domain_verify" content="58d85eda460a4d5629f8e7d8d7896436" />

<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/widget.css" rel="stylesheet">
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
						<img src="/resources/img/logo_v2_1.png" alt="GlamFiesta Logo"
							class="img-responsive center" />
					</div>
					<!--/logo-->
					<ul class="nav nav-pills nav-stacked menu">
						<li>&nbsp;</li>
						<li class="selectd"><a href="/"><span class="fa fa-leaf"></span>&nbsp;Fresh
								Looks</a></li>
						<li><a href="/toplooks"><span class="fa fa-thumbs-o-up"></span>&nbsp;Top
								Looks</a></li>
						<li><a href="/fashionistas"><span class="fa fa-users"></span>&nbsp;Fashionistas</a></li>
						<c:if test="${ul.loginstatus == true }">
							<li><a href="/dashboard"><span class="fa fa-tachometer"></span>&nbsp;Dashboard</a></li>
							<li><a href="/logout"><span class="fa fa-sign-in"></span>&nbsp;Sign
									Out</a></li>
						</c:if>
						<c:if test="${ul.loginstatus !=true }">
							<li><a href="/login"><span class="fa fa-sign-in"></span>&nbsp;Sign
									In</a></li>
							<li><a href="/signup"><span class="fa fa-user"></span>&nbsp;Sign
									Up</a></li>
						</c:if>
						<li><a href="/aboutus.htm"><span
								class="fa fa-chevron-circle-right"></span>&nbsp;About Us</a></li>
						<li><a href="/contactus.htm"><span class="fa fa-envelope"></span>&nbsp;Contact
								Us</a></li>
						<li><a href="/how-to-post-look.htm"><span
								class="fa fa-question-circle"></span>&nbsp;How 2 Post</a></li>
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
						<a href="/search?season=All+Season" target="_blank"> <img
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
								<h1>GlamFiesta - Get Noticed. Inspire Others.</h1>
								<p class="lead">Fashion Social Network for trendy street
									style</p>
								<img style="display: none;" id="img-spinner"
									src="/resources/img/spinner.gif" alt="Loading Lookbook..." />
							</div>
						</div>
						<!-- div class="row">
							<div class="col-md-8">
								
								<div class="panel panel-default">
									<div class="panel-body">
										<h4>
											<c:forEach items="${globaltags }" var="globaltag">
												<c:if test="${globaltag != currtag }">
													<a href="/toplooks?tag=${globaltag}"><b>#${globaltag}</b></a>&nbsp;&nbsp;
												</c:if>
												<c:if test="${globaltag == currtag }">
													<b>#${globaltag}</b>&nbsp;&nbsp;
												</c:if>
											</c:forEach>
										</h4>
									</div>
								</div>
							</div> 
							
						</div -->

						<div class="row">
							<div class="col-md-8">
								<!--  // main content here -->
								<script async
									src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
								<!-- gf-respnsv -->
								<ins class="adsbygoogle" style="display: block"
									data-ad-client="ca-pub-9745887657365691"
									data-ad-slot="8920725253" data-ad-format="auto"></ins>
								<script>
									(adsbygoogle = window.adsbygoogle || [])
											.push({});
								</script>
								<c:if test="${not empty lookbookwrapper }">
									<c:if test="${not empty lookbookwrapper.lookbooklist }">
										<c:forEach items="${lookbookwrapper.lookbooklist}"
											var="lookbook">
											<div class="row">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="panel-body">
															<div class="row">
																<div class="col-lg-12">
																	<h4 class="headline">
																		<b>${lookbook.lookbookname}</b>
																	</h4>
																	<div class="row">
																		<div class="col-xs-2 col-md-2">
																			<div class="panel-thumbnail">
																				<a href="/fashionistas/${lookbook.usrinfo.account_username}"
																					target="_blank"> <img
																					class="img-responsive img-thumbnail"
																					src="${lookbook.usrinfo.uavatar}"
																					alt="${lookbook.usrinfo.account_username }"
																					onContextMenu="return false;"
																					style="width: auto; height: 65px; margin-top: 0px;">
																				</a>
																			</div>
																		</div>
																		<div class="col-xs-10 col-md-10">
																			<p>
																				<a href="/fashionistas/${lookbook.usrinfo.account_username}"
																					target="_blank">${lookbook.usrinfo.account_username}</a>,
																				from <b>${lookbook.usrinfo.location}</b>
																				<%
																					/*| <small class="text-right"><b>${lookbook.published_dttm_str}</b></small>*/
																				%>
																			</p>
																		</div>
																	</div>
																</div>
															</div>

															<div class="row">

																<div class="col-md-5">
																	<div class="thumbnail card">
																		<div>
																			<p>
																				<a
																					href="/fashion/${lookbook.lookbook_seo_url}"
																					target="_blank"><img
																					src="${lookbook.gcs_photo_url1}"
																					class="img-responsive"></a>
																			</p>

																		</div>
																	</div>
																</div>
																<!-- //col-md-5 -->
																<div class="col-md-7">
																	<p>${lookbook.lookbookstory}</p>
																	<p>
																		<span class="text-right"><a class="btn btn-red"
																			href="/fashion/${lookbook.lookbook_seo_url}"
																			target="_blank"> View Lookbook</a></span>
																	</p>
																	<p>&nbsp;</p>
																	<p>
																	<div id="icons">
																		<h4 class="headline">
																			<span>Love & Share</span>
																		</h4>
																		<ul>
																			<li><a
																				href="http://www.facebook.com/sharer.php?u=http://www.glamfiesta.com/fashion/${lookbook.lookbook_seo_url}"
																				target="_blank" rel="nofollow"><i
																					class="fa fa-facebook"></i></a></li>
																			<li><a
																				href="http://twitter.com/?status=http://www.glamfiesta.com/fashion/${lookbook.lookbook_seo_url}"
																				target="_blank" rel="nofollow"><i
																					class="fa fa-twitter"></i></a></li>
																			<li><a
																				href="https://plus.google.com/share?url=http://www.glamfiesta.com/fashion/${lookbook.lookbook_seo_url}"
																				target="_blank" rel="nofollow"><i
																					class="fa fa-google-plus"></i></a></li>
																			<li><a
																				href="http://www.linkedin.com/shareArticle?mini=true&url=http://www.glamfiesta.com/fashion/${lookbook.lookbook_seo_url}"
																				target="_blank" rel="nofollow"><i
																					class="fa fa-linkedin"></i></a></li>
																		</ul>
																	</div>
																	</p>
																</div>
																<!-- //col-md-7 -->
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- // row -->

										</c:forEach>
										<div class="row">
											<div class="col-md-3"></div>
											<div class="col-md-6">&nbsp;</div>
											<div class="col-md-3">
												<c:if test="${not empty lookbookwrapper.next_page}">
													<a href="#" rel="nofollow"
														onclick="javascript: $('#img-spinner').show();location.href='/?next_page=${lookbookwrapper.next_page}'"><b>Load
															Next Page &gt;</b></a>
												</c:if>
											</div>
										</div>
									</c:if>
									<c:if test="${empty lookbookwrapper.lookbooklist }">
										<b>${usrmssg }</b>
									</c:if>
								</c:if>

							</div>
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-12">
										<a href="/interviews"><img
											src="/resources/img/spotlight.jpg"
											style="height: 250px; width: 300px; padding-bottom: 10px;"
											class="img-responsive" /></a>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
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
								</div>

								<div class="row">
									<div class="col-md-12">
										<h4 class="headline">
											<span>Top Fashionista...</span>
										</h4>
										<div class="row">
											<c:if test="${not empty leaders }">
												<ul class="widget-fashionistas">
													<c:forEach items="${leaders}" var="leader">
														<li>
															<div class="row">
																<div class="col-xs-3">
																	<a href="/fashionistas/${leader.account_username }"> <span
																		class="img"> <img
																			class="img-responsive img-thumbnail"
																			src="${leader.uavatar_thumb_profile }"
																			alt="${leader.account_username }">
																	</span>
																	</a>
																</div>
																<div class="col-xs-9">
																	<span class="fashionista clearfix"> <span
																		class="name"> <a
																			href="/fashionistas/${leader.account_username }"><strong>${leader.account_username }</strong></a>
																			<p>
																				<small><i class="fa fa-map-marker"></i>
																					${leader.location }</small>
																			</p> <!-- p><a href="/u/${newuser.account_username }" class="btn btn-xs btn-primary">View Profile</a></p-->
																	</span>
																	</span>
																</div>
															</div>
														</li>
													</c:forEach>
												</ul>
												<a href="/fashionistas/topleaders" class="btn btn-blue">See
													All</a>
											</c:if>
										</div>
									</div>
								</div>
								<!-- //row -->

								<div class="row">
									<div class="col-md-12">
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
									<div class="col-md-12">
										<h4 class="headline">
											<span>New Fashionista</span>
										</h4>
										<div class="row">
											<c:if test="${not empty newusers }">
												<ul class="widget-fashionistas">
													<c:forEach items="${newusers}" var="newuser">
														<li>
															<div class="row">
																<div class="col-xs-3">
																	<a href="/fashionistas/${newuser.account_username }"> <span
																		class="img"> <img
																			class="img-responsive img-thumbnail"
																			src="${newuser.uavatar_thumb_profile }"
																			alt="${newuser.account_username }">
																	</span>
																	</a>
																</div>
																<div class="col-xs-9">
																	<span class="fashionista clearfix"> <span
																		class="name"> <a
																			href="/fashionistas/${newuser.account_username }"><strong>${newuser.account_username }</strong></a>
																			<p>
																				<small><i class="fa fa-map-marker"></i>
																					${newuser.location }</small>
																			</p> <!-- p><a href="/u/${newuser.account_username }" class="btn btn-xs btn-primary">View Profile</a></p-->
																	</span>
																	</span>
																</div>
															</div>
														</li>
													</c:forEach>
												</ul>
												<a href="/fashionistas/risingstars" class="btn btn-blue">See
													All</a>
											</c:if>
										</div>
									</div>
								</div>
								<!-- //row -->


							</div>

						</div>

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
					Copyright 2015-2016 by <a href="">GlamFiesta</a> | All Rights
					Reserved | <a href="/privacy_policy.htm">Privacy Policy</a> | <a
						href="/tos.htm">Terms of Service</a> | <a href="/sitemap.htm">Sitemap</a>
				</div>
			</div>
		</div>
	</footer>



	<!-- JavaScript -->

	<script src="/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>

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
	<script src="https://apis.google.com/js/platform.js" async defer></script>

</body>
</html>