$(function() {
	var time = $('.time');
	if (time) {
		time.each(function(i, obj) {
			var date = obj.getAttribute('data-date');
			obj.innerHTML = moment(date).fromNow();
		});
	}
});

$(document).on('click', '.dropdown-menu-emo', function(e) {
	e.stopPropagation();
});

$('emoji-picker').on('emoji-click', function(event) {
	var input = $('.' + $(event.target).attr('data-input'));
	input.val(input.val() + event.originalEvent.detail.unicode);
	input.focus();
});

$(".comment-subject").focus(function() {
	$('.ci-buttons').css('display', 'block');
});

$('.cm-close').on('click', function(e) {
	$(this).closest('#df-cm-buttons').css('display', 'none');
});

$(document).on('click', '.btn-spoil', function(e) {
	if ($(this).hasClass('active')) $(this).removeClass('active');
	else $(this).addClass('active');
});

$(document).on('click', '.show-spoil', function(e) {
	$(this).parent().find('p').css('filter', 'blur(0)');
	$(this).css('display', 'none');
});

$(document).on('click', '.ib-reply', function(e) {
	var id = $(this).attr('data-id');
	$('#reply-' + id).css('display', 'block');
});

$(document).on('click', '.btn-close-reply', function(e) {
	var id = $(this).attr('data-id');
	$('#reply-' + id).css('display', 'none');
});

$(document).on('click', '.cm-btn-show-rep', function(e) {
	var id = $(this).attr('data-id');

	if ($(this).hasClass('active')) {
		$('#replies-' + id).css('display', 'none');
		$(this).children('span').text($(this).children('span').text().replace('Hide', 'View'));
		$(this).children('i').css('transform', 'rotate(0)');
		$(this).removeClass('active');
	} else {
		$('#replies-' + id).css('display', 'block');
		$(this).children('span').text($(this).children('span').text().replace('View', 'Hide'));
		$(this).children('i').css('transform', 'rotate(180deg)');
		$(this).addClass('active');
	}
});

$('.comment-movie-form').submit(function(e) {
	e.preventDefault();
	
	var movieEpId = $('.cmt-movieEp-id').val();
	var text = $('.comment-subject').val();
	var spoil = $('.btn-spoil-base').hasClass('active');

	$.ajax({
		type: 'POST',
		url: "/send_comment_movie",
		data: { movieEpId: movieEpId, text: text, spoil: spoil },
		success: function(data) {
			$('.comment-subject').val('');
			$('.btn-spoil').removeClass('active');
			$('.cw_list').prepend(data);
			
			var id = $('.cw_l-line').first().attr('data-id');
			
			var obj = document.getElementById(`cm-time-${id}`);
			var date = obj.getAttribute('data-date');
			obj.innerHTML = moment(date).fromNow();
		}
	});
});

$(document).on('submit', '.comment-movie-reply-form', function(e) {
	e.preventDefault();
	
	var id = $()
	var movieEpId = $(this).attr('data-movieEpId');
	var parentId = $(this).attr('data-parentId');
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
			$('#replies-' + parentId).append(data);
			
		}
	});
});

$(document).on('submit', '.comment-movie-reply-reply-form', function(e) {
	e.preventDefault();
	
	var id = $()
	var movieEpId = $(this).attr('data-movieEpId');
	var parentId = $(this).attr('data-parentId');
	var replyId = $(this).attr('data-replyId');
	var text = $('.cm-input-' + replyId).val();
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
			$('#replies-' + parentId).append(data);
		}
	});
});

$('#cm-view-more').on('click', function(e){
    const self = $(this);
    const page = parseInt($(this).attr('data-page'));
    const totalPage = parseInt($(this).attr('data-totalPage'));
	const movieEpId = $(this).attr('data-movieEpId');
	
     $.ajax({
        type: 'POST',
        url: "/view_more_movie_comments",
        data: { page: page, movieEpId: movieEpId },
        success: function(response){
			var currentPage = page + 1;
           	$('#cm-view-more').attr('data-page', currentPage);
           	
           	if(currentPage >= totalPage - 1){
                $('#cm-view-more').css('display', 'none');
            }
           	
			$('.cw_list').append(response);
        }
    });
});

function sortAsc(selector, attrName) {
    return $($(selector).toArray().sort(function(a, b){
        var aVal = parseInt(a.getAttribute(attrName)),
            bVal = parseInt(b.getAttribute(attrName));
        return aVal - bVal;
    }));
}
function sortDesc(selector, attrName) {
    return $($(selector).toArray().sort(function(a, b){
        var aVal = parseInt(a.getAttribute(attrName)),
            bVal = parseInt(b.getAttribute(attrName));
        return aVal - bVal;
    }).reverse());
}

$('.cm-sort').on("click", function(e){
	var value = $(this).attr('data-value');
	
	$(".cm-sort").removeClass('active');
	$(".cm-sort i").remove();
	$(this).addClass('active');
	
	if(value == 'newest'){
		$('.cw_l-lineBase').sort(function(a,b) {
		     return Date.parse($(b).attr('data-date')) - Date.parse($(a).attr('data-date'));
		}).appendTo('.cw_list');
		$(this).html('Newest ' + '<i class="fas fa-check ml-2"></i>');
	}else if(value == 'oldest'){
		$('.cw_l-lineBase').sort(function(a,b) {
		     return Date.parse($(a).attr('data-date')) - Date.parse($(b).attr('data-date'));
		}).appendTo('.cw_list');
		$(this).html('Oldest ' + '<i class="fas fa-check ml-2"></i>');
	}else{
		$('.cw_l-lineBase').sort(function(a,b) {
		     return Date.parse($(b).attr('data-vote')) - Date.parse($(a).attr('data-vote'));
		}).appendTo('.cw_list');
		$(this).html('Top ' + '<i class="fas fa-check ml-2"></i>');
	}
});