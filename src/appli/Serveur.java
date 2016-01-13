package appli;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Serveur extends Abstract_Serveur
{
	
	public Serveur() {
		try {
			this.connexion = new ServerSocket(5566);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run ()
	{
		try
		{
			while(true)
			{
				Socket client = connexion.accept();
				Client handler = new Client(client);
				handler.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
