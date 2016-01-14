/**
 * Projet Programmation 2 - Licence professionnel SIL en alternance
 * 14 décembre 2015 / 15 janvier 2016
 *  
 * @author Cadorel Maël - Blin Marina
 * @version 1.0.0
 */

/**
 * Localisation de la classe
 */
package appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 *
 */
public class GestionnaireClientHaut extends Thread
{
	private Socket client;
	private ServeurHaut server;

	GestionnaireClientHaut(Socket client, ServeurHaut server)
	{
		this.client = client;
		this.server = server;
	}

	public void run()
	{
		try
		{
			PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

			writer.println("Entrez '\\quit' pour quitter l'application");

			while (true)
			{
				writer.println("[Vous] : ");
				String line = reader.readLine();
				System.out.println("[Mot/Phrase à retourner] : " + line);
				if (line.trim().equals("\\quit"))
				{
					writer.println("A bientôt !");
					break;
				}
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