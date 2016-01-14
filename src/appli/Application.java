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

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

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
		HashMap<String, String> CONFIG = new HashMap<String, String>();
		CONFIG = new HashMap<String, String>();
		CONFIG.put("timeout", "240");
		CONFIG.put("port", "5566");
		CONFIG.put("level", "bas");
        
		try 
		{
			ResourceBundle resources = ResourceBundle.getBundle("appli.config");
		}
		catch(MissingResourceException e) 
		{
			System.out.println(	"Erreur : impossible de trouver un ficher de" +
								"configuration 'config.properties', utilisation" +
								"des paramètres par défaut.");
		}
		
		Serveur serveur = new Serveur(CONFIG);
		serveur.run();
	}
}
