package us.ihmc.simon.repositories;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;

public class Repository {
	/**
	 * 
	 */
	private ContinuousSpace<Repository> space;
	private double radius = 40;
	private RunEnvironment state;
	private double dy;
	private double dx;
	String id;
	double phase;

	public Repository(ContinuousSpace<Repository> space, String id, double phase) {
		this.space = space;
		state = RunEnvironment.getInstance();
		this.id = id;
		dx =  100 + radius * Math.cos(phase);
	    dy = 100 + radius * -Math.sin(phase);
		this.phase = phase;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		NdPoint myPoint = space.getLocation(this);
		double time = state.getCurrentSchedule().getTickCount() * 2 * Math.PI / 360.0;

	    dx = 100 + radius * Math.cos(phase + time);
	    dy = 100 + radius * -Math.sin(phase + time); // counter-clockwise
	    
	    System.out.println("dx:\t" + dx + "\tdy:\t" + dy + "\tx:\t" + myPoint.getX() + "\ty:\t" + myPoint.getY());
	    
	    NdPoint otherPoint = new NdPoint(dx, dy);
	    space.moveTo(this, otherPoint.getX(), otherPoint.getY());

	}
	
	public String getId(){
		return id;
	}
	
	public double getY(){
		return dy;
	}

}
