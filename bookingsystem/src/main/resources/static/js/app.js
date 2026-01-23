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

            div.innerHTML = reservations.map(res => {
                const date = formatDate(res.startTime);
                const time = formatTimeRange(res.startTime, res.endTime);

                return `
                    <p>
                        <strong>${date}</strong><br>
                        ${time}<br>
                        <small>Varannut: ${res.nickname ?? "—"}</small><br>
                        <button class="delete-btn" onclick="deleteReservation(${res.id}, ${roomId})">Poista</button>
                    </p>
                `;
            }).join("");
        });
}

function createReservation(roomId) {
    const startTime = document.getElementById("startTime").value;
    const endTime = document.getElementById("endTime").value;
    const nickname = document.getElementById("nickname").value;

    const start = new Date(startTime);
    const end = new Date(endTime);

    if (start.getMinutes() !== 0 || end.getMinutes() !== 0) {
        document.getElementById("result").innerText =
            "Varaus on tehtävä tasatunnein (esim. 16:00–17:00).";
        return;
    }

    const diffHours = (end - start) / (1000 * 60 * 60);
    if (diffHours !== 1) {
        document.getElementById("result").innerText =
            "Varausajan on oltava tasan 1 tunti.";
        return;
    }

    const startIso = start.toISOString();
    const endIso = end.toISOString();

    fetch(`/api/reservations?roomId=${roomId}&startTime=${startIso}&endTime=${endIso}&nickname=${encodeURIComponent(nickname)}`, {
        method: "POST"
    })
        .then(async r => {
            if (r.ok) {
                document.getElementById("result").innerText = "Varaus onnistui!";
                document.getElementById("result").style.color = "#2d3436";

                setTimeout(() => {
                    window.location.href = "/reservations.html";
                }, 1000);
            } else {
                const error = await r.text();
                document.getElementById("result").innerText = error;
                document.getElementById("result").style.color = "#d63031";
            }
        });
}

function deleteReservation(id, roomId) {
    fetch(`/api/reservations/${id}`, { method: "DELETE" })
        .then(() => {
            if (roomId) loadReservations(roomId);
            loadAllReservations();
        });
}

function loadAllReservations() {
    document.querySelector("#roomA tbody").innerHTML = "";
    document.querySelector("#roomB tbody").innerHTML = "";

    fetch("/api/rooms")
        .then(r => r.json())
        .then(rooms => {
            rooms.forEach(room => {
                fetch(`/api/reservations/room/${room.id}`)
                    .then(r => r.json())
                    .then(reservations => {
                        const tbody = document.querySelector(
                            room.name === "Room A" ? "#roomA tbody" : "#roomB tbody"
                        );

                        if (reservations.length === 0) {
                            tbody.innerHTML = `<tr><td colspan="4">Ei varauksia.</td></tr>`;
                            return;
                        }

                        reservations.forEach(res => {
                            const date = formatDate(res.startTime);
                            const time = formatTimeRange(res.startTime, res.endTime);
                            const name = res.nickname ?? "—";

                            tbody.innerHTML += `
                                <tr>
                                    <td>${date}</td>
                                    <td>${time}</td>
                                    <td>${name}</td>
                                    <td>
                                        <button class="delete-btn" onclick="deleteReservation(${res.id}, ${room.id})">
                                            Poista
                                        </button>
                                    </td>
                                </tr>
                            `;
                        });
                    });
            });
        });
}

function formatDate(isoString) {
    const d = new Date(isoString);
    const day = String(d.getDate()).padStart(2, "0");
    const month = String(d.getMonth() + 1).padStart(2, "0");
    const year = d.getFullYear();
    return `${day}.${month}.${year}`;
}

function formatTimeRange(startIso, endIso) {
    const s = new Date(startIso);
    const e = new Date(endIso);

    const sh = String(s.getHours()).padStart(2, "0");
    const sm = String(s.getMinutes()).padStart(2, "0");

    const eh = String(e.getHours()).padStart(2, "0");
    const em = String(e.getMinutes()).padStart(2, "0");

    return `${sh}:${sm} - ${eh}:${em}`;
}


