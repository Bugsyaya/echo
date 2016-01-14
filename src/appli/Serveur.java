package appli;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Serveur
{

	int MAXCLIENT = 10;
	int countClient = 0;
	final HashMap<String, String> CONFIG;
	ServerSocket connexion;
	PrintWriter writer = null;

	public Serveur(HashMap<String, String> CONFIG)
	{
		this.CONFIG = CONFIG;
		
		try
		{
			this.connexion = new ServerSocket(5566);
		}
		catch (IOException e)
		{
			System.out.println("Impossible de se connecter :\n"
					+ e.getMessage());
		}
	}

	public void run()
	{

		try
		{
			while (true)
			{
				if (countClient < Integer.valueOf(CONFIG.getOrDefault("maxclient", "10")))
				{
					Socket client = connexion.accept();

					try
					{
						writer = new PrintWriter(client.getOutputStream(), true);
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}

					countClient++;
					writer.println("Vous êtes le " + countClient + " client\n");
					client.setSoTimeout(1000 * Integer.valueOf(CONFIG.getOrDefault("timeout", "240")));

					if (client != null)
					{
						System.out.println("Client connecté : " + client);
					}

					switch(CONFIG.getOrDefault("level", "bas"))
					{
						case "haut": 
							ClientHautNiv handlerHautNiv = new ClientHautNiv(client);
							handlerHautNiv.call();
							break;
						default:
							ClientBasNiv handlerBasNiv = new ClientBasNiv(client);
							handlerBasNiv.start();
							break;
					}
				}
				else
				{
					writer.println("Le nombre de personne maximum est atteint pour le moment. Veuillez attendre.");
					break;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
