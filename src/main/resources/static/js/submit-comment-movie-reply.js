$(document).on('submit', '.comment-movie-reply-form', function(e) {
	e.preventDefault();

	var movieEpId = $(this).attr('data-movieEpId');
	var parentId = $(this).attr('data-id');
	var text = $('.cm-input-' + parentId).val();
	var tagName = $(this).attr('data-tagName');
	var spoil = $('.btn-spoil-reply').hasClass('active');

	$.ajax({
		type: 'POST',
		url: "/send_comment_movie",
		data: { movieEpId: movieEpId, parentId: parentId, text: text, tagName: tagName, spoil: spoil },
		success: function(data) {
			$('.loading').css('display', 'none');
			$('.cm-input-' + parentId).val('');
			$('.reply-block').css('display', 'none');
			$('#block-reply-' + parentId).css('display', 'block');
			$('#block-reply-' + parentId).children('.rep-more').children('.cm-btn-show-rep').children('span').text(
				$('#block-reply-' + parentId).children('.rep-more').children('.cm-btn-show-rep').children('span').text().replace(/(\d+)+/g, function(match, number) {
					return parseInt(number) + 1;
				})
			);
			$('.btn-spoil-reply').removeClass('active');
			$('#replies-' + parentId).prepend(data);
			
			var obj = document.getElementById(`cm-time-${parentId}`);
			var date = obj.getAttribute('data-date');
			obj.innerHTML = moment(date).fromNow();
		}
	});
});