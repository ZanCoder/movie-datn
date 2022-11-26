$('.remove-cw').click(function(e){
	var movieId = $(this).attr('data-movieId');
	var flexMovie = $(this).parent().parent();
	
	$.ajax({
		type: 'POST',
		url: "/remove_cw",
		data: { movieId: movieId },
		success: function(response) {
			if (response['validated']) {
				flexMovie.hide(500,function(){
			        flexMovie.remove();
				});
				toastr.success('Remove movie from Continue Watching success.', '', { timeout: 5000, positionClass: 'toast-bottom-right' });
				setTimeout(function(e){
					window.location.reload();
				}, 1000);
			} else {
				toastr.error(response['errorMessages'].error, '', { timeout: 5000, positionClass: 'toast-bottom-right' });
			}
		}
	});
});