# SMTP Benchmark Tool

Questo progetto è un'applicazione Java basata su **Spring Boot** progettata per eseguire benchmark prestazionali su servizi SMTP. Il tool permette di misurare l'efficienza dell'invio e analizzare come la dimensione dei messaggi (inclusi allegati) influenzi i tempi di consegna.

## 📋 Funzionalità
* **Autenticazione**: Supporto completo per servizi SMTP con credenziali username/password.
* **Misurazioni**: Calcolo automatico di tempi **minimi, massimi e medi** di delivery.
* **Payload**: Analisi dell'impatto dimensionale del messaggio (corpo testo + allegati Multipart).

## Requisiti Tecnici
* **Java**: 17 o superiore.
* **Build Tool**: Maven

## Installazione e Configurazione

### 1. Configurazione del Server SMTP (smtp4dev)
Per i test in ambiente locale si raccomanda l'uso di **smtp4dev**.

1. **Installazione**:
   ```bash
   winget install smtp4dev

2. **Avvio**:
   ```bash
   winget install smtp4dev

3. **Configurazione smtp4dev**:
   - Accedere alle impostazioni (icona ingranaggio in alto a destra)
   - Nella sezione SMTP Server, disabilitare l'opzione "Allow Any Credentials".
   - Nella sezione Users, aggiungere le credenziali da utilizzare nel form di login dell'app.
