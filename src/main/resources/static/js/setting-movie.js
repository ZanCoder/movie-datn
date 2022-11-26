$('.expand-movie').click(function(e){
	var type = $(this).attr('data-type');
	
	if(type == 'collapse'){
		$('.we').css('width', '98%');
		$('#my_video').css('height', '650px');
		$('.watch-detail').css('display', 'none');
		$('.watch-center').css('width', '100%');
		$(this).attr('data-type', 'expand');
		$(this).html('<i class="fas fa-compress"></i>&nbsp; Collapse');
	}else{
		$('.we').css('width', '80%');
		$('#my_video').css('height', '510px');
		$('.watch-detail').css('display', 'flex');
		$('.watch-center').css('width', '75%');
		$(this).attr('data-type', 'collapse');
		$(this).html('<i class="fas fa-expand"></i>&nbsp; Expand');
	}
});

$('.turn-light').click(function(e){
	var light = $(this).attr('data-light');
    
    if(light === 'on'){
		$(".turn-light").text("Off");
		$(this).attr('data-light', 'off');
		$(this).css('color', 'red');
		jQuery("#wrapper").append('<div id="light-overlay" style="position: fixed; z-index: 3; background-color: rgb(0, 0, 0); opacity: 1; top: 0px; left: 0px; width: 100%; height: 100%;"></div>');
		jQuery(".watch-center").css({
        "z-index": "4",
        "position": "relative"
    	});
    	$('#header').css('z-index', 3);
	}else{
		$(".turn-light").text("On");
		$(this).attr('data-light', 'on');
		$(this).css('color', '#cae962');
		$('#light-overlay').remove();
		jQuery(".watch-center").css({
        "z-index": "2",
        "position": "relative"
    	});
    	$('#header').css('z-index', 999);
	}
});