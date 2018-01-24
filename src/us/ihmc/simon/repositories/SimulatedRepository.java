package us.ihmc.simon.repositories;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;

public class SimulatedRepository extends Repository {
	/**
	 * 
	 */

	public SimulatedRepository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		super(space,id,data);
	}
	
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		double time = getState().getCurrentSchedule().getTickCount() / data.getMean()  * Math.PI * 2;
		System.out.println("Sim Time:\t" + time);
	    setX(100 + getRadius() * Math.cos(getPhase() + time));
	    setY(100 + getRadius() * -Math.sin(getPhase() + time)); // counter-clockwise
	    getSpace().moveTo(this, getX(), getY());
	}
}
