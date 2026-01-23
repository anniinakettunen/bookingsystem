KOKOUSHUONEIDEN VARAUSJÄRJESTELMÄN DOKUMENTAATIO

1. Johdanto
   
Tämän dokumentin tarkoituksena on kuvata kokoushuoneiden varausjärjestelmän rakenne, toiminnallisuus ja tekninen toteutus. Järjestelmä on suunniteltu pienimuotoiseen organisaatiokäyttöön, jossa käyttäjät voivat tehdä tuntikohtaisia varauksia kahteen eri kokoushuoneeseen ( A ja B). Järjestelmä koostuu Java Spring Boot backend‑palvelusta sekä HTML/JavaScript‑pohjaisesta käyttöliittymästä.


2. Järjestelmän yleiskuvaus
   
Varausjärjestelmä mahdollistaa seuraavat toiminnot:

uuden varauksen luominen

varausajan validointi (tasan 1 tunti, tasatunnit)

varauslistan tarkastelu huoneittain

varauksen poistaminen

Varaukset voi tunnistaa nimimerkillä.

Backend tarjoaa REST‑rajapinnan, jonka kautta varaukset tallennetaan ja haetaan.

3. Arkkitehtuuri
   
Järjestelmä noudattaa kolmikerrosarkkitehtuuria:

3.1 Esityskerros (Frontend)
HTML‑sivut:

index.html
create-reservation.html
reservations.html

JavaScript:
app.js

CSS‑tyyli:
style.css


3.2 Sovelluslogiikkakerros (Service)
ReservationService.java  
Sisältää varauksen luonti‑ ja poistologiikan sekä validoinnin.

3.3 Tietokerros (Model + Controller)
Reservation.java
ReservationController.java

Tietomalli kuvaa varauksen rakenteen, ja controller tarjoaa REST‑rajapinnan.

4. Tietomalli
   
4.1 Reservation‑Entity
Kenttä	Tyyppi	Kuvaus
id	Long	Yksilöllinen tunniste
roomId	int	Huoneen tunniste (1 = A, 2 = B)
nickname	String	Varaajan nimi
startTime	LocalDateTime	Varauksen alkuaika
endTime	LocalDateTime	Varauksen loppuaika


6. REST‑rajapinta
5.1 POST /api/reservations
Luo uuden varauksen.
Validoinnit:

varauksen tulee alkaa tasatunnilta
varauksen tulee päättyä tasatunnilta
varauksen keston tulee olla täsmälleen 1 tunti

5.2 GET /api/reservations
Palauttaa kaikki varaukset.

5.3 DELETE /api/reservations/{id}
Poistaa varauksen.

6. Käyttöliittymä
6.1 Etusivu (index.html)
Sisältää:

järjestelmän otsikon
kuvan
kaksi toimintopainiketta:

Tee varaus
Varauslista

6.2 Varauslomake (create-reservation.html)
Käyttäjä syöttää:

nimimerkin
huoneen
alkamisajan
päättymisajan
JavaScript suorittaa validoinnin ennen varauksen lähettämistä.

6.3 Varauslista (reservations.html)

Näyttää:

Kokoushuone Neliöjuuri varaukset
Kokoushuone Funktio varaukset
Poista‑painikkeen jokaiselle varaukselle
Taulukot renderöidään JavaScriptin avulla.

7. JavaScript‑logiikka
app.js sisältää seuraavat keskeiset toiminnot:

createReservation(roomId)
lähettää POST‑pyynnön backendille
loadAllReservations()
hakee GET‑pyynnöllä kaikki varaukset
jakaa ne huoneittain
renderöi taulukot
deleteReservation(id)
lähettää DELETE‑pyynnön

JavaScript toimii täysin ilman ulkoisia kirjastoja.

8. Validointi
   
Validointi toteutetaan sekä frontendissä että backendissä:

Frontend:
minuutit = 0

kesto = 1 tunti

Backend:
varmistaa, ettei päällekkäisiä varauksia synny
varmistaa, että data on eheää

9. Tietovirta
    
Käyttäjä täyttää lomakkeen
JavaScript validoi syötteen
POST‑kutsu lähetetään backendille
Backend tallentaa varauksen
Varauslista päivittyy automaattisesti
Poista‑painike lähettää DELETE‑kutsun

10. Järjestelmän vahvuudet
    
yksinkertainen ja selkeä arkkitehtuuri
helppo ylläpitää
ei riippuvuuksia ulkoisiin kirjastoihin
kevyt ja nopea
REST‑rajapinta mahdollistaa jatkokehityksen

11. Jatkokehitysmahdollisuudet
    
käyttäjätilit ja kirjautuminen
päällekkäisten varausten estäminen visuaalisesti
kalenterinäkymä
sähköposti‑ilmoitukset
varauksen muokkaus
mobiilioptimoitu UI
tietokantatuki (esim. PostgreSQL)

12. Yhteenveto
    
Tämä varausjärjestelmä ja tarkoituksenmukainen ratkaisu pienten tilojen hallintaan. Sen arkkitehtuuri on modulaarinen ja helposti laajennettavissa. 
