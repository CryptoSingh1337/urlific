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
    setupCreateDialog();
  }
})

function setupCreateDialog() {
  const openCreateDialogBtn = document.getElementById('open-create-dialog-btn');
  const closeCreateDialogBtn = document.getElementById('close-create-dialog-btn');
  const createDialog = document.getElementById('create-dialog');

  openCreateDialogBtn.addEventListener('click', () => createDialog.showModal());
  closeCreateDialogBtn.addEventListener('click', () => createDialog.close());
}

function setupEditDialog(link) {
  const openEditDialogBtn = document.getElementById('open-edit-dialog-btn');
  const closeEditDialogBtn = document.getElementById('close-edit-dialog-btn');
  const editDialog = document.getElementById('edit-dialog');
  const editId = document.getElementById('edit-id');
  const editName = document.getElementById('edit-name');
  const editRedirectUrl = document.getElementById('edit-redirect-url');

  link = JSON.parse(link)
  editId.value = link.id;
  editName.value = link.name;
  editRedirectUrl.value = link.redirectUrl;

  openEditDialogBtn.addEventListener('click', () => editDialog.showModal());
  closeEditDialogBtn.addEventListener('click', () => editDialog.close());
}