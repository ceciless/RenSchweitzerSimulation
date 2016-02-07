package aeroport.entity.Avion;

public enum EtatAvion {
	
	AttenteDePisteETaxiway("Attente que les pistes se libèrent"),
	Approche("Approche de l'aéroport"),
	Atterrissage("Atterrissage"),
	Decollage("Décollage"),
	Roulement("Roulement"),
	NotificationDebut("Notification approche"),
	NotificationFin("Notification fin de manoeuvre"),
	Dechargement("Déchargement des passagers"),
	Embarquement("Embarquement des passagers"),
	Ravitaillement("Préparation et ravitaillement de l'avion");
	
	private String etatAvion;
	private EtatAvion(String etat){
		etatAvion = etat;
	}
	
	public String toString(){
		return etatAvion;
	}

}
