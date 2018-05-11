# Social Z web service / client Android 
di **Farhan Latif Gazi** `5^IA`

Il progetto `Social Z web service / client Android` è un'estensione del progetto `Social Z`, e si divide in due parti.

La prima parte `Social Z web service` è una web application che implementa un servizio web `SOAP W3C` che fornisce a tutti i clienti di tale servizio una lista di hobby con i suoi praticanti. Le informazioni, prese dallo stesso database di `Social Z`,  sono rappresentate come un oggetto `JSON`.

La seconda parte del progetto `Social Z Android client` è un'applicazione android che funge da cliente al servizio web prima descritto. L'applicazione usa la libreria `ksoap2` per connettersi al web service. Le istruzioni per la richiesta vengono eseguite dentro un `AsyncTask`.
Le informazioni, una volta ricevute, sono convertite in un oggetto `POJO` tramite l'uso della libreria `GSON`.
Sia gli `Hobbies` che la mailing list dei loro `practitioners` sono rappresentate tramite delle `RecyclerView` in due differenti activities.
