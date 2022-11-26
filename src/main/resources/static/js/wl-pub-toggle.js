$('.wl-dub-toggle').click(function(e){
	var self = $('.wl-dub-toggle');
	$.ajax({
		type: 'POST',
		url: "/wl_pub_toggle",
		success: function(response) {
			if(response['validated']){
				if(self.hasClass('active')) self.removeClass('active');
				else self.addClass('active');
				toastr.success("Your changes has ben saved.", '', {timeout: 5000, positionClass: 'toast-bottom-right'});
			}else{
				toastr.error(response['errorMessages'].error, '', {timeout: 5000, positionClass: 'toast-bottom-right'});
			}
		}
	});
});