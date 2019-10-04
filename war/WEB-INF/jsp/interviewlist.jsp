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
								<h1>GlamFiesta - Spotlight interviews with members</h1>
							</div>
						</div>

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

								<div class="row">
									<hr />
								</div>
								<div class="row">
									<c:if test="${not empty intervwlist }">
										<ul class="widget-fashionistas">
											<c:forEach items="${intervwlist}" var="interview">
												<li>
													<div class="row">
														<div class="col-xs-2">
															<a href="/interviews/${interview.intrvw_seo_title}">
																<span class="img"> <img
																	class="img-responsive img-thumbnail"
																	src="${interview.fshnphoto1}"
																	alt="${interview.usernm }">
															</span>
															</a>
														</div>
														<div class="col-xs-6">
															<span class="fashionista clearfix name"> 
																<strong>${interview.intrvwtitle }</strong>
															</span>
														</div>
														<div class="col-xs-4">
															<a href="/interviews/${interview.intrvw_seo_title}"
																class="btn btn-xs btn-primary">View Interview</a>
														</div>
													</div>
												</li>
											</c:forEach>
										</ul>
									</c:if>
								</div>
								<!-- //row -->



							</div>
							<!-- //col-md-8 -->
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-12">
										<a href="/spotlight"><img
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
										<h3 class="headline">
											<span>Popular Lookbook</span>
										</h3>
										<c:forEach items="${randomlooks}" var="randomlook">
											<div class="row">
												<div class="col-sm-6 col-xs-6 col-md-12 col-lg-12">
													<div class="panel-thumbnail">
														<a
															href="/fashion/${randomlook.lookbook_seo_url}">
															<img src="${randomlook.gcs_photo_url1}"
															class="img-responsive" onContextMenu="return false;"
															style="width: 300px; height: auto; margin-top: 10px;">
														</a>
													</div>
												</div>
											</div>
										</c:forEach>
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