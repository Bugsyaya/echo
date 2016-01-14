package hautNiveau;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Implémentation haut niveau du serveur TCP echo.
 * Renvoie les messages reçus via une connexion ServerSocket.
 * Les threads sont gérés par un ExecutorService.
 * 
 */
public class ServeurHaut
{
	Properties prop = new Properties();
	private ServerSocket connexion;

	/**
	 * Constructeur. Il se charge de lire
	 * le fichier de configuration et d'initialiser
	 * le socket serveur. 
	 */
	public ServeurHaut()
	{
		try {
			// Configuration
			prop.load(new FileInputStream("config.properties"));

			// ouverture du socket
			this.connexion = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
		}
		catch (IOException e)
		{
			System.out.println("Impossible de se connecter :\n" + e.getMessage());
		}
	}

	public void launch()
	{
		// c'est ce thread pool qui s'occupera de lancer les threads clients 
		ExecutorService res = Executors.newFixedThreadPool(Integer.parseInt(prop.getProperty("coMax")));
		
		try 
		{
			while(true)
			{
				Socket client = connexion.accept();

				client.setSoTimeout(Integer.parseInt(prop.getProperty("temps")) * 1000);
				
				System.out.println("Client connecté : " + client);
				
				// On crée le thread client...
				GestionnaireClientHaut handler = new GestionnaireClientHaut(client);
				// et au lieu de le 'start' manuellement, on le délègue au ThreadPool 
				res.submit(handler);
			}		
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			res.shutdown();
		}
	}
}