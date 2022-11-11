// Recharge
const rechargeBtn = document.querySelectorAll('.js-modal-rechargeBtn')
const modalRecharge = document.querySelector('.js-modal-recharge')
const modalRechargeContainer = document.querySelector('.js-modal-container-recharge')
const modalRechargeClose = document.querySelector('.js-modal-close-recharge')

function showBuyTickets() {
    modalRecharge.classList.add('open')
}

function hideBuyTickets() {
    modalRecharge.classList.remove('open')
}

for (const rechargeBtns of rechargeBtn) {
    rechargeBtns.addEventListener('click', showBuyTickets)
}

modalRechargeClose.addEventListener('click', hideBuyTickets)
modalRecharge.addEventListener('click', hideBuyTickets)
modalRechargeContainer.addEventListener('click', function (event) {
    event.stopPropagation()
})