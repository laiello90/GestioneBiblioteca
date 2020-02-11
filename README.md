# GestioneBiblioteca
Repository per la tesina del corso di Programmazione Avanzata e Progettazione del Software

# Requisiti del sistema
1. MySQL server
2. Java Virtual Machine

MySQL reperibile sul disco oppure all'indirizzo  http://www.mysql.it/downloads/mysql 
Java reperibile sul disco oppure all'indirizzo  http://www.java.com/it/download/

# Guida all'installazione

Dopo aver installato e configurato MySQL, aprire il prompt dei comandi (MySQL Command Line Client)
e caricare il database con il comando \. \percorsodelfile\Database.sql

A questo punto per eseguire il progetto è sufficiente eseguire il file Biblioteca.jar

Per iniziare ad usare il programma si può accedere con l'amministratore di default:
Username = admin
Password = admin

Note

Per aprire il progetto in Eclipse è necessario aggiungere al progetto le seguenti librerie presenti
nella cartella del progetto come librerie esterne:
itextpdf.jar
mail.jar
mysql-connector-java-5.1.20-bin.jar

# Descrizione

Il progetto in questione prevedeva la creazione di un software per la gestione di una biblioteca in linguaggio JAVA con le seguenti caratteristiche:
•	Gestione dei volumi disponibili all’interno della biblioteca.
•	Gestione degli utenti di diversa tipologia: (Utente, Bibliotecario, Amministratore).
•	Gestione dei prestiti a carico degli utenti.
•	Inserimento e rimozione di libri e utenti dall’archivio.
•	Invio di solleciti di restituzione libri via email da parte dell’amministratore della piattaforma per i prestiti protratti entro una certa durata.
Inoltre era necessario realizzare la base di dati a supporto del software, realizzata con MySQL e connessa al software tramite driver JDBC e la documentazione allegata in UML del progetto che presenta:
•	Diagrammi dei casi d’uso e scenari di utilizzo
•	Diagrammi delle attività
•	Diagrammi di sequenza
•	Diagramma delle classi
•	Diagramma dei Package
