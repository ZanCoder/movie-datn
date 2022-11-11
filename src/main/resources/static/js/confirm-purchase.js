$(document).on("click", ".open-purchaseModal", function () {
	 var id = $(this).attr('data-movieId');
     var title = $(this).attr('data-movieTitle');
     var budget = $(this).attr('data-movieBudget');
     
     $("#purchase-form #movie-id").val(id);
     $("#purchase-form .confirm-info .confirm-info-title").text(title);
     $("#purchase-form .confirm-info .confirm-info-coin").text(budget + " coin ");
});

$(document).on('submit', '#purchase-form', function(e) {
	e.preventDefault();
	
	var data = {};
	data['movie_id'] = $("#movie-id").val();
	data['recaptcha'] = $('#purchase-recaptcha .g-recaptcha-response').val();
	
	$('#purchase-loading').css('display', 'block');
    $('#purchase-error').css('display', 'none');

	$.ajax({
		type: 'POST',
		url: "/purchase",
		data: {movie_id: data.movie_id, recaptcha: data.recaptcha},
		success: function(response) {
			$('#purchase-loading').css('display', 'none');
			
			if (response['validated'] == true) {
				window.location.reload();
			}else{
				$('#purchase-error').css('display', 'block');
                $('#purchase-error').text(response['errorMessages'].error);
			}
		}
	});
});