package aeroport.entity.Gate;

import simulation.features.SimFeatures;

public class GateFeatures extends SimFeatures{
	
	int numeroGate;
	int nbGate=6;
	public int getNumeroGate(){
		return numeroGate;
	}
	
	public GateFeatures(String id) {
		super(id);
		this.numeroGate=numeroGate;
	}

}
