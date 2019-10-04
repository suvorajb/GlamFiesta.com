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
<title>GlamFiesta | Dashboard manage comments...</title>
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
										<h3>Update Your Profile Information</h3>
									</div>
									<div class="col-sm-6">
										<ol class="breadcrumb">
											<li><a href="/dashboard"><b><c:out
															value="${usrbo.account_username }" />'s Dashboard</b></a></li>
											<li class="active">Update Profile</li>
										</ol>
									</div>
								</div>
							</div>
						</div>

						<!-- Nav tabs -->
						<ul class="nav nav-tabs nav-justified ">
							<li class="active"><a href="#comments_by_me"
								data-toggle="tab"><strong>Manage Comments</strong></a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">

							<div class="tab-pane active" id="comments_by_me">
								<div class="row">
									<div class="col-sm-12">

										<div class="panel panel-default widget">
											<div class="panel-heading">
												<span class="fa fa-comment"></span>
												<h3 class="panel-title">My Comments</h3>
											</div>
											<div class="panel-body">
												<c:if test="${not empty comments }">
													<ul class="list-group">
														<c:forEach items="${comments}" var="comment">
															<li class="list-group-item">
																<div class="row">
																	<div class="col-xs-8 col-md-8">
																		<div class="mic-info">Posted on
																			${comment.commentdt}</div>
																		<div class="comment-text">
																			<strong> ${comment.commenttxt}</strong>
																		</div>
																	</div>
																	<div class="col-xs-2 col-md-2">
																		<a href="" class="btn btn-xs btn-danger"><i
																			class="fa fa-minus"></i>&nbsp;<b>Delete</b></a>
																	</div>
																</div>
															</li>
														</c:forEach>
													</ul>
												</c:if>
												<c:if test="${empty comments }">
													<h4>You have not posted any comments yet...</h4>
												</c:if>
											</div>
										</div>
										<!-- //panel -->

									</div>
									<!-- //col-sm-12 -->
								</div>
								<!-- //row -->

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
							</div>
							<!-- // tabe-pane profile update -->

						</div>
						<!-- tab content -->

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