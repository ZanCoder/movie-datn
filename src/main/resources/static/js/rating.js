$(".rate-movie").click(".rate-movie", function(e){
	var rate = $(this).attr('data-rate');
	var movieId = $(this).attr('data-movieId');
	var movieRated = $('.movie-rated');
	var totalMovieRated = $('.total-movie-rated');
	var currentRate = $(this);
	
	$('.rate-movie').removeClass('rated');
	$('#rate-loading').css('display', 'block');
	
	$.ajax({
		type: 'POST',
		url: "/rating",
		data: { rate: rate, movieId: movieId },
		success: function(response) {
			$('#rate-loading').css('display', 'none');
			
			if(response['validated']){
				currentRate.addClass('rated');
				
				movieRated.text(response['messages'].movieRated);
				totalMovieRated.text('(' + response['messages'].totalMovieRated + ' voted)');
				
				console.log(response['messages'].movieRated);
				
				toastr.success('Thanks for the vote.', '', {timeout: 5000, positionClass: 'toast-bottom-right'});
			}else{
				toastr.error(response['errorMessages'].error, '', {timeout: 5000, positionClass: 'toast-bottom-right'});
			}
		}
	});
})