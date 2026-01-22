
function loadRooms() {
    fetch("/api/rooms")
        .then(r => r.json())
        .then(rooms => {
            const div = document.getElementById("rooms");
            div.innerHTML = rooms.map(room =>
                `<p>
                    <strong>${room.name}</strong>
                    <a href="/reservations.html?roomId=${room.id}">View reservations</a>
                </p>`
            ).join("");
        });
}

function loadReservations(roomId) {
    fetch(`/api/reservations/room/${roomId}`)
        .then(r => r.json())
        .then(reservations => {
            const div = document.getElementById("reservationList");
            if (reservations.length === 0) {
                div.innerHTML = "<p>No reservations.</p>";
                return;
            }

            div.innerHTML = reservations.map(res =>
                `<p>
                    ${res.startTime} → ${res.endTime}
                    <button onclick="deleteReservation(${res.id}, ${roomId})">Delete</button>
                </p>`
            ).join("");
        });
}


function createReservation(roomId) {
    const startTime = document.getElementById("startTime").value;
    const endTime = document.getElementById("endTime").value;

    fetch("/api/reservations?roomId=" + roomId +
        "&startTime=" + startTime +
        "&endTime=" + endTime,
        { method: "POST" })
        .then(async r => {
            const text = await r.text();
            document.getElementById("result").innerText = text;

            if (r.ok) {
                setTimeout(() => {
                    window.location.href = "/reservations.html?roomId=" + roomId;
                }, 1000);
            }
        });
}

// Delete reservation
function deleteReservation(id, roomId) {
    fetch(`/api/reservations/${id}`, { method: "DELETE" })
        .then(() => loadReservations(roomId));
}
