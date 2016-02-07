 package simulation.monitor;

import java.util.HashMap;
import java.util.List;

import enstabretagne.base.utility.Logger;
import enstabretagne.monitor.IMonitor;
import simulation.engine.SimEngine;
import simulation.scenario.SimScenario;


public abstract class  MonteCarloMonitor extends AbstractMonitor implements IMonitor {


public MonteCarloMonitor(HashMap<String,HashMap<String,Object>> loggersNames)
{
	super(loggersNames);

}

@Override
public void run(SimScenario s, int replique) {
	setCurrentScenario(s);
	this.engine = new SimEngine();
	engine.init(s,replique);
	getCurrentScenario().initialize();
	getCurrentScenario().activate();
	this.engine.initialize();
	this.engine.resume();
	while (engine.triggerNextEvent()) {
	}	
	this.engine.terminate();
	getCurrentScenario().deactivate();
	getCurrentScenario().terminate();
}

@Override
public void run(List<SimScenario> listeScenario,int repliqueNumber) {
	for(SimScenario scenario : listeScenario) {
		for(int k=0;k<repliqueNumber;k++) {
			run(scenario,k);	
		}
	}
}

@Override
public void terminate(boolean restart) {
	Logger.Terminate();
}

}
