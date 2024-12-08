const BASE_URL = 'http://localhost:8080/api/users'; // Base URL for the backend API

// Handle Create User Form Submission
document.getElementById('createUserForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    // Collect form input values
    const name = document.getElementById('userName').value;
    const email = document.getElementById('userEmail').value;
    const amount = parseFloat(document.getElementById('initialAmount').value);

    // Prepare the payload for the API request
    const userData = {
        name: name,
        email: email,
        balance: amount
    };

    try {
        // Send a POST request to create a new user
        const response = await fetch(BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        // Parse and display the response
        if (response.ok) {
            const result = await response.json();
            document.getElementById('createUserResponse').innerText =
                `User created successfully! ID: ${result.id}, Name: ${result.name}, Email: ${result.email}, Balance: ${result.balance}`;
        } else {
            document.getElementById('createUserResponse').innerText =
                "Failed to create user. Please check your input.";
        }
    } catch (error) {
        document.getElementById('createUserResponse').innerText =
            "Error creating user: " + error.message;
    }
});

// Function to list all users
async function listAllUsers() {
    const response = await fetch(`${BASE_URL}/all`);
    const data = await response.json();
    document.getElementById('userListOutput').textContent = JSON.stringify(data, null, 2);
}

// Function to list active users
async function listActiveUsers() {
    const response = await fetch(BASE_URL);
    const data = await response.json();
    document.getElementById('userListOutput').textContent = JSON.stringify(data, null, 2);
}

// Function to activate a user
async function activateUser() {
    const userId = document.getElementById('activateUserId').value;
    const response = await fetch(`${BASE_URL}/${userId}/activate`, { method: 'PATCH' });
    alert(response.ok ? 'User activated successfully' : 'Failed to activate user');
}

// Function to activate a user
async function deactivateUser() {
    const userId = document.getElementById('deactivateUserId').value;
    const response = await fetch(`${BASE_URL}/${userId}`, { method: 'DELETE' });
    alert(response.ok ? 'User deactivated successfully' : 'Failed to deactivate user');
}

// Function to deposit money
async function depositMoney() {
    const userId = document.getElementById('depositUserId').value;
    const amount = document.getElementById('depositAmount').value;
    const response = await fetch(`${BASE_URL}/${userId}/deposit?amount=${amount}`, { method: 'PATCH' });
    const data = await response.text();
    alert(data);
}

// Function to withdraw money
async function withdrawMoney() {
    const userId = document.getElementById('withdrawUserId').value;
    const amount = document.getElementById('withdrawAmount').value;
    const response = await fetch(`${BASE_URL}/${userId}/withdraw?amount=${amount}`, { method: 'PATCH' });
    const data = await response.text();
    alert(data);
}

// Function to transfer money between users
async function transferMoney() {
    const fromId = document.getElementById('fromUserId').value;
    const toId = document.getElementById('toUserId').value;
    const amount = document.getElementById('transferAmount').value;
    const response = await fetch(`${BASE_URL}/${fromId}/${toId}/transfer?amount=${amount}`, { method: 'PATCH' });
    const data = await response.text();
    alert(data);
}
