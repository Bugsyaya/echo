<h1>Serveur TCP</h1>

<h2>#Programmation2 #Client/Serveur #Echo #Java #LicencePro</h2>

<h3>I Le cadre</h3>
Lors de notre Licence Pro SIL en Alternance, nous avons eu un module "Programmation 2" nous apprennant Ã  rÃ©aliser une relation Client/Serveur en Haut et Bas niveau.
AprÃ¨s plusieurs cours et TP dans ce modle, nous avons eu Ã  rÃ©aliser un mini projet en binÃ´me.

<h3>II Le projet</h3>
Développer un serveur TCP pour le protocole echo (https://tools.ietf.org/html/rfc862), capable de supporter plusieurs connexions simultanées. Si un client reste inactif pendant une durée supérieure à un paramètre axé, cette connexion est fermée par le serveur. 

Deux implémentations sont à fournir : 
<ul>
<li>l'une utilisant l'API de bas niveau</li>
<li>l'autre utilisant l'API de haut niveau</li>
</ul>

Au démarrage, le serveur lira un fichier de configuration pour déterminer les valeurs des paramètres suivants : 
<ul>
<li>implémentation à utiliser</li>
<li>nombre maximal de connexions simultanées</li>
<li>durée maximale d'inactivité d'une connexion</li>
<li>port d'écoute du serveur</li>

<h3>III Et du coup ? Comment lancer le projet ?</h3>
