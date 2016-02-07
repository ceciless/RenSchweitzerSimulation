package aeroport.entity.Piste;

import simulation.features.SimFeatures;

public class PisteFeatures extends SimFeatures{

	String nom;
	
	public PisteFeatures(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
		this.nom=nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
