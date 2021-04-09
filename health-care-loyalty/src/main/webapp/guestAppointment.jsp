<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){
    $('#category').on('change', function (){
    	$.ajax({
            url: "/loadDoctor",
            data: {
                "categoryId": $( "#category option:selected" ).val()
            },
            type: "get",
            cache: false,
            success: function (data) {
                if(data) {
                	 var options = '<option value="">-Select doctor-</option>';
                     for (var i = 0; i < data.length; i++) {
                       options += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                     }
                     $("select#doctor").html(options);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('ERROR:' + XMLHttpRequest.status + ', status text: ' + XMLHttpRequest.statusText);
            }
        });
    });
    
    $("#doctor").change(function(){
  	  $("input[name='doctorId']").val($(this).val())
  	});
});

</script>
</head>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-2 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<li class="active"><a href="">Request An Appointment</a></li>
					</ul>
					<div class="payment-adress">
						<a
							class="btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/login" type="submit" name="submit"
							value="adminListing">Back</a>
					</div>
					<!-- </form> -->
					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="review-content-section">
										<form action="/appointment/guest/save" method="POST" onsubmit="return ValidateForm(this);">
												<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>
														<div class="form-group">
															<input name="name" type="text" class="form-control"
																placeholder="Full Name" required>
														</div>
														<div class="form-group">
															<input name="age"
																type="number" class="form-control"
																placeholder="Age" required>
														</div>

														<div class="form-group">
															<input name="email" type="text" class="form-control"
																placeholder="Email" required>
														</div>

														<div class="form-group">
															<textarea class="form-control" rows="5" id="address" placeholder="Address & Pincode"
																name="address"></textarea>
														</div>
														<div class="form-group">
															<div class=" col-md-6">
																	<select name="countryCode" class="form-control">
																		<option value="">Country Code</option>
																		<c:forEach var="options" items="${countryCodeList}"
																			varStatus="status">
																			<option value="${options.countryCode}" ${user.countryCode eq options.countryCode ?'selected':''}>${options.countryCode}</option>
																		</c:forEach>
																	</select>
															</div>
															<div class=" col-md-6">
																<input name="mobile" type="number" class="form-control"
																	pattern="[1-9]{1}[0-9]{9}"
																	title="Enter 10 digit mobile number"
																	placeholder="Mobile No."
																	required>
															</div>
														</div>
													</div>
													
													<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
													<div class="form-group"></div>
														<div class="form-group">
																<select name="category" id="category" class="form-control">
																	<option value="">-Select Category-</option>
																	<c:forEach var="options" items="${categoryList}"
																		varStatus="status">
																		<option value="${options.id}">${options.categoryDesc}</option>
																	</c:forEach>
																</select>
															</div>
															
															<div class="form-group">
																<select name="doctor"  id="doctor"  class="form-control">
																	<option value="">-Select Doctor-</option>
																	<c:forEach var="options" items="${doctorsList}"
																		varStatus="status">
																		 <option value="${options.id}">${options.name}</option>
																	</c:forEach>
																</select>
															</div>
															<input type="hidden" name="doctorId" id="doctorId">
															
															<div class="form-group">
																<input name="appointmentDate" type="date" class="form-control"
																	placeholder="Select"
																	required>
															</div>
															
															<div class="form-group">
																<select name="appointmentTime" id="appointmentTime" class="form-control">
																	<option value="">-Select time-</option>
																	 <optgroup label="Forenoon">
																	<option value="09.45am to 10.30am">09.45am to 10.30am</option>
																	<option value="10.45am to 11.30am">10.45am to 11.30am</option>
																	<option value="11.45am to 12.45pm">11.45am to 12.45pm</option>
																	 </optgroup>
																	 <optgroup label="Afternoon">
																	<option value="02.45pm to 03.30pm">02.45pm to 03.30pm</option>
																	<option value="03.45pm to 04.30pm">03.45pm to 04.30pm</option>
																	<option value="04.45pm to 05.30pm">04.45pm to 05.30pm</option>
																	<option value="05.45pm to 06.30pm">05.45pm to 06.30pm</option>
																	 </optgroup>
																</select>
															</div>
																<div class="form-group">
															<textarea class="form-control" rows="5" id="description" placeholder="Description"
																name="description"></textarea>
														</div>
													</div>

													<button
																class="btn btn-primary waves-effect waves-light col-md-offset-9 col-md-3"
																type="submit">Book Appointment</button>
												</div>
												</form>
											</div>
											

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>

</html>

