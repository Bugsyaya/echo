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



/**
 * 
 *
 */
public class Client extends Thread
{
	Socket client;

	Client(Socket client)
	{
		this.client = client;
	}

	public void run()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
			write.println("[type 'bye' to disconnect");

			while(true)
			{
				String line = reader.readLine();
				if (line.trim().equals("bye")) 
				{
					writer.println("bye !");
					break;
				}
				writer.println("[echo] : " + line);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.message());
		}
		finally
		{
			try
			{
				client.close();
			}
			catch (Exception e)
			{
				System.out.println(e.message());
			}
		}
	}
}
