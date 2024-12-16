document.getElementById("registerForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        nama: document.getElementById("nama").value,
        roles: document.getElementById("roles").value
    };

    try {
        const response = await fetch('/register/newUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        if (response.ok) {
            const data = await response.json();
            alert(`Registrasi berhasil! Nomor Rekam Medis Anda: ${data.noRekamMedis}`);
        } else {
            alert('Registrasi gagal!');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Terjadi kesalahan.');
    }
});
