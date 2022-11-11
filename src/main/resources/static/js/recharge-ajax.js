$(document).on('submit', '#recharge-form', function(e) {
	e.preventDefault();
	var data = {};

	data['bank'] = $("#recharge-bank").find(":selected").text();
	data['account'] = $("#recharge-account").val();
	data['coin'] = $('input[name=money]:checked', '#recharge-form').val() == undefined ? 0 : $('input[name=money]:checked', '#recharge-form').val();
	data['recaptcha'] = $('#recharge-recaptcha .g-recaptcha-response').val();
	
	$('#recharge-loading').css('display', 'block');
    $('#recharge-error').css('display', 'none');
    $('#recharge-success').css('display', 'none');

	$.ajax({
		type: 'POST',
		url: "/recharge",
		data: { bank: data.bank, account: data.account, coin: data.coin, recaptcha: data.recaptcha },
		success: function(response) {
			$('#recharge-loading').css('display', 'none');
			if (response['validated'] == true) {
				$('#recharge-success').css('display', 'block');
                $('#recharge-success').text("Recharge successful! Please refresh page.");
			}else{
				$('#recharge-error').css('display', 'block');
				$('#recharge-error').text(response['errorMessages'].error);
			}
		}
	});
});