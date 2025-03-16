# Inlämningsuppgift med Spring boot

## Hur programmet körs?
1. Ställa in environment variabler `MYSQL_USER` och `MYSQL_PASSWORD` enligt din MySQL
2. Kör `src/Main.java`


## Applikations beskrivning

En API-tjänst har skapats som fungerar som en anslagstavla/forum. Användare kan ansluta till kanalen och skicka meddelanden.
REST API fungerar på ett sådant sätt att `/channels/`-endpoint kan hämta alla befintliga kanaler (`GET /channels/`), 
skapa en ny kanal (`POST /channels/`), uppdatera en kanal (`PUT /channels/{id}`) och ta bort (`DELETE /channels/{id}`).
Man kan också skapa nya meddelanden på en kanal (`POST /channels/{id}`, och hämta alla meddelanden på en kanal (`GET /channels/{id}`).

Endpoint `/messages/` kan hämta alla befintliga meddelanden (`GET /messages/`), uppdatera meddelandeinnehållet 
(`PUT /messages/{id}`) och ta bort ett specifikt meddelande (`DELETE /messages/{id}`).

Exempel på hur man går in i Postman

channels: 
```
{
  "name": "first channel"
}
```

messages:
```
{
  "message": "first message"
}
```
