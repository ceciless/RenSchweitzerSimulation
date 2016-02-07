package aeroport.entity.Avion;

import enstabretagne.base.time.LogicalDuration;
import simulation.features.SimFeatures;

public class AvionFeatures extends SimFeatures {
	
	double lambdaRoulement;
	LogicalDuration dureeApproche;
	String nom;
	

	public AvionFeatures(String nom,
			double dureeApproche,
			double lambdaRoulement){
		super(nom);
		this.nom=nom;
		this.lambdaRoulement = lambdaRoulement;
		this.dureeApproche=LogicalDuration.ofMinutes((long)dureeApproche);
	}


	public double getLambdaRoulement() {
		return lambdaRoulement;
	}


	public void setLambdaRoulement(double lambdaRoulement) {
		this.lambdaRoulement = lambdaRoulement;
	}


	public LogicalDuration getDureeApproche() {
		return dureeApproche;
	}


	public void setDureeApproche(LogicalDuration dureeApproche) {
		this.dureeApproche = dureeApproche;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}
