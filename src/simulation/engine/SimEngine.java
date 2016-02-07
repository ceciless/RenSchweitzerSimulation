package simulation.engine;

import java.util.HashSet;
import java.util.Set;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.utility.Logger;
import enstabretagne.simulation.core.SortedList;
import simulation.entity.SimEntity;
import simulation.event.IEventObserver;
import simulation.event.ISimEvent;
import simulation.scenario.ScenarioId;
import simulation.scenario.SimScenario;


public class SimEngine implements ISimulationDateProvider, IEventObserver {

	private LogicalDateTime currentDateTime;
	private SortedList<ISimEvent> echeancier = new SortedList<>();
	private Set<SimEntity> entities = new HashSet<>();
	private SimScenario currentScenario;
	private LogicalDateTime startDate;
	private LogicalDateTime endDate;
	private int replique;
	
    private SimScenario getCurrentScenario() {
		return currentScenario;
	}
	
	public void setCurrentScenario(SimScenario currentScenario) {
		this.currentScenario = currentScenario;
	}

	public SimEngine() {
		
	}
	
	public void init(SimScenario simScenario, int replique){
		setCurrentScenario(simScenario);
    	this.currentScenario.setEngine(this);
    	this.startDate=simScenario.getStartDate();
    	this.endDate=simScenario.getEndDate();
        this.currentDateTime = startDate;
        this.replique=replique;
        Logger.setScenarioId(new ScenarioId(simScenario.getScenarioId().getScenarioId(),replique)); 
	}
	
	public ScenarioId getScenarioId(){
		return this.getCurrentScenario().getScenarioId();
	}
	
	@Override
	public LogicalDateTime simulationDate() {
		return currentDateTime;
	}
	
	@Override
	public void onEventPosted(ISimEvent event) {
		echeancier.add(event);
	}
	
	public void initialize() {
		for (SimEntity entity : entities)
			entity.initialize();
	}
	
	public void pause() {
		for (SimEntity entity : entities)
			entity.pause();
	}
	
	public void resume() {
		for (SimEntity entity : entities)
			entity.activate();
	}
	public void terminate(){
		for (SimEntity entity : entities)
			entity.terminate();
	}
	
	public boolean triggerNextEvent() {
		// TODO add maxTime check
		if (echeancier.size() == 0) {
			for (SimEntity entity : entities)
				entity.terminate();
			return false;
		}
		ISimEvent nextEvent = echeancier.first();
		echeancier.remove(nextEvent);
		currentDateTime = nextEvent.scheduleDate();
		for (SimEntity entity : entities) {
			if (entity.isAffectedBy(nextEvent))
				entity.lock();
		}
		nextEvent.process();
		for (SimEntity entity : entities) {
			if (entity.isAffectedBy(nextEvent))
				entity.release();
		}
		return true;
	}

	public void addEntity(SimEntity simEntity) {
		entities.add(simEntity);
	}

	public LogicalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LogicalDateTime endDate) {
		this.endDate = endDate;
	}

	public Set<SimEntity> getEntities() {
		return entities;
	}

	public void setEntities(Set<SimEntity> entities) {
		this.entities = entities;
	}

	public int getReplique() {
		return replique;
	}

	public void setReplique(int replique) {
		this.replique = replique;
	}
	
	
}
