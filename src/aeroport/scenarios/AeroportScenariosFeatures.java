package aeroport.scenarios;

import aeroport.entity.Aeroport.AeroportFeatures;
import simulation.features.SimFeatures;

public class AeroportScenariosFeatures extends SimFeatures{
	double frequenceArriveeAvionParHeure;
	String debutArriveeAvion;
	String finArriveeAvion;
	AeroportFeatures aeroportFeatures;
	String[]debutPeriodeDePointe;
	String[]finPeriodeDePointe;
		
	public AeroportScenariosFeatures(String id,
			String debutArriveeAvion,
			String finArriveeAvion, 
			String[]debutPeriodeDePointe,
			String[]finPeriodeDePointe,
			AeroportFeatures aeroportFeatures){
		super(id);
		this.debutArriveeAvion = debutArriveeAvion;
		this.finArriveeAvion = finArriveeAvion;
		this.aeroportFeatures = aeroportFeatures;

	}


	
	public AeroportFeatures getAeroportFeatures() {
		return aeroportFeatures;
	}

	public void setAeroportFeatures(AeroportFeatures aeroportFeatures) {
		this.aeroportFeatures = aeroportFeatures;
	}

	public void setFrequenceArriveeAvionParHeure(
			double frequenceArriveeAvionParHeure) {
		this.frequenceArriveeAvionParHeure = frequenceArriveeAvionParHeure;
	}

	public void setDebutArriveeAvion(String debutArriveeAvion) {
		this.debutArriveeAvion = debutArriveeAvion;
	}

	public void setFinArriveeAvion(String finArriveeAvion) {
		this.finArriveeAvion = finArriveeAvion;
	}

	public String getDebutArriveeAvion() {
		return debutArriveeAvion;
	}

	public String getFinArriveeAvion() {
		return finArriveeAvion;
	}

	public double getFrequenceArriveeAvionParHeure() {
		return frequenceArriveeAvionParHeure;
	}

	public String[] getDebutPeriodeDePointe() {
		return debutPeriodeDePointe;
	}

	public void setDebutPeriodeDePointe(String[] debutPeriodeDePointe) {
		this.debutPeriodeDePointe = debutPeriodeDePointe;
	}

	public String[] getFinPeriodeDePointe() {
		return finPeriodeDePointe;
	}

	public void setFinPeriodeDePointe(String[] finPeriodeDePointe) {
		this.finPeriodeDePointe = finPeriodeDePointe;
	}

}
