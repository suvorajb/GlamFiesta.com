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
<title>Admin Featured Fashionistas</title>
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/style1.css" rel="stylesheet">
<link href="/resources/css/font-awesome.css" rel="stylesheet">
<link href="/resources/css/bootstrap-tagsinput.css" rel="stylesheet">
<link href="/resources/css/bootstrap3-wysihtml5.min.css" rel="stylesheet">

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
          <li class="selectd"><a href="/"><span class="fa fa-leaf"></span>&nbsp;Fresh Looks</a></li>
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

        		<!-- dashboard main content page -->
				    <!-- Topic Header -->
				      <div class="topic">
				        <div class="container">
				          <div class="row">
				            <div class="col-sm-6"><h3>Manage Featured Fashionista</h3></div>
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
        
						<form:form modelAttribute="ftrdfashionistaform" method="POST" action="/mnda/ftrd/">
							
							<div class="panel panel-default">
								<div class="panel-body">
								
									<div class="form-group">
									  <div class="col-sm-12"><label>Fashionista Username <span class="asterisk">*</span></label>
									  	<form:input type="text" path="account_username" class="form-control" />
									  </div>
									</div>
									
									<div class="form-group">
									  <div class="col-sm-12">
										<label>Publish as Fatured Fashionista?</label>
										<div  class="well">
											<form:radiobutton path="ftrd_publish_status" value="Yes" /> Yes Publish Now <br/>
											<form:radiobutton path="ftrd_publish_status" value="No"/>  No, I'll publish later <br/>
											
										</div>
									  </div>
									</div>
									
									
									<div class="form-group">
									  <div class="col-sm-12">
										<label>Note from Editor: </label>
										<form:textarea path="note_from_editor" rows="5" class="form-control" />
									  </div>
									</div>	
									
									<div class="form-group">
										<div class="col-sm-12"><label>Youtube video URL1:</label><br/>
											<form:input type="text" path="youtube_video_url1" class="form-control" placeholder="e.g. https://www.youtube.com/watch?v=fScPyyO6mN0" />										</div>
									</div>
																		
									<div class="form-group">
										<div class="col-sm-12"><label>Youtube video URL2:</label><br/>
											<form:input type="text" path="youtube_video_url2" class="form-control" />										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-12"><label>Youtube video URL3:</label><br/>
											<form:input type="text" path="youtube_video_url3" class="form-control" />										</div>
									</div>									
									
									<div class="form-group">
										<div class="col-sm-12"><label>Fashion blog URL1:</label><br/>
											<form:input type="text" path="fashion_blog_url1" class="form-control"/>										</div>
									</div>									
										
								</div>	<!-- panel-body -->
								
								<div class="panel-footer">
									<div class="row">
									  <div class="col-sm-9 col-sm-offset-3">
										<input type="submit" class="btn btn-primary" value="Save" />
										<input type="submit" class="btn btn-default" value="Rest All Fields"/>
									  </div>
									</div>
								</div>
							
							</div><!-- panel -->							
						</form:form>
				   
				
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
<script src="/resources/js/bootstrap-tagsinput.min.js"></script>
<script src="/resources/js/wysihtml5x-toolbar.min.js"></script>
<script src="/resources/js/bootstrap3-wysihtml5.min.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<script>
$("#note_from_editor").wysihtml5({
    "font-styles":  true, //Font styling, e.g. h1, h2, etc
    "color":        true, //Button to change color of font
    "emphasis":     true, //Italics, bold, etc
    "textAlign":    true, //Text align (left, right, center, justify)
    "lists":        true, //(Un)ordered lists, e.g. Bullets, Numbers
    "blockquote":   true, //Button to insert quote
    "link":         true, //Button to insert a link
    "table":        false, //Button to insert a table
    "image":        false, //Button to insert an image
    "video":        false, //Button to insert video
    "html":         false //Button which allows you to edit the generated HTML
});
</script>    
</body>
</html>