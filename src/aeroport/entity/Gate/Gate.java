package aeroport.entity.Gate;

import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import enstabretagne.base.messages.Messages;
import aeroport.entity.Gate.IGate;
import simulation.engine.SimEngine;
import simulation.entity.SimEntity;
import simulation.features.SimFeatures;

public class Gate extends SimEntity implements IGate, IRecordable {
	boolean estLibre;
	
	public boolean getDisponibilite(){
		return estLibre;
	}
	
	public boolean setDisponibilite(boolean estLibre){
		this.estLibre=estLibre;
		return estLibre;
	}
	
	public Gate(SimEngine engine, SimFeatures features){
		super(engine,features);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		Logger.Information(this, "", Messages.CreationGate);
	}
	
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		Logger.Information(this, "", Messages.GateActive);
		setDisponibilite(true);
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
		return "Gate";
	}
	
}
