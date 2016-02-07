package aeroport.entity.Taxiway;

import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import enstabretagne.base.messages.Messages;

import org.omg.PortableServer.THREAD_POLICY_ID;

import aeroport.entity.Taxiway.ITaxiway;
import simulation.engine.SimEngine;
import simulation.entity.SimEntity;
import simulation.features.SimFeatures;

public class Taxiway extends SimEntity implements ITaxiway, IRecordable {
	
	TaxiwayFeatures taxiwayFeatures;
	String nom;
	boolean estLibre;
	int numero;
	
	public Taxiway(SimEngine engine, SimFeatures features) {
		super(engine, features);
		this.taxiwayFeatures=(TaxiwayFeatures) features;
		this.nom=taxiwayFeatures.getName();
		this.estLibre=true;
		this.numero=taxiwayFeatures.getNumeroTaxiway();
		
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		Logger.Information(this,"",Messages.CreationTaxiway);
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		Logger.Information(this,"",Messages.TaxiwayActive);
	}
	


	public TaxiwayFeatures getTaxiwayFeatures() {
		return taxiwayFeatures;
	}

	public void setTaxiwayFeatures(TaxiwayFeatures taxiwayFeatures) {
		this.taxiwayFeatures = taxiwayFeatures;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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
		return "Taxiway";
	}

}
