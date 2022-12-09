$('.lbl-autorize-role').click(function(e){
	e.stopPropagation();
    e.stopImmediatePropagation();
    
	var id = $(this).attr('data-id');
	var value = $(this).attr('data-value');
	var input = $(this).attr('for');
	
	$.ajax({
		type : "POST",
		url : `/set_authorize`,
		data : {
			id: id, value: value
		},
		success : function(data) {
			$(`#${input}-${id}`).prop("checked", true);
			toastr.success('Update authorize successfully.', '', { timeout: 5000, positionClass: 'toast-bottom-right' });
		},
		error: function(data){
			toastr.error('Something went wrong! Please try again later.', '', { timeout: 5000, positionClass: 'toast-bottom-right' });
		}
	});
});