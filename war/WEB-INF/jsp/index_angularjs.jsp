<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>GlamFiesta - Discover the fashionista in you | new outfits, looks, Lookbook, OOTD, Outfit Of The Day, fashion, beauty, style idea... </title>
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
</head>

<body ng-app="indxLkbkApp">
	<div class="spinner" style="display:none;">
	    <img id="img-spinner" src="/resources/img/spinner.gif" alt="Loading Comments..."/>
	</div>
<div class="container">
  
  <div class="row">
    
    <nav class="col-sm-2"><!--nav-->
      <div class="row">
        <div class="col-sm-12"><!--logo-->
          <img src="/resources/img/logo_v2_1.png" alt="test" class="img-responsive center"/>
        </div><!--/logo-->
        <ul class="nav nav-pills nav-stacked menu">
          <li>&nbsp;</li>
          <li class="selectd"><a href="/"><span class="fa fa-home"></span>&nbsp;&nbsp;Home</a></li>
          <li><a href="/fashionistas"><span class="fa fa-users"></span>&nbsp;&nbsp;Fashionistas</a></li>
          <c:if test="${ul.loginstatus == true }">
          	<li><a href="/dashboard"><span class="fa fa-tachometer"></span>&nbsp;&nbsp;Dashboard</a></li>
          	<li><a href="/logout"><span class="fa fa-sign-in"></span>&nbsp;&nbsp;Sign Out</a></li>
          </c:if>
          <c:if test="${ul.loginstatus == false }"><li><a href="/login"><span class="fa fa-sign-in"></span>&nbsp;&nbsp;Sign In</a></li></c:if>
          
          <li><a href="/signup"><span class="fa fa-user"></span>&nbsp;&nbsp;Sign Up</a></li>
          <li><a href="/contactus"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;Contact</a></li>
          
        </ul>
        
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
        		<div class="col-md-9" ng-controller="lkbkListController">
        			<div ng-repeat="lookbook in lookbooklist">
        			
						<div class="row">
			                <div class="col-md-12 post">
			                    <div class="row">
			                        <div class="col-lg-12">
			                            <h4 class="headline"><span>{{lookbook.lookbookname}}</span></h4>
										<div class="row">
											<div class="col-xs-2 col-md-1">
					                            <p><a href=""><img src="{{lookbook.usrinfo.uavatar}}" class="img-responsive" alt=""></a></p>
											</div>
											<div class="col-xs-10 col-md-11"><p>Posted By <a href="/{{lookbook.usrinfo.account_username}}">{{lookbook.usrinfo.account_username}}</a>, from <b>{{lookbook.usrinfo.location}}</b>
											 | <small class="text-right"><b>{{lookbook.published_dttm_str}}</b></small>
											</p>
											</div>
										</div>
			                        </div>
			                    </div>
			
			                    <div class="row">
			                        <div class="col-md-5">
										<div class="thumbnail card">
											<div>
												<p><a href=""><img src="{{lookbook.gcs_photo_url1}}" class="img-responsive"></a></p>
												<p> {{lookbook.total_comments}} <span class="fa fa-comment text-blue"></span>  
													{{lookbook.total_likes}} <span class="fa fa-heart text-red"></span></p>
											</div>
										</div>
			                        </div>
			                        <div class="col-md-7">
			                            <p>{{lookbook.lookbookstory}} </p>						
			                            <p><span class="text-right"><a class="btn btn-red" href="/{{lookbook.usrinfo.account_username}}/lookbook/view/{{lookbook.lookbookid}}/{{lookbook.lookbook_seo_url}}">Read more</a></span></p>
										<p>&nbsp;</p>
										<p>
											<div id="icons">
												<h4 class="headline"><span>Share</span></h4>
												<ul>
													<li><a href="http://facebook.com/"><i class="fa fa-facebook"></i></a></li>
													<li><a href="http://linkedin.com/"><i class="fa fa-twitter"></i></a></li>
													<li><a href="http://twitter.com/"><i class="fa fa-google-plus"></i></a></li>
													<li><a href="http://twitter.com/"><i class="fa fa-linkedin"></i></a></li>
												</ul>
											</div>
										</p>
			                        </div>
			                    </div>
			                </div>
			            </div><!-- // row -->
            		</div>
            	</div>
		        <div class="col-md-3">
		        	<h3 class="headline"><span>Advertisements...</span></h3>
		            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		                <!-- Indicators -->
		                <ol class="carousel-indicators">
		                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
		                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
		                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
		                </ol>
		                <!-- Wrapper for slides -->
		                <div class="carousel-inner">
		                    <div class="item active">
		                        <img src="http://placehold.it/292/16a085/FFF&text=Advertisement1" alt="" class="img-responsive" />
		                    </div>
		                    <div class="item">
		                        <img src="http://placehold.it/292/d35400/FFF&text=Advertisement2" alt="" class="img-responsive" />
		                    </div>
		                    <div class="item">
		                        <img src="http://placehold.it/292/2980b9/FFF&text=Advertisement3" alt="" class="img-responsive" />
		                    </div>
		                    <div class="item">
		                        <img src="http://placehold.it/292/8e44ad/FFF&text=Advertisement4" alt="" class="img-responsive" />
		                    </div>
		                </div>
		                <!-- Controls -->
		                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
		                    <span class="glyphicon glyphicon-chevron-left"></span></a><a class="right carousel-control"
		                        href="#carousel-example-generic" data-slide="next"><span class="glyphicon glyphicon-chevron-right">
		                    </span>
		                </a>
		            </div>
		        </div><!-- // col-md-3 -->
		        
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
			<div class="copyright">Copyright 2014-2015 by <a href="">GlamFiesta</a> | All Rights Reserved</div>
        </div>
      </div>
    </footer>



<!-- JavaScript -->
<!-- JavaScript -->
<script src="/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/resources/js/angular.min.js"></script>
<script>
	
	var lookbooklistapp = angular.module('indxLkbkApp', []);
	
	lookbooklistapp.controller('lkbkListController', ['$scope', '$http', function ($scope, $http) {
		
		$scope.lookbooklist = [];
		$(".spinner").show();
		// refresh the comments list on first load
		$http({
				method: 'GET',
				url: '/looks/new',
		        headers : { 'Accept': 'application/json', 'Content-Type': 'application/json' }
			})
			.success(function (results, status, headers, config) {
				//console.log(JSON.stringify(results))
				$scope.lookbooklist = results;
				$(".spinner").hide();
			})
			.error(function(err){"ERR", console.log(err)});
	}]);
</script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->


</body>
</html>