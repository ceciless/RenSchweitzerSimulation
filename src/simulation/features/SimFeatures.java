package simulation.features;

public abstract class SimFeatures {
	
	private String name;

	public String getName() {
		return name;
	}

	public SimFeatures(String name) {
		this.name = name;
	}
	public void setName(String name){
		this.name=name;
	}
}
