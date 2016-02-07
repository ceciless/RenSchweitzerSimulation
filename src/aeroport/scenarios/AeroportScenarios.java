package aeroport.scenarios;

import java.util.List;

import aeroport.entity.Aeroport.Aeroport;
import aeroport.entity.Avion.Avion;
import aeroport.entity.Avion.AvionFeatures;
import aeroport.entity.Avion.EtatAvion;
import aeroport.utils.Converter;
import aeroport.utils.FonctionUtils;
import aeroport.scenarios.AeroportScenarios;

import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;

import simulation.engine.SimEngine;
import simulation.event.SimEvent;
import simulation.features.SimFeatures;
import simulation.scenario.ScenarioId;
import simulation.scenario.SimScenario;
import simulation.core.SimObject;

public class AeroportScenarios extends SimScenario implements IRecordable{

	MoreRandom random;
	double lambdaArriveeAvion;
	double lambdaRoulement;
	LogicalDateTime aujourdhui;
	Aeroport aeroport;
	AeroportScenariosFeatures aeroportScenariosFeatures;
	LogicalDateTime debutArriveeAvion;
	LogicalDateTime finArriveeAvion;
	String finAvion;
	String debutAvion;
	LogicalDateTime debutSimulation;
	LogicalDateTime finSimulation;
	String[] debutPeriodeDePointe;
	String[] finPeriodeDePointe;
	int nbAvion;

	public AeroportScenarios(SimEngine engine, ScenarioId id, SimFeatures features, LogicalDateTime start,
			LogicalDateTime end) {
		super(engine, id, features, start, end);
		this.aeroportScenariosFeatures = (AeroportScenariosFeatures)features;
		this.aujourdhui=start;
		random = new MoreRandom(MoreRandom.globalSeed);
		lambdaArriveeAvion = aeroportScenariosFeatures.getFrequenceArriveeAvionParHeure()/3600;
		debutArriveeAvion = aujourdhui.add(LogicalDuration.fromString(aeroportScenariosFeatures.getDebutArriveeAvion()));
		finArriveeAvion = aujourdhui.add(LogicalDuration.fromString(aeroportScenariosFeatures.getFinArriveeAvion()));
		this.debutSimulation = start;
		this.finSimulation = end;
		this.debutPeriodeDePointe=aeroportScenariosFeatures.getDebutPeriodeDePointe();
		this.finPeriodeDePointe=aeroportScenariosFeatures.getFinPeriodeDePointe();
		nbAvion=0;
	}
	
	
	
	public AeroportScenariosFeatures getAeroportScenariosFeatures() {
		return aeroportScenariosFeatures;
	}

	public void setAeroportScenariosFeatures(AeroportScenariosFeatures aeroportScenariosFeatures) {
		this.aeroportScenariosFeatures = aeroportScenariosFeatures;
	}


	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		postEvent(new DebutSimulation(),debutSimulation);
	}

	
	class DebutSimulation extends SimEvent{
		@Override
		public void process() {
			Logger.Information(AeroportScenarios.this,"","Debut de la simulation");
			AeroportScenarios.this.aujourdhui=AeroportScenarios.this.debutSimulation;
			AeroportScenarios.this.aeroport = new Aeroport(getEngine(), aeroportScenariosFeatures.getAeroportFeatures());
			AeroportScenarios.this.aeroport.initialize();
			AeroportScenarios.this.aeroport.activate();
			postEvent( new DebutArriveeAvion(),debutArriveeAvion);
			postEvent(new FinSimulation(), AeroportScenarios.this.getFinSimulation());
		}		
	}
	
	class FinSimulation extends SimEvent{

		@Override
		public void process() {
			// TODO Auto-generated method stub
			Logger.Information(AeroportScenarios.this,"","Fin de la simulation");
		}
		
		
	}

	class DebutArriveeAvion extends SimEvent{
		
		@Override
		public void process(){
/*			LogicalDateTime d = getProchainAvion();
			if(d!=null) postEvent(new NouveauAvionEvent(),d);*/
			postEvent(new NouveauAvionEvent(),currentDate().add(LogicalDuration.ofMinutes(10)));
			postEvent(new FinArriveeAvion(),AeroportScenarios.this.finArriveeAvion);			
			Logger.Information(AeroportScenarios.this,"", "Debut Arrivee des Avions");
		}
	}
	class FinArriveeAvion extends SimEvent{

		@Override
		public void process() {
			//postEvent(new DebutArriveeAvion(),aujourdhui.add(LogicalDuration.fromString(debutAvion)));			
			Logger.Information(AeroportScenarios.this, "", "Fin Arrivee Nouveaux Avions" );
		}
	}
	
	class NouveauAvionEvent extends SimEvent {
		int dureeProchainAvion;
		
		@Override
		public void process(){
/*			if (LogicalDuration.soustract(currentDate(), aujourdhui).equals(finArriveeAvion)){
				double delaiArrivee = 0;
			}
			if (Converter.isWeekEndDay(aujourdhui)) {
				double delaiArrivee=random.nextDouble()*10*60;
				
			}
			else {
				for (int i=0; i<AeroportScenarios.this.getDebutPeriodePointe().length;i++){
					if (!(LogicalDuration.soustract(currentDate(), aujourdhui)).equals(finPeriodeDePointe)){
						double delaiArrivee=random.nextDouble()*10*60;
					}
					else {
						double delaiArrivee=random.nextDouble()*20*60;
					}
				}
			}*/
			Avion avion = new Avion(getEngine(),AeroportScenarios.this.getAeroportScenariosFeatures().getAeroportFeatures().getAvionFeatures());
			avion.setName(FonctionUtils.avionName(AeroportScenarios.this.nbAvion));
			AeroportScenarios.this.nbAvion++;
			avion.initialize();
			avion.activate();
			avion.setAeroport(AeroportScenarios.this.aeroport);
			AeroportScenarios.this.getAeroport().notificationVolArrive(avion);
/*			Logger.Information(AeroportScenarios.this, "", "Nouvel Avion");
			LogicalDateTime d = getProchainAvion();
			if(d!=null) postEvent(new NouveauAvionEvent(),d);*/
		}
	}
	
	public LogicalDateTime getProchainAvion() {
		double d = random.nextExp(lambdaArriveeAvion);
		LogicalDuration t = LogicalDuration.ofSeconds(d);
		LogicalDateTime prochaineFinArriveeAvion = currentDate().finArriveeAvion);
		LogicalDateTime possibleArriveeAvion = currentDate().add(t);
		if (possibleArriveeAvion.compareTo(prochaineFinArriveeAvion)<0)
			return possibleArriveeAvion;
		else return null;
	}

	public Aeroport getAeroport() {
		return aeroport;
	}

	public String[] getDebutPeriodePointe() {
		return debutPeriodeDePointe;
	}

	public String[] getFinPeriodeDePointe() {
		return finPeriodeDePointe;
	}

	public void setFinPeriodeDePointe(String[] finPeriodeDePointe) {
		this.finPeriodeDePointe = finPeriodeDePointe;
	}

	


	public LogicalDateTime getDebutArriveeAvion() {
		return debutArriveeAvion;
	}



	public void setDebutArriveeAvion(LogicalDateTime debutArriveeAvion) {
		this.debutArriveeAvion = debutArriveeAvion;
	}



	public LogicalDateTime getFinArriveeAvion() {
		return finArriveeAvion;
	}



	public void setFinArriveeAvion(LogicalDateTime finArriveeAvion) {
		this.finArriveeAvion = finArriveeAvion;
	}



	public LogicalDateTime getDebutSimulation() {
		return debutSimulation;
	}

	public void setDebutSimulation(LogicalDateTime debutSimulation) {
		this.debutSimulation = debutSimulation;
	}

	public LogicalDateTime getFinSimulation() {
		return finSimulation;
	}

	public void setFinSimulation(LogicalDateTime finSimulation) {
		this.finSimulation = finSimulation;
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
		return "AeroportScenarios";
	}
	
}
