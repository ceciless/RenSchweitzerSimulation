/**
* Classe Aeroport.java
*@author Ren Sisi - Schweitzer Marie-Alice
*@version 1.0.
*/

package aeroport.entity.Aeroport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import aeroport.entity.Avion.Avion;
import aeroport.entity.Avion.EtatAvion;
import aeroport.entity.Gate.Gate;
import aeroport.entity.Gate.GateFeatures;
import aeroport.entity.Piste.Piste;
import aeroport.entity.Piste.PisteFeatures;
import aeroport.entity.Taxiway.Taxiway;
import aeroport.entity.Taxiway.TaxiwayFeatures;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import aeroport.entity.Aeroport.IAeroport;
import enstabretagne.base.messages.Messages;
import simulation.engine.SimEngine;
import simulation.entity.SimEntity;
import simulation.event.SimEvent;
import simulation.features.SimFeatures;

public class Aeroport extends SimEntity implements IAeroport,IRecordable{

	LogicalDuration ouvertureAeroport;
	LogicalDuration fermetureAeroport;
	boolean estOuvert;
	PisteFeatures pisteFeatures;
	
	LinkedList<Taxiway> listeTaxiway;
	LinkedList<Gate> listeGate;
	Piste piste;
	
	AeroportFeatures aeroportFeatures;
	LinkedList<Avion> nbAvionAttente;
	
	LinkedList<Avion> listeAvionAttentePisteEtTaxiway;
	LinkedList<Avion> listeAvionAttenteGate;
	LinkedList<Avion> listeAvionAttenteTaxiway;
	LinkedList<Avion> listeAvionAttentePiste;
	
	int nbAvionEnRetard=0;
	
	public boolean EstOuvert() {
		return estOuvert;
	}
	
	public Aeroport(SimEngine engine,SimFeatures features) {
		super(engine,features);
		this.aeroportFeatures = (AeroportFeatures) features;
	}
	
	@Override
	public void initialize(){
		super.initialize();
		Logger.Information(this,"",Messages.CreationAeroport);
		listeGate = new LinkedList<Gate>();
		listeTaxiway = new LinkedList<Taxiway>();
		nbAvionAttente = new LinkedList<Avion>();
	}
	
	@Override
	public void activate(){
		super.activate();
		Logger.Information(this,"",Messages.AeroportActive);
		piste = new Piste(getEngine(), aeroportFeatures.getPisteFeatures());
		piste.initialize();
		piste.activate();
		for (GateFeatures gateFeatures: aeroportFeatures.getListGateFeatures()){
			Gate gate = new Gate(getEngine(), gateFeatures);
			gate.initialize();
			gate.activate();
			listeGate.add(gate);
		}
		for(TaxiwayFeatures taxiwayFeatures: this.aeroportFeatures.getListTaxiwayFeatures()){
			Taxiway taxiway = new Taxiway(getEngine(),taxiwayFeatures);
			taxiway.initialize();
			taxiway.activate();
			listeTaxiway.add(taxiway);
		}
	}

	/*
	 * Lorsque l'avion approche de l'aeroport
	 */
	public void notificationVolArrive(Avion a){
		for (Taxiway taxiway : listeTaxiway){
			if (taxiway.getNumero() == 1){
				if(piste.getDisponibilite()==false || taxiway.isEstLibre()==false){
					System.out.println("a");
					a.setEtatAvion(EtatAvion.AttenteDePisteETaxiway);
					ajoutAvionListeAvionAttentePisteEtTaxiway(a);
				}
				else {
					a.setEtatAvion(EtatAvion.Approche);
					a.approche();
					piste.setDisponibilite(false);
					taxiway.setEstLibre(false);
				}
			}
		}		
	}
	
	public void Atterrissage(Avion a){
		a.setEtatAvion(EtatAvion.Atterrissage);

	}
	
	public void Roulement(Avion a){
		piste.setDisponibilite(true);
		for  (Taxiway taxiway : listeTaxiway){
			if (taxiway.getNumero() == 1){
				taxiway.setEstLibre(false);
			}
		}
		a.setEtatAvion(EtatAvion.Roulement);		
	}
	
	public void NotificationFin(Avion a){
		for  (Taxiway taxiway : listeTaxiway){
			if (taxiway.getNumero() == 1){
				taxiway.setEstLibre(true);
			}
		}
		for (Gate gate : listeGate){
			if (gate.getDisponibilite()==true){
				Gate gateUtilise = gate;
				gateUtilise.setDisponibilite(false);	
			}
			
		}
		a.setEtatAvion(EtatAvion.NotificationFin);
	}
		
		
	public void Manoeuvres(Avion a){	
		a.setEtatAvion(EtatAvion.Dechargement);
		a.setEtatAvion(EtatAvion.Ravitaillement);
		a.setEtatAvion(EtatAvion.Embarquement);
		a.setEtatAvion(EtatAvion.NotificationDebut);
	}

	/*
	public void Depart(Avion a){
		gateUtilise.setDisponibilite = true;
		for (Taxiway taxiway : listeTaxiway){
			if (taxiway.getNumero() == 2){
				if(piste.getDisponibilite()==false || taxiway.getDisponibilite()==false){
					a.setEtatAvion(EtatAvion.Attente);
					nbAvionEnRetard++;
				}
				else {
					gate.setDisponibilite(true);
					taxiway.setDisponibilite(false);

		a.setEtatAvion(EtatAvion.Roulement); }
		taxiway.setDisponibilite(true);
		piste.setDisponibilite(false);
		a.setEtatAvion(EtatAvion.Decollage);
		piste.setDisponibilite(true);
		a.setEtatAvion(EtatAvion.NotificationFin);
			}}
	}
	*/
	
	public void ajoutAvionListeAvionAttentePisteEtTaxiway (Avion a){
		Logger.Information(this, "",a.getNom()+"  est en attente d'une piste et d'un taxiway");
		listeAvionAttentePisteEtTaxiway.add(a);
	}
	
	public void ajoutAvionListeAvionAttenteGate(Avion a){
		listeAvionAttenteGate.add(a);
	}
	public void ajoutAvionListeAvionAttenteTaxiway(Avion a){
		listeAvionAttenteTaxiway.add(a);
	}
	public void ajoutAvionListeAvionAttentePiste(Avion a){
		listeAvionAttentePiste.add(a);
	}
	
	public void ajoutTaxiway(Taxiway taxiway){
		this.listeTaxiway.add(taxiway);
	}
	public void ajoutGate(Gate gate){
		this.listeGate.add(gate);
	}
	
	public LogicalDuration getOuvertureAeroport() {
		return ouvertureAeroport;
	}

	public void setOuvertureAeroport(LogicalDuration ouvertureAeroport) {
		this.ouvertureAeroport = ouvertureAeroport;
	}

	public LogicalDuration getFermetureAeroport() {
		return fermetureAeroport;
	}

	public void setFermetureAeroport(LogicalDuration fermetureAeroport) {
		this.fermetureAeroport = fermetureAeroport;
	}

	public int getNbAvionEnRetard() {
		return nbAvionEnRetard;
	}

	public void setNbAvionEnRetard(int nbAvionEnRetard) {
		this.nbAvionEnRetard = nbAvionEnRetard;
	}

	public LinkedList<Avion> getListeAvionAttentePisteEtTaxiway() {
		return listeAvionAttentePisteEtTaxiway;
	}

	public void setListeAvionAttentePisteEtTaxiway(LinkedList<Avion> listeAvionAttentePisteEtTaxiway) {
		this.listeAvionAttentePisteEtTaxiway = listeAvionAttentePisteEtTaxiway;
	}

	public LinkedList<Avion> getListeAvionAttenteGate() {
		return listeAvionAttenteGate;
	}

	public void setListeAvionAttenteGate(LinkedList<Avion> listeAvionAttenteGate) {
		this.listeAvionAttenteGate = listeAvionAttenteGate;
	}

	public LinkedList<Avion> getListeAvionAttenteTaxiway() {
		return listeAvionAttenteTaxiway;
	}

	public void setListeAvionAttenteTaxiway(LinkedList<Avion> listeAvionAttenteTaxiway) {
		this.listeAvionAttenteTaxiway = listeAvionAttenteTaxiway;
	}

	public LinkedList<Avion> getListeAvionAttentePiste() {
		return listeAvionAttentePiste;
	}

	public void setListeAvionAttentePiste(LinkedList<Avion> listeAvionAttentePiste) {
		this.listeAvionAttentePiste = listeAvionAttentePiste;
	}

	@Override
	public String[] getTitles() {
		String[] titre = {"Nombre d'avions en retard"};
		return titre;
	}

	public String affichageListe(LinkedList liste){
		String resultat = " ";
		for (int i=0;i<liste.size();i++){
			resultat+=" "+liste.get(i).toString();
		}
		return resultat;
	}
	
	@Override
	public String[] getRecords() {
		affichageListe(nbAvionAttente);
		return null;
	}

	@Override
	public String getClassement() {
		// TODO Auto-generated method stub
		return "Aeroport";
	}


}
