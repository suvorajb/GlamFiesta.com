<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta | Terms of Service</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="glamfiesta terms of service">

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
						<li class="selectd"><a href="/aboutus.htm"><span
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
								<p class="lead">Privacy Policy of www.glamfiesta.com</p>
							</div>
						</div>

						<div class="row">
							<div class="col-md-9">
								<div class="panel panel-default">
									<div class="panel-body">


										<h2>Web Site Terms and Conditions of Use</h2>

										<h3>1. Terms</h3>

										<p>By accessing this web site, you are agreeing to be
											bound by these web site Terms and Conditions of Use, all
											applicable laws and regulations, and agree that you are
											responsible for compliance with any applicable local laws. If
											you do not agree with any of these terms, you are prohibited
											from using or accessing this site. The materials contained in
											this web site are protected by applicable copyright and trade
											mark law.</p>

										<h3>2. Use License</h3>

										<ol type="a">
											<li>Permission is granted to temporarily download one
												copy of the materials (information or software) on Glam
												Fiesta's web site for personal, non-commercial transitory
												viewing only. This is the grant of a license, not a transfer
												of title, and under this license you may not:

												<ol type="i">
													<li>modify or copy the materials;</li>
													<li>use the materials for any commercial purpose, or
														for any public display (commercial or non-commercial);</li>
													<li>attempt to decompile or reverse engineer any
														software contained on Glam Fiesta's web site;</li>
													<li>remove any copyright or other proprietary
														notations from the materials; or</li>
													<li>transfer the materials to another person or
														"mirror" the materials on any other server.</li>
												</ol>
											</li>
											<li>This license shall automatically terminate if you
												violate any of these restrictions and may be terminated by
												Glam Fiesta at any time. Upon terminating your viewing of
												these materials or upon the termination of this license, you
												must destroy any downloaded materials in your possession
												whether in electronic or printed format.</li>
										</ol>

										<h3>3. Disclaimer</h3>

										<ol type="a">
											<li>The materials on Glam Fiesta's web site are provided
												"as is". Glam Fiesta makes no warranties, expressed or
												implied, and hereby disclaims and negates all other
												warranties, including without limitation, implied warranties
												or conditions of merchantability, fitness for a particular
												purpose, or non-infringement of intellectual property or
												other violation of rights. Further, Glam Fiesta does not
												warrant or make any representations concerning the accuracy,
												likely results, or reliability of the use of the materials
												on its Internet web site or otherwise relating to such
												materials or on any sites linked to this site.</li>
										</ol>

										<h3>4. Limitations</h3>

										<p>In no event shall Glam Fiesta or its suppliers be
											liable for any damages (including, without limitation,
											damages for loss of data or profit, or due to business
											interruption,) arising out of the use or inability to use the
											materials on Glam Fiesta's Internet site, even if Glam Fiesta
											or a Glam Fiesta authorized representative has been notified
											orally or in writing of the possibility of such damage.
											Because some jurisdictions do not allow limitations on
											implied warranties, or limitations of liability for
											consequential or incidental damages, these limitations may
											not apply to you.</p>

										<h3>5. Revisions and Errata</h3>

										<p>The materials appearing on Glam Fiesta's web site could
											include technical, typographical, or photographic errors.
											Glam Fiesta does not warrant that any of the materials on its
											web site are accurate, complete, or current. Glam Fiesta may
											make changes to the materials contained on its web site at
											any time without notice. Glam Fiesta does not, however, make
											any commitment to update the materials.</p>

										<h3>6. Links</h3>

										<p>Glam Fiesta has not reviewed all of the sites linked to
											its Internet web site and is not responsible for the contents
											of any such linked site. The inclusion of any link does not
											imply endorsement by Glam Fiesta of the site. Use of any such
											linked web site is at the user's own risk.</p>

										<h3>7. Site Terms of Use Modifications</h3>

										<p>Glam Fiesta may revise these terms of use for its web
											site at any time without notice. By using this web site you
											are agreeing to be bound by the then current version of these
											Terms and Conditions of Use.</p>

										<h3>8. Governing Law</h3>

										<p>Any claim relating to Glam Fiesta's web site shall be
											governed by the laws of the State of Kolkata, West Bengal
											without regard to its conflict of law provisions.</p>

										<p>General Terms and Conditions applicable to Use of a Web
											Site.</p>



									</div>
								</div>
							</div>
							<div class="col-md-3">
								<h3 class="headline">
									<span>Advertisements...</span>
								</h3>
								<script async
									src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
								<!-- static_pg_glamfiesta_rect1 -->
								<ins class="adsbygoogle"
									style="display: inline-block; width: 300px; height: 250px"
									data-ad-client="ca-pub-9745887657365691"
									data-ad-slot="7625793253"></ins>
								<script>
									(adsbygoogle = window.adsbygoogle || [])
											.push({});
								</script>
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