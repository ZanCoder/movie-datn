$(document).on('click', '.link-highlight', function(e) {
    e.stopPropagation();
    var attrStep = $(this).attr('data-show');
    $(this).parents('.tab-pane').hide();
    $(attrStep).show();
});