package basNiveau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 *
 */
public class GestionnaireClientBas implements Runnable
{
	private Socket client;
	private ServeurBas server;

	GestionnaireClientBas(Socket client, ServeurBas server)
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