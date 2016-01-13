package appli;

import java.io.*;
import java.net.*;
import java.lang.Thread;


public abstract class Abstract_Serveur
{
	
	protected ServerSocket connexion;
	
	public abstract void run();
	
}
