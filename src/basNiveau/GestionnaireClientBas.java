package basNiveau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Thread pour représenter une connexion d'un client
 * au serveur echo. Il implémente la tâche de renvoyer
 * au client ses messages.
 */
public class GestionnaireClientBas implements Runnable
{
	private Socket client;
	private ServeurBas server;

	GestionnaireClientBas(Socket client, ServeurBas server)
	{
		this.client = client;
		
		// utile pour comptabiliser les déconnexions
		this.server = server;
	}

	/**
	 * Définit la tâche assignée à ce thread.
	 * C'est le point d'entrée du thread, la 
	 * méthode appelée lorsqu'on lance 
	 * GestionnaireClientBas.start().
	 */
	public void run()
	{
		try
		{
			// chaque gestionnaire de client doit avoir son propre writer
			PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

			writer.println("Entrez '\\quit' pour quitter l'application");

			while (true)
			{
				writer.println("[Vous] : ");
				String line = reader.readLine();
				
				// 'logging' basique sur le serveur
				System.out.println("[Mot/Phrase à retourner] : " + line);
				
				if (line.trim().equals("\\quit"))
				{
					writer.println("A bientôt !");
					break;
				}
				
				// fonctionnalité d'echo
				writer.println("[echo] : " + line);
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				// informe le serveur qu'une 'place' s'est libérée
				server.removeClient();
				client.close();
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}