$(function () {
	var timer_qtip = null;
	var xhr = null;
	$('.qtip-movie').hover(
		function(e) {
			var self = $(e.currentTarget);
			var id = self.attr('data-id');
			
			if (timer_qtip) {
				clearTimeout(timer_qtip);
				timer_qtip = null;
			}
			
			timer_qtip = setTimeout(function() {
				xhr = $.ajax({
					type: 'POST',
					url: '/qtip',
					dataType: "html",
					data: {id: id},
					success: function(data) {
						xhr = null;
						
						self.popover({
							trigger: 'hover',
							html: true,
							animation: true,
							container: self,
							placement: 'auto',
							content: data
						}).popover('show');
					}
				});
			}, 1000);
		},
	
		function(e) {
			var self = $(e.currentTarget);
			if (timer_qtip) {
				clearTimeout(timer_qtip);
				timer_qtip = null;
			} else if (xhr) {
				xhr.abort();
				xhr = null;
			} else{
				self.popover('dispose');
			}
		}
	);
});