$('.report-movie').click(function(e){
	var movieEpId = $(this).attr('data-movieEpId');
	var movieEpTitle = $(this).attr('data-movieEpTitle');
	var movieTitle = $(this).attr('data-movieTitle');
	var movieEp = $(this).attr('data-movieEp');
	var movieType = $(this).attr('data-movieType');
	
	$('.report-movie-info .report-movie-title').text(movieTitle);
	$('.report-movie-id').val(movieEpId);
	if(parseInt(movieType) == 1){
		$('.report-movie-info .report-movie-ep').text("Episode Full");
	}else{
		$('.report-movie-info .report-movie-ep').text(`Episode ${movieEp}: ${movieEpTitle}`);
	}
});

$('#report-movie-form').submit(function(e){
	e.preventDefault();
	
	var movieEpId = $('.report-movie-id').val();
	var reportMovieValues = $('input[name="report-movie-video"]:checkbox:checked').map(function() {
    	return $(this).next("label").text().split(' ').join(' ');
	}).get().join(', ');
	var reportAudioValues = $('input[name="report-movie-audio"]:checkbox:checked').map(function() {
    	return $(this).next("label").text().split(' ').join(' ');
	}).get().join(', ');
	var reportOther = $('.report-movie-other textarea').val();
	var recaptcha = $('#report-movie-recaptcha .g-recaptcha-response').val();
	
	$('#report-movie-loading').css('display', 'block');
    $('#report-movie-error').css('display', 'none');
    $('#report-movie-success').css('display', 'none');
	
	$.ajax({
		type: 'POST',
		url: "/report_movie",
		data: {movieEpId: movieEpId, reportMovieValues: reportMovieValues, reportAudioValues: reportAudioValues, reportOther: reportOther, recaptcha: recaptcha},
		success: function(response) {
			$('#report-movie-loading').css('display', 'none');
			
			if (response['validated'] == true) {
				$('#report-movie-success').css('display', 'block');
                $('#report-movie-success').text("Thank you for reporting an issue with this video.");
			}else{
				$('#report-movie-error').css('display', 'block');
				$('#report-movie-error').text(response['errorMessages'].error);
			}
		}
	}); 
});