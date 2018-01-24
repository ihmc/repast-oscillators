package us.ihmc.simon.repositories;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;

public abstract class Repository {
	/**
	 * 
	 */
	private ContinuousSpace<Repository> space;
	private double radius = 40;
	private RunEnvironment state;
	private double dy;
	private double dx;
	private double phase;
	String id;
	RepositoryData data;

	public Repository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		this.space = space;
		state = RunEnvironment.getInstance();
		this.id = id;
		dx =  100 + radius * Math.cos(0);
	    dy = 100 + radius * -Math.sin(0);
		this.data = data;
		this.phase = 0;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public abstract void step();
	
	public String getId(){
		return id;
	}

	public void setY(double y){
		this.dy = y;
	}
	public double getY(){
		return dy;
	}
	
	public void setX(double x){
		this.dx = x;
	}

	public double getX(){
		return dx;
	}

	public double getRadius(){
		return radius;
	}
	
	public ContinuousSpace<Repository> getSpace(){
		return space;
	}

	public RunEnvironment getState(){
		state.setScheduleTickDelay(4);
		return state;
	}
	
	public void setPhase(double phase){
		this.phase = phase;
	}
	
	public double getPhase(){
		return phase;
	}
	
	public boolean hasNext(){
		return data.hasNext();
	}
	
	public Integer getNext(){
		return data.getNext();
	}
	

}
