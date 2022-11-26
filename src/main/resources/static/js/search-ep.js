$('.search-ep-input').on('keyup', function(e){
    var numberOfEp = $(this).val();
    var eps = $('.eps li').map(function(){
        $(this).removeClass('current-search');
        return $(this).attr('rel');
    });

    if(eps[numberOfEp - 1] == numberOfEp){
        $(`.eps li[rel=${numberOfEp}]`).addClass('current-search');
        if(e.which == 13) {
            $(`.eps li[rel=${numberOfEp}] a`)[0].click();
        }
    }
});