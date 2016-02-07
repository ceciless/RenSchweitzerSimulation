package simulation.entity;

import java.util.HashSet;
import java.util.Set;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import simulation.entity.EntityState;
import simulation.core.SimObject;
import simulation.engine.SimEngine;
import simulation.event.ISimEvent;
import simulation.event.SimEvent;
import simulation.features.SimFeatures;

public class SimEntity extends SimObject{
	
	private EntityState state;
	private Set<ISimEvent> events = new HashSet<>();
	private SimEngine engine;
	

	public SimEntity(SimEngine engine, SimFeatures simFeature) {
		super(simFeature.getName());
		this.getClass().getSimpleName();
		this.engine = engine;
		engine.addEntity(this);
		setState(EntityState.BORN);
	}
	
	public void terminate() {
		setState(EntityState.DEAD);
	}
	
	public void initialize() {
		setState(EntityState.IDLE);
	}
	
	public void activate() {
		setState(EntityState.ACTIVE);
	}
	
	public void deactivate() {
		setState(EntityState.BORN);
	}
	
	public void pause() {
		setState(EntityState.IDLE);
	}
	
	public void lock() {
		setState(EntityState.HELD);
	}
	
	public void release() {
		setState(EntityState.ACTIVE);
	}
	
	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}
	
	public void addEvent(ISimEvent event) {
		if (event.scheduleDate().compareTo( engine.getEndDate())<=0){
			events.add(event);
			engine.onEventPosted(event);
		}
	}
	public void addEvent(ISimEvent event, SimEvent maxEvent) {
		if (event.scheduleDate().compareTo(maxEvent.scheduledDate)>=0){
			
			
		}
		events.add(event);
		engine.onEventPosted(event);
	}
	
	public boolean isAffectedBy(ISimEvent event) {
		return events.contains(event);
	}
	public LogicalDateTime currentDate(){
		return engine.simulationDate();
	}
	public void post(SimEvent simEvent, LogicalDuration logicalDuration){
		simEvent.resetProcessDate(currentDate().add(logicalDuration));
		this.addEvent(simEvent);

	}
	public void postEvent(SimEvent simEvent, LogicalDateTime logicalDateTime){
		simEvent.resetProcessDate(logicalDateTime);
		this.addEvent(simEvent);
	}
	public SimEngine getEngine() {
		return engine;
	}
	public void setEngine(SimEngine simEngine){
		this.engine=simEngine;
	}	

}
