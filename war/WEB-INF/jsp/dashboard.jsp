<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>GlamFiesta | Dashboard to post new looks & connect with
	others</title>
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
							<li class="selectd"><a href="/dashboard"><span
									class="fa fa-tachometer"></span>&nbsp;Dashboard</a></li>
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

						<!--  // main content here -->
						<!-- dashboard main content page -->

						<!-- Topic Header -->
						<div class="topic">
							<div class="container">
								<div class="row">
									<div class="col-sm-6">
										<h3>
											<i class="fa fa-tachometer fa-3"></i>
											<c:out value="${usrbo.account_username }" />
											's Dashboard
										</h3>
									</div>
									<div class="col-sm-6">
										<h3>
											<a href="/dashboard/updtprofile"
												class="btn btn-sm btn-red text-right">Update Profile</a> <a
												href="/dashboard/friends"
												class="btn btn-sm btn-red text-right">Social Connections</a>
										</h3>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-8">
								<div class="row">
									<div class="col-md-12">
										<h2 class="profile-name">
											Welcome <a href="/fashionistas/${usrbo.account_username }"> <c:out
													value="${usrbo.account_username }" />
											</a> !!
										</h2>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="profile-header">
											<div class="col-md-6">
												<img src="${usrbo.uavatar}" class="thumbnail img-responsive"
													style="width: auto; height: 125px;" />
											</div>
											<div class="col-md-6">
												<a href="/dashboard/updtprofile" class="text-right"><b>Change
														Profile Image</b></a>
												<div class="profile-location">
													<i class="fa fa-2x fa-map-marker"></i>
													<c:out value="${usrbo.citynm }" />
												</div>
											</div>
										</div>
										<!-- profile-header -->
									</div>
									<div class="col-md-6">
										<div class="row">
											<div class="col-md-12">
												<p>
													<a href="/dashboard/mylookbooks"
														class="btn btn-xs btn-red text-right">My Lookbook</a>
												</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<p>
													<a href="/dashboard/mypoints"
														class="btn btn-xs btn-blue text-right">My Points
														History</a>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- // col-md-10 -->
							<div class="col-md-4">
								<div class="panel panel-default">
									<div class="panel-body">
										<h4 class="headline">
											<b>People You May Like</b>
										</h4>
										<c:if test="${not empty rndmfashionistas }">
											<c:forEach items="${rndmfashionistas}" var="fashionista">
												<div class="col-sm-4">
													<a href="/fashionistas/${fashionista.account_username }"> <span
														class="img"> <img class="img-responsive"
															src="${fashionista.uavatar_thumb_profile }"
															alt="${fashionista.account_username }"
															style="width: 65px; height: 65px; margin-bottom: 2px;">
													</span>
													</a>
												</div>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<!-- // row -->
						<c:if test="${not empty succsmssg }">
							<div class="alert alert-success alert-dismissible" role="alert">
								<button type="button" class="close" data-dismiss="alert">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<c:out value="${succsmssg}" />
							</div>
						</c:if>

						<!-- Nav tabs -->
						<ul class="nav nav-tabs nav-justified ">
							<li class="active"><a href="#quickmenu" data-toggle="tab"><strong>Quick
										Menu</strong></a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane active" id="quickmenu">
								<h4 class="subtitle">Select a quick action from the menu</h4>
								<div class="row">
									<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
										<div class="panel panel-default card">
											<div class="panel-body">
												<h4>Wearing a New dress?</h4>
												<p align="middle">
													<img src="/resources/img/dress-icon.png"
														style="width: auto; height: 75px;" />
												</p>
												<p>
													<a href="/dashboard/lookbook/postnew"
														class="btn btn-block btn-primary">Post New Look Photo</a>
												</p>
											</div>
										</div>
									</div>
									<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
										<div class="panel panel-default card">
											<div class="panel-body">
												<h4>Change Profile Picture</h4>
												<p align="middle">
													<i class="fa fa-4x fa-user"></i>
												</p>
												<p>
													<a href="/dashboard/updtprofile#avatar"
														class="btn btn-block btn-primary">Change Profile</a>
												</p>
											</div>
										</div>
									</div>
									<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
										<div class="panel panel-default card">
											<div class="panel-body">
												<h4>Manage User Comments</h4>
												<p align="middle">
													<i class="fa fa-4x fa-comments"></i>
												</p>
												<p>
													<a href="/dashboard/comments/manage"
														class="btn btn-block btn-primary">Manage Comments</a>
												</p>
											</div>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
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
									<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
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
								<!-- // row -->
							</div>
							<!-- // tab-pane -->

						</div>
						<!-- tab-content -->

						<!-- // dashboard main content page -->
					</div>
					<!--  //col-xs-12 -->
				</div>
				<!-- // row -->

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
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</body>
</html>