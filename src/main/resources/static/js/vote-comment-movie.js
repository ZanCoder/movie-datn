$(document).on('click', '.cm-btn-vote', function(e){
    e.stopPropagation();
    e.stopImmediatePropagation();
    
	var type = $(this).attr('data-type');
	var id = $(this).attr('data-id');
	var self = '.cm-btn-' + id;
	
	$.ajax({
        type: 'POST',
        url: "/vote_movie_comment",
        data: { type: type, id: id },
        success: function(response){
			$('.ib-like ' + self).children('.value').text(response['messages'].like == 0 ? '' : ' ' + response['messages'].like);
			$('.ib-dislike ' + self).children('.value').text(response['messages'].dislike == 0 ? '' : ' ' + response['messages'].dislike);
            
            if(type == '1'){
                if($(self, '.ib-like').hasClass('active')){
                    $(self, '.ib-like').removeClass('active');
                }else{
                    $(self, '.ib-like').addClass('active');
                }
                $(self, '.ib-dislike').removeClass('active');
            }else{
                if($(self, '.ib-dislike').hasClass('active')){
                    $(self, '.ib-dislike').removeClass('active');
                }else{
                    $(self, '.ib-dislike').addClass('active');
                }
                $(self, '.ib-like').removeClass('active');
            }
            
			console.log(response);
        }
    });
});