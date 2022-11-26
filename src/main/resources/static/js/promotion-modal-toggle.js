$('.detail-promotion .item').click(function(e){
	var src = $(this).attr('data-src');
	$('#if-promotion-src').attr('src', src);
});

$('#modalpromotion').on('hidden.bs.modal', function () {
    $('#if-promotion-src').attr('src', '');
});