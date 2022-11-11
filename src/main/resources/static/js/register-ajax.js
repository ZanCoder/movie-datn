$(document).on('submit', '#register-form', function(e) {
	e.preventDefault();
	
	var data = {};
	data['username'] = $("#re-username").val();
	data['password'] = $("#re-password").val();
	data['email'] = $("#re-email").val();
	data['confirmPassword'] = $("#re-confirmpassword").val();
	data['recaptcha'] = $('#register-recaptcha .g-recaptcha-response').val();
	
	$('#register-loading').css('display', 'block');
    $('#register-error').css('display', 'none');
    $('#register-success').css('display', 'none');

	$.ajax({
		type: 'POST',
		url: "/register",
		contentType: 'application/json;charset=utf-8',
		data: JSON.stringify(data),
		success: function(response) {
			$('#register-loading').css('display', 'none');

			if (response['validated'] == true) {
				$('#register-success').css('display', 'block');
                $('#register-success').text("Register successfully! Please login.");
			} else {
				$('#register-error').css('display', 'block');
				if(response['errorMessages'].username){
                	$('#register-error').text(response['errorMessages'].username);
            	}else if(response['errorMessages'].email){
					$('#register-error').text(response['errorMessages'].email);
				}else if(response['errorMessages'].password){
					$('#register-error').text(response['errorMessages'].password);
				}else if(response['errorMessages'].confirmPassword){
					$('#register-error').text(response['errorMessages'].confirmPassword);
				}else if(response['errorMessages'].recaptcha){
					$('#register-error').text(response['errorMessages'].recaptcha);
				}else{
					$('#register-error').text("Something went wrong. Please try again later.");
				}
			}
		}
	});
});