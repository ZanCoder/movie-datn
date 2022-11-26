$('.change-ep').click(function(e){
	var change;
	var ep = $(this).attr('data-ep');
	var slug = $(this).attr('data-slug');
	var season = $(this).attr('data-season');
	var totalEp = $(this).attr('data-totalEp');
	
	if($(this).hasClass('watch-prev')){
		change = 0;
		if(parseInt(ep) - 1 < 1) return;
		window.location.href = `/watch-movie/${slug}/${season + '-' + (parseInt(ep) - 1)}`;
	} else {
		change = 1;
		if(parseInt(ep) + 1 > totalEp) return;
		window.location.href = `/watch-movie/${slug}/${season + '-' + (parseInt(ep) + 1)}`;
	}

});