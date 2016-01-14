<h1>Serveur TCP</h1>

<h2>#Programmation2 #Client/Serveur #Echo #Java #LicencePro</h2>

<h3>I Le cadre</h3>
Lors de notre Licence Pro SIL en Alternance, nous avons eu un module "Programmation 2" introduisant à la programmation concurrente. Nous avons appris à gérer des contextes d'exécution en Java, à la fois en bas niveau et en haut niveau (API java.util.concurrent).
Après plusieurs cours et TP dans ce module, nous avons eu à réaliser ce projet.

<h3>II Le projet</h3>
Développer un serveur TCP pour le protocole echo (https://tools.ietf.org/html/rfc862), capable de supporter plusieurs connexions simultanées. Si un client reste inactif pendant une durée supérieure à un paramètre axé, cette connexion est fermée par le serveur.

Deux implémentations sont à fournir :

<ul>
	<li>l'une utilisant l'API de bas niveau</li>
	<li>l'autre utilisant l'API de haut niveau.</li>
</ul>

Au démarrage, le serveur lira un fichier de configuration pour déterminer les valeurs des paramètres suivants :

<ul>
	<li>implémentation à utiliser</li>
	<li>nombre maximal de connexions simultanées</li>
	<li>durée maximale d'inactivité d'une connexion</li>
	<li>port d'écoute du serveur</li>
</ul>

<h3>III Réalisation</h3>
L'application est développée en JavaSE1.7.
Nous avons travaillé sur Eclipse et Vim, et nous avons versionné notre projet avec Git. Nous avons simulé des connexions client avec Telnet, en ligne de commande avec Bash (sous Linux)  et Putty (sous Windows).

<h3>IV Et du coup ? Comment lancer le projet ?</h3>
Depuis un terminal équipé d'une JVM supportant 1.7, il suffit de lancer
	`java -jar echo.jar`.
Les clients peuvent s'y connecter via un
	`telnet <ip du serveur> <port utilisé>`
