$(document).on('submit', '#forgot-form', function(e) {
	e.preventDefault();
	var data = {};

	data['email'] = $("#forgot-email").val();
	data['recaptcha'] = $('#forgot-recaptcha .g-recaptcha-response').val();
	
	$('#forgot-loading').css('display', 'block');
    $('#forgot-error').css('display', 'none');
    $('#forgot-success').css('display', 'none');

	$.ajax({
		type: 'POST',
		url: "/forgot",
		data: { email: data.email, recaptcha: data.recaptcha },
		success: function(response) {
			$('#forgot-loading').css('display', 'none');
			console.log(response);
			if (response['validated'] == true) {
				$('#forgot-success').css('display', 'block');
                $('#forgot-success').text("Reset successfully! Check email for new password");
			}else{
				$('#forgot-error').css('display', 'block');
				$('#forgot-error').text(response['errorMessages'].error);
			}
		}
	});
});