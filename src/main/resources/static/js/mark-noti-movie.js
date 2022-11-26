$('.btn-noti-maar').click(function(){
	$.ajax({
		type : "POST",
		url : `/mark_noti_movie`,
		success : function() {
			window.location.reload();
		}
	});
});