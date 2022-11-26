$('.wl-item').click(function(e){
	var movieId = $(this).attr('data-movieId');
	var type = $(this).attr('data-type');
	
	$.ajax({
		type: 'POST',
		url: "/update_wl",
		data: { movieId: movieId, type: type },
		success: function(response) {
			if(response['validated']){
				toastr.success(response['messages'].message, '', {timeout: 5000, positionClass: 'toast-bottom-right'});
				setTimeout(function(e){
					window.location.reload();
				}, 400);
			}else{
				toastr.error(response['errorMessages'].error, '', {timeout: 5000, positionClass: 'toast-bottom-right'});
			}
		}
	});
});