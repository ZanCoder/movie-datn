$(window).ready(function() {
	if ($('.load-cm-movie').length > 0) {
		$('#comment-movie-loading').css('display', 'block');
		var hT = $('.load-cm-movie').offset().top,
			hH = $('.load-cm-movie').outerHeight(),
			wH = $(window).height(),
			wS = $(this).scrollTop();
		if (wS >= (hT + hH - wH) && (hT >= wS) && (wS + wH >= hT + hH)) {
			var movieEpId = $('.load-cm-movie').attr('data-movieEpId');
			$.ajax({
				type: 'GET',
				url: "/load_comment_movie",
				data: { movieEpId: movieEpId },
				success: function(data) {
					$('#comment-movie-loading').css('display', 'none');
					$('.load-cm-movie').html(data);
				}
			});
			$(window).off("scroll");
		}
	}
});

$(window).scroll(function() {
	if ($('.load-cm-movie').length > 0) {
		$('#comment-movie-loading').css('display', 'block');
		var hT = $('.load-cm-movie').offset().top,
			hH = $('.load-cm-movie').outerHeight(),
			wH = $(window).height(),
			wS = $(this).scrollTop();
		if (wS > (hT + hH - wH) && (hT > wS) && (wS + wH > hT + hH)) {
			var movieEpId = $('.load-cm-movie').attr('data-movieEpId');
			$.ajax({
				type: 'GET',
				url: "/load_comment_movie",
				data: { movieEpId: movieEpId },
				success: function(data) {
					$('#comment-movie-loading').css('display', 'none');
					$('.load-cm-movie').html(data);
				}
			});
			$(window).off("scroll");
		}
	}
});