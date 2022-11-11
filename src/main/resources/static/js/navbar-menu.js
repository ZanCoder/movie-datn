// Open Menu
function openBtn() {
    document.querySelector('#close-bg').classList.add('active');
    document.querySelector('#close').classList.add('active');
}

// Close Menu
function closeBtn() {
    document.querySelector('#close-bg').classList.remove('active');
    document.querySelector('#close').classList.remove('active');
}

let closeMenu = document.querySelector('.js-closeMenu');
let menuContainer = document.querySelector('.js-closeMenu-container');

if(closeMenu) closeMenu.addEventListener('click', closeBtn);
if(menuContainer){
	menuContainer.addEventListener('click', function(event) {
    	event.stopPropagation();
	});	
}