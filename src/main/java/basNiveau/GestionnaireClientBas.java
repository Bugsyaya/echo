/**
 * Projet Programmation 2 - Licence professionnel SIL en alternance
 * 14 d�cembre 2015 / 15 janvier 2016
 *  
 * @author Cadorel Ma�l - Blin Marina
 * @version 1.0.0
 */

package main.java.basNiveau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Thread pour repr�senter une connexion d'un client
 * au serveur echo. Il impl�mente la t�che de renvoyer
 * au client ses messages.
 */
public class GestionnaireClientBas implements Runnable
{
	private Socket client;
	private ServeurBas server;

	GestionnaireClientBas(Socket client, ServeurBas server)
	{
		this.client = client;
		
		// utile pour comptabiliser les d�connexions
		this.server = server;
	}

	/**
	 * D�finit la t�che assign�e � ce thread.
	 * C'est le point d'entr�e du thread, la 
	 * m�thode appel�e lorsqu'on lance 
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
				System.out.println("[Mot/Phrase � retourner] : " + line);
				
				if (line.trim().equals("\\quit"))
				{
					writer.println("A bient�t !");
					break;
				}
				
				// fonctionnalit� d'echo
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
				// informe le serveur qu'une 'place' s'est lib�r�e
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