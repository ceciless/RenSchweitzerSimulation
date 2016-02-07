package simulation.scenario;

public class ScenarioId {
	
	String scenarioId;
	long repliqueNumber;
	
	public ScenarioId(String scenarioId, long repliqueNumber){
		this.scenarioId=scenarioId;
		this.repliqueNumber=repliqueNumber;
	}
	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}
	public long getRepliqueNumber() {
		return repliqueNumber;
	}
	public void setRepliqueNumber(long repliqueNumber) {
		this.repliqueNumber = repliqueNumber;
	}
	
	public final static ScenarioId ScenarioID_NULL = new ScenarioId("DefaultScenario",0);

	
}
