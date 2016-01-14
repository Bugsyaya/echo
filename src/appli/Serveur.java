package appli;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur
{
	PrintWriter writer = null;

	int MAXCLIENT = 10;
	int countClient = 0;
	ServerSocket connexion;

	public Serveur()
	{
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
				if (countClient < 11)
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
					client.setSoTimeout(180 * 1000);

					if (client != null)
					{
						System.out.println("Client connecté : " + client);
					}

					switch("haut_niveau")
					{
						case "haut_niveau": 
							ClientHautNiv handlerHautNiv = new ClientHautNiv(client);
							handlerHautNiv.call();
							break;
						default :
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
