<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>GlamFiesta | ${lookbook.lookbookname}</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="${lookbook.lookbookname}">

<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/jasny-bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
</head>

<body>

	<div class="navmenu navmenu-default navmenu-fixed-left offcanvas">
		<a class="navmenu-brand" href="#"><img
			src="/resources/img/logo_v2_1.png" alt="GlamFiesta"
			class="img-responsive center" /></a>
		<ul class="nav navmenu-nav">
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

	<div class="navbar navbar-default navbar-fixed-top">
		<button type="button" class="navbar-toggle" data-toggle="offcanvas"
			data-target=".navmenu" data-canvas="body">
			Click For Menu <span class="icon-bar"></span> <span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
	</div>

	<div class="container">

		<div class="row">
			<div class="col-xs-12">

				<div class="row">
					<div class="col-md-12">
						<!--  // main content here -->

						<div class="alert alert-danger alert-dismissible" role="alert"
							id="lookbook_like_err" style="display: none;">
							<button type="button" class="close" data-dismiss="alert">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4>
								<strong>Oh Snap!</strong>You need to login before you either
								follow friend or like the lookbook... <strong><a
									href="/login">Login Here</a></strong>
							</h4>
						</div>
						<div class="alert alert-danger alert-dismissible" role="alert"
							id="err_box" style="display: none;"></div>
						<div class="row">
							<div class="col-sm-10">
								<h1>${lookbook.lookbookname}...</h1>
							</div>
							<div class="col-sm-2">
								<ol class="breadcrumb pull-right hidden-xs">
									<li class="active"><a href="/">&lt;&lt;Home</a></li>
								</ol>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-8">
								<p class="lead">
									By <a href="/u/${usrbo.account_username}/">${usrbo.account_username}</a>
									from ${usrbo.citynm}
								</p>
							</div>
							<div class="col-sm-4">
								<ol class="breadcrumb pull-right hidden-xs">
									<li><a href="/u/${usrbo.account_username}/">${usrbo.account_username}</a></li>
									<li class="active">Lookbook</li>
								</ol>
							</div>
						</div>

						<c:if test="${lookbook.total_no_photos > 0 }">

							<div class="row">

								<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
									<div class="row">
										<div class="col-sm-6 col-xs-6 col-md-2 col-lg-2">
											<h4 class="headline">
												<span>Looks You may Like</span>
											</h4>
											<c:forEach items="${randomlooks}" var="randomlook">
												<div class="row">
														<div class="col-sm-6 col-xs-6 col-md-12 col-lg-12">
															<div class="panel-thumbnail">
																<a
																	href="/u/${randomlook.lookbook_owner}/lookbook/view/${randomlook.lookbookid}/${randomlook.lookbook_seo_url}">
																	<img src="${randomlook.gcs_photo_url1}"
																	class="img-responsive" onContextMenu="return false;"
																	style="width:350px;height:auto;margin-top:10px;">
																</a>
															</div>
														</div>
												</div>
											</c:forEach>
										</div>
										<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
											<p>
												<c:if test="${not empty lookbook.gcs_photo_url1}">
													<img src="${lookbook.gcs_photo_url1 }"
														class="img-responsive" onContextMenu="return false;">
												</c:if>
											</p>
										</div>
										<div class="col-sm-12 col-xs-12 col-md-4 col-lg-4">
											<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
												<div class="row">
													<c:if
														test="${lookbook.islookbook_liked_by_login_usr == false }">
														<h4>
															<button id="vote" class="btn btn-red">
																<i class="fa fa-lg fa-thumbs-o-up"></i> | &nbsp;&nbsp;Like
															</button>
														</h4>
													</c:if>
													<c:if
														test="${lookbook.islookbook_liked_by_login_usr == true }">
														<h4>
															<button id="vote" class="btn btn-red" disabled="disabled">
																<i class="fa fa-lg fa-thumbs-o-up"></i> | &nbsp;&nbsp;Like
															</button>
														</h4>
													</c:if>
												</div>

												<div class="row">
													<c:if test="${not empty lookbook.lookbookseason }">
														<p>
															<b>Lookbook Season: </b> ${lookbook.lookbookseason}
														</p>
													</c:if>
													<c:if test="${not empty lookbook.lookbookstylestr }">
														<p>
															<b>Lookbook Style: </b> ${lookbook.lookbookstylestr}
														</p>
													</c:if>

													<c:if test="${not empty lookbook.updtdtags }">
														<p>
															<b>Tags : </b>
															<c:forEach items="${lookbook.updtdtags}" var="tag">
																<a href="/search?tag=${tag}">${tag }</a>
															</c:forEach>
													</c:if>
													<c:if test="${not empty lookbook.outfit_type1}">
														<p>
															<b>${lookbook.outfit_type1 } : </b>
															${lookbook.outfit_brand1 }
														</p>
													</c:if>
													<c:if test="${not empty lookbook.outfit_type2}">
														<p>
															<b>${lookbook.outfit_type2 } : </b>
															${lookbook.outfit_brand2 }
														</p>
													</c:if>
													<c:if test="${not empty lookbook.outfit_type3}">
														<p>
															<b>${lookbook.outfit_type3 } : </b>
															${lookbook.outfit_brand3 }
														</p>
													</c:if>
													<c:if test="${not empty lookbook.outfit_type4}">
														<p>
															<b>${lookbook.outfit_type4 } : </b>
															${lookbook.outfit_brand4 }
														</p>
													</c:if>
													<c:if test="${not empty lookbook.outfit_type5}">
														<p>
															<b>${lookbook.outfit_type5 } : </b>
															${lookbook.outfit_brand5 }
														</p>
													</c:if>
												</div>
												<div class="row">
													<h4>
														<b>Stats:</b> <b>${lookbook.total_comments }</b> <span
															class="fa fa-lg fa-comments-o text-blue"></span> <b><span
															id="tot_likes">${lookbook.total_likes }</span></b> <span
															class="fa fa-lg fa-heart text-red"></span>
													</h4>
												</div>
											</div>

											<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
												<div class="well well-sm">
													<div class="media">
														<h4>
															<a href="/u/${usrbo.account_username}/">${usrbo.account_username}</a>
														</h4>
														<a class="thumbnail" href="/u/${usrbo.account_username}/">
															<img src="${usrbo.uavatar }" class="media-object
															center-block img-thumbnail
															img-responsive" style="width: 150px;">
														</a>
														<div class="media-body">
															<input type="hidden" value="${usrbo.userid}" id="uid" />
															<p class="text-center">
																<button type="button" class="btn btn-red"
																	id="usr_follow_actn" rel="nofollow">
																	<i class="fa fa-1x fa-arrow-right"></i> Follow
																</button>
															</p>
															<p class="text-center">
																<b><span class="label label-info">${usrbo.total_lookbooks }
																		Looks</span> | <span class="label label-success">${usrbo.total_glampoints }
																		Points</span> | <span class="label label-warning">${usrbo.total_followers }
																		Followers</span></b>
															</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!--  / row -->
									<c:if test="${not empty lookbook.lookbookstory}">
										<div class="row">
											<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
												<p>${lookbook.lookbookstory}</p>
											</div>
										</div>
									</c:if>
									<c:if test="${not empty lookbook}">
										<c:if test="${lookbook.total_no_photos > 1 }">
											<div class="row">
												<h4 class="headline">
													<span>All Looks...</span>
												</h4>
												<div class="col-sm-12">

													<c:if test="${not empty lookbook.gcs_photo_url1}">
														<div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
															<div class="thumbnail card">
																<div>
																	<img src="${lookbook.gcs_photo_url1 }"
																		class="img-responsive" onContextMenu="return false;">
																</div>
															</div>
														</div>
													</c:if>

													<c:if test="${not empty lookbook.gcs_photo_url2}">
														<div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
															<div class="thumbnail card">
																<div>
																	<img src="${lookbook.gcs_photo_url2 }"
																		class="img-responsive" onContextMenu="return false;">
																</div>
															</div>
														</div>
													</c:if>

													<c:if test="${not empty lookbook.gcs_photo_url3}">
														<div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
															<div class="thumbnail card">
																<div>
																	<img src="${lookbook.gcs_photo_url3 }"
																		class="img-responsive" onContextMenu="return false;">
																</div>
															</div>
														</div>
													</c:if>
												</div>
											</div>
											<!--  / row -->
										</c:if>
									</c:if>

									<c:if test="${not empty lookbook.likedbyusrs }">
										<div class="row">
											<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
												<h4 class="headline">
													<span>Liked By...</span>
												</h4>
											</div>
										</div>
										<div class="row">
											<c:forEach items="${lookbook.likedbyusrs}" var="likedusr">
												<div class="col-sm-4 col-xs-4 col-md-2 col-lg-2">
													<div align="center">
														<a href="/u/${likedusr.account_username }"> <img
															src="${likedusr.uavatar_thumb_profile }"
															class="img-responsive card" style="width: 80px;" />
														</a> <small><b><a
																href="/u/${likedusr.account_username }">${likedusr.account_username }</a></b></small>
													</div>
												</div>
											</c:forEach>

										</div>
										<!-- // row -->
									</c:if>
								</div>
								<!-- // end of col-md-12 -->
							</div>
							<!-- // row -->

						</c:if>
						<c:if test="${not empty lookbook.lookbook_youtube_videourl }">
							<div class="row">
								<div class="col-sm-12">
									<h4 class="headline">
										<span>Lookbook Video...</span>
									</h4>
									<div class="row">
										<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
											<div class="text-left">
												<div id="youtbe"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
											<script async
												src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
											<!-- gf-respnsv -->
											<ins class="adsbygoogle" style="display: block"
												data-ad-client="ca-pub-9745887657365691"
												data-ad-slot="8920725253" data-ad-format="auto"></ins>
											<script>
																(adsbygoogle = window.adsbygoogle || []).push({});
																</script>
										</div>
									</div>
								</div>
							</div>
							<!-- // row -->
						</c:if>

						<c:if test="${not empty latestlooks }">
							<div class="row">
								<div class="col-sm-12">
									<h4 class="headline">
										<span>Latest Looks...</span>
									</h4>
									<div class="row">

										<c:forEach items="${latestlooks }" var="latestlook">
											<div class="col-sm-6 col-xs-6 col-md-3 col-lg-3">
												<div class="panel-thumbnail">
													<a
														href="/u/${latestlook.lookbook_owner}/lookbook/view/${latestlook.lookbookid}/${latestlook.lookbook_seo_url}">
														<img src="${latestlook.gcs_photo_url1}"
														class="img-responsive" onContextMenu="return false;"
														style="width: 177px; height: auto; margin-top: 0px;">
													</a>
													<h3>
														<a
															href="/u/${latestlook.lookbook_owner}/lookbook/view/${latestlook.lookbookid}/${latestlook.lookbook_seo_url}">${latestlook.lookbookname}</a>
													</h3>
												</div>
											</div>
										</c:forEach>

									</div>
								</div>
							</div>
						</c:if>
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
														(adsbygoogle = window.adsbygoogle || []).push({});
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
														(adsbygoogle = window.adsbygoogle || []).push({});
														</script>
							</div>
						</div>
						<c:if test="${not empty morelooks }">
							<div class="row">
								<div class="col-sm-12">
									<h4 class="headline">
										<span>More from User...</span>
									</h4>
									<div class="row">

										<c:forEach items="${morelooks }" var="morelook">
											<div class="col-sm-6 col-xs-6 col-md-3 col-lg-3">
												<div class="panel-thumbnail">
													<a
														href="/u/${morelook.lookbook_owner}/lookbook/view/${morelook.lookbookid}/${morelook.lookbook_seo_url}">
														<img src="${morelook.gcs_photo_url1}"
														class="img-responsive" onContextMenu="return false;"
														style="width: 177px; height: auto; margin-top: 0px;">
													</a>
												</div>
											</div>
										</c:forEach>

									</div>
								</div>
							</div>
						</c:if>

						<div class="row" ng-app="LkbkCmmntApp"
							ng-controller="lkbkCmmntController">
							<div class="col-sm-12">
								<h3 class="headline">
									<span>Comments</span>
								</h3>
								<div class="row">
									<div class="col-sm-12">
										<c:if test="${not empty ul }">
											<form>
												<input type="hidden" ng-model="cmmnt.lookbookid" />
												<div class="form-group">
													<label for="message">Post your comments</label>

													<div class="row">
														<div class="col-sm-8">
															<div class="alert alert-danger alert-dismissible"
																role="alert" id="cmmnt_err_blnk_txtbox"
																style="display: none;">
																<button type="button" class="close" data-dismiss="alert">
																	<span aria-hidden="true">&times;</span><span
																		class="sr-only">Close</span>
																</button>
																<h4>
																	<strong>Error!</strong> You can not post blank
																	comment... pls write something...
																</h4>
															</div>
															<div class="alert alert-danger alert-dismissible"
																role="alert" id="cmmnt_othr_err" style="display: none;">
																<button type="button" class="close" data-dismiss="alert">
																	<span aria-hidden="true">&times;</span><span
																		class="sr-only">Close</span>
																</button>
																<h4>
																	<strong>Oh Snap!</strong>Something went wrong, pls
																	refresh the page and continue...
																</h4>
															</div>
															<textarea class="form-control"
																ng-model="cmmnt.comment_txt"
																placeholder="Enter your message"></textarea>
														</div>
														<div class="col-sm-4">
															<img id="img-spinner" src="/resources/img/spinner.gif"
																alt="Loading..." style="display: none;" /> <a
																id="save-btn" class="btn btn-red"
																ng-click="sveCmmnt(cmmnt)">Save Comment</a>
														</div>
													</div>
												</div>

											</form>
										</c:if>
										<c:if test="${empty ul }">
											<h4>
												<a href="/login" class="btn btn-red">Login</a> to post
												comments
											</h4>
										</c:if>
										<hr />

										<div class="row">
											<div class="col-sm-8">

												<div class="panel">
													<div class="panel-heading">
														<h2 class="panel-title">
															<i class="fa fa-2x fa-comment"></i>Recent Comments
														</h2>
													</div>
													<div class="panel-body">
														<ul class="list-group" ng-repeat="comment in commentslist">
															<li class="list-group-item">
																<div class="row">
																	<div class="col-xs-2 col-md-1">
																		<a href="/u/{{comment.username}}" rel="nofollow"><img
																			src="{{comment.uavatar_thumb}}"
																			class="img-responsive image-circle" alt="" /></a>
																	</div>
																	<div class="col-xs-10 col-md-11">
																		<div class="mic-info">
																			By: <a href="/u/{{comment.username}}" rel="nofollow">{{comment.username}}</a>
																			on {{comment.commentdt}}
																		</div>
																		<div class="comment-text">
																			{{comment.commenttxt}}</div>

																	</div>
																</div>
															</li>
														</ul>
													</div>
												</div>
												<!-- //panel -->

											</div>
											<!-- //col-sm-8 -->
											<div class="col-sm-4"></div>
										</div>
										<!-- //row -->
									</div>
								</div>
							</div>
						</div>
						<!-- // lkbkCmmntController row -->



					</div>
					<!-- //col-md-12 -->
					

				</div>
				<!-- //row -->

			</div>
		</div>
	</div>
	<!-- // container -->

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
	<script src="/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="/resources/js/jasny-bootstrap.min.js"
		type="text/javascript"></script>

	<script src="/resources/js/angular.min.js"></script>

	<c:if test="${lookbook.total_no_photos > 0 }">
		<!-- script src="/resources/js/pgwslider.min.js" type="text/javascript"></script -->
		<script>
	
	/* $(document).ready(function() {
		var pgwSlider = $('.pgwSlider').pgwSlider();
		pgwSlider.stopSlide();
	}); */
	
	var lookbookcmmntapp = angular.module('LkbkCmmntApp', []);
	
	lookbookcmmntapp.controller('lkbkCmmntController', ['$scope', '$http', function ($scope, $http)  {
		// set the default values of the comment form input fields
		$scope.cmmnt = {
				lookbookid: "${lookbook.lookbookid }",
				comment_txt: ""
		};
		$scope.commentslist = [];
		
		// refresh the comments list on first load
		$http({
				method: 'GET',
				url: '/comments/lookbook/list/' + $scope.cmmnt.lookbookid,
		        headers : { 'Accept': 'application/json', 'Content-Type': 'application/json' }
			})
			.success(function (results, status, headers, config) {
				//console.log(JSON.stringify(results))
				$scope.commentslist = results;
			})
			.error(function(err){"ERR", console.log(err)});
		
		
		// invoke save function to save the comment
		$scope.sveCmmnt = function(cmmnt) {
			//alert('cmmnt.comment_txt- ' + cmmnt.comment_txt);
			var cmmnt_val = cmmnt.comment_txt;
			if (/^\s*$/.test(cmmnt_val)) {
			   $("#cmmnt_err_blnk_txtbox").show();
			}else {
				$(".img-spinner").show();
				$(".save-btn").hide();
				$("#cmmnt_err_blnk_txtbox").hide();
				$http({
					method: 'POST',
					url: '/comments/lookbook/save',
					data: $scope.cmmnt, 
			        headers : { 'Accept': 'application/json', 'Content-Type': 'application/json' }
				})
				.success(function (mssg, status, headers, config) {
					//alert("data - " + JSON.stringify(data));
					
					/* if(data.mssg_type=="SCS") {
						$("#tot_comments").html(mssg.total_comments + " Comments");
					}else */ 
					if(mssg.mssg_type=="ERR") {
						 $("#cmmnt_othr_err").show();
						 $(".save-btn").show();
					}
					
					// refresh the comments list on first load
					$http({
							method: 'GET',
							url: '/comments/lookbook/list/' + $scope.cmmnt.lookbookid,
					        headers : { 'Accept': 'application/json', 
					        			'Content-Type': 'application/json', 
					        			'Cache-Control':'no-cache',
					        			'Pragma':'no-cache'}
						})
						.success(function (cmmntlist) {
							//console.log(JSON.stringify(results))
							$scope.commentslist = cmmntlist;
							$(".img-spinner").hide();
							$(".save-btn").show();
						})
						.error(function(err){"ERR", console.log(err)});
				})
				.error(function(err){"ERR", console.log(err)});
			};
		}

	}]);

	</script>
		<script>
	// function to get the youtube video id and generate the embedd code
		function getId(url) {
		    var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
		    var match = url.match(regExp);
		
		    if (match && match[2].length == 11) {
		        return match[2];
		    } else {
		        return 'error';
		    }
		}
		
		// save the lookbook liked by the user
		$("#vote").click(function() {
			$(".img-spinner").show();
			$.get("/likes/lookbook/save?lookbookid=" + ${lookbook.lookbookid }, function(mssg){
				//alert("mssgbn: " + JSON.stringify(mssg));
				$(".img-spinner").hide();
				if(mssg.mssg_type=="SCS") {
					$("#tot_likes").html(mssg.total_likes + " Likes");
				}else {
					$("#lookbook_like_err").show();
				}
			});
		});	
	</script>
	</c:if>
	<c:if test="${not empty lookbook.lookbook_youtube_videourl }">
		<script>
		var vidId = getId('${lookbook.lookbook_youtube_videourl}');
		$('#youtbe').html('<iframe width="560" height="315" src="//www.youtube.com/embed/' + vidId + '?autoplay=1" frameborder="0" allowfullscreen></iframe>');
	</script>
	</c:if>
	<script>
	$(document).ready(function() {
		$("#usr_follow_actn").click(function() {
			var uid = $("#uid").val();
			//alert("uname-" + uname);
			$.get("/followers/save?u2fllw=" + uid, function(mssg) {
				//alert(JSON.stringify(data));
				//alert("status- " + status);
				$("#usr_follow_actn").hide();
				if(mssg.mssg_type=="SCS") {
					//$("#tot_likes").html(mssg.total_likes + " Likes");
				}else {
					var err_mssg = '<button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>' + 
					    		   '<h4><strong>Oh Snap!</strong> ' + mssg.mssg + '</h4>';
					$("#err_box").hide();
					$("#err_box").html(err_mssg);
					$("#err_box").show();
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
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-60905111-1', 'auto');
  ga('send', 'pageview');

</script>
</body>
</html>