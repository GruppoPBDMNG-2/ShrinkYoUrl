### Base Docker Image

    ubuntu:latest
    
### Prerequisiti
- Docker installato
- Porte 8080 e 27017 inoltrate, sia lato host che lato guest, sull'indirizzo 127.0.0.1

### Utilizzo

1. Clonare il repository

   <pre> git clone https://github.com/GruppoPBDMNG-2/ShrinkYoUrl</pre>

2. Entrare nella cartella ShrinkYoUrl
   <pre> cd ShrinkYoUrl </pre>

3. Eseguire il comando build
   <pre> docker build -t gruppo_pbdmng-2/shrinkyourl . </pre>

4. Creare il container utilizzando l'immagine appena creata
   <pre> docker run -d -p 8080:8080 -p 27017:27017 --name=shrinkyourl gruppo_pbdmng-2/shrinkyourl </pre>

5. Eseguire il container
   <pre> docker exec -it shrinkyourl bash </pre>

6. Lanciare uno dei seguenti comandi:
   <pre> ./start </pre>
   se si vuole eseguire il server oppure
   <pre> ./test </pre>
   nel caso in cui si vogliano eseguire soltanto i test

7. Per accedere al client, inserire, all'interno della barra degli indirizzi del proprio browser, il seguente indirizzo:
   <pre> localhost:8080 </pre>
