'use strict';

window.addEventListener('load', () => {
  const pathname = window.location.pathname;

  const navigationLinks = document.getElementsByClassName('link');
  for (let i = 0; i < navigationLinks.length; i++) {
    if (pathname === navigationLinks[i].pathname) {
      navigationLinks[i].classList.add('bg-purple-100');
      break;
    }
  }

  if (pathname === '/dashboard') {
    setupDialog();
  }
})

function setupDialog() {
  const openDialogBtn = document.getElementById('openDialogBtn');
  const closeDialogBtn = document.getElementById('closeDialogBtn');
  const dialog = document.getElementById('dialog');

  openDialogBtn.addEventListener('click', () => dialog.showModal());
  closeDialogBtn.addEventListener('click', () => dialog.close());
}