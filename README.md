<h1>Serveur TCP</h1>

<h2>#Programmation2 #Client/Serveur #Echo #Java #LicencePro</h2>

<h3>I Le cadre</h3>
Lors de notre Licence Pro SIL en Alternance, nous avons eu un module "Programmation 2" nous apprennant à réaliser une relation Client/Serveur en Haut et Bas niveau.
Après plusieurs cours et TP dans ce modle, nous avons eu à réaliser un mini projet en binôme.

<h3>II Le projet</h3>
D�velopper un serveur TCP pour le protocole echo (https://tools.ietf.org/html/rfc862), capable de supporter plusieurs connexions simultan�es. Si un client reste inactif pendant une dur�e sup�rieure � un param�tre ax�, cette connexion est ferm�e par le serveur. 

Deux impl�mentations sont � fournir : 
<ul>
<li>l'une utilisant l'API de bas niveau</li>
<li>l'autre utilisant l'API de haut niveau</li>
</ul>

Au d�marrage, le serveur lira un fichier de configuration pour d�terminer les valeurs des param�tres suivants : 
<ul>
<li>impl�mentation � utiliser</li>
<li>nombre maximal de connexions simultan�es</li>
<li>dur�e maximale d'inactivit� d'une connexion</li>
<li>port d'�coute du serveur</li>

<h3>III Et du coup ? Comment lancer le projet ?</h3>
