'use strict'

const openButton = document.getElementById('open-menu-btn')
const closeButton = document.getElementById('close-menu-btn')
const mobileMenu = document.getElementById('mobile-menu')

openButton.addEventListener('click', () => {
  mobileMenu.classList.toggle('display-none')
})

closeButton.addEventListener('click', () => {
  mobileMenu.classList.toggle('display-none')
})

const backToTopBtn = document.getElementById("btn-back-to-top");

window.onscroll = function () {
  if (
    document.body.scrollTop > 20 ||
    document.documentElement.scrollTop > 20
  ) {
    backToTopBtn.style.display = "block";
  } else {
    backToTopBtn.style.display = "none";
  }
}

backToTopBtn.addEventListener("click", () => {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
})
