const modalEl = document.getElementById('modal');
let modal = new bootstrap.Modal(modalEl);

globalThis.getModal = function(url) {
	fetch(url, {method: 'GET'})
		.then(async response => {
			if (!response.ok) {
				throw new Error('Failed to load modal content');
			}
			if (response.redirected) {
				globalThis.location.href = response.url;
				return;
			}

			// Put loaded HTML into the modal and show
			modalEl.innerHTML = await response.text();
			modal.dispose();
			modal = new bootstrap.Modal(modalEl);
			modal.show();
		})
		.catch(error => {
			console.error(error);
		});
}
globalThis.postModal = function (url, event, form) {
	// Prevent default form submission
	event.preventDefault();

	// Send form data
	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
		},
		body: new URLSearchParams(new FormData(form))
	})
		.then(async response => {
			if (!response.ok) {
				throw new Error('Failed to load modal content');
			}
			if (response.redirected) {
				globalThis.location.href = response.url;
				return;
			}

			// Put loaded HTML into the modal
			modalEl.innerHTML = await response.text();
			modal.dispose();
			modal = new bootstrap.Modal(modalEl);
			modal.show();
		})
		.catch(error => {
			console.error(error);
		});

	// Prevent submitting the form
	return false;
}
