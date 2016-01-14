package appli;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class ServeurBas
{
	private int MAXCLIENT;
	private int countClient = 0;
	
	Properties prop = new Properties();
	private ServerSocket connexion;

	public ServeurBas()
	{
		try {
			prop.load(new FileInputStream("config.properties"));

			MAXCLIENT = Integer.parseInt(prop.getProperty("coMax"));
			this.connexion = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
		}
		catch (IOException e)
		{
			System.out.println("Impossible de se connecter :\n" + e.getMessage());
		}
	}

	public void run()
	{
		try
		{
			while (true)
			{
				Socket client = connexion.accept();
				PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
				
				if (getCountClient() < MAXCLIENT)
				{
					addClient();
					writer.println("Vous êtes le " + countClient + " client\n");
					client.setSoTimeout(Integer.parseInt(prop.getProperty("port")) * 1000);

					System.out.println("Client connecté : " + client);
					
					GestionnaireClientBas handler = new GestionnaireClientBas(client, this);
					handler.start();
				}
				else
				{
					writer.println("Le nombre de personne maximum est atteint pour le moment. Veuillez attendre.");
					client.close();
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public synchronized int getCountClient()
	{
		return countClient;
	}
	
	public synchronized void addClient()
	{
		countClient ++;
	}
	
	public synchronized void removeClient()
	{
		countClient --;
	}
}
