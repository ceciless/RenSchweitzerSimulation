package aeroport.entity.Avion;

import aeroport.entity.Aeroport.Aeroport;
import enstabretagne.base.messages.Messages;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import aeroport.entity.Avion.IAvion;
import aeroport.utils.Converter;
import simulation.engine.SimEngine;
import simulation.entity.SimEntity;
import simulation.event.SimEvent;
import simulation.features.SimFeatures;

public class Avion extends SimEntity implements IAvion, IRecordable{
	
	int numeroAvion;
	double lambdaRoulement;
	String nom;
	Aeroport aeroport;
	AvionFeatures avionFeatures;
	EtatAvion etatAvion;
	LogicalDateTime heureDebutAttente;
	LogicalDateTime time = currentDate(); 
	LogicalDuration dureeAttente;
	boolean estOuvert;

	public int getNumeroAvion(){
		return numeroAvion;
	}
	
	public int setNumeroAvion(int num){
		numeroAvion=num;
		return numeroAvion;
	}
	
	public Avion(SimEngine engine, SimFeatures features ){
		super(engine,features);
		this.avionFeatures = (AvionFeatures)features;
		lambdaRoulement = avionFeatures.getLambdaRoulement();
		this.nom=avionFeatures.getNom();
	}

	public EtatAvion getEtatAvion(){
		return etatAvion;
	}
	
	
	public void approche(){
		Logger.Information(this, "", nom + Messages.Approche);
		post(new FinApproche(),avionFeatures.getDureeApproche());	
	}
	
	class FinApproche extends SimEvent{

		@Override
		public void process() {
			// TODO Auto-generated method stub
			Logger.Information(Avion.this, "", Avion.this.nom+"finit approche");
		}		
	}
	

	public void setEtatAvion(EtatAvion etatAvion){
		Logger.Information(this, "", this.nom+etatAvion);
		if (etatAvion.equals(EtatAvion.AttenteDePisteETaxiway)){
			time=currentDate();
		}
		if (etatAvion.equals(EtatAvion.Approche)) {
			//dureeAttente=LogicalDuration.soustract(time, heureDebutAttente);					
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.Atterrissage)){
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.Roulement)){
			dureeAttente=LogicalDuration.soustract(time, heureDebutAttente);				
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.NotificationFin)){
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.Dechargement)){
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.Ravitaillement)){
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.Embarquement)){
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.NotificationDebut)){
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.Decollage)){
			time=currentDate();

		}
		if (etatAvion.equals(EtatAvion.NotificationFin)){
			time=currentDate();

		}
		
		this.etatAvion=etatAvion;
	}
	
	public boolean notifierAeroportOuvert(){
		estOuvert = true;
		return estOuvert;
	}
	public boolean notifierAeroportFerme(){
		estOuvert = false;
		return estOuvert;
	}

	public LogicalDateTime getHeureDebutAttente() {
		return heureDebutAttente;
	}

	public void setHeureDebutAttente(LogicalDateTime heureDebutAttente) {
		this.heureDebutAttente = heureDebutAttente;
	}

	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public AvionFeatures getAvionFeatures() {
		return avionFeatures;
	}

	public void setAvionFeatures(AvionFeatures avionFeatures) {
		this.avionFeatures = avionFeatures;
	}

	@Override
	public String[] getTitles() {
		// TODO Auto-generated method stub
		String[] titre = { "Attente" };
		return titre;
	}

	@Override
	public String[] getRecords() {
		// TODO Auto-generated method stub
		String[] registres = {Converter.ConvertFromLogicalDurationSecondToDoubleMinute(dureeAttente)};
		return registres;
	}

	@Override
	public String getClassement() {
		// TODO Auto-generated method stub
		return "Avion";
	}

	
}
