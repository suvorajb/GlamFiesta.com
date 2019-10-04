<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta | Search Results</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="search results page">

<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
</head>

<body>
	<div class="spinner">
		<img id="img-spinner" src="/resources/img/spinner.gif"
			alt="Loading Comments..." />
	</div>
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
								<h1>GlamFiesta - Get Noticed. Inspire Others.</h1>
								<p class="lead">Search Results</p>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<blockquote class="blockquote-reverse">
									<h4>
										Displaying results of your search work <b>" ${searchterm}
											"</b>
									</h4>
								</blockquote>
							</div>
						</div>

						<div class="row">
							<div class="col-md-9">
								<!--  // main content here -->

								<c:if test="${not empty looks }">
									<%
										int i = 0;
									%>
									<c:forEach items="${looks}" var="lookbook">
										<%
											if (i == 3 || i == 8) {
										%>
										<div class="row">
											<div class="col-md-12">
												<script async
													src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
												<!-- gf-respnsv -->
												<ins class="adsbygoogle" style="display: block"
													data-ad-client="ca-pub-9745887657365691"
													data-ad-slot="8920725253" data-ad-format="auto"></ins>
												<script>
													(adsbygoogle = window.adsbygoogle
															|| []).push({});
												</script>
											</div>
										</div>
										<%
											}
										%>

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
																			target="_blank"><i class="fa fa-facebook"></i></a></li>
																		<li><a
																			href="http://twitter.com/?status=http://www.glamfiesta.com/fashion/${lookbook.lookbook_seo_url}"
																			target="_blank"><i class="fa fa-twitter"></i></a></li>
																		<li><a
																			href="https://plus.google.com/share?url=http://www.glamfiesta.com/fashion/${lookbook.lookbook_seo_url}"
																			target="_blank"><i class="fa fa-google-plus"></i></a></li>
																		<li><a
																			href="http://www.linkedin.com/shareArticle?mini=true&url=http://www.glamfiesta.com/fashion/${lookbook.lookbook_seo_url}"
																			target="_blank"><i class="fa fa-linkedin"></i></a></li>
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
										<%
											i++;
										%>
									</c:forEach>
								</c:if>

								<c:if test="${empty looks }">
									<h3>No Looks found matching your search... please try
										again...</h3>
								</c:if>

							</div>
							<!-- // col-md-9 -->
							<div class="col-md-3">
								<div class="row">
									<div class="col-md-12">
										<h3 class="headline">
											<span>Advertisements...</span>
										</h3>
										<p>
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
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<h3 class="headline">
											<span>Popular Lookbook</span>
										</h3>
										<c:forEach items="${randomlooks}" var="randomlook">
											<div class="row">
												<div class="col-sm-6 col-xs-6 col-md-12 col-lg-12">
													<div class="panel-thumbnail">
														<a
															href="/fashion/${lookbook.lookbook_seo_url}">
															<img src="${randomlook.gcs_photo_url1}"
															class="img-responsive" onContextMenu="return false;"
															style="width: 350px; height: auto; margin-top: 10px;">
														</a>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>

							</div>
							<!-- // col-md-3 -->

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
	<script>
		$(".spinner").hide();
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