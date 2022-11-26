(function () {
    var tab = window.location.pathname.split('/')[2];
    switch(tab) {
      case 'profile':
        $('.user-tab').removeClass('active');
        $('.user-tab-profile').addClass('active');
        break;
      case 'continue-watching':
        $('.user-tab').removeClass('active');
        $('.user-tab-continueWatching').addClass('active');
        break;
      case 'watch-list':
        $('.user-tab').removeClass('active');
        $('.user-tab-watchList').addClass('active');

        const urlSearchParams = new URLSearchParams(window.location.search);
        const params = Object.fromEntries(urlSearchParams.entries());

        switch(params.sort){
            case '':
                $('.bhsi-name strong').text('Default');
                break;
            case 'recently-added':
                $('.bhsi-name strong').text('Recently Added');
                break;
            case 'recently-updated':
                $('.bhsi-name strong').text('Recently Updated');
                break;
            case 'score':
                $('.bhsi-name strong').text('Score');
                break;
            case 'a-z':
                $('.bhsi-name strong').text('Name A-Z');
                break;
            case 'released-date':
                $('.bhsi-name strong').text('Released Date');
                break;
            case 'most-watched':
                $('.bhsi-name strong').text('Most Watched');
                break;
        }

        break;
      case 'notification':
        $('.user-tab').removeClass('active');
        $('.user-tab-notification').addClass('active');
        break;
      case 'purchase-history':
        $('.user-tab').removeClass('active');
        $('.user-tab-purchase-history').addClass('active');
        break;
      case 'mal':
    }
})();