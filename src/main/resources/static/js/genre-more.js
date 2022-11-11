$('.nav-more-genre').on('click', (e) => {
	let list_genre = $('.list-genre');
	let list_genre_new = '<ul class="list-genre">'
	
	$.ajax({
		type : "GET",
		url : `/genre-more`,
		contentType: 'application/json',
		success : function(genres) {
			for(genre of genres){
				list_genre_new += `<li><a href="/genre/${genre.genre_slug}" class="nav-link">${genre.genre_name}</a></li>`
			}
			list_genre_new += '</ul>'
			
			list_genre.replaceWith(list_genre_new);
			
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
});