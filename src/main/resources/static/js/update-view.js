/*
var url = new URL(document.URL);
if (url.pathname.includes('watch-movie')) {
	window.focus();
	window.addEventListener('blur', function(e) {
		if (document.activeElement == document.querySelector('#my_video')) {
			var movieId = $('#my_video').attr('data-movieId');
			
			$.ajax({
				type: 'POST',
				url: "/update_view",
				data: { movieId: movieId },
				success: function(response) {
					console.log(response);
				}
			});
			
		}
	}, { once: true });
} 
*/
