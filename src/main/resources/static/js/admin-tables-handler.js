/* account table */
$('.file-upload-browse.admin-account').click(function(e) {
	var id = $(this).attr('data-id');
	$(`.file-upload-default#admin-account-avatar-${id}`).trigger('click');
});
$('.file-upload-default').change(function(e) {
	var id = $(this).attr('data-id');
	$(`.file-upload-info.admin-account#${id}`).val(window.URL.createObjectURL(this.files[0]));
});
function submit_account(id, username, email, budget, avatar, status, loading, error, success) {
	$.ajax({
		type: 'POST',
		url: "/edit_admin_account",
		data: { id, username: username, email: email, budget: budget, avatar: avatar, status: status },
		success: function(response) {
			loading.css('display', 'none');
			if (response['validated']) {
				success.text(response['messages'].success);
				success.css('display', 'block');
			} else {
				error.text(response['errorMessages'].error);
				error.css('display', 'block');
			}
		}
	});
}
$('.form-admin-account').submit(function(e) {
	e.preventDefault();
	e.stopPropagation();
	e.stopImmediatePropagation();

	var data = {};
	data['id'] = $(this).children('#admin-account-id').val();
	data['username'] = $(`#admin-account-username-${data['id']}`).val();
	data['email'] = $(`#admin-account-email-${data['id']}`).val();
	data['budget'] = $(`#admin-account-budget-${data['id']}`).val();
	data['avatar'] = $(`.file-upload-info.admin-account#${data['id']}`).val();
	data['status'] = $(`#admin-account-status-${data['id']}`).prop("checked");

	var file = document.getElementById(`admin-account-avatar-${data['id']}`);

	var loading = $(`#admin-account-loading-${data['id']}`);
	var error = $(`#admin-account-error-${data['id']}`);
	var success = $(`#admin-account-success-${data['id']}`);

	loading.css('display', 'block');
	error.css('display', 'none');
	success.css('display', 'none');

	if (file.files.length != 0) {
		var form = new FormData();
		form.append("image", file.files[0]);

		var settings = {
			"url": "https://api.imgbb.com/1/upload?key=dabde36377b251e651dc3b30f63ab681",
			"method": "POST",
			"timeout": 0,
			"processData": false,
			"mimeType": "multipart/form-data",
			"contentType": false,
			"data": form
		};

		$.ajax(settings).done(function(response) {
			var jx = JSON.parse(response);
			data['avatar'] = jx.data.url;
			submit_account(data['id'], data['username'], data['email'],
				 data['budget'], data['avatar'], data['status'], loading, error, success);
		}).fail(function() {
			toastr.error(response['errorMessages'].error, '', { timeout: 5000, positionClass: 'toast-bottom-right' });
			return;
		});
	} else {
		submit_account(data['id'], data['username'], data['email'],
			data['budget'], data['avatar'], data['status'], loading, error, success);
	}
});

/* movie table */
$('.file-upload-browse.admin-movie-poster').click(function(e) {
	var id = $(this).attr('data-id');
	$(`.file-upload-default#admin-movie-poster-${id}`).trigger('click');
});
$('.file-upload-default.admin-movie-poster').change(function(e) {
	var id = $(this).attr('data-id');
	$(`.file-upload-info.admin-movie-poster#poster-${id}`).val(window.URL.createObjectURL(this.files[0]));
});

$('.file-upload-browse.admin-movie-cover').click(function(e) {
	var id = $(this).attr('data-id');
	$(`.file-upload-default#admin-movie-cover-${id}`).trigger('click');
});
$('.file-upload-default.admin-movie-cover').change(function(e) {
	var id = $(this).attr('data-id');
	$(`.file-upload-info.admin-movie-cover#cover-${id}`).val(window.URL.createObjectURL(this.files[0]));
});

function submit_movie(id, title, quality, duration_min, poster,
	cover, description, release_date, productions, vip, budget,
	imdb_rate, trailer, type, status, genres, countries, loading, error, success) {
	$.ajax({
		type: 'POST',
		url: "/edit_admin_movie",
		data: {
			id, title, quality, duration_min, poster,
			cover, description, release_date, productions, vip, budget,
			imdb_rate, trailer, type, status, genres, countries
		},
		success: function(response) {
			loading.css('display', 'none');
			if (response['validated']) {
				success.text(response['messages'].success);
				success.css('display', 'block');
			} else {
				error.text(response['errorMessages'].error);
				error.css('display', 'block');
			}
		}
	});
}

$('.form-admin-movie').submit(function(e) {
	e.preventDefault();
	e.stopPropagation();
	e.stopImmediatePropagation();

	var data = {};
	data['id'] = $(this).children('#admin-movie-id').val();
	data['title'] = $(`#admin-movie-title-${data['id']}`).val();
	data['quality'] = $(`#admin-movie-quality-${data['id']}`).val();
	data['duration_min'] = $(`#admin-movie-duration-${data['id']}`).val();
	data['poster'] = $(`.file-upload-info.admin-movie-poster#poster-${data['id']}`).val();
	data['cover'] = $(`.file-upload-info.admin-movie-cover#cover-${data['id']}`).val();
	data['description'] = $(`#admin-movie-description-${data['id']}`).val();
	data['release_date'] = $(`#admin-movie-release-${data['id']}`).val();
	data['productions'] = $(`#admin-movie-productions-${data['id']}`).val();
	data['budget'] = $(`#admin-movie-budget-${data['id']}`).val();
	data['vip'] = $(`#admin-movie-vip-${data['id']}`).prop("checked");
	data['imdb_rate'] = $(`#admin-movie-imdb-${data['id']}`).val();
	data['trailer'] = $(`#admin-movie-trailer-${data['id']}`).val();
	data['type'] = $(`#admin-movie-type-${data['id']}`).val();
	data['status'] = $(`#admin-movie-status-${data['id']}`).val();
	data['genres'] = []; data['countries'] = [];
	$(`.form-check-genre-${data['id']}:checked`).each(function() { data['genres'].push($(this).val()); });
	$(`.form-check-country-${data['id']}:checked`).each(function() { data['countries'].push($(this).val()); });

	var files = [];
	var requests = [];
	var poster = document.getElementById(`admin-movie-poster-${data['id']}`);
	var cover = document.getElementById(`admin-movie-cover-${data['id']}`);

	if (poster.files.length) {
		var formPoster = new FormData();
		formPoster.append("image", poster.files[0]);
		files.push(formPoster);
		
		requests.push(
			$.ajax({
				url: "https://api.imgbb.com/1/upload?key=dabde36377b251e651dc3b30f63ab681",
				method: "POST",
				timeout: 0,
				processData: false,
				mimeType: "multipart/form-data",
				contentType: false,
				data: formPoster,
				success: function(response) {
					var jx = JSON.parse(response);
					data['poster'] = jx.data.url;
				}
			})
		)
	}

	if (cover.files.length) {
		var formCover = new FormData();
		formCover.append("image", cover.files[0]);
		files.push(formCover);
		
		requests.push(
			$.ajax({
				url: "https://api.imgbb.com/1/upload?key=dabde36377b251e651dc3b30f63ab681",
				method: "POST",
				timeout: 0,
				processData: false,
				mimeType: "multipart/form-data",
				contentType: false,
				data: formCover,
				success: function(response) {
					var jx = JSON.parse(response);
					data['cover'] = jx.data.url;
				}
			})
		)
	}

	var loading = $(`#admin-movie-loading-${data['id']}`);
	var error = $(`#admin-movie-error-${data['id']}`);
	var success = $(`#admin-movie-success-${data['id']}`);

	loading.css('display', 'block');
	error.css('display', 'none');
	success.css('display', 'none');

	if (files.length != 0) {
		$.when.apply($, requests).then(function () {
          submit_movie(data['id'], data['title'], data['quality'], data['duration_min'], data['poster'],
			data['cover'], data['description'], data['release_date'], data['productions'], data['vip'], data['budget'],
			data['imdb_rate'], data['trailer'], data['type'], data['status'], data['genres'].join(','), data['countries'].join(','), loading, error, success);
        });
	} else {
		submit_movie(data['id'], data['title'], data['quality'], data['duration_min'], data['poster'],
			data['cover'], data['description'], data['release_date'], data['productions'], data['vip'], data['budget'],
			data['imdb_rate'], data['trailer'], data['type'], data['status'], data['genres'].join(','), data['countries'].join(','), loading, error, success);
	}
});

$('.form-admin-movie_ep').submit(function(e) {
	e.preventDefault();
	e.stopPropagation();
	e.stopImmediatePropagation();
	
	var id = $(this).children('#admin-movie_ep-id').val();
	var movie = $(`#admin-movie_ep-movie-${id}`).val();
	var title = $(`#admin-movie_ep-title-${id}`).val();
	var ep = $(`#admin-movie_ep-ep-${id}`).val();
	var season = $(`#admin-movie_ep-season-${id}`).val();
	var api_key = $(`#admin-movie_ep-api-${id}`).val();
	
	var loading = $(`#admin-movie_ep-loading-${id}`);
	var error = $(`#admin-movie_ep-error-${id}`);
	var success = $(`#admin-movie_ep-success-${id}`);

	loading.css('display', 'block');
	error.css('display', 'none');
	success.css('display', 'none');
	
	$.ajax({
		type: 'POST',
		url: "/edit_admin_movie_ep",
		data: {
			id, movie, title, ep, season, api_key
		},
		success: function(response) {
			loading.css('display', 'none');
			if (response['validated']) {
				success.text(response['messages'].success);
				success.css('display', 'block');
			} else {
				error.text(response['errorMessages'].error);
				error.css('display', 'block');
			}
		}
	});
});