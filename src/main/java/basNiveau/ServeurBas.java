/**
 * Projet Programmation 2 - Licence professionnel SIL en alternance
 * 14 décembre 2015 / 15 janvier 2016
 *  
 * @author Cadorel Maël - Blin Marina
 * @version 1.0.0
 */

package main.java.basNiveau;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;


/**
 * Implémentation bas niveau du serveur TCP echo.
 * Renvoie les messages reçus via une connexion ServerSocket.
 * Chaque client connecté est géré par un thread serveur.
 * 
 */
public class ServeurBas
{
	// on veut contrôler le nombre maximum de clients connectés.
	private int MAXCLIENT; 
	private int countClient = 0;
	
	Properties prop = new Properties();
	private ServerSocket connexion;

	/**
	 * Constructeur. Il se charge de lire
	 * le fichier de configuration et d'initialiser
	 * le socket serveur. 
	 */
	public ServeurBas()
	{
		try {
			// configuration
			prop.load(new FileInputStream("config.properties"));
			MAXCLIENT = Integer.parseInt(prop.getProperty("coMax"));
			
			// ouverture du socket
			this.connexion = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
		}
		catch (IOException e)
		{
			System.out.println("Impossible de se connecter :\n" + e.getMessage());
		}
	}
	
	/**
	 * Appelée pour démarrer le serveur.
	 */
	public void run()
	{
		try
		{
			while (true)
			{
				// socket en écoute
				Socket client = connexion.accept();

				// le writer gère l'affichage chez le client
				PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
				
				if (getCountClient() < MAXCLIENT)
				{
					addClient();
					writer.println("Vous êtes le " + countClient + " client\n");
					
					// le socket gère lui-même le timeout
					client.setSoTimeout(Integer.parseInt(prop.getProperty("temps")) * 1000);

					System.out.println("Client connecté : " + client);
					
					// Bas niveau : on démarre juste un thread Client
					GestionnaireClientBas handler = new GestionnaireClientBas(client, this);
					Thread thread = new Thread(handler);
					thread.start();
				}
				else
				{
					writer.println("Le nombre de personnes maximum est atteint pour le moment. Veuillez attendre.");
					client.close();
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Accesseur de countClient (utile pour contrôler
	 * le maximum de clients actifs)
	 * @return int
	 */
	public synchronized int getCountClient()
	{
		return countClient;
	}
	
	/**
	 * Incrémente countClient (utile pour contrôler
	 * le maximum de clients actifs)
	 */
	public synchronized void addClient()
	{
		countClient ++;
	}
	
	/**
	 * Décrémente countClient (utile pour contrôler
	 * le maximum de clients actifs). Appelé par 
	 * le client lorsqu'il se déconnecte.
	 */
	public synchronized void removeClient()
	{
		countClient --;
	}
}
