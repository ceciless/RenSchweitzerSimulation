package simulation.monitor;

import java.util.HashMap;
import java.util.List;
import enstabretagne.base.utility.Logger;
import simulation.engine.SimEngine;
import simulation.scenario.SimScenario;


public abstract class AbstractMonitor {

	private SimScenario currentScenario;
	public SimScenario getCurrentScenario(){
		return currentScenario;
	}

	protected void setCurrentScenario(SimScenario currentScenario) {
		this.currentScenario = currentScenario;
	}

	protected SimEngine engine;
	HashMap<String,HashMap<String,Object>> loggersNames;

	public SimEngine getEngine() {
		return engine;
	}
	
	public AbstractMonitor(HashMap<String,HashMap<String,Object>> loggersNames)
	{
		this.loggersNames=loggersNames;	
		Logger.Init(loggersNames,true);

	}
	
	public abstract void run(SimScenario s,int repliqueNumber);
	public abstract void run(List<SimScenario> listeScenario,int repliqueNumbers);
	public abstract void terminate(boolean restart);
}


