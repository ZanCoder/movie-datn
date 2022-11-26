$('.watchContent__main-sidebar-mostView-header a').click(function(e){
	
	var type = $(this).attr('class').split('-').at(-1);
	var self = $(this);
	
	$.ajax({
		type : "POST",
		url : `/change_tab_mostviewed`,
		data : {
			type: type
		},
		success : function(data) {
			$('.mostView-container').html(data);
			$('.watchContent__main-sidebar-mostView-header a').removeClass('active');
			self.addClass('active');
		},
		error: function(data){
			toastr.error('Something went wrong! Please try again later.', '', { timeout: 5000, positionClass: 'toast-bottom-right' });
		}
	});
});