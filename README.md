# Rentmanager Java

Ce repository représente le projet Java de Lucas BARREAU élève en MIN2 à l'EPF en 4ème année.

Il a été développé en JEE puis en utilisant le framework SpringBoot et tourne en local grâce à Tomcat7.

## Réalisation
Le projet et les fonctionnalités attendues ont été réalisée en y ajoutant quelques éléments supplémentaires.
La fonction de visualisation des détails d'un réservation n'a pas été effectuée car elle ne m'a pas semblée essentielle.
L'ensemble des éléments relatifs à ces dernières sont affichés dans la liste.

Les tests avec JUnit n'ont pas été réalisé à la demande des professeurs.

## Installation
Pour le tester dans IntelliJ, clonez le repo :

```bash
git clone https://github.com/PatLaCisay/Projet_MIN_Java
```

Exécutez par la suite les commandes suivantes afin de remplir la BDD de test :

```bash
cd src/main/java/com/epf/rentmanager/persistence
javac FillDatabase.java
java FillDatabase.java
```
Un message de succès s'affiche dans le terminal, vous pouvez maintenant lancer le serveur.
```bash
mvn tomcat7:run
```
> :warning: Si vous utilisez IntelliJ sans avoir installé *mvn* sur votre machine pressez **ctrl** + **enter** pour
que le serveur se lance.

RDV @ http://localhost:8080/rentmanager/home