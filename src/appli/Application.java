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
		Serveur serveur = new Serveur();
		serveur.run();
	}
}
