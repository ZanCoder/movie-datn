$('.nav-more-country').on('click', (e) => {
	let list_country = $('.list-country');
	let list_country_new = '<ul class="list-country">'
	
	$.ajax({
		type : "GET",
		url : `/country-more`,
		contentType: 'application/json',
		success : function(countries) {
			for(country of countries){
				list_country_new += `<li><a href="/country/${country.country_slug}" class="nav-link">${country.country_name}</a></li>`
			}
			list_country_new += '</ul>'
			
			list_country.replaceWith(list_country_new);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
});