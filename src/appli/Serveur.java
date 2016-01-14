package appli;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur extends Abstract_Serveur
{
	PrintWriter writer = null;

	int MAXCLIENT = 10;
	int countClient = 0;

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

					Client handler = new Client(client);
					handler.start();
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
