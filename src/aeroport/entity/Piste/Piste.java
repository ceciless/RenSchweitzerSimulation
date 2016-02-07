package aeroport.entity.Piste;

import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import enstabretagne.base.messages.Messages;
import aeroport.entity.Piste.IPiste;
import simulation.engine.SimEngine;
import simulation.entity.SimEntity;
import simulation.features.SimFeatures;

public class Piste extends SimEntity implements IPiste, IRecordable {
	
	PisteFeatures pisteFeatures;
	String nom;
	
	public Piste(SimEngine engine, SimFeatures features) {
		super(engine, features);
		this.pisteFeatures= (PisteFeatures)features;
		this.nom=pisteFeatures.getNom();
		this.estLibre=true;
	}

	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		Logger.Information(this, "",Messages.CreationPiste);
	}
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		Logger.Information(this,"",Messages.PisteActive);
		setDisponibilite(true);
	}
	
	boolean estLibre;
	public boolean getDisponibilite(){
		return estLibre;
	}
	public boolean setDisponibilite(boolean estLibre){
		this.estLibre=estLibre;
		return estLibre;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public boolean isEstLibre() {
		return estLibre;
	}


	public void setEstLibre(boolean estLibre) {
		this.estLibre = estLibre;
	}


	@Override
	public String[] getTitles() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] getRecords() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getClassement() {
		// TODO Auto-generated method stub
		return "Piste";
	}
	

}
