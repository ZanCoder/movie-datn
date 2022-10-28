$(".btn-change-server").click(function() {
	var movie = $(this).attr('data-movie');
	var server = $(this).attr('data-server');
	var ep = $(this).attr('data-ep');
	
	if($(this).hasClass('current-server')) return;
	
	$(".btn-change-server").removeClass("current-server");
	$(this).addClass("current-server")
	
	$.ajax({
		type : "POST",
		url : `/change-server`,
		data : {
			movie: movie,
			server: server,
			ep: ep
		},
		success : function(data) {
			console.log("SUCCESS: ", data);
			$('#my_video').attr('src', server == 1 ? 'https://short.ink/'+data : 'https://slwatch.co/e/'+data );
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
});