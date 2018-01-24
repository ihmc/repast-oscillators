package us.ihmc.simon.repositories;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;

public class HistoricalRepository extends Repository {
	/**
	 * 
	 */

	private int timer;
	private int duration;
	private double dailyMovement;
	
	public HistoricalRepository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		super(space,id,data);
		timer = 0;
		duration = 0;
		dailyMovement=0;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		if(timer == 0 ){
			if(hasNext()){
				int gap = getNext().intValue();
				duration = gap;
				dailyMovement = 1.0/gap;
				timer = gap - 1;
			} else {
				RunEnvironment.getInstance().endRun();
			}
		} else {
			timer--;
		}
		double time = (duration - timer) * dailyMovement * Math.PI * 2;

		System.out.println("His Time:\t" + time);
		
	    setX(100 + getRadius() * Math.cos(getPhase() + time));
	    setY(100 + getRadius() * -Math.sin(getPhase() + time)); // counter-clockwise
	    getSpace().moveTo(this, getX(), getY());
	}
	
}
