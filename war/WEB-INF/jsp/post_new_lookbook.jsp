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
<title>GlamFiesta | Post New Look</title>
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
<link href="/resources/css/bootstrap-tagsinput.css" rel="stylesheet">
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

						<!-- dashboard main content page -->

						<!-- Topic Header -->
						<div class="topic">
							<div class="container">
								<div class="row">
									<div class="col-sm-6">
										<h3>Post A New Look</h3>
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


						<div class="profile-header">
							<h3 class="profile-name">Post a new look in 30 seconds...</h3>
							<b>Photo Posting Rules:</b>
							<ul>
								<li><small>No offensive photos are allowed.</small></li>
								<li><small>Photographers are welcome to post their
										model's photos.</small></li>
								<li><small>No commercial advertisements or spam
										photos are allowed.</small></li>
								<li><small>No children's photos pls</small></li>
								<li><small>No Photo can be posted which you don't
										own rights. We'll remove if there is any complaints.</small></li>
								<li><small>Selfie pictures are welcome!</small></li>
							</ul>
						</div>
						<!-- profile-header -->

						<form:form modelAttribute="lookbookform" method="POST"
							action="${photoUploadUrl}" enctype="multipart/form-data">

							<div class="panel panel-default">
								<div class="panel-body">
									<div class="error_box"
										class="alert alert-danger alert-dismissible"></div>
									<c:if test="${not empty errmssg }">
										<div class="alert alert-danger alert-dismissible" role="alert">
											<button type="button" class="close" data-dismiss="alert">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<c:out value="${errmssg}" />
										</div>
									</c:if>
									<div class="form-group">
										<div class="col-sm-12">
											<label>What are you wearing today ? (Look Title))<span
												class="asterisk">*</span></label>
											<form:input type="text" path="lookbookname"
												class="form-control"
												placeholder="e.g. Bohemian Maxi Skirt to enjoy the summer heat" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12">
											<label>Is this your look? <span class="asterisk">*</span></label>
											<div class="well">
												<form:radiobutton path="lookbookme" value="Yes" />
												Yes, this is my look <br />
												<form:radiobutton path="lookbookme" value="No" />
												No, I am posting someone else's look <br />
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12">
											<h4>Look Photo</h4>
											<label>Browse & Upload Your Look Photo </label> <span
												class="asterisk">*</span>
											<div class="well">
												<input type="file" name="outfit1" />
											</div>

											<!-- label>Upload Photo 2 </label>
										<div  class="well">
											<input type="file" name="outfit2" /> 
										</div>
										
										<label>Upload Photo 3 </label>
										<div  class="well">
											<input type="file" name="outfit3" /> 
										</div -->
										</div>

									</div>
									<!-- // form-group -->

									<div class="form-group">
										<div class="col-sm-6">
											<label>Lookbook Style</label>
											<form:select path="lookbookstyle" class="form-control"
												multiple="multiple">
												<form:option selected="selected" value="Street Style">Street Style</form:option>
												<form:option value="Vintage">Vintage</form:option>
												<form:option value="Metro Style">Metro Style</form:option>
												<form:option value="Bohemian">Bohemian</form:option>
												<form:option value="Student, back to school">Student, back to school</form:option>
												<form:option value="Corporate & Business Style">Corporate & Business Style</form:option>
												<form:option value="Business Casual">Business Casual</form:option>
												<form:option value="Classic & Elegant">Classic & Elegant</form:option>
												<form:option value="Modern & Trendy">Modern & Trendy</form:option>
												<form:option value="Only Denim">Only Denim</form:option>
												<form:option value="Formal wear">Formal wear</form:option>
												<form:option value="Geek & Nerdy">Geek & Nerdy</form:option>
												<form:option value="Leather">Leather</form:option>
												<form:option value="Maternity">Style for Maternity</form:option>
												<form:option value="Sexy">Sexy</form:option>
												<form:option value="Sports">Sports</form:option>
												<form:option value="DIY (Do It Yourself) Outfit">DIY (Do It Yourself) Outfit</form:option>
												<form:option value="70s Look">70s Look</form:option>
												<form:option value="80s Look">80s Look</form:option>
												<form:option value="90s Look">90s Look</form:option>
											</form:select>

										</div>

										<div class="col-sm-6">
											<label>Season of your lookbook</label>
											<form:select path="lookbookseason" class="form-control">
												<form:option selected="selected" value="All Season">All Season</form:option>
												<form:option value="Summer">Summer</form:option>
												<form:option value="Fall">Fall</form:option>
												<form:option value="Winter">Winter</form:option>
												<form:option value="Spring">Spring</form:option>
											</form:select>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12">
											<label>Do you have any Youtube Fashion video ? [Copy
												the URL]</label><br />
											<form:input type="text" path="lookbook_youtube_videourl"
												class="form-control"
												placeholder="e.g. https://www.youtube.com/watch?v=fScPyyO6mN0" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12">
											<label>Any Tags / Keywords </label> <small>[Press
												Enter after every tag/keyword]</small><br />
											<form:input type="text" path="tags" class="form-control"
												data-role="tagsinput" />
											<span class="help-block"></span>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12">
											<label>Write something about your lookbook </label>
											<form:textarea path="lookbookstory" rows="5"
												placeholder="Let other fashionistas know about your lookbook..."
												class="form-control" />
											<span class="help-block">example - my first spring
												OOTD featuring tops from H&M and leggings from Kohls.</span>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12">
											<label>Publish Lookbook Now?</label>
											<div class="well">
												<form:radiobutton path="lookbook_publish_status" value="Yes" />
												Yes Publish Now <br />
												<form:radiobutton path="lookbook_publish_status" value="No" />
												No, I'll publish later <br />

											</div>
										</div>
									</div>

								</div>
								<!-- panel-body -->

								<div class="panel-footer">
									<div class="row">
										<div class="col-sm-9 col-sm-offset-3">
											<input id="sbmtbttn" type="submit" class="btn btn-primary"
												value="Create Lookbook" /> <img style="display: none;"
												id="img-spinner" src="/resources/img/spinner.gif"
												alt="Loading..." /> <input id="rstbttn" type="reset"
												class="btn btn-default" value="Reset All Fields" />
										</div>
									</div>
								</div>

							</div>
							<!-- panel -->
						</form:form>

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
									(adsbygoogle = window.adsbygoogle || [])
											.push({});
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
									(adsbygoogle = window.adsbygoogle || [])
											.push({});
								</script>
							</div>
						</div>

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
	<script src="/resources/js/validate.min.js"></script>
	<script src="/resources/js/bootstrap-tagsinput.min.js"></script>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

	<script type="text/javascript">
		new FormValidator(
				'lookbookform',
				[
						{
							name : 'lookbookname',
							display : 'Look Title',
							rules : 'required|max_length[499]|min_length[6]'
						},
						{
							name : 'outfit1',
							display : 'Look Photo',
							rules : 'required|is_file_type[gif,png,jpg,jpeg,JPG,Jpg,GIF,Gif,PNG,Png,JPEG,Jpeg]'
						} ],
				function(errors, evt) {

					/*
					 * DO NOT COPY AND PASTE THIS CALLBACK. THIS IS CONFIGURED TO WORK ON THE DOCUMENTATION PAGE ONLY.
					 * YOU MUST CUSTOMIZE YOUR CALLBACK TO WORK UNDER YOUR ENVIRONMENT.
					 */

					var SELECTOR_ERRORS = $('.error_box')

					if (errors.length > 0) {
						SELECTOR_ERRORS.empty();

						for (var i = 0, errorLength = errors.length; i < errorLength; i++) {
							SELECTOR_ERRORS.append('<h4><font color=red>'
									+ errors[i].message + '</font></h4>');
						}

					} else {
						frmSbmt();
						return true;
					}
				});

		function frmSbmt() {
			//$('input[type=submit]', this).attr('disabled', 'disabled');
			$('#sbmtbttn').hide();
			$('#rstbttn').hide();
			$('#img-spinner').show();
		}
	</script>

</body>
</html>