# SMTP Benchmark Tool

Tool progettato per eseguire benchmark prestazionali su servizi SMTP. Esso consiste nell'inviare N messaggi ad un servizio SMTP per calcolarne i tempi minimi, massimi e medi di recezione da parte del server, sulla base di fattori come il numero di messaggi inviati e la grandezza (in byte) del corpo del messaggio.

## Funzionalità
* **Autenticazione**: Supporto completo per servizi SMTP con credenziali username/password
* **Misurazioni**: Calcolo automatico di tempi **minimi, massimi e medi** di consegna e ricezione
* **Payload**: Analisi dell'impatto dimensionale del messaggio (corpo testo + allegati)

## Tecnologie
* **Java**: 17 o superiore
* Maven

## Installazione e Configurazione

### 1. Configurazione del Server SMTP (smtp4dev)
Per i test in ambiente locale si raccomanda l'uso di **smtp4dev**

1. **Installazione**:
   ```bash
   winget install smtp4dev

2. **Avvio**:
   ```bash
   Rnwood.Smtp4dev

3. **Configurazione autenticazione**:

   - Accedere alle impostazioni (icona ingranaggio in alto a destra)
   - Nella sezione SMTP Server, disabilitare l'opzione "Allow Any Credentials"
   - Nella sezione Users, aggiungere le credenziali da utilizzare nel form di login dell'app
