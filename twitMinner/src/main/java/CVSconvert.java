package main.java;

import twitter4j.Status;

public class CVSconvert {

	public static String print(Status status) 
	{
		// Recupère les mots d'un status qui sont séparés par un espace.
		String[] TabMots = status.getText().split(" "); 
		
		// Convertir le texte en forme CSV
		String finaltext = new String();
		for (int i = 0; i < TabMots.length ; ++i)
			finaltext += "\"" + TabMots[i] + "\";";
		
		// Retrait du dernier ";"
		finaltext = finaltext.substring(0, finaltext.length()-1);
		
		// Eviter l'exception du getCountry() == null
		String country = new String();
		
		if (status.getPlace() != null)
			country = status.getPlace().getCountry();
		
		// Forme du CSV : "Date";"User";"Country";"Text1";"Text2"...
		return "\"" + status.getCreatedAt() + "\";\"@" + status.getUser().getName() + "\";\"" + country + "\";" + finaltext;
	}
}
