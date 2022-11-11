var timer_search;
var xhr_search = null;
$('.header__search-input').keyup((e) => {
	let search = $(e.target).val();
	let header_res = $('.header__search-result .result');
	let loading = $('#search-loading');
	var options = { year: 'numeric', month: 'long', day: 'numeric' };
	header_res.empty();

	if (search === '' || search.trim().length === 0 || !search) return;
	
	loading.css('display', 'block');
	
	timer_search && clearTimeout(timer_search);

	timer_search = setTimeout(function() {
		xhr_search = $.ajax({
			type: "POST",
			url: `/search-movie`,
			data: {
				title: search
			},
			success: function(movies) {
				let results = '';
				let check_more_res = movies.length > 5 ? true : false;
				movies = movies.slice(0, 5);
				
				for (movie of movies) {
					results += `
						<a href="/movie/${movie.slug}/1">
							<div class="header__search-result-items">
								<img src="${movie.poster}"
									alt="" class="header__search-result-items-logo">
								<div class="flex-search">
									<h3 class="header__search-result-items-title">${movie.title}</h3>
									<div class="header__search-result-items-name">${movie.status}</div>
									<div class="header__search-result-items-infor">
										<span>${new Date(movie.release_date).toLocaleDateString("en-US", options)}</span>
										<i class="dot"></i>
										${movie.type}
										<i class="dot"></i>
										<span>${movie.duration_min}</span>
									</div>
								</div>
							</div>
						</a>`
				}
				
				if(check_more_res)
					results += `
							<!-- Views All Result -->
							<div class="header__search-result-viewAll">
								<a href="/search?keyword=${search}" class="header__search-result-viewAll-link">View all results <i
										class="fa-solid fa-chevron-right"></i></a>
							</div>`;

				header_res.css('display', 'block');
				header_res.append(results);
				loading.css("display", "none");
				if(timer_search) clearTimeout(timer_search);
			},
			error: function(e) {
				console.log("ERROR: ", e);
				if(timer_search) clearTimeout(timer_search);
			}
		});
	}, 1000);
});

$(".header__search-input").blur(function(e){
	$(this).val('');
	setTimeout(function(){
		let header_res = $('.header__search-result .result');
	    if(xhr_search){
			xhr_search.abort();
			xhr_search = null;
		}
		header_res.empty();
    }, 150);
});

