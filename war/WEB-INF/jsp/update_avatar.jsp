<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>GlamFiesta | Dashboard Update avatar pic </title>
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
</head>

<body>
<div class="container">
  
  <div class="row">
    
    <nav class="col-sm-2"><!--nav-->
      <div class="row">    
        <div class="col-sm-12"><!--logo-->
          <img src="/resources/img/logo_v2_1.png" alt="GlamFiesta" class="img-responsive center"/>
        </div><!--/logo-->
        <ul class="nav nav-pills nav-stacked menu">
          <li>&nbsp;</li>
          <li><a href="/"><span class="fa fa-leaf"></span>&nbsp;Fresh Looks</a></li>
          <li><a href="/toplooks"><span class="fa fa-thumbs-o-up"></span>&nbsp;Top Looks</a></li>
          <li><a href="/fashionistas"><span class="fa fa-users"></span>&nbsp;Fashionistas</a></li>
          <c:if test="${ul.loginstatus == true }">
          	<li class="selectd"><a href="/dashboard"><span class="fa fa-tachometer"></span>&nbsp;Dashboard</a></li>
          	<li><a href="/logout"><span class="fa fa-sign-in"></span>&nbsp;Sign Out</a></li>
          </c:if>
          <c:if test="${ul.loginstatus !=true }">
          	<li><a href="/login"><span class="fa fa-sign-in"></span>&nbsp;Sign In</a></li>
          	<li><a href="/signup"><span class="fa fa-user"></span>&nbsp;Sign Up</a></li>          	
          </c:if>
          <li><a href="/aboutus.htm"><span class="fa fa-chevron-circle-right"></span>&nbsp;About Us</a></li>
          <li><a href="/contactus.htm"><span class="fa fa-envelope"></span>&nbsp;Contact Us</a></li>
          <li><a href="/how-to-post-look.htm"><span class="fa fa-question-circle"></span>&nbsp;How 2 Post</a></li>
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
      </nav><!--/nav-->

      
      <div class="col-sm-10">
        
        <div class="row">
          <div class="col-xs-12">
           
        		<!--  // main content here -->
        		<!-- dashboard main content page -->
				    <!-- Topic Header -->
				      <div class="topic">
				        <div class="container">
				          <div class="row">
				            <div class="col-sm-6"><h3>Update Your Profile Information</h3></div>
				            <div class="col-sm-6">
				            	<ol class="breadcrumb">
				            		<li><a href="/dashboard"><b><c:out value="${usrbo.account_username }"/>'s Dashboard</b></a></li>
					                <li class="active">Update Profile</li>
					            </ol>
				            </div>
				          </div>
				        </div>
				      </div>   
					  <c:if test="${not empty succsmssg }">
						<div class="alert alert-success alert-dismissible" role="alert">
  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  							<c:out value="${succsmssg}"/>
						</div>
					  </c:if>	
					  <c:if test="${not empty errmssg }">
						<div class="alert alert-danger alert-dismissible" role="alert">
  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  							<c:out value="${errmssg}"/>
						</div>
					  </c:if>					  				      
				    <!-- Nav tabs -->
				      <ul class="nav nav-tabs nav-justified ">
						<li class="active"><a href="#profile" data-toggle="tab"><strong>Update Profile</strong></a></li>
						<li><a href="#avatar" data-toggle="tab"><strong>Update Avatar</strong></a></li>
						<li><a href="#pwd" data-toggle="tab"><strong>Change Password</strong></a></li>
				      </ul>
					
					<!-- Tab panes -->
					<div class="tab-content">
					
						<div class="tab-pane active" id="profile">
						
						<form:form modelAttribute="userform" method="POST" action="/dashboard/updtprofile">
						
								<div class="panel panel-default">
								  <div class="panel-heading"><h4><span>Profile Information...</span></h4></div>
									<div class="panel-body">
	
										<div class="form-group">
											<div class="col-sm-12">
												<label>Email id </label>
												<form:input type="text" path="account_email" class="form-control" disabled="true"/>
											</div>
										</div>
	
										<div class="form-group">
											<div class="col-sm-12">
												<label>Username </label>
												<form:input type="text" path="account_username" class="form-control" disabled="true"/>
											</div>
										</div>											
																		
										<div class="form-group">
										  <div class="col-sm-6"><label>First Name</label>
											<form:input type="text" path="first_name" class="form-control" placeholder="Type your first name..." />
										  </div>
										  <div class="col-sm-6"><label>Last Name </label>
											<form:input type="text" path="last_name" class="form-control" placeholder="Type your last name..." />										
										  </div>
										</div>
										
										<div class="form-group">
										  <div class="col-sm-12">
											<label>Tell Others About You </label>
											<form:textarea path="aboutme" rows="5" class="form-control" placeholder="Describe in few words about you..."/>
										  </div>
										</div>
	
										<div class="form-group">
										  <div class="col-sm-12">
											<label> Where are you from?</label>
											<form:input type="text" path="citynm" class="form-control" placeholder="e.g. San Francisco, CA, USA" />
										  </div>
										</div>
										
										<div class="form-group">
										  <div class="col-sm-12">
												<label>Where others can find you?</label><br/>
												<label><i class="fa fa-twitter"></i> Twitter Profile</label> <form:input type="text" path="twitter_ac" class="form-control" placeholder="Type your Twitter account url"  /><br/>
												<label><i class="fa fa-facebook"></i> Facebook Page/Profile</label> <form:input type="text" path="facebook_ac" class="form-control" placeholder="Type your Facebook url"  /><br/>
												<label><i class="fa fa-google-plus"></i> Google+ Profile/Page </label> <form:input type="text" path="google_plus_ac" class="form-control" placeholder="Type your Google+ channel name"  /><br/>
												<label><i class="fa fa-youtube"></i> Youtube Channel </label> <form:input type="text" path="youtube_ac" class="form-control" placeholder="Type your Youtube channel name"  /><br/>
												<label><i class="fa fa-linkedin"></i> Linkedin professional Profile/Page</label> <form:input type="text" path="linkedin_ac" class="form-control" placeholder="Type your Linkedin profile"  /><br/>
												<label><i class="fa fa-pinterest"></i> Pinterest Board</label> <form:input type="text" path="pinterest_ac" class="form-control" placeholder="Type your Pinterest board url"  /><br/>
												<label><i class="fa fa-instagram"></i> Instagram Profile</label> <form:input type="text" path="instagram_ac" class="form-control" placeholder="Type your Instagram profile url"  /><br/>
												<label><i class="fa fa-tumblr"></i> Tumblr Page</label> <form:input type="text" path="tumblr_ac" class="form-control" placeholder="Type your Tumblr profile url"  /><br/>
												<label><i class="fa fa-wordpress"></i> Blog/Website</label> <form:input type="text" path="website_blog_url" class="form-control" placeholder="Type your Blor or website url"  /><br/>
										  </div>
										</div>
										
									</div>	<!-- panel-body -->
									
									<div class="panel-footer">
										<div class="row">
										  <div class="col-sm-9 col-sm-offset-3">
										  	<input type="submit" class="btn btn-primary" value="Update Profile">
											<button type="reset" class="btn btn-default">Reset</button>
										  </div>
										</div>
									</div>
								
								</div><!-- panel -->							
							</form:form>
	
						</div><!-- // tabe-pane profile update -->
						
						
						<div class="tab-pane" id="avatar">
						<form:form modelAttribute="avatarform" method="POST" action="${avatarUploadUrl}" enctype="multipart/form-data">
								
								<div class="panel panel-default">
								  <div class="panel-heading"><h4><span>Update Avatar</span></h4></div>
									<div class="panel-body">
														
										<div class="form-group">
											<div class="col-sm-4">
												<img src="${usrbo.uavatar}" class="thumbnail img-responsive" alt="" />
												<!-- >a href=""><b>Delete</b></a -->
											</div>
											<div class="col-sm-8">
												<label>Upload Avatar </label>
												<input type="file" name="avatar"/>
											</div>
										</div>
	
									  
									</div>	<!-- panel-body -->
									
									<div class="panel-footer">
										<div class="row">
										  <div class="col-sm-9 col-sm-offset-3">
										  	<input type="submit" class="btn btn-primary" value="Update Avatar">
											<button type="reset" class="btn btn-default">Reset</button>
										  </div>
										</div>
									</div>
								
								</div><!-- panel -->							
							</form:form>
	
						</div><!-- // tabe-pane avatar update -->	
	
						<div class="tab-pane" id="pwd">
							<form:form modelAttribute="pwdform" method="POST" action="/dashboard/updtpwd">
									
									<div class="panel panel-default">
									  <div class="panel-heading"><h4><span>Change Password</span></h4></div>
										<div class="panel-body">
															
											<div class="form-group">
												<div class="col-sm-6">
													<label>Update Password </label>
													<form:input type="text" path="account_password" class="form-control"/>
												</div>
											</div>
		
										  
										</div>	<!-- panel-body -->
										
										<div class="panel-footer">
											<div class="row">
											  <div class="col-sm-9 col-sm-offset-3">
											  	<input type="submit" class="btn btn-primary" value="Change Password">
												<button type="reset" class="btn btn-default">Reset</button>
											  </div>
											</div>
										</div>
									
									</div><!-- panel -->							
							</form:form>
						</div><!-- // tabe-pane avatar update -->
					</div> <!-- tab content -->
				
				<!-- // dashboard main content page -->
          </div><!--  //col-xs-12 -->
        </div><!-- // row -->
        
      </div>
    </div>   
        
 </div><!--/.content-->
 
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
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<script>
		// This example displays an address form, using the autocomplete feature
		// of the Google Places API to help users fill in the information.
		
		var placeSearch, autocomplete;
	
		function initialize() {
		  // Create the autocomplete object, restricting the search
		  // to geographical location types.
		  autocomplete = new google.maps.places.Autocomplete((document.getElementById('citynm')), { types: ['(cities)'] });
		  // When the user selects an address from the dropdown,
		  // populate the address fields in the form.
		  google.maps.event.addListener(autocomplete, 'place_changed', function() {
		    fillInAddress();
		  });
		}

		// The START and END in square brackets define a snippet for our documentation:
		// [START region_fillform]
		function fillInAddress() {
		  // Get the place details from the autocomplete object.
		  var place = autocomplete.getPlace();
		}
			
		// Bias the autocomplete object to the user's geographical location,
		// as supplied by the browser's 'navigator.geolocation' object.
		function geolocate() {
		  if (navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(function(position) {
		      var geolocation = new google.maps.LatLng(
		          position.coords.latitude, position.coords.longitude);
		      autocomplete.setBounds(new google.maps.LatLngBounds(geolocation,
		          geolocation));
		    });
		  }
		}
		
		initialize();
	</script>    
</body>
</html>