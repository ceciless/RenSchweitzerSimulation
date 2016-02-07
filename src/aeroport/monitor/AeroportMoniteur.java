package aeroport.monitor;


import java.time.DayOfWeek;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aeroport.entity.Aeroport.AeroportFeatures;
import aeroport.entity.Avion.AvionFeatures;
import aeroport.entity.Gate.GateFeatures;
import aeroport.entity.Piste.PisteFeatures;
import aeroport.entity.Taxiway.TaxiwayFeatures;
import aeroport.scenarios.AeroportScenarios;
import aeroport.scenarios.AeroportScenariosFeatures;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.LoggerParamsNames;
import enstabretagne.base.utility.loggerimpl.SXLSXExcelDataloggerImpl;
import enstabretagne.base.utility.loggerimpl.SysOutLogger;
import enstabretagne.monitor.IMonitor;
import simulation.engine.SimEngine;
import simulation.monitor.MonteCarloMonitor;
import simulation.scenario.ScenarioId;
import simulation.scenario.SimScenario;


public class AeroportMoniteur extends MonteCarloMonitor  implements IMonitor {
	
	
	public AeroportMoniteur(HashMap<String, HashMap<String, Object>> loggersNames) {
		super(loggersNames);
	}

	public static void main(String[] args) {
		
		System.out.println("Debut =" + Instant.now());

		//Configuration requise pour le logger
		HashMap<String,HashMap<String,Object>> loggersNames = new HashMap<String,HashMap<String,Object>>();
		loggersNames.put(SysOutLogger.class.getCanonicalName(),new HashMap<String,Object>());
		
		HashMap<String,Object> xlsxParams=new HashMap<String,Object>();		
		xlsxParams.put(LoggerParamsNames.DirectoryName.toString(), System.getProperty("user.dir") + "/data/");
		xlsxParams.put(LoggerParamsNames.FileName.toString(), "Aeroport.xlsx");
		loggersNames.put(SXLSXExcelDataloggerImpl.class.getCanonicalName(),xlsxParams);
		
		//création du moniteur
		AeroportMoniteur aeroportMonitor = new AeroportMoniteur(loggersNames);


		//Déclaration des données qui serviront à l'initialisation du scénario
		LogicalDateTime start = new LogicalDateTime("01/09/2014 00:00");
		
		long nbJoursSimu = 3;
		int nbReplique =1;
		String debutArriveeAvion="07:00";
		String finArriveeAvion="22:00";
		String[] debutPeriodeDePointe = {"07:00","17:00"};
		String[] finPeriodeDePointe = {"10:00","19:00"};
		double intervalArriveeAvionParHeure = 10.0;
		int i1 = 1; int i2 = 2;
		double lambdaRoulement = 20;
		int nbGate = 6;
		
		// en minutes
		double dureeApproche = 5.0;
		
		PisteFeatures pisteFeatures = new PisteFeatures("Piste");
		
		List<TaxiwayFeatures> listeTaxiwayFeatures = new ArrayList<TaxiwayFeatures>();
		listeTaxiwayFeatures.add(new TaxiwayFeatures("Taxiway1", i1));
		listeTaxiwayFeatures.add(new TaxiwayFeatures("Taxiway2", i2));
		
		List<GateFeatures> listeGateFeatures = new ArrayList<GateFeatures>();
		for (int i=1; i<=nbGate; i++){
			listeGateFeatures.add(new GateFeatures("Gate"+String.valueOf(i)));
		}

		AvionFeatures avionFeatures = new AvionFeatures("F00",dureeApproche, lambdaRoulement);
		
		List<SimScenario> listeScenario = new ArrayList<SimScenario>();
		
		AeroportFeatures aeroportFeatures = new AeroportFeatures(
				"BrestAeroport",
				pisteFeatures,
				listeTaxiwayFeatures,
				listeGateFeatures,
				debutArriveeAvion,
				finArriveeAvion,
				avionFeatures);
		
		AeroportScenariosFeatures aeroportScenarioFeatures = new AeroportScenariosFeatures(
				"premierAeroportScenario",
				debutArriveeAvion,
				finArriveeAvion,
				debutPeriodeDePointe,
				finPeriodeDePointe,
				aeroportFeatures );
		
		AeroportScenarios aeroportScenario = new AeroportScenarios(
				new SimEngine(),
				new ScenarioId("premierScenario",nbReplique),
				aeroportScenarioFeatures,
				start,
				start.add(LogicalDuration.ofDay(nbJoursSimu))
				);
		listeScenario.add(aeroportScenario);
		
		aeroportMonitor.run(listeScenario, nbReplique);
		aeroportMonitor.terminate(false);
		System.out.println("fin de la simulation a l'instant "+Instant.now());
		

	}

	@Override
	public Temporal InitialCalendarDate() {
		// TODO Auto-generated method stub
		return null;
	}

		
}

