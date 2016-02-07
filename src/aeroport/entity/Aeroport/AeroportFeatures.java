package aeroport.entity.Aeroport;

import java.util.List;

import aeroport.entity.Avion.AvionFeatures;
import aeroport.entity.Gate.GateFeatures;
import aeroport.entity.Piste.PisteFeatures;
import aeroport.entity.Taxiway.TaxiwayFeatures;

import simulation.features.SimFeatures;


public class AeroportFeatures extends SimFeatures {
	
	List<TaxiwayFeatures> listeTaxiwayFeatures;
	List<GateFeatures> listeGateFeatures;
	PisteFeatures pisteFeatures;
	String debutArriveeAvion;
	String finArriveeAvion;
	AvionFeatures avionFeatures;
	
	
	public List<TaxiwayFeatures> getListeTaxiwayFeatures() {
		return listeTaxiwayFeatures;
	}

	public void setListeTaxiwayFeatures(List<TaxiwayFeatures> listeTaxiwayFeatures) {
		this.listeTaxiwayFeatures = listeTaxiwayFeatures;
	}

	public List<GateFeatures> getListeGateFeatures() {
		return listeGateFeatures;
	}

	public void setListeGateFeatures(List<GateFeatures> listeGateFeatures) {
		this.listeGateFeatures = listeGateFeatures;
	}

	public AvionFeatures getAvionFeatures() {
		return avionFeatures;
	}

	public void setAvionFeatures(AvionFeatures avionFeatures) {
		this.avionFeatures = avionFeatures;
	}

	public void setDebutArriveeAvion(String debutArriveeAvion) {
		this.debutArriveeAvion = debutArriveeAvion;
	}

	public void setFinArriveeAvion(String finArriveeAvion) {
		this.finArriveeAvion = finArriveeAvion;
	}

	public AeroportFeatures(String id,
			PisteFeatures pisteFeatures,
			List<TaxiwayFeatures> listeTaxiwayFeatures,
			List<GateFeatures> listeGateFeatures,
			String heureOuverture,
			String heureFermeture,
			AvionFeatures avionFeatures){
		super(id);
		this.listeGateFeatures=listeGateFeatures;
		this.listeTaxiwayFeatures=listeTaxiwayFeatures;
		this.pisteFeatures=pisteFeatures;
		this.avionFeatures=avionFeatures;
	}

	public List<TaxiwayFeatures> getListTaxiwayFeatures() {
		return listeTaxiwayFeatures;
	}

	public void setListTaxiwayFeatures(List<TaxiwayFeatures> listTaxiwayFeatures) {
		this.listeTaxiwayFeatures = listTaxiwayFeatures;
	}

	public List<GateFeatures> getListGateFeatures() {
		return listeGateFeatures;
	}

	public void setListGateFeatures(List<GateFeatures> listGateFeatures) {
		this.listeGateFeatures = listGateFeatures;
	}

	public PisteFeatures getPisteFeatures() {
		return pisteFeatures;
	}

	public void setPisteFeatures(PisteFeatures pisteFeatures) {
		this.pisteFeatures = pisteFeatures;
	}
	
	public String getDebutArriveeAvion(){
		return debutArriveeAvion;
	}
	
	public String getFinArriveeAvion(){
		return finArriveeAvion;
	}
			
}
