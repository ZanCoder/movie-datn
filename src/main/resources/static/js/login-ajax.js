$(document).on('submit', '#login-form', function(e) {
	e.preventDefault();
	var data = {};

	data['email'] = $("#login-email").val();
	data['password'] = $("#login-password").val();
	data['rememberme'] = $("#remember").val();
	data['recaptcha'] = $('#login-recaptcha .g-recaptcha-response').val();
	
	$('#login-loading').css('display', 'block');
    $('#login-error').css('display', 'none');

	$.ajax({
		type: 'POST',
		url: "/j_spring_security_check",
		data: { emailAre: data.email + ',' + data.recaptcha, password: data.password },
		success: function(response) {
			var status = JSON.parse(response);
			$('#login-loading').css('display', 'none');
			
			if (status['success'] == true) {
				console.log('Login successfully!');
                window.location.reload();
			} else {
				$('#login-error').css('display', 'block');
                $('#login-error').text("Something went wrong. Please try again later.");
			}
		}
	});
});