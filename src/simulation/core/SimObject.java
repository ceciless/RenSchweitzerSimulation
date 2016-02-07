package simulation.core;

public class SimObject extends Object{
	String name;
	
	protected SimObject (String name){
		this.name=name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
