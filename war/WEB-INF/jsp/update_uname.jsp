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
<title>GlamFiesta | Dashboard - Update username</title>
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
          	<li><a href="/dashboard"><span class="fa fa-tachometer"></span>&nbsp;Dashboard</a></li>
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
            
            <div class="row">
              <div class="col-md-12">
                <h1>GlamFiesta</h1> <p class="lead">Celebrate the fashionista in you</p>  
              </div>
            </div>
            
            <div class="row">
        		<div class="col-md-9">
        		<!--  // main content here -->
	        		<form:form modelAttribute="accform" method="POST" action="/unmupdt">
						
	                    <h4 class="nomargin">Create Unique Username</h4>
	                    <p><small>Please create an unique username to use in glamfiesta.com</small></p>
	                    
						<c:if test="${not empty errmssg }">
							<div class="alert alert-danger alert-dismissible" role="alert">
	  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  							<c:out value="${errmssg}"/>
							</div>
						</c:if>					
						
						<div class="row">
	                        <div class="col-sm-8"><form:input type="text" path="account_email" class="form-control" placeholder="Your Emailid" readonly="true" /></div>
	                    </div>
						
						<hr/>
	
						<div class="row">
	                        <div class="col-sm-8">
	                        <span class="help-block">You need to create an unique username which you'll use for login</span>
	                        <small>No space or ? or ! symbol allowed</small>
	                        <form:input type="text" path="account_username" class="form-control" placeholder="Pls create your unique username" /></div>
	                    </div>
	
						<div class="row">
	                        <div class="col-sm-8"><form:input type="text" path="citynm" class="form-control" placeholder="Where are you from? e.g. New York" onFocus="javascript: geolocate();"/></div>
	                    </div>
	                    
						<div class="error_box" class="alert alert-danger alert-dismissible" ></div>
						
						<div class="row">
	                        <div class="col-sm-8"><input type="submit" class="btn btn-red" value="Save Username"></div>
	                    </div>
						
	                    
	                </form:form>
        		
            	</div>
		        <div class="col-md-3">
		        	<h3 class="headline"><span>Advertisements...</span></h3>
		            			<p>
					            	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
									<!-- static_pg_glamfiesta_rect1 -->
									<ins class="adsbygoogle"
									     style="display:inline-block;width:300px;height:250px"
									     data-ad-client="ca-pub-9745887657365691"
									     data-ad-slot="7625793253"></ins>
									<script>
									(adsbygoogle = window.adsbygoogle || []).push({});
									</script>
					            </p>
		        </div>
		        
    		</div>
                
          </div>
        </div>
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
<script src="/resources/js/validate.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->

<script type="text/javascript">
	new FormValidator('signupform', [
	
	{
	    name: 'citynm',
	    display: 'City Name',
	    rules: 'required'
	},
	{
	    name: 'account_username',
	    display: 'Username',
	    rules: 'required|alpha_dash|min_length[3]'
	}], function(errors, evt) {
	
	    /*
	     * DO NOT COPY AND PASTE THIS CALLBACK. THIS IS CONFIGURED TO WORK ON THE DOCUMENTATION PAGE ONLY.
	     * YOU MUST CUSTOMIZE YOUR CALLBACK TO WORK UNDER YOUR ENVIRONMENT.
	     */
	
	    var SELECTOR_ERRORS = $('.error_box')
	    
	    if (errors.length > 0) {
	        SELECTOR_ERRORS.empty();
	
	        for (var i = 0, errorLength = errors.length; i < errorLength; i++) {
	            SELECTOR_ERRORS.append('<font color=red>' + errors[i].message + '</font><br />');
	        }
	
	    }else {
	    	return true;
	    }
	});
</script>
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