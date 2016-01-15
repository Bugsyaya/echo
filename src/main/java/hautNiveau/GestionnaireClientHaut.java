/**
 * Projet Programmation 2 - Licence professionnel SIL en alternance
 * 14 d�cembre 2015 / 15 janvier 2016
 *  
 * @author Cadorel Ma�l - Blin Marina
 * @version 1.0.0
 */

package main.java.hautNiveau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class GestionnaireClientHaut implements Callable<Object> 
{

	private Socket client;

	GestionnaireClientHaut(Socket client)
	{
		this.client = client;
	}

	public Object call()
	{
		try
		{
			PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			writer.println("Entrez '\\quit' pour quitter l'application");

			while (true)
			{
				writer.println("[Vous] : ");
				String line = reader.readLine();
				System.out.println("[Mot/Phrase � retourner] : " + line);
				if (line.trim().equals("\\quit"))
				{
					writer.println("A bient�t !");
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
				client.close();
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
}