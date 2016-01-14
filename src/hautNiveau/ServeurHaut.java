package hautNiveau;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurHaut
{
	Properties prop = new Properties();
	private ServerSocket connexion;

	public ServeurHaut()
	{
		try {
			prop.load(new FileInputStream("config.properties"));

			this.connexion = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
		}
		catch (IOException e)
		{
			System.out.println("Impossible de se connecter :\n" + e.getMessage());
		}
	}

	public void call()
	{
		ExecutorService res = Executors.newFixedThreadPool(Integer.parseInt(prop.getProperty("coMax")));
		
		try 
		{
			while(true)
			{
				Socket client = connexion.accept();

				client.setSoTimeout(Integer.parseInt(prop.getProperty("port")) * 1000);
				
				System.out.println("Client connecté : " + client);
				
				GestionnaireClientHaut handler = new GestionnaireClientHaut(client);
				res.submit(handler);
			}		
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			res.shutdown();
		}
	}
}