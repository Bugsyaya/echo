package appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientHautNiv implements Callable<Object> 
{

	Socket client;

	ClientHautNiv(Socket client)
	{
		this.client = client;
	}

	public Object call()
	{
		PrintWriter writer = null;

		try
		{
			writer = new PrintWriter(client.getOutputStream(), true);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

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
			writer.println("Vous avez été déconnecté !");
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
