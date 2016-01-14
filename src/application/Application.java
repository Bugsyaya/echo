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
package application;

import hautNiveau.ServeurHaut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import basNiveau.ServeurBas;

/**
 * Classe principal de l'application
 */
public class Application
{
	/**
	 * Méthode principal permettant de lancer l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Properties prop = new Properties();
		
		try 
		{
			prop.load(new FileInputStream("config.properties"));
			
			if("haut".equals(prop.getProperty("niveau"))) 
			{
				ServeurHaut serveur = new ServeurHaut();
				serveur.call();
			} 
			else if("bas".equals(prop.getProperty("niveau"))) 
			{
				ServeurBas serveur = new ServeurBas();
				serveur.run();
			} 
			else 
			{
				System.err.println("Propri�t� niveau fausse");
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Fichier config.properties introuvable");
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}
}
