$('.profile-user-avatar').click(function() { $('#profile-avatar-upload').trigger('click'); });

$('#profile-avatar-upload').change(function(e) {
	$('#preview-avatar').attr('src', window.URL.createObjectURL(this.files[0]));
});

function update_profile(username, currentpass, pass, confirm, avatar) {
	$.ajax({
		type: 'POST',
		url: "/update_profile",
		data: { username: username, currentpass: currentpass, pass: pass, confirm: confirm, avatar: avatar },
		success: function(response) {
			$('#profile-loading').css('display', 'none');

			if (response['validated']) {
				$('#pro5-currentpass').val('');
				$('#pro5-pass').val('');
				$('#pro5-confirm').val('');
				console.log(avatar);
				toastr.success('Update profile successfully.', '', { timeout: 5000, positionClass: 'toast-bottom-right' });
			} else {
				toastr.error(response['errorMessages'].error, '', { timeout: 5000, positionClass: 'toast-bottom-right' });
			}
		}
	});
}

$('#profile-form').submit(function(e) {
	e.preventDefault();

	var file = document.getElementById('profile-avatar-upload');
	var username = $('#pro5-name').val();
	var currentpass = $('#pro5-currentpass').val();
	var pass = $('#pro5-pass').val();
	var confirm = $('#pro5-confirm').val();
	var avatar = '';

	$('#profile-loading').css('display', 'block');

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
			avatar = jx.data.url;
			update_profile(username, currentpass, pass, confirm, avatar);
		}).fail(function() {
			toastr.error(response['errorMessages'].error, '', { timeout: 5000, positionClass: 'toast-bottom-right' });
			return;
		});
	} else {
		update_profile(username, currentpass, pass, confirm, avatar);
	}
});