package aeroport.entity.Taxiway;

import simulation.features.SimFeatures;

public class TaxiwayFeatures extends SimFeatures {

	int numeroTaxiway;


	public TaxiwayFeatures(String id, int numeroTaxiway) {
		super(id);
		this.numeroTaxiway=numeroTaxiway;
	}


	public int getNumeroTaxiway() {
		return numeroTaxiway;
	}
	public void setNumeroTaxiway(int numeroTaxiway) {
		this.numeroTaxiway = numeroTaxiway;
	}
}
