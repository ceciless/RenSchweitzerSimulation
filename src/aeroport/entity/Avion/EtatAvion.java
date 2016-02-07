package aeroport.entity.Avion;

public enum EtatAvion {
	
	AttenteDePisteETaxiway("Attente que les pistes se lib�rent"),
	Approche("Approche de l'a�roport"),
	Atterrissage("Atterrissage"),
	Decollage("D�collage"),
	Roulement("Roulement"),
	NotificationDebut("Notification approche"),
	NotificationFin("Notification fin de manoeuvre"),
	Dechargement("D�chargement des passagers"),
	Embarquement("Embarquement des passagers"),
	Ravitaillement("Pr�paration et ravitaillement de l'avion");
	
	private String etatAvion;
	private EtatAvion(String etat){
		etatAvion = etat;
	}
	
	public String toString(){
		return etatAvion;
	}

}
