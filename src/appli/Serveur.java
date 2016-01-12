package appli;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Serveur
{
	public static void main (String[] args)
	{
		try
		{
			ServerSocket server = new ServerSocket(5566);
			while(true)
			{
				Socket client = server.accept();
				Handler handler = new Handler(client);
				handler.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.message());
		}
	}
}
