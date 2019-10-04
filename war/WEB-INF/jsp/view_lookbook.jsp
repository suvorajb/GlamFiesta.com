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
								<h1>GlamFiesta - ${lookbook.lookbookname}</h1>
								<p class="lead">By <a href="/fashionistas/${usrbo.account_username}" target="_blank">${usrbo.account_username }</a>
									from ${usrbo.citynm}
								</p>
								<img style="display: none;" id="img-spinner"
									src="/resources/img/spinner.gif" alt="Loading Lookbook..." />
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
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
								<div class="row">
									<div class="col-md-12">
										<div class="panel panel-default">
											<!-- // lookbook panel -->
											<div class="panel-body">

												<div class="row">

													<div class="col-sm-12 col-xs-12 col-md-9 col-lg-9">
														<c:if test="${not empty lookbook.gcs_photo_url1}">
															<img src="${lookbook.gcs_photo_url1 }"
																class="img-responsive" onContextMenu="return false;">
														</c:if>
													</div>
													<!-- //col-md-7 -->

													<div class="col-sm-12 col-xs-12 col-md-3 col-lg-3">

														<div class="row">
															<c:if
																test="${lookbook.islookbook_liked_by_login_usr == false }">
																<h4>
																	<button id="vote" class="btn btn-red">
																		<i class="fa fa-lg fa-thumbs-o-up"></i> |
																		&nbsp;&nbsp;Like
																	</button>
																</h4>
															</c:if>
															<c:if
																test="${lookbook.islookbook_liked_by_login_usr == true }">
																<h4>
																	<button id="vote" class="btn btn-red"
																		disabled="disabled">
																		<i class="fa fa-lg fa-thumbs-o-up"></i> |
																		&nbsp;&nbsp;Like
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
													<!-- //col-md-5 -->
												</div>
												<!-- // row -->
												<c:if test="${not empty lookbook.lookbookstory }">
													<div class="row">
														<div class="col-md-12">
															<h3 class="headline">
																<span>${usrbo.account_username}'s thought</span>
															</h3>
															<p>${lookbook.lookbookstory}</p>
														</div>
													</div>
												</c:if>
												<div class="row">
													<div class="col-md-12">
														<div id="icons">
															<h4 class="headline">
																<span>Share this Style</span>
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
													</div>
												</div>

											</div>
										</div>
										<!-- // panel end -->

									</div>
								</div>
								<!-- // row -->
								<c:if test="${not empty lookbook.lookbook_youtube_videourl }">
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<!-- // lookbook panel -->
												<div class="panel-body">

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

												</div>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${not empty morelooks }">
									<div class="row">
										<div class="col-sm-12 col-md-12">
											<div class="panel panel-default">
												<!-- // lookbook panel -->
												<div class="panel-body">

													<h4 class="headline">
														<span>More looks from ${usrbo.account_username }</span>
													</h4>
													<div class="row">
														<c:forEach items="${morelooks }" var="morelook">
															<div class="col-sm-6 col-xs-6 col-md-3 col-lg-3">
																<div class="panel-thumbnail">
																	<a
																		href="/fashion/${morelook.lookbook_seo_url}">
																		<img src="${morelook.gcs_photo_url1}"
																		class="img-responsive" onContextMenu="return false;"
																		style="width: 177px; height: auto; margin-top: 0px;">
																	</a>
																</div>
															</div>
														</c:forEach>
													</div>
													<!-- //row -->
												</div>
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
																		<button type="button" class="close"
																			data-dismiss="alert">
																			<span aria-hidden="true">&times;</span><span
																				class="sr-only">Close</span>
																		</button>
																		<h4>
																			<strong>Error!</strong> You can not post blank
																			comment... pls write something...
																		</h4>
																	</div>
																	<div class="alert alert-danger alert-dismissible"
																		role="alert" id="cmmnt_othr_err"
																		style="display: none;">
																		<button type="button" class="close"
																			data-dismiss="alert">
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
																<ul class="list-group"
																	ng-repeat="comment in commentslist">
																	<li class="list-group-item">
																		<div class="row">
																			<div class="col-xs-2 col-md-1">
																				<a href="/fashionistas/{{comment.username}}"><img
																					src="{{comment.uavatar_thumb}}"
																					class="img-responsive image-circle" alt="" /></a>
																			</div>
																			<div class="col-xs-10 col-md-11">
																				<div class="mic-info">
																					By: <a href="/fashionistas/{{comment.username}}">
																					{{comment.username}}</a> on
																					{{comment.commentdt}}
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
												</div>
												<!-- //row -->

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

												<c:if test="${not empty latestlooks }">
													<div class="row">
														<div class="col-sm-12 col-md-12">
															<div class="panel panel-default">
																<!-- // lookbook panel -->
																<div class="panel-body">

																	<h4 class="headline">
																		<span>Latest Street Style</span>
																	</h4>
																	<div class="row">

																		<c:forEach items="${latestlooks }" var="latestlook">
																			<div class="col-sm-6 col-xs-6 col-md-3 col-lg-3">
																				<div class="panel-thumbnail">
																					<a
																						href="/fashion/${latestlook.lookbook_seo_url}">
																						<img src="${latestlook.gcs_photo_url1}"
																						class="img-responsive"
																						onContextMenu="return false;"
																						style="width: 177px; height: auto; margin-top: 0px;">
																					</a>
																					<h3>
																						<a
																							href="/fashion/${latestlook.lookbook_seo_url}">${latestlook.lookbookname}</a>
																					</h3>
																				</div>
																			</div>
																		</c:forEach>

																	</div>
																	<!-- //row -->
																</div>
															</div>
														</div>
													</div>
												</c:if>
											</div>
										</div>
									</div>
								</div>
								<!-- // lkbkCmmntController row -->

							</div>
							<!-- //col-md-8 -->
							<div class="col-md-4">
								<div class="row">
									<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
										<div class="well well-sm">
											<h3>About Fashionista</h3>
											<div class="media">
												<a class="pull-left" href="/fashionistas/${usrbo.account_username}/">
													<img src="${usrbo.uavatar }"
													class="media-object
																center-block img-thumbnail
																img-responsive"
													style="width: 100px;">
												</a>
												<div class="media-body">
													<h4>
														<b>${usrbo.account_username}</b> from <b>${usrbo.citynm}</b>
													</h4>
													<p>
														<span class="label label-info">${usrbo.total_lookbooks }
															Looks</span> <span class="label label-warning">${usrbo.total_glampoints }
															Points</span>
													</p>
													<p>
														<button type="button" class="btn btn-red"
															id="usr_follow_actn" rel="nofollow">
															<i class="fa fa-1x fa-arrow-right"></i> Follow
														</button>
													</p>
												</div>
											</div>
										</div>
										<!-- //well well-sm -->
									</div>
								</div>
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
										<!-- Place this tag where you want the widget to render. -->
										<div class="g-person" data-width="240"
											data-href="//plus.google.com/u/0/110161307069826216017"
											data-rel="author"></div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<h3 class="headline">
											<span>Popular street style</span>
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
	<script src="/resources/js/jasny-bootstrap.min.js"
		type="text/javascript"></script>
	<script src="/resources/js/angular.min.js" type="text/javascript"></script>

	<c:if test="${lookbook.total_no_photos > 0 }">
		<script>
	
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