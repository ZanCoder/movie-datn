$("#my_video").on('load', function (){
    var movieId = $(this).attr('data-movieId');
    var movieEp = $(this).attr('data-movieEp');
    var movieSeason = $(this).attr('data-movieSeason');
    
	$.ajax({
		type: 'POST',
		url: "/update_view",
		data: { movieId: movieId, movieEp: movieEp, movieSeason: movieSeason },
		success: function(response) {
			console.log(response);
		}
	});
});

$("#my_video").ready(function (){
	 $('.loading-movie').css('display', 'none');
});