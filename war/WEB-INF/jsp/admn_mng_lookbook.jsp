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
<title>GlamFiesta - Social Network for YouTube Fashionistas and fashion bloggers |
	LOOKBOOK | OOTD | Style Inspiration</title>
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
        
      </nav><!--/nav-->
      
      <div class="col-sm-10">
        
        <div class="row">
          <div class="col-xs-12">

        		<!-- dashboard main content page -->
				    <!-- Topic Header -->
				      <div class="topic">
				        <div class="container">
				          <div class="row">
				            <div class="col-sm-6"><h3>Manage Lookbook</h3></div>
				            <div class="col-sm-6">
				            	<ol class="breadcrumb">
				            		<li><a href="/dashboard"><b><c:out value="${usrbo.account_username }"/>'s Dashboard</b></a></li>
					                <li class="active">Update Profile</li>
					            </ol>
				            </div>
				          </div>
				        </div>
				      </div>  				    
					  <div class="profile-header">
						<h2 class="profile-name">Manage Your Lookbook</h2>
						<div class="profile-location"><i class="fa fa-arrow-right"></i> Update your lookbook information or photos
						</div>
					  </div><!-- profile-header -->

                      <c:if test="${not empty succsmssg }">
						<div class="alert alert-success alert-dismissible" role="alert">
  							<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  							<c:out value="${succsmssg}"/>
						</div>
					  </c:if>
        
						<form:form modelAttribute="lookbookform" method="POST" action="/mnda/lkbk">
							<form:hidden path="lookbookid"/>
							
							<div class="panel panel-default">
								<div class="panel-body">
								
									<div class="form-group">
									  <div class="col-sm-12"><label>Lookbook title <span class="asterisk">*</span></label>
									  	<form:input type="text" path="lookbookname" class="form-control" placeholder="e.g. Bohemian Maxi Skirt to enjoy the summer heat" readonly="readonly" />
									  </div>
									</div>
									
									<div class="form-group">
									  <div class="col-sm-12">
										<label>Is this your look? <span class="asterisk">*</span></label>
										<div  class="well">
											<form:radiobutton path="lookbookme" value="Yes"/>  Yes, this is my look <br/>
											<form:radiobutton path="lookbookme" value="No"/>  No, I am posting someone else's look <br/>
										</div>
									  </div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-6">
											<label>Lookbook Style <span class="asterisk">*</span></label>
											<form:select path="lookbookstyle" class="form-control" multiple="multiple">
												<form:option value="Street Style">Street Style</form:option>
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
												<form:option value="90s Look">90s Look</form:option>											</form:select>
											
										</div>
										<div class="col-sm-6">
											<label>Lookbook Season<span class="asterisk">*</span></label>
											<form:select path="lookbookseason" class="form-control">
												<form:option value="All Season">All Season</form:option>
												<form:option value="Summer">Summer</form:option>
												<form:option value="Fall">Fall</form:option>
												<form:option value="Winter">Winter</form:option>
												<form:option value="Spring">Spring</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-12"><label>Tags <span class="asterisk">*</span></label><br/>
											<form:input type="text" path="tags" class="form-control"  value="Street Style, OOTD, new Outfits" data-role="tagsinput" />
										</div>
									</div>
									
									<div class="form-group">
									  <div class="col-sm-12">
										<label>About the Looks: </label>
										<form:textarea path="lookbookstory" rows="5" placeholder="Let other fashionistas know about your lookbook..." class="form-control" />
										<span class="help-block">Be creative while writing about your lookbook. You can link back to your web site page or blog also.
										However the uniquely you'll write about your lookbook, the more <i class="fa fa-heart text-red"></i> you'll get from our community of 
										fashionistas. You can mention about the inspiration behind trying this outfit, from where others can purchase, an idea of the price 
										and the occasions the outfit matches with. Share some tips to others who also want to try this outfits.  </span>
									  </div>
									</div>	
									
									<div class="form-group">
										<div class="col-sm-12"><label>Youtube Lookbook/OOTD glam video:</label><br/>
											<form:input type="text" path="lookbook_youtube_videourl" class="form-control" placeholder="e.g. https://www.youtube.com/watch?v=fScPyyO6mN0" />										</div>
									</div>
																		
									<div class="form-group">
									  <div class="col-sm-12">
										<label>Publish Lookbook Now?</label>
										<div  class="well">
											<form:radiobutton path="lookbook_publish_status" value="Yes" /> Yes Publish Now <br/>
											<form:radiobutton path="lookbook_publish_status" value="No"/>  No, I'll publish later <br/>
											
										</div>
									  </div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-12">
											<label><h4>Lookbook Photos <a href="/dashboard/lookbook/manage/photos?lookbookid=${lookbook.lookbookid }">Manage Photos</a></h4></label> <br/>
										<!-- div class="well" -->
													<c:if test="${not empty lookbook}">
													
														<c:if test="${not empty lookbook.gcs_photo_url1}">
															<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
																<div class="thumbnail card">
																	<div>
																		<img src="${lookbook.gcs_photo_url1 }" class="img-responsive">
																	</div>
																</div>
															</div>						
														</c:if>
														
														<c:if test="${not empty lookbook.gcs_photo_url2}">
															<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
																<div class="thumbnail card">
																	<div>
																		<img src="${lookbook.gcs_photo_url2 }" class="img-responsive">
																	</div>
																</div>
															</div>						
														</c:if>
														
														<c:if test="${not empty lookbook.gcs_photo_url3}">
															<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
																<div class="thumbnail card">
																	<div>
																		<img src="${lookbook.gcs_photo_url3 }" class="img-responsive">
																	</div>
																</div>
															</div>						
														</c:if>
														
													</c:if>
													
												<!-- /div> // well -->	
											</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-12">
											<label>Tell others Fashionistas where did you buy these outfits from? </label>
											
											<div class="row"><div class="col-sm-12"><label><i>Outfit Type 1</i></label></div></div>
											<div class="row">
												<div class="col-sm-3">
													<form:input type="text" path="outfit_type1" class="form-control" placeholder="Outfit Type e.g. Maxi Skirt"  />
												</div>
												<div class="col-sm-9">
													<form:input type="text" path="outfit_brand1" class="form-control" placeholder="Brand name or URL of the outfit? e.g. http://www.amazon.com/gp/product/B00EMDL9Y2"  />
												</div>
											</div>
											<hr/>
											
											<div class="row"><div class="col-sm-12"><label><i>Outfit Type 2</i></label></div></div>
											<div class="row">
												<div class="col-sm-3">
													<form:input type="text" path="outfit_type2" class="form-control" placeholder="Outfit Type e.g. Maxi Skirt"  />
												</div>
												<div class="col-sm-9">
													<form:input type="text" path="outfit_brand2" class="form-control" placeholder="Brand name or URL of the outfit? e.g. http://www.amazon.com/gp/product/B00EMDL9Y2"  />
												</div>
											</div>
											<hr/>
											
											<div class="row"><div class="col-sm-12"><label><i>Outfit Type 3</i></label></div></div>
											<div class="row">
												<div class="col-sm-3">
													<form:input type="text" path="outfit_type3" class="form-control" placeholder="Outfit Type e.g. Maxi Skirt"  />
												</div>
												<div class="col-sm-9">
													<form:input type="text" path="outfit_brand3" class="form-control" placeholder="Brand name or URL of the outfit? e.g. http://www.amazon.com/gp/product/B00EMDL9Y2"  />
												</div>
											</div>

											<hr/>
											
											<div class="row"><div class="col-sm-12"><label><i>Outfit Type 4</i></label></div></div>
											<div class="row">
												<div class="col-sm-3">
													<form:input type="text" path="outfit_type4" class="form-control" placeholder="Outfit Type e.g. Maxi Skirt"  />
												</div>
												<div class="col-sm-9">
													<form:input type="text" path="outfit_brand4" class="form-control" placeholder="Brand name or URL of the outfit? e.g. http://www.amazon.com/gp/product/B00EMDL9Y2"  />
												</div>
											</div>
											<hr/>
											
											<div class="row"><div class="col-sm-12"><label><i>Outfit Type 5</i></label></div></div>
											<div class="row">
												<div class="col-sm-3">
													<form:input type="text" path="outfit_type5" class="form-control" placeholder="Outfit Type e.g. Maxi Skirt"  />
												</div>
												<div class="col-sm-9">
													<form:input type="text" path="outfit_brand5" class="form-control" placeholder="Brand name or URL of the outfit? e.g. http://www.amazon.com/gp/product/B00EMDL9Y2"  />
												</div>
											</div>
											
											
										</div>
									</div>	
									
									<div class="form-group">
									  <div class="col-sm-12">
										<label>Disapprove this look? <span class="asterisk">*</span></label>
										<div  class="well">
											<form:radiobutton path="disapproved" value="Yes"/>  Yes <br/>
											<form:radiobutton path="disapproved" value="No"/>  No <br/>
										</div>
									  </div>
									</div>	
									
									<div class="form-group">
									  <div class="col-sm-12">
										<label>Disapprove comments: </label>
										<form:textarea path="disapproved_cmmt" rows="5" class="form-control" />
									  </div>
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
$("#disapproved_cmmt").wysihtml5({
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