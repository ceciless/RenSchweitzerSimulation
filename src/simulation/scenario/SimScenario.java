package simulation.scenario;

import enstabretagne.base.time.LogicalDateTime;
import simulation.engine.SimEngine;
import simulation.entity.SimEntity;
import simulation.features.SimFeatures;



public class SimScenario extends SimEntity {
	
	LogicalDateTime startDate;
	LogicalDateTime endDate;
	ScenarioId scenarioId;
	
	public SimScenario(SimEngine engine, ScenarioId id, SimFeatures simFeature,LogicalDateTime startDate, LogicalDateTime endDate) {
		super(engine, simFeature);
		// TODO Auto-generated constructor stub
		this.startDate=startDate;
		this.endDate=endDate;
		this.scenarioId=id;
	}
	
	public LogicalDateTime getStartDate() {
		return startDate;
	}

	
	public ScenarioId getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(ScenarioId scenarioId) {
		this.scenarioId = scenarioId;
	}

	public void setStartDate(LogicalDateTime startDate) {
		this.startDate = startDate;
	}

	public LogicalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LogicalDateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
	}
	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		super.deactivate();
	}
	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		super.terminate();
	}
	

}
