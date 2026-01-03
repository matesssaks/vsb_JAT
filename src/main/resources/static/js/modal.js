document.addEventListener('DOMContentLoaded', () => {
	const modalEl = document.getElementById('modal');
	if (!modalEl) return;

	async function updateModal(url, options = {}) {
		try {
			const response = await fetch(url, options);

			if (!response.ok) throw new Error(`HTTP error status: ${response.status}`);

			if (response.redirected) {
				window.location.href = response.url;
				return;
			}

			modalEl.innerHTML = await response.text();
			bootstrap.Modal.getOrCreateInstance(modalEl).show();
		} catch (error) {
			console.error('Modal error:', error);
		}
	}

	document.addEventListener('click', async (event) => {
		const url = event.target.closest('a[data-modal]')?.getAttribute('href');
		if (url) {
			event.preventDefault();
			await updateModal(url);
		}
	});

	document.addEventListener('submit', async (event) => {
		const form = event.target.closest('form[data-modal]');
		if (form) {
			event.preventDefault();
			const url = form.getAttribute('action') || window.location.href;
			await updateModal(url, {
				method: form.getAttribute('method') || 'POST',
				headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
				body: new URLSearchParams(new FormData(form))
			});
		}
	});
});
