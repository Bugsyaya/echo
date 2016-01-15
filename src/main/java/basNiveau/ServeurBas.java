/**
 * Projet Programmation 2 - Licence professionnel SIL en alternance
 * 14 d�cembre 2015 / 15 janvier 2016
 *  
 * @author Cadorel Ma�l - Blin Marina
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
 * Impl�mentation bas niveau du serveur TCP echo.
 * Renvoie les messages re�us via une connexion ServerSocket.
 * Chaque client connect� est g�r� par un thread serveur.
 * 
 */
public class ServeurBas
{
	// on veut contr�ler le nombre maximum de clients connect�s.
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
	 * Appel�e pour d�marrer le serveur.
	 */
	public void run()
	{
		try
		{
			while (true)
			{
				// socket en �coute
				Socket client = connexion.accept();

				// le writer g�re l'affichage chez le client
				PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
				
				if (getCountClient() < MAXCLIENT)
				{
					addClient();
					writer.println("Vous �tes le " + countClient + " client\n");
					
					// le socket g�re lui-m�me le timeout
					client.setSoTimeout(Integer.parseInt(prop.getProperty("temps")) * 1000);

					System.out.println("Client connect� : " + client);
					
					// Bas niveau : on d�marre juste un thread Client
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
	 * Accesseur de countClient (utile pour contr�ler
	 * le maximum de clients actifs)
	 * @return int
	 */
	public synchronized int getCountClient()
	{
		return countClient;
	}
	
	/**
	 * Incr�mente countClient (utile pour contr�ler
	 * le maximum de clients actifs)
	 */
	public synchronized void addClient()
	{
		countClient ++;
	}
	
	/**
	 * D�cr�mente countClient (utile pour contr�ler
	 * le maximum de clients actifs). Appel� par 
	 * le client lorsqu'il se d�connecte.
	 */
	public synchronized void removeClient()
	{
		countClient --;
	}
}
