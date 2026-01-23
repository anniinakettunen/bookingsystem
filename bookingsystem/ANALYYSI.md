Mitä tekoäly teki hyvin?

- Tässä projektissa on käytetty tekoälyinä ChatGBT, Microsoft Copilot ja Google Geminiä. Käytin kolmea eri tekoälyä sen takia, että 
ne kaikki tekevät ja ymmärtävät käyttäjää vähän eri tavalla. Katsoin projektin aikana mikä tekoäly ymmärtää minua parhaiten. 

CHATGBT

- ChatGBT antoi hyvin valmiin tiedostorakenteen ja vähän ylikin pyytämäni. 
- Sain ChatGBT:ltä hyvän pohjan projektille, se ajatteli tietokantataulutkin puolestani. 
- Tekoäly sisällytti tärkeät säännöt: päällekkäisyyksien tarkistus, menneisyysaikojen estäminen ja startTime < endTime -logiikka.

MICROSOFT COPILOT
- ymmärsi ongelmani parhaiten
- ehdotti eri koodiratkaisuja kaikkein tarkiten eli tekoälyn paranneltu koodi tai sen ehdotukset toimivat lähes aina moitteitta tai pienellä muokkauksella 
- kysyin onko aikaisemmasta projektistani hyötyä, jossa tein varausjärjestelmän sekä heitin tekoälylle koodia niin se erotteli reilusti mistä voisi ottaa mallia ja minkä jättää pois



GOOGLE GEMINI

2. Mitä tekoäly teki huonosti?

CHATGBT

- Aluksi ChatGBT yliajatteli projektin laajuutta sekä ehdotti mm. DTO rakennetta, jota en pyytänyt. Tarkensin, että tehdään vain yksinkertainen alku jota voidaan laajentaa tarvittaessa. 
- Sovellus ei ollut suoraan käynnistettävissä ilman lisäkonfiguraatiota.
- koodi, jonka tekoäly antoi ei vastannut odotuksiani ja vaihdoin varhaisessa vaiheessa Microsoft Copilotiin.

MICROSOFT COPILOT
- ehdotti liikaa erilaisia ratkaisuja. 
- ehdotti johonkin kohtaa koodia, mutta ei kertonyt mihin entityyn/controlleriin tai .html hän haluaisi korjatun koodin sijoittaa
- ehdotin esim. keskitettyä ulkoasua, mutta tekoäly silti ehdotti tyyliä vasemmalle pyynnöstäni huolimatta
- tekoäly ehdotti reservationstatusta ja kysyin muutaman kerran mikä se on sekä onko se tarpeellinen. Meidän näkemykset ei ihan täsmännyt. Tekoäly halusi väkisin laittaa sen projektiin. Itse en halunnut sitä, koska varaus joko on olemassa tai ei ole olemassa. 
- jouduin muistuttamaan tehtävänannosta usein tekoälyä
- Microsoft Copilot oli teknisesti tarkin ja hyödyllisin, mutta vaati ohjaamista.”


GOOGLE GEMINI

- Käytin tekoälyä kuvan luomiseen sekä virheiden korjaamiseen
- yleisesti olen huomannut, että Gemini ymmärtää useat asiat kaikkein huonoiten verrattuna ChatGBT tai Copilot
- Tästä minulla on maksullinen versio ja siksi tein sillä kuvan etusivulle.
- Google Gemini soveltui parhaiten yksittäisiin tehtäviin, kuten kuvan luomiseen.

3. Mitkä olivat tärkeimmät parannukset, jotka teit tekoälyn tuottamaan koodiin ja miksi?
- ChatGBT sekoitti endTime ja startTime keskenään ja ei laittanut niitä järjestykseen vaan ensin tuli lopetus ja sitten aloitus. 
- ChatGBT loi todella pitkiä funktioden/atribuuttien jne. nimiä, joita piti lyhentää sekä selkeyttää. 
- eri importit eivät tulleet tekoälyltä, lisäksi se teki oletuksia mm. projektin tiedostojen nimistä 
- tekoälyn turhat kommentit koodin seassa 
- fronendin muokkaaminen oli suurimpia parannuksia, ihan ensimmäisenä indexissä oli vain Varausjärjestelmä karun näköisellä tekstillä. Käytin siihen eniten aikaa, että sain siitä hyväksyttävällä tavalla visuaalisen.

Projektin aikana opin, että tekoälyä kannattaa käyttää apuna, mutta ei koskaan kritiikittömästi.

